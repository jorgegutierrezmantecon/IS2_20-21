package es.unican.is2.practica5refac;

import java.time.LocalDate;

public class TarjetaDebito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	// WMC=1 CCog=0
	public TarjetaDebito(String numero, String titular, CuentaAhorro c) { // WMC+1 CCog+0
		super(numero, titular, c);
	}
	
	
	// WMC=2 CCog=1
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC+1 CCog+0
		comprobarSaldo(x);
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	// WMC=2 CCog=1
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // WMC=1 CCog=0
		comprobarSaldo(x);
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	private void comprobarSaldo(double x) throws saldoInsuficienteException {
		if (saldoDiarioDisponible<x) { // WMC+1 CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
	}
	
	// WMC=1 CCog=0
	public LocalDate getCaducidadDebito() { // WMC+1 CCog+0
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	// WMC=1 CCog=0
	public void restableceSaldo() { // WMC+1 CCog+0
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito(); 
	}
	
	// WMC=1 CCog=0
	public CuentaAhorro getCuentaAsociada() { // WMC+1 CCog+0
		return mCuentaAsociada;
	}

}