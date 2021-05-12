package es.unican.is2.modelo;

import java.util.Date;

public class Desprogramado extends AlarmaState {
	
	public void alarmaOn(Alarmas context, String id) {
		Alarma a = context.alarma(id);
		context.activarAlarma(a);
	}
	
	public void nuevaAlarma(Alarmas context, String id, Date hora) {
		Alarma a = new Alarma(id, hora);
		this.exitAction(context);
		boolean resultado = context.anhadeAlarma(a);
		if (resultado) {
			context.setState(getEstadoProgramado());
			this.exitAction(context);
			getEstadoProgramado().entryAction(context);
			getEstadoProgramado().doAction(context);
		}
	}
	
	public void borraAlarma(Alarmas context, String id) {
		Alarma a = context.alarma(id);
		context.eliminaAlarma(a);
	}
	
}
