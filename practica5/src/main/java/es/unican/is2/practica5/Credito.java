package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

// Total: WMC=16 CCog=7
public class Credito extends Tarjeta {
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	// WMC=1 CCog=0
	public Credito(String numero, String titular, CuentaAhorro c, double credito) { // WMC+1 CCog+0
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi?n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	// WMC=3 CCog=2
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC+1 CCog+0
		if (x<0) // WMC+1 CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero autom?tico");
		x += x * 0.05; // A?adimos una comisi?n de un 5%
		m.setI(-x);
		
		if (getGastosAcumulados()+x > mCredito) // WMC+1 CCog+1
			throw new saldoInsuficienteException("Cr?dito insuficiente");
		else {
			mMovimientosMensuales.add(m);
		}
	}

	@Override
	// WMC=3 CCog=2
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // WMC+1 CCog+0
		if (x<0) // WMC+1 CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito) // WMC+1 CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a cr?dito en: " + datos);
		m.setI(-x);
		mMovimientosMensuales.add(m);
	}
	
	// WMC=2 CCog=1
    public double getGastosAcumulados() { // WMC+1 CCog+0
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { // WMC+1 CCog+1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		return -r;
	}
	
	// WMC=1 CCog=0
	public LocalDate getCaducidadCredito() { // WMC+1 CCog+0
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * M?todo que se invoca autom?ticamente el d?a 1 de cada mes
	 */
	// WMC=3 CCog=2
	public void liquidar() { // WMC+1 CCog+0
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidaci?n de operaciones tarjeta cr?dito");
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { // WMC+1 CCog+1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(r);
	
		if (r != 0) // WMC+1 CCog+1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	// WMC=1 CCog=0
	public List<Movimiento> getMovimientosUltimoMes() {
		return mMovimientosMensuales;
	}
	
	// WMC=1 CCog=0
	public Cuenta getCuentaAsociada() {
		return mCuentaAsociada;
	}
	
	// WMC=1 CCog=0
	public List<Movimiento> getMovimientos() {
		return mhistoricoMovimientos;
	}

}