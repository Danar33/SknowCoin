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

public class SingUpActivity_tres extends AppCompatActivity {

    //variables de la pantalla anterior de registro
    private String correo;
    private String constrasena;
    private String nombres;
    private String apellidos;
    private String documento;

    //variables de esta pantalla
    private String codigoEstudiante;
    private String carreraUniversitaria;
    private String semestreActual;

    // Views
    private EditText codigo;
    private EditText carrera;
    private EditText semestre;

    private Button finalizarButton;
    private Button cancelarButton;

    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tres);

        Intent intent = getIntent();
        correo = intent.getStringExtra("CORREO");
        constrasena = intent.getStringExtra("PASS");
        nombres = intent.getStringExtra("NOMBRES");
        apellidos = intent.getStringExtra("APELLIDOS");
        documento = intent.getStringExtra("DOCUMENTO");

        codigo = (EditText) findViewById(R.id.editText_cod_estudiante);
        carrera = (EditText) findViewById(R.id.editText_carrera);
        semestre = (EditText) findViewById(R.id.editText_semestre);

        finalizarButton = (Button) findViewById(R.id.button_terminar_registro_basico);
        cancelarButton = (Button) findViewById(R.id.cancelar_registro);

        sKnowCoinApp = new SKnowCoinApp();

    }

    public void clickContinuarRegistro(View v) {

        carreraUniversitaria = carrera.getText().toString();
        codigoEstudiante = codigo.getText().toString();
        semestreActual = semestre.getText().toString();

        if ((!isEmpty(semestre)) && (!isEmpty(carrera)) && (!isEmpty(codigo))) {
            if (true) {// esto es lo que debe validar que se haya registrado
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        } else {
            mostrarToast("Debes ingresar todos los datos");
        }//no ha introducido los ultimos datos

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

    public void clickAtrasRegistro(View v) {
        if (true) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
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
