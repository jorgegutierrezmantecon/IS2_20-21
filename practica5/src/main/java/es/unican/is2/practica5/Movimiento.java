package es.unican.is2.practica5;

import java.time.LocalDateTime;

public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	// WMC=1 CCog=0
	public double getI() { // WMC+1 CCog+0
		return mImporte;
	}

	// WMC=1 CCog=0
	public String getC() { // WMC+1 CCog+0
		return mConcepto;
	}

	// WMC=1 CCog=0
	public void setC(String newMConcepto) { // WMC+1 CCog+0
		mConcepto = newMConcepto;
	}

	// WMC=1 CCog=0
	public LocalDateTime getF() { // WMC+1 CCog+0
		return mFecha;
	}

	// WMC=1 CCog=0
	public void setF(LocalDateTime newMFecha) { // WMC+1 CCog+0
		mFecha = newMFecha;
	}

	// WMC=1 CCog=0
	public void setI(double newMImporte) { // WMC+1 CCog+0
		mImporte = newMImporte;
	}
}