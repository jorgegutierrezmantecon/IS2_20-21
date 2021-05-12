package es.unican.is2.practica4.containers;

import java.util.ArrayList;

/**
 * Clase que implementa una lista ordenada 
 * Los elementos de la lista deben implementar la interfaz Comparable<E>
 *
 * @author IS2, Veronica Fernandez Gonzalez y Jorge Gutierrez Mantecon 2020/2021
 */
public class ListaOrdenada<E extends Comparable<E>> implements IListaOrdenada<E> {

	private ArrayList<E> lista = new ArrayList<E>();


	public E get(int indice) {
		// retorna el elemento
		return lista.get(indice);
	}

	public void add(E elemento) {
		// busca el lugar donde debe insertarse
		int indice = 0;
		// comprobamos que el elemento no sea null
		if (lista.size() != 0 && elemento != null) {
			while (indice<lista.size()  && elemento.compareTo(lista.get(indice))>0) {
				indice++;
			}
		// si es null, que lance excepcion
		} else if (elemento == null) {
			throw new NullPointerException();
		}
		lista.add(indice, elemento);
	}


	public E remove(int indice) {
		// quitamos el -1
		E borrado = lista.remove(indice);
		return borrado;
	}


	public int size() {
		return lista.size();         
	}


	public void clear() {
		// llamamos a clear
		lista.clear();
	}
} 