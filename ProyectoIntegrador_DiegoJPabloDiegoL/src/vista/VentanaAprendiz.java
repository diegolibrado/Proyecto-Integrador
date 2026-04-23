package vista;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Modelo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class VentanaAprendiz extends JFrame {
	
	private DefaultTableModel modeloTabla;
	private JTable table;
	public VentanaAprendiz(String titulo) {
		super(titulo);
		configInicial();
		inicializarComponentes();
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

		// Titulo Página
		JLabel lblTitulo = new JLabel("Menú Aprendiz");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 298, 40);
		getContentPane().add(lblTitulo);

		// Panel con informacion
				JPanel pnlBarraHorizontal_1 = new JPanel();
				pnlBarraHorizontal_1.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
				pnlBarraHorizontal_1.setLayout(null);
				pnlBarraHorizontal_1.setForeground(new Color(196, 204, 203));
				pnlBarraHorizontal_1.setBackground(new Color(165, 191, 201));
				pnlBarraHorizontal_1.setBounds(80, 25, 782, 236);
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

				cargarDatosCitas();

				// FONDO
				JLabel lblFondo = new JLabel("");
				lblFondo.setBounds(0, 0, 944, 501);
				getContentPane().add(lblFondo);
				lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}
	
	public void cargarDatosCitas() {
		modeloTabla.setRowCount(0);

		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();

		String query = "SELECT c.dia, c.hora, c.duracion, cl.nombre AS cliente, ta.nombre_sala AS taller, e.nombre AS empleado, tr.nombre AS traje "
				+ "FROM CITA c " + "JOIN CLIENTE cl ON c.id_cliente = cl.id_cliente "
				+ "JOIN TALLER ta ON c.id_taller = ta.id_taller "
				+ "JOIN EMPLEADO e ON c.id_empleado_responsable = e.id_empleado "
				+ "JOIN TRAJE tr ON c.id_traje = tr.id_traje";
		try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {

			// Añadimos los datos
			while (rs.next()) {
				Object[] fila = new Object[7];
				fila[0] = rs.getDate("dia");
				fila[1] = rs.getTime("hora");
				fila[2] = rs.getInt("duracion");
				fila[3] = rs.getString("cliente");
				fila[4] = rs.getString("taller");
				fila[5] = rs.getString("empleado");
				fila[6] = rs.getString("traje");

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
