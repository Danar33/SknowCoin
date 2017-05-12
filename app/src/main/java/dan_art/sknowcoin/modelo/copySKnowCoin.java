package dan_art.sknowcoin.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dan_art.sknowcoin.Firebase.Autenticacion;
import dan_art.sknowcoin.Firebase.ConexionFirebase;

/**
 * Created by VAIO PRO on 04/05/2017.
 */

public class copySKnowCoin {

    public static final String MyPREFERENCES = "MyPrefs";

    private Usuario usuario;
    private Tutoria tutoria;
    private SharedPreferences sharedpreferences;

    private ConexionFirebase conexionFirebase;
    private Autenticacion autenticacion;
    //private String var;

    public copySKnowCoin() {

        conexionFirebase = new ConexionFirebase();
        autenticacion = new Autenticacion();
    }

    public void registrarUsuario(String nombre, String telefono, String correo, String codigo, String contrasena,
                                 String area, String semestre, int rol, Context contexto) {


        Usuario usuario = new Usuario(nombre, telefono, correo, codigo,
                area, semestre, rol);

        conexionFirebase.getDatabaseReference().child(conexionFirebase.USUARIOS_REFERENCE).child(codigo).setValue(usuario);

        autenticacion.signUp(correo, contrasena, contexto);

    }

    public void listarPorAsignaturas(String area){

        final List<Tutoria> tutorias=new ArrayList<>();

        Object obj = conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).child("A00267576").addValueEventListener(new ValueEventListener() {            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Tutoria tutoria = dataSnapshot.getValue(Tutoria.class);

                tutorias.add(tutoria);

                Log.d("test", tutorias.get(0).toString());

        }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " );
            }
        });
    }

    public void listarPorTutor(String tutor){

        conexionFirebase.getDatabaseReference().child(conexionFirebase.USUARIOS_REFERENCE).orderByChild("nombre").equalTo(tutor).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String var = dataSnapshot.getKey();

                Log.d("test", var);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    public void listarPorMateria(){


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
