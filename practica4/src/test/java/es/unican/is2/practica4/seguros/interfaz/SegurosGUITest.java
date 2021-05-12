package es.unican.is2.practica4.seguros.interfaz;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.keystroke.KeyStrokeMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase FEST para testear la interfaz
 * @author Veronica Fernandez Gonzalez y Jorge Gutierrez Mantecon
 *
 */
public class SegurosGUITest {
	
	private FrameFixture demo;
	
	@Before
	public void setUp() {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	
	@After
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	public void testCaso0() { // test de formato
		// Comprobamos que la interfaz tiene el aspecto deseado
		demo.button("btnCalcular").requireText("CALCULAR");
		
		// comprobamos que el combobox tiene el numero de valores adecuados y que
		// siguen el formato del enum de Cobertura
		demo.comboBox().requireItemCount(3);
		demo.comboBox().requireSelection("TODO_RIESGO");
		demo.comboBox().selectItem("TODO_RIESGO");
		demo.comboBox().selectItem("TERCEROS_LUNAS");
		demo.comboBox().selectItem("TERCEROS");
	}

	@Test
	public void testCaso1() {
		// CASO 1: CASO VALIDO
		
		KeyStrokeMap.clearKeyStrokes();
		// escribimos una fecha
		demo.textBox("txtFechaUltimoSiniestro").setText("");
		demo.textBox("txtFechaUltimoSiniestro").enterText("13/11/2019");
		
		demo.comboBox().selectItem("TERCEROS_LUNAS");
		
		// Escribimos una potencia
		demo.textBox("txtPotencia").setText("");
		demo.textBox("txtPotencia").enterText("75");
		
		// va a ser un cliente minusvalido
		demo.radioButton("btnMinusvalia").click();
		
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("487.5");
	}
	
	@Test
	public void testCaso2() {
		// CASO 2: CASO NO VALIDO, METEMOS MAL LA FECHA
		demo.textBox("txtFechaUltimoSiniestro").setText("");
		demo.textBox("txtFechaUltimoSiniestro").enterText("1965/1/14");
		
		demo.comboBox().selectItem("TERCEROS");
		
		demo.textBox("txtPotencia").setText("");
		demo.textBox("txtPotencia").enterText("75");
		
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("La fecha no se pudo parsear");
	}
	
	@Test
	public void testCaso3() {
		// CASO 3: CASO NO VALIDO, METEMOS MAL LA FECHA
		demo.textBox("txtFechaUltimoSiniestro").setText("");
		demo.textBox("txtFechaUltimoSiniestro").enterText("13/11/2019");
		
		demo.comboBox().selectItem("TERCEROS");
		
		demo.textBox("txtPotencia").setText(""); // dejamos el campo vacio
		
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("Formato de potencia erróneo");
	}

}
