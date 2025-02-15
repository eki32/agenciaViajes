package agenciaViajes.vista.paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import agenciaViajes.bbdd.controlador.Controlador;
import agenciaViajes.bbdd.entidad.Agencia;
import agenciaViajes.bbdd.entidad.Pais;
import agenciaViajes.bbdd.entidad.Viaje;

import java.awt.Color;

/**
 * Clase que representa el panel para crear un nuevo perfil de agencia. Permite
 * a los usuarios ingresar información sobre la agencia, como nombre,
 * contraseña, color de marca, logo, número de empleados y tipo de agencia.
 */
public class PanelNuevoViaje {

	private JPanel panelNuevoViaje = null;
	private JTextField textFieldNombreViaje;
	private JTextArea textAreaDescrip;
	private JDatePickerImpl datePickerInicio;
	private JDatePickerImpl datePickerFin;
	private JLabel lblCantidadDias;
	private JComboBox<String> comboBoxTipoViaje;
	JComboBox<String> comboBoxPais;
	private JLabel lblLogo;
	public int idAgencia;
	public DefaultTableModel modeloViajes;
	private Controlador controlador = new Controlador();
	private Date fechaInicio;
	private Date fechaFin;
	private GregorianCalendar calendarInicio = new GregorianCalendar();
	private GregorianCalendar calendarFin = new GregorianCalendar();
	private JTextArea textAreaServNoIncl;

	/**
	 * Establece la agencia y el viaje en el panel.
	 *
	 * @param idAgencia ID de la agencia de viajes.
	 * @param idViaje   ID del viaje asociado.
	 */
	public void setIdAgencia(int idAgencia) {

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
		System.out.println("ID de Viaje cargado correctamente en ida/vuelta: " + idViaje);
	}

