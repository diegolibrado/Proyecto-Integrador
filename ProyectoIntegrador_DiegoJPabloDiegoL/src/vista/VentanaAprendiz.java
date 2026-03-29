package vista;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

/**
 * 
 */
public class VentanaAprendiz extends JFrame {
	public VentanaAprendiz(String titulo) {
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
		setSize(960,540);
	}
	
	private void inicializarComponentes() {
		JLabel lblMenuAprendiz = new JLabel("MENÚ APRENDIZ");
		lblMenuAprendiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuAprendiz.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblMenuAprendiz.setBounds(27, 63, 298, 40);
		getContentPane().add(lblMenuAprendiz);
		
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setLayout(null);
		pnlBarraHorizontal.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 99, 944, 282);
		getContentPane().add(pnlBarraHorizontal);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\fondo.jpeg"));
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		
		JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		pnlBarraHorizontal.setBounds(0, 109, 944, 282);
		getContentPane().add(lblTitulo);
	}
}
