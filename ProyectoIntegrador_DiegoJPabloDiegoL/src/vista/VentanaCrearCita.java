package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Calendar;

public class VentanaCrearCita extends JFrame {

	private String rangoUsuario;
	private JTextField txtNombreTraje;
	private JTextField txtNombreResponsable;
	private JButton btnGuardarCambios;
	private JButton btnCerrarSesion;
	private JButton btnAtras;
	private JTextField txtIdCita;
	private JTextField txtNombreCliente;
	private JTextField txtNombreTaller;
	private JTextField txtNumAprendices;
	private JSpinner spinnerFecha ;
	private JSpinner spinnerHora;
	private JSpinner spinnerDuracion;
	
	public VentanaCrearCita(String rango) {
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
		JLabel lblTitulo = new JLabel("Crear Cita");
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

		// COMPONENTES DEL FORMULARIO
		JLabel lblIdCita = new JLabel("ID de la Cita:");
		lblIdCita.setFont(new Font("Verdana", Font.BOLD, 14));
		lblIdCita.setBounds(484, 146, 106, 30);
		pnlFormulario.add(lblIdCita);
		txtIdCita = new JTextField();
		txtIdCita.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtIdCita.setBounds(602, 146, 98, 30);
		pnlFormulario.add(txtIdCita);

		JLabel lblNombreCliente = new JLabel("Nombre del Cliente:");
		lblNombreCliente.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombreCliente.setBounds(49, 23, 204, 30);
		pnlFormulario.add(lblNombreCliente);
		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreCliente.setBounds(263, 23, 150, 30);
		pnlFormulario.add(txtNombreCliente);
		
		JLabel lblNombreTraje = new JLabel("Nombre del Traje:");
		lblNombreTraje.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombreTraje.setBounds(49, 146, 204, 30);
		pnlFormulario.add(lblNombreTraje);
		txtNombreTraje = new JTextField();
		txtNombreTraje.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreTraje.setBounds(263, 146, 150, 30);
		pnlFormulario.add(txtNombreTraje);
		
		JLabel lblFecha = new JLabel("Día:");
		lblFecha.setFont(new Font("Verdana", Font.BOLD, 14));
		lblFecha.setBounds(484, 23, 37, 30);
		pnlFormulario.add(lblFecha);
		spinnerFecha = new JSpinner();
		spinnerFecha.setModel(new SpinnerDateModel(new Date(1776808800000L), null, null, Calendar.DAY_OF_MONTH));
		spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
		spinnerFecha.setFont(new Font("Verdana", Font.PLAIN, 12));
		spinnerFecha.setBounds(602, 27, 98, 23);
		pnlFormulario.add(spinnerFecha);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Verdana", Font.BOLD, 14));
		lblHora.setBounds(484, 64, 50, 30);
		pnlFormulario.add(lblHora);
		spinnerHora = new JSpinner();
		spinnerHora.setModel(new SpinnerDateModel(new Date(1776808800000L), null, null, Calendar.MINUTE));
		spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
		spinnerHora.setFont(new Font("Verdana", Font.PLAIN, 12));
		spinnerHora.setBounds(602, 68, 98, 23);
		pnlFormulario.add(spinnerHora);
		
		JLabel lblDuracion = new JLabel("Duración (h):");
		lblDuracion.setFont(new Font("Verdana", Font.BOLD, 14));
		lblDuracion.setBounds(484, 105, 188, 30);
		pnlFormulario.add(lblDuracion);
		spinnerDuracion = new JSpinner();
		spinnerDuracion.setModel(new SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
		spinnerDuracion.setFont(new Font("Verdana", Font.PLAIN, 12));
		spinnerDuracion.setBounds(602, 109, 98, 23);
		pnlFormulario.add(spinnerDuracion);
		
		JLabel lblNombreTaller = new JLabel("Nombre del Taller:");
		lblNombreTaller.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombreTaller.setBounds(49, 64, 204, 30);
		pnlFormulario.add(lblNombreTaller);
		txtNombreTaller = new JTextField();
		txtNombreTaller.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreTaller.setBounds(263, 64, 150, 30);
		pnlFormulario.add(txtNombreTaller);
		
		JLabel lblNombreResponsable = new JLabel("Nombre del Responsable:");
		lblNombreResponsable.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombreResponsable.setBounds(49, 105, 235, 30);
		pnlFormulario.add(lblNombreResponsable);
		txtNombreResponsable = new JTextField();
		txtNombreResponsable.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreResponsable.setBounds(263, 105, 150, 30);
		pnlFormulario.add(txtNombreResponsable);
		
		JLabel lblNumAprendices = new JLabel("Numero de Aprendices:");
		lblNumAprendices.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNumAprendices.setBounds(49, 187, 204, 30);
		pnlFormulario.add(lblNumAprendices);
		txtNumAprendices = new JTextField();
		txtNumAprendices.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNumAprendices.setBounds(263, 187, 150, 30);
		pnlFormulario.add(txtNumAprendices);
		
		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}

	// Getters y Setters
	public String getNombreCliente() { 
		return txtNombreCliente.getText(); 
	}
	
	public String getNombreTaller() { 
		return txtNombreTaller.getText(); 
	}
	
	public String getNombreResponsable() { 
		return txtNombreResponsable.getText(); 
	}
	
	public String getNombreTraje() { 
		return txtNombreTraje.getText(); 
	}
	
	public String getNumAprendices() { 
		return txtNumAprendices.getText(); 
	}
	
	public String getIdCita() { 
		return txtIdCita.getText(); 
	}
	
	public String getRangoUsuario() {
		return rangoUsuario;
	}
	
	// Fecha desde el spinnerFecha
	public java.sql.Date getFechaCita(){
		Date fecha = (Date) spinnerFecha.getValue();
		return new java.sql.Date(fecha.getTime());
	}
	
	// Fecha desde el spinnerHora
	public String getHoraCita() {
	    Date hora = (Date) spinnerHora.getValue();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
	    return sdf.format(hora);
	}
	
	// Duracion desde el spinnerDuracion
	public int getDuracion() {
	    return (int) spinnerDuracion.getValue();
	}
	
	public JButton getBtnGuardarCambios() {
		return btnGuardarCambios;
	}
	
	public void setControladorGuardar(ActionListener c) {
		btnGuardarCambios.addActionListener(c);
	}

	/**
	 * @return the btnCerrarSesion
	 */
	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	/**
	 * @return the btnAtras
	 */
	public JButton getBtnAtras() {
		return btnAtras;
	}
}
