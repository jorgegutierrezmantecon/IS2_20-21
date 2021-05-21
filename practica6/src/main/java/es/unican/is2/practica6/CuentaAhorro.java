package es.unican.is2.practica6;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

// Total: WMC=14 CCog=5
public class CuentaAhorro extends Cuenta {

	private List<Movimiento> movimientos;

	// WMC=1 CCog=0
	public CuentaAhorro(String numCuenta) { // WMC+1 CCog+0
		super(numCuenta);
		movimientos = new LinkedList<Movimiento>();
	}

	// WMC=2 CCog=1
	public void ingresar(String concepto, double importe) throws DatoErroneoException { // WMC+1 CCog+0
		compruebaCantidadCorrecta(importe);
		if (concepto == null) { // WMC+1 CCog+1
			anhadirMovimiento("Ingreso en efectivo", importe);
		} else {
			anhadirMovimiento(concepto, importe);
		}
	}

	// WMC=2 CCog=1
	public void retirar(String concepto, double importe) throws SaldoInsuficienteException, DatoErroneoException { // WMC+1 CCog+0
		compruebaSaldoCorrecto(importe);
		compruebaCantidadCorrecta(importe);
		if (concepto == null) { // WMC+1 CCog+1
			anhadirMovimiento("Retirada de efectivo", -importe);
		} else {
			anhadirMovimiento(concepto, -importe);
		}
	}

	// WMC=2 CCog=1
	public double getSaldo() { // WMC+1 CCog+0
		double saldoTotal = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) { // WMC+1 CCog+1
			Movimiento movimiento = movimientos.get(i);
			saldoTotal += movimiento.getImporte();
		}
		return saldoTotal;
	}

	// WMC=1 CCog=0
	public void addMovimiento(Movimiento movimiento) { // WMC+1 CCog+0
		movimientos.add(movimiento);
	}

	// WMC=1 CCog=0
	public List<Movimiento> getMovimientos() { // WMC+1 CCog+0
		return movimientos;
	}
	
	// 3 metodos getters eliminados en referencia a las fechas de caducidad y limite debito
	
	// WMC=1 CCog=0
	private void anhadirMovimiento(String concepto, double importe) { // WMC+1 CCog+0
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto(concepto);
		movimiento.setImporte(importe);
		addMovimiento(movimiento);
	}
	
	// WMC=2 CCog=1
	private void compruebaCantidadCorrecta(double importe) throws DatoErroneoException { // WMC+1 CCog+0
		if (importe <= 0) // WMC+1 CCog+1
			throw new DatoErroneoException("No se puede ingresar una cantidad negativa");
	}
	
	// WMC=2 CCog=1
	private void compruebaSaldoCorrecto(double importe) { // WMC+1 CCog+0
		if (getSaldo() < importe) // WMC+1 CCog+1
			throw new SaldoInsuficienteException("Saldo insuficiente");
	}

}