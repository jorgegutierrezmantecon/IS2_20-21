package es.unican.is2.practica6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class TarjetaCredito extends Tarjeta {
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	// WMC=1 CCog=0
	public TarjetaCredito(String numero, String titular, CuentaAhorro c, double credito) { // WMC+1 CCog+0
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	private void compruebaCantidadCorrecta(double x) throws datoErroneoException {
		if (x<0) // WMC+1 CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
	}

	@Override
	// WMC=3 CCog=2
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // WMC+1 CCog+0
		compruebaCantidadCorrecta(x);
		
		if (getGastosAcumulados() + x > mCredito) // WMC+1 CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Compra a crédito en: " + datos);
		m.setImporte(-x);
		mMovimientosMensuales.add(m);
	}
	
	// WMC=2 CCog=1
    public double getGastosAcumulados() { // WMC+1 CCog+0
		double r;
		r = getImporteMovimientos();
		return -r;
	}

	private double getImporteMovimientos() {
		double r = 0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { // WMC+1 CCog+1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getImporte();
		}
		return r;
	}
	
	// WMC=1 CCog=0
	public LocalDate getCaducidadCredito() { // WMC+1 CCog+0
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	// WMC=3 CCog=2
	public void liquidar() { // WMC+1 CCog+0
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setFecha(now);
		liq.setConcepto("Liquidación de operaciones tarjeta crédito");
		double r;
		r = getImporteMovimientos();
		liq.setImporte(r);
	
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
	
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC+1 CCog+0
		if (x<0) // WMC+1 CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Retirada en cajero automático");
		x += x * 0.05; // Añadimos una comisión de un 5%
		m.setImporte(-x);
		
		if (getGastosAcumulados()+x > mCredito) // WMC+1 CCog+1
			throw new saldoInsuficienteException("Crédito insuficiente");
		else {
			mMovimientosMensuales.add(m);
		}
	}

}