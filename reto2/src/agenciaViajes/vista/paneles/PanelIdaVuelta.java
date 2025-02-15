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
 * Clase que representa un panel para la gestión de vuelos de ida y vuelta en
 * una agencia de viajes. Permite seleccionar aeropuertos, aerolíneas, fechas,
 * horarios y otros detalles del viaje.
 */
public class PanelIdaVuelta {

	private JTextField tfDuracion;
	private JTextField tfCodVuelo;
	private JTextField tfNombreEvento;
	public JPanel panelIdaVuelta = null;
	public JLabel lblLogo;
	private JTextField tfHorario;
	private JTextField tfDuracionVuelta;
	private JTextField tfHorarioVuelta;
	private JTextField tfPrecioTotal;
	private JTextField tfCodVueloVuelta;
	private JComboBox comboBoxTipo;
	private JComboBox comboBoxIdaYVuelta;
	private JDatePickerImpl datePickerVueloIda;
	private JDatePickerImpl datePickerFecVuelta;
	private int idAgencia;
	private Window window;
	private int idViaje;
	private Date fechaInicio;
	private Date fechaFin;
	private GregorianCalendar calendarInicio = new GregorianCalendar();
	private GregorianCalendar calendarFin = new GregorianCalendar();

	private ArrayList<JPanel> paneles;
	JComboBox<String> comboBoxOrigen = new JComboBox<>();
	JComboBox<String> comboBoxDestino = new JComboBox<>();
	JComboBox<String> comboBoxAerolinea = new JComboBox<>();
	JComboBox<String> comboBoxAerolineaVuelta = new JComboBox<>();
	private Controlador controlador = new Controlador();

	/**
	 * Establece la agencia y el viaje en el panel.
	 *
	 * @param idAgencia ID de la agencia de viajes.
	 * @param idViaje   ID del viaje asociado.
	 */
	
	public void setIdAgencia(int idAgencia, int idViaje) {
		if (idAgencia > 0) { // Solo actualiza si es válido
			this.idAgencia = idAgencia;
			System.out.println("🛠️Seteando ID Agencia en PanelViajesEventos: " + idAgencia);// visualizar consola
			this.idViaje= idViaje;
			System.out.println("🛠️Seteando ID Viaje en PanelViajesEventos: " + idViaje);// visualizar consola
			
			cargarColorPanel(idAgencia);
			cargarLogo(idAgencia);

		}
	}
//
//	/**
//	 * Carga el identificador del viaje y muestra un mensaje de confirmación.
//	 *
//	 * @param idViaje Identificador del viaje a cargar.
//	 */
//	private void cargarIdViaje(int idViaje) {
//		this.idViaje = idViaje;
//		System.out.println("✅ ID de Viaje cargado correctamente en ida/vuelta: " + idViaje);
//	}

