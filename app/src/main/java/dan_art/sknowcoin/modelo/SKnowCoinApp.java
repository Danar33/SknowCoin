package dan_art.sknowcoin.modelo;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dan_art.sknowcoin.Firebase.Autenticacion;
import dan_art.sknowcoin.Firebase.ConexionFirebase;
import dan_art.sknowcoin.layout_handlers.MainActivity;


/**
 * Created by Luisf0425 on 27/04/17.
 */

public class SKnowCoinApp {

    private Usuario usuario;
    private Tutoria tutoria;

    private ConexionFirebase conexionFirebase;
    private Autenticacion autenticacion;

    public SKnowCoinApp() {

        conexionFirebase = new ConexionFirebase();
        autenticacion = new Autenticacion();
    }

    public boolean registrarUsuario(Usuario usuario, Context contexto) {


        conexionFirebase.getDatabaseReference().child(conexionFirebase.USUARIOS_REFERENCE).child(usuario.getCodigo()).setValue(usuario);
        conexionFirebase.getDatabaseReference().child(conexionFirebase.RANKING_REFERENCE).child(usuario.getCodigo()).setValue(5);

        return autenticacion.signUp(usuario.getCorreo(), usuario.getContrasena(), contexto);

    }

    public String publicarTutoria(Tutoria tutoria, String push2) {

        if (push2.isEmpty()) {
            String push = conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).push().getKey();
            return push;
        } else {
            conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).child(push2).setValue(tutoria);
            return "";
        }
    }

    public void recalcularRankingUsuario(final String codigo, final int calificacion) {

        conexionFirebase.getDatabaseReference().child(conexionFirebase.RANKING_REFERENCE).child(codigo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                double ranking = dataSnapshot.getValue(Integer.class);
                ranking = (ranking + calificacion) / 2;

                if (ranking < 3.0) {

                    conexionFirebase.getDatabaseReference().child(conexionFirebase.USUARIOS_REFERENCE).child(codigo).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Usuario usuario = dataSnapshot.getValue(Usuario.class);
                            usuario.setRol(3);

                            conexionFirebase.getDatabaseReference().child(conexionFirebase.USUARIOS_REFERENCE).child(codigo).setValue(usuario);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("The read failed: ");
                        }
                    });

                } else {
                    conexionFirebase.getDatabaseReference().child(conexionFirebase.RANKING_REFERENCE).child(codigo).setValue(ranking);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: ");
            }
        });


    }

    public ArrayList<Tutoria> listarTutoriasPorTutor(String nombre) {
        final ArrayList<Tutoria> tutorias = new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("nombreTutor").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    tutorias.add(tutoria);
                }
                //Log.d("test",tutorias.get(0).getNombreTutor());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return tutorias;
    }

    public ArrayList<String> listarAreas() {
        final ArrayList<String> areas = new ArrayList<String>();
        conexionFirebase.getDatabaseReference().child("areas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String area = postSnapshot.getKey();
                    areas.add(area);
                }
                // Log.d("test",areas.get(0));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return areas;
    }

    public ArrayList<Tutoria> listarTutoriasPorMateria(String nombre) {
        final ArrayList<Tutoria> tutorias = new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("materia").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    tutorias.add(tutoria);
                }
                //Log.d("test",tutorias.get(0).getNombreTutor());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return tutorias;
    }

    public ArrayList<Tutoria> listarTutoriasPorArea(String nombre) {
        final ArrayList<Tutoria> tutorias = new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("area").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    tutorias.add(tutoria);
                }
                Log.d("test", tutorias.get(0).getNombreTutor());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return tutorias;
    }


    public void solicitarTutoria(String codigoUsuario, String idTutoria) {

        PublicacionesUsuario publicacionesUsuario = new PublicacionesUsuario(codigoUsuario, idTutoria);
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_USUARIO_REFERENCE).push().setValue(publicacionesUsuario);
    }

    public int contarSolicitudesTutoria(String codigoTutoria) {

        final ArrayList<PublicacionesUsuario> publicacionesUsuarios = new ArrayList<PublicacionesUsuario>();

        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_USUARIO_REFERENCE).orderByChild("idTutoria").equalTo(codigoTutoria).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    PublicacionesUsuario publicacionesUsuario = postSnapshot.getValue(PublicacionesUsuario.class);
                    publicacionesUsuarios.add(publicacionesUsuario);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return publicacionesUsuarios.size();
    }

    public ArrayList<Tutoria> totalTutorias() {

        final ArrayList<Tutoria> tutorias = new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child("publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    tutorias.add(tutoria);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return tutorias;
    }

    public void dejarReporte(String idTutoria, String problema) {

        Reporte reporte = new Reporte();
        reporte.setEstado(0);
        reporte.setProblema(problema);
        reporte.setIdTutoria(idTutoria);

        conexionFirebase.getDatabaseReference().child(conexionFirebase.REPORTES_REFERENCE).push().setValue(reporte);

    }

    public ArrayList<Reporte> listarReportesPorTutoria(String idTutoria, final int estado, final MainActivity gg) {
        final ArrayList<Reporte> reportes = new ArrayList<>();


        conexionFirebase.getDatabaseReference().child(ConexionFirebase.REPORTES_REFERENCE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    //Log.d("databse error", d.getValue(Reporte.class).getProblema());
                    reportes.add(d.getValue(Reporte.class));

                }
                gg.printResportes();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return reportes;

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tutoria getTutoria() {
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }
}
