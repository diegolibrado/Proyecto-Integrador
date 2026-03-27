/**
 * 
 */
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

/**
 * 
 */
public class VentanaLogin extends JFrame {
	private JPasswordField passwordField;
	private JTextField textFieldUsuario;
	
	public VentanaLogin(String titulo) {
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
		setSize(600, 400);
	}
	
	private void inicializarComponentes() {
		JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(0, 0, 586, 77);
		getContentPane().add(lblTitulo);
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblContraseña.setBounds(60, 157, 158, 40);
		getContentPane().add(lblContraseña);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUsuario.setBounds(60, 87, 158, 40);
		getContentPane().add(lblUsuario);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(240, 157, 216, 40);
		getContentPane().add(passwordField);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsuario.setBounds(240, 87, 216, 40);
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(160, 300, 100, 30);
		getContentPane().add(btnLogin);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBorrar.setBounds(340, 300, 100, 30);
		getContentPane().add(btnBorrar);
		
		JLabel lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEmpleado.setBounds(60, 227, 158, 40);
		getContentPane().add(lblEmpleado);
		
		JRadioButton rdbtnMaestro = new JRadioButton("Maestro");
		rdbtnMaestro.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnMaestro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnMaestro.setBounds(189, 242, 102, 20);
		getContentPane().add(rdbtnMaestro);

		JRadioButton rdbtnOficial = new JRadioButton("Oficial");
		rdbtnOficial.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOficial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnOficial.setBounds(289, 242, 101, 20);
		getContentPane().add(rdbtnOficial);

		JRadioButton rdbtnAprendiz = new JRadioButton("Aprendiz");
		rdbtnAprendiz.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnAprendiz.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnAprendiz.setBounds(389, 242, 101, 20);
		getContentPane().add(rdbtnAprendiz);

		ButtonGroup grupoCategorias = new ButtonGroup();
		grupoCategorias.add(rdbtnMaestro);
		grupoCategorias.add(rdbtnOficial);
		grupoCategorias.add(rdbtnAprendiz);

	}
}
