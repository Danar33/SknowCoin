package dan_art.sknowcoin.layout_handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    public void clickLogin(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }// click login

    public void backUpLogin(View v) {

        String elCorreo = correo.getText().toString();
        String laContrasena = contrasena.getText().toString();

        if ((!isEmpty(correo)) && (!isEmpty(contrasena))) {
            boolean seguir = false;

            if (!elCorreo.trim().matches("") && !laContrasena.trim().matches("")) {
                seguir = sKnowCoinApp.loginUusuario(elCorreo, laContrasena, this);
            } else {
                mostrarToast("Correo o contraseña incorrecta");
            }// se pudo establecer conexion

            if (seguir == true) {

                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("ELCORREO", elCorreo);
                startActivity(intent);
            }
        } else {
            mostrarToast("No has ingresado tus datos");
        }// no ha llenado los datos

    }// click login

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void mostrarToast(String mensaje) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, mensaje, duration);
        toast.show();
    }// mostrar toast

    public void iniciarRegistro(View v) {
        Intent intent = new Intent(this, SingUpActivity_uno.class);
        startActivity(intent);
    }// click Registro

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
