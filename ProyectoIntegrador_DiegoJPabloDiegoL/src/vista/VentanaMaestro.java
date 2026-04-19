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

import modelo.Empleado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMaestro extends JFrame {
	Empleado empleado = new Empleado();

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

		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Menú Maestro");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 298, 40);
		getContentPane().add(lblTitulo);

		// BOTONES
		JButton btnTalleres = new JButton("Talleres");
		btnTalleres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres("Gestion de talleres");
				vGestionTalleres.setVisible(true);
				dispose();
			}
		});
		ImageIcon iconoTalleres = new ImageIcon("C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\talleres.png");
		java.awt.Image imgTalleres = iconoTalleres.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		btnTalleres.setIcon(new ImageIcon(imgTalleres));
		// Colocamos el texto abajo
		btnTalleres.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTalleres.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTalleres.setBackground(new Color(165, 191, 201));
		btnTalleres.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTalleres.setBounds(360, 96, 110, 90);
		pnlBarraHorizontal.add(btnTalleres);

		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionClientes vGestionCilientes = new VentanaGestionClientes("Gestion de clientes");
				vGestionCilientes.setVisible(true);
				dispose();
			}
		});
		ImageIcon iconoClientes = new ImageIcon("C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\clientes.png");
		java.awt.Image imgClientes = iconoClientes.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		btnClientes.setIcon(new ImageIcon(imgClientes));
		// Colocamos el texto abajo
		btnClientes.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnClientes.setHorizontalTextPosition(SwingConstants.CENTER);
		btnClientes.setBackground(new Color(165, 191, 201));
		btnClientes.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnClientes.setBounds(480, 96, 110, 90);
		pnlBarraHorizontal.add(btnClientes);

		JButton btnCitas = new JButton("Citas");
		btnCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionCitas vGestionCitas = new VentanaGestionCitas("Gestion de citas");
				vGestionCitas.setVisible(true);
				dispose();
			}
		});
		ImageIcon iconoCitas = new ImageIcon("C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\citas.png");
		java.awt.Image imgCitas = iconoCitas.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); //Para que se autoescale y se coloque el tamaño correctamente
		btnCitas.setIcon(new ImageIcon(imgCitas));
		// Colocamos el texto abajo
		btnCitas.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCitas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCitas.setBackground(new Color(165, 191, 201));
		btnCitas.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCitas.setBounds(240, 96, 110, 90);
		pnlBarraHorizontal.add(btnCitas);
		
		JButton btnTrajes = new JButton("Trajes");
		btnTrajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionTrajes vGestionTrajes = new VentanaGestionTrajes("Gestion de citas");
				vGestionTrajes.setVisible(true);
				dispose();
			}
		});
		ImageIcon iconoTrajes = new ImageIcon("C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\trajes.png");
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
		lblFondo.setIcon(new ImageIcon(
				"C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\fondo.jpeg"));

	}
}
