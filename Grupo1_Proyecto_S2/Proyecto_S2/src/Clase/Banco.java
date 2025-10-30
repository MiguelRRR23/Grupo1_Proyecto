package Clase;

public class Banco {
	private int codigoBanco;
    private String nombreBanco;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String ruc;
    private String gerente;
    
	public Banco(int codigoBanco, String nombreBanco, String direccion, String ciudad, String telefono, String ruc,
			String gerente) {
		this.codigoBanco = codigoBanco;
		this.nombreBanco = nombreBanco;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.ruc = ruc;
		this.gerente = gerente;
	}

	public int getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(int codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

    
	
	
}
