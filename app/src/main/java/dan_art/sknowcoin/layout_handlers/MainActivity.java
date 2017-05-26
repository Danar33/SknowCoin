package dan_art.sknowcoin.layout_handlers;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.Reporte;
import dan_art.sknowcoin.modelo.SKnowCoinApp;

//Main
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CountDownTimer(1500, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                iniciarLogin();
            }
        }.start();

    }//on create

    public void iniciarLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }//iniciar juego
}//Main Activity
