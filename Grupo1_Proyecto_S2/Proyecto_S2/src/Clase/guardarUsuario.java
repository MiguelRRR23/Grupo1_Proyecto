package Clase;
import java.io.*;
import java.util.ArrayList;

public class guardarUsuario {
	private String ruta = "C:\\Users\\User\\Desktop\\PROYECTO22\\Grupo1_Proyecto\\Grupo1_Proyecto_S2\\Proyecto_S2\\src\\Data\\usuarios.txt";

    public ArrayList<Usuario> cargar() {
        ArrayList<Usuario> lista = new ArrayList<>();

        try {
            File file = new File(ruta);
            if (!file.exists()) return lista;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    lista.add(new Usuario(partes[0], partes[1]));
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error al leer usuarios.");
        }

        return lista;
    }

    public void guardar(ArrayList<Usuario> lista) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ruta));
            for (Usuario u : lista) {
                pw.println(u.getUsuario() + ";" + u.getClave());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar usuarios.");
        }
    }
}