	/**
	 * Constructor de la clase PanelIdaVuelta.
	 *
	 * @param paneles   Lista de paneles para gestionar la navegación.
	 * @param idAgencia ID de la agencia de viajes.
	 * @param idViaje   ID del viaje a gestionar.
	 */
	public PanelIdaVuelta(ArrayList<JPanel> paneles, int idAgencia, int idViaje) {

		this.paneles = paneles;
		this.idAgencia = idAgencia;
		this.idViaje = idViaje;

		panelIdaVuelta = new JPanel();
		panelIdaVuelta.setBackground(Color.LIGHT_GRAY);
		panelIdaVuelta.setBounds(0, 0, 1039, 666);
		panelIdaVuelta.setLayout(null);

		JLabel lbNombreEvento = new JLabel("NOMBRE EVENTO");
		lbNombreEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lbNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbNombreEvento.setBounds(148, 78, 110, 17);
		panelIdaVuelta.add(lbNombreEvento);

		tfNombreEvento = new JTextField();
		tfNombreEvento.setBounds(309, 74, 130, 26);
		panelIdaVuelta.add(tfNombreEvento);
		tfNombreEvento.setColumns(10);

		JLabel lblTipoEvento = new JLabel("TIPO EVENTO");
		lblTipoEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoEvento.setBounds(148, 119, 86, 17);
		panelIdaVuelta.add(lblTipoEvento);

		JLabel lbTrayecto = new JLabel("TRAYECTO");
		lbTrayecto.setHorizontalAlignment(SwingConstants.CENTER);
		lbTrayecto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbTrayecto.setBounds(148, 161, 67, 17);
		panelIdaVuelta.add(lbTrayecto);

		lblLogo = new JLabel("Cargando logo...");
		lblLogo.setBounds(809, 0, 230, 128);
		panelIdaVuelta.add(lblLogo);

		JLabel lbOrigen = new JLabel("AEROPUERTO ORIGEN");
		lbOrigen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbOrigen.setBounds(148, 206, 151, 16);
		panelIdaVuelta.add(lbOrigen);

		JLabel lbDestino = new JLabel("AEROPUERTO DESTINO");
		lbDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbDestino.setBounds(148, 244, 151, 16);
		panelIdaVuelta.add(lbDestino);

		JLabel lbFecIda = new JLabel("FECHA IDA");
		lbFecIda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbFecIda.setBounds(148, 281, 110, 16);
		panelIdaVuelta.add(lbFecIda);

		datePickerVueloIda = new JDatePickerImpl(new JDatePanelImpl(null));
		datePickerVueloIda.setBounds(309, 277, 151, 30);
		panelIdaVuelta.add(datePickerVueloIda);

		JLabel lbCodVuelo = new JLabel("CODIGO VUELO");
		lbCodVuelo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCodVuelo.setBounds(148, 320, 151, 16);
		panelIdaVuelta.add(lbCodVuelo);

		JLabel lbAerolinea = new JLabel("AEROLINEA");
		lbAerolinea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbAerolinea.setBounds(148, 358, 151, 16);
		panelIdaVuelta.add(lbAerolinea);

		JLabel lbHorario = new JLabel("HORARIO");
		lbHorario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbHorario.setBounds(148, 432, 61, 16);
		panelIdaVuelta.add(lbHorario);

		JLabel lbDuracion = new JLabel("DURACION");
		lbDuracion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbDuracion.setBounds(148, 471, 97, 16);
		panelIdaVuelta.add(lbDuracion);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					
					guardarDatosIdaVuelta(idViaje);
					paneles.get(3).setVisible(false);
					paneles.get(2).setVisible(true);
					resetearCampos();
				}
			}

		});
		btnGuardar.setBounds(275, 568, 117, 29);
		panelIdaVuelta.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(3).setVisible(false);
				paneles.get(2).setVisible(true);
				resetearCampos();

			}
		});
		btnCancelar.setBounds(538, 568, 117, 29);
		panelIdaVuelta.add(btnCancelar);

		comboBoxTipo = new JComboBox();
		comboBoxTipo.setEnabled(false);
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "VUELO", "ALOJAMIENTO", "ACTIVIDAD" }));
		comboBoxTipo.setBounds(309, 116, 151, 27);
		panelIdaVuelta.add(comboBoxTipo);

		comboBoxIdaYVuelta = new JComboBox();
		comboBoxIdaYVuelta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxIdaYVuelta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String seleccion = (String) comboBoxIdaYVuelta.getSelectedItem();

				if (seleccion.equals("IDA")) {
					paneles.get(3).setVisible(false);
					paneles.get(5).setVisible(true);
				}

			}
		});

		comboBoxIdaYVuelta.setModel(new DefaultComboBoxModel(new String[] { "IDA/VUELTA", "IDA" }));
		comboBoxIdaYVuelta.setBounds(309, 158, 151, 27);
		panelIdaVuelta.add(comboBoxIdaYVuelta);

		// Combobox aerolinea
		comboBoxAerolinea = new JComboBox<>();
		ArrayList<Aerolinea> listaAerolineas = controlador.buscarAerolinea();
		DefaultComboBoxModel<String> modeloCombo2 = new DefaultComboBoxModel<>();
		for (Aerolinea aerolinea : listaAerolineas) {
			modeloCombo2.addElement(aerolinea.getNom_ae());
		}

		comboBoxAerolinea.setModel(modeloCombo2);
		comboBoxAerolinea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxAerolinea.setBounds(309, 353, 130, 26);
		panelIdaVuelta.add(comboBoxAerolinea);

		// ComboBoxAeropuerto origen
		ArrayList<Aeropuerto> listaAeropuertos = controlador.buscarAeropuertos();
		DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>();
		for (Aeropuerto codAeropuerto : listaAeropuertos) {
			modeloCombo.addElement(codAeropuerto.getCod_aeropuerto() + " " + " " + codAeropuerto.getCiudad());
		}
		comboBoxOrigen.setModel(modeloCombo);
		comboBoxOrigen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxOrigen.setBounds(309, 204, 151, 22);
		panelIdaVuelta.add(comboBoxOrigen);

		// Combobox aerolinea VUELTA
		ArrayList<Aerolinea> listaAerolineas2 = controlador.buscarAerolinea();
		DefaultComboBoxModel<String> modeloCombo3 = new DefaultComboBoxModel<>();
		for (Aerolinea aerolinea : listaAerolineas2) {
			modeloCombo3.addElement(aerolinea.getNom_ae());
		}
		comboBoxAerolineaVuelta.setModel(modeloCombo3);
		comboBoxAerolineaVuelta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxAerolineaVuelta.setBounds(677, 353, 130, 26);
		panelIdaVuelta.add(comboBoxAerolineaVuelta);

		// ComboBoxAeropuerto destino
		ArrayList<Aeropuerto> listaAeropuertosDestino = controlador.buscarAeropuertos();
		DefaultComboBoxModel<String> modeloComboAe = new DefaultComboBoxModel<>();
		for (Aeropuerto codAeropuerto : listaAeropuertosDestino) {
			modeloComboAe.addElement(codAeropuerto.getCod_aeropuerto() + " " + " " + codAeropuerto.getCiudad());
		}

		comboBoxDestino.setModel(modeloComboAe);
		comboBoxDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxDestino.setBounds(309, 238, 151, 22);
		panelIdaVuelta.add(comboBoxDestino);

		JButton btnBuscar = new JButton("BUSCAR VIAJE");
		btnBuscar.setHorizontalAlignment(SwingConstants.LEADING);
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
		panelIdaVuelta.add(btnBuscar);

		tfCodVuelo = new JTextField();
		tfCodVuelo.setEditable(true);
		tfCodVuelo.setBounds(309, 315, 130, 26);
		panelIdaVuelta.add(tfCodVuelo);
		tfCodVuelo.setColumns(10);

		tfHorario = new JTextField();
		tfHorario.setEditable(true);
		tfHorario.setColumns(10);
		tfHorario.setBounds(309, 427, 130, 26);
		panelIdaVuelta.add(tfHorario);

		tfDuracion = new JTextField();
		tfDuracion.setEditable(true);
		tfDuracion.setColumns(10);
		tfDuracion.setBounds(309, 466, 130, 26);
		panelIdaVuelta.add(tfDuracion);

		JLabel lbFecVuelta = new JLabel("FECHA VUELTA");
		lbFecVuelta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbFecVuelta.setBounds(506, 281, 110, 16);
		panelIdaVuelta.add(lbFecVuelta);

		datePickerFecVuelta = new JDatePickerImpl(new JDatePanelImpl(null));
		datePickerFecVuelta.setBounds(680, 267, 151, 30);
		panelIdaVuelta.add(datePickerFecVuelta);

		JLabel lbCodVueloVuelta = new JLabel("CODIGO VUELO");
		lbCodVueloVuelta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCodVueloVuelta.setBounds(506, 320, 151, 16);
		panelIdaVuelta.add(lbCodVueloVuelta);

		JLabel lbAerolineaVuelta = new JLabel("AEROLINEA VUELTA");
		lbAerolineaVuelta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbAerolineaVuelta.setBounds(506, 358, 151, 16);
		panelIdaVuelta.add(lbAerolineaVuelta);

		JLabel lbPrecioTotal = new JLabel("PRECIO TOTAL");
		lbPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbPrecioTotal.setBounds(506, 396, 130, 16);
		panelIdaVuelta.add(lbPrecioTotal);

		JLabel lbHorarioVuelta = new JLabel("HORARIO VUELTA");
		lbHorarioVuelta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbHorarioVuelta.setBounds(506, 432, 130, 16);
		panelIdaVuelta.add(lbHorarioVuelta);

		JLabel lblDuracionVuelta = new JLabel("DURACION VUELTA");
		lblDuracionVuelta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDuracionVuelta.setBounds(506, 471, 130, 16);
		panelIdaVuelta.add(lblDuracionVuelta);

		tfDuracionVuelta = new JTextField();
		tfDuracionVuelta.setEditable(true);
		tfDuracionVuelta.setColumns(10);
		tfDuracionVuelta.setBounds(677, 466, 130, 26);
		panelIdaVuelta.add(tfDuracionVuelta);

		tfHorarioVuelta = new JTextField();
		tfHorarioVuelta.setEditable(true);
		tfHorarioVuelta.setColumns(10);
		tfHorarioVuelta.setBounds(677, 427, 130, 26);
		panelIdaVuelta.add(tfHorarioVuelta);

		tfPrecioTotal = new JTextField();
		tfPrecioTotal.setEditable(true);
		tfPrecioTotal.setColumns(10);
		tfPrecioTotal.setBounds(677, 391, 130, 26);
		panelIdaVuelta.add(tfPrecioTotal);

		tfCodVueloVuelta = new JTextField();
		tfCodVueloVuelta.setEditable(true);
		tfCodVueloVuelta.setColumns(10);
		tfCodVueloVuelta.setBounds(677, 315, 130, 26);
		panelIdaVuelta.add(tfCodVueloVuelta);

	}

	/**
	 * Guarda los datos del vuelo de ida y vuelta en la base de datos.
	 *
	 * @param idViaje El ID del viaje al que se asocian los datos del vuelo.
	 */
	private void guardarDatosIdaVuelta(int idViaje) {
		System.out.println("DEBUG: idViaje antes de guardar = " + this.idViaje);
		System.out.println("El boton guardar datos IDA/VUELTA recibe idviaje " + PanelIdaVuelta.this.idViaje);

		String nombreEvento = tfNombreEvento.getText();
		String tipoEventoSeleccionado = (String) comboBoxTipo.getSelectedItem();
		String trayecto = (String) comboBoxIdaYVuelta.getSelectedItem();
		String aeropuertoOrigen = (String) comboBoxOrigen.getSelectedItem();
		String aeropuertoDestino = (String) comboBoxDestino.getSelectedItem();

		// Recoger datos fecha inicio
		calendarInicio = (GregorianCalendar) datePickerVueloIda.getModel().getValue();
		if (calendarInicio == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Seleccione una fecha válida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		fechaInicio = new Date(calendarInicio.getTimeInMillis());

		// Recoger datos fecha fin
		calendarFin = (GregorianCalendar) datePickerFecVuelta.getModel().getValue();
		if (calendarFin == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Seleccione una fecha válida para la vuelta.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		fechaFin = new Date(calendarFin.getTimeInMillis());

		// Codigo vuelo IDA en int
		String codigoVueloTexto = tfCodVuelo.getText();
		int codigoVuelo = 0;

		try {
			codigoVuelo = Integer.parseInt(codigoVueloTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Código de vuelo inválido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfCodVuelo.requestFocus();
		}

		// Codigo vuelo VUELTA en int
		String codigoVueloVueltaTexto = tfCodVueloVuelta.getText();
		int codigoVueloVuelta = 0;
		try {
			codigoVueloVuelta = Integer.parseInt(codigoVueloTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Código de vuelo inválido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfCodVuelo.requestFocus();
		}

		String aerolinea = (String) comboBoxAerolinea.getSelectedItem();
		String aerolineaVuelta = (String) comboBoxAerolineaVuelta.getSelectedItem();

		String precioTexto = tfPrecioTotal.getText();
		double precio = 0;
		try {
			precio = Double.parseDouble(precioTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
			tfPrecioTotal.requestFocus();
		}
		String horarioIdaString = tfHorario.getText(); // Formato esperado: "HH:mm:ss"
		String horarioVueltaString = tfHorarioVuelta.getText(); // Formato esperado: "HH:mm:ss"
		// Validar el formato antes de la conversión
		if (!(horarioIdaString.matches("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")
				|| horarioVueltaString.matches("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$"))) {
			System.out.println("Error: Formato de hora inválido. Debe ser HH:mm:ss");
		} else {
			// Convertir String a LocalTime
			LocalTime horarioIda = LocalTime.parse(horarioIdaString);
			LocalTime horarioVuelta = LocalTime.parse(horarioIdaString);

			String duracionIda = tfDuracion.getText();
			String duracionVuelta = tfDuracionVuelta.getText();

			System.out.println("IDVIAJE en guardar evento " + this.idViaje);

			Evento evento = new Evento();
			evento.setNom_evento(nombreEvento);
			evento.setTipo(tipoEventoSeleccionado);
			evento.setTrayecto(trayecto);
			evento.setAe_origen(aeropuertoOrigen);
			evento.setAe_destino(aeropuertoDestino);
			evento.setFec_ida(fechaInicio);
			evento.setFec_vuelta(fechaFin);
			evento.setCod_vuelo_ida(codigoVuelo);
			evento.setCod_vuelo_ida(codigoVueloVuelta);
			evento.setAerolinea_ida(aerolinea);
			evento.setAerolinea_vuelta(aerolineaVuelta);
			evento.setHora_ida(horarioIda);
			evento.setHora_vuelta(horarioVuelta);
			evento.setPrecio(precio);
			evento.setDuracion_ida(duracionIda);
			evento.setDuracion_vuelta(duracionIda);

			evento.setId_viaje(this.idViaje);

			try {
				controlador.guardarEventoVueloIdaVuelta(evento, this.idViaje) ;
				JOptionPane.showMessageDialog(panelIdaVuelta, "Datos guardados correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(panelIdaVuelta, "Error al guardar los datos: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
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
				lblLogo.setIcon(null);
				return;
			}

			for (Agencia agenciaLogueada : agencias) {
				if (idAgencia == agenciaLogueada.getId_agencia()) {
					String logoUrl = agenciaLogueada.getLogo();
					System.out.println("Logo: " + logoUrl);

					if (logoUrl == null || logoUrl.isEmpty()) {
						lblLogo.setIcon(new ImageIcon("ruta/a/logo_por_defecto.png"));
						lblLogo.setText("");
						return;
					}

					URL url = new URL(logoUrl);
					Image imagen = ImageIO.read(url);
					ImageIcon icono = new ImageIcon(
							imagen.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));

					lblLogo.setIcon(icono);
					lblLogo.setText("");

					// Limpiar
					lblLogo.revalidate();
					lblLogo.repaint();
					panelIdaVuelta.revalidate();
					panelIdaVuelta.repaint();

					return;
				}
			}

			lblLogo.setText("No se encontró el logo.");
			lblLogo.setIcon(null);
		} catch (IOException e) {
			lblLogo.setText("Error al cargar el logo.");
			lblLogo.setIcon(null);
		} catch (Exception e) {
			lblLogo.setText("Error inesperado.");
			lblLogo.setIcon(null);
			e.printStackTrace();
		}
	}

	/**
	 * Carga el color de fondo del panel según la configuración de la agencia.
	 *
	 * @param idAgencia El ID de la agencia para buscar su configuración de color.
	 */
	private void cargarColorPanel(int idAgencia) {
		System.out.println("Cargando color para ID Agencia: " + idAgencia); // Depuración

		ArrayList<Agencia> agencias = controlador.buscarAgenciasPorId();

		for (Agencia agenciaLogueada : agencias) {
			if (idAgencia == agenciaLogueada.getId_agencia()) {
				String colorHex = agenciaLogueada.getColor();
				System.out.println("Color Hex de la agencia: " + colorHex); // visualizar consola

				if (colorHex == null || colorHex.isEmpty()) {
					panelIdaVuelta.setBackground(Color.LIGHT_GRAY);
				} else {
					try {
						panelIdaVuelta.setBackground(Color.decode(colorHex));
					} catch (NumberFormatException e) {
						panelIdaVuelta.setBackground(Color.LIGHT_GRAY);
					}
				}
				panelIdaVuelta.repaint();
				break;
			}
		}
	}

	/**
	 * Valida que todos los campos requeridos en el formulario tengan valores
	 * correctos.
	 *
	 * @return `true` si todos los campos son válidos, `false` en caso contrario.
	 */

	private boolean validarCampos() {
		// Validar que los campos de texto no estén vacíos
		if (tfNombreEvento.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "El nombre del evento no puede estar vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfNombreEvento.requestFocus();
			return false;
		}

		if (comboBoxTipo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Debe seleccionar un tipo de evento.", "Error",
					JOptionPane.ERROR_MESSAGE);
			comboBoxTipo.requestFocus();
			return false;
		}

		if (comboBoxIdaYVuelta.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Debe seleccionar un tipo de trayecto (ida o ida y vuelta).",
					"Error", JOptionPane.ERROR_MESSAGE);
			comboBoxIdaYVuelta.requestFocus();
			return false;
		}

		if (comboBoxOrigen.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Debe seleccionar un aeropuerto de origen.", "Error",
					JOptionPane.ERROR_MESSAGE);
			comboBoxOrigen.requestFocus();
			return false;
		}

		if (comboBoxDestino.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Debe seleccionar un aeropuerto de destino.", "Error",
					JOptionPane.ERROR_MESSAGE);
			comboBoxDestino.requestFocus();
			return false;
		}

		// Validar fecha de ida
		calendarInicio = (GregorianCalendar) datePickerVueloIda.getModel().getValue();
		if (calendarInicio == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Seleccione una fecha válida para la ida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		fechaInicio = new Date(calendarInicio.getTimeInMillis());

		// Validar fecha de vuelta si aplica
		if (comboBoxIdaYVuelta.getSelectedItem().equals("IDA Y VUELTA")) {
			calendarFin = (GregorianCalendar) datePickerFecVuelta.getModel().getValue();
			if (calendarFin == null) {
				JOptionPane.showMessageDialog(panelIdaVuelta, "Seleccione una fecha válida para la vuelta.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			fechaFin = new Date(calendarFin.getTimeInMillis());

			// Validar que la fecha de vuelta sea posterior a la de ida
			if (!fechaFin.after(fechaInicio)) {
				JOptionPane.showMessageDialog(panelIdaVuelta,
						"La fecha de vuelta debe ser posterior a la fecha de ida.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		// Validar código de vuelo de ida
		try {
			Integer.parseInt(tfCodVuelo.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Código de vuelo inválido. Debe ser un número entero.",
					"Error", JOptionPane.ERROR_MESSAGE);
			tfCodVuelo.requestFocus();
			return false;
		}

		// Validar código de vuelo de vuelta si aplica
		if (comboBoxIdaYVuelta.getSelectedItem().equals("IDA Y VUELTA")) {
			try {
				Integer.parseInt(tfCodVueloVuelta.getText().trim());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(panelIdaVuelta,
						"Código de vuelo de vuelta inválido. Debe ser un número entero.", "Error",
						JOptionPane.ERROR_MESSAGE);
				tfCodVueloVuelta.requestFocus();
				return false;
			}
		}

		// Validar aerolínea
		if (comboBoxAerolinea.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Debe seleccionar una aerolínea.", "Error",
					JOptionPane.ERROR_MESSAGE);
			comboBoxAerolinea.requestFocus();
			return false;
		}

		if (comboBoxIdaYVuelta.getSelectedItem().equals("IDA Y VUELTA")
				&& comboBoxAerolineaVuelta.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Debe seleccionar una aerolínea para el vuelo de vuelta.",
					"Error", JOptionPane.ERROR_MESSAGE);
			comboBoxAerolineaVuelta.requestFocus();
			return false;
		}

		// Validar precio
		try {
			double precio = Double.parseDouble(tfPrecioTotal.getText().trim());
			if (precio < 0) {
				JOptionPane.showMessageDialog(panelIdaVuelta, "El precio no puede ser negativo.", "Error",
						JOptionPane.ERROR_MESSAGE);
				tfPrecioTotal.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Precio inválido. Debe ser un número decimal.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfPrecioTotal.requestFocus();
			return false;
		}

		// Validar horarios (HH:mm:ss)
		if (!tfHorario.getText().trim().matches("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Formato de horario de ida inválido. Debe ser HH:mm:ss.",
					"Error", JOptionPane.ERROR_MESSAGE);
			tfHorario.requestFocus();
			return false;
		}

		if (comboBoxIdaYVuelta.getSelectedItem().equals("IDA Y VUELTA")
				&& !tfHorarioVuelta.getText().trim().matches("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Formato de horario de vuelta inválido. Debe ser HH:mm:ss.",
					"Error", JOptionPane.ERROR_MESSAGE);
			tfHorarioVuelta.requestFocus();
			return false;
		}

		// Validar duración
		if (tfDuracion.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Debe ingresar una duración para la ida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfDuracion.requestFocus();
			return false;
		}

		if (comboBoxIdaYVuelta.getSelectedItem().equals("IDA Y VUELTA")
				&& tfDuracionVuelta.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelIdaVuelta, "Debe ingresar una duración para la vuelta.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfDuracionVuelta.requestFocus();
			return false;
		}

		return true;
	}

	/**
	 * Resetea los campos del formulario a su estado inicial.
	 */
	private void resetearCampos() {

		tfNombreEvento.setText("");
		tfCodVuelo.setText("");
		tfCodVueloVuelta.setText("");
		datePickerFecVuelta.getModel().setValue(null);
		datePickerVueloIda.getModel().setValue(null);
		tfHorario.setText("");
		tfHorarioVuelta.setText("");
		tfPrecioTotal.setText("");
		tfDuracion.setText("");
		tfDuracionVuelta.setText("");
	}

	/**
	 * Devuelve el panel principal de esta vista.
	 *
	 * @return El panel principal de la vista de vuelos de ida y vuelta.
	 */
	public JPanel getPanel() {

		return panelIdaVuelta;
	}

}