package dan_art.sknowcoin.modelo;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class Tutoria {

    private String hora;
    private String lugar;
    private String codigo;
    private String materia;
    private String nombreTuror;
    private int precio;

    public Tutoria(String hora, String lugar, String codigo, String materia, String nombreTuror, int precio) {
        this.hora = hora;
        this.lugar = lugar;
        this.codigo = codigo;
        this.materia = materia;
        this.nombreTuror = nombreTuror;
        this.precio = precio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNombreTuror() {
        return nombreTuror;
    }

    public void setNombreTuror(String nombreTuror) {
        this.nombreTuror = nombreTuror;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
