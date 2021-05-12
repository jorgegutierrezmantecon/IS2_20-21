package es.unican.is2.practica5refac;

import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	// WMC=1 CCog=0
	public CuentaValores(String numCuenta, List<Valor> valores) { // WMC+1 CCog+0
		super(numCuenta);
		this.valores = valores;
	}
	
	// WMC=1 CCog=0
	public List<Valor> getValores() { // WMC+1 CCog+0
		return valores;
	}
	
	// WMC=1 CCog=0
	public void anhadeValor(Valor v) { // WMC+1 CCog+0
		valores.add(v);
	}
	
	
}
