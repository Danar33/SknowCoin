package dan_art.sknowcoin.layout_handlers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.Reporte;
import dan_art.sknowcoin.modelo.SKnowCoinApp;
import dan_art.sknowcoin.modelo.Tutoria;

public class DetalleTutoriaActivity extends AppCompatActivity {

    private TextView textView_Editable_Materia, textView_Editable_Lugar, textView_Editable_Fecha, textView_Editable_Tutor;
    private EditText editText_Reporte;
    private Button button_Enviar;
    private RatingBar ratingBar_Tutoria;
    private ImageView imageView_Area;

    private SKnowCoinApp sKnowCoinApp;

    private Tutoria tutoria;
    private Reporte reporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tutoria);

        sKnowCoinApp = new SKnowCoinApp();

        setComponentes();
        inicializar();
    }


    private void inicializar() {

        Intent intent = getIntent();
        String extra = intent.getStringExtra("codigo_tutoria");

        sKnowCoinApp.consultarTutoriaPorId(extra, this);

    }

    private void setComponentes() {

        textView_Editable_Fecha = (TextView) findViewById(R.id.textView_Editable_Fecha);
        textView_Editable_Lugar = (TextView) findViewById(R.id.textView_Editable_Lugar);
        textView_Editable_Materia = (TextView) findViewById(R.id.textView_Editable_Materia);
        textView_Editable_Tutor = (TextView) findViewById(R.id.textView_Editable_Tutor);

        editText_Reporte = (EditText) findViewById(R.id.editText_Reporte);
        button_Enviar = (Button) findViewById(R.id.button_Enviar);
        ratingBar_Tutoria = (RatingBar) findViewById(R.id.ratingBar_Tutoria);
        imageView_Area = (ImageView) findViewById(R.id.imageView_Area);

    }


    public void setTutoria(Tutoria t) {
        tutoria = t;
        reporte = sKnowCoinApp.listarReportesPorTutoria(tutoria.getId(), this).get(0);
    }

    public void setReporte(Reporte r) {
        reporte = r;
        actualizarCampos();
    }

    private void actualizarCampos() {

        textView_Editable_Fecha.setText(tutoria.getHora());
        textView_Editable_Tutor.setText(tutoria.getNombreTutor());
        textView_Editable_Materia.setText(tutoria.getMateria());
        textView_Editable_Lugar.setText(tutoria.getLugar());

        String area = tutoria.getMateria();
        int drawable = -1;

        switch (area) {
            case "Sistemas":
                drawable = R.drawable.area_sistemas;
                break;
            case "Medicina":
                drawable = R.drawable.area_medicina;
                break;
            case "Tics":
                drawable = R.drawable.area_tics;
                break;
            case "Fisica":
                drawable = R.drawable.area_fisica;
                break;
            case "Biologia":
                drawable = R.drawable.area_biologia;
                break;
            case "Ecologia":
                drawable = R.drawable.area_ecologia;
                break;
            case "Economia":
                drawable = R.drawable.area_economia;
                break;
            case "Estadistica":
                drawable = R.drawable.area_estadistica;
                break;
            case "Geometria":
                drawable = R.drawable.area_geometria;
                break;
            case "Quimica":
                drawable = R.drawable.area_quimica;
                break;
            default:
                drawable = R.drawable.area_tics;
                break;
        }// switch de areas

        if (drawable != -1) {
            imageView_Area.setImageDrawable(getResources().getDrawable(drawable));
        }// se cargo imagen

        if (reporte != null && (reporte.getEstado() == 2 || reporte.getEstado() == 1)) {
            ratingBar_Tutoria.setNumStars(reporte.getCalificacion());
            ratingBar_Tutoria.setEnabled(false);
            editText_Reporte.setText(reporte.getProblema());
            editText_Reporte.setEnabled(false);
            button_Enviar.setEnabled(false);
        }

    }

    public void buttonEnviarAction(View v) {


        int calificacion = ratingBar_Tutoria.getNumStars();
        String comentario = editText_Reporte.getText().toString();

        if (reporte == null) {

            Reporte r = new Reporte();
            r.setIdTutoria(tutoria.getId());
            r.setProblema(comentario);
            r.setEstado(2);
            r.setCalificacion(calificacion);

            sKnowCoinApp.dejarReporte(r);
            sKnowCoinApp.recalcularRankingUsuario(tutoria.getCodigo(), r.getCalificacion());

            actualizarCampos();

        }

    }

}
