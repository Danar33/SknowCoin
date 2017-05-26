package dan_art.sknowcoin.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by VAIO PRO on 27/04/2017.
 */

public class ConexionFirebase {

    public static final String AREAS_REFERENCE = "areas";
    public static final String ESTADOS_REFERENCE = "estados";
    public static final String PUBLICACIONES_REFERENCE = "publicaciones";
    public static final String RANKING_REFERENCE = "ranking";
    public static final String REPORTES_REFERENCE = "reportes";
    public static final String ROLES_REFERENCE = "roles";
    public static final String USUARIOS_REFERENCE = "usuarios";
    public static final String PUBLICACIONES_USUARIO_REFERENCE = "publicaciones_usuario";

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;

    private FirebaseStorage storage;
    private StorageReference storageRef;


    public ConexionFirebase() {
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://sknown-coin.appspot.com");
    }

    public DatabaseReference getDatabaseReference() {
        return mDatabase;
    }

    public StorageReference getStorageRef() {
        return storageRef;
    }
}
