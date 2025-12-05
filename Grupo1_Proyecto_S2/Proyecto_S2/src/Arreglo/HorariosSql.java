package Arreglo;

import Clase.Horario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Clase de acceso a datos para la entidad Horario. Proporciona un método
 * para listar todos los horarios registrados en la base de datos
 * mediante el procedimiento almacenado correspondiente. Esta clase
 * permite poblar componentes de interfaz (combo boxes) con los turnos
 * disponibles.
 */
public class HorariosSql {

    /**
     * Recupera todos los horarios de la base de datos.
     * @return lista de objetos Horario
     */
    public ArrayList<Horario> listar() {
        ArrayList<Horario> lista = new ArrayList<>();
        try {
            Connection cx = conexionMysql.getConexion();
            if (cx == null) return lista;
            CallableStatement sp = cx.prepareCall("{CALL sp_ListarHorarios()}");
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                Horario h = new Horario();
                h.setIdTurno(rs.getInt("id_turno"));
                h.setDiaTurnos(rs.getString("dia_turnos"));
                h.setHorario(rs.getString("horario"));
                lista.add(h);
            }
        } catch (Exception e) {
            System.out.println("Error al listar horarios: " + e.getMessage());
        }
        return lista;
    }
}