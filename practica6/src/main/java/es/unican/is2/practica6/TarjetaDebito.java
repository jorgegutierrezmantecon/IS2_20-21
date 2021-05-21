package es.unican.is2.practica6;

import java.time.LocalDate;

// Total: WMC=8 CCog=1
public class TarjetaDebito extends Tarjeta {
	
	private double saldoDiarioDisponible;
	private double limiteDebito;
	private static final double LIMITE_INICIAL = 1000; // ponemos el limite de debito como una constante

	// WMC=1 CCog=0
	public TarjetaDebito(String numero, String titular, CuentaAhorro cuenta, LocalDate fechaDeCaducidad) { // WMC+1 CCog+0
		super(numero, titular, cuenta, fechaDeCaducidad);
		limiteDebito = LIMITE_INICIAL;
	}
	
	
	// WMC=1 CCog=0
	@Override
	public void retirarEnCajero(double importe) throws SaldoInsuficienteException, DatoErroneoException { // WMC+1 CCog+0
		comprobarSaldoCorrecto(importe);
		this.cuentaAsociada.retirar("Retirada en cajero automático", importe);
		saldoDiarioDisponible-=importe;
	}
	
	// WMC=1 CCog=0
	@Override
	public void pagoEnEstablecimiento(String datos, double importe) throws SaldoInsuficienteException, DatoErroneoException { // WMC+1 CCog+0
		comprobarSaldoCorrecto(importe);
		this.cuentaAsociada.retirar("Compra en : " + datos, importe);
		saldoDiarioDisponible-=importe;
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	// WMC=1 CCog=0
	public void restableceSaldo() { // WMC+1 CCog+0
		saldoDiarioDisponible = limiteDebito; 
	}
	
	// WMC=1 CCog=0
	public double getLimiteDebito() { // WMC+1 CCog+0
		return limiteDebito;
	}
	
	// WMC=2 CCog=1
	private void comprobarSaldoCorrecto(double importe) throws SaldoInsuficienteException { // WMC+1 CCog+0
		if (saldoDiarioDisponible<importe) { // WMC+1 CCog+1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
	}

}