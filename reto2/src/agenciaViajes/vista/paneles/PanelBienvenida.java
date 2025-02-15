package agenciaViajes.vista.paneles;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

/**
 * Clase que representa un panel de bienvenida, incluyendo un botón que permite
 * navegar a otra pantalla. Proporciona métodos para acceder al panel y
 * configurar su comportamiento.
 *
 * @author [Equipo 4]
 */
public class PanelBienvenida {

	/**
	 * Panel de bienvenida.
	 */
	private JPanel panelBienvenida = null;

	/**
	 * Constructor que crea el panel de bienvenida y configura su apariencia.
	 * Incluye un botón que cambia la visibilidad de otros paneles al ser
	 * presionado.
	 *
	 * @param paneles Lista de paneles que se pueden mostrar u ocultar.
	 */
	public PanelBienvenida(ArrayList<JPanel> paneles) {

		panelBienvenida = new JPanel();

		panelBienvenida.setBackground(Color.LIGHT_GRAY);
		panelBienvenida.setBounds(0, 0, 1039, 666);
		panelBienvenida.setLayout(null);

		JButton btnBienvenida = new JButton("BIENVENIDA");
		btnBienvenida.setBounds(277, 253, 471, 108);
		btnBienvenida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paneles.get(0).setVisible(false);// login
				paneles.get(1).setVisible(true);// login

			}
		});
		btnBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panelBienvenida.add(btnBienvenida);

	}

	/**
	 * Obtiene el panel de bienvenida.
	 *
	 * @return El panel de bienvenida.
	 */
	public JPanel getPanel() {

		return panelBienvenida;
	}
}