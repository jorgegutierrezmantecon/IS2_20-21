package es.unican.is2.practica4.seguros.modelo;

import java.time.LocalDate;

@SuppressWarnings("serial")
/**
 * Implementacion de la clase Seguro con su metodo precio
 * @author Jorge Gutierrez Mantecon y Veronica Fernandez Gonzalez
 *
 */
public class Seguro {
	
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	
	// enum de cobertura
	public enum Cobertura
	{
		TERCEROS,
		TODO_RIESGO,
		TERCEROS_LUNAS
	}
	private Cobertura cobertura;
	private Cliente cliente;
	
	// excepcion que se lanza cuando un dato esta mal introducido
	public class DatoIncorrectoException extends RuntimeException{};
	
	// constructor de la clase
	public Seguro(int potencia, Cobertura cobertura, Cliente cliente) {
		// la potencia no puede ser 0 o menos
		if (potencia <= 0) {
			throw new DatoIncorrectoException();
		} else {
			this.potenciaCV = potencia;
		}
		
		// la cobertura no puede ser null
		if (cobertura == null) {
			throw new DatoIncorrectoException();
		}
		
		// la cobertura debe tener uno de los tres valores definidos (aunque por definicion de java es
		// imposible que no sea asi, así que este if no se va a evaluar)
		if (cobertura.equals(Cobertura.TERCEROS) || cobertura.equals(Cobertura.TODO_RIESGO) 
				|| cobertura.equals(Cobertura.TERCEROS_LUNAS)) {
			this.cobertura = cobertura;
		} else {
			throw new DatoIncorrectoException(); // no se lanzara nunca debido a que java no lo permite
		}
		
		// el cliente no debe ser null
		if (cliente == null) {
			throw new DatoIncorrectoException();
		} else {
			this.cliente = cliente;
		}
	}

	// metodo precio de la clase Seguro
	public double precio() {
		double precioBase;
		double precioTotal;
		
		// ponemos un precio base para cada cobertura
		if (cobertura.equals(Cobertura.TERCEROS)) {
			precioBase = 400;
		} else if (cobertura.equals(Cobertura.TODO_RIESGO)) {
			precioBase = 1000;
		} else if (cobertura.equals(Cobertura.TERCEROS_LUNAS)) {
			precioBase = 600;
		} else {
			throw new DatoIncorrectoException(); // no se lanzara nunca debido a que java no lo permite
		}
		
		double porcentajeSubida = 0;
		
		// comprobamos la subida por potencia
		if (potenciaCV <= 0) {
			throw new DatoIncorrectoException(); // en el test de cobertura no se lanzara debido a que ya lo hemos comprobado en el constructor
		} else if (potenciaCV >= 90 && potenciaCV <= 110) {
			porcentajeSubida = 5.0;
		} else if (potenciaCV > 110) {
			porcentajeSubida = 20.0;
		}
		
		double subidaPotencia = 0;
		
		// comprobamos la subida por potencia
		if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusDays(366))
				&& fechaUltimoSiniestro.isBefore(LocalDate.now())) {
			subidaPotencia = 200;
		} else if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusDays(365*3+1))
				&& fechaUltimoSiniestro.isBefore(LocalDate.now().minusDays(365))) {
			subidaPotencia = 50;
		} else if (fechaUltimoSiniestro.isAfter(LocalDate.now())) {
			throw new DatoIncorrectoException();
		}
		
		
		double descuento = 0;
		
		// si el cliente es minusvalido, tiene descuento
		if (cliente == null) {
			throw new DatoIncorrectoException(); // en el test de cobertura no se lanzara debido a que ya lo hemos comprobado en el constructor
		} else if (cliente.isMinusvalia()) {
			descuento += 25.0;
		}
		
		precioTotal = ((precioBase + precioBase*((double)porcentajeSubida/100.0)) + subidaPotencia);
		return precioTotal - precioTotal*((double)descuento/100.0);
	}

	// metodos getters necesarios
	
	public int getPotenciaCV() {
		return potenciaCV;
	}

	public Cobertura getCobertura() {
		return cobertura;
	}
	
	// metodo necesario para establecer la fecha del ultimo siniestro
	public void setFechaUltimoSiniestro(LocalDate fechaUltimoSiniestro) {
		this.fechaUltimoSiniestro = fechaUltimoSiniestro;
	}
	
	
}
