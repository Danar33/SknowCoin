package dan_art.sknowcoin.modelo;

/**
 * Created by Luisf0425 on 27/04/17.
 */

public class Tutoria {

    private String horario;
    private String tutor;
    private String[] estudiantesInscritos;
    private String materia;
    private int precio;

    public Tutoria(String horario, String tutor, String[] estudiantesInscritos, String materia, int precio) {
        this.horario = horario;
        this.tutor = tutor;
        this.estudiantesInscritos = estudiantesInscritos;
        this.materia = materia;
        this.precio = precio;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String[] getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public void setEstudiantesInscritos(String[] estudiantesInscritos) {
        this.estudiantesInscritos = estudiantesInscritos;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
