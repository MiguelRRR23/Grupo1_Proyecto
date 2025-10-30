package Arreglo;

import java.util.ArrayList;
import Clase.*;

public class OPERAC {
    private ArrayList<Cuenta> cuentas;
    private ArchivoCuenta archivo;

    public OPERAC() {
    	archivo = new ArchivoCuenta();
        cuentas = archivo.cargar(); 
    }

    public void Adicionar(Cuenta c) {
        cuentas.add(c);
        archivo.guardar(cuentas);
    }

    public Cuenta Obtener(int i) {
        return cuentas.get(i);
    }

    public int Tamaño() {
        return cuentas.size();
    }

    public Cuenta BuscarPorNumero(String numero) {
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta().equalsIgnoreCase(numero)) return c;
        }
        return null;
    }

    public Cuenta BuscarPorDni(int dni) {
        for (Cuenta c : cuentas) {
            if (c.getPersona().getDni() == dni) return c;
        }
        return null;
    }

    public Cuenta BuscarPorNombre(String nombre) {
        for (Cuenta c : cuentas) {
            if (c.getPersona().getNombre().equalsIgnoreCase(nombre)) return c;
        }
        return null;
    }

    public void Eliminar(String numeroCuenta) {
        Cuenta c = BuscarPorNumero(numeroCuenta);
        if (c != null) cuentas.remove(c);
        archivo.guardar(cuentas);
    }
    
    public void GuardarArchivo() {
        archivo.guardar(cuentas);
    }
}