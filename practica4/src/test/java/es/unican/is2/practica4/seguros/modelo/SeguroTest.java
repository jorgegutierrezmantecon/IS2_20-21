package es.unican.is2.practica4.seguros.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import es.unican.is2.practica4.seguros.modelo.Seguro.Cobertura;

/**
 * Clases para tests de la clase Seguro
 * @author Veronica Fernandez Gonzalez y Jorge Gutierrez Mantecon
 *
 */
public class SeguroTest {

	@Test
	public void testCrearSeguro() {
		Cliente c1 = new Cliente("Jose", "76676767K", true);
		Seguro s;
		
		s = new Seguro(1,Cobertura.TODO_RIESGO,c1);
		assertTrue("La potencia no es igual a la esperada", s.getPotenciaCV() == 1);
		assertTrue("La cobertura no es igual a la esperada", s.getCobertura() == Cobertura.TODO_RIESGO);
		assertTrue("El cliente es igual al esperado", c1.getDNI() == "76676767K");
		
		s = new Seguro(45,Cobertura.TERCEROS,c1);
		assertTrue("La potencia no es igual a la esperada", s.getPotenciaCV() == 45);
		assertTrue("La cobertura no es igual a la esperada", s.getCobertura() == Cobertura.TERCEROS);
		
		s = new Seguro(45,Cobertura.TERCEROS_LUNAS,c1);
		assertTrue("La cobertura no es igual a la esperada", s.getCobertura() == Cobertura.TERCEROS_LUNAS);
		
		try {
			s = new Seguro(-150,Cobertura.TODO_RIESGO,c1);
			fail("Se ha introducido una potencia no válida y no se ha lanzado la excepción");
		} catch(Seguro.DatoIncorrectoException e) {
			// Debe lanzarse la excepción
		}
		
		try {
			s = new Seguro(1,null,c1);
			fail("Se ha introducido una cobertura no válida y no se ha lanzado la excepción");
		} catch(Seguro.DatoIncorrectoException e) {
			// Debe lanzarse la excepción
		}
		
		try {
			s = new Seguro(1,Cobertura.TODO_RIESGO,null);
			fail("Se ha introducido un cliente no válido y no se ha lanzado la excepción");
		} catch(Seguro.DatoIncorrectoException e) {
			// Debe lanzarse la excepción
		}
	}
	
	@Test
	public void testPrecio() {
		
		Cliente c1 = new Cliente("Jose", "76676767K", true);
		Cliente c2 = new Cliente("Manuel", "78676767H", false);
		Seguro s;
		double precioEsperado;
		
		s = new Seguro(1,Cobertura.TODO_RIESGO,c1);
		s.setFechaUltimoSiniestro(LocalDate.now().minusDays(1));
		precioEsperado = (1000 + 200);
		precioEsperado = precioEsperado - precioEsperado*(25.0/100.0);
		assertTrue("El precio retornado no es el esperado",s.precio()==precioEsperado);
		
		s = new Seguro(45,Cobertura.TERCEROS,c1);
		s.setFechaUltimoSiniestro(LocalDate.now().minusDays(100));
		precioEsperado = (400 + 200);
		precioEsperado = precioEsperado - precioEsperado*(25.0/100.0);
		assertTrue("El precio retornado no es el esperado",s.precio()==precioEsperado);
		
		s = new Seguro(89,Cobertura.TERCEROS_LUNAS,c2);
		s.setFechaUltimoSiniestro(LocalDate.now().minusDays(365));
		precioEsperado = (600 + 200);
		assertTrue("El precio retornado no es el esperado",s.precio()==precioEsperado);
		
		s = new Seguro(90,Cobertura.TERCEROS_LUNAS,c2);
		s.setFechaUltimoSiniestro(LocalDate.now().minusDays(367));
		precioEsperado = ((600 + 600*(5.0/100.0)) + 50);
		assertTrue("El precio retornado no es el esperado",s.precio()==precioEsperado);
		
		s = new Seguro(100,Cobertura.TERCEROS_LUNAS,c2);
		s.setFechaUltimoSiniestro(LocalDate.now().minusDays(365*2));
		precioEsperado = ((600 + 600*(5.0/100.0)) + 50);
		assertTrue("El precio retornado no es el esperado",s.precio()==precioEsperado);
		
		s = new Seguro(110,Cobertura.TERCEROS_LUNAS,c2);
		s.setFechaUltimoSiniestro(LocalDate.now().minusDays(365*3));
		precioEsperado = ((600 + 600*(5.0/100.0)) + 50);
		assertTrue("El precio retornado no es el esperado",s.precio()==precioEsperado);
		
		s = new Seguro(111,Cobertura.TERCEROS_LUNAS,c2);
		s.setFechaUltimoSiniestro(LocalDate.now().minusDays(1));
		precioEsperado = 600 + 600*(20.0/100.0) + 200;
		assertTrue("El precio retornado no es el esperado",s.precio()==precioEsperado);
		
		s = new Seguro(250,Cobertura.TERCEROS_LUNAS,c2);
		s.setFechaUltimoSiniestro(LocalDate.now().minusDays(1));
		precioEsperado = 600 + 600*(20.0/100.0) + 200;
		assertTrue("El precio retornado no es el esperado",s.precio()==precioEsperado);
		
		@SuppressWarnings("unused")
		double precioTest;
		
		s = new Seguro(250,Cobertura.TERCEROS_LUNAS,c2);
		try {
			s.setFechaUltimoSiniestro(LocalDate.now().plusDays(1));
			precioTest = s.precio();
			fail("Se ha introducido una fecha no válida y no se ha lanzado la excepción");
		} catch (Seguro.DatoIncorrectoException e) {
			// Debe lanzarse la excepción
		}
	}

}
