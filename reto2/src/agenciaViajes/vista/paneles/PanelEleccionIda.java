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
import agenciaViajes.bbdd.entidad.Aerolinea;
import agenciaViajes.bbdd.entidad.Aeropuerto;
import agenciaViajes.bbdd.entidad.Agencia;
import agenciaViajes.bbdd.entidad.Evento;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

/**
 * Clase que representa el panel para la elección de un vuelo de ida. Permite
 * configurar los detalles del vuelo y guardarlos.
 *
 * @author [Equipo 4]
 */

public class PanelEleccionIda {

	private JPanel panelEleccionIda;
	private JTextField tfNombreEvento;
	private JTextField tfCodVuelo;
	private JTextField tfPrecio;
	private JTextField tfHorario;
	private JTextField tfDuracion;
	private JComboBox comboBoxTipo;
	private JComboBox comboBoxTrayecto;
	public JLabel lblLogo;
	private JDatePickerImpl datePickerVueloIda;
	private Date fechaInicio;
	private int idAgencia;
	private int idViaje;
	private Window ventana;
	private ArrayList<JPanel> paneles;
	private GregorianCalendar calendarInicio = new GregorianCalendar();
	private Controlador controlador = new Controlador();
	JComboBox<String> comboBoxOrigen = new JComboBox<>();
	JComboBox<String> comboBoxDestino = new JComboBox<>();
	JComboBox<String> comboBoxAerolinea = new JComboBox<>();

	/**
	 * Establece el identificador de la agencia y del viaje, cargando la
	 * configuración correspondiente.
	 * 
	 * @param idAgencia Identificador de la agencia.
	 * @param idViaje   Identificador del viaje.
	 */
	public void setIdAgencia(int idAgencia, int idViaje) {
		this.idAgencia = idAgencia;
		this.idViaje = idViaje; // Guarda el idViaje correctamente
		cargarColorPanel(idAgencia);
		cargarLogo(idAgencia);

	}

	/**
	 * Carga el identificador del viaje y muestra un mensaje de confirmación.
	 *
	 * @param idViaje Identificador del viaje a cargar.
	 */
	private void cargarIdViaje(int idViaje) {
		this.idViaje = idViaje;
		System.out.println("✅ ID de Viaje cargado correctamente en IDA: " + idViaje);
	}

