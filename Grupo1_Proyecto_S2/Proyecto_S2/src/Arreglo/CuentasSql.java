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

                Banco b = new Banco(1, "Scotiabank", "", "", "", "", "");

                Cuenta c = new Cuenta(
                    rs.getString("numero_cuenta"),
                    p,
                    b,
                    rs.getString("tipo_cuenta"),
                    rs.getDouble("monto")
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
                    rs.getInt("DNI"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDO"),
                    rs.getString("DIRECCION"),
                    rs.getString("TELEFONO"),
                    rs.getString("EMAIL")
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

                return new Cuenta(
                    rs.getString("numero_cuenta"),
                    p,
                    b,
                    rs.getString("tipo_cuenta"),
                    rs.getDouble("monto")
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
            CallableStatement sp = cx.prepareCall("{CALL sp_InsertarCuenta(?,?,?,?,?)}");

            sp.setString(1, c.getNumeroCuenta());
            sp.setInt(2, c.getPersona().getDni());
            sp.setString(3, c.getTipoCuenta());
            sp.setDouble(4, c.getMonto());
            sp.setString(5, c.getBanco().getNombreBanco());

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
            CallableStatement sp = cx.prepareCall("{CALL sp_ModificarCuenta(?,?,?)}");

            sp.setString(1, c.getNumeroCuenta());
            sp.setString(2, c.getTipoCuenta());
            sp.setDouble(3, c.getMonto());

            sp.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error modificar cuenta: " + e.getMessage());
        }
        return false;
    }
}
