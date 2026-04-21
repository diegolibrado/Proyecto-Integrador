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

public class VentanaCrearTaller extends JFrame {

	// Declaracion de variables
	private String rangoUsuario;
	
	// Campos para el formulario
	private JTextField txtIdTaller;
	private JTextField txtNombreTaller;
	private JComboBox<String> cmbTipoSala; // AHORA ES UN JCOMBOBOX

	public VentanaCrearTaller(String rango) {
		this.rangoUsuario = rango;
		inicializarComponentes();
		configInicial();
		configurarPermisos();
	}

	private void configInicial() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(960, 540);
		setLocationRelativeTo(null);
	}

	private void configurarPermisos() {
		// Logica de permisos si fuera necesaria
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

		// Panel horizontal principal
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
		JLabel lblTitulo = new JLabel("Crear Taller");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 333, 40);
		getContentPane().add(lblTitulo);

		// Botón Guardar Cambios
		JButton btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnGuardarCambios.setBackground(new Color(165, 191, 201));
		btnGuardarCambios.setBounds(22, 231, 109, 30);
		pnlBarraHorizontal.add(btnGuardarCambios);
		
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarTallerEnBD();
			}
		});

		// Botón Atrás
		JButton btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(rangoUsuario);
				vGestionTalleres.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setBounds(22, 11, 30, 30); 
		getContentPane().add(btnAtras);

		// Panel con informacion (EL FORMULARIO)
		JPanel pnlFormulario = new JPanel();
		pnlFormulario.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlFormulario.setLayout(null);
		pnlFormulario.setBackground(new Color(165, 191, 201));
		pnlFormulario.setBounds(139, 25, 782, 236);
		pnlBarraHorizontal.add(pnlFormulario);

		// --- COMPONENTES DEL FORMULARIO ---
		
		// 1. ID Taller
		JLabel lblIdTaller = new JLabel("ID del Taller:");
		lblIdTaller.setFont(new Font("Verdana", Font.BOLD, 14));
		lblIdTaller.setBounds(50, 40, 150, 30);
		pnlFormulario.add(lblIdTaller);
		
		txtIdTaller = new JTextField();
		txtIdTaller.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtIdTaller.setBounds(200, 40, 255, 30);
		pnlFormulario.add(txtIdTaller);

		// 2. Nombre del Taller
		JLabel lblNombreTaller = new JLabel("Nombre:");
		lblNombreTaller.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombreTaller.setBounds(50, 100, 150, 30);
		pnlFormulario.add(lblNombreTaller);
		
		txtNombreTaller = new JTextField();
		txtNombreTaller.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreTaller.setBounds(200, 100, 255, 30);
		pnlFormulario.add(txtNombreTaller);

		// 3. Tipo de Sala
		JLabel lblTipoSala = new JLabel("Tipo de Sala:");
		lblTipoSala.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTipoSala.setBounds(50, 160, 150, 30);
		pnlFormulario.add(lblTipoSala);
		
		// Opciones para el desplegable
		String[] opcionesSala = {"Diseño", "Costura", "Pruebas"};
		cmbTipoSala = new JComboBox<>(opcionesSala);
		cmbTipoSala.setModel(new DefaultComboBoxModel(new String[] {"Diseño", "Costura", "Pruebas"}));
		cmbTipoSala.setFont(new Font("Verdana", Font.PLAIN, 14));
		cmbTipoSala.setBounds(200, 160, 255, 30);
		cmbTipoSala.setBackground(Color.WHITE); 
		pnlFormulario.add(cmbTipoSala);

		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}

	private void guardarTallerEnBD() {
		// 1. Recogemos los datos (del TextField y del ComboBox)
		String idStr = txtIdTaller.getText();
		String nombre = txtNombreTaller.getText();
		// Aquí sacamos el texto seleccionado en el ComboBox
		String tipo = cmbTipoSala.getSelectedItem().toString();

		// 2. Validaciones básicas
		if (idStr.isEmpty() || nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int idTaller = 0;
		try {
			idTaller = Integer.parseInt(idStr); // Convertimos el ID a número
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El ID del taller debe ser un número entero.", "Error de formato",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// 3. Conexión y guardado en la Base de Datos
		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();

		// Utilizamos PreparedStatement para evitar inyección SQL
		String query = "INSERT INTO TALLER (id_taller, nombre_sala, tipo_sala) VALUES (?, ?, ?)";

		try (PreparedStatement pst = conexion.prepareStatement(query)) {
			// Asignamos los valores a los interrogantes de la query
			pst.setInt(1, idTaller);
			pst.setString(2, nombre);
			pst.setString(3, tipo);

			// Ejecutamos la consulta
			int filasAfectadas = pst.executeUpdate();

			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(this, "Taller creado exitosamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);

				// Volvemos automáticamente a la ventana de gestión para ver el nuevo taller
				VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(rangoUsuario);
				vGestionTalleres.setVisible(true);
				dispose(); // Cerramos la ventana de creación
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el taller en BD: \n" + e.getMessage(), "Error SQL",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			conector.cerrarConexion(conexion);
		}
	}
}
