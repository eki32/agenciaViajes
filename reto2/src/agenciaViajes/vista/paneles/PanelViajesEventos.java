package agenciaViajes.vista.paneles;

import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;

import agenciaViajes.bbdd.controlador.Controlador;

import agenciaViajes.bbdd.entidad.Agencia;
import agenciaViajes.bbdd.entidad.Evento;
import agenciaViajes.bbdd.entidad.Viaje;
import agenciaViajes.vista.ficheros.GestorFicheros;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

/**
 * Clase que representa el panel principal de la aplicación, donde se muestran
 * los viajes y eventos asociados a una agencia de viajes. Permite crear nuevos
 * viajes, seleccionar viajes existentes para ver sus detalles, generar ofertas
 * para clientes, desconectarse de la sesión actual y eliminar viajes o eventos.
 */
public class PanelViajesEventos {

	private JPanel panelViajesEventos = null;
	private JTable tablaViajes;
	private JTable tablaEventos;
	private JButton btnNuevoViaje;
	private JButton btnSeleccionar;
	private JButton btnGenerarAlerta;
	private JButton btnDesconectar;
	public JLabel lblLogo;
	public DefaultTableModel modeloViajes;
	public DefaultTableModel modeloEventos;
	private Window ventana;
	private GregorianCalendar calendarInicio;
	private Controlador controlador = new Controlador();
	private ArrayList<JPanel> paneles;
	public int idAgencia;
	private int idEvento;
	protected int idViaje;

	/**
	 * Establece el ID de la agencia y actualiza la interfaz gráfica con la
	 * información correspondiente a esa agencia.
	 *
	 * @param idAgencia El ID de la agencia de viajes.
	 */
	public void setIdAgencia(int idAgencia) {
		if (idAgencia > 0) { // Solo actualiza si es válido
			this.idAgencia = idAgencia;
			System.out.println("🛠️Seteando ID Agencia en PanelViajesEventos: " + idAgencia);// visualizar consola
			cargarColorPanel(idAgencia);
			cargarLogo(idAgencia);
			actualizarTablaViajes(modeloViajes, idAgencia);
			panelViajesEventos.repaint();// Forzar actualización
		}
	}

