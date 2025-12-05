package Clase;

public class Usuario {
    // Identificador único del usuario. Renombrado a idUsuario para
    // reflejar la columna id_usuario de la base de datos.
    private int idUsuario;
    private String usuario;
    private String clave;
    private String rol;
    private Integer idTurno;
    private String diaTurnos;
    private String horario;

    public Usuario() {}

    public Usuario(int idUsuario, String usuario, String clave, String rol, Integer idTurno, String diaTurnos, String horario) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
        this.idTurno = idTurno;
        this.diaTurnos = diaTurnos;
        this.horario = horario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
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
}
