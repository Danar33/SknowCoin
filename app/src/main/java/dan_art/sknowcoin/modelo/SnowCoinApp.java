package dan_art.sknowcoin.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class SnowCoinApp {

    private Usuario usuario;
    private Tutoria tutoria;
    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    //hola

    public SnowCoinApp() {

    }

    public void registrarUsuario(String nombre, int telefono, String correo, String contraseña, String codigoEstudiante,
                                    String carreraPrincipal, String semestreActual, Roll roll, int calificacion) {


        Usuario usuario=new Usuario(nombre, telefono, correo, contraseña, codigoEstudiante,
                                    carreraPrincipal, semestreActual, roll, calificacion);

        //mDatabase.child("usuarios").child(codigoEstudiante).setValue(usuario);


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
