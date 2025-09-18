package Clase;

public class Banco {
	private int dni;
	private String nom,tipo;
	private double canti;
	public Banco(int dni, String nom, String tipo, double canti) {
		
		this.dni = dni;
		this.nom = nom;
		this.tipo = tipo;
		this.canti = canti;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getCanti() {
		return canti;
	}
	public void setCanti(double canti) {
		this.canti = canti;
	}
	
	
}
