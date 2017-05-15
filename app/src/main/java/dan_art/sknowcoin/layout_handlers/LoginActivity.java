package dan_art.sknowcoin.layout_handlers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.SKnowCoinApp;

/**
 * Created by dan_a on 11/05/2017.
 */

public class LoginActivity extends AppCompatActivity {

    public final static String USUARIO_PREFERENCES = "USUARIO_PREFERENCES";

    private EditText correo;
    private EditText contrasena;
    private Button loginButton;
    private Button singupButton;

    private SharedPreferences preferences;

    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = (EditText) findViewById(R.id.editText_correo);
        contrasena = (EditText) findViewById(R.id.editText_contrasena);

        loginButton = (Button) findViewById(R.id.button_login);
        singupButton = (Button) findViewById(R.id.iniciar_registro_enLogin);

        sKnowCoinApp = new SKnowCoinApp();

    }

    public void clickLogin(View v) {

        String elCorreo = correo.getText().toString();
        String laContrasena = contrasena.getText().toString();

        boolean seguir = false;

        if (!elCorreo.trim().matches("") && !laContrasena.trim().matches("")) {

            seguir = sKnowCoinApp.loginUusuario(elCorreo, laContrasena, this);

        } else {

            //Mandar mensaje que no se pudo iniciar sesion
        }

        if(seguir == true) {

            preferences = getSharedPreferences(USUARIO_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("correo", elCorreo);
            editor.commit();

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

    }// click login

    public void iniciarRegistro(View v) {
        Intent intent = new Intent(this, SingUpActivity_uno.class);
        startActivity(intent);
    }// click Registro

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
