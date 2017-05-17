package dan_art.sknowcoin.modelo;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dan_art.sknowcoin.Firebase.Autenticacion;
import dan_art.sknowcoin.Firebase.ConexionFirebase;


/**
 * Created by Luisf0425 on 27/04/17.
 */

public class SKnowCoinApp {

    public static final String MyPREFERENCES = "MyPrefs";

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
        conexionFirebase.getDatabaseReference().child(conexionFirebase.RANKING_REFERENCE).child(usuario.getCodigo()).setValue(0);

        return autenticacion.signUp(usuario.getCorreo(), usuario.getContrasena(), contexto);

    }

    public boolean loginUusuario(String correo, String contrasena, Context contexto) {

        return autenticacion.signIn(correo, contrasena, contexto);
    }

    public void publicarTutoria(Tutoria tutoria) {

        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).push().setValue(tutoria);
    }

    public void recalcularRankingUsuario(final String codigo, final int calificacion) {

        conexionFirebase.getDatabaseReference().child(conexionFirebase.RANKING_REFERENCE).child(codigo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int ranking = dataSnapshot.getValue(Integer.class);
                ranking = (ranking + calificacion)/2;
                conexionFirebase.getDatabaseReference().child(conexionFirebase.RANKING_REFERENCE).child(codigo).setValue(ranking);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: ");
            }
        });


    }

    public ArrayList<Tutoria> listarTutoriasPorTutor(String nombre) {
        final ArrayList<Tutoria> tutorias=new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("nombreTutor").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Tutoria tutoria=postSnapshot.getValue(Tutoria.class);
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

    public ArrayList<String> listarAreas(){
        final ArrayList<String> areas=new ArrayList<String>();
        conexionFirebase.getDatabaseReference().child("areas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String area=postSnapshot.getKey();
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
        final ArrayList<Tutoria> tutorias=new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("materia").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Tutoria tutoria=postSnapshot.getValue(Tutoria.class);
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
        final ArrayList<Tutoria> tutorias=new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("area").equalTo(nombre).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Tutoria tutoria=postSnapshot.getValue(Tutoria.class);
                    tutorias.add(tutoria);
                }
                Log.d("test",tutorias.get(0).getNombreTutor());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return tutorias;
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
