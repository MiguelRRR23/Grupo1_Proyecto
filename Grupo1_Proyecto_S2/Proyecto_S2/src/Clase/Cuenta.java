package Clase;

public class Cuenta {
    private String numeroCuenta;
    private Persona persona;
    /**
     * Aunque la base de datos ya no almacene el nombre del banco, mantenemos
     * este campo a nivel de aplicación para utilizar la información de
     * Scotiabank cuando sea necesario (por ejemplo, para mostrar en la GUI).
     */
    private Banco banco;
    private String tipoCuenta;
    private double monto;
    // Nuevos campos asociados al préstamo
    private int numeroCuotas;
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaFin;

    public Cuenta(String numeroCuenta, Persona persona, Banco banco, String tipoCuenta,
                  double monto, int numeroCuotas, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
        this.numeroCuenta = numeroCuenta;
        this.persona = persona;
        this.banco = banco;
        this.tipoCuenta = tipoCuenta;
        this.monto = monto;
        this.numeroCuotas = numeroCuotas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Constructor auxiliar para casos en los que no se tienen fechas/cuotas (compatibilidad)
    public Cuenta(String numeroCuenta, Persona persona, Banco banco, String tipoCuenta, double monto) {
        this(numeroCuenta, persona, banco, tipoCuenta, monto, 0, null, null);
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

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public java.sql.Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(java.sql.Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public java.sql.Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(java.sql.Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
