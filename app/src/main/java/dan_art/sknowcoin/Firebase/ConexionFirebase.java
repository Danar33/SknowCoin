package dan_art.sknowcoin.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by VAIO PRO on 27/04/2017.
 */

public class ConexionFirebase {

    public static final  String AREAS_REFERENCE = "areas";
    public static final  String ESTADOS_REFERENCE = "estados";
    public static final  String PUBLICACIONES_REFERENCE = "publicaciones";
    public static final  String RANKING_REFERENCE = "ranking";
    public static final  String REPORTES_REFERENCE = "reportes";
    public static final  String ROLES_REFERENCE = "roles";
    public static final  String USUARIOS_REFERENCE = "usuarios";

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;


    public ConexionFirebase(){
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
    }

    public DatabaseReference getDatabaseReference(){
        return mDatabase;
    }
}
