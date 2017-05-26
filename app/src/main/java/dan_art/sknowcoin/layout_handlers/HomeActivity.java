package dan_art.sknowcoin.layout_handlers;

import android.content.Intent;
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

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MyHome";

    private ArrayList<Tutoria> tutoriasDisponibles = new ArrayList<>();

    private AdaptadorTutoriaDisponible adaptadorTutorias;
    private ListView listaTutorias;
    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //LLENADO DE PRUEBA
        /*
        for (int i = 0; i < 15; i++) {
            String codigo = "A00028300";
            String hora = "Lunes 4:00 p.m";
            String materia = "Aplicaciones Móviles";
            String area = "Tics";
            String nombreTutor = "Nombre Apellido " + i;
            String lugar = "Icesi";
            int precio = 18000;

            Tutoria nueva = new Tutoria(codigo, hora, materia, area, nombreTutor, precio, lugar);
            tutoriasDisponibles.add(nueva);
        }// for que crea tutorias */

        //  tutoriasDisponibles = sKnowCoinApp.totalTutorias();

     /*   Log.d(TAG, "TAMAÑOOOOOOOOOOOOOOOOOOOOOOOOOOOOO:::: " + tutoriasDisponibles.size());
        for (int i = 0; i < tutoriasDisponibles.size(); i++) {
            Log.d(TAG, "TUTORIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + tutoriasDisponibles.get(i).getMateria());
        }//*/

        sKnowCoinApp = new SKnowCoinApp();
        //adaptadorTutorias = new AdaptadorTutoriaDisponible(this, tutoriasDisponibles, this);
        //list_view_content();
        Bundle datos = this.getIntent().getExtras();

        String materia=datos.getString("materia");
        char a=datos.getChar("char");
        String t="";

        if(materia.equals("home")) {
            sKnowCoinApp.totalTutoriasUsuarios(this);
        }else if(a==('a')){
            sKnowCoinApp.listarTutoriasPorTutor(this,materia);
        }else{
            sKnowCoinApp.listarTutoriasPorMateria(this,materia);
        }

    }// on create

    public void verInfoTutor() {
        VerInfoTutorDialogo newFragment = new VerInfoTutorDialogo();
        //TODO newFragment.show(getSupportFragmentManager(), "missiles");
    }

    public void list_view_content() {
        /// Asignacion del adaptador
        listaTutorias = (ListView) findViewById(R.id.tutorias_home_list_layout);

        actualizarLista();

        listaTutorias.setAdapter(adaptadorTutorias);
    }// contenido del list view

    public void actualizarLista() {

        for (int i = 0; i < tutoriasDisponibles.size(); i++) {
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("materia","home");
            startActivity(intent);
        } else if (id == R.id.nav_edit_profile) {
            Intent intent = new Intent(this, PerfilUsuarioActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_cambiar_rol) {
            Intent intent = new Intent(this, HomeTutorActivity.class);

            startActivity(intent);
        } else if (id == R.id.nav_materias) {
            Intent intent = new Intent(this, BuscarMateriaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_buscar_tutor) {
            Intent intent = new Intent(this, BuscarTutorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_top_mensual) {

        } else if (id == R.id.nav_tutorias_solicitadas) {
            Intent intent = new Intent(this, TutoriasSolicitadasActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ArrayList<Tutoria> getTutoriasDisponibles() {
        return tutoriasDisponibles;
    }

    public void setTutoriasDisponibles(ArrayList<Tutoria> tutoriasDisponibles) {
        this.tutoriasDisponibles = tutoriasDisponibles;
    }

    public void tutorias() {
        adaptadorTutorias = new AdaptadorTutoriaDisponible(this, tutoriasDisponibles, this);

        list_view_content();
    }
}//home activity
