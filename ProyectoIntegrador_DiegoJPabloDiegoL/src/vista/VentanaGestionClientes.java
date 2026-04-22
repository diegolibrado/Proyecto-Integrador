package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

		// --- BOTÓN CREAR ---
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(25, 30, 100, 30);
		btnCrear.addActionListener(e -> {
			VentanaAccionCliente diag = new VentanaAccionCliente(this, "CREAR");
			diag.setVisible(true);
			if (diag.isGuardado()) {
				int maxId = 0;
				for(int i = 0; i < modeloTabla.getRowCount(); i++) {
					int idActual = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString());
					if(idActual > maxId) maxId = idActual;
				}
				modeloTabla.addRow(new Object[]{maxId + 1, diag.getNombre(), diag.getSuperpoder(), diag.getColores()});
			}
		});
		pnlCuerpo.add(btnCrear);

		// --- BOTÓN ELIMINAR ---
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(25, 75, 100, 30);
		btnEliminar.addActionListener(e -> {
			int fila = table.getSelectedRow();
			if (fila != -1) {
				modeloTabla.removeRow(fila);
			} else {
				JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla para eliminarlo.");
			}
		});
		pnlCuerpo.add(btnEliminar);

		// --- BOTÓN MODIFICAR ---
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(25, 120, 100, 30);
		btnModificar.addActionListener(e -> {
			int fila = table.getSelectedRow();
			if (fila != -1) {
				VentanaAccionCliente diag = new VentanaAccionCliente(this, "MODIFICAR");
				diag.prellenarDatos(
					modeloTabla.getValueAt(fila, 0).toString(),
					modeloTabla.getValueAt(fila, 1).toString(),
					modeloTabla.getValueAt(fila, 2).toString(),
					modeloTabla.getValueAt(fila, 3).toString()
				);
				diag.setVisible(true);
				if (diag.isGuardado()) {
					modeloTabla.setValueAt(diag.getNombre(), fila, 1);
					modeloTabla.setValueAt(diag.getSuperpoder(), fila, 2);
					modeloTabla.setValueAt(diag.getColores(), fila, 3);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla para modificarlo.");
			}
		});
		pnlCuerpo.add(btnModificar);

		// --- BOTÓN GUARDAR ---
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(25, 240, 100, 30);
		btnGuardar.addActionListener(e -> {
			guardarEnBaseDeDatos();
		});
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

		modeloTabla = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
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
		if (conexion == null) return;

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
			System.out.println("Error al cargar datos: " + e.getMessage());
		} finally {
			conector.cerrarConexion(conexion);
		}
	}

	private void guardarEnBaseDeDatos() {
		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();
		
		if (conexion == null) {
			JOptionPane.showMessageDialog(this, "No hay conexión con la base de datos.");
			return;
		}

		try {
			// 1. Limpiar tabla
			Statement st = conexion.createStatement();
			st.executeUpdate("DELETE FROM CLIENTE");

			// 2. Insertar lo que hay en la tabla
			String sql = "INSERT INTO CLIENTE (id_cliente, nombre, superpoder, colores) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(sql);

			for (int i = 0; i < modeloTabla.getRowCount(); i++) {
				ps.setInt(1, Integer.parseInt(modeloTabla.getValueAt(i, 0).toString()));
				ps.setString(2, modeloTabla.getValueAt(i, 1).toString());
				ps.setString(3, modeloTabla.getValueAt(i, 2).toString());
				ps.setString(4, modeloTabla.getValueAt(i, 3).toString());
				ps.executeUpdate();
			}
			JOptionPane.showMessageDialog(this, "¡Cambios guardados con éxito en la base de datos!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
		} finally {
			conector.cerrarConexion(conexion);
		}
	}
}