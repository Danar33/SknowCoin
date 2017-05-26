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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.IOException;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.SKnowCoinApp;
import dan_art.sknowcoin.modelo.Usuario;

public class PerfilUsuarioActivity extends AppCompatActivity {

    public final static String USUARIO_PREFERENCES = "USUARIO_PREFERENCES";
    public final static int RESULT_LOAD_IMAGE = 1;

    private EditText editText_Semestre, editText_Area, editText_Telefono, editText_Correo, editText_Codigo, editText_Nombre;
    private Button buttonEditar;
    private SharedPreferences prefs;

    private Usuario usuario;

    private SKnowCoinApp sKnowCoinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });//*/ // NO BORREN ESTAS LINEAS
        sKnowCoinApp = new SKnowCoinApp();
        prefs = getSharedPreferences(USUARIO_PREFERENCES, Context.MODE_PRIVATE);

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

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageView2);


            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String codigo = prefs.getString("codigo_usuario", "000");

            sKnowCoinApp.subirFotoPerfil(codigo, selectedImage);

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

}
