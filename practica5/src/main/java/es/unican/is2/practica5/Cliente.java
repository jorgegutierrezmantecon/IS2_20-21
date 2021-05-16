package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

// Total: WMC=8 CCog=10
public class Cliente {
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

    // WMC = 1 CCog = 0
 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  // WMC+1
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
 	// WMC = 1 CCog = 0
	public void cambiaDireccion(String calle, String zip, String localidad) { // WMC+1
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	// WMC=5 CCog=10
	public double getSaldoTotal() { // WMC+1 Ccog+0
		double total = 0.0;
		for (Cuenta c: Cuentas) { // WMC+1 Ccog+1  
			if (c instanceof CuentaAhorro) { // WMC+1 Ccog+2
				total += ((CuentaAhorro) c).getSaldo();
			} else 
				if (c instanceof CuentaValores)  { // WMC+1 CCog+3
					for (Valor v: ((CuentaValores) c).getValores()) { // WMC+1 CCog+4
						total += v.getCotizacionActual()*v.getNumValores();
					}
			}
		}
		return total;
	}
	
	// WMC=1 CCog=0
	public void anhadeCuenta(Cuenta c) { // WMC+1 CCog+0
		Cuentas.add(c);
	}
	
}