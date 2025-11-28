package Arreglo;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionMysql {

    private static final String URL = "jdbc:mysql://localhost:3306/scotiabank_db";
    private static final String USER = "root";
    private static final String PASS = "gustavo"; 

    public static Connection getConexion() {
        Connection cnx = null;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente.");

            
            cnx = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión a MySQL exitosa.");

        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return cnx;
    }

    public static void main(String[] args) {
        
        getConexion();
    }
}
