package es.unican.is2.practica4.seguros.modelo;

/**
 * Clase que representa a un cliente
 * @author Veronica Fernandez Gonzalez y Jorge Gutierrez Mantecon
 *
 */
public class Cliente {
	
	private String nombre;
	private String DNI;
	private boolean minusvalia;
	
	public Cliente(String nombre, String DNI, boolean minusvalia) {
		this.nombre = nombre;
		this.DNI = DNI;
		this.minusvalia = minusvalia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDNI() {
		return DNI;
	}

	public boolean isMinusvalia() {
		return minusvalia;
	}
}
