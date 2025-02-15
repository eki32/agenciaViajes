package agenciaViajes.vista.paneles;

import javax.swing.*;

import agenciaViajes.bbdd.controlador.Controlador;
import agenciaViajes.bbdd.entidad.Agencia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que representa el panel para crear un nuevo perfil de agencia. Permite
 * a los usuarios ingresar información sobre la agencia, como nombre,
 * contraseña, color de marca, logo, número de empleados y tipo de agencia.
 */
public class PanelNuevoPerfil {

	private JPanel panelNuevoPerfil;
	private JTextField textFieldNombreAgencia;
	private JTextField textFieldColorHexadecimal;
	private JTextField textFieldURLLogoAgencia;
	private JPanel panelColor;
	private JComboBox<String> comboBoxNumEmpleados;// Si no pongo lo de string me sale subrayado en amarillo
	private JComboBox<String> comboBoxTipoAgencia;
	private JPasswordField passwordField;
	private String nombreAgencia;
	private String passwordUsuario;
	private String colorMarca;
	private String logo;
	Controlador controlador = new Controlador();

	/**
	 * Constructor del panel.
	 */
	public PanelNuevoPerfil(ArrayList<JPanel> paneles) {

		panelNuevoPerfil = new JPanel();
		panelNuevoPerfil.setBounds(0, 0, 1039, 666);
		panelNuevoPerfil.setBackground(Color.LIGHT_GRAY);
		panelNuevoPerfil.setLayout(null);

		JLabel lblNuevoPerfil = new JLabel("NUEVO PERFIL");
		lblNuevoPerfil.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNuevoPerfil.setBounds(50, 42, 234, 32);
		panelNuevoPerfil.add(lblNuevoPerfil);

		JLabel lblPassword = new JLabel("CONTRASEÑA");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPassword.setBounds(50, 187, 122, 20);
		panelNuevoPerfil.add(lblPassword);

		JLabel lblNombreAgencia = new JLabel("NOMBRE AGENCIA");
		lblNombreAgencia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombreAgencia.setBounds(50, 137, 122, 20);
		panelNuevoPerfil.add(lblNombreAgencia);

		JLabel lblColorMarca = new JLabel("COLOR DE MARCA");
		lblColorMarca.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblColorMarca.setBounds(50, 241, 122, 20);
		panelNuevoPerfil.add(lblColorMarca);

		JLabel lblNmeroDeEmpleados = new JLabel("NUMERO DE EMPLEADOS");
		lblNmeroDeEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNmeroDeEmpleados.setBounds(50, 301, 155, 20);
		panelNuevoPerfil.add(lblNmeroDeEmpleados);

		JLabel lblLogo = new JLabel("LOGO");
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLogo.setBounds(53, 417, 122, 20);
		panelNuevoPerfil.add(lblLogo);

		JLabel lblTipoDeAgencia = new JLabel("TIPO DE AGENCIA");
		lblTipoDeAgencia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoDeAgencia.setBounds(50, 364, 122, 20);
		panelNuevoPerfil.add(lblTipoDeAgencia);

		// Nombre Agencia
		textFieldNombreAgencia = new JTextField();
		textFieldNombreAgencia.setBounds(244, 137, 203, 20);
		panelNuevoPerfil.add(textFieldNombreAgencia);
		textFieldNombreAgencia.setColumns(10);

		// TextField color hexadecimal
		textFieldColorHexadecimal = new JTextField();
		textFieldColorHexadecimal.setColumns(10);
		textFieldColorHexadecimal.setBounds(333, 241, 59, 20);
		panelNuevoPerfil.add(textFieldColorHexadecimal);

		// Panel de color que se actualiza al meter un intro en el textfield
		panelColor = new JPanel();
		panelColor.setBounds(409, 241, 38, 20);
		panelNuevoPerfil.add(panelColor);

		textFieldColorHexadecimal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					actualizarColorPanel();
				}
			}
		});

		// Número de empleados
		comboBoxNumEmpleados = new JComboBox<>();
		comboBoxNumEmpleados.setModel(new DefaultComboBoxModel<>(
				new String[] { "Entre 2 y 10 empleados", "Entre 10 y 100 empleados", "Entre 100 y 1000 empleados" }));
		comboBoxNumEmpleados.setBounds(244, 301, 203, 22);
		panelNuevoPerfil.add(comboBoxNumEmpleados);

		// Tipo de agencia
		comboBoxTipoAgencia = new JComboBox<>();
		comboBoxTipoAgencia
				.setModel(new DefaultComboBoxModel<>(new String[] { "Mayorista", "Minorista", "Mayorista-Minorista" }));
		comboBoxTipoAgencia.setBounds(244, 364, 203, 22);
		panelNuevoPerfil.add(comboBoxTipoAgencia);

		// URL del logo
		textFieldURLLogoAgencia = new JTextField();
		textFieldURLLogoAgencia.setColumns(10);
		textFieldURLLogoAgencia.setBounds(244, 417, 203, 20);
		panelNuevoPerfil.add(textFieldURLLogoAgencia);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				guardarDatos();
				paneles.get(9).setVisible(false);
				paneles.get(1).setVisible(true);
			}
		});
		btnGuardar.setBounds(275, 568, 117, 29);
		panelNuevoPerfil.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				resetearCampos();

				paneles.get(9).setVisible(false);
				paneles.get(1).setVisible(true);
			}
		});
		btnCancelar.setBounds(538, 568, 117, 29);
		panelNuevoPerfil.add(btnCancelar);

		passwordField = new JPasswordField();
		passwordField.setBounds(244, 184, 203, 20);
		panelNuevoPerfil.add(passwordField);

	}

	/**
	 * Actualiza el color de fondo del panel de color según el código hexadecimal
	 * ingresado.
	 */
	private void actualizarColorPanel() {
		String hexColor = textFieldColorHexadecimal.getText();

		if (!validarColorHexadecimal(hexColor)) {
			JOptionPane.showMessageDialog(panelNuevoPerfil, "Código hexadecimal inválido. Debe ser #RRGGBB o #RGB",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Color color = Color.decode(hexColor);
			panelColor.setBackground(color);
			panelColor.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(panelNuevoPerfil, "Código hexadecimal inválido.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	 * Guarda los datos ingresados en el formulario
	 */
	private void guardarDatos() {

		Agencia nuevaAgencia = new Agencia();

		nombreAgencia = textFieldNombreAgencia.getText();
		passwordUsuario = passwordField.getText();
		colorMarca = textFieldColorHexadecimal.getText();
		logo = textFieldURLLogoAgencia.getText();

		String idTipoAgencia = null;
		String cod_empleado = null;

		if (comboBoxTipoAgencia.getSelectedIndex() == 0) {
			idTipoAgencia = controlador.buscarID_Mayorista();
			// TipoAgencia = "Mayorista";
		}

		if (comboBoxTipoAgencia.getSelectedIndex() == 1) {
			idTipoAgencia = controlador.buscarID_Minorista();
			// TipoAgencia = "Minorista";
		}

		if (comboBoxTipoAgencia.getSelectedIndex() == 2) {
			idTipoAgencia = controlador.buscarID_MayoMino();
			// TipoAgencia = "Mayorista-minorista";
		}
		if (comboBoxNumEmpleados.getSelectedIndex() == 0) {
			cod_empleado = controlador.buscarID_2_10();
			// numeroEmpleados = "Entre 2 y 10 empleados";
		}

		if (comboBoxNumEmpleados.getSelectedIndex() == 1) {
			cod_empleado = controlador.buscarID_10_100();
			// numeroEmpleados = "Entre 10 y 100 empleados";
		}

		if (comboBoxNumEmpleados.getSelectedIndex() == 2) {
			cod_empleado = controlador.buscarID_100_1000();
			// numeroEmpleados = "Entre 100 y 1000 empleados";
		}

		// Mostrar los datos en la consola (solo si los campos son válidos)
		nuevaAgencia.setNom_agencia(nombreAgencia);
		nuevaAgencia.setPassword(passwordUsuario);
		nuevaAgencia.setColor(colorMarca);
		nuevaAgencia.setLogo(logo);
		nuevaAgencia.setId_tipo_agen(idTipoAgencia);
		nuevaAgencia.setCod_emp(cod_empleado);

		controlador.guardarAgencia(nuevaAgencia);

		JOptionPane.showMessageDialog(panelNuevoPerfil, "Datos guardados correctamente.", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);

		resetearCampos();
	}

	/**
	 * Valida si los campos de nombre, color y URL son válidos.
	 *
	 * @param nombre El nombre de la agencia.
	 * @param color  El código de color hexadecimal.
	 * @param url    La URL del logo.
	 * @return {@code true} si todos los campos son válidos, {@code false} en caso
	 *         contrario.
	 */
	private boolean validarCampos(String nombre, String color, String url) {
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(panelNuevoPerfil, "El nombre de la agencia es obligatorio.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!validarColorHexadecimal(color)) {
			JOptionPane.showMessageDialog(panelNuevoPerfil, "El color hexadecimal es inválido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!validarURL(url)) {
			JOptionPane.showMessageDialog(panelNuevoPerfil, "La URL del logo es inválida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Valida si el código de color ingresado es hexadecimal válido.
	 * 
	 * @param color El código de color a validar.
	 * @return {@code true} si el código de color es válido, {@code false} en caso
	 *         contrario.
	 */
	private boolean validarColorHexadecimal(String color) {
		return color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
	}

	/**
	 * Valida si la URL ingresada es válida.
	 * 
	 * @param url La URL a validar.
	 * @return {@code true} si la URL es válida, {@code false} en caso contrario.
	 */
	private boolean validarURL(String url) {
		String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

	/**
	 * Restablece los valores de los campos de entrada a sus valores
	 * predeterminados.
	 */
	private void resetearCampos() {

		textFieldNombreAgencia.setText("");
		passwordField.setText("");
		textFieldColorHexadecimal.setText("");
		textFieldURLLogoAgencia.setText("");
		comboBoxNumEmpleados.setSelectedIndex(0);
		comboBoxTipoAgencia.setSelectedIndex(0);
		panelColor.setBackground(null);
	}

	/**
	 * Devuelve el panel.
	 *
	 * @return panelNuevoPerfil.
	 */
	public JPanel getPanel() {
		return panelNuevoPerfil;
	}
}
