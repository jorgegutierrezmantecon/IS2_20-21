package es.unican.is2.practica5refactorizada;

import java.time.LocalDate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CuentaAhorroTest {
	private CuentaAhorro sut;
	private static Movimiento m1, m2, m3;
	private TarjetaDebito t1;
	private TarjetaCredito t2;
	
	@BeforeClass
	public static void inicializarMovimientos() {
		m1 = new Movimiento();
		m1.setImporte(100);
		m2 = new Movimiento();
		m2.setImporte(200);
		m3 = new Movimiento();
		m3.setImporte(1500);
	}

	@Before
	public void setUpBeforeClass() throws Exception {
		//sut = new CuentaAhorro("794311", LocalDate.now().plusYears(3), LocalDate.now().plusYears(4));
		sut = new CuentaAhorro("794311");
		t1 = new TarjetaDebito("45454545", "José", sut, LocalDate.now().plusYears(3));
		t2 = new TarjetaCredito("34343434", "José", sut, 1000, LocalDate.now().plusYears(4));
	}

	@Test
	public void testConstructor() {
		assertTrue(t1.getFechaDeCaducidad().equals(LocalDate.now().plusYears(3)));
		assertTrue(t2.getFechaDeCaducidad().equals(LocalDate.now().plusYears(4)));
		assertTrue(t1.getLimiteDebito()==1000);
		assertTrue(sut.getMovimientos().size()==0);
		assertTrue(sut.getNumCuenta().equals("794311"));
	}
	
	@Test
	public void testGetSaldoYAddMovimiento() {
		assertTrue(sut.getSaldo()==0);	

		sut.addMovimiento(m1);
		assertTrue(sut.getSaldo() == 100);
		
		sut.addMovimiento(m2);
		sut.addMovimiento(m3);
		assertTrue(sut.getSaldo()==1800);
	}
	
	@Test
	public void testRetirarSinConcepto() {
		
		try {
			sut.retirar(null, -10); // cambiamos la llamada por el nuevo metodo retirar fusionado
			fail("Debería lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		} catch (saldoInsuficienteException e) {
			fail("Debería lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar(null, 50); // cambiamos la llamada por el nuevo metodo retirar fusionado
			assertTrue(sut.getSaldo()==50);
			assertTrue(sut.getMovimientos().size()==2);
			assertTrue(sut.getMovimientos().get(1).getConcepto().equals("Retirada de efectivo"));
		} catch (datoErroneoException e) {
			fail("No debería lanzar DatoErroneoException");
		} catch (saldoInsuficienteException e) {
			fail("No debería lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar(null, 100); // cambiamos la llamada por el nuevo metodo retirar fusionado
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (datoErroneoException e) {
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (saldoInsuficienteException e) {
			
		}
	
	}
	
	@Test
	public void testIngresarSinConcepto () {
	
		// Test ingresar negativo
		try {
			sut.ingresar(null, -1); // cambiamos la llamada por el nuevo metodo ingresar fusionado
			fail("Debería lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		}

		// Test ingresar el limite
		try {
			sut.ingresar(null, 0.01); // cambiamos la llamada por el nuevo metodo ingresar fusionado
			assertTrue(sut.getSaldo()==0.01);
			assertTrue(sut.getMovimientos().size()==1);
			assertTrue(sut.getMovimientos().get(0).getConcepto().equals("Ingreso en efectivo"));
			
			sut.ingresar(null, 100); // cambiamos la llamada por el nuevo metodo ingresar fusionado
			assertTrue(sut.getSaldo()==100.01);
			assertTrue(sut.getMovimientos().size()==2);
			
		} catch (datoErroneoException e) {
			fail("No debería lanzar la excepción");
		}
		
	}

	
}
