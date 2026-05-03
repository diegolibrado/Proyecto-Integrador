package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class VentanaModificarTraje extends JFrame {

	// Declaracion de variables de clase (para poder acceder a ellas en el método de guardar)
	private String rangoUsuario;
	private int idUsuario;
	private JTextField txtIdTraje;
	private JTextField txtNombreTraje;
	private JComboBox<String> cmbEstado;
	private JButton btnGuardarCambios;
	private JButton btnAtras;
	private JButton btnCerrarSesion;
	
	// El constructor ahora recibe los datos desde la tabla
	public VentanaModificarTraje(String rango, int id, String nombre, String estado) {
		this.rangoUsuario = rango;
		this.idUsuario = id;
		inicializarComponentes();
		configInicial();
		configurarPermisos();
		
		// CARGAMOS LOS DATOS EN EL FORMULARIO
		txtIdTraje.setText(String.valueOf(id));
		txtIdTraje.setEditable(false);
		txtNombreTraje.setText(nombre);
		cmbEstado.setSelectedItem(estado);
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
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);

		// Titulo Pagina (Cambiado a Modificar)
		JLabel lblTitulo = new JLabel("Modificar Traje");
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
		JLabel lblIdTraje = new JLabel("ID del Taller:");
		lblIdTraje.setFont(new Font("Verdana", Font.BOLD, 14));
		lblIdTraje.setBounds(50, 40, 150, 30);
		pnlFormulario.add(lblIdTraje);
		
		txtIdTraje = new JTextField();
		txtIdTraje.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtIdTraje.setBounds(200, 40, 255, 30);
		pnlFormulario.add(txtIdTraje);

		// 2. Nombre del Taller
		JLabel lblNombreTaller = new JLabel("Nombre:");
		lblNombreTaller.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNombreTaller.setBounds(50, 100, 150, 30);
		pnlFormulario.add(lblNombreTaller);
		
		txtNombreTraje = new JTextField();
		txtNombreTraje.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombreTraje.setBounds(200, 100, 255, 30);
		pnlFormulario.add(txtNombreTraje);

		// 3. Tipo de Sala
		JLabel lblTipoSala = new JLabel("Estado:");
		lblTipoSala.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTipoSala.setBounds(50, 160, 150, 30);
		pnlFormulario.add(lblTipoSala);
		
		// Opciones para el desplegable
		String[] opcionesEstado = {"en diseño", "costura", "taller"};
		cmbEstado = new JComboBox<>(opcionesEstado);
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

	public String getIdTraje() {
		return txtIdTraje.getText();
	}

	public String getNombreTraje() {
		return txtNombreTraje.getText();
	}

	public String getEstado() {
		return cmbEstado.getSelectedItem().toString();
	}
	
	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnGuardarCambios() {
		return btnGuardarCambios;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}
	
	
}