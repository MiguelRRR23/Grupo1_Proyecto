package Arreglo;

import java.util.ArrayList;
import Clase.*;

public class OPERAC {
    private ArrayList<Cuenta> cuentas;
   

   

    public void Adicionar(Cuenta c) {
        cuentas.add(c);
       
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
        
    }
    
   
}