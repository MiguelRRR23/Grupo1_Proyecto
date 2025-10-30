package Clase;

import java.io.*;
import java.util.ArrayList;

public class ArchivoCuenta {

    private String ruta = "C:\\Users\\User\\Desktop\\Nueva carpeta (4)\\Grupo1_Proyecto\\Grupo1_Proyecto_S2\\Proyecto_S2\\src\\Data\\cuentas.txt";

  
    public void guardar(ArrayList<Cuenta> lista) {
        try {
            FileWriter fw = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fw);

            for (Cuenta c : lista) {
                pw.println(
                    c.getNumeroCuenta() + ";" +
                    c.getPersona().getDni() + ";" +
                    c.getPersona().getNombre() + ";" +
                    c.getBanco().getNombreBanco() + ";" +
                    c.getTipoCuenta() + ";" +
                    c.getMonto()
                );
            }

            pw.close();
            fw.close();
            System.out.println("Archivo guardado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    
    public ArrayList<Cuenta> cargar() {
        ArrayList<Cuenta> lista = new ArrayList<>();

        try {
            File file = new File(ruta);
            if (!file.exists()) return lista; 

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 6) {
                    String numCuenta = partes[0];
                    int dni = Integer.parseInt(partes[1]);
                    String nombre = partes[2];
                    String bancoNom = partes[3];
                    String tipo = partes[4];
                    double monto = Double.parseDouble(partes[5]);

                    Persona p = new Persona(dni, nombre, "", "", "", "");
                    Banco b = new Banco(1, bancoNom, "Av. Canaval y Moreyra 522", "Lima", "013122222", "20100105939", "Carlos Ramos");
                    Cuenta c = new Cuenta(numCuenta, p, b, tipo, monto);
                    lista.add(c);
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }

        return lista;
    }
}
