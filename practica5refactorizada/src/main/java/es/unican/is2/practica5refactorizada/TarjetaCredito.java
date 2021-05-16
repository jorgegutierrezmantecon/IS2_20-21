package es.unican.is2.practica5refactorizada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

// Total: WMC=15 CCog=4
public class TarjetaCredito extends Tarjeta {
	
	private double credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;
	private static final double COMISION = 0.05;
	
	// WMC=1 CCog=0
	public TarjetaCredito(String numero, String titular, CuentaAhorro cuenta, double credito, LocalDate fechaDeCaducidad) { // WMC+1 CCog+0
		super(numero, titular, cuenta, fechaDeCaducidad);
		this.credito = credito;
		movimientosMensuales = new LinkedList<Movimiento>();
		historicoMovimientos = new LinkedList<Movimiento>();
	}

	@Override
	// WMC=1 CCog=0
	public void pagoEnEstablecimiento(String datos, double importe) throws saldoInsuficienteException, datoErroneoException { // WMC+1 CCog+0
		compruebaCantidadCorrecta(importe);
		compruebaSaldoCorrecto(importe, "Saldo insuficiente");		
		anhadirMovimiento("Compra a crédito en: " + datos, importe);
	}
	
	@Override
	// WMC=1 CCog=0
	public void retirarEnCajero(double importe) throws saldoInsuficienteException, datoErroneoException { // WMC+1 CCog+0
		compruebaCantidadCorrecta(importe);		
		importe += importe * COMISION; // Añadimos la comision		
		compruebaSaldoCorrecto(importe, "Crédito insuficiente");		
		anhadirMovimiento("Retirada en cajero automático", importe);
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	// WMC=2 CCog=1
	public void liquidar() { // WMC+1 CCog+0
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setFecha(now);
		liq.setConcepto("Liquidación de operaciones tarjeta crédito");
		double importeTotal;
		importeTotal = getImporteMovimientos();
		liq.setImporte(importeTotal);
	
		if (importeTotal != 0) // WMC+1 CCog+1
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	// WMC=1 CCog=0
	public List<Movimiento> getMovimientosUltimoMes() {
		return movimientosMensuales;
	}
	
	// WMC=1 CCog=0
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	}
	
	// WMC=2 CCog=1
	private void compruebaSaldoCorrecto(double importe, String mensaje) throws saldoInsuficienteException { // WMC+1 CCog+0
		if (-getImporteMovimientos() + importe > credito) // WMC+1 CCog+1
			throw new saldoInsuficienteException(mensaje);
	}
	
	// WMC=2 CCog=1
	private void compruebaCantidadCorrecta(double importe) throws datoErroneoException { // WMC+1 CCog+0
		if (importe<0) // WMC+1 CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
	}
	
	// WMC=1 CCog=0
	private void anhadirMovimiento(String concepto, double importe) { // WMC+1 CCog+0
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto(concepto);
		movimiento.setImporte(-importe);
		movimientosMensuales.add(movimiento);
	}
	
	// WMC=2 CCog=1
	private double getImporteMovimientos() { // WMC+1 CCog+0
		double importeTotal = 0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { // WMC+1 CCog+1
			Movimiento movimiento = (Movimiento) movimientosMensuales.get(i);
			importeTotal += movimiento.getImporte();
		}
		return importeTotal;
	}

}