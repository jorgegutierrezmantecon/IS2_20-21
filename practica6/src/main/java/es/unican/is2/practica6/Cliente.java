package es.unican.is2.practica6;

import java.util.LinkedList;
import java.util.List;

// Total: WMC=8 CCog=4
public class Cliente {
	
	private String nombre;
	private Direccion direccion;
	private String telefono;
	private String dni;

	private List<Cuenta> cuentas = new LinkedList<Cuenta>();

    // WMC=1 CCog=0
 	public Cliente(String titular, Direccion direccion, String telefono, String dni) {  // WMC+1 CCog+0
		this.nombre = titular;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dni = dni;
	}
	
 	// WMC=1 CCog=0
	public void cambiaDireccion(Direccion direccion) { // WMC+1 CCog+0
		this.direccion = direccion;
	}
	
	// WMC=2 CCog=1
	public double getSaldoTotal() { // WMC+1 CCog+0
		double total = 0.0;
		for (Cuenta cuenta: cuentas) { // WMC+1 CCog+1  
			total += getSaldoCuenta(cuenta);
		}
		return total;
	}

	// Metodo que realiza el calculo del saldo de una cuenta
	// WMC=3 CCog=3
	private double getSaldoCuenta(Cuenta cuenta) { // WMC+1 CCog+0
		double total = 0.0;
		if (cuenta instanceof CuentaAhorro) { // WMC+1 CCog+1
			total = ((CuentaAhorro) cuenta).getSaldo();
		} else { 
			for (Valor valor: ((CuentaValores) cuenta).getValores()) { // WMC+1 CCog+2
				total += valor.getCotizacionActual()*valor.getNumValores();
			}
		}
		return total;
	}
	
	// WMC=1 CCog=0
	public void anhadeCuenta(Cuenta cuenta) { // WMC+1 CCog+0
		cuentas.add(cuenta);
	}
	
	// METODOS GETTERS DE LOS ATRIBUTOS PRIVADOS
    public String getNombre() {
		return nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDni() {
		return dni;
	}
	
}