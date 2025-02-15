package agenciaViajes;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import agenciaViajes.bbdd.controlador.Controlador;
import agenciaViajes.bbdd.entidad.Pais;
import agenciaViajes.vista.paneles.Window;

/**
 * Clase principal que lanza la aplicación de agencia de viajes.
 */
public class AgenciaViajesMain extends JFrame {

	private static final long serialVersionUID = 1L;

	
	/**
	   * Punto de entrada de la aplicación.
	   *
	   * @param args Argumentos de línea de comandos (no utilizados).
	   */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new Window().frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
