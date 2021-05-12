package es.unican.is2.vista;

import javax.swing.*;

import es.unican.is2.modelo.Alarmas;

import java.awt.event.MouseAdapter;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.awt.Font;

@SuppressWarnings("deprecation")
public class GUIAlarmas implements Observer {

	private JFrame alarmaFrame;
	private JTextField idAlarmaField;
	private JTextPane alarmasActivasTextPane;
	private JTextPane alarmasDesactivadasTextPane;
	private JTextField introduceIdTextField;
	private java.util.Date date = new java.util.Date();
	private JSpinner horaSpinner;
	Alarmas a = new Alarmas();
	JButton nuevaAlarmaButton;
	JButton apagarButton;
	JButton offButton;
	JButton onButton;
	JButton eliminarButton;

	/**
	 * Create the application.
	 */
	public GUIAlarmas(Alarmas a) {
		this.a = a;
		a.registrarObservador(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		alarmaFrame = new JFrame();
		alarmaFrame.setTitle("Alarmas");
		alarmaFrame.setBounds(100, 100, 451, 478);
		alarmaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		alarmaFrame.getContentPane().setLayout(null);
		
		JLabel idAlarmaLabel = new JLabel("Id Alarma");
		idAlarmaLabel.setBounds(24, 73, 71, 17);
		alarmaFrame.getContentPane().add(idAlarmaLabel);
		
		idAlarmaField = new JTextField();
		idAlarmaField.setBounds(105, 70, 86, 20);
		alarmaFrame.getContentPane().add(idAlarmaField);
		idAlarmaField.setColumns(10);
		
		JLabel horaLabel = new JLabel("Hora Alarma");
		horaLabel.setBounds(24, 113, 71, 17);
		alarmaFrame.getContentPane().add(horaLabel);
		
		horaSpinner = new JSpinner();
		horaSpinner.setModel(new SpinnerDateModel());
		horaSpinner.setEditor(new JSpinner.DateEditor(horaSpinner, "HH:mm"));
		horaSpinner.setBounds(105, 110, 86, 20);
		alarmaFrame.getContentPane().add(horaSpinner);
		
		nuevaAlarmaButton = new JButton("Nueva Alarma");
		/*nuevaAlarmaButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			
		});*/
		nuevaAlarmaButton.setBounds(24, 149, 167, 23);
		alarmaFrame.getContentPane().add(nuevaAlarmaButton);
		
		apagarButton = new JButton("\u00A1APAGAR!");
		/*apagarButton.addMouseListener(new MouseAdapter() {
			@Override
			
		});*/
		apagarButton.setBounds(24, 183, 167, 44);
		alarmaFrame.getContentPane().add(apagarButton);
		
		JLabel alarmasActivasLabel = new JLabel("Alarmas activas");
		alarmasActivasLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		alarmasActivasLabel.setBounds(284, 34, 95, 14);
		alarmaFrame.getContentPane().add(alarmasActivasLabel);
		
		JLabel alarmasDesactivadasLabel = new JLabel("Alarmas desactivadas");
		alarmasDesactivadasLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		alarmasDesactivadasLabel.setBounds(271, 189, 131, 14);
		alarmaFrame.getContentPane().add(alarmasDesactivadasLabel);
		
		JLabel idAlarma2Label = new JLabel("Id Alarma");
		idAlarma2Label.setBounds(24, 350, 71, 15);
		alarmaFrame.getContentPane().add(idAlarma2Label);
		
		introduceIdTextField = new JTextField();
		introduceIdTextField.setToolTipText("");
		introduceIdTextField.setBounds(87, 348, 315, 17);
		alarmaFrame.getContentPane().add(introduceIdTextField);
		introduceIdTextField.setColumns(10);
		
		offButton = new JButton("OFF");
		/*offButton.addMouseListener(new MouseAdapter() {
			@Override
			
		});*/
		offButton.setBounds(87, 377, 102, 20);
		alarmaFrame.getContentPane().add(offButton);
		
		onButton = new JButton("ON");
		/*onButton.addMouseListener(new MouseAdapter() {
			@Override
			
		});*/
		onButton.setBounds(192, 377, 104, 20);
		alarmaFrame.getContentPane().add(onButton);
		
		eliminarButton = new JButton("ELIMINAR");
		/*eliminarButton.addMouseListener(new MouseAdapter() {
			@Override
			
		});*/
		eliminarButton.setBounds(299, 377, 103, 20);
		alarmaFrame.getContentPane().add(eliminarButton);
		
		alarmasActivasTextPane = new JTextPane();
		alarmasActivasTextPane.setEditable(false);
		alarmasActivasTextPane.setBounds(268, 59, 134, 113);
		alarmaFrame.getContentPane().add(alarmasActivasTextPane);
		
		alarmasDesactivadasTextPane = new JTextPane();
		alarmasDesactivadasTextPane.setEditable(false);
		alarmasDesactivadasTextPane.setBounds(271, 214, 131, 113);
		alarmaFrame.getContentPane().add(alarmasDesactivadasTextPane);
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getIdAlarma() {
		return idAlarmaField.getText();
	}
	
	public String getIdAlarma2() {
		return introduceIdTextField.getText();
	}
	
	public Date getHora() {
		return (Date)horaSpinner.getValue();
	}
	
	public void actualizar() {
		alarmasActivasTextPane.setText(a.alarmasActivas().toString());
		alarmasDesactivadasTextPane.setText(a.alarmasDesactivadas().toString());
	}
	
	public JFrame getFrame() {
		return alarmaFrame;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setNuevaAlarma(MouseAdapter a) {
		nuevaAlarmaButton.addMouseListener(a);
	}
	
	public void setApagarAlarma(MouseAdapter a) {
		apagarButton.addMouseListener(a);
	}
	
	public void setOffAlarma(MouseAdapter a) {
		offButton.addMouseListener(a);
	}
	
	public void setOnAlarma(MouseAdapter a) {
		onButton.addMouseListener(a);
	}
	
	public void setEliminarAlarma(MouseAdapter a) {
		eliminarButton.addMouseListener(a);
	}
}
