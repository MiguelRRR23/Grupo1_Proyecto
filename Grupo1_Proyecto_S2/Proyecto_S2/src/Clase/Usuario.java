package Clase;

public class Usuario {
	private String usuario;
    private String clave;

    public Usuario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() { return usuario; }
    public String getClave() { return clave; }

}
