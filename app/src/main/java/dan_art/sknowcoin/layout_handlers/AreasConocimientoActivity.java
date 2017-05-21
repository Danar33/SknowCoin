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
import android.view.View;

import java.util.ArrayList;

import dan_art.sknowcoin.R;


/**
 * Created by dan_a on 04/05/2017.
 */

public class AreasConocimientoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Integer> idElegidas = new ArrayList<>();
    private static final String TAG = "AreasConocimiento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_areas);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Intent intent = getIntent();
    }// on create

    public void onClickBotones (View v){
        int viewId = v.getId();
        idElegidas.add(viewId);
    }// on click botones

    public void iniciarBusqueda (View v){
        Intent intent = new Intent(this, BuscarMateriaActivity.class);
        startActivity(intent);
    }// on click botones

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_areas_menu_btn) {
            return true;
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

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_edit_profile) {

        } else if (id == R.id.nav_materias) {
            Intent intent = new Intent(this, BuscarMateriaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_buscar_tutor) {

        } else if (id == R.id.nav_top_mensual) {

        } else if (id == R.id.nav_tutorias_solicitadas) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_areas);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}//home activity
