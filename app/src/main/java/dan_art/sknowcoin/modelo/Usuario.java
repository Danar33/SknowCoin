package dan_art.sknowcoin.modelo;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class Usuario {

    private String nombre;
    private String telefono;
    private String correo;
    private String codigo;
    private String area;
    private String semestre;
    private String contrasena;
    private int rol;

    public Usuario(String nombre, String telefono, String correo,
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public String getContrasena(){
        return contrasena;
    }
}
