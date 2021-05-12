package es.unican.is2.practica5refac;

@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException {

	public saldoInsuficienteException (String mensaje) {
		super(mensaje);
	}
}
