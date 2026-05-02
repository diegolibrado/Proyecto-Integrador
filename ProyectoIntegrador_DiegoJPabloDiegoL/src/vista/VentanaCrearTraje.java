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

import controlador.taller.ControladorCrearTaller;
import controlador.taller.ControladorModificarTaller;
import controlador.traje.ControladorCrearTraje;
import modelo.Modelo;

public class VentanaCrearTraje extends JFrame {

	private String rangoUsuario;
	private String ventanaOrigen;
	private JTextField txtIdTraje;
	private JTextField txtNombreTraje;
	private JComboBox<String> cmbEstado;
	private JButton btnGuardarCambios;
	private JButton btnAtras;
	private JButton btnCerrarSesion;
	
	public VentanaCrearTraje(String rango, String origen) {
		this.rangoUsuario = rango;
		this.ventanaOrigen = origen;
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

		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Crear Traje");
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
		JLabel lblIdTraje = new JLabel("ID del Traje:");
		lblIdTraje.setFont(new Font("Verdana", Font.BOLD, 14));
		lblIdTraje.setBounds(50, 40, 150, 30);
		pnlFormulario.add(lblIdTraje);
		
		txtIdTraje = new JTextField();
		txtIdTraje.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtIdTraje.setBounds(200, 40, 255, 30);
		pnlFormulario.add(txtIdTraje);

		// 2. Nombre del Taller
		JLabel lblNombreTraje = new JLabel("Nombre:");
		lblNombreTraje.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombreTraje.setBounds(50, 100, 150, 30);
		pnlFormulario.add(lblNombreTraje);
		
		txtNombreTraje = new JTextField();
		txtNombreTraje.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreTraje.setBounds(200, 100, 255, 30);
		pnlFormulario.add(txtNombreTraje);

		// 3. Tipo de Sala
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Verdana", Font.BOLD, 14));
		lblEstado.setBounds(50, 160, 150, 30);
		pnlFormulario.add(lblEstado);
		
		// Opciones para el desplegable
		cmbEstado = new JComboBox<>(new String[] {"en diseño", "costura", "taller"});
		cmbEstado.setFont(new Font("Verdana", Font.PLAIN, 14));
		cmbEstado.setBounds(200, 160, 255, 30);
		cmbEstado.setBackground(Color.WHITE); 
		pnlFormulario.add(cmbEstado);

		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}
	
	/**
	 * Metodo que llamaremos desde el controlador
	 * @param cCrearTraje
	 */
	public void setControladorGuardar(ControladorCrearTraje cCrearTraje) {
	    btnGuardarCambios.addActionListener(cCrearTraje);
	    btnAtras.addActionListener(cCrearTraje);
	    btnCerrarSesion.addActionListener(cCrearTraje);
	}

	public String getRangoUsuario() { return rangoUsuario; }
	public String getIdTaller() { return txtIdTraje.getText(); }
	public String getNombreTaller() { return txtNombreTraje.getText(); }
	public String getTipoSala() { return cmbEstado.getSelectedItem().toString(); }
	public JButton getBtnAtras() { return btnAtras; }
	public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
	public JButton getBtnGuardarCambios() { return btnGuardarCambios; }
	public String getIdTraje() { return txtIdTraje.getText(); } 
	public String getNombreTraje() { return txtNombreTraje.getText(); }
	public String getEstado() { return cmbEstado.getSelectedItem().toString(); }
	public String getVentanaOrigen() { return ventanaOrigen; }	
}
