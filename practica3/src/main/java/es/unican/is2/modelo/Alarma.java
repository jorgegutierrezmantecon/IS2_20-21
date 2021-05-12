package es.unican.is2.modelo;

import java.util.Date;

public class Alarma implements Comparable<Alarma> {
	
	private String id;
	private Date fecha;
	private boolean activa;
	
	public Alarma(String id, Date hora) {
		this.id = id;
		this.fecha = hora;
		this.activa = true;
	}
	
	public String id() {
		return id;
	}
	
	public Date fecha() {
		return fecha;
	}
	
	public boolean activa() {
		return this.activa;
	}
	
	public void setActiva(boolean bool) {
		this.activa = bool;
	}

	@Override
	public int compareTo(Alarma a) {
		return this.fecha.compareTo(a.fecha());
	}
	
	@Override
	public String toString() {
		return id;
	}
	
}

