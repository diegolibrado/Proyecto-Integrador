package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import controlador.traje.ControladorMenuTraje;
import modelo.Traje;

public class VentanaGestionTrajes extends JFrame {

	private String rangoUsuario;
	private int idUsuario;
	private JButton btnEliminarTraje;
	private JButton btnCrearTraje;
	private JButton btnModificar;
	private JButton btnAtras;
	private JButton btnCerrarSesion;
	private DefaultTableModel modeloTabla;
	private JTable table;

	public VentanaGestionTrajes(String rango, int id) {
		this.rangoUsuario = rango;
		this.idUsuario = id;
		configInicial();
		inicializarComponentes();
	}

	private void configInicial() {
		setTitle("Gestión de Trajes");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(960, 540);
		setLocationRelativeTo(null);
	}

	private void inicializarComponentes() {
		ControladorMenuTraje controlador = new ControladorMenuTraje(this, rangoUsuario, idUsuario);

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

		// Panel horizontal central
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		pnlBarraHorizontal.setLayout(null);
		getContentPane().add(pnlBarraHorizontal);

		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Gestión de Trajes");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 400, 40);
		getContentPane().add(lblTitulo);

		// BOTÓN CREAR
		btnCrearTraje = new JButton("Crear");
		btnCrearTraje.setBackground(new Color(165, 191, 201));
		btnCrearTraje.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCrearTraje.setBounds(22, 25, 109, 30);
		pnlBarraHorizontal.add(btnCrearTraje);

		// BOTÓN ELIMINAR
		btnEliminarTraje = new JButton("Eliminar");
		btnEliminarTraje.setBackground(new Color(165, 191, 201));
		btnEliminarTraje.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminarTraje.setBounds(22, 63, 109, 30);
		pnlBarraHorizontal.add(btnEliminarTraje);

		// BOTÓN MODIFICAR
		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(165, 191, 201));
		btnModificar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnModificar.setBounds(22, 101, 109, 30);
		pnlBarraHorizontal.add(btnModificar);

		// BOTÓN CERRAR SESIÓN
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);

		// BOTÓN ATRÁS
		btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setBounds(22, 11, 30, 30);
		getContentPane().add(btnAtras);

		// Contenedor de Tabla
		JPanel pnlTablaContainer = new JPanel();
		pnlTablaContainer.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlTablaContainer.setBackground(new Color(165, 191, 201));
		pnlTablaContainer.setBounds(150, 25, 782, 236);
		pnlTablaContainer.setLayout(null);
		pnlBarraHorizontal.add(pnlTablaContainer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 762, 216);
		pnlTablaContainer.add(scrollPane);

		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("NOMBRE");
		modeloTabla.addColumn("ESTADO");

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);

		// Ocultar columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);

		// Fondo
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
		getContentPane().add(lblFondo);
	}

	public void setControlador(controlador.traje.ControladorMenuTraje cMenuTrajes) {
		btnModificar.addActionListener(cMenuTrajes);
	    btnEliminarTraje.addActionListener(cMenuTrajes);
	    btnCrearTraje.addActionListener(cMenuTrajes);
	    btnAtras.addActionListener(cMenuTrajes);
	    btnCerrarSesion.addActionListener(cMenuTrajes); 
	}

	public void cargarDatosTrajes(ArrayList<Traje> datosTraje) {
		modeloTabla.setRowCount(0);
		for (Traje t : datosTraje) {
			modeloTabla.addRow(new Object[] { t.getId_traje(), t.getNombre(), t.getEstado() });
		}
	}

	public int getIdTrajeSeleccionado() {
		int fila = table.getSelectedRow();
		return (fila != -1) ? (int) modeloTabla.getValueAt(fila, 0) : -1;
	}

	public String getNombreTrajeSeleccionado() {
		int fila = table.getSelectedRow();
		return (fila != -1) ? modeloTabla.getValueAt(fila, 1).toString() : null;
	}

	public String getEstadoTrajeSeleccionado() {
		int fila = table.getSelectedRow();
		return (fila != -1) ? modeloTabla.getValueAt(fila, 2).toString() : null;
	}

	public JButton getBtnEliminar() {
		return btnEliminarTraje;
	}

	public JButton getBtnCrear() {
		return btnCrearTraje;
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