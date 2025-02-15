package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import agenciaViajes.bbdd.controlador.Controlador;
import agenciaViajes.bbdd.entidad.Agencia;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Clase que representa un panel para la elección del trayecto. Permite
 * configurar los detalles del trayecto y guardarlos.
 *
 * @author [Equipo 4]
 */
public class PanelEleccionTrayecto {

	private JPanel panelEleccionTrayecto;
	private JTextField tfNombreEvento;
	public JLabel lblLogo;
	private Controlador controlador = new Controlador();
	private ArrayList<JPanel> paneles;
	private int idAgencia;
	private int idViaje;

	/**
	 * Establece el identificador de la agencia, cargando la configuración
	 * correspondiente (color del panel y logo).
	 *
	 * @param idAgencia Identificador de la agencia.
	 */
	public void setIdAgencia(int idAgencia) {

		this.idAgencia = idAgencia;
		cargarColorPanel(idAgencia);
		cargarLogo(idAgencia);

	}

	/**
	 * Carga el identificador del viaje y muestra un mensaje de confirmación. This
	 * method currently does nothing. Implement logic to load or set the viaje ID if
	 * necessary.
	 *
	 * @param idViaje Identificador del viaje a cargar.
	 */
	private void cargarIdViaje(int idViaje) {
		System.out.println("ID de Viaje cargado correctamente en TRAYECTO: " + idViaje);
	}

	/**
	 * Constructor de la clase PanelEleccionTrayecto. Inicializa los componentes
	 * gráficos y configura la interfaz.
	 *
	 * @param paneles   Lista de paneles de la aplicación.
	 * @param idAgencia Identificador de la agencia.
	 */
	public PanelEleccionTrayecto(ArrayList<JPanel> paneles, int idAgencia, int idViaje) {

		this.paneles = paneles;
		this.idAgencia = idAgencia;
		this.idViaje= idViaje;
		panelEleccionTrayecto = new JPanel();
		panelEleccionTrayecto.setBackground(Color.LIGHT_GRAY);
		panelEleccionTrayecto.setBounds(0, 0, 1039, 666);
		panelEleccionTrayecto.setLayout(null);

		JLabel lbNombreEvento = new JLabel("NOMBRE EVENTO");
		lbNombreEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lbNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNombreEvento.setBounds(148, 78, 110, 17);
		panelEleccionTrayecto.add(lbNombreEvento);

		tfNombreEvento = new JTextField();
		tfNombreEvento.setBounds(309, 74, 130, 26);
		panelEleccionTrayecto.add(tfNombreEvento);
		tfNombreEvento.setColumns(10);

		lblLogo = new JLabel("Cargando logo...");
		lblLogo.setBounds(809, 0, 230, 128);
		panelEleccionTrayecto.add(lblLogo);

		JLabel lblTipoEvento = new JLabel("TIPO EVENTO");
		lblTipoEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoEvento.setBounds(148, 119, 86, 17);
		panelEleccionTrayecto.add(lblTipoEvento);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Seleccionar...", "VUELO", "ALOJAMIENTO", "ACTIVIDAD" }));
		comboBox.setBounds(309, 158, 151, 27);
		panelEleccionTrayecto.add(comboBox);

		JLabel lbTrayecto = new JLabel("TRAYECTO");
		lbTrayecto.setHorizontalAlignment(SwingConstants.CENTER);
		lbTrayecto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbTrayecto.setBounds(148, 161, 67, 17);
		panelEleccionTrayecto.add(lbTrayecto);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// guardarDatos();
				
				paneles.get(6).setVisible(false);
				paneles.get(2).setVisible(true);
			}
		});
		btnGuardar.setBounds(275, 568, 117, 29);
		panelEleccionTrayecto.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(6).setVisible(false);
				paneles.get(2).setVisible(true);
			}
		});
		btnCancelar.setBounds(538, 568, 117, 29);
		panelEleccionTrayecto.add(btnCancelar);

		JComboBox comboBoxIdaYVuelta = new JComboBox();
		comboBoxIdaYVuelta.setEnabled(false);
		comboBoxIdaYVuelta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String seleccion = (String) comboBoxIdaYVuelta.getSelectedItem();
				if (seleccion.equals("IDA")) {
					paneles.get(6).setVisible(false);
					paneles.get(6).setVisible(true);
				}
				if (seleccion.equals("IDA Y VUELTA")) {
					paneles.get(6).setVisible(false);
					paneles.get(3).setVisible(true);

				}

			}
		});

		comboBoxIdaYVuelta.setModel(new DefaultComboBoxModel(new String[] { "VUELO", "ALOJAMIENTO", "ACTIVIDAD" }));
		comboBoxIdaYVuelta.setBounds(309, 116, 151, 27);
		panelEleccionTrayecto.add(comboBoxIdaYVuelta);
	}

	/**
	 * Carga el logo de la agencia correspondiente en el panel.
	 *
	 * @param idAgencia Identificador de la agencia.
	 */
	private void cargarLogo(int idAgencia) {
		try {
			System.out.println("ID recibido en cargarLogo: " + idAgencia);
			ArrayList<Agencia> agencias = controlador.buscarAgenciasPorId();
			if (agencias == null || agencias.isEmpty()) {
				lblLogo.setText("No hay agencias registradas.");
				return;
			}

			for (Agencia agenciaLogueada : agencias) {
				if (idAgencia == agenciaLogueada.getId_agencia()) {
					System.out.println("Logo: " + agenciaLogueada.getLogo());
					URL url = new URL(agenciaLogueada.getLogo());
					Image imagen = ImageIO.read(url);
					ImageIcon icono = new ImageIcon(
							imagen.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
					lblLogo.setIcon(icono);
					lblLogo.setText("");
					return;
				}
			}

			lblLogo.setText("No se encontró el logo.");

		} catch (IOException e) {
			lblLogo.setText("Error al cargar el logo.");
		} catch (Exception e) {
			lblLogo.setText("Error inesperado.");
			e.printStackTrace();
		}
	}

	/**
	 * Cambia el color del panel según la configuración de la agencia.
	 *
	 * @param idAgencia Identificador de la agencia.
	 */
	private void cargarColorPanel(int idAgencia) {
		ArrayList<Agencia> agencias = controlador.buscarAgenciasPorId();

		for (Agencia agenciaLogueada : agencias) {
			if (idAgencia == agenciaLogueada.getId_agencia()) {
				String colorHex = agenciaLogueada.getColor();
				if (colorHex != null) {
					panelEleccionTrayecto.setBackground(Color.decode(colorHex));
				} else {
					panelEleccionTrayecto.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	/**
	 * Obtiene el panel para la elección del trayecto.
	 *
	 * @return El panel para la elección del trayecto.
	 */
	public JPanel getPanel() {

		return panelEleccionTrayecto;
	}
}