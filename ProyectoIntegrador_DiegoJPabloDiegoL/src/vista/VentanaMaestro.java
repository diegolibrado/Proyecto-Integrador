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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setSize(960, 540);
		setLocationRelativeTo(null);
	}

	private void inicializarComponentes() {
		
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
		JLabel lblMenuMaestro = new JLabel("MENÚ MAESTRO");
		lblMenuMaestro.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuMaestro.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblMenuMaestro.setBounds(27, 63, 298, 40);
		getContentPane().add(lblMenuMaestro);

		JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		getContentPane().add(lblTitulo);

		// BARRA HORIZONTAL (decoracion del fondo)
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		getContentPane().add(pnlBarraHorizontal);
		pnlBarraHorizontal.setLayout(null);

		JButton btnTalleres = new JButton("Talleres");
		btnTalleres.setBackground(new Color(165, 191, 201));
		btnTalleres.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTalleres.setBounds(22, 63, 109, 30);
		pnlBarraHorizontal.add(btnTalleres);

		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBackground(new Color(165, 191, 201));
		btnClientes.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnClientes.setBounds(22, 101, 109, 30);
		pnlBarraHorizontal.add(btnClientes);

		JButton btnTrajes = new JButton("Trajes");
		btnTrajes.setBackground(new Color(165, 191, 201));
		btnTrajes.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTrajes.setBounds(22, 139, 109, 30);
		pnlBarraHorizontal.add(btnTrajes);

		JButton btnCitas = new JButton("Citas");
		btnCitas.setBackground(new Color(165, 191, 201));
		btnCitas.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCitas.setBounds(22, 25, 109, 30);
		pnlBarraHorizontal.add(btnCitas);
		
		JPanel pnlBarraHorizontal_1 = new JPanel();
		pnlBarraHorizontal_1.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlBarraHorizontal_1.setLayout(null);
		pnlBarraHorizontal_1.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal_1.setBackground(new Color(165, 191, 201));
		pnlBarraHorizontal_1.setBounds(139, 25, 782, 236);
		pnlBarraHorizontal.add(pnlBarraHorizontal_1);
		
				JLabel lblFondo = new JLabel("");
				lblFondo.setBounds(0, 0, 944, 501);
				getContentPane().add(lblFondo);
				lblFondo.setIcon(new ImageIcon(
						"C:\\Users\\diego\\Proyecto-Integrador\\ProyectoIntegrador_DiegoJPabloDiegoL\\img\\fondo.jpeg"));
	}
}
