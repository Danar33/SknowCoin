package dan_art.sknowcoin.layout_handlers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import dan_art.sknowcoin.R;

/**
 * Created by dan_a on 22/05/2017.
 */

public class VerInfoTutorDialogo extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_info_tutor, null))
                // Add action buttons
                .setPositiveButton(R.string.txt_btn_solicitar_tutDisp, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.txt_btn_cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        VerInfoTutorDialogo.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}// clase
