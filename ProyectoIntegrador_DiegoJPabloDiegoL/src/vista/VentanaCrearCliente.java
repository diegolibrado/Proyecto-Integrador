package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class VentanaCrearCliente extends JFrame {

	private String rangoUsuario;
	private JTextField txtIdCliente;
	private JTextField txtNombreCliente;
	private JTextField txtSuperpoder;
	private JTextField txtColores;
	private JButton btnGuardarCambios;

	public VentanaCrearCliente(String rango) {
		this.rangoUsuario = rango;
		inicializarComponentes();
		configInicial();
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
		JLabel lblCopyright = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(new Color(255, 255, 255));
		lblCopyright.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblCopyright);

		// Panel horizontal principal
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
		JLabel lblTitulo = new JLabel("Crear Cliente");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 333, 40);
		getContentPane().add(lblTitulo);

		// Botón Guardar Cambios
		btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnGuardarCambios.setBackground(new Color(165, 191, 201));
		btnGuardarCambios.setBounds(22, 231, 109, 30);
		pnlBarraHorizontal.add(btnGuardarCambios);

		// Botón Atrás
		JButton btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Redirige a la ventana de gestión en SINGULAR
				VentanaGestionCliente vGestionCliente = new VentanaGestionCliente(rangoUsuario);
				vGestionCliente.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setBounds(22, 11, 30, 30);
		getContentPane().add(btnAtras);

		// Panel Formulario
		JPanel pnlFormulario = new JPanel();
		pnlFormulario.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlFormulario.setLayout(null);
		pnlFormulario.setBackground(new Color(165, 191, 201));
		pnlFormulario.setBounds(139, 25, 782, 236);
		pnlBarraHorizontal.add(pnlFormulario);

		// 1. ID Cliente
		JLabel lblIdCliente = new JLabel("ID del Cliente:");
		lblIdCliente.setFont(new Font("Verdana", Font.BOLD, 14));
		lblIdCliente.setBounds(50, 20, 150, 30);
		pnlFormulario.add(lblIdCliente);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtIdCliente.setBounds(200, 20, 255, 30);
		pnlFormulario.add(txtIdCliente);

		// 2. Nombre
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombre.setBounds(50, 70, 150, 30);
		pnlFormulario.add(lblNombre);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreCliente.setBounds(200, 70, 255, 30);
		pnlFormulario.add(txtNombreCliente);

		// 3. Superpoder
		JLabel lblSuperpoder = new JLabel("Superpoder:");
		lblSuperpoder.setFont(new Font("Verdana", Font.BOLD, 14));
		lblSuperpoder.setBounds(50, 120, 150, 30);
		pnlFormulario.add(lblSuperpoder);
		
		txtSuperpoder = new JTextField();
		txtSuperpoder.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSuperpoder.setBounds(200, 120, 255, 30);
		pnlFormulario.add(txtSuperpoder);

		// 4. Colores
		JLabel lblColores = new JLabel("Colores:");
		lblColores.setFont(new Font("Verdana", Font.BOLD, 14));
		lblColores.setBounds(50, 170, 150, 30);
		pnlFormulario.add(lblColores);
		
		txtColores = new JTextField();
		txtColores.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtColores.setBounds(200, 170, 255, 30);
		pnlFormulario.add(txtColores);

		// Fondo
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}
	
	public void setControladorGuardar(ActionListener c) {
		btnGuardarCambios.addActionListener(c);
	}

	public String getRangoUsuario() {
		return rangoUsuario;
	}

	public String getIdCliente() {
		return txtIdCliente.getText();
	}

	public String getNombreCliente() {
		return txtNombreCliente.getText();
	}

	public String getSuperpoder() {
		return txtSuperpoder.getText();
	}

	public String getColores() {
		return txtColores.getText();
	}
}