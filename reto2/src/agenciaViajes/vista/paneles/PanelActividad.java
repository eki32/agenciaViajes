package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import agenciaViajes.bbdd.controlador.Controlador;
import agenciaViajes.bbdd.entidad.Agencia;
import agenciaViajes.bbdd.entidad.Evento;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import javax.swing.JTextArea;

/**
 * Clase que representa un panel para la gestión de actividades dentro de un
 * viaje. Permite registrar el nombre del evento, su descripción, la fecha y el
 * precio.
 */
public class PanelActividad {

	private JPanel panelActividad;
	private JTextField tfNombreEvento;
	private JLabel lbTipo;
	private JTextField tfPrecio;
	private int idAgencia;
	private int idViaje;
	private JLabel lblLogo;
	private JComboBox comboBoxTipo;
	private JTextArea textAreaDescrip;
	private Date fechaInicio;
	private ArrayList<JPanel> paneles;
	private JDatePickerImpl datePickerActividad;
	private Controlador controlador = new Controlador();
	private GregorianCalendar calendarInicio = new GregorianCalendar();

	/**
	 * Establece el identificador de la agencia y del viaje, cargando la
	 * configuración correspondiente.
	 * 
	 * @param idAgencia Identificador de la agencia.
	 * @param idViaje   Identificador del viaje.
	 */
	public void setIdAgencia(int idAgencia, int idViaje) {

		this.idViaje = idViaje;
		this.idAgencia = idAgencia;
		cargarColorPanel(idAgencia);
		cargarLogo(idAgencia);

	}

	/**
	 * Establece el identificador del viaje en el panel de actividad y muestra un
	 * mensaje de confirmación.
	 *
	 * @param idViaje Identificador del viaje a cargar.
	 */
	private void cargarIdViaje(int idViaje) {
		this.idViaje = idViaje;
		System.out.println("ID de Viaje cargado correctamente en PanelActividad: " + idViaje);
	}

	/**
	 * Constructor de la clase PanelActividad. Crea un panel donde los usuarios
	 * pueden ingresar los detalles de una actividad.
	 *
	 * @param paneles   Lista de paneles para gestionar la navegación.
	 * @param idAgencia ID de la agencia de viajes.
	 * @param idViaje   ID del viaje asociado.
	 */
	public PanelActividad(ArrayList<JPanel> paneles, int idAgencia, int idViaje) {

		this.idViaje = idViaje;
		this.paneles = paneles;
		this.idAgencia = idAgencia;
		panelActividad = new JPanel();
		panelActividad.setBackground(Color.LIGHT_GRAY);
		panelActividad.setBounds(0, 0, 1039, 666);
		panelActividad.setLayout(null);

		JLabel lbNombreEvento = new JLabel("NOMBRE EVENTO");
		lbNombreEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lbNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbNombreEvento.setBounds(148, 78, 110, 17);
		panelActividad.add(lbNombreEvento);

		tfNombreEvento = new JTextField();
		tfNombreEvento.setBounds(309, 74, 130, 26);
		panelActividad.add(tfNombreEvento);
		tfNombreEvento.setColumns(10);

		lblLogo = new JLabel("Cargando logo...");
		lblLogo.setBounds(809, 0, 230, 128);
		panelActividad.add(lblLogo);

		lbTipo = new JLabel("TIPO EVENTO");
		lbTipo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbTipo.setBounds(148, 123, 110, 20);
		panelActividad.add(lbTipo);

		comboBoxTipo = new JComboBox();
		comboBoxTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxTipo.setEnabled(false);
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String) comboBoxTipo.getSelectedItem();

				if (seleccion.equals("VUELO")) {
					paneles.get(8).setVisible(false);
					paneles.get(5).setVisible(true);
				}
				if (seleccion.equals("ALOJAMIENTO")) {
					paneles.get(8).setVisible(false);
					paneles.get(4).setVisible(true);

				}

