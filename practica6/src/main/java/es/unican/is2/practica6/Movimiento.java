package es.unican.is2.practica6;

import java.time.LocalDateTime;

public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	// WMC=1 CCog=0
	public double getImporte() { // WMC+1 CCog+0
		return mImporte;
	}

	// WMC=1 CCog=0
	public String getConcepto() { // WMC+1 CCog+0
		return mConcepto;
	}

	// WMC=1 CCog=0
	public void setConcepto(String newMConcepto) { // WMC+1 CCog+0
		mConcepto = newMConcepto;
	}

	// WMC=1 CCog=0
	public LocalDateTime getFecha() { // WMC+1 CCog+0
		return mFecha;
	}

	// WMC=1 CCog=0
	public void setFecha(LocalDateTime newMFecha) { // WMC+1 CCog+0
		mFecha = newMFecha;
	}

	// WMC=1 CCog=0
	public void setImporte(double newMImporte) { // WMC+1 CCog+0
		mImporte = newMImporte;
	}
}