	/**
	 * Constructor de la clase PanelEleccionIda. Inicializa los componentes gráficos
	 * y configura la interfaz.
	 * 
	 * @param paneles   Lista de paneles de la aplicación.
	 * @param idAgencia Identificador de la agencia de viajes.
	 * @param idViaje   Identificador del viaje.
	 */
	public PanelEleccionIda(ArrayList<JPanel> paneles, int idAgencia, int idViaje, Window ventana) {
		this.paneles = paneles;
		this.idAgencia = idAgencia;
		this.idViaje = idViaje;
		this.ventana=ventana;

		panelEleccionIda = new JPanel();
		panelEleccionIda.setBackground(Color.LIGHT_GRAY);
		panelEleccionIda.setBounds(0, 0, 1039, 666);
		panelEleccionIda.setLayout(null);

		JLabel lbNombreEvento = new JLabel("NOMBRE EVENTO");
		lbNombreEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lbNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbNombreEvento.setBounds(148, 78, 110, 17);
		panelEleccionIda.add(lbNombreEvento);

		tfNombreEvento = new JTextField();
		tfNombreEvento.setBounds(309, 74, 130, 26);
		panelEleccionIda.add(tfNombreEvento);
		tfNombreEvento.setColumns(10);

		lblLogo = new JLabel("Cargando logo...");
		lblLogo.setBounds(809, 0, 230, 128);
		panelEleccionIda.add(lblLogo);

		JLabel lblTipoEvento = new JLabel("TIPO EVENTO");
		lblTipoEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoEvento.setBounds(148, 119, 86, 17);
		panelEleccionIda.add(lblTipoEvento);

		comboBoxTipo = new JComboBox();
		comboBoxTipo.setEnabled(false);
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "VUELO", "ALOJAMIENTO", "ACTIVIDAD" }));
		comboBoxTipo.setBounds(309, 116, 151, 27);
		panelEleccionIda.add(comboBoxTipo);

		JLabel lbTrayecto = new JLabel("TRAYECTO");
		lbTrayecto.setHorizontalAlignment(SwingConstants.LEFT);
		lbTrayecto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbTrayecto.setBounds(148, 161, 67, 17);
		panelEleccionIda.add(lbTrayecto);

		comboBoxTrayecto = new JComboBox();
		comboBoxTrayecto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String seleccion = (String) comboBoxTrayecto.getSelectedItem();

				if (seleccion.equals("IDA Y VUELTA")) {

					PanelIdaVuelta panelIdaVuelta = new PanelIdaVuelta(paneles, idAgencia, idViaje);
					paneles.set(3, panelIdaVuelta.panelIdaVuelta);
					paneles.get(5).setVisible(false);
					paneles.get(3).setVisible(true);
				}

			}
		});

		comboBoxTrayecto.setModel(new DefaultComboBoxModel(new String[] { "IDA", "IDA Y VUELTA" }));
		comboBoxTrayecto.setBounds(309, 158, 151, 27);
		panelEleccionIda.add(comboBoxTrayecto);

		JLabel lbOrigen = new JLabel("AEROPUERTO ORIGEN");
		lbOrigen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbOrigen.setBounds(148, 206, 151, 16);
		panelEleccionIda.add(lbOrigen);

		JLabel lbDestino = new JLabel("AEROPUERTO DESTINO");
		lbDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbDestino.setBounds(148, 244, 151, 16);
		panelEleccionIda.add(lbDestino);

		datePickerVueloIda = new JDatePickerImpl(new JDatePanelImpl(null));
		datePickerVueloIda.setBounds(309, 274, 151, 30);
		panelEleccionIda.add(datePickerVueloIda);

		JLabel lbCodVuelo = new JLabel("CODIGO VUELO");
		lbCodVuelo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCodVuelo.setBounds(148, 320, 151, 16);
		panelEleccionIda.add(lbCodVuelo);

		JLabel lbAerolinea = new JLabel("AEROLINEA");
		lbAerolinea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbAerolinea.setBounds(148, 358, 151, 16);
		panelEleccionIda.add(lbAerolinea);

		JLabel lbPrecio = new JLabel("PRECIO");
		lbPrecio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbPrecio.setBounds(148, 396, 61, 16);
		panelEleccionIda.add(lbPrecio);

		JLabel lbHorario = new JLabel("HORARIO");
		lbHorario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbHorario.setBounds(148, 432, 61, 16);
		panelEleccionIda.add(lbHorario);

		JLabel lbDuracion = new JLabel("DURACION");
		lbDuracion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbDuracion.setBounds(148, 471, 97, 16);
		panelEleccionIda.add(lbDuracion);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					guardarDatosIda(idViaje);
					paneles.get(5).setVisible(false);
					paneles.get(2).setVisible(true);
					resetearCampos();
				}
			}
		});
		btnGuardar.setBounds(275, 568, 117, 29);
		panelEleccionIda.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(5).setVisible(false);
				paneles.get(2).setVisible(true);
				resetearCampos();
			}
		});
		btnCancelar.setBounds(538, 568, 117, 29);
		panelEleccionIda.add(btnCancelar);

		// Combobox aerolinea
		ArrayList<Aerolinea> listaAerolineas = controlador.buscarAerolinea();
		DefaultComboBoxModel<String> modeloAerolinea = new DefaultComboBoxModel<>();

		for (Aerolinea aerolinea : listaAerolineas) {
			modeloAerolinea.addElement(aerolinea.getNom_ae());
		}
		comboBoxAerolinea.setModel(modeloAerolinea);
		comboBoxAerolinea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxAerolinea.setBounds(309, 353, 130, 26);
		panelEleccionIda.add(comboBoxAerolinea);

		// ComboBoxAeropuerto origen
		ArrayList<Aeropuerto> listaAeropuertos = controlador.buscarAeropuertos();
		DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>();
		// Llenar el combobox con los nombres de los países
		for (Aeropuerto codAeropuerto : listaAeropuertos) {
			modeloCombo.addElement(codAeropuerto.getCod_aeropuerto() + " " + " " + codAeropuerto.getCiudad());
		}
		comboBoxOrigen.setModel(modeloCombo);
		comboBoxOrigen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxOrigen.setBounds(309, 204, 151, 22);
		panelEleccionIda.add(comboBoxOrigen);

		// ComboBox aeropuerto destino
		ArrayList<Aeropuerto> listaAeropuertosD = controlador.buscarAeropuertos();
		DefaultComboBoxModel<String> modeloCombo1 = new DefaultComboBoxModel<>();
		// Llenar el combobox con los nombres de los países
		for (Aeropuerto codAeropuerto : listaAeropuertos) {
			modeloCombo1.addElement(codAeropuerto.getCod_aeropuerto() + " " + " " + codAeropuerto.getCiudad());
		}
		comboBoxDestino.setModel(modeloCombo1);
		comboBoxDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxDestino.setBounds(309, 238, 151, 22);
		panelEleccionIda.add(comboBoxDestino);

		JButton btnBuscar = new JButton("BUSCAR VIAJE");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					try {
						Desktop.getDesktop().browse(new URI("https://www.skyscanner.es/"));
					} catch (IOException | URISyntaxException ex) {
						ex.printStackTrace();
					}
				}
				resetearCampos();

			}
		});
		btnBuscar.setBounds(482, 216, 117, 29);
		panelEleccionIda.add(btnBuscar);

		tfCodVuelo = new JTextField();
		tfCodVuelo.setEditable(true);
		tfCodVuelo.setBounds(309, 315, 130, 26);
		panelEleccionIda.add(tfCodVuelo);
		tfCodVuelo.setColumns(10);

		tfPrecio = new JTextField();
		tfPrecio.setEditable(true);
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(309, 391, 130, 26);
		panelEleccionIda.add(tfPrecio);

		tfHorario = new JTextField();
		tfHorario.setEditable(true);
		tfHorario.setColumns(10);
		tfHorario.setBounds(309, 427, 130, 26);
		panelEleccionIda.add(tfHorario);

		tfDuracion = new JTextField();
		tfDuracion.setEditable(true);
		tfDuracion.setColumns(10);
		tfDuracion.setBounds(309, 466, 130, 26);
		panelEleccionIda.add(tfDuracion);

		JLabel lbFecIdaVuelo = new JLabel("FECHA IDA");
		lbFecIdaVuelo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbFecIdaVuelo.setBounds(148, 287, 124, 16);
		panelEleccionIda.add(lbFecIdaVuelo);

	}

	/**
	 * Guarda los datos del vuelo de ida en la base de datos.
	 * 
	 * @param idViaje Identificador del viaje asociado al vuelo.
	 */
	private void guardarDatosIda(int idViaje) {
		System.out.println("El boton guardar datos IDA/VUELTA recibe idviaje " + this.idViaje);

		String nombreEvento = tfNombreEvento.getText();
		String tipoEventoSeleccionado = (String) comboBoxTipo.getSelectedItem();
		String trayecto = (String) comboBoxTrayecto.getSelectedItem();
		String aeropuertoOrigen = (String) comboBoxOrigen.getSelectedItem();
		String aeropuertoDestino = (String) comboBoxDestino.getSelectedItem();
		calendarInicio = (GregorianCalendar) datePickerVueloIda.getModel().getValue();
		if (calendarInicio == null) {
			JOptionPane.showMessageDialog(panelEleccionIda, "Seleccione una fecha válida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		fechaInicio = new Date(calendarInicio.getTimeInMillis());

		String codigoVueloTexto = tfCodVuelo.getText();
		int codigoVuelo = 0;

		try {
			codigoVuelo = Integer.parseInt(codigoVueloTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panelEleccionIda, "Código de vuelo inválido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfCodVuelo.requestFocus();
		}

		String aerolinea = (String) comboBoxAerolinea.getSelectedItem();
		double precio = 0;
		String precioTexto = tfPrecio.getText();
		try {
			precio = Double.parseDouble(precioTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panelEleccionIda, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
			tfPrecio.requestFocus();
		}
		String horarioIdaString = tfHorario.getText(); // Formato esperado: "HH:mm:ss"

		// Validar el formato antes de la conversión
		if (!horarioIdaString.matches("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")) {
			System.out.println("Error: Formato de hora inválido. Debe ser HH:mm:ss");
		} else {
			// Convertir String a LocalTime
			LocalTime horarioIda = LocalTime.parse(horarioIdaString);

			String duracionIda = tfDuracion.getText();

			System.out.println("IDVIAJE en guardar evento " + this.idViaje);
			Evento evento = new Evento();
			evento.setNom_evento(nombreEvento);
			evento.setTipo(tipoEventoSeleccionado);
			evento.setTrayecto(trayecto);
			evento.setAe_origen(aeropuertoOrigen);
			evento.setAe_destino(aeropuertoDestino);
			evento.setFec_ida(fechaInicio);
			evento.setCod_vuelo_ida(codigoVuelo);
			evento.setAerolinea_ida(aerolinea);
			evento.setPrecio(precio);
			evento.setHora_ida(horarioIda);
			evento.setDuracion_ida(duracionIda);

			evento.setId_viaje(this.idViaje);

			try {
				controlador.guardarEventoVueloIda(evento);
				JOptionPane.showMessageDialog(panelEleccionIda, "Datos guardados correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(panelEleccionIda, "Error al guardar los datos: " + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
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
					panelEleccionIda.setBackground(Color.decode(colorHex));
				} else {
					panelEleccionIda.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	/**
	 * Valida que todos los campos requeridos sean correctos antes de guardar los
	 * datos.
	 * 
	 * @return true si los datos son válidos, false en caso contrario.
	 */
	private boolean validarCampos() {
		if (tfNombreEvento.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelEleccionIda, "El nombre del evento no puede estar vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxTipo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelEleccionIda, "Debe seleccionar un tipo de evento.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxTrayecto.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelEleccionIda, "Debe seleccionar un trayecto.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxOrigen.getSelectedItem() == null || comboBoxDestino.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelEleccionIda, "Debe seleccionar los aeropuertos de origen y destino.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (datePickerVueloIda.getModel().getValue() == null) {
			JOptionPane.showMessageDialog(panelEleccionIda, "Debe seleccionar una fecha válida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(tfCodVuelo.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(panelEleccionIda, "El código de vuelo debe ser un número válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Double.parseDouble(tfPrecio.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(panelEleccionIda, "El precio debe ser un número válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!tfHorario.getText().trim().matches("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")) {
			JOptionPane.showMessageDialog(panelEleccionIda, "El horario debe estar en formato HH:mm:ss.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (tfDuracion.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelEleccionIda, "Debe ingresar la duración.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void resetearCampos() {

		tfNombreEvento.setText("");
		tfCodVuelo.setText("");
		datePickerVueloIda.getModel().setValue(null);
		tfHorario.setText("");
		tfPrecio.setText("");
		tfDuracion.setText("");
	}

	/**
	 * Devuelve el panel de selección de vuelo de ida.
	 * 
	 * @return JPanel con la interfaz de selección de vuelo de ida.
	 */
	public JPanel getPanel() {

		return panelEleccionIda;
	}
}
