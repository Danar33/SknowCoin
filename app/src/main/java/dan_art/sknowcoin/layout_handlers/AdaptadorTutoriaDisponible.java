package dan_art.sknowcoin.layout_handlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.PublicacionesUsuario;
import dan_art.sknowcoin.modelo.SKnowCoinApp;
import dan_art.sknowcoin.modelo.Tutoria;

/**
 * Created by dan_a on 17/05/2017.
 */

public class AdaptadorTutoriaDisponible extends ArrayAdapter<Tutoria> {


    public final static String USUARIO_PREFERENCES = "USUARIO_PREFERENCES";
    private SharedPreferences prefs;

    Activity activity;
    Context context;
    int index;

    private SKnowCoinApp sKnowCoinApp;

    public AdaptadorTutoriaDisponible(Context context, ArrayList<Tutoria> tutorias, Activity activity) {
        super(context, 0, tutorias);
        this.activity = activity;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Tutoria tutoria = getItem(position);
        index = position;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tutoria_disponible, parent, false);
        }// convert view not null

        // Lookup view for data population
        TextView tvNombreUsuario = (TextView) convertView.findViewById(R.id.tutDisp_nombre_tutor);
        TextView tvRango = (TextView) convertView.findViewById(R.id.tutDisp_tutor_rank);
        TextView tvNivel = (TextView) convertView.findViewById(R.id.tutDsip_nivel);
        TextView tvHorario = (TextView) convertView.findViewById(R.id.tutDisp_horario_tutoria);
        TextView tvPrecio = (TextView) convertView.findViewById(R.id.tutDisp_precio_numerico);

        // LLenar los viwes con el contenido del usuario
        tvNombreUsuario.setText(tutoria.getNombreTutor());
        tvRango.setText(tutoria.getMateria());
        tvHorario.setText(tutoria.getHora());

        int miles = tutoria.getPrecio() / 1000;
        String precioTexto = "$" + miles + ".000";
        tvPrecio.setText(precioTexto);

        sKnowCoinApp = new SKnowCoinApp();
        prefs = context.getSharedPreferences(USUARIO_PREFERENCES, Context.MODE_PRIVATE);

        String nivelPrueba = "1";
        tvNivel.setText(nivelPrueba);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.tutDisp_area);

        String area = tutoria.getMateria();
        Bitmap bImage = null;

        switch (area) {
            case "Biologia":
                bImage = BitmapFactory.decodeResource(activity.getResources(), R.drawable.area_biologia);
                break;
            case "Física":
                bImage = BitmapFactory.decodeResource(activity.getResources(), R.drawable.area_fisica);
                break;
            case "Tics":
                bImage = BitmapFactory.decodeResource(activity.getResources(), R.drawable.area_tics);
                break;
            case "Medicina":
                bImage = BitmapFactory.decodeResource(activity.getResources(), R.drawable.area_medicina);
                break;
            case "Sistemas":
                bImage = BitmapFactory.decodeResource(activity.getResources(), R.drawable.area_sistemas);
                break;
            default:
                bImage = BitmapFactory.decodeResource(activity.getResources(), R.drawable.area_tics);
                break;
        }// switch de areas

        if (bImage != null) {
            imageView.setImageBitmap(bImage);
        }// se cargo imagen


        Button solicitar = (Button) convertView.findViewById(R.id.tutDisp_btn_solicitar);
        solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PublicacionesUsuario publicacionesUsuario = new PublicacionesUsuario();

                String codigo = prefs.getString("codigo_usuario", "000");

                publicacionesUsuario.setCodigoUsuario(codigo);
                publicacionesUsuario.setIdTutoria(tutoria.getId());

                String pus2 = sKnowCoinApp.solicitarPublicacionSolicitada(publicacionesUsuario, "");
                sKnowCoinApp.solicitarPublicacionSolicitada(publicacionesUsuario, pus2);

                mostrarToast("Solicitud a " + tutoria.getMateria() + " enviada.");
               // mostrarDetalleTutoria(tutoria);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }// view

    private void mostrarDetalleTutoria(Tutoria tutoria) {

        Intent intent = new Intent(activity, DetalleTutoriaActivity.class);
        intent.putExtra("codigo_tutoria", tutoria.getId());

        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("dd", "dd");
    }

    public void clicAdaptadorTutoriaDisponible(View v) {

    }// clic of adapter

    public void mostrarToast(String mensaje) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, mensaje, duration);
        toast.show();
    }// mostrar toast

}// adaptador para usuarios