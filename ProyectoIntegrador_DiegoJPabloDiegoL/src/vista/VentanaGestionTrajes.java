package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import controlador.ControladorMenuTraje;

public class VentanaGestionTrajes extends JFrame {

	private String rangoUsuario;
	private int idUsuario;
	private JButton btnAtras;
	private JButton btnCerrarSesion;
	private DefaultTableModel modeloTabla;
	private JTable table;

	public VentanaGestionTrajes(String rango, int id) {
		this.rangoUsuario = rango;
		this.idUsuario = id;
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

		JLabel lblCopyright = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(Color.WHITE);
		lblCopyright.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblCopyright);

		// Panel horizontal
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		getContentPane().add(pnlBarraHorizontal);
		pnlBarraHorizontal.setLayout(null);

		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);

		JLabel lblTitulo = new JLabel("Gestión de Trajes");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 333, 40);
		getContentPane().add(lblTitulo);

		btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setBounds(22, 11, 30, 30);
		getContentPane().add(btnAtras);

		JPanel pnlBarraHorizontal_1 = new JPanel();
		pnlBarraHorizontal_1.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlBarraHorizontal_1.setLayout(null);
		pnlBarraHorizontal_1.setBackground(new Color(165, 191, 201));
		pnlBarraHorizontal_1.setBounds(22, 25, 899, 236);
		pnlBarraHorizontal.add(pnlBarraHorizontal_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 879, 216);
		pnlBarraHorizontal_1.add(scrollPane);

		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("NOMBRE");
		modeloTabla.addColumn("ESTADO");
		modeloTabla.addColumn("CLIENTE");

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);

		JLabel lblFondo = new JLabel(new ImageIcon("img\\fondo.jpeg"));
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
	}

	public void setControlador(ControladorMenuTraje c) {
		btnAtras.addActionListener(c);
		btnCerrarSesion.addActionListener(c);
	}

	public JButton getBtnAtras() { return btnAtras; }
	public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
	public DefaultTableModel getModeloTabla() { return modeloTabla; }
}