package dan_art.sknowcoin.layout_handlers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.SKnowCoinApp;

/**
 * Created by dan_a on 11/05/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText correo;
    private EditText contrasena;
    private Button loginButton;
    private Button singupButton;

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

    public void clickLogin(){
        String elCorreo = correo.getText().toString();
        String laContrasena = contrasena.getText().toString();

        sKnowCoinApp.loginUusuario(elCorreo, laContrasena, this);

    }// click login

    public void clickRegistro(){

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
