package dan_art.sknowcoin.modelo;

import android.content.Context;
import android.content.SharedPreferences;

import dan_art.sknowcoin.Firebase.Autenticacion;
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
    private Autenticacion autenticacion;

    public SKnowCoinApp() {

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
