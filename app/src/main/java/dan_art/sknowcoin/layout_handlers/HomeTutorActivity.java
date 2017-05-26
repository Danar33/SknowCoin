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
import android.widget.ListView;

import java.util.ArrayList;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.SKnowCoinApp;
import dan_art.sknowcoin.modelo.Tutoria;


/**
 * Created by dan_a on 04/05/2017.
 */

public class HomeTutorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String USUARIO_PREFERENCES = "USUARIO_PREFERENCES";

    private static final String TAG = "TutorHome";

    private ArrayList<Tutoria> itemsHomeTutor = new ArrayList<>();

    private AdaptadorHomeTutor adaptadorTutorias;
    private ListView listaTutorias;

    private SharedPreferences preferences;

    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tutores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tutor);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });//*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_tutores);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home_tutores);
        navigationView.setNavigationItemSelectedListener(this);

        sKnowCoinApp = new SKnowCoinApp();

        preferences = getSharedPreferences(USUARIO_PREFERENCES, Context.MODE_PRIVATE);

        Intent intent = getIntent();
        //String correo = intent.getStringExtra("ELCORREO");


        //preferences = getSharedPreferences(USUARIO_PREFERENCES, Context.MODE_PRIVATE);
        // SharedPreferences.Editor editor = preferences.edit();

        //Usuario usuario = sKnowCoinApp.buscarTutorPorCorreo(correo);
        //editor.putString("codigo", usuario.getCodigo());

        //editor.commit();


        //LLENADO DE PRUEBA
        /*
        for (int i = 0; i < 15; i++) {
            String codigo = "A00028300";
            String hora = "Viernes 2:00 p.m";
            String materia = "Lab. Redes";
            String area = "Tics";
            String nombreTutor = "Nombre Estudiante " + i;
            String lugar = "Icesi";
            int precio = 18000;

            Tutoria nueva = new Tutoria(codigo, hora, materia, area, nombreTutor, precio, lugar);
            itemsHomeTutor.add(nueva);
        }// for que crea tutorias */

        //  tutoriasDisponibles = sKnowCoinApp.totalTutorias();

     /*   Log.d(TAG, "TAMAÑOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO:::: " + tutoriasDisponibles.size());
        for (int i = 0; i < tutoriasDisponibles.size(); i++) {
            Log.d(TAG, "TUTORIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + tutoriasDisponibles.get(i).getMateria());
        }//*/

        //adaptadorTutorias = new AdaptadorHomeTutor(this, itemsHomeTutor, this);

        //list_view_content();

        sKnowCoinApp=new SKnowCoinApp();
        sKnowCoinApp.totalTutoriasIdTutor(this,preferences.getString("codigo_usuario","000"));

    }// on create

    public void list_view_content() {
        /// Asignacion del adaptador
        listaTutorias = (ListView) findViewById(R.id.home_tutor_list_layout);

        actualizarLista();

        listaTutorias.setAdapter(adaptadorTutorias);
    }// contenido del list view

    public void actualizarLista() {

        for (int i = 0; i < itemsHomeTutor.size(); i++) {
            // TODO
            adaptadorTutorias.notifyDataSetChanged();
        }// for top 5
    }// actualizar lista


    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }//*/
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

        } else if (id == R.id.nav_cambiar_a_estudiante) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("materia","home");
            startActivity(intent);
        } else if (id == R.id.nav_crear_tutorias) {
            Intent intent = new Intent(this, CrearTutoriaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_buscar_tutor) {

        } else if (id == R.id.nav_top_mensual) {

        } else if (id == R.id.nav_tutorias_solicitadas) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_tutores);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ArrayList<Tutoria> getItemsHomeTutor() {
        return itemsHomeTutor;
    }

    public void tutorias(){
        adaptadorTutorias = new AdaptadorHomeTutor(this, itemsHomeTutor, this);

        list_view_content();
    }
}//home activity
