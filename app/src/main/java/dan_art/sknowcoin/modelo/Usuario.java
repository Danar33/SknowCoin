package dan_art.sknowcoin.modelo;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class Usuario {

    private String nombre;
    private int telefono;
    private String correo;
    private String codigo;
    private String area;
    private String semestre;
    private int rol;

    public Usuario(String nombre, int telefono, String correo,
                   String codigo, String area, String semestre, int rol) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.codigo = codigo;
        this.area = area;
        this.semestre = semestre;
        this.rol = rol;
    }

    public Usuario(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getcodigo() {
        return codigo;
    }

    public void setcodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getarea() {
        return area;
    }

    public void setarea(String area) {
        this.area = area;
    }

    public String getsemestre() {
        return semestre;
    }

    public void setsemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
}
