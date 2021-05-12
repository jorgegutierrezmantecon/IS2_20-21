package es.unican.is2.practica5;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	// WMC=1 CCog=0
	public Debito(String numero, String titular, CuentaAhorro c) { // WMC+1 CCog+0
		super(numero, titular, c);
	}
	
	
	// WMC=2 CCog=1
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC+1 CCog+0
		if (saldoDiarioDisponible<x) { // WMC+1 CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero autom�tico", x);
		saldoDiarioDisponible-=x;
	}
	
	// WMC=2 CCog=1
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // WMC=1 CCog=0
		if (saldoDiarioDisponible<x) { // WMC+1 CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	// WMC=1 CCog=0
	public LocalDate getCaducidadDebito() { // WMC+1 CCog+0
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * M�todo invocado autom�ticamente a las 00:00 de cada d�a
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