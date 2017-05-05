package dan_art.sknowcoin.Firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by VAIO PRO on 04/05/2017.
 */

public class Autenticacion {

    private FirebaseAuth mAuth;

    public Autenticacion() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(String email, String password, final Context contexto) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(contexto, "Bienvenido.", Toast.LENGTH_SHORT).show();

                    //TODO: Ir a la actividad que corresponde

                } else {
                    Toast.makeText(contexto, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        mAuth.signOut();
    }

    public void signIn(String email, String password, final Context contexto) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(contexto, "Bienvenido.", Toast.LENGTH_SHORT).show();

                    //TODO: Ir a la actividad que corresponde

                } else {
                    Toast.makeText(contexto, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        mAuth.signOut();
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }
}
