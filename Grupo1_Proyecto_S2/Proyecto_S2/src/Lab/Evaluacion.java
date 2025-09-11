package Lab;

public class Evaluacion {

private double n1, n2, n3, n4;

public Evaluacion(double n1, double n2, double n3, double n4) {

	this.n1 = n1;
	this.n2 = n2;
	this.n3 = n3;
	this.n4 = n4;
}


public double calcularPromedio() {
	return (n1+n2+n3+n4)/ 4.0;
}



}
