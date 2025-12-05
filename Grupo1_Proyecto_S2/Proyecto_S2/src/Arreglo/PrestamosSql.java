package Arreglo;

import Clase.Prestamo;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase de acceso a datos para la entidad Prestamo. Proporciona métodos
 * para listar, insertar, modificar y eliminar registros de la tabla
 * `prestamos` utilizando los procedimientos almacenados definidos en la
 * base de datos. Con estas operaciones es posible reflejar y explotar la
 * relación de muchos‑a‑muchos entre clientes y prestamistas.
 */
public class PrestamosSql {

    /**
     * Devuelve todos los prstamos almacenados en la base de datos.
     */
    public ArrayList<Prestamo> listar() {
        ArrayList<Prestamo> lista = new ArrayList<>();
        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_ListarPrestamos()}");
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                Prestamo pr = new Prestamo();
                pr.setIdPrestamo(rs.getInt("id_prestamo"));
                pr.setDni(rs.getInt("dni"));
                pr.setIdUsuario(rs.getInt("id_usuario"));
                pr.setNumeroCuenta(rs.getString("numero_cuenta"));
                pr.setMonto(rs.getDouble("monto"));
                pr.setNumCuotas(rs.getInt("num_cuotas"));
                pr.setTasaAnual(rs.getDouble("tasa_anual"));
                pr.setFechaInicio(rs.getDate("fecha_inicio"));
                pr.setFechaFin(rs.getDate("fecha_fin"));
                pr.setEstado(rs.getString("estado"));
                pr.setMotivo(rs.getString("motivo"));
                pr.setIngresosMensuales(rs.getDouble("ingresos_mensuales"));
                // Leer el nombre del usuario que registró el préstamo, si está disponible
                try {
                    String nombreUsuario = rs.getString("nombre_usuario");
                    pr.setNombreUsuario(nombreUsuario);
                } catch (Exception ex) {
                    // Si el alias no existe en el resultado, se ignora
                }
                lista.add(pr);
            }
        } catch (Exception e) {
            System.out.println("Error listar prestamos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Devuelve los préstamos correspondientes a un cliente específico.
     */
    public ArrayList<Prestamo> listarPorCliente(int dniCliente) {
        ArrayList<Prestamo> lista = new ArrayList<>();
        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_ListarPrestamosPorCliente(?)}");
            sp.setInt(1, dniCliente);
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                Prestamo pr = new Prestamo();
                pr.setIdPrestamo(rs.getInt("id_prestamo"));
                pr.setDni(rs.getInt("dni"));
                pr.setIdUsuario(rs.getInt("id_usuario"));
                pr.setNumeroCuenta(rs.getString("numero_cuenta"));
                pr.setMonto(rs.getDouble("monto"));
                pr.setNumCuotas(rs.getInt("num_cuotas"));
                pr.setTasaAnual(rs.getDouble("tasa_anual"));
                pr.setFechaInicio(rs.getDate("fecha_inicio"));
                pr.setFechaFin(rs.getDate("fecha_fin"));
                pr.setEstado(rs.getString("estado"));
                pr.setMotivo(rs.getString("motivo"));
                pr.setIngresosMensuales(rs.getDouble("ingresos_mensuales"));
                try {
                    String nombreUsuario = rs.getString("nombre_usuario");
                    pr.setNombreUsuario(nombreUsuario);
                } catch (Exception ex) {
                    // no alias
                }
                lista.add(pr);
            }
        } catch (Exception e) {
            System.out.println("Error listar prestamos por cliente: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Devuelve los préstamos atendidos por un usuario concreto.
     * @param idUsuario identificador del usuario empleado (id_usuario)
     */
    public ArrayList<Prestamo> listarPorUsuario(int idUsuario) {
        ArrayList<Prestamo> lista = new ArrayList<>();
        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_ListarPrestamosPorUsuario(?)}");
            sp.setInt(1, idUsuario);
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                Prestamo pr = new Prestamo();
                pr.setIdPrestamo(rs.getInt("id_prestamo"));
                pr.setDni(rs.getInt("dni"));
                pr.setIdUsuario(rs.getInt("id_usuario"));
                pr.setNumeroCuenta(rs.getString("numero_cuenta"));
                pr.setMonto(rs.getDouble("monto"));
                pr.setNumCuotas(rs.getInt("num_cuotas"));
                pr.setTasaAnual(rs.getDouble("tasa_anual"));
                pr.setFechaInicio(rs.getDate("fecha_inicio"));
                pr.setFechaFin(rs.getDate("fecha_fin"));
                pr.setEstado(rs.getString("estado"));
                pr.setMotivo(rs.getString("motivo"));
                pr.setIngresosMensuales(rs.getDouble("ingresos_mensuales"));
                try {
                    String nombreUsuario = rs.getString("nombre_usuario");
                    pr.setNombreUsuario(nombreUsuario);
                } catch (Exception ex) {
                    // ignore
                }
                lista.add(pr);
            }
        } catch (Exception e) {
            System.out.println("Error listar prestamos por usuario: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Inserta un préstamo en la base de datos. Devuelve true si la
     * operación se realizó sin errores.
     */
    public boolean insertar(Prestamo pr) {
        try {
            Connection cx = conexionMysql.getConexion();
            // Llamamos al procedimiento almacenado con parámetros actualizados
            CallableStatement sp = cx.prepareCall("{CALL sp_InsertarPrestamo(?,?,?,?,?,?,?,?,?,?,?)}");
            sp.setInt(1, pr.getDni());
            sp.setInt(2, pr.getIdUsuario());
            sp.setString(3, pr.getNumeroCuenta());
            sp.setDouble(4, pr.getMonto());
            sp.setInt(5, pr.getNumCuotas());
            sp.setDouble(6, pr.getTasaAnual());
            sp.setDate(7, pr.getFechaInicio());
            sp.setDate(8, pr.getFechaFin());
            sp.setString(9, pr.getEstado());
            sp.setString(10, pr.getMotivo());
            sp.setDouble(11, pr.getIngresosMensuales());
            sp.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error insertar prestamo: " + e.getMessage());
        }
        return false;
    }

    /**
     * Actualiza un préstamo existente. El registro se identifica con
     * id_prestamo. Todos los campos pueden actualizarse.
     */
    public boolean modificar(Prestamo pr) {
        try {
            Connection cx = conexionMysql.getConexion();
            // El procedimiento sp_ModificarPrestamo recibe 9 parámetros en total: el id
            // del préstamo y ocho campos a actualizar (monto, cuotas, tasa, fechas,
            // estado, motivo e ingresos). Ajustamos el número de placeholders
            CallableStatement sp = cx.prepareCall("{CALL sp_ModificarPrestamo(?,?,?,?,?,?,?,?,?)}");
            sp.setInt(1, pr.getIdPrestamo());
            sp.setDouble(2, pr.getMonto());
            sp.setInt(3, pr.getNumCuotas());
            sp.setDouble(4, pr.getTasaAnual());
            sp.setDate(5, pr.getFechaInicio());
            sp.setDate(6, pr.getFechaFin());
            sp.setString(7, pr.getEstado());
            sp.setString(8, pr.getMotivo());
            sp.setDouble(9, pr.getIngresosMensuales());
            // The stored procedure expects all nine parameters up to ingresos_mensuales, but we pass 9 values here (we skip idPrestamista etc.).
            // Since the procedure sp_ModificarPrestamo has 8 IN params after id_prestamo_in, this call matches: monto,num_cuotas,tasa_anual,fecha_inicio,fecha_fin,estado,motivo,ingresos_mensuales
            sp.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error modificar prestamo: " + e.getMessage());
        }
        return false;
    }

    /**
     * Elimina un préstamo de la base de datos por su identificador.
     */
    public boolean eliminar(int idPrestamo) {
        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_EliminarPrestamo(?)}");
            sp.setInt(1, idPrestamo);
            sp.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error eliminar prestamo: " + e.getMessage());
        }
        return false;
    }
}