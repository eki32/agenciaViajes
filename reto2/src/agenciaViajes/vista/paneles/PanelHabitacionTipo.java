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

/**
 * Clase que representa un panel para gestionar la reserva de habitaciones en un
 * alojamiento. Permite seleccionar la ciudad, el tipo de habitación, las fechas
 * de entrada y salida, y el precio del alojamiento.
 */
public class PanelHabitacionTipo {

	private JPanel panelHabitacionTipo;
	private JTextField tfNombreEvento;
	private JLabel lbTipo;
	private JLabel lbTipoHabitacion;
	private JTextField tfCiudadAloj;
	private JTextField tfPrecioAloj;
	private JComboBox tipoEven;
	private JComboBox tipoHab;
	private JLabel lblLogo;
	private int idAgencia;
	private int idViaje;
	private Date fechaInicio;
	private Date fechaFin;
	private ArrayList<JPanel> paneles;
	private GregorianCalendar calendarInicio = new GregorianCalendar();
	private GregorianCalendar calendarFin = new GregorianCalendar();
	private Controlador controlador = new Controlador();
	private JDatePickerImpl datePickerFecEntrada;
	private JDatePickerImpl datePickerFecSalida;

	/**
	 * Establece el identificador de la agencia y el viaje, cargando la
	 * configuración correspondiente (color del panel y logo).
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
	 * Carga el identificador del viaje y muestra un mensaje de confirmación.
	 *
	 * @param idViaje Identificador del viaje a cargar.
	 */
	private void cargarIdViaje(int idViaje) {
		this.idViaje = idViaje;
		System.out.println("✅ ID de Viaje cargado correctamente en IDA: " + idViaje);
	}

