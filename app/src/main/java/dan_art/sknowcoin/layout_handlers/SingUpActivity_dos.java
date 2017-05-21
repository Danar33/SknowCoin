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

public class SingUpActivity_dos extends AppCompatActivity {

    //variables de la pantalla anterior de registro
    private String correo;
    private String constrasena;

    // Views
    private EditText nombres;
    private EditText telefono;
    private EditText apellidos;

    private Button continuarButton;
    private Button atrasButton;

    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_dos);

        Intent intent = getIntent();
        correo = intent.getStringExtra("CORREO");
        constrasena = intent.getStringExtra("PASS");

        nombres = (EditText) findViewById(R.id.editText_nombre);
        telefono = (EditText) findViewById(R.id.editText_telefono);
        apellidos = (EditText) findViewById(R.id.editText_apellidos);

        continuarButton = (Button) findViewById(R.id.button_continuar_registro);
        atrasButton = (Button) findViewById(R.id.atras_registro);

        sKnowCoinApp = new SKnowCoinApp();

    }

    public void clickContinuarRegistroDos(View v) {
        String nom = nombres.getText().toString();
        String tel = telefono.getText().toString();
        String ape = apellidos.getText().toString();
        String nombreCompleto = nom + " " + ape;

        if ((!isEmpty(nombres)) && (!isEmpty(telefono)) && (!isEmpty(apellidos))) {
            Intent intent = new Intent(this, SingUpActivity_tres.class);
            intent.putExtra("CORREO", correo);
            intent.putExtra("PASS", constrasena);
            intent.putExtra("NOMBRES", nombreCompleto);
            intent.putExtra("TELEFONO", tel);
            startActivity(intent);
        } else {
            mostrarToast("Debes ingresar todos los datos");
        }//no ha introducido los dastos pedidos

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

    public void clicAtrasRegistroDos(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }// click Registro

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
