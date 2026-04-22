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

public class VentanaGestionClientes extends JFrame {

	private String rangoUsuario;
	private DefaultTableModel modeloTabla;
	private JTable table;

	public VentanaGestionClientes(String rango) {
		this.rangoUsuario = rango;
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
		
		JButton btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(22, 11, 40, 30);
		btnAtras.addActionListener(e -> {
			new VentanaMaestro("Gestión").setVisible(true);
			dispose();
		});
		getContentPane().add(btnAtras);

		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCerrarSesion.setBounds(815, 63, 110, 30);
		btnCerrarSesion.addActionListener(e -> {
			new VentanaLogin("Login").setVisible(true);
			dispose();
		});
		getContentPane().add(btnCerrarSesion);

		JLabel lblTitulo = new JLabel("Gestión de Clientes");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 400, 50);
		getContentPane().add(lblTitulo);

		JPanel pnlCuerpo = new JPanel();
		pnlCuerpo.setBackground(new Color(196, 204, 203, 180));
		pnlCuerpo.setBounds(0, 120, 944, 300);
		getContentPane().add(pnlCuerpo);
		pnlCuerpo.setLayout(null);

		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(25, 30, 100, 30);
		pnlCuerpo.add(btnCrear);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(25, 75, 100, 30);
		pnlCuerpo.add(btnEliminar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(25, 120, 100, 30);
		pnlCuerpo.add(btnModificar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(25, 240, 100, 30);
		pnlCuerpo.add(btnGuardar);

		JPanel pnlTabla = new JPanel();
		pnlTabla.setBorder(new LineBorder(Color.DARK_GRAY));
		pnlTabla.setBackground(new Color(165, 191, 201));
		pnlTabla.setBounds(150, 30, 770, 240);
		pnlCuerpo.add(pnlTabla);
		pnlTabla.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 760, 230);
		pnlTabla.add(scrollPane);

		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("NOMBRE");
		modeloTabla.addColumn("SUPERPODER");
		modeloTabla.addColumn("COLORES");

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);

		cargarDatosClientes();

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
		getContentPane().add(lblFondo);
	}

	public void cargarDatosClientes() {
		modeloTabla.setRowCount(0);
		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();
		String query = "SELECT id_cliente, nombre, superpoder, colores FROM CLIENTE";

		try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {
			while (rs.next()) {
				modeloTabla.addRow(new Object[]{
					rs.getInt("id_cliente"),
					rs.getString("nombre"),
					rs.getString("superpoder"),
					rs.getString("colores")
				});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		} finally {
			conector.cerrarConexion(conexion);
		}
	}
}