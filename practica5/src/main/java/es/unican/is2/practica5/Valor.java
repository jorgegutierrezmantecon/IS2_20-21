package es.unican.is2.practica5;

public class Valor {
	
	private String entidad;	
	private int numValores;
	private double cotizacionActual;
	
	// WMC=1 CCog=0
	public Valor(String entidad, int numValores, double cotizacionActual) { // WMC+1 CCog+0
		super();
		this.entidad = entidad;
		this.numValores = numValores;
		this.cotizacionActual = cotizacionActual;
	}
	
	// WMC=1 CCog=0
	public int getNumValores() { // WMC+1 CCog+0
		return numValores;
	}

	// WMC=1 CCog=0
	public void setNumValores(int numValores) { // WMC+1 CCog+0 
		this.numValores = numValores;
	}

	// WMC=1 CCog=0
	public double getCotizacionActual() { // WMC+1 CCog+0
		return cotizacionActual;
	}

	// WMC=1 CCog=0
	public void setCotizacionActual(double cotizacionActual) { // WMC+1 CCog+0
		this.cotizacionActual = cotizacionActual;
	}

	// WMC=1 CCog=0
	public String getEntidad() { // WMC+1 CCog+0
		return entidad;
	}


}