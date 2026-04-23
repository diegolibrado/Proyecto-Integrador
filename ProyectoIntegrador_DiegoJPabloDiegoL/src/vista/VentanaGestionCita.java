package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorCrearCita;
import modelo.Cita;
import modelo.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VentanaGestionCita extends JFrame {

	// Declaracion de variables
	private String rangoUsuario;
	private JButton btnEliminarCita;
	private JButton btnCrearCita;
	private JButton btnModificarCita;
	private JButton btnGuardarCambios;
	private DefaultTableModel modeloTabla;
	private JTable table;

	public VentanaGestionCita(String rango) {
		this.rangoUsuario = rango;
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
		btnCerrarSesion.setBounds(5, 211, 140, 30);
		pnlBarraHorizontal.add(btnCerrarSesion);
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
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));

		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Gestión de Citas");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 298, 40);
		getContentPane().add(lblTitulo);

		// BOTONES
		btnCrearCita = new JButton("Crear");
		btnCrearCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCrearCita vCrearCita = new VentanaCrearCita(rangoUsuario);
				ControladorCrearCita cCrearCita = new ControladorCrearCita(vCrearCita);
				vCrearCita.getBtnGuardarCambios().addActionListener(cCrearCita);
				vCrearCita.setVisible(true);
				dispose();
			}
		});
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

		JButton btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnGuardarCambios.setBackground(new Color(165, 191, 201));
		btnGuardarCambios.setBounds(22, 141, 109, 30);
		pnlBarraHorizontal.add(btnGuardarCambios);

		JButton btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		// Para que se autoescale y se coloque el tamaño correctamente
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Dependiendo del tipo de empleado volveremos a una pagina u otra
				// De momento solo a la de maestro
				VentanaMaestro vMaestro = new VentanaMaestro("Gestion de citas");
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
		pnlBarraHorizontal_1.setBounds(150, 25, 782, 236);
		pnlBarraHorizontal.add(pnlBarraHorizontal_1);

		// ScrollPane para la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 762, 216);
		pnlBarraHorizontal_1.add(scrollPane);

		// Tabla
		table = new JTable();
		scrollPane.setViewportView(table);
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("DÍA");
		modeloTabla.addColumn("HORA");
		modeloTabla.addColumn("DURACIÓN");
		modeloTabla.addColumn("CLIENTE");
		modeloTabla.addColumn("TALLER");
		modeloTabla.addColumn("RESPONSABLE");
		modeloTabla.addColumn("TRAJE");

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);

//		cargarDatosCitas();

		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}

	public void cargarDatosCitas(ArrayList<Cita> datosCitas) {
/*		modeloTabla.setRowCount(0);
//
//		Modelo conector = new Modelo();
//		Connection conexion = conector.getConexion();
//
//		String query = "SELECT c.dia, c.hora, c.duracion, cl.nombre AS cliente, ta.nombre_sala AS taller, e.nombre AS empleado, tr.nombre AS traje "
//				+ "FROM CITA c " + "JOIN CLIENTE cl ON c.id_cliente = cl.id_cliente "
//				+ "JOIN TALLER ta ON c.id_taller = ta.id_taller "
//				+ "JOIN EMPLEADO e ON c.id_empleado_responsable = e.id_empleado "
//				+ "JOIN TRAJE tr ON c.id_traje = tr.id_traje";
//		try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {
//
//			// Añadimos los datos
//			while (rs.next()) {
//				Object[] fila = new Object[7];
//				fila[0] = rs.getDate("dia");
//				fila[1] = rs.getTime("hora");
//				fila[2] = rs.getInt("duracion");
//				fila[3] = rs.getString("cliente");
//				fila[4] = rs.getString("taller");
//				fila[5] = rs.getString("empleado");
//				fila[6] = rs.getString("traje");
//
//				modeloTabla.addRow(fila);
//
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "Error de SQL: " + e.getMessage());
//			// Si o si cerramos la conexion, haya errores o no.
//		} finally {
//			conector.cerrarConexion(conexion);
//		}*/

		modeloTabla.setRowCount(0);
		for (Cita c : datosCitas) {
			Object[] fila = new Object[7];
			fila[0] = c.getDia();
			fila[1] = c.getHora();
			fila[2] = c.getDuracion();
			fila[3] = c.getId_cliente(); 
			fila[4] = c.getId_taller();
			fila[5] = c.getId_empleado();
			fila[6] = c.getId_traje();
//
			modeloTabla.addRow(fila);
		}
		
	}
}
