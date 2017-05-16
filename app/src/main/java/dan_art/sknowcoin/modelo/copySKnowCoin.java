package dan_art.sknowcoin.modelo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dan_art.sknowcoin.Firebase.Autenticacion;
import dan_art.sknowcoin.Firebase.ConexionFirebase;

/**
 * Created by VAIO PRO on 04/05/2017.
 */

public class copySKnowCoin {

    public static final String MyPREFERENCES = "MyPrefs";

    //private Usuario usuario;
    private Tutoria tutoria;
    private SharedPreferences sharedpreferences;

    private ConexionFirebase conexionFirebase;
    private Autenticacion autenticacion;
    //private String var;

    private ArrayList<Usuario> tutores;

    public copySKnowCoin() {

        conexionFirebase = new ConexionFirebase();
        autenticacion = new Autenticacion();
        //tutores=new ArrayList<Usuario>();
    }

    //public void registrarUsuario(String nombre, String telefono, String correo, String codigo, String contrasena,
  //                               String area, String semestre, int rol, Context contexto) {

//        Usuario usuario = new Usuario(area, codigo, correo, edad, nombre, rol, semestre, telefono);

    //    conexionFirebase.getDatabaseReference().child(conexionFirebase.USUARIOS_REFERENCE).child(codigo).setValue(usuario);

//        autenticacion.signUp(correo, contrasena, contexto);

  //  }

    public ArrayList<Tutoria> listarPorTutor(String nombre) {
        final ArrayList<Tutoria> tutorias=new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("nombreTutor").equalTo(nombre).addValueEventListener(new ValueEventListener() {
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

    public ArrayList<Tutoria> listarPorMateria(String nombre) {
        final ArrayList<Tutoria> tutorias=new ArrayList<Tutoria>();
        conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).orderByChild("materia").equalTo(nombre).addValueEventListener(new ValueEventListener() {
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

    public ArrayList<Usuario> getTutores() {
        return tutores;
    }

    public Tutoria getTutoria() {
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }
}

