package Clase;

import java.sql.Date;

/**
 * Representa un préstamo otorgado a un cliente. Cada préstamo enlaza a un
 * cliente, un prestamista y una cuenta donde se depositan los fondos. Este
 * objeto almacena los detalles de la operación (monto, cuotas, tasas y
 * fechas), el estado de la solicitud y los parámetros utilizados para la
 * evaluación del crédito (ingresos mensuales y motivo).  De esta forma
 * se refleja la relación de muchos‑a‑muchos entre clientes y prestamistas.
 */
public class Prestamo {

    private int idPrestamo;
    // Identificador del cliente que recibe el préstamo (DNI)
    private int dni;
    // Identificador del usuario (empleado) que registra el préstamo
    private int idUsuario;
    // Nombre de usuario del empleado que registró el préstamo. Este campo
    // no se almacena en la base de datos, se utiliza únicamente para
    // mostrar en los reportes.
    private String nombreUsuario;
    private String numeroCuenta;
    private double monto;
    private int numCuotas;
    private double tasaAnual;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String motivo;
    private double ingresosMensuales;

    public Prestamo() {
    }

    /**
     * Construye un préstamo con todos los campos definidos. El idPrestamo
     * generalmente lo genera la base de datos.
     */
    public Prestamo(int idPrestamo, int dni, int idUsuario,
                    String numeroCuenta, double monto, int numCuotas,
                    double tasaAnual, Date fechaInicio, Date fechaFin,
                    String estado, String motivo, double ingresosMensuales) {
        this.idPrestamo = idPrestamo;
        this.dni = dni;
        this.idUsuario = idUsuario;
        this.numeroCuenta = numeroCuenta;
        this.monto = monto;
        this.numCuotas = numCuotas;
        this.tasaAnual = tasaAnual;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.motivo = motivo;
        this.ingresosMensuales = ingresosMensuales;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    /**
     * Obtiene el DNI del cliente asociado al préstamo.
     */
    public int getDni() {
        return dni;
    }

    /**
     * Establece el DNI del cliente asociado al préstamo.
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el identificador del usuario que registró el préstamo.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario que registró el préstamo.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre de usuario del empleado que registró el préstamo.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario del empleado que registró el préstamo.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getNumCuotas() {
        return numCuotas;
    }

    public void setNumCuotas(int numCuotas) {
        this.numCuotas = numCuotas;
    }

    public double getTasaAnual() {
        return tasaAnual;
    }

    public void setTasaAnual(double tasaAnual) {
        this.tasaAnual = tasaAnual;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(double ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }
}