	/**
	 * Constructor de la clase PanelHabitacionTipo. Crea un panel donde los usuarios
	 * pueden ingresar los detalles de su alojamiento.
	 *
	 * @param paneles   Lista de paneles para gestionar la navegación.
	 * @param idAgencia ID de la agencia de viajes.
	 * @param idViaje   ID del viaje a gestionar.
	 */
	public PanelHabitacionTipo(ArrayList<JPanel> paneles, int idAgencia, int idViaje) {

		this.idViaje = idViaje;
		this.paneles = paneles;
		this.idAgencia = idAgencia;

		panelHabitacionTipo = new JPanel();
		panelHabitacionTipo.setBackground(Color.LIGHT_GRAY);
		panelHabitacionTipo.setBounds(0, 0, 1039, 666);
		panelHabitacionTipo.setLayout(null);

		JLabel lbNombreEvento = new JLabel("NOMBRE EVENTO");
		lbNombreEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lbNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbNombreEvento.setBounds(148, 78, 110, 17);
		panelHabitacionTipo.add(lbNombreEvento);

		tfNombreEvento = new JTextField();
		tfNombreEvento.setBounds(309, 74, 130, 26);
		panelHabitacionTipo.add(tfNombreEvento);
		tfNombreEvento.setColumns(10);

		lblLogo = new JLabel("Cargando logo...");
		lblLogo.setBounds(809, 0, 230, 128);
		panelHabitacionTipo.add(lblLogo);

		lbTipo = new JLabel("TIPO EVENTO");
		lbTipo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbTipo.setBounds(148, 117, 110, 20);
		panelHabitacionTipo.add(lbTipo);

		tipoHab = new JComboBox();
		tipoHab.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tipoHab.setModel(
				new DefaultComboBoxModel(new String[] { "Doble", "Individual-Doble", "Individual", "Triple" }));
		tipoHab.setBounds(309, 171, 151, 27);
		panelHabitacionTipo.add(tipoHab);

		tipoEven = new JComboBox();
		tipoEven.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tipoEven.setEnabled(false);
		tipoEven.setModel(new DefaultComboBoxModel(new String[] { "ALOJAMIENTO", "ACTIVIDAD", "VUELO" }));
		tipoEven.setBounds(309, 116, 151, 27);
		panelHabitacionTipo.add(tipoEven);

		lbTipoHabitacion = new JLabel("TIPO HABITACION");
		lbTipoHabitacion.setHorizontalAlignment(SwingConstants.LEFT);
		lbTipoHabitacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbTipoHabitacion.setBounds(148, 172, 135, 20);
		panelHabitacionTipo.add(lbTipoHabitacion);

		JLabel lbCiudad = new JLabel("CIUDAD");
		lbCiudad.setHorizontalAlignment(SwingConstants.LEFT);
		lbCiudad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCiudad.setBounds(148, 222, 135, 20);
		panelHabitacionTipo.add(lbCiudad);

		JLabel lbPrecio = new JLabel("PRECIO");
		lbPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lbPrecio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbPrecio.setBounds(148, 267, 135, 20);
		panelHabitacionTipo.add(lbPrecio);

		JLabel lbFecEntrada = new JLabel("FECHA ENTRADA");
		lbFecEntrada.setHorizontalAlignment(SwingConstants.LEFT);
		lbFecEntrada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbFecEntrada.setBounds(148, 314, 135, 20);
		panelHabitacionTipo.add(lbFecEntrada);

		datePickerFecEntrada = new JDatePickerImpl(new JDatePanelImpl(null));
		datePickerFecEntrada.setBounds(309, 304, 150, 30);
		panelHabitacionTipo.add(datePickerFecEntrada);

		JLabel lbFecSalida = new JLabel("FECHA SALIDA");
		lbFecSalida.setHorizontalAlignment(SwingConstants.LEFT);
		lbFecSalida.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbFecSalida.setBounds(501, 314, 135, 20);
		panelHabitacionTipo.add(lbFecSalida);

		datePickerFecSalida = new JDatePickerImpl(new JDatePanelImpl(null));
		datePickerFecSalida.setBounds(602, 304, 150, 30);
		panelHabitacionTipo.add(datePickerFecSalida);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					guardarDatosAlojamiento(idViaje);
					paneles.get(4).setVisible(false);
					paneles.get(2).setVisible(true);
					resetearCampos();
				}
			}

		});
		btnGuardar.setBounds(275, 568, 117, 29);
		panelHabitacionTipo.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(4).setVisible(false);
				paneles.get(2).setVisible(true);
				resetearCampos();
			}
		});
		btnCancelar.setBounds(538, 568, 117, 29);
		panelHabitacionTipo.add(btnCancelar);

		JButton btnBuscarAloj = new JButton("BUSCAR");
		btnBuscarAloj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscarAloj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Abrir Booking en el navegador predeterminado
					Desktop.getDesktop().browse(new URI("https://www.booking.com/"));
					System.out.println("✅ Redirigiendo a Booking.com");
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace();
					System.out.println("⚠️ Error al abrir Booking.com");

				}
			}
		});

		btnBuscarAloj.setBounds(455, 241, 117, 29);
		panelHabitacionTipo.add(btnBuscarAloj);

		tfCiudadAloj = new JTextField();
		tfCiudadAloj.setColumns(10);
		tfCiudadAloj.setBounds(309, 220, 130, 26);
		panelHabitacionTipo.add(tfCiudadAloj);

		tfPrecioAloj = new JTextField();
		tfPrecioAloj.setColumns(10);
		tfPrecioAloj.setBounds(309, 265, 130, 26);
		panelHabitacionTipo.add(tfPrecioAloj);

	}

	/**
	 * Guarda los datos del alojamiento en la base de datos. Recopila la información
	 * del formulario, valida los datos y utiliza el controlador para guardar el
	 * evento de alojamiento.
	 *
	 * @param idViaje Identificador del viaje asociado al alojamiento.
	 */
	private void guardarDatosAlojamiento(int idViaje) {
		System.out.println("El boton guardar datos ALOJAMIENTO recibe idviaje " + this.idViaje);
		String nombreEvento = tfNombreEvento.getText();
		String tipoEventoSeleccionado = (String) tipoEven.getSelectedItem();
		String idTipoHabitacion = (String) tipoHab.getSelectedItem();
		switch (tipoHab.getSelectedIndex()) {
		case 0:
			idTipoHabitacion = String.valueOf(controlador.buscarId_Doble());
			break;
		case 1:
			idTipoHabitacion = String.valueOf(controlador.buscarId_IndividualDoble());
			break;
		case 2:
			idTipoHabitacion = String.valueOf(controlador.buscarId_Individual());
			break;
		case 3:
			idTipoHabitacion = String.valueOf(controlador.buscarId_Triple());
			break;
		}

		String ciudad = tfCiudadAloj.getText();
		double precio = 0;
		String precioTexto = tfPrecioAloj.getText();
		try {
			precio = Double.parseDouble(precioTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
			tfPrecioAloj.requestFocus();
		}

		// Recoger datos fecha inicio
		calendarInicio = (GregorianCalendar) datePickerFecEntrada.getModel().getValue();
		if (calendarInicio == null) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Seleccione una fecha válida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		fechaInicio = new Date(calendarInicio.getTimeInMillis());

		// Recoger datos fecha inicio
		calendarFin = (GregorianCalendar) datePickerFecSalida.getModel().getValue();
		if (calendarFin == null) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Seleccione una fecha válida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		fechaFin = new Date(calendarFin.getTimeInMillis());

		Evento evento = new Evento();
		evento.setNom_evento(nombreEvento);
		evento.setTipo(tipoEventoSeleccionado);
		evento.setId_tipo_hab(idTipoHabitacion);
		evento.setPrecio(precio);
		evento.setCiudad(ciudad);
		evento.setFec_ida(fechaInicio);
		evento.setFec_salida(fechaFin);

		evento.setId_viaje(this.idViaje);

		try {
			controlador.guardarEventoAlojamiento(evento);
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Datos guardados correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Error al guardar los datos: " + e.getMessage(), "Error",
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
					panelHabitacionTipo.setBackground(Color.decode(colorHex));
				} else {
					panelHabitacionTipo.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

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
	 * Valida los campos de entrada en el formulario de tipo de habitación.
	 *
	 * @return `true` si todos los campos son válidos, `false` en caso contrario.
	 */
	private boolean validarCampos() {
		// Validar que el nombre del evento no esté vacío
		if (tfNombreEvento.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "El nombre del evento no puede estar vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfNombreEvento.requestFocus();
			return false;
		}

		// Validar selección del tipo de evento
		if (tipoEven.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Debe seleccionar un tipo de evento.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tipoEven.requestFocus();
			return false;
		}

		// Validar selección del tipo de habitación
		if (tipoHab.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Debe seleccionar un tipo de habitación.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tipoHab.requestFocus();
			return false;
		}

		// Validar la ciudad
		if (tfCiudadAloj.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Debe ingresar una ciudad.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfCiudadAloj.requestFocus();
			return false;
		}

		// Validar el precio
		try {
			double precio = Double.parseDouble(tfPrecioAloj.getText().trim());
			if (precio < 0) {
				JOptionPane.showMessageDialog(panelHabitacionTipo, "El precio no puede ser negativo.", "Error",
						JOptionPane.ERROR_MESSAGE);
				tfPrecioAloj.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Precio inválido. Debe ser un número decimal.", "Error",
					JOptionPane.ERROR_MESSAGE);
			tfPrecioAloj.requestFocus();
			return false;
		}

		// Validar fecha de entrada
		calendarInicio = (GregorianCalendar) datePickerFecEntrada.getModel().getValue();
		if (calendarInicio == null) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Seleccione una fecha válida para la entrada.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		fechaInicio = new Date(calendarInicio.getTimeInMillis());

		// Validar fecha de salida
		calendarFin = (GregorianCalendar) datePickerFecSalida.getModel().getValue();
		if (calendarFin == null) {
			JOptionPane.showMessageDialog(panelHabitacionTipo, "Seleccione una fecha válida para la salida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		fechaFin = new Date(calendarFin.getTimeInMillis());

		// Verificar que la fecha de salida sea posterior a la de entrada
		if (!fechaFin.after(fechaInicio)) {
			JOptionPane.showMessageDialog(panelHabitacionTipo,
					"La fecha de salida debe ser posterior a la fecha de entrada.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true; // Todos los campos son válidos
	}

	private void resetearCampos() {

		tfNombreEvento.setText("");
		tfCiudadAloj.setText("");
		datePickerFecEntrada.getModel().setValue(null);
		datePickerFecSalida.getModel().setValue(null);
		tfPrecioAloj.setText("");
	}

	/**
	 * Obtiene el panel para la configuración del tipo de habitación y el
	 * alojamiento.
	 *
	 * @return El panel para la configuración del tipo de habitación y el
	 *         alojamiento.
	 */
	public JPanel getPanel() {

		return panelHabitacionTipo;
	}
}