				if (seleccion.equals("ACTIVIDAD")) {
					paneles.get(8).setVisible(false);
					paneles.get(8).setVisible(true);
				}
			}
		});

		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "ACTIVIDAD" }));
		comboBoxTipo.setBounds(309, 116, 151, 27);
		panelActividad.add(comboBoxTipo);

		textAreaDescrip = new JTextArea();
		textAreaDescrip.setEditable(true);
		textAreaDescrip.setBounds(309, 187, 241, 132);
		panelActividad.add(textAreaDescrip);

		JLabel lbDesc = new JLabel("DESCRIPCION");
		lbDesc.setHorizontalAlignment(SwingConstants.LEFT);
		lbDesc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbDesc.setBounds(148, 299, 110, 20);
		panelActividad.add(lbDesc);

		JLabel lbPrecioAct = new JLabel("PRECIO");
		lbPrecioAct.setHorizontalAlignment(SwingConstants.LEFT);
		lbPrecioAct.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbPrecioAct.setBounds(148, 373, 110, 20);
		panelActividad.add(lbPrecioAct);

		datePickerActividad = new JDatePickerImpl(new JDatePanelImpl(null));
		datePickerActividad.setBounds(309, 447, 153, 30);
		panelActividad.add(datePickerActividad);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					guardarDatosActividad(idViaje);
					paneles.get(8).setVisible(false);
					paneles.get(2).setVisible(true);
					resetearCampos();
				}
			}

		});
		btnGuardar.setBounds(275, 568, 117, 29);
		panelActividad.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(8).setVisible(false);
				paneles.get(2).setVisible(true);
				resetearCampos();
			}
		});
		btnCancelar.setBounds(538, 568, 117, 29);
		panelActividad.add(btnCancelar);

		tfPrecio = new JTextField();
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(309, 371, 130, 26);
		panelActividad.add(tfPrecio);

		JLabel lbFecActividad = new JLabel("FECHA ACTIVIDAD");
		lbFecActividad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbFecActividad.setBounds(148, 447, 130, 16);
		panelActividad.add(lbFecActividad);

	}

	/**
	 * Guarda los datos de la actividad en la base de datos. Este método recopila la
	 * información del formulario, crea un objeto Evento y lo envía al controlador
	 * para su persistencia. Muestra mensajes de confirmación o error según el
	 * resultado de la operación.
	 *
	 * @param idViaje Identificador del viaje al que pertenece la actividad.
	 */
	private void guardarDatosActividad(int idViaje) {
		String nombreEvento = tfNombreEvento.getText();
		String tipoEventoSeleccionado = (String) comboBoxTipo.getSelectedItem();
		String descripionActividad = textAreaDescrip.getText();

		double precio = 0;
		String precioTexto = tfPrecio.getText();
		try {
			precio = Double.parseDouble(precioTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panelActividad, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
			tfPrecio.requestFocus();
		}

		calendarInicio = (GregorianCalendar) datePickerActividad.getModel().getValue();
		if (calendarInicio == null) {
			JOptionPane.showMessageDialog(panelActividad, "Seleccione una fecha válida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		fechaInicio = new Date(calendarInicio.getTimeInMillis());

		Evento evento = new Evento();
		evento.setNom_evento(nombreEvento);
		evento.setTipo(tipoEventoSeleccionado);
		evento.setDesc_act(descripionActividad);
		evento.setPrecio(precio);
		evento.setFec_ida(fechaInicio);

		evento.setId_viaje(this.idViaje);

		try {
			controlador.guardarEventoActividad(evento);
			JOptionPane.showMessageDialog(panelActividad, "Datos guardados correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(panelActividad, "Error al guardar los datos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Cambia el color del panel según la configuración de la agencia.
	 * 
	 * @param idAgencia Identificador de la agencia.
	 */
	public void cargarColorPanel(int idAgencia) {

		ArrayList<Agencia> agencias = controlador.buscarAgenciasPorId();

		for (Agencia agenciaLogueada : agencias) {
			if (idAgencia == agenciaLogueada.getId_agencia()) {
				String colorHex = agenciaLogueada.getColor();
				if (colorHex != null) {
					panelActividad.setBackground(Color.decode(colorHex));
				} else {
					panelActividad.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	/**
	 * Carga el logo de la agencia en el panel. Busca el logo asociado al
	 * identificador de la agencia y lo muestra en el componente de etiqueta.
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
	 * Valida los campos del formulario de actividad para asegurarse de que todos
	 * los datos requeridos sean correctos. Muestra mensajes de error si algún campo
	 * no cumple con los requisitos.
	 *
	 * @return `true` si todos los campos son válidos, `false` en caso contrario.
	 */
	private boolean validarCampos() {
		// Validar que el nombre del evento no esté vacío
		if (tfNombreEvento.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelActividad, "El nombre del evento no puede estar vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfNombreEvento.requestFocus();
			return false;
		}

		// Validar que el tipo de evento esté seleccionado
		if (comboBoxTipo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelActividad, "Debe seleccionar un tipo de evento.", "Error",
					JOptionPane.ERROR_MESSAGE);
			comboBoxTipo.requestFocus();
			return false;
		}

		// Validar que la descripción de la actividad no esté vacía
		if (textAreaDescrip.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelActividad, "Debe ingresar una descripción para la actividad.", "Error",
					JOptionPane.ERROR_MESSAGE);
			textAreaDescrip.requestFocus();
			return false;
		}

		// Validar que el precio sea un número válido y no negativo
		try {
			double precio = Double.parseDouble(tfPrecio.getText().trim());
			if (precio < 0) {
				JOptionPane.showMessageDialog(panelActividad, "El precio no puede ser negativo.", "Error",
						JOptionPane.ERROR_MESSAGE);
				tfPrecio.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(panelActividad, "Precio inválido. Debe ser un número decimal.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfPrecio.requestFocus();
			return false;
		}

		// Validar que la fecha de la actividad esté seleccionada
		calendarInicio = (GregorianCalendar) datePickerActividad.getModel().getValue();
		if (calendarInicio == null) {
			JOptionPane.showMessageDialog(panelActividad, "Seleccione una fecha válida para la actividad.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		fechaInicio = new Date(calendarInicio.getTimeInMillis());

		return true; // Si todas las validaciones pasan
	}

	private void resetearCampos() {

		tfNombreEvento.setText("");
		textAreaDescrip.setText("");
		tfPrecio.setText("");
		datePickerActividad.getModel().setValue(null);
	}

	/**
	 * Obtiene el panel de actividad.
	 *
	 * @return El panel de actividad.
	 */
	public JPanel getPanel() {

		return panelActividad;
	}
}
