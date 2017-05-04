package dan_art.sknowcoin.modelo;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class Usuario {

    private String nombre;
    private int telefono;
    private String correo;
    private String contraseña;
    private String codigoEstudiante;
    private String carreraPrincipal;
    private String semestreActual;
    private Roll roll;
    private Reporte reporte;
    private int calificacion;

    public Usuario(String nombre, int telefono, String correo, String contraseña,
                   String codigoEstudiante, String carreraPrincipal, String semestreActual, Roll roll, int calificacion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.codigoEstudiante = codigoEstudiante;
        this.carreraPrincipal = carreraPrincipal;
        this.semestreActual = semestreActual;
        this.roll = roll;
        this.calificacion = calificacion;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getCarreraPrincipal() {
        return carreraPrincipal;
    }

    public void setCarreraPrincipal(String carreraPrincipal) {
        this.carreraPrincipal = carreraPrincipal;
    }

    public String getSemestreActual() {
        return semestreActual;
    }

    public void setSemestreActual(String semestreActual) {
        this.semestreActual = semestreActual;
    }

    public Roll getRoll() {
        return roll;
    }

    public void setRoll(Roll roll) {
        this.roll = roll;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
