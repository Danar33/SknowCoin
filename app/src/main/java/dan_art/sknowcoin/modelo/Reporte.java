package dan_art.sknowcoin.modelo;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class Reporte {

    private String id;
    private int estado;
    private int calificacion;
    private String idTutoria;
    private String problema;

    public Reporte() {
    }

    public Reporte(int estado, int calificacion, String idTutoria, String problema) {
        this.estado = estado;
        this.calificacion = calificacion;
        this.idTutoria = idTutoria;
        this.problema = problema;
    }

    public Reporte(String id, int estado, int calificacion, String idTutoria, String problema) {
        this.id = id;
        this.estado = estado;
        this.calificacion = calificacion;
        this.idTutoria = idTutoria;
        this.problema = problema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getIdTutoria() {
        return idTutoria;
    }

    public void setIdTutoria(String idTutoria) {
        this.idTutoria = idTutoria;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }
}
