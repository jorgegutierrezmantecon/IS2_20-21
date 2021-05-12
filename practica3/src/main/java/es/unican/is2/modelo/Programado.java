package es.unican.is2.modelo;

import java.util.Date;
import java.util.Timer; 
import java.util.TimerTask;

public class Programado extends AlarmaState {
	
	protected Timer timer = new Timer();
	protected AlarmaSonarTask alarmaSonarTask;
	
	public void entryAction(Alarmas context) {
		alarmaSonarTask = new AlarmaSonarTask(context);
		timer.schedule(alarmaSonarTask, context.alarmaMasProxima().fecha());
	}

	public void alarmaOn(Alarmas context, String id) {
		Alarma a = context.alarma(id);
		context.activarAlarma(a);
	}
	
	public void alarmaOff(Alarmas context, String id) {
		Alarma a = context.alarma(id);
		context.desactivarAlarma(a);
		if (context.alarmasActivas().size() <= 0) {
			context.setState(getEstadoDesprogramado());
			this.exitAction(context);
			getEstadoDesprogramado().entryAction(context);
			getEstadoDesprogramado().doAction(context);
		}
	}
	
	public void borraAlarma(Alarmas context, String id) {
		Alarma a = context.alarma(id);
		context.eliminaAlarma(a);
		if (context.alarmasActivas().size() <= 0) {
			context.setState(getEstadoDesprogramado());
			this.exitAction(context);
			getEstadoDesprogramado().entryAction(context);
			getEstadoDesprogramado().doAction(context);
		}
	}
	
	public void nuevaAlarma(Alarmas context, String id, Date hora) {
		Alarma a = new Alarma(id, hora);
		context.anhadeAlarma(a);
	}
	
	public class AlarmaSonarTask extends TimerTask {
		private Alarmas context;
		public AlarmaSonarTask (Alarmas context) {
			this.context = context;
		}
		
		public void run() {
			context.setState(getEstadoSonando());
			getEstadoProgramado().exitAction(context);
			getEstadoSonando().entryAction(context);
			getEstadoSonando().doAction(context);
		}
	}
}
