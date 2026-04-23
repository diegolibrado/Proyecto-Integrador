package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import controlador.ControladorMenuMaestro;
import modelo.Empleado;
import modelo.Modelo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMaestro extends JFrame {
	
	private JButton btnCitas;
	private JButton btnTrajes;
	private JButton btnTalleres;
	private JButton btnClientes;
	private JButton btnCerrarSesion;
	private String rangoUsuario;
	private int idUsuario;
	

	public VentanaMaestro(String titulo, String rango, int id) {
		super(titulo);
		rangoUsuario = rango;
		idUsuario = id;
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
		
		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(new Color(72, 119, 109));
		pnlFooter.setBounds(0, 481, 944, 20);
		getContentPane().add(pnlFooter);

		// Copyright
		JLabel lblNewLabel_1 = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblNewLabel_1);

		// Panel horizontal
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		getContentPane().add(pnlBarraHorizontal);
		pnlBarraHorizontal.setLayout(null);

		// Boton Cerrar Sesion
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);
		
		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Menú Maestro");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 298, 40);
		getContentPane().add(lblTitulo);

		// BOTONES
		btnTalleres = new JButton("Talleres");
		ImageIcon iconoTalleres = new ImageIcon("img\\talleres.png");
		java.awt.Image imgTalleres = iconoTalleres.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		btnTalleres.setIcon(new ImageIcon(imgTalleres));
		btnTalleres.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTalleres.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTalleres.setBackground(new Color(165, 191, 201));
		btnTalleres.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTalleres.setBounds(360, 96, 110, 90);
		pnlBarraHorizontal.add(btnTalleres);

		btnClientes = new JButton("Clientes");
		JButton btnClientes = new JButton("Clientes");
		ImageIcon iconoClientes = new ImageIcon("img\\clientes.png");
		java.awt.Image imgClientes = iconoClientes.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		btnClientes.setIcon(new ImageIcon(imgClientes));
		btnClientes.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnClientes.setHorizontalTextPosition(SwingConstants.CENTER);
		btnClientes.setBackground(new Color(165, 191, 201));
		btnClientes.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnClientes.setBounds(480, 96, 110, 90);
		pnlBarraHorizontal.add(btnClientes);

		btnCitas = new JButton("Citas");
		ImageIcon iconoCitas = new ImageIcon("img\\citas.png");
		java.awt.Image imgCitas = iconoCitas.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		btnCitas.setIcon(new ImageIcon(imgCitas));
		btnCitas.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCitas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCitas.setBackground(new Color(165, 191, 201));
		btnCitas.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCitas.setBounds(240, 96, 110, 90);
		pnlBarraHorizontal.add(btnCitas);

		btnTrajes = new JButton("Trajes");
		ImageIcon iconoTrajes = new ImageIcon("img\\trajes.png");
		java.awt.Image imgTrajes = iconoTrajes.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		btnTrajes.setIcon(new ImageIcon(imgTrajes));
		// Colocamos el texto abajo
		btnTrajes.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTrajes.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTrajes.setBackground(new Color(165, 191, 201));
		btnTrajes.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTrajes.setBounds(600, 96, 110, 90);
		pnlBarraHorizontal.add(btnTrajes);


		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}

	public void setControlador(ControladorMenuMaestro c) {
		btnCitas.addActionListener(c);
		btnTalleres.addActionListener(c);
		btnTrajes.addActionListener(c);
		btnClientes.addActionListener(c);
		btnCerrarSesion.addActionListener(c);
	}
	
	/**
	 * @return the btnCitas
	 */
	public JButton getBtnCitas() {
		return btnCitas;
	}
	
	/**
	 * @return the btnTrajes
	 */
	public JButton getBtnTrajes() {
		return btnTrajes;
	}

	/**
	 * @return the btnTalleres
	 */
	public JButton getBtnTalleres() {
		return btnTalleres;
	}

	/**
	 * @return the btnClientes
	 */
	public JButton getBtnClientes() {
		return btnClientes;
	}

	/**
	 * @return the btnCerrarSesion
	 */
	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}
}
