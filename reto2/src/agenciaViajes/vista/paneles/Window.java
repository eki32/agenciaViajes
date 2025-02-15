package agenciaViajes.vista.paneles;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Clase que representa la ventana principal de la aplicación. Gestiona la
 * visualización de diferentes paneles según la navegación del usuario.
 */
public class Window {

	public JFrame frame;
	public ArrayList<JPanel> paneles;
	public PanelViajesEventos panelViajesEventos;
	public PanelNuevoViaje panelNuevoViaje;
	public PanelEleccionIda panelEleccionIda;
	public PanelIdaVuelta panelIdaVuelta;
	public PanelEleccionEvento panelEleccionEvento;
	public PanelEleccionTrayecto panelEleccionTrayecto;
	public PanelHabitacionTipo panelHabitacionTipo;
	public PanelActividad panelActividad;

	/**
	 * Constructor de la clase Window.
	 */
	public Window() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1039, 666);
		frame.setLocationRelativeTo(null);// pone la ventana en el centro de la pantalla

		paneles = new ArrayList<>();

		// Creamos los gestores y los paneles. Y pasamos la lista por el constructor.
		PanelBienvenida gestorBienvenida = new PanelBienvenida(paneles);
		JPanel panel1 = gestorBienvenida.getPanel();
		paneles.add(panel1); // 0

		PanelLogin gestorLogin = new PanelLogin(this);
		JPanel panel2 = gestorLogin.getPanel();
		paneles.add(panel2); // 1

		panelViajesEventos = new PanelViajesEventos(paneles, -1, this);
		JPanel panel3 = panelViajesEventos.getPanel();
		paneles.add(panel3); // 2

		panelIdaVuelta = new PanelIdaVuelta(paneles, -1,-1);
		JPanel panel4 = panelIdaVuelta.getPanel();
		paneles.add(panel4); // 3

		panelHabitacionTipo = new PanelHabitacionTipo(paneles, -1, -1);
		JPanel panel5 = panelHabitacionTipo.getPanel();
		paneles.add(panel5); // 4

		panelEleccionIda = new PanelEleccionIda(paneles, -1, -1,this);
		JPanel panel6 = panelEleccionIda.getPanel();
		paneles.add(panel6); // 5

		panelEleccionTrayecto = new PanelEleccionTrayecto(paneles, -1, -1);
		JPanel panel7 = panelEleccionTrayecto.getPanel();
		paneles.add(panel7);

		panelEleccionEvento = new PanelEleccionEvento(paneles, -1, -1);
		JPanel panel8 = panelEleccionEvento.getPanel();
		paneles.add(panel8);

		panelActividad = new PanelActividad(paneles, -1, -1);
		JPanel panel9 = panelActividad.getPanel();
		paneles.add(panel9);

		PanelNuevoPerfil gestorNuevoPerfil = new PanelNuevoPerfil(paneles);
		JPanel panel10 = gestorNuevoPerfil.getPanel();
		paneles.add(panel10);

		panelNuevoViaje = new PanelNuevoViaje(paneles, -1, -1, this);
		JPanel panel11 = panelNuevoViaje.getPanel();
		paneles.add(panel11);

		frame.add(paneles.get(0)).setVisible(true);// bienvenida
		frame.add(paneles.get(1)).setVisible(false);// login
		frame.add(paneles.get(2)).setVisible(false);// viajesEventos
		frame.add(paneles.get(3)).setVisible(false);// idaVuelta
		frame.add(paneles.get(4)).setVisible(false);// habitacionTipo
		frame.add(paneles.get(5)).setVisible(false);// ida
		frame.add(paneles.get(6)).setVisible(false);// eleccionTrayecto
		frame.add(paneles.get(7)).setVisible(false);// tipoEvento
		frame.add(paneles.get(8)).setVisible(false);// actividad
		frame.add(paneles.get(9)).setVisible(false);// nuevoPerfil
		frame.add(paneles.get(10)).setVisible(false);// nuevoViaje
	}
}