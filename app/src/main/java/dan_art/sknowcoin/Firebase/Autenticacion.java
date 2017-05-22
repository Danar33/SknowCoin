package dan_art.sknowcoin.Firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by VAIO PRO on 04/05/2017.
 */

public class Autenticacion {

    private FirebaseAuth mAuth;
    private boolean retorno = false;
    public Context context;


    public Autenticacion(Context context) {
        mAuth = FirebaseAuth.getInstance();
        this.context = context;
    }

    public Autenticacion() {
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean signUp(String email, String password, final Context contexto) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    retorno = true;
                    Toast.makeText(contexto, "Bienvenido.", Toast.LENGTH_SHORT).show();

                } else {
                    retorno = false;
                    Toast.makeText(contexto, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        mAuth.signOut();

        return retorno;
    }

    public Boolean signIn(String email, String password, final Context contexto) {

        final ArrayList<Boolean> retorno1 = new ArrayList<Boolean>();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    retorno1.add(Boolean.TRUE);

                    //Toast.makeText(contexto, "Bienvenido.", Toast.LENGTH_SHORT).show();

                } else {
                    retorno1.add(Boolean.FALSE);
                    Toast.makeText(contexto, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        mAuth.signOut();
        return retorno1.get(0);
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }
}
