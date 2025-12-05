package Clase;

/**
 * Representa un horario de trabajo para un empleado. Cada horario
 * contiene un identificador, el texto que describe los días del turno
 * (por ejemplo, "Lunes a Viernes") y el rango horario (por ejemplo,
 * "08:00-16:00"). Esta clase sirve para poblar los combo boxes de
 * selección de turno y para transportar la información entre la base
 * de datos y la interfaz de usuario.
 */
public class Horario {
    private int idTurno;
    private String diaTurnos;
    private String horario;

    public Horario() {}

    public Horario(int idTurno, String diaTurnos, String horario) {
        this.idTurno = idTurno;
        this.diaTurnos = diaTurnos;
        this.horario = horario;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getDiaTurnos() {
        return diaTurnos;
    }

    public void setDiaTurnos(String diaTurnos) {
        this.diaTurnos = diaTurnos;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        // Devuelve una representación amigable para mostrar en un ComboBox
        return diaTurnos + " " + horario;
    }
}