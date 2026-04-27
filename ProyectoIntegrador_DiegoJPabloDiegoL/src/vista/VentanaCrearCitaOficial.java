package vista;


import vista.oficial.VentanadeOficial;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.LineBorder;
import vista.VentanaLogin;

public class VentanaCrearCitaOficial extends JFrame {

	private String rangoUsuario;
	private JTextField txtNombreTraje, txtNombreResponsable, txtIdCita, txtNombreCliente, txtNombreTaller, txtNumAprendices;
	private JButton btnGuardarCambios;
	private JSpinner spinnerFecha, spinnerHora, spinnerDuracion;
	
	public VentanaCrearCitaOficial(String rango) {
		this.rangoUsuario = rango;
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

		JLabel lblCopyright = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblCopyright.setForeground(Color.WHITE);
		lblCopyright.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblCopyright);

		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		pnlBarraHorizontal.setLayout(null);
		getContentPane().add(pnlBarraHorizontal);

		// Título
		JLabel lblTitulo = new JLabel("Crear Cita (Oficial)");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 400, 40);
		getContentPane().add(lblTitulo);

		// Botón Guardar
		btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setBackground(new Color(165, 191, 201));
		btnGuardarCambios.setBounds(22, 231, 109, 30);
		pnlBarraHorizontal.add(btnGuardarCambios);

		// BOTÓN ATRÁS (Modificado para volver a VentanadeOficial)
		JButton btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.addActionListener(e -> {
			new VentanadeOficial("Menu Oficial").setVisible(true);
			dispose();
		});
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setBounds(22, 11, 30, 30);
		getContentPane().add(btnAtras);

		// Formulario (Mantenemos tu diseño)
		JPanel pnlFormulario = new JPanel();
		pnlFormulario.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlFormulario.setLayout(null);
		pnlFormulario.setBackground(new Color(165, 191, 201));
		pnlFormulario.setBounds(139, 25, 782, 236);
		pnlBarraHorizontal.add(pnlFormulario);

		// Campos (Simplificados para el código pero iguales en UI)
		txtNombreCliente = crearCampo(pnlFormulario, "Nombre Cliente:", 23);
		txtNombreTaller = crearCampo(pnlFormulario, "Nombre Taller:", 64);
		txtNombreResponsable = crearCampo(pnlFormulario, "Responsable:", 105);
		txtNombreTraje = crearCampo(pnlFormulario, "Nombre Traje:", 146);
		txtNumAprendices = crearCampo(pnlFormulario, "Aprendices:", 187);

		// Spinners
		spinnerFecha = new JSpinner(new SpinnerDateModel());
		spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
		spinnerFecha.setBounds(602, 27, 98, 23);
		pnlFormulario.add(spinnerFecha);

		spinnerHora = new JSpinner(new SpinnerDateModel());
		spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
		spinnerHora.setBounds(602, 68, 98, 23);
		pnlFormulario.add(spinnerHora);

		spinnerDuracion = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		spinnerDuracion.setBounds(602, 109, 98, 23);
		pnlFormulario.add(spinnerDuracion);

		// Fondo
		JLabel lblFondo = new JLabel(new ImageIcon("img\\fondo.jpeg"));
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
	}

	private JTextField crearCampo(JPanel pnl, String texto, int y) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(new Font("Verdana", Font.BOLD, 14));
		lbl.setBounds(49, y, 200, 30);
		pnl.add(lbl);
		JTextField txt = new JTextField();
		txt.setBounds(263, y, 150, 30);
		pnl.add(txt);
		return txt;
	}
	
    // --- GETTERS ---
	public String getNombreCliente() { return txtNombreCliente.getText(); }
	public String getNombreTaller() { return txtNombreTaller.getText(); }
	public String getNombreResponsable() { return txtNombreResponsable.getText(); }
	public String getNombreTraje() { return txtNombreTraje.getText(); }
	public JButton getBtnGuardarCambios() { return btnGuardarCambios; }
}