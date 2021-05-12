package es.unican.is2.vista;

import es.unican.is2.controlador.ControladorAlarma;
import es.unican.is2.modelo.Alarmas;

/**
 * Clase Main desde donde se llamar� a la aplicaci�n MVC de Alarmas
 * @author Jorge Guti�rrez Mantec�n y Ver�nica Fern�ndez Gonz�lez
 *
 */
public class AlarmaMVC {
	
	@SuppressWarnings("unused")
	public static void main (String[] args) {
		Alarmas modelo = new Alarmas();
		GUIAlarmas vista = new GUIAlarmas(modelo);
		ControladorAlarma ctr = new ControladorAlarma(modelo, vista);
		vista.getFrame().setVisible(true);
	}

}
