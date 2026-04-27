package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Modelo;

public class VentanaGestionTrajes extends JFrame {

	// Declaracion de variables
	private String rangoUsuario;
	private int idUsuario;
	private JButton btnEliminarCita;
	private JButton btnCrearCita;
	private JButton btnModificarCita;
	private JButton btnGuardarCambios;
	private DefaultTableModel modeloTabla;
	private JTable table;

	public VentanaGestionTrajes(String rango, int id) {
		this.rangoUsuario = rango;
		this.idUsuario = id;
		inicializarComponentes();
		configInicial();
		configurarPermisos();
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

	/**
	 * Metodo para que dependiendo de que tipo de empleado haya ingresado, se
	 * muestren unas cosas u otras
	 */
	private void configurarPermisos() {

	}

	private void inicializarComponentes() {

		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(new Color(72, 119, 109));
		pnlFooter.setBounds(0, 481, 944, 20);
		getContentPane().add(pnlFooter);

		// Copyright
		JLabel lblNewLabel_1 = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblNewLabel_1);

		// Panel horizontal
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		getContentPane().add(pnlBarraHorizontal);
		pnlBarraHorizontal.setLayout(null);

		// Boton Cerrar Sesion
		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
				// Creo objeto tipo controlador asociado a la nueva ventana para que pueda
				// volver a iniciar sesion
				controlador.ControladorLogin c = new controlador.ControladorLogin(vLogin);
				vLogin.setControlador(c);
				vLogin.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);

		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Gestión de Trajes");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 333, 40);
		getContentPane().add(lblTitulo);

		JButton btnAtras = new JButton("");
		// Para que se autoescale y se coloque el tamañocorrectamente
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Dependiendo del tipo de empleado volveremos a una pagina u otra
				// De momento solo a la de maestro
				VentanaMaestro vMaestro = new VentanaMaestro("Gestion de citas", rangoUsuario, idUsuario);
				vMaestro.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setFont(new Font("Verdana", Font.PLAIN, 5));
		btnAtras.setBounds(22, 11, 30, 30); // Posición arriba a la izquierda
		getContentPane().add(btnAtras);

		// Panel con informacion
		JPanel pnlBarraHorizontal_1 = new JPanel();
		pnlBarraHorizontal_1.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlBarraHorizontal_1.setLayout(null);
		pnlBarraHorizontal_1.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal_1.setBackground(new Color(165, 191, 201));
		pnlBarraHorizontal_1.setBounds(22, 25, 899, 236);
		pnlBarraHorizontal.add(pnlBarraHorizontal_1);

		// ScrollPane para la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 879, 216);
		pnlBarraHorizontal_1.add(scrollPane);

		// Tabla
		table = new JTable();
		scrollPane.setViewportView(table);
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("NOMBRE");
		modeloTabla.addColumn("ESTADO");
		modeloTabla.addColumn("CLIENTE");

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);

		cargarDatosTrajes();

		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}

	public void cargarDatosTrajes() {
		modeloTabla.setRowCount(0);

		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();

		String query = "SELECT t.id_traje, t.nombre, t.estado, cl.nombre AS cliente " + "FROM TRAJE t "
				+ "JOIN CLIENTE cl ON t.id_cliente = cl.id_cliente";
		try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {

			// Añadimos los datos
			while (rs.next()) {
				Object[] fila = new Object[4];
				fila[0] = rs.getInt("id_traje");
				fila[1] = rs.getString("nombre");
				fila[2] = rs.getString("estado");
				fila[3] = rs.getString("cliente");

				modeloTabla.addRow(fila);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de SQL: " + e.getMessage());
			// Si o si cerramos la conexion, haya errores o no.
		} finally {
			conector.cerrarConexion(conexion);
		}
	}
}
