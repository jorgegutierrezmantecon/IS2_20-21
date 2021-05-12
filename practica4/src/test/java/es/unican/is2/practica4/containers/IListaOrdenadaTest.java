package es.unican.is2.practica4.containers;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Clase de tests para la clase ListaOrdenada
 * @author Veronica Fernandez Gonzalez y Jorge Gutierrez Mantecon
 *
 */
public class IListaOrdenadaTest {
	
	// creamos la lista para los tests
	ListaOrdenada<String> lista = new ListaOrdenada<String>();

	@Test
	public void testAdd() {
		
		// CASOS VALIDOS
		lista.add("A");
		assertTrue(lista.size() == 1);
		
		lista.add("C"); // con esta llamada aseguramos la cobertura con el resto de llamadas
		lista.add("B");
		assertTrue(lista.size() == 3);
		assertTrue(lista.get(1).equals("B"));
		assertTrue(lista.get(2).equals("C"));
		
		lista.add("D");
		lista.add("E");
		lista.add("F");
		assertTrue(lista.size() == 6);
		assertTrue(lista.get(5).equals("F"));
		
		lista = new ListaOrdenada<String>();
		
		// CASOS NO VALIDOS
		try {
			lista.add(null);
			fail("Se ha introducido un valor null en lista vacía y no se ha lanzado la excepción");
		} catch (NullPointerException e) {
			// Debe lanzarse la excepción
		}
		
		lista.add("A");
		lista.add("B");
		lista.add("C");
		try {
			lista.add(null);
			fail("Se ha introducido un valor null en lista no vacía y no se ha lanzado la excepción");
		} catch (NullPointerException e) {
			// Debe lanzarse la excepción
		}
	}
	
	@Test
	public void testGet() {
		// CASOS VALIDOS
		lista = new ListaOrdenada<String>();
		lista.add("A");
		lista.add("B");
		lista.add("C");
		lista.add("D");
		lista.add("E");
		
		assertTrue(lista.get(0).equals("A"));
		
		assertTrue(lista.get(2).equals("C"));
		
		assertTrue(lista.get(4).equals("E"));
		
		// CASOS NO VALIDOS
		try {
			lista.get(-5);
			fail("Se ha introducido un indice no correcto y no se ha lanzado la excepción");
		} catch (IndexOutOfBoundsException e) {
			// Debe lanzarse la excepción
		}
		
		try {
			lista.get(15);
			fail("Se ha introducido un indice no correcto y no se ha lanzado la excepción");
		} catch (IndexOutOfBoundsException e) {
			// Debe lanzarse la excepción
		}
	}
	
	@Test
	public void testRemove() {
		lista = new ListaOrdenada<String>();
		lista.add("A");
		lista.add("B");
		lista.add("C");
		lista.add("D");
		lista.add("E");
		
		String elemento = lista.remove(0);
		assertTrue(elemento.equals("A"));
		assertTrue(lista.size() == 4);
		
		lista.add("A");
		elemento = lista.remove(2);
		assertTrue(elemento.equals("C"));
		assertTrue(lista.size() == 4);
		
		lista.add("C");
		elemento = lista.remove(4);
		assertTrue(elemento.equals("E"));
		assertTrue(lista.size() == 4);
	}
	
	@Test
	public void testSize() {

		lista = new ListaOrdenada<String>();
		
	    //casos validos
	    assertTrue(lista.size()==0);

	    lista.add("A");
	    assertTrue(lista.size()==1);

	    lista.add("B");
	    lista.add("C");
	    lista.add("D");
	    lista.add("E");
	    assertTrue(lista.size()==5);

	}
	
	@Test
	public void testClear() {

		lista = new ListaOrdenada<String>();
		
	    //casos validos
	    lista.clear();
	    assertTrue(lista.size()==0);

	    lista.add("A");
	    lista.clear();
	    assertTrue(lista.size()==0);

	    lista.add("A");
	    lista.add("B");
	    lista.add("C");
	    lista.add("D");
	    lista.add("E");
	    lista.clear();
	    assertTrue(lista.size()==0);

	}

}
