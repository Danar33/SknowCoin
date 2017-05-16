package dan_art.sknowcoin.modelo;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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
