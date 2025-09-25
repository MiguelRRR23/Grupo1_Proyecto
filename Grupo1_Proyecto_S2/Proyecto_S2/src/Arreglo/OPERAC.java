package Arreglo;

import java.util.ArrayList;

import Clase.Banco;

public class OPERAC {
private ArrayList<Banco>banc;
public OPERAC() {
	banc=new ArrayList<Banco>();
	Adicionar(new Banco(12345678, "Marcos estrada", "credito", 3000));
	Adicionar(new Banco(123456789, "Marquito Inga", "creditos", 5000));
	Adicionar(new Banco(12345675, "Carlos estra", "creditoss", 5000));
}
public void Adicionar(Banco x) {
	
	banc.add(x);
}

public void Adicionar(int dni, String nom, String tipo, double canti) {
    Banco b = new Banco(dni, nom, tipo, canti);
    banc.add(b);
}



public Banco Obtener(int x) {
	return banc.get(x);
	
}
public int Tamaño() {
	return banc.size();
}

public Banco Buscar(int dni) {
	for(int i=0;i<Tamaño(); i++){
		if(Obtener(i).getDni()==dni) return Obtener(i);
	}
	return null;
}
public Banco Buscar(String nom) {
    for (Banco b : banc) {
        if (b.getNom().equalsIgnoreCase(nom)) return b;
    }
    return null;
}

public Banco Buscar(String nom, String tipo) {
	for (Banco b : banc) {
        if (b.getNom().equalsIgnoreCase(nom)) {
            return b;
        }
    }
    return null;
}



public void Eliminar (Banco x) {
	banc.remove(x);
}

}
