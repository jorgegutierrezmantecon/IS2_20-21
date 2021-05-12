package es.unican.is2.modelo;

import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends AlarmaState {
	
	protected Timer timer;
	protected AlarmaSonarTask alarmaIntervaloTask;
	
	public void entryAction(Alarmas context) {
		timer = new Timer();
		alarmaIntervaloTask = new AlarmaSonarTask(context);
		context.activarMelodia();
		timer.schedule(alarmaIntervaloTask, context.intervaloSonar());
	}
	
	public void exitAction(Alarmas context) {
		context.desactivarMelodia();
		context.eliminaAlarma(context.alarmaMasProxima());
		if (context.alarmasActivas().size() <= 0) {
			context.setState(getEstadoDesprogramado());
			getEstadoDesprogramado().entryAction(context);
			getEstadoDesprogramado().doAction(context);
		} else {
			context.setState(getEstadoProgramado());
			getEstadoProgramado().entryAction(context);
			getEstadoProgramado().doAction(context);
		}
	}
	
	public void apagar(Alarmas context) {
		timer.cancel();
		this.exitAction(context);
	}
	
	public class AlarmaSonarTask extends TimerTask {
		private Alarmas context;
		public AlarmaSonarTask (Alarmas context) {
			this.context = context;
		}
		
		public void run() {
			getEstadoSonando().exitAction(context);
		}
	}

}
