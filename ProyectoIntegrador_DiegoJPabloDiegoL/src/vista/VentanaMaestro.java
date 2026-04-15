package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class VentanaMaestro extends JFrame {
	public VentanaMaestro(String titulo) {
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
		JLabel lblMenuMaestro = new JLabel("MENÚ MAESTRO");
		lblMenuMaestro.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuMaestro.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblMenuMaestro.setBounds(27, 63, 298, 40);
		getContentPane().add(lblMenuMaestro);
		
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
