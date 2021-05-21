package es.unican.is2.practica6;

import java.time.LocalDate;

// Total: WMC=2 CCog=0
public abstract class Tarjeta {
	
	protected String numero;
	protected String titular;		
	protected CuentaAhorro cuentaAsociada;
	private LocalDate fechaDeCaducidad;

	// WMC=1 CCog=0
	protected Tarjeta(String numero, String titular, CuentaAhorro cuenta, LocalDate fechaDeCaducidad) { // WMC+1 CCog+0
		this.numero = numero;
		this.titular = titular;
		this.cuentaAsociada = cuenta;
		this.fechaDeCaducidad = fechaDeCaducidad;
	}
	
	// WMC=1 CCog=0
	public LocalDate getFechaDeCaducidad() { // WMC+1 CCog+0
		return this.fechaDeCaducidad;
	}
	
	// WMC=1 CCog=0
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void retirarEnCajero(double importe) throws SaldoInsuficienteException, DatoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double importe)
			throws SaldoInsuficienteException, DatoErroneoException;
	
}