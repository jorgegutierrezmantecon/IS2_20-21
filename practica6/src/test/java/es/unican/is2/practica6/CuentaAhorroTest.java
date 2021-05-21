package es.unican.is2.practica6;

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
		sut = new CuentaAhorro("794311");
		t1 = new TarjetaDebito("45454545", "José", sut, LocalDate.now().plusYears(3));
		t2 = new TarjetaCredito("34343434", "José", sut, 1000, LocalDate.now().plusYears(4));
	}
	
	@Test
	public void testConstructor() {
		assertEquals(LocalDate.now().plusYears(3),t1.getFechaDeCaducidad());
		assertEquals(LocalDate.now().plusYears(4),t2.getFechaDeCaducidad());
		assertEquals(Double.doubleToLongBits(1000),Double.doubleToLongBits(t1.getLimiteDebito()));
		assertEquals(0,sut.getMovimientos().size());
		assertEquals("794311",sut.getNumCuenta());
	}
	
	@Test
	public void testGetSaldoYAddMovimiento() {
		assertEquals(Double.doubleToLongBits(0),Double.doubleToLongBits(sut.getSaldo()));	

		sut.addMovimiento(m1);
		assertEquals(Double.doubleToLongBits(100),Double.doubleToLongBits(sut.getSaldo()));
		
		sut.addMovimiento(m2);
		sut.addMovimiento(m3);
		assertEquals(Double.doubleToLongBits(1800),Double.doubleToLongBits(sut.getSaldo()));
	}
	
	@Test
	public void testRetirarSinConcepto() {
		
		try {
			sut.retirar(null, -10);
			fail("Debería lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		} catch (SaldoInsuficienteException e) {
			fail("Debería lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar(null, 50);
			assertEquals(Double.doubleToLongBits(50),Double.doubleToLongBits(sut.getSaldo()));
			assertEquals(2,sut.getMovimientos().size());
			assertEquals("Retirada de efectivo",sut.getMovimientos().get(1).getConcepto());
		} catch (DatoErroneoException e) {
			fail("No debería lanzar DatoErroneoException");
		} catch (SaldoInsuficienteException e) {
			fail("No debería lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar(null, 100);
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (DatoErroneoException e) {
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (SaldoInsuficienteException e) {
			
		}
	
	}
	
	@Test
	public void testIngresarSinConcepto () {
	
		// Test ingresar negativo
		try {
			sut.ingresar(null, -1);
			fail("Debería lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		}

		// Test ingresar el limite
		try {
			sut.ingresar(null, 0.01);
			assertEquals(Double.doubleToLongBits(0.01),Double.doubleToLongBits(sut.getSaldo()));
			assertEquals(1,sut.getMovimientos().size());
			assertEquals("Ingreso en efectivo",sut.getMovimientos().get(0).getConcepto());
			
			sut.ingresar(null, 100);
			assertEquals(Double.doubleToLongBits(100.01),Double.doubleToLongBits(sut.getSaldo()));
			assertEquals(2,sut.getMovimientos().size());
			
		} catch (DatoErroneoException e) {
			fail("No debería lanzar la excepción");
		}
		
	}

	
}
