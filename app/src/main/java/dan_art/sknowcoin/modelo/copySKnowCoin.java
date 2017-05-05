package dan_art.sknowcoin.modelo;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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

    public copySKnowCoin() {

        conexionFirebase = new ConexionFirebase();
    }

    public void registrarUsuario(String nombre, int telefono, String correo, String contraseña, String codigoEstudiante,
                                 String carreraPrincipal, String semestreActual, int rol, int calificacion) {


        Usuario usuario = new Usuario(nombre, telefono, correo, contraseña, codigoEstudiante,
                carreraPrincipal, semestreActual, rol, calificacion);

        conexionFirebase.getDatabaseReference().child(conexionFirebase.USUARIOS_REFERENCE).child(codigoEstudiante).setValue(usuario);


    }

    public void listarPorAsignaturas(String materia){

        final List<Tutoria> tutorias=new ArrayList<>();
        Object obj = conexionFirebase.getDatabaseReference().child(conexionFirebase.PUBLICACIONES_REFERENCE).
                child("materia").equalTo(materia).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    tutorias.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Tutoria tutoria = postSnapshot.getValue(Tutoria.class);

                    tutorias.add(tutoria);
                }

                Log.d("test",tutorias.get(0).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " );
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
}
