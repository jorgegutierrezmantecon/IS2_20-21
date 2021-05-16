package es.unican.is2.practica5refactorizada;

import java.time.LocalDateTime;

// Total: WMC=6 CCog=0
public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	// WMC=1 CCog=0
	public double getImporte() { // WMC+1 CCog+0
		return importe;
	}

	// WMC=1 CCog=0
	public String getConcepto() { // WMC+1 CCog+0
		return concepto;
	}

	// WMC=1 CCog=0
	public void setConcepto(String newConcepto) { // WMC+1 CCog+0
		concepto = newConcepto;
	}

	// WMC=1 CCog=0
	public LocalDateTime getFecha() { // WMC+1 CCog+0
		return fecha;
	}

	// WMC=1 CCog=0
	public void setFecha(LocalDateTime newFecha) { // WMC+1 CCog+0
		fecha = newFecha;
	}

	// WMC=1 CCog=0
	public void setImporte(double newImporte) { // WMC+1 CCog+0
		importe = newImporte;
	}
}