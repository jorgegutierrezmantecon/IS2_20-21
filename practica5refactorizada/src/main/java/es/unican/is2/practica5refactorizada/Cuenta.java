package es.unican.is2.practica5refactorizada;

// Total: WMC=2 CCog=0
public abstract class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { // WMC+1 CCog+0
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() { // WMC+1 CCog+0
		return numCuenta;
	}
	
	
}
