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

public void Eliminar (Banco x) {
	banc.remove(x);
}

}
