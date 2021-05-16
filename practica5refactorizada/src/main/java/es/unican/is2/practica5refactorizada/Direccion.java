package es.unican.is2.practica5refactorizada;

// Total: WMC=1 CCog=0
public class Direccion {
	public String calle;
	public String CP;
	public String localidad;

	// WMC=1 CCog=0
	public Direccion(String calle, String CP, String localidad) { // WMC+1 CCog+0
		this.calle = calle;
		this.CP = CP;
		this.localidad = localidad;
	}
}