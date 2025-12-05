package Arreglo;

import Clase.*;
import java.sql.*;
import java.util.ArrayList;

public class CuentasSql {

  
    public ArrayList<Cuenta> listar() {
        ArrayList<Cuenta> lista = new ArrayList<>();

        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_ListarCuentas()}");
            ResultSet rs = sp.executeQuery();

            while (rs.next()) {

                Persona p = new Persona(
                    rs.getInt("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    "",
                    "",
                    ""
                );

                // Crear un banco genérico, ya que el nombre no se almacena en la tabla
                Banco b = new Banco(1, "Scotiabank", "", "", "", "", "");

                // Leer las nuevas columnas (num_cuotas y fechas) de la consulta
                int numCuotas = rs.getInt("num_cuotas");
                java.sql.Date fechaInicio = rs.getDate("fecha_inicio");
                java.sql.Date fechaFin = rs.getDate("fecha_fin");

                Cuenta c = new Cuenta(
                    rs.getString("numero_cuenta"),
                    p,
                    b,
                    rs.getString("tipo_cuenta"),
                    rs.getDouble("monto"),
                    numCuotas,
                    fechaInicio,
                    fechaFin
                );

                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error listar cuentas: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR PERSONA 
    public Persona buscarPersonaDNI(int dni) {

        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_ConsultarDNI(?)}");
            sp.setInt(1, dni);

            ResultSet rs = sp.executeQuery();

            if (rs.next()) {
                return new Persona(
                    rs.getInt("dni_persona"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("email")
                );
            }

        } catch (Exception e) {
            System.out.println("Error buscar persona DNI: " + e.getMessage());
        }

        return null;
    }
    
    public boolean editarPersona(Persona p) {
        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_Editar(?,?,?,?,?,?)}");

            sp.setInt(1, p.getDni());
            sp.setString(2, p.getNombre());
            sp.setString(3, p.getApellido());
            sp.setString(4, p.getDireccion());
            sp.setString(5, p.getTelefono());
            sp.setString(6, p.getEmail());

            sp.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error editar persona: " + e.getMessage());
        }
        return false;
    }

    
    public Cuenta buscarCuentaDNI(int dni) {

        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_BuscarCuentaDNI(?)}");
            sp.setInt(1, dni);

            ResultSet rs = sp.executeQuery();

            if (rs.next()) {

                Persona p = new Persona(
                    rs.getInt("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    "",
                    "",
                    ""
                );

                Banco b = new Banco(1, "Scotiabank", "", "", "", "", "");

                int numCuotas = rs.getInt("num_cuotas");
                java.sql.Date fechaInicio = rs.getDate("fecha_inicio");
                java.sql.Date fechaFin = rs.getDate("fecha_fin");

                return new Cuenta(
                    rs.getString("numero_cuenta"),
                    p,
                    b,
                    rs.getString("tipo_cuenta"),
                    rs.getDouble("monto"),
                    numCuotas,
                    fechaInicio,
                    fechaFin
                );
            }

        } catch (Exception e) {
            System.out.println("Error buscar cuenta DNI: " + e.getMessage());
        }

        return null;
    }

    
    public boolean insertar(Cuenta c) {
        try {
            Connection cx = conexionMysql.getConexion();
            // Llamar al procedimiento que ahora admite cuotas y fechas
            CallableStatement sp = cx.prepareCall("{CALL sp_InsertarCuenta(?,?,?,?,?,?,?)}");

            sp.setString(1, c.getNumeroCuenta());
            sp.setInt(2, c.getPersona().getDni());
            sp.setString(3, c.getTipoCuenta());
            sp.setDouble(4, c.getMonto());
            sp.setInt(5, c.getNumeroCuotas());
            sp.setDate(6, c.getFechaInicio());
            sp.setDate(7, c.getFechaFin());

            sp.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error insertar: " + e.getMessage());
        }
        return false;
    }
    
    public boolean insertarPersona(Persona p) {
        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_Insertar(?,?,?,?,?,?)}");

            sp.setInt(1, p.getDni());
            sp.setString(2, p.getNombre());
            sp.setString(3, p.getApellido());
            sp.setString(4, p.getDireccion());
            sp.setString(5, p.getTelefono());
            sp.setString(6, p.getEmail());

            sp.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error insertar persona: " + e.getMessage());
        }
        return false;
    }

    // ELIMINAR CUENTA
    public boolean eliminar(String numero) {
        try {
            Connection cx = conexionMysql.getConexion();
            CallableStatement sp = cx.prepareCall("{CALL sp_EliminarCuenta(?)}");
            sp.setString(1, numero);

            sp.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error eliminar cuenta: " + e.getMessage());
        }
        return false;
    }

    // MODIFICAR CUENTA
    public boolean modificar(Cuenta c) {
        try {
            Connection cx = conexionMysql.getConexion();
            // El procedimiento ahora actualiza tipo, monto, cuotas y fechas
            CallableStatement sp = cx.prepareCall("{CALL sp_ModificarCuenta(?,?,?,?,?,?)}");

            sp.setString(1, c.getNumeroCuenta());
            sp.setString(2, c.getTipoCuenta());
            sp.setDouble(3, c.getMonto());
            sp.setInt(4, c.getNumeroCuotas());
            sp.setDate(5, c.getFechaInicio());
            sp.setDate(6, c.getFechaFin());

            sp.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error modificar cuenta: " + e.getMessage());
        }
        return false;
    }
}
