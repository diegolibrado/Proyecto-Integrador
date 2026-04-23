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

public class VentanaGestionCliente extends JFrame {

	// Declaracion de variables
	private String rangoUsuario;
	private DefaultTableModel modeloTabla;
	private JTable table;

	public VentanaGestionCliente(String rango) {
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
		JLabel lblTitulo = new JLabel("Gestión de Cliente");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 400, 40);
		getContentPane().add(lblTitulo);

		// BOTONES
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBackground(new Color(165, 191, 201));
		btnCrear.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCrear.setBounds(22, 25, 109, 30);
		pnlBarraHorizontal.add(btnCrear);
		
		btnCrear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        VentanaCrearCliente vCrear = new VentanaCrearCliente(rangoUsuario);
		        controlador.ControladorCrearCliente cCrear = new controlador.ControladorCrearCliente(vCrear);
		        vCrear.setControladorGuardar(cCrear);
		        vCrear.setVisible(true);
		        dispose(); 
		    }
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(165, 191, 201));
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminar.setBounds(22, 63, 109, 30);
		pnlBarraHorizontal.add(btnEliminar);
		controlador.ControladorEliminarCliente cEliminar = new controlador.ControladorEliminarCliente(this);
		btnEliminar.addActionListener(cEliminar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(165, 191, 201));
		btnModificar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnModificar.setBounds(22, 101, 109, 30);
		pnlBarraHorizontal.add(btnModificar);
		
		btnModificar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int filaSeleccionada = table.getSelectedRow();
		        
		        if (filaSeleccionada == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona un cliente de la tabla para modificarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
		        String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
		        String superpoder = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
		        String colores = (String) modeloTabla.getValueAt(filaSeleccionada, 3);

		        VentanaModificarCliente vModificar = new VentanaModificarCliente(rangoUsuario, id, nombre, superpoder, colores);
		        controlador.ControladorModificarCliente cModificar = new controlador.ControladorModificarCliente(vModificar);
		        vModificar.setControladorModificar(cModificar);
		        vModificar.setVisible(true);
		        dispose();
		    }
		});

		JButton btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMaestro vMaestro = new VentanaMaestro("Gestion de citas");
				vMaestro.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setBounds(22, 11, 30, 30);
		getContentPane().add(btnAtras);

		// Panel con informacion
		JPanel pnlBarraHorizontal_1 = new JPanel();
		pnlBarraHorizontal_1.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlBarraHorizontal_1.setLayout(null);
		pnlBarraHorizontal_1.setBackground(new Color(165, 191, 201));
		pnlBarraHorizontal_1.setBounds(139, 25, 782, 236);
		pnlBarraHorizontal.add(pnlBarraHorizontal_1);

		// ScrollPane para la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 762, 216);
		pnlBarraHorizontal_1.add(scrollPane);

		// Tabla
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("NOMBRE");
		modeloTabla.addColumn("SUPERPODER");
		modeloTabla.addColumn("COLORES");

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);

		cargarDatosCliente();
        
		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
		getContentPane().add(lblFondo);
	}
	
	public void cargarDatosCliente() {
		modeloTabla.setRowCount(0);
		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();

		String query = "SELECT id_cliente, nombre, superpoder, colores FROM CLIENTE";

		try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {
			while (rs.next()) {
				Object[] fila = new Object[4];
				fila[0] = rs.getInt("id_cliente");
				fila[1] = rs.getString("nombre");
				fila[2] = rs.getString("superpoder");
				fila[3] = rs.getString("colores");

				modeloTabla.addRow(fila);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de SQL: " + e.getMessage());
		} finally {
			conector.cerrarConexion(conexion);
		}
	}
	
	public int getIdClienteSeleccionado() {
		int filaSeleccionada = table.getSelectedRow();
		if (filaSeleccionada == -1) {
			return -1; 
		}
		return (int) modeloTabla.getValueAt(filaSeleccionada, 0);
	}

    public String getRangoUsuario() {
        return rangoUsuario;
    }
}
