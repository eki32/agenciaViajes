package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.ImageIcon;

/**
 * Clase que representa un panel para la elección de eventos, incluyendo campos
 * para el nombre del evento y tipo de evento. Proporciona métodos para acceder
 * al panel y configurar su comportamiento.
 *
 * @author [Equipo 4]
 */
public class PanelEleccionEvento {

	private JPanel panelEleccionEvento;
	private JTextField tfNombreEvento;
	public JLabel lblLogo;
	private Controlador controlador = new Controlador();
	private ArrayList<JPanel> paneles;
	private int idAgencia;
	private int idViaje;

	/**
	 * Establece el identificador de la agencia y el viaje, y carga el color y logo
	 * de la agencia en el panel.
	 *
	 * @param idAgencia Identificador de la agencia.
	 * @param idViaje   Identificador del viaje.
	 */
	public void setIdAgencia(int idAgencia, int idViaje) {

		this.idViaje = idViaje; // Guarda el idViaje correctamente
		this.idAgencia = idAgencia;
		cargarColorPanel(idAgencia);
		cargarLogo(idAgencia);

	}

	/**
	 * Constructor que crea el panel de elección de eventos y configura su
	 * apariencia. Incluye campos para el nombre del evento y tipo de evento, así
	 * como botones para guardar y cancelar.
	 *
	 * @param paneles   Lista de paneles que se pueden mostrar u ocultar.
	 * @param idAgencia Identificador de la agencia.
	 * @param idViaje   Identificador del viaje.
	 */
	public PanelEleccionEvento(ArrayList<JPanel> paneles, int idAgencia, int idViaje) {

		this.paneles = paneles;
		this.idAgencia = idAgencia;
		this.idViaje = idViaje;

		panelEleccionEvento = new JPanel();
		panelEleccionEvento.setBackground(Color.LIGHT_GRAY);
		panelEleccionEvento.setBounds(0, 0, 1039, 666);
		panelEleccionEvento.setLayout(null);

		JLabel lbNombreEvento = new JLabel("NOMBRE EVENTO");
		lbNombreEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lbNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbNombreEvento.setBounds(148, 78, 169, 17);
		panelEleccionEvento.add(lbNombreEvento);

		lblLogo = new JLabel("Cargando logo...");
		lblLogo.setBounds(809, 0, 230, 128);
		panelEleccionEvento.add(lblLogo);

		tfNombreEvento = new JTextField();
		tfNombreEvento.setEditable(false);
		tfNombreEvento.setText("----------------------");
		tfNombreEvento.setEnabled(false);
		tfNombreEvento.setBounds(398, 75, 153, 26);
		panelEleccionEvento.add(tfNombreEvento);
		tfNombreEvento.setColumns(10);

		JLabel lblTipoEvento = new JLabel("TIPO EVENTO");
		lblTipoEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoEvento.setBounds(148, 119, 153, 17);
		panelEleccionEvento.add(lblTipoEvento);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String seleccion = (String) comboBox.getSelectedItem();
				if (seleccion.equals("VUELO")) {
					paneles.get(7).setVisible(false);
					paneles.get(5).setVisible(true);
				}
				if (seleccion.equals("ALOJAMIENTO")) {
					paneles.get(7).setVisible(false);
					paneles.get(4).setVisible(true);

				}

				if (seleccion.equals("ACTIVIDAD")) {
					paneles.get(7).setVisible(false);
					paneles.get(8).setVisible(true);
				}
			}
		});

		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Seleccionar...", "VUELO", "ALOJAMIENTO", "ACTIVIDAD" }));
		comboBox.setBounds(400, 116, 151, 27);
		panelEleccionEvento.add(comboBox);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(7).setVisible(false);
				paneles.get(2).setVisible(true);
			}
		});
		btnGuardar.setBounds(275, 568, 117, 29);
		panelEleccionEvento.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(7).setVisible(false);
				paneles.get(2).setVisible(true);
			}
		});
		btnCancelar.setBounds(538, 568, 117, 29);
		panelEleccionEvento.add(btnCancelar);

	}

	/**
	 * Carga el logo de la agencia en el panel. Busca el logo asociado al
	 * identificador de la agencia y lo muestra en la etiqueta correspondiente.
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
	 * Cambia el color del panel según la configuración de la agencia. Busca el
	 * color asociado al identificador de la agencia y lo aplica al panel.
	 *
	 * @param idAgencia Identificador de la agencia.
	 */
	private void cargarColorPanel(int idAgencia) {
		ArrayList<Agencia> agencias = controlador.buscarAgenciasPorId();

		for (Agencia agenciaLogueada : agencias) {
			if (idAgencia == agenciaLogueada.getId_agencia()) {
				String colorHex = agenciaLogueada.getColor();
				if (colorHex != null) {
					panelEleccionEvento.setBackground(Color.decode(colorHex));
				} else {
					panelEleccionEvento.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	/**
	 * Obtiene el panel de elección de eventos.
	 *
	 * @return El panel de elección de eventos.
	 */
	public JPanel getPanel() {

		return panelEleccionEvento;
	}

}