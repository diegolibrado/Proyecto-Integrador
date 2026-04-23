package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import controlador.cliente.ControladorCrearCliente;

public class VentanaCrearCliente extends JFrame {

	private String rangoUsuario;
	private JTextField txtNombreCliente;
	private JTextField txtSuperpoder;
	private JTextField txtColores;
	private JButton btnGuardarCambios;
	private JButton btnAtras;
	private JButton btnCerrarSesion;

	public VentanaCrearCliente(String rango) {
		this.rangoUsuario = rango;
		inicializarComponentes();
		configInicial();
		
		ControladorCrearCliente controlador = new ControladorCrearCliente(this);
		setControladorGuardar(controlador);
	}

	private void configInicial() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(960, 540);
		setLocationRelativeTo(null);
		setTitle("Crear Nuevo Cliente");
	}

	private void inicializarComponentes() {

		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(new Color(72, 119, 109));
		pnlFooter.setBounds(0, 481, 944, 20);
		getContentPane().add(pnlFooter);

		JLabel lblCopyright = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(new Color(255, 255, 255));
		lblCopyright.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblCopyright);

		// Panel horizontal principal
		JPanel pnlBarraHorizontal = new JPanel();
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
		JLabel lblTitulo = new JLabel("Crear Cliente");
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
		btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		if (new java.io.File("img\\flecha_izq.png").exists()) {
			java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
			btnAtras.setIcon(new ImageIcon(imgAtras));
		}
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

		// 1. Nombree
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombre.setBounds(50, 45, 150, 30);
		pnlFormulario.add(lblNombre);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreCliente.setBounds(200, 45, 255, 30);
		pnlFormulario.add(txtNombreCliente);

		// 2. Superpoder
		JLabel lblSuperpoder = new JLabel("Superpoder:");
		lblSuperpoder.setFont(new Font("Verdana", Font.BOLD, 14));
		lblSuperpoder.setBounds(50, 105, 150, 30);
		pnlFormulario.add(lblSuperpoder);
		
		txtSuperpoder = new JTextField();
		txtSuperpoder.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSuperpoder.setBounds(200, 105, 255, 30);
		pnlFormulario.add(txtSuperpoder);

		// 3. Colores
		JLabel lblColores = new JLabel("Colores:");
		lblColores.setFont(new Font("Verdana", Font.BOLD, 14));
		lblColores.setBounds(50, 165, 150, 30);
		pnlFormulario.add(lblColores);
		
		txtColores = new JTextField();
		txtColores.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtColores.setBounds(200, 165, 255, 30);
		pnlFormulario.add(txtColores);

		// Fondo
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
		getContentPane().add(lblFondo);
	}
	
	public void setControladorGuardar(ActionListener c) {
		btnGuardarCambios.addActionListener(c);
		btnAtras.addActionListener(c);
		btnCerrarSesion.addActionListener(c);
	}

	//  GETTERS
	
	public String getNombreCliente() {
		return txtNombreCliente.getText();
	}

	public String getSuperpoderCliente() {
		return txtSuperpoder.getText();
	}

	public String getColorCliente() {
		return txtColores.getText();
	}

	public JButton getBtnGuardarCambios() {
		return btnGuardarCambios;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	public String getRangoUsuario() {
		return rangoUsuario;
	}
}