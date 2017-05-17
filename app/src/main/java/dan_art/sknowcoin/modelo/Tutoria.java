package dan_art.sknowcoin.modelo;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class Tutoria {

    private String codigo;
    private String hora;
    private String lugar;
    private String materia;
    private String area;
    private String nombreTutor;
    private int precio;

    public Tutoria(String codigo, String hora, String materia, String nombreTutor, int precio, String lugar) {
        this.codigo = codigo;
        this.hora = hora;
        this.lugar = lugar;
        this.materia = materia;
        this.nombreTutor = nombreTutor;
        this.precio = precio;
    }

    public Tutoria(){

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


}
