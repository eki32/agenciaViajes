package agenciaViajes.vista.paneles;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import agenciaViajes.bbdd.controlador.Controlador;
import agenciaViajes.bbdd.entidad.Agencia;
import agenciaViajes.bbdd.entidad.Pais;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * Clase que representa el panel de login para las agencias de viajes. Permite a
 * las agencias ingresar con su nombre de usuario y contraseña, y también
 * proporciona la opción de registrar una nueva agencia.
 */
public class PanelLogin {

	private JPanel panelLogin;
	private JTextField tfNomAgencia;
	// private JTextField tfContraseña;
	private JPasswordField tfContraseña;
	private Window window;
	Scanner teclado = new Scanner(System.in);

	/**
	 * Constructor de la clase PanelLogin.
	 *
	 * @param window La ventana principal de la aplicación.
	 */
	public PanelLogin(Window window) {

		this.window = window;
		panelLogin = new JPanel();
		panelLogin.setBounds(0, 0, 1039, 666);
		panelLogin.setBackground(Color.LIGHT_GRAY);
		panelLogin.setLayout(null);

		JLabel lblNomAgencia = new JLabel("NOMBRE AGENCIA");
		lblNomAgencia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNomAgencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomAgencia.setBounds(333, 177, 114, 16);
		panelLogin.add(lblNomAgencia);

		JLabel lblContraseña = new JLabel("CONTRASEÑA");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseña.setBounds(333, 257, 87, 16);
		panelLogin.add(lblContraseña);

		tfNomAgencia = new JTextField();
		tfNomAgencia.setBounds(560, 172, 130, 26);
		panelLogin.add(tfNomAgencia);
		tfNomAgencia.setColumns(10);

		tfContraseña = new JPasswordField();
		tfContraseña.setColumns(10);
		tfContraseña.setBounds(560, 252, 130, 26);
		panelLogin.add(tfContraseña);

		JButton btnIniSesion = new JButton("INICIAR SESION");
		btnIniSesion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnIniSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				login();
			}

		});
		btnIniSesion.setBounds(336, 454, 141, 29);
		panelLogin.add(btnIniSesion);

		JButton btnNuevaAgencia = new JButton("NUEVA AGENCIA");
		btnNuevaAgencia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNuevaAgencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelLogin.setVisible(false);
				window.paneles.get(9).setVisible(true);
			}
		});
		btnNuevaAgencia.setBounds(578, 454, 148, 29);
		panelLogin.add(btnNuevaAgencia);
	}

	/**
	 * Realiza el proceso de login de la agencia. Verifica las credenciales
	 * ingresadas con la base de datos y, si son correctas, muestra el panel
	 * principal de la aplicación.
	 */
	private void login() {

		Controlador controlador = new Controlador();
		ArrayList<Agencia> agencias = controlador.buscarAgencias();
		boolean loginExitoso = false;

		if (agencias != null && !agencias.isEmpty()) {
			for (Agencia agenciaLogueada : agencias) {
				System.out.println("Agencia en BD: " + agenciaLogueada.getNom_agencia() + " | ID: "
						+ agenciaLogueada.getId_agencia());

				if (tfNomAgencia.getText().equals(agenciaLogueada.getNom_agencia())
						&& tfContraseña.getText().equals(agenciaLogueada.getPassword())) {

					loginExitoso = true;
					int idAgencia = agenciaLogueada.getId_agencia();
					System.out.println("ID de Agencia Logueada (de BD): " + idAgencia);

					// Si el ID sigue siendo 0, error en la base
					if (idAgencia == 0) {
						System.out.println("⚠ ERROR: ID de Agencia sigue siendo 0. Verificar la base de datos.");
					}

					// Actualizar ID de agencia en los paneles
					window.panelViajesEventos.setIdAgencia(idAgencia);
					window.panelNuevoViaje.setIdAgencia(idAgencia);
					window.panelEleccionEvento.setIdAgencia(idAgencia, -1);
					window.panelEleccionTrayecto.setIdAgencia(idAgencia);
					window.panelEleccionIda.setIdAgencia(idAgencia, -1);
					window.panelIdaVuelta.setIdAgencia(idAgencia, -1);
					window.panelActividad.setIdAgencia(idAgencia, -1);
					window.panelHabitacionTipo.setIdAgencia(idAgencia, -1);

					panelLogin.setVisible(false);
					window.paneles.get(2).setVisible(true);
					window.panelViajesEventos.getPanel().repaint();

					break;
				}
			}
		}

		if (!loginExitoso) {
			JOptionPane.showMessageDialog(panelLogin, "No se encontró la agencia para este usuario.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		limpiarCampos();
	}

	/**
	 * Limpia los campos del login.
	 */
	private void limpiarCampos() {
		// Limpiar los campos de texto
		tfNomAgencia.setText("");
		tfContraseña.setText("");

	}

	/**
	 * Devuelve el panel.
	 * 
	 * @return panel.
	 */
	public JPanel getPanel() {

		return panelLogin;
	}

}