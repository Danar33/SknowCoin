package dan_art.sknowcoin.layout_handlers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.SKnowCoinApp;
import dan_art.sknowcoin.modelo.Usuario;

public class PerfilUsuarioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String USUARIO_PREFERENCES = "USUARIO_PREFERENCES";
    public final static int RESULT_LOAD_IMAGE = 1;

    private EditText editText_Semestre, editText_Area, editText_Telefono, editText_Correo, editText_Codigo, editText_Nombre;
    private Button buttonEditar;
    private SharedPreferences prefs;
    private ImageView imageView;

    private Usuario usuario;

    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_perfil_usuario);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_perfil_usuario);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_perfil_usuario);
        navigationView.setNavigationItemSelectedListener(this);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });//*/ // NO BORREN ESTAS LINEAS

        imageView = (ImageView) findViewById(R.id.imageView2);

        sKnowCoinApp = new SKnowCoinApp();
        prefs = getSharedPreferences(USUARIO_PREFERENCES, Context.MODE_PRIVATE);

        String nombreImagen = prefs.getString("nombre_imagen", "iii");
        String codigo = prefs.getString("codigo_usuario", "000");

        if (!nombreImagen.matches("iii") && !codigo.matches("000")) {

            File fotoFile = sKnowCoinApp.setFotoPerfil(codigo, nombreImagen);
            Uri uri = Uri.fromFile(fotoFile);
            imageView.setImageURI(uri);
        }

        inicializarComponentes();
        inicializarInfo();
    }

    private void inicializarInfo() {


        String codigo = prefs.getString("codigo_usuario", "000");

        if (codigo != "000") {

            usuario = sKnowCoinApp.getUsuarioPorCodigo(codigo, this);

        }

    }

    public void pintarInfoUsuario(Usuario us) {

        usuario = us;

        editText_Semestre.setText(usuario.getSemestre());
        editText_Area.setText(usuario.getArea());
        editText_Telefono.setText(usuario.getTelefono());
        editText_Correo.setText(usuario.getCorreo());
        editText_Codigo.setText(usuario.getCodigo());
        editText_Nombre.setText(usuario.getNombre());

    }

    private void inicializarComponentes() {

        editText_Semestre = (EditText) findViewById(R.id.editText_Semestre);
        editText_Area = (EditText) findViewById(R.id.editText_Area);
        editText_Telefono = (EditText) findViewById(R.id.editText_Telefono);
        editText_Correo = (EditText) findViewById(R.id.editText_Correo);
        editText_Codigo = (EditText) findViewById(R.id.editText_Codigo);
        editText_Nombre = (EditText) findViewById(R.id.editText_Nombre);

        buttonEditar = (Button) findViewById(R.id.buttonEditar);

        flipComponentes();

    }

    private void flipComponentes() {

        editText_Semestre.setEnabled(!editText_Semestre.isEnabled());
        editText_Area.setEnabled(!editText_Area.isEnabled());
        editText_Telefono.setEnabled(!editText_Telefono.isEnabled());
        editText_Correo.setEnabled(!editText_Correo.isEnabled());
        editText_Nombre.setEnabled(!editText_Nombre.isEnabled());

    }

    public void buttonEditarAction(View v) {

        flipComponentes();

        if (buttonEditar.getText().equals("Editar")) {
            buttonEditar.setText("Guardar");
        } else {
            buttonEditar.setText("Editar");

            usuario.setSemestre(editText_Semestre.getText().toString());
            usuario.setArea(editText_Area.getText().toString());
            usuario.setTelefono(editText_Telefono.getText().toString());
            usuario.setCorreo(editText_Correo.getText().toString());
            usuario.setNombre(editText_Nombre.getText().toString());

            sKnowCoinApp.mergeUsuario(usuario);

        }
    }

    public void onClickImage(View v) {

        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String codigo = prefs.getString("codigo_usuario", "000");

            sKnowCoinApp.subirFotoPerfil(codigo, selectedImage);

            Log.i("INFO: ", selectedImage.getLastPathSegment());

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre_imagen", selectedImage.getLastPathSegment());

            Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 400, 600, true);

            imageView.setImageBitmap(resizedbitmap);

        }


    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    @Override
    public void onBackPressed() {

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

        } else if (id == R.id.nav_top_mensual) {

        } else if (id == R.id.nav_tutorias_solicitadas) {
            Intent intent = new Intent(this, TutoriasSolicitadasActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_perfil_usuario);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}// clase
