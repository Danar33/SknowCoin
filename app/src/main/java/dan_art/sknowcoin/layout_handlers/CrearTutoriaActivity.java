package dan_art.sknowcoin.layout_handlers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.SKnowCoinApp;
import dan_art.sknowcoin.modelo.Tutoria;


/**
 * Created by dan_a on 04/05/2017.
 */

public class CrearTutoriaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String USUARIO_PREFERENCES = "USUARIO_PREFERENCES";

    private static final String TAG = "CrearTutoria";

    private EditText nombreTutoria;
    private EditText lugarTutoria;
    private EditText horaTutoria;
    private EditText precioTutoria;
    private Button boton;

    private SKnowCoinApp sKnowCoinApp;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tutoria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_crear_tutoria);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });//*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_crear_tutoria);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_crear_tutoria);
        navigationView.setNavigationItemSelectedListener(this);

        nombreTutoria = (EditText) findViewById(R.id.editText_nombre_materia);
        lugarTutoria = (EditText) findViewById(R.id.editText_lugar_tutoria);
        horaTutoria = (EditText) findViewById(R.id.editText_hora);
        precioTutoria = (EditText) findViewById(R.id.editText_precio_tutoria);

        boton = (Button) findViewById(R.id.btn_seleccionar_area);

        prefs = getSharedPreferences(USUARIO_PREFERENCES, Context.MODE_PRIVATE);

        sKnowCoinApp = new SKnowCoinApp();

        Intent intent = getIntent();


    }// on create

    public void seleccionarArea (View v){
        Intent intent = new Intent(this, AreasConocimientoActivity.class);
        startActivity(intent);
    }// selesccionar area

    public void crearTutoria(View v) {

        String nombre = nombreTutoria.getText().toString();
        String lugar = lugarTutoria.getText().toString();
        String hora = horaTutoria.getText().toString();
        String precio = precioTutoria.getText().toString();
        String codigoUsuario = prefs.getString("codigo_usuario", "000");
        String nombreUsuario = prefs.getString("nombre_usuario", "aaa");

        if (!nombre.isEmpty() && !lugar.isEmpty() && !hora.isEmpty() && !precio.isEmpty() && !codigoUsuario.matches("000") && !nombreUsuario.matches("aaa")) {

            Tutoria tutoria = new Tutoria();
            tutoria.setPrecio(Integer.parseInt(precio));
            tutoria.setMateria(nombre);
            tutoria.setLugar(lugar);
            tutoria.setHora(hora);
            tutoria.setCodigo(codigoUsuario);
            tutoria.setNombreTutor(nombreUsuario);

            // TODO debo hacer que se cambie el area
            tutoria.setArea("mat");

            String id = sKnowCoinApp.publicarTutoria(tutoria, "");

            tutoria.setId(id);
            sKnowCoinApp.publicarTutoria(tutoria, id);
        }
    }

    public void cancelar(View v) {
        super.onBackPressed();
    }// cancela la accion

    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }//*/
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_tutor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sol) {
            Intent intent = new Intent(this, AreasConocimientoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        /*TextView tv = (TextView) findViewById(R.id.userName_MenuEstud);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/epimodem.ttf");
        tv.setTypeface(face);//*/

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit_profile) {

        } else if (id == R.id.nav_home) {
            Intent intent = new Intent(this, HomeTutorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_cambiar_a_estudiante) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tutorias_solicitadas) {
            //  Intent intent = new Intent(this, BuscarMateriaActivity.class);
            // startActivity(intent);
        } else if (id == R.id.nav_buscar_tutor) {

        } else if (id == R.id.nav_top_mensual) {

        } else if (id == R.id.nav_tutorias_solicitadas) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_crear_tutoria);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}//home activity
