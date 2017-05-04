package dan_art.sknowcoin.modelo;

import android.content.SharedPreferences;

import dan_art.sknowcoin.Firebase.ConexionFirebase;


/**
 * Created by Luisf0425 on 27/04/17.
 */

public class SKnowCoinApp {

    public static final String MyPREFERENCES = "MyPrefs";

    private Usuario usuario;
    private Tutoria tutoria;
    private SharedPreferences sharedpreferences;

    private ConexionFirebase conexionFirebase;

    public SKnowCoinApp() {

        conexionFirebase = new ConexionFirebase();
    }

    public void registrarUsuario(String nombre, int telefono, String correo, String contraseña, String codigoEstudiante,
                                 String carreraPrincipal, String semestreActual, int rol, int calificacion) {


        Usuario usuario = new Usuario(nombre, telefono, correo, contraseña, codigoEstudiante,
                carreraPrincipal, semestreActual, rol, calificacion);

        conexionFirebase.getDatabaseReference().child(conexionFirebase.USUARIOS_REFERENCE).child(codigoEstudiante).setValue(usuario);


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