	/**
	 * Constructor de la clase PanelViajesEventos.
	 *
	 * @param paneles   Lista de paneles de la aplicación.
	 * @param idAgencia ID de la agencia de viajes.
	 * @param ventana   Ventana principal de la aplicación.
	 */
	public PanelViajesEventos(ArrayList<JPanel> paneles, int idAgencia, Window ventana) {

		this.ventana = ventana; // Guardar referencia a Window
		this.paneles = paneles;
		this.idAgencia = idAgencia;
		this.panelViajesEventos = new JPanel();
		this.panelViajesEventos.setBounds(0, 0, 1039, 666);
		this.panelViajesEventos.setLayout(null);

		lblLogo = new JLabel("Cargando logo...");
		lblLogo.setBounds(809, 0, 230, 128);
		panelViajesEventos.add(lblLogo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(95, 56, 596, 189);
		panelViajesEventos.add(scrollPane);
		// Modelo de tabla con columna oculta para id_viaje
		modeloViajes = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modeloViajes.addColumn("ID Viaje"); // Columna oculta
		modeloViajes.addColumn("Viaje");
		modeloViajes.addColumn("Tipo");
		modeloViajes.addColumn("Fecha Inicio");
		modeloViajes.addColumn("Fecha Fin");
		modeloViajes.addColumn("Nº Días");
		modeloViajes.addColumn("País");

		tablaViajes = new JTable(modeloViajes);
		tablaViajes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION); // Permite seleccionar solo una
																						// fila
		// Agregar MouseListener para detectar clic en una fila y actualizar la tabla de
		// eventos
		tablaViajes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tablaViajes.getSelectedRow() != -1) {
					int selectedRow = tablaViajes.getSelectedRow();
					int idViaje = (int) modeloViajes.getValueAt(selectedRow, 0);
					System.out.println("ID Viaje seleccionado: " + idViaje);

					// Actualizar la tabla de eventos con el nuevo idViaje
					actualizarTablaEventos(modeloEventos, idViaje);
				}
			}
		});

		// Ocultar la columna del ID para que no sea visible en la tabla
		tablaViajes.getColumnModel().getColumn(0).setMinWidth(0);
		tablaViajes.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaViajes.getColumnModel().getColumn(0).setWidth(0);

		scrollPane.add(tablaViajes);
		scrollPane.setViewportView(tablaViajes);

		actualizarTablaViajes(modeloViajes, idAgencia);

		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setEnabled(false);
		scrollPane_11.setBounds(95, 346, 596, 189);
		panelViajesEventos.add(scrollPane_11);
		modeloEventos = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloEventos.addColumn("ID Evento");
		modeloEventos.addColumn("Nombre Evento");
		modeloEventos.addColumn("Tipo");
		modeloEventos.addColumn("Fecha");
		modeloEventos.addColumn("Precio");
		tablaEventos = new JTable(modeloEventos);
		tablaEventos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION); // Permite seleccionar solo una
		// fila
		tablaEventos.getColumnModel().getColumn(0).setMinWidth(0);
		tablaEventos.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaEventos.getColumnModel().getColumn(0).setWidth(0);
		scrollPane_11.add(tablaEventos);
		scrollPane_11.setViewportView(tablaEventos);

		btnNuevoViaje = new JButton("NUEVO VIAJE");
		btnNuevoViaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNuevoViaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paneles.get(2).setVisible(false);
				paneles.get(10).setVisible(true);

			}

		});

		btnNuevoViaje.setBounds(28, 592, 185, 23);
		panelViajesEventos.add(btnNuevoViaje);

		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSeleccionar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (tablaViajes.getSelectedRow() != -1) {
					int selectedRow = tablaViajes.getSelectedRow();
					int idViaje = (int) modeloViajes.getValueAt(selectedRow, 0);

					System.out.println("ID Viaje seleccionado: " + PanelViajesEventos.this.idViaje);

					// Pasar el idViaje correctamente
					ventana.panelEleccionEvento.setIdAgencia(idAgencia, idViaje);
					ventana.panelEleccionIda.setIdAgencia(idAgencia, idViaje);
					ventana.panelIdaVuelta.setIdAgencia(idAgencia, idViaje);
					ventana.panelHabitacionTipo.setIdAgencia(idAgencia, idViaje);
					ventana.panelActividad.setIdAgencia(idAgencia, idViaje);

					panelViajesEventos.setVisible(false);
					ventana.panelEleccionEvento.getPanel().setVisible(true);
				}
			}
		});

		btnSeleccionar.setBounds(210, 276, 185, 23);
		panelViajesEventos.add(btnSeleccionar);

		btnGenerarAlerta = new JButton("GENERAR OFERTA CLIENTE");
		btnGenerarAlerta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGenerarAlerta.addActionListener(new ActionListener() {
			private int idViaje;

			public void actionPerformed(ActionEvent e) {

				generarOfertaCliente();

			}

			private void generarOfertaCliente() {
				// Verificar si hay una fila seleccionada en la tabla de viajes
				int selectedRowViaje = tablaViajes.getSelectedRow();

				if (selectedRowViaje == -1) {
					JOptionPane.showMessageDialog(panelViajesEventos, "Por favor, selecciona un viaje.",
							"Selección requerida", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtener el ID del viaje seleccionado (asumiendo que está en la primera
				// columna)
				int idViaje = (int) modeloViajes.getValueAt(selectedRowViaje, 0);

				// Llamar al método generarOferta de Evento para obtener la información del
				// viaje
				String contenidoOferta = controlador.generarOferta(idViaje);

				// Crear un nombre de archivo único con timestamp
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
				String timestamp = now.format(formatter);
				String nombreFichero = "Oferta_" + idViaje + "_" + timestamp + ".txt";

				// Configurar JFileChooser para seleccionar la ubicación de guardado
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Guardar Oferta Cliente");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
				fileChooser.setFileFilter(filter);
				fileChooser.setSelectedFile(new File(nombreFichero));

				int userSelection = fileChooser.showSaveDialog(panelViajesEventos);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					String filePath = fileToSave.getAbsolutePath();
					if (!filePath.toLowerCase().endsWith(".txt")) {
						filePath += ".txt";
					}

					// Guardar el archivo usando GestorFicheros
					GestorFicheros gestorFicheros = new GestorFicheros();
					gestorFicheros.guardarFichero(filePath, contenidoOferta);

					// Mostrar el contenido del archivo en la consola

					gestorFicheros.leerTodoFichero();
					System.out.println(gestorFicheros);

					JOptionPane.showMessageDialog(panelViajesEventos, "Oferta guardada en: " + filePath,
							"Oferta Guardada", JOptionPane.INFORMATION_MESSAGE);
				} else {
					System.out.println("Guardado cancelado por el usuario.");
				}
			}

		});

		btnGenerarAlerta.setBounds(611, 592, 185, 23);
		panelViajesEventos.add(btnGenerarAlerta);
		btnDesconectar = new JButton("DESCONECTAR");
		btnDesconectar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDesconectar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				paneles.get(2).setVisible(false);
				JOptionPane.showMessageDialog(panelViajesEventos, "Se ha desconectado de la sesión", "DESCONEXIÓN",
						JOptionPane.INFORMATION_MESSAGE);
				paneles.get(0).setVisible(true);

			}

		});

		btnDesconectar.setBounds(844, 592, 185, 23);
		panelViajesEventos.add(btnDesconectar);

		JButton btnBorrarViaje = new JButton("BORRAR VIAJE");
		btnBorrarViaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tablaViajes.getSelectedRow() != -1) {
					int filaSeleccionada = tablaViajes.getSelectedRow();
					// Obtener el ID del viaje (suponiendo que está en la primera columna)
					int idViaje = (int) tablaViajes.getValueAt(filaSeleccionada, 0);

					int confirmacion = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de eliminar el viaje seleccionado?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirmacion == JOptionPane.YES_OPTION) {
						boolean eliminado = controlador.eliminarViaje(idViaje);

						if (eliminado) {
							JOptionPane.showMessageDialog(null, "Viaje eliminado con éxito.");
							((DefaultTableModel) tablaViajes.getModel()).removeRow(filaSeleccionada);
						} else {
							JOptionPane.showMessageDialog(null, "Error al eliminar el viaje.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un viaje para borrar.");
				}
				actualizarTablaViajes(modeloEventos, PanelViajesEventos.this.idAgencia);
			}
		});
		btnBorrarViaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBorrarViaje.setBounds(453, 276, 185, 23);
		panelViajesEventos.add(btnBorrarViaje);

		JButton btnBorrarEvento = new JButton("BORRAR EVENTO");
		btnBorrarEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBorrarEvento.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				borrarEvento();
				actualizarTablaEventos(modeloEventos, PanelViajesEventos.this.idEvento);
			}
		});
		btnBorrarEvento.setBounds(319, 591, 217, 23);
		panelViajesEventos.add(btnBorrarEvento);

	}

	/**
	 * Carga el logo de la agencia desde la base de datos y lo muestra en el panel.
	 * Si no se encuentra el logo o hay un error al cargarlo, se muestra un mensaje
	 * de error.
	 *
	 * @param idAgencia El ID de la agencia de la cual se cargará el logo.
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
					panelViajesEventos.revalidate();
					panelViajesEventos.repaint();

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
	 * Elimina un evento seleccionado de la tabla de eventos.
	 */
	protected void borrarEvento() {
		if (tablaEventos.getSelectedRow() != -1) {
			int filaSeleccion = tablaEventos.getSelectedRow();
			// Obtener el ID del viaje (suponiendo que está en la primera columna)
			this.idEvento = (int) modeloEventos.getValueAt(filaSeleccion, 0);
			System.out.println("ID Evento seleccionado: " + idEvento);

			int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el Evento seleccionado?",
					"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

			if (confirmacion == JOptionPane.YES_OPTION) {
				boolean eliminado = controlador.eliminarEvento(idEvento);

				if (eliminado) {
					JOptionPane.showMessageDialog(null, "Evento eliminado con éxito.");
					((DefaultTableModel) tablaEventos.getModel()).removeRow(filaSeleccion);
				} else {
					JOptionPane.showMessageDialog(null, "Error al eliminar el Evento.");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione un Evento para borrar.");
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
					panelViajesEventos.setBackground(Color.LIGHT_GRAY);
				} else {
					try {
						panelViajesEventos.setBackground(Color.decode(colorHex));
					} catch (NumberFormatException e) {
						panelViajesEventos.setBackground(Color.LIGHT_GRAY);
					}
				}
				panelViajesEventos.repaint();
				break;
			}
		}
	}

	/**
	 * Actualiza la tabla de viajes con los datos de la base de datos.
	 *
	 * @param modelo    Modelo de la tabla de viajes.
	 * @param idAgencia ID de la agencia de viajes.
	 */
	public void actualizarTablaViajes(DefaultTableModel modeloViajes, int idAgencia) {
		this.idAgencia = idAgencia;
		modeloViajes.setRowCount(0);
		System.out.println("id agencia en actualizarTabla " + this.idAgencia);
		// Obtener la lista de viajes
		ArrayList<Viaje> listaViajes = controlador.buscarViajes(PanelViajesEventos.this.idAgencia);

		// Comprobar si la lista de viajes es nula o está vacía
		if (listaViajes == null || listaViajes.isEmpty()) {
			JOptionPane.showMessageDialog(panelViajesEventos, "No se encontraron viajes.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return; // Salir si no hay viajes
		}

		// Si la lista no es nula ni vacía, agregar los viajes a la tabla
		for (Viaje viaje : listaViajes) {
			viaje.setTipo(controlador.buscarTipo()); // asignar un tipo
			modeloViajes.addRow(new Object[] { viaje.getId_viaje(), // ID oculto
					viaje.getNom_viaje(), viaje.getTipo(), viaje.getFec_ini(), viaje.getFec_fin(), viaje.getDuracion(),
					viaje.getPais() });
		}
	}

	/**
	 * Actualiza la tabla de eventos con los datos de la base de datos para un viaje
	 * específico.
	 *
	 * @param modelo  Modelo de la tabla de eventos.
	 * @param idViaje ID del viaje para el cual se obtendrán los eventos.
	 */
	public void actualizarTablaEventos(DefaultTableModel modeloEventos, int idViaje) {
		modeloEventos.setRowCount(0); // Limpiar la tabla antes de actualizar

		System.out.println("📆 Buscando eventos para ID Viaje: " + idViaje);

		ArrayList<Evento> listaEventos = controlador.buscarEventos(idViaje);

		if (listaEventos == null || listaEventos.isEmpty()) {
			System.out.println("⚠ No se encontraron eventos para este viaje.");
			return;
		}

		for (Evento evento : listaEventos) {
			modeloEventos.addRow(new Object[] { evento.getId_evento(), evento.getNom_evento(), evento.getTipo(),
					evento.getFec_ida(), evento.getPrecio() });
		}
	}

	/**
	 * Devuelve el idAgencia de este panel
	 *
	 * @return idAgencia en este panel
	 */
	public int getIdAgencia() {
		return this.idAgencia;
	}

	/**
	 * Devuelve el panel principal de esta vista.
	 *
	 * @return El panel principal de la vista de viajes y eventos.
	 */
	public JPanel getPanel() {
		return panelViajesEventos;

	}
}
