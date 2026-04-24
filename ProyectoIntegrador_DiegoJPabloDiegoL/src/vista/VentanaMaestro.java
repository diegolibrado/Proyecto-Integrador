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

import controlador.ControladorMenuPpal;

public class VentanaMaestro extends JFrame {
	
	private JButton btnCitas;
	private JButton btnTrajes;
	private JButton btnTalleres;
	private JButton btnClientes;
	private String rangoUsuario; // Añadido para gestionar el rango si fuera necesario

	public VentanaMaestro(String titulo) {
		super(titulo);
		configInicial();
		inicializarComponentes();
	}

	private void configInicial() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(960, 540);
		setLocationRelativeTo(null);
	}

	private void inicializarComponentes() {

		// Instanciamos el controlador que centraliza las acciones
		ControladorMenuPpal controlador = new ControladorMenuPpal(this);
		
		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(new Color(72, 119, 109));
		pnlFooter.setBounds(0, 481, 944, 20);
		getContentPane().add(pnlFooter);

		// Copyright
		JLabel lblCopyright = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(new Color(255, 255, 255));
		lblCopyright.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblCopyright);

		// Panel horizontal principal
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		pnlBarraHorizontal.setLayout(null);
		getContentPane().add(pnlBarraHorizontal);

		// Boton Cerrar Sesion
		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
				controlador.ControladorLogin c = new controlador.ControladorLogin(vLogin);
				vLogin.setControlador(c);
				vLogin.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);
		
		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Menú Maestro");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 298, 40);
		getContentPane().add(lblTitulo);

		// --- BOTONES ---

		// 1. Citas
		btnCitas = new JButton("Citas");
		prepararBoton(btnCitas, "img\\citas.png", 240, 96, pnlBarraHorizontal, controlador);

		// 2. Talleres
		btnTalleres = new JButton("Talleres");
		prepararBoton(btnTalleres, "img\\talleres.png", 360, 96, pnlBarraHorizontal, controlador);

		// 3. Clientes
		btnClientes = new JButton("Clientes");
		prepararBoton(btnClientes, "img\\clientes.png", 480, 96, pnlBarraHorizontal, controlador);

		// 4. Trajes
		btnTrajes = new JButton("Trajes");
		prepararBoton(btnTrajes, "img\\trajes.png", 600, 96, pnlBarraHorizontal, controlador);

		// Fondo (Se añade al final para que quede por debajo)
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
		getContentPane().add(lblFondo);
	}

	/**
	 * Método auxiliar para configurar el estilo y posición de los botones del menú
	 */
	private void prepararBoton(JButton boton, String rutaImg, int x, int y, JPanel panel, ActionListener listener) {
		ImageIcon icono = new ImageIcon(rutaImg);
		if (new java.io.File(rutaImg).exists()) {
			java.awt.Image img = icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(img));
		}
		boton.setVerticalTextPosition(SwingConstants.BOTTOM);
		boton.setHorizontalTextPosition(SwingConstants.CENTER);
		boton.setBackground(new Color(165, 191, 201));
		boton.setFont(new Font("Verdana", Font.PLAIN, 14));
		boton.setBounds(x, y, 110, 90);
		boton.addActionListener(listener);
		panel.add(boton);
	}

	// --- GETTERS PARA EL CONTROLADOR ---

	public JButton getBtnCitas() {
		return btnCitas;
	}
	
	public JButton getBtnTrajes() {
		return btnTrajes;
	}

	public JButton getBtnTalleres() {
		return btnTalleres;
	}

	public JButton getBtnClientes() {
		return btnClientes;
	}

	public String getRangoUsuario() {
		return rangoUsuario;
	}
}
