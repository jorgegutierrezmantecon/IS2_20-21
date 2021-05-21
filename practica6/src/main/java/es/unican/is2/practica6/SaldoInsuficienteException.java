package es.unican.is2.practica6;

@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {

	public SaldoInsuficienteException (String mensaje) {
		super(mensaje);
	}
}
