package dan_art.sknowcoin.modelo;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dan_art.sknowcoin.Firebase.Autenticacion;
import dan_art.sknowcoin.Firebase.ConexionFirebase;
import dan_art.sknowcoin.layout_handlers.BuscarMateriaActivity;
import dan_art.sknowcoin.layout_handlers.DetalleTutoriaActivity;
import dan_art.sknowcoin.layout_handlers.BuscarTutorActivity;
import dan_art.sknowcoin.layout_handlers.HomeActivity;
import dan_art.sknowcoin.layout_handlers.HomeTutorActivity;
import dan_art.sknowcoin.layout_handlers.MainActivity;
import dan_art.sknowcoin.layout_handlers.PerfilUsuarioActivity;


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

    public void subirFotoPerfil(String idUsuario, Uri rutaFoto) {

        Uri file = rutaFoto;
        StorageReference profileRef = conexionFirebase.getStorageRef().child(idUsuario + "/" + file.getLastPathSegment());
        profileRef.putFile(file);
    }

    public File setFotoPerfil(String idUsuario, String nombreImagen) {

        StorageReference fotoRef = conexionFirebase.getStorageRef().child(idUsuario + "/" + nombreImagen + ".jpg");

        File localFile = null;
        try {
            localFile = File.createTempFile(nombreImagen, "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        fotoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        return localFile;
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

    public void listarTutoriasPorTutor(final HomeActivity act, String nombre) {
        final ArrayList<Tutoria> tutorias = new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("nombreTutor").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    //tutorias.add(tutoria);
                    act.getTutoriasDisponibles().add(tutoria);
                }
                act.tutorias();
                //Log.d("test",tutorias.get(0).getNombreTutor());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //return tutorias;
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

    public void listarTutoriasPorMateria(final HomeActivity act, String nombre) {
        //final ArrayList<Tutoria> tutorias = new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("materia").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    //tutorias.add(tutoria);
                    act.getTutoriasDisponibles().add(tutoria);
                }
                act.tutorias();
                //Log.d("test",tutorias.get(0).getNombreTutor());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //return tutorias;
    }

    public void listarTutoriasPorArea(final HomeActivity act, String nombre) {
        //final ArrayList<Tutoria> tutorias = new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("area").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    //tutorias.add(tutoria);
                    act.getTutoriasDisponibles().add(tutoria);
                }
                act.tutorias();
                //  Log.d("test", tutorias.get(0).getNombreTutor());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //return tutorias;
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

    public void dejarReporte(Reporte r) {

        conexionFirebase.getDatabaseReference().child(conexionFirebase.REPORTES_REFERENCE).push().setValue(r);

    }

    public ArrayList<Reporte> listarReportesPorTutoria(String idTutoria, final int estado, final MainActivity gg) {
        final ArrayList<Reporte> reportes = new ArrayList<>();


        conexionFirebase.getDatabaseReference().child(ConexionFirebase.REPORTES_REFERENCE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    reportes.add(d.getValue(Reporte.class));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return reportes;

    }

    public void totalTutoriasUsuarios(final HomeActivity act) {

        conexionFirebase.getDatabaseReference().child("publicaciones").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    act.getTutoriasDisponibles().add(tutoria);
                }
                act.tutorias();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    public void totalTutoriasPorMaterias(final BuscarMateriaActivity act) {
        conexionFirebase.getDatabaseReference().child("publicaciones").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    if (act.getTutoriasMaterias().isEmpty()) {
                        act.getTutoriasMaterias().add(tutoria);
                    } else {
                        for (int i = 0; i < act.getTutoriasMaterias().size(); i++) {

                            if (!(tutoria.getMateria().equals(act.getTutoriasMaterias().get(i).getMateria()))) {
                                act.getTutoriasMaterias().add(tutoria);
                            }
                        }
                    }
                }
                act.materias();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    public void totalTutoriasPorTutor(final BuscarTutorActivity act) {
        conexionFirebase.getDatabaseReference().child("usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Usuario tutor = postSnapshot.getValue(Usuario.class);
                    if (tutor.getRol() > 1) {
                        act.getTutoriasTutores().add(tutor);
                    }
                }
                act.tutores();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    public void totalTutoriasTutor(final HomeTutorActivity act) {

        conexionFirebase.getDatabaseReference().child("publicaciones").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);
                    act.getItemsHomeTutor().add(tutoria);
                }
                act.tutorias();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
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

    public Usuario getUsuarioPorCodigo(String id, final PerfilUsuarioActivity activity) {

        final Usuario[] usuario = new Usuario[1];
        usuario[0] = new Usuario();

        Query q = conexionFirebase.getDatabaseReference().child(ConexionFirebase.USUARIOS_REFERENCE).orderByChild("codigo").equalTo(id);

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    usuario[0] = d.getValue(Usuario.class);
                    activity.pintarInfoUsuario(usuario[0]);
                    break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return usuario[0];

    }

    public void mergeUsuario(Usuario usuario) {

        conexionFirebase.getDatabaseReference().child(ConexionFirebase.USUARIOS_REFERENCE).child(usuario.getCodigo()).setValue(usuario);

    }

    public Tutoria consultarTutoriaPorId(String id, final DetalleTutoriaActivity act) {

        final Tutoria[] tutorias = new Tutoria[1];

        Query q = conexionFirebase.getDatabaseReference().child(ConexionFirebase.PUBLICACIONES_REFERENCE).orderByKey().equalTo(id);

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    tutorias[0] = d.getValue(Tutoria.class);
                    act.setTutoria(tutorias[0]);
                    break;

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return tutorias[0];

    }

}
