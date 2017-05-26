package dan_art.sknowcoin.modelo;

/**
 * Created by VAIO PRO on 17/05/2017.
 */

public class PublicacionesUsuario {

    private String codigoUsuario, idTutoria;

    public PublicacionesUsuario(String codigoUsuario, String idTutoria){

        this.codigoUsuario = codigoUsuario;
        this.idTutoria = idTutoria;

    }
    public PublicacionesUsuario(){

    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getIdTutoria() {
        return idTutoria;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public void setIdTutoria(String idTutoria) {
        this.idTutoria = idTutoria;
    }
}
