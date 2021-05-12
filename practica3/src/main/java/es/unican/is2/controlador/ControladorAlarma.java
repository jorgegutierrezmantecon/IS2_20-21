package es.unican.is2.controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JOptionPane;

import es.unican.is2.modelo.Alarmas;
import es.unican.is2.vista.GUIAlarmas;

@SuppressWarnings("deprecation")
public class ControladorAlarma {
	
	Alarmas modelo;
	GUIAlarmas vista;
	
	public ControladorAlarma(Alarmas m, GUIAlarmas v) {
		
		modelo = m;
		vista = v;
		
		modelo.registrarObservador(vista);
		
		vista.setNuevaAlarma(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				int tam = modelo.alarmasActivas().size();
				String id = vista.getIdAlarma();
				Date hora = vista.getHora();
				Date date = vista.getDate();
				hora.setYear(date.getYear());
				hora.setMonth(date.getMonth());
				hora.setDate(date.getDate());
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No se ha introducido el ID");
				} else {
					modelo.nuevaAlarma(id,hora);
					if (modelo.alarmasActivas().size() > tam) {
						JOptionPane.showMessageDialog(null, "Alarma creada");
						vista.actualizar();
					}
				}
			}
		});
		
		vista.setApagarAlarma(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) {
				modelo.apagar();
				JOptionPane.showMessageDialog(null, "Alarma apagada");
				vista.actualizar();
			}
		});
		
		vista.setOffAlarma(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				String id = vista.getIdAlarma2();
				modelo.alarmaOff(id);
				vista.actualizar();
			}
		});
		
		vista.setOnAlarma(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				String id = vista.getIdAlarma2();
				modelo.alarmaOn(id);
				vista.actualizar();
			}
		});
		
		vista.setEliminarAlarma(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				int tam = modelo.alarmasActivas().size() + modelo.alarmasDesactivadas().size();
				String id = vista.getIdAlarma2();
				modelo.borraAlarma(id);
				if (modelo.alarmasActivas().size() + modelo.alarmasDesactivadas().size() < tam) {
					vista.actualizar();
					JOptionPane.showMessageDialog(null, "Alarma eliminada");
				}
			}
		});
	}

}
