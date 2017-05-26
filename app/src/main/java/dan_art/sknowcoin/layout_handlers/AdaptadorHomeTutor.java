package dan_art.sknowcoin.layout_handlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dan_art.sknowcoin.R;
import dan_art.sknowcoin.modelo.Tutoria;

/**
 * Created by dan_a on 17/05/2017.
 */

public class AdaptadorHomeTutor extends ArrayAdapter<Tutoria> {

    Activity activity;
    Context context;
    int index;

    public AdaptadorHomeTutor(Context context, ArrayList<Tutoria> tutorias, Activity activity) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_home_tutores, parent, false);
        }// convert view not null

        // Lookup view for data population
        TextView tvNombreUsuario = (TextView) convertView.findViewById(R.id.tutDisp_nombre_tutor);
        TextView tvRango = (TextView) convertView.findViewById(R.id.tutDisp_tutor_rank);
        TextView tvNivel = (TextView) convertView.findViewById(R.id.tutDsip_nivel);
        TextView tvHorario = (TextView) convertView.findViewById(R.id.tutDisp_horario_tutoria);

        // LLenar los viwes con el contenido del usuario
        tvNombreUsuario.setText(tutoria.getNombreTutor());
        tvRango.setText(tutoria.getMateria());
        tvHorario.setText(tutoria.getHora());

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
        }// switch de areas

        if (bImage != null) {
            imageView.setImageBitmap(bImage);
        }// se cargo imagen

/*
        Button solicitar = (Button) convertView.findViewById(R.id.tutDisp_btn_solicitar);
        solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarToast("Solicitud enviada");
                mostrarDetalleTutoria(tutoria);
            }
        }); */
        // Return the completed view to render on screen
        return convertView;
    }// view

    private void mostrarDetalleTutoria(Tutoria tutoria) {

        Intent intent = new Intent(activity, DetalleTutoriaActivity.class);
        intent.putExtra("codigo_tutoria", tutoria.getId());

        activity.startActivity(intent);


    }

    public void clicAdaptadorTutoriaDisponible(View v) {

    }// clic of adapter

    public void mostrarToast(String mensaje) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, mensaje, duration);
        toast.show();
    }// mostrar toast

}// adaptador para usuarios