package es.unican.is2.practica6;

// Total: WMC=1 CCog=0
public class Direccion {
	private String calle;
	private String cp;
	private String localidad;

	// WMC=1 CCog=0
	public Direccion(String calle, String cp, String localidad) { // WMC+1 CCog+0
		this.calle = calle;
		this.cp = cp;
		this.localidad = localidad;
	}
	
	// METODOS GETTERS
	public String getCalle() {
		return calle;
	}

	public String getCp() {
		return cp;
	}

	public String getLocalidad() {
		return localidad;
	}
}