package dan_art.sknowcoin.layout_handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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

public class SingUpActivity_uno extends AppCompatActivity {

    private EditText correoE;
    private EditText contrasena;
    private EditText confirmContrasena;

    private Button continuarButton;
    private Button atrasButton;

    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_inicio);

        correoE = (EditText) findViewById(R.id.editText_nuevo_correo);
        contrasena = (EditText) findViewById(R.id.editText_nueva_contrasena);
        confirmContrasena = (EditText) findViewById(R.id.editText_confirmar_contrasena);

        continuarButton = (Button) findViewById(R.id.button_siguiente_registro);
        atrasButton = (Button) findViewById(R.id.atras_registro_inicial);

        sKnowCoinApp = new SKnowCoinApp();

    }

    public void clicSiguienteRegistro(View v) {
        String correo = correoE.getText().toString();
        String pass = contrasena.getText().toString();
        String confiPass = confirmContrasena.getText().toString();

        if (pass.equals(confiPass)) {
            if ((!isEmpty(correoE)) && (!isEmpty(contrasena)) && (!isEmpty(confirmContrasena))) {
                Intent intent = new Intent(this, SingUpActivity_dos.class);
                intent.putExtra("CORREO", correo);
                intent.putExtra("PASS", pass);
                startActivity(intent);
            } else {
                mostrarToast("Debes ingresar todos los datos");
            }//no ha introducido los dastos pedidos
        } else {
            mostrarToast("Las contraseñas no coinciden");
        }// las contraseñas no son iguales

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

    public void clickAtrasRegistroUno(View v) {
        super.onBackPressed();
    }// click Registro

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
