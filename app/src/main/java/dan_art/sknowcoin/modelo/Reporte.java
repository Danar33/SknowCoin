package dan_art.sknowcoin.modelo;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class Reporte {

    private int estado;
    private String idTutoria;
    private String problema;

    public Reporte() {
    }

    public String getIdTutoria() {
        return idTutoria;
    }

    public void setIdTutoria(String idTutoria) {
        this.idTutoria = idTutoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }
}
