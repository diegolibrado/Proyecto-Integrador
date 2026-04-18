package vista;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame {
	private JPasswordField passwordField;
	private JTextField textFieldUsuario;

	public VentanaLogin(String titulo) {
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
		getContentPane().setLayout(null);

		// TITULO
		JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
		lblTitulo.setBounds(27, 63, 298, 40);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		getContentPane().add(lblTitulo);

		// BARRA HORIZONTAL (decoracion del fondo)
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 109, 944, 282);
		getContentPane().add(pnlBarraHorizontal);
		pnlBarraHorizontal.setLayout(null);

		// IMAGEN DEL LOGO (no todavia)
		JLabel lblLogo = new JLabel("LOGO");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblLogo.setBackground(new Color(255, 255, 255));
		lblLogo.setBounds(37, 64, 153, 153);
		pnlBarraHorizontal.add(lblLogo);

		// PETICION PARAMETROS INICIO DE SESIÓN
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(330, 89, 158, 40);
		pnlBarraHorizontal.add(lblUsuario);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBackground(new Color(255, 255, 255));
		textFieldUsuario.setBounds(510, 89, 216, 40);
		pnlBarraHorizontal.add(textFieldUsuario);
		textFieldUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldUsuario.setColumns(10);

		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setBounds(330, 159, 158, 40);
		pnlBarraHorizontal.add(lblContraseña);
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 22));

		passwordField = new JPasswordField();
		passwordField.setBounds(510, 159, 216, 40);
		pnlBarraHorizontal.add(passwordField);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);

		// BOTÓN DE ACCESO
		JButton btnLogin = new JButton("Acceder");
		btnLogin.setBounds(788, 159, 111, 40);
		pnlBarraHorizontal.add(btnLogin);

		// LISTENER PARA CONFIRMAR ACCESO
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMaestro vMaestro = new VentanaMaestro("Menu de Gestión - Maestro");
				vMaestro.setVisible(true);
				dispose();
			}
		});
		btnLogin.setBackground(new Color(165, 191, 201));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));

		// IMAGEN DE FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(
				"C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\fondo.jpeg"));
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);

	}
}