	/**
	 * Constructor de la clase PanelNuevoViaje.
	 * 
	 * @param paneles   Lista de paneles en la interfaz de usuario.
	 * @param idAgencia Identificador de la agencia de viajes.
	 * @param idViaje   Identificador del viaje (puede ser usado para edición).
	 * @param ventana   Referencia a la ventana principal.
	 */
	public PanelNuevoViaje(ArrayList<JPanel> paneles, int idAgencia, int idViaje, Window ventana) {

		this.idAgencia = idAgencia;
		this.panelNuevoViaje = new JPanel();
		this.panelNuevoViaje.setBounds(0, 0, 1041, 666);
		this.panelNuevoViaje.setLayout(null);

		JLabel lblNombreViaje = new JLabel("NOMBRE VIAJE");
		lblNombreViaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombreViaje.setBounds(46, 92, 122, 20);
		panelNuevoViaje.add(lblNombreViaje);

		JLabel lblTipoViaje = new JLabel("TIPO DE VIAJE");
		lblTipoViaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoViaje.setBounds(47, 130, 122, 20);
		panelNuevoViaje.add(lblTipoViaje);

		lblLogo = new JLabel("Cargando logo...");
		lblLogo.setBounds(809, 0, 230, 128);
		panelNuevoViaje.add(lblLogo);

		JLabel lblNewLabel = new JLabel("NUEVO VIAJE");
		lblNewLabel.setBounds(46, 32, 234, 32);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNuevoViaje.add(lblNewLabel);

		JLabel lblInicioViaje = new JLabel("INICIO VIAJE");
		lblInicioViaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInicioViaje.setBounds(46, 195, 122, 20);
		panelNuevoViaje.add(lblInicioViaje);

		JLabel lblFinViaje = new JLabel("FIN VIAJE");
		lblFinViaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFinViaje.setBounds(46, 236, 122, 20);
		panelNuevoViaje.add(lblFinViaje);

		JLabel lblDias = new JLabel("DIAS");
		lblDias.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDias.setBounds(46, 282, 122, 20);
		panelNuevoViaje.add(lblDias);

		lblCantidadDias = new JLabel("0");
		lblCantidadDias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidadDias.setForeground(new Color(255, 0, 0));
		lblCantidadDias.setBounds(185, 285, 122, 14);
		panelNuevoViaje.add(lblCantidadDias);

		JLabel lblPais = new JLabel("PAIS");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPais.setBounds(46, 336, 122, 20);
		panelNuevoViaje.add(lblPais);

		comboBoxPais = new JComboBox<>();
		Controlador controlador = new Controlador();
		DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>();
		ArrayList<Pais> listaPaises = controlador.buscarPaises();

		// Llenar el combobox con los nombres de los países
		if (listaPaises == null || listaPaises.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encontraron países en la base de datos.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			for (Pais pais : listaPaises) {
				if (pais.getNom_pais() != null) { // Evita valores nulos
					modeloCombo.addElement(pais.getNom_pais());
				}
			}
		}

		comboBoxPais.setModel(modeloCombo);
		comboBoxPais.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxPais.setBounds(185, 335, 203, 22);
		panelNuevoViaje.add(comboBoxPais);

		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDescripcion.setBounds(46, 449, 122, 20);
		panelNuevoViaje.add(lblDescripcion);

		JLabel lblServiciosNoIncluidos = new JLabel("SERVICIOS NO INCLUIDOS");
		lblServiciosNoIncluidos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblServiciosNoIncluidos.setBounds(46, 567, 171, 20);
		panelNuevoViaje.add(lblServiciosNoIncluidos);

		// Campo de texto para el nombre del viaje
		textFieldNombreViaje = new JTextField();
		textFieldNombreViaje.setBounds(185, 92, 203, 20);
		panelNuevoViaje.add(textFieldNombreViaje);
		textFieldNombreViaje.setColumns(10);

		// Combobox para el tipo de viaje
		comboBoxTipoViaje = new JComboBox();
		comboBoxTipoViaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxTipoViaje.setBounds(186, 129, 203, 22);
		comboBoxTipoViaje.setModel(new DefaultComboBoxModel(new String[] { "Luna de Miel", "Senior", "Grupos",
				"Viajes grandes (destinos exóticos + vuelo + alojamiento)", "Escapadas",
				"Familias (con niños pequeños)" }));
		panelNuevoViaje.add(comboBoxTipoViaje);

		// Datepicker para la fecha de inicio
		datePickerInicio = new JDatePickerImpl(new JDatePanelImpl(null));
		datePickerInicio.setBounds(185, 195, 203, 30);
		panelNuevoViaje.add(datePickerInicio);

		// Datepicker para la fecha de fin
		datePickerFin = new JDatePickerImpl(new JDatePanelImpl(null));
		datePickerFin.setBounds(185, 236, 203, 30);
		panelNuevoViaje.add(datePickerFin);

		// ActionListener para calcular los días al seleccionar fecha en cualquier
		// datePicker
		datePickerInicio.addActionListener(e -> {
			actualizarCantidadDias();
		});
		datePickerFin.addActionListener(e -> {
			actualizarCantidadDias();
		});

		// Área de texto para la descripción
		textAreaDescrip = new JTextArea();
		textAreaDescrip.setBounds(244, 385, 203, 84);
		panelNuevoViaje.add(textAreaDescrip);

		// Área de texto para servicios no incluidos
		textAreaServNoIncl = new JTextArea();
		textAreaServNoIncl.setBounds(244, 503, 203, 84);
		panelNuevoViaje.add(textAreaServNoIncl);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarCampos(textFieldNombreViaje, datePickerInicio, datePickerFin, textAreaDescrip,
						textAreaServNoIncl)) {
					guardarDatos();
					ventana.panelViajesEventos.setIdAgencia(PanelNuevoViaje.this.idAgencia);

					System.out.println("ID Agencia antes de setear en PanelViajesEventos: " + idAgencia);

					System.out.println("Todos los datos son válidos. Guardando información...");
					paneles.get(10).setVisible(false);
					paneles.get(2).setVisible(true);

				} else {
					JOptionPane.showMessageDialog(panelNuevoViaje,
							"Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(380, 631, 117, 29);
		panelNuevoViaje.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(10).setVisible(false);
				paneles.get(2).setVisible(true);
			}
		});
		btnCancelar.setBounds(589, 631, 117, 29);
		panelNuevoViaje.add(btnCancelar);

	}

	/**
	 * Guarda los datos del viaje ingresados por el usuario en la base de datos.
	 */
	private void guardarDatos() {

		Viaje camposViaje = new Viaje();

		// Obtener valores de los campos
		String descripcion = textAreaDescrip.getText().trim();
		String servNoInclu = textAreaServNoIncl.getText().trim();
		String pais = (String) comboBoxPais.getSelectedItem();
		String nombreViaje = textFieldNombreViaje.getText().trim();

		String tipoViaje = (String) comboBoxTipoViaje.getSelectedItem();
		switch (comboBoxTipoViaje.getSelectedIndex()) {
		case 0:
			tipoViaje = String.valueOf(controlador.buscarLunaMiel());
			break;
		case 1:
			tipoViaje = String.valueOf(controlador.buscarSenior());
			break;
		case 2:
			tipoViaje = String.valueOf(controlador.buscarGrupos());
			break;
		case 3:
			tipoViaje = String.valueOf(controlador.buscarViajesGrandes());
			break;
		case 4:
			tipoViaje = String.valueOf(controlador.buscarEscapadas());
			break;
		case 5:
			tipoViaje = String.valueOf(controlador.buscarFamilias());
			break;
		}

		// Validar campos obligatorios
		if (nombreViaje.isEmpty() || descripcion.isEmpty() || servNoInclu.isEmpty()) {
			JOptionPane.showMessageDialog(panelNuevoViaje, "Por favor, complete todos los campos obligatorios.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Obtener fechas desde los DatePickers (con conversión correcta de
		// GregorianCalendar a Date)
		calendarInicio = (GregorianCalendar) datePickerInicio.getModel().getValue();
		calendarFin = (GregorianCalendar) datePickerFin.getModel().getValue();

		fechaInicio = (calendarInicio != null) ? new Date(calendarInicio.getTimeInMillis()) : null;
		fechaFin = (calendarFin != null) ? new Date(calendarFin.getTimeInMillis()) : null;

		// Validar fechas
		if (fechaInicio == null || fechaFin == null || fechaInicio.after(fechaFin)) {
			JOptionPane.showMessageDialog(panelNuevoViaje, "Las fechas ingresadas no son válidas.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Convertir la cantidad de días a entero de forma segura
		int numDias;
		try {
			numDias = Integer.parseInt(lblCantidadDias.getText().trim());
			if (numDias <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(panelNuevoViaje, "Número de días inválido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Asignar valores al objeto Viaje
		camposViaje.setNom_viaje(nombreViaje);
		camposViaje.setId_tipo_viaje(tipoViaje);
		camposViaje.setFec_ini(fechaInicio);
		camposViaje.setFec_fin(fechaFin);
		camposViaje.setDuracion(numDias);
		camposViaje.setPais(pais);
		camposViaje.setDesc_viaje(descripcion);
		camposViaje.setServ_no_inc(servNoInclu);
		camposViaje.setId_agencia(this.idAgencia);

		// Intentar guardar en la base de datos
		try {
			controlador.guardarViaje(camposViaje);
			JOptionPane.showMessageDialog(panelNuevoViaje, "Datos guardados correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			resetearCampos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(panelNuevoViaje, "Error al guardar los datos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Actualiza la cantidad de días del viaje en función de las fechas de inicio y
	 * fin seleccionadas.
	 */
	private void actualizarCantidadDias() {
		calendarInicio = (GregorianCalendar) datePickerInicio.getModel().getValue();
		calendarFin = (GregorianCalendar) datePickerFin.getModel().getValue();

		// Verificar que ambas fechas no sean nulas antes de operar
		if (calendarInicio != null && calendarFin != null) {
			fechaInicio = (calendarInicio != null) ? new Date(calendarInicio.getTimeInMillis()) : null;
			fechaFin = (calendarFin != null) ? new Date(calendarFin.getTimeInMillis()) : null;

			long diff = fechaFin.getTime() - fechaInicio.getTime();
			long dias = diff / (1000 * 60 * 60 * 24); // Convertir milisegundos a días

			lblCantidadDias.setText(String.valueOf(dias > 0 ? dias : 0));
		} else {
			lblCantidadDias.setText("0");
		}
	}

	/**
	 * Valida los campos obligatorios antes de guardar el viaje.
	 * 
	 * @param textFieldNombre     Campo de texto del nombre del viaje.
	 * @param datePickerInicio    Selector de fecha de inicio.
	 * @param datePickerFin       Selector de fecha de fin.
	 * @param textAreaDescripcion Área de texto para la descripción del viaje.
	 * @param textAreaServicios   Área de texto para los servicios no incluidos.
	 * @return true si los campos son válidos, false en caso contrario.
	 */
	private boolean validarCampos(JTextField textFieldNombre, JDatePickerImpl datePickerInicio,
			JDatePickerImpl datePickerFin, JTextArea textAreaDescripcion, JTextArea textAreaServicios) {

		String nombreViaje = textFieldNombreViaje.getText().trim();
		calendarInicio = (GregorianCalendar) datePickerInicio.getModel().getValue();
		calendarFin = (GregorianCalendar) datePickerFin.getModel().getValue();
		fechaInicio = (calendarInicio != null) ? new Date(calendarInicio.getTimeInMillis()) : null;
		fechaFin = (calendarFin != null) ? new Date(calendarFin.getTimeInMillis()) : null;

		if (nombreViaje.isEmpty() || fechaInicio == null || fechaFin == null) {
			return false;
		}

		Date fechaActual = new Date(System.currentTimeMillis());
		if (fechaInicio.before(fechaActual) || fechaFin.before(fechaInicio)) {
			return false;
		}

		// Validar que el nombre del viaje no esté vacío
		if (textFieldNombre.getText().trim().isEmpty()) {
			return false;
		}

		// Validar que la fecha de inicio no sea nula y sea mayor o igual a la fecha
		// actual
		if (fechaInicio == null || fechaInicio.before(fechaActual)) {
			return false;
		}

		// Validar que la fecha de fin sea posterior a la fecha de inicio
		if (fechaFin == null || !fechaFin.after(fechaInicio)) {
			return false;
		}

		// Validar que la descripción no esté vacía
		if (textAreaDescripcion.getText().trim().isEmpty()) {
			return false;
		}

		// Validar que los servicios no incluidos no estén vacíos
		if (textAreaServicios.getText().trim().isEmpty()) {
			return false;
		}

		// Todos los campos son válidos
		return true;
	}

	/**
	 * Carga el color del panel según el identificador de la agencia.
	 * 
	 * @param idAgencia Identificador de la agencia de viajes.
	 */
	public void cargarColorPanel(int idAgencia) {

		ArrayList<Agencia> agencias = controlador.buscarAgenciasPorId();

		for (Agencia agenciaLogueada : agencias) {
			if (idAgencia == agenciaLogueada.getId_agencia()) {
				String colorHex = agenciaLogueada.getColor();
				if (colorHex != null) {
					panelNuevoViaje.setBackground(Color.decode(colorHex));
				} else {
					panelNuevoViaje.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	/**
	 * Carga el logo de la agencia de viajes en el panel.
	 * 
	 * @param idAgencia Identificador de la agencia de viajes.
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
	 * Restablece los campos del formulario a sus valores predeterminados.
	 */
	private void resetearCampos() {

		textFieldNombreViaje.setText("");
		textAreaDescrip.setText("");
		textAreaServNoIncl.setText("");
		datePickerInicio.getModel().setValue(null);
		datePickerFin.getModel().setValue(null);
		comboBoxPais.setSelectedIndex(0);
		lblCantidadDias.setText("0");
	}

	/**
	 * Obtiene el panel principal de la interfaz de nuevo viaje.
	 * 
	 * @return JPanel correspondiente a la creación de un nuevo viaje.
	 */
	private void volverAInicio() {
		panelNuevoViaje.setVisible(false);

	}

	public JPanel getPanel() {

		return panelNuevoViaje;
	}
}
