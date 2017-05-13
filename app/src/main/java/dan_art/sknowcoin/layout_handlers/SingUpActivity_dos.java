package dan_art.sknowcoin.layout_handlers;

import android.content.Intent;
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

public class SingUpActivity_dos extends AppCompatActivity {

    private EditText ccodigo;
    private EditText carrera;
    private EditText semestre;

    private Button finalizarButton;
    private Button cancelarButton;

    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_dos);

        ccodigo = (EditText) findViewById(R.id.editText_cod_estudiante);
        carrera = (EditText) findViewById(R.id.editText_carrera);
        semestre = (EditText) findViewById(R.id.editText_semestre);

        finalizarButton = (Button) findViewById(R.id.button_terminar_registro_basico);
        cancelarButton = (Button) findViewById(R.id.cancelar_registro);

        sKnowCoinApp = new SKnowCoinApp();

    }

    public void clickContinuarRegistro(View v) {


        if(true) {
          Intent intent = new Intent(this, HomeActivity.class);
          startActivity(intent);
        }

    }// click login

    public void clickAtrasRegistro(View v) {
        if(true) {
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
