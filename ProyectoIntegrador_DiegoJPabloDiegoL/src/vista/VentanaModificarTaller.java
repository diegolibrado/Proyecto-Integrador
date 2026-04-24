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

import controlador.ControladorMenuMaestro;
import controlador.taller.ControladorModificarTaller;
import modelo.Modelo;

public class VentanaModificarTaller extends JFrame {

	// Declaracion de variables de clase (para poder acceder a ellas en el método de guardar)
	private String rangoUsuario;
	private int idUsuario;
	private JTextField txtIdTaller;
	private JTextField txtNombreTaller;
	private JComboBox<String> cmbTipoSala;
	private JButton btnGuardarCambios;
	private JButton btnAtras;
	private JButton btnCerrarSesion;
	
	// El constructor ahora recibe los datos desde la tabla
	public VentanaModificarTaller(String rango, int id, String nombre, String tipo, int idUser) {
		this.rangoUsuario = rango;
		this.idUsuario = idUser;
		inicializarComponentes();
		configInicial();
		configurarPermisos();
		
		// CARGAMOS LOS DATOS EN EL FORMULARIO
		txtIdTaller.setText(String.valueOf(id));
		txtIdTaller.setEditable(false); // El ID no se debe poder modificar
		
		txtNombreTaller.setText(nombre);
		cmbTipoSala.setSelectedItem(tipo);
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

		ControladorModificarTaller cModificarTaller = new ControladorModificarTaller(this, rangoUsuario, idUsuario);
		
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
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);

		// Titulo Pagina (Cambiado a Modificar)
		JLabel lblTitulo = new JLabel("Modificar Taller");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 333, 40);
		getContentPane().add(lblTitulo);

		// Botón Guardar Cambios
		btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnGuardarCambios.setBackground(new Color(165, 191, 201));
		btnGuardarCambios.setBounds(22, 231, 109, 30);
		pnlBarraHorizontal.add(btnGuardarCambios);

		// Botón Atrás
		btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
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
	
	/**
	 * Metodo que llamaremos desde el controlador
	 * @param c
	 */
	public void setControladorModificar(ActionListener c) {
		btnGuardarCambios.addActionListener(c);
	    btnAtras.addActionListener(c);
	    btnCerrarSesion.addActionListener(c);
	}

	public String getRangoUsuario() {
		return rangoUsuario;
	}

	public String getIdTaller() {
		return txtIdTaller.getText();
	}

	public String getNombreTaller() {
		return txtNombreTaller.getText();
	}

	public String getTipoSala() {
		return cmbTipoSala.getSelectedItem().toString();
	}

	/**
	 * @return the btnAtras
	 */
	public JButton getBtnAtras() {
		return btnAtras;
	}

	/**
	 * @return the btnGuardarCambios
	 */
	public JButton getBtnGuardarCambios() {
		return btnGuardarCambios;
	}

	/**
	 * @return the btnCerrarSesion
	 */
	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}
	
	
}