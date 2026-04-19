package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class VentanaOficial extends JFrame {
	public VentanaOficial(String titulo) {
		super(titulo);
		configInicial();
		inicializarComponentes();
	}

	private void configInicial() {
		// ventana se cierra con la X
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// AbsoluteLayout (ponemos los componentes donde nos dé la gana)
		getContentPane().setLayout(null);

		// tamaño de la ventana
		setSize(960, 540);
	}

	private void inicializarComponentes() {
		// Panel horizontal
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		getContentPane().add(pnlBarraHorizontal);
		pnlBarraHorizontal.setLayout(null);

		// Boton Cerrar Sesion
		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
				vLogin.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(793, 68, 129, 30);
		getContentPane().add(btnCerrarSesion);

		// Titulo Página
		JLabel lblTitulo = new JLabel("Menú Oficial");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 298, 40);
		getContentPane().add(lblTitulo);

		// BOTONES
		JButton btnCitas = new JButton("Citas");
		btnCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionCitas vGestionCitas = new VentanaGestionCitas("Gestion de citas");
				vGestionCitas.setVisible(true);
				dispose();
			}
		});
		ImageIcon iconoCitas = new ImageIcon("C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\citas.png");
		java.awt.Image imgCitas = iconoCitas.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		btnCitas.setIcon(new ImageIcon(imgCitas));
		// Colocamos el texto abajo
		btnCitas.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCitas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCitas.setBackground(new Color(165, 191, 201));
		btnCitas.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCitas.setBounds(417, 96, 110, 90);
		pnlBarraHorizontal.add(btnCitas);
		
		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon(
				"C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\fondo.jpeg"));
	}
}
