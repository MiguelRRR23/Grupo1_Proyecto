package Clase;

public class Cuenta {
	private String numeroCuenta;
    private Persona persona;
    private Banco banco;
    private String tipoCuenta;
    private double monto;
	public Cuenta(String numeroCuenta, Persona persona, Banco banco, String tipoCuenta, double monto) {
		
		this.numeroCuenta = numeroCuenta;
		this.persona = persona;
		this.banco = banco;
		this.tipoCuenta = tipoCuenta;
		this.monto = monto;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
    
    
}
