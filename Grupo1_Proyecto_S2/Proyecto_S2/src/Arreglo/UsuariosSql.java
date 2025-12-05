package Arreglo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.util.ArrayList;

/**
 * Clase de acceso a datos para la tabla de usuarios. Permite listar,
 * insertar y eliminar usuarios de la base de datos. Está pensada para
 * ser utilizada desde interfaces administrativas para gestionar cuentas
 * de empleados y administradores.
 */
public class UsuariosSql {

    /**
     * Obtiene la lista de usuarios registrados en la base de datos con
     * información de sus horarios. Cada elemento del ArrayList es un
     * arreglo de String de longitud 7 con el siguiente formato:
     * [0] id_usuario, [1] usuario, [2] clave, [3] rol,
     * [4] id_turno (o "-"), [5] día(s) del turno (o "-"),
     * [6] rango horario (o "-").
     *
     * @return lista de usuarios y sus atributos principales
     */
    public ArrayList<String[]> listar() {
        ArrayList<String[]> lista = new ArrayList<>();
        try {
            Connection cx = conexionMysql.getConexion();
            if (cx == null) return lista;
            // Utilizamos el procedimiento almacenado que ya incluye los campos de horario
            CallableStatement sp = cx.prepareCall("{CALL sp_ListarUsuarios()}");
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                String idUsuarioStr = String.valueOf(rs.getInt("id_usuario"));
                String usuario = rs.getString("usuario");
                String clave = rs.getString("clave");
                String rol = rs.getString("rol");
                int idTurno = rs.getInt("id_turno");
                String turnoStr = rs.wasNull() ? "-" : String.valueOf(idTurno);
                String diaTurnos = rs.getString("dia_turnos");
                if (diaTurnos == null) diaTurnos = "-";
                String horario = rs.getString("horario");
                if (horario == null) horario = "-";
                lista.add(new String[]{idUsuarioStr, usuario, clave, rol, turnoStr, diaTurnos, horario});
            }
        } catch (Exception e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    /**
     //Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario nombre de usuario
     * @param clave contraseña
     * @param rol rol del usuario (EMPLEADO o ADMIN)
     * @return true si la inserción fue exitosa
     */
    public boolean insertarUsuario(String usuario, String clave, String rol, Integer idTurno) {
        try {
            Connection cx = conexionMysql.getConexion();
            if (cx == null) return false;
            // Llamar al procedimiento almacenado que acepta el turno como parámetro
            CallableStatement sp = cx.prepareCall("{CALL sp_InsertarUsuario(?,?,?,?)}");
            sp.setString(1, usuario);
            sp.setString(2, clave);
            sp.setString(3, rol);
            if (idTurno == null) {
                sp.setNull(4, java.sql.Types.INTEGER);
            } else {
                sp.setInt(4, idTurno);
            }
            sp.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
        }
        return false;
    }

    /**
     * Elimina un usuario de la base de datos en función de su nombre de usuario.
     *
     * @param usuario nombre de usuario a eliminar
     * @return true si la eliminación fue exitosa
     */
    public boolean eliminarUsuario(String usuario) {
        try {
            Connection cx = conexionMysql.getConexion();
            if (cx == null) return false;
            PreparedStatement ps = cx.prepareStatement(
                    "DELETE FROM usuarios WHERE usuario=?");
            ps.setString(1, usuario);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
        return false;
    }
}