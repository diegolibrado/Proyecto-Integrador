package vista;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.taller.ControladorMenuTaller;
import modelo.Taller;

public class VentanaGestionTalleres extends JFrame {

	// Declaracion de variables
	private String rangoUsuario;
	private JButton btnEliminar;
	private JButton btnCrear;
	private JButton btnModificar;
	private JButton btnAtras;
	private JButton btnCerrarSesion;
	private DefaultTableModel modeloTabla;
	private JTable table;

	public VentanaGestionTalleres(String rango) {
		this.rangoUsuario = rango;
		inicializarComponentes();
		configInicial();
	}

	/**
	 * Metodo para determinar la configuracion inicial de la ventana.
	 */
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

		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Gestión de Talleres");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 333, 40);
		getContentPane().add(lblTitulo);

		// --- BOTONES DE ACCIÓN ---
		btnCrear = new JButton("Crear");
		btnCrear.setBackground(new Color(165, 191, 201));
		btnCrear.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCrear.setBounds(22, 25, 109, 30);
		pnlBarraHorizontal.add(btnCrear);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(165, 191, 201));
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminar.setBounds(22, 63, 109, 30);
		pnlBarraHorizontal.add(btnEliminar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(165, 191, 201));
		btnModificar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnModificar.setBounds(22, 101, 109, 30);
		pnlBarraHorizontal.add(btnModificar);

		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);

		btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setBounds(22, 11, 30, 30);
		getContentPane().add(btnAtras);

		// --- PANEL DE LA TABLA ---
		JPanel pnlContenedorTabla = new JPanel();
		pnlContenedorTabla.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlContenedorTabla.setLayout(null);
		pnlContenedorTabla.setBackground(new Color(165, 191, 201));
		pnlContenedorTabla.setBounds(150, 25, 782, 236);
		pnlBarraHorizontal.add(pnlContenedorTabla);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 762, 216);
		pnlContenedorTabla.add(scrollPane);

		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("NOMBRE");
		modeloTabla.addColumn("TIPO");

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);

		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}

	public void setControlador(ControladorMenuTaller c) {
	    btnAtras.addActionListener(c);
		btnCrear.addActionListener(c);
		btnCrear.addActionListener(c);
		btnEliminar.addActionListener(c);
		btnModificar.addActionListener(c);
		btnCerrarSesion.addActionListener(c);
		btnAtras.addActionListener(c);
	}
	
	public void cargarDatosTalleres(ArrayList<Taller> datosTaller) {
		modeloTabla.setRowCount(0);
		for (Taller t : datosTaller) {
			Object[] fila = new Object[3];
			fila[0] = t.getId_taller();
			fila[1] = t.getNombre();
			fila[2] = t.getTipo_sala();
			modeloTabla.addRow(fila);
		}
	}

	public int getIdTallerSeleccionado() {
		int filaSeleccionada = table.getSelectedRow();
		if (filaSeleccionada == -1)
			return -1;
		return (int) modeloTabla.getValueAt(filaSeleccionada, 0);
	}

	public String getNombreTallerSeleccionado() {
		int fila = table.getSelectedRow();
		return (fila != -1) ? modeloTabla.getValueAt(fila, 1).toString() : null;
	}

	public String getTipoTallerSeleccionado() {
		int fila = table.getSelectedRow();
		return (fila != -1) ? modeloTabla.getValueAt(fila, 2).toString() : null;
	}

	// GETTERS
	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}

	public JButton getBtnModificar() {
		return btnModificar;
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