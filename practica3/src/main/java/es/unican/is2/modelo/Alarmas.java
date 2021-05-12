package es.unican.is2.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.PriorityQueue;

@SuppressWarnings("deprecation")
public class Alarmas implements Comparator<Alarma>  {
	
	private AlarmaState state;
	private final static int INTERVALO_SONAR = 10000; // este intervalo se puede ir cambiando
	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private List<Alarma> alarmasDesactivadas = new ArrayList<Alarma>();

	private ArrayList<Observer> escuchadores = new ArrayList<Observer>();
	
	public Alarmas() {
		state = AlarmaState.init(this);
	}
	
	public Alarma alarma(String id) {
		for (Alarma a: alarmasActivas) {
			if (a.id().equals(id)) {
				return a;
			}
		}
		
		for (Alarma a: alarmasDesactivadas) {
			if (a.id().equals(id)) {
				return a;
			}
		}
		
		actualizarObservadores();
		
		return null;
	}
	
	public boolean anhadeAlarma(Alarma a) {
		boolean resul = false;
		if (alarma(a.id()) == null) {
			resul = alarmasActivas.add(a);
			actualizarObservadores();
			return resul;
		} else {
			return false;
		}
	}
	
	public boolean eliminaAlarma(Alarma a) {
		boolean resul = false;
		if (a.activa() == true) {
			resul = alarmasActivas.remove(a);
		} else {
			resul = alarmasDesactivadas.remove(a);
		}
		actualizarObservadores();
		return resul;
	}
	
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek();
	}
	
	public void desactivarAlarma(Alarma a) {
		if (a.activa()) {
			alarmasActivas.remove(a);
			alarmasDesactivadas.add(a);
			a.setActiva(false);
		}
		actualizarObservadores();
	}
	
	public void activarAlarma(Alarma a) {
		if (!a.activa()) {
			alarmasActivas.add(a);
			alarmasDesactivadas.remove(a);
			a.setActiva(true);
		}
		actualizarObservadores();
	}
	
	public PriorityQueue<Alarma> alarmasActivas() {
		return alarmasActivas;
	}
	
	public List<Alarma> alarmasDesactivadas() {
		return alarmasDesactivadas;
	}
	
	public void activarMelodia() {
		System.out.println("Melodia activada");
	}
	
	public void desactivarMelodia() {
		System.out.println("Melodia desactivada");
	}
	
	public void setState(AlarmaState value) {
		this.state = value;
	}
	
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
	}
	
	public void borraAlarma(String id) {
		state.borraAlarma(this, id);
	}

	public void apagar() {
		state.apagar(this);
	}
	
	public void alarmaOn(String id) {
		state.alarmaOn(this, id);
	}
	
	public void alarmaOff(String id) {
		state.alarmaOff(this, id);
	}
	
	public int intervaloSonar() {
		return INTERVALO_SONAR;
	}

	@Override
	public int compare(Alarma arg0, Alarma arg1) {
		return arg0.compareTo(arg1);
	}

	public void registrarObservador (Observer o) { 
		escuchadores.add(o);
		o.update(null, o);
	}
	
	public void eliminarObservador (Observer o) { 
		escuchadores.remove(o); 
	}
	
	public void actualizarObservadores () { 
		Iterator<Observer> i = escuchadores.iterator();
		while(i.hasNext()) {
			Observer o = (Observer) i.next();
			o.update(null, o);
		}
	}
}
