package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo;
import controlador.cita.ControladorModificarCita; // Asegúrate de que la ruta sea correcta

public class VentanaModificarCitas extends JFrame {

	private String rangoUsuario;
	private DefaultTableModel modeloTabla;
	private JTable table;
	private JButton btnCrearCita, btnEliminarCita, btnModificarCita, btnGuardarCambios, btnAtras, btnCerrarSesionTop;

	public VentanaModificarCitas(String rango) {
		this.rangoUsuario = rango;
		configInicial();
		inicializarComponentes();
		cargarDatosCitas();
	}

	private void configInicial() {
		setTitle("Gestión de Citas");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(960, 540);
		setLocationRelativeTo(null);
	}

	private void inicializarComponentes() {

		// --- FOOTER ---
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(new Color(72, 119, 109));
		pnlFooter.setBounds(0, 481, 944, 20);
		getContentPane().add(pnlFooter);

		JLabel lblCopyright = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(Color.WHITE);
		lblCopyright.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblCopyright);

		// --- TITULO ---
		JLabel lblTitulo = new JLabel("Gestión de Citas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 400, 40);
		getContentPane().add(lblTitulo);

		// --- BOTÓN ATRÁS ---
		btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(22, 11, 40, 30);
		getContentPane().add(btnAtras);

		// --- BOTÓN CERRAR SESIÓN ---
		btnCerrarSesionTop = new JButton("Cerrar sesión");
		btnCerrarSesionTop.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesionTop.setBackground(new Color(165, 191, 201));
		btnCerrarSesionTop.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesionTop);

		// --- PANEL CENTRAL ---
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		pnlBarraHorizontal.setLayout(null);
		getContentPane().add(pnlBarraHorizontal);

		// --- BOTONES LATERALES ---
		btnCrearCita = new JButton("Crear");
		btnCrearCita.setBackground(new Color(165, 191, 201));
		btnCrearCita.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCrearCita.setBounds(22, 25, 109, 30);
		pnlBarraHorizontal.add(btnCrearCita);

		btnEliminarCita = new JButton("Eliminar");
		btnEliminarCita.setBackground(new Color(165, 191, 201));
		btnEliminarCita.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminarCita.setBounds(22, 63, 109, 30);
		pnlBarraHorizontal.add(btnEliminarCita);

		btnModificarCita = new JButton("Modificar");
		btnModificarCita.setBackground(new Color(165, 191, 201));
		btnModificarCita.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnModificarCita.setBounds(22, 101, 109, 30);
		pnlBarraHorizontal.add(btnModificarCita);

		btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnGuardarCambios.setBackground(new Color(165, 191, 201));
		btnGuardarCambios.setBounds(22, 231, 109, 30);
		pnlBarraHorizontal.add(btnGuardarCambios);

		// --- CONTENEDOR TABLA ---
		JPanel pnlTabla = new JPanel();
		pnlTabla.setBorder(new LineBorder(Color.DARK_GRAY));
		pnlTabla.setBackground(new Color(165, 191, 201));
		pnlTabla.setBounds(150, 25, 770, 240);
		pnlTabla.setLayout(null);
		pnlBarraHorizontal.add(pnlTabla);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 760, 230);
		pnlTabla.add(scrollPane);
		
		// TABLA
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("DÍA");
		modeloTabla.addColumn("HORA");
		modeloTabla.addColumn("DURACIÓN");
		modeloTabla.addColumn("CLIENTE");
		modeloTabla.addColumn("TALLER");
		modeloTabla.addColumn("RESPONSABLE");
		modeloTabla.addColumn("TRAJE");
		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);
		
		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		// Asegúrate de que la carpeta 'img' y el archivo 'fondo.jpeg' existen en la
		// raíz de tu proyecto
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
		getContentPane().add(lblFondo);

		

	}

	// --- MÉTODOS DE DATOS ---

	public void cargarDatosCitas() {
		modeloTabla.setRowCount(0);
		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();
		if (conexion == null)
			return;

		String query = "SELECT c.id_cita, c.dia, c.hora, c.duracion, cl.nombre AS cliente, ta.nombre_sala AS taller, e.nombre AS empleado, tr.nombre AS traje "
				+ "FROM CITA c " + "JOIN CLIENTE cl ON c.id_cliente = cl.id_cliente "
				+ "JOIN TALLER ta ON c.id_taller = ta.id_taller "
				+ "JOIN EMPLEADO e ON c.id_empleado_responsable = e.id_empleado "
				+ "JOIN TRAJE tr ON c.id_traje = tr.id_traje";

		try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {
			while (rs.next()) {
				modeloTabla.addRow(new Object[] { rs.getInt("id_cita"), rs.getDate("dia"), rs.getTime("hora"),
						rs.getInt("duracion"), rs.getString("cliente"), rs.getString("taller"),
						rs.getString("empleado"), rs.getString("traje") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conector.cerrarConexion(conexion);
		}
	}

	// --- MÉTODO SET CONTROLADOR (COMO EN TALLERES) ---

	public void setControlador(ControladorModificarCita c) {
		btnCrearCita.addActionListener(c);
		btnEliminarCita.addActionListener(c);
		btnModificarCita.addActionListener(c);
		btnGuardarCambios.addActionListener(c);
		btnAtras.addActionListener(c);
		btnCerrarSesionTop.addActionListener(c);
	}

	// --- GETTERS PARA EL CONTROLADOR ---

	public JButton getBtnCrear() {
		return btnCrearCita;
	}

	public JButton getBtnEliminar() {
		return btnEliminarCita;
	}

	public JButton getBtnModificar() {
		return btnModificarCita;
	}

	public JButton getBtnGuardar() {
		return btnGuardarCambios;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesionTop;
	}

	public String getRangoUsuario() {
		return rangoUsuario;
	}

	public int getIdCitaSeleccionada() {
		int fila = table.getSelectedRow();
		return (fila != -1) ? (int) modeloTabla.getValueAt(fila, 0) : -1;
	}

}