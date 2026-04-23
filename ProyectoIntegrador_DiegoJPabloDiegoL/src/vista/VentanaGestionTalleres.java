package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.taller.ControladorMenuTaller;
import modelo.Taller;

public class VentanaGestionTalleres extends JFrame {

	private String rangoUsuario;
	private JButton btnEliminar;
	private JButton btnCrear;
	private JButton btnModificar;
	private JButton btnAtras;
	private JButton btnCerrarSesion;
	private DefaultTableModel modeloTabla;
	private JTable table;
	
	public VentanaGestionTalleres(String rango) {
		this.rangoUsuario = rango;
		configInicial();
		inicializarComponentes();
	}

	private void configInicial() {
		setTitle("Gestión de Talleres");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(960, 540);
		setLocationRelativeTo(null);
	}

	private void inicializarComponentes() {
		// Instanciamos el controlador que gestionará los eventos de esta ventana
		ControladorMenuTaller controlador = new ControladorMenuTaller(this);
		
		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(new Color(72, 119, 109));
		pnlFooter.setBounds(0, 481, 944, 20);
		getContentPane().add(pnlFooter);

		JLabel lblCopyright = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(Color.WHITE);
		lblCopyright.setFont(new Font("Verdana", Font.PLAIN, 10));
		pnlFooter.add(lblCopyright);

		// Panel horizontal central
		JPanel pnlBarraHorizontal = new JPanel();
		pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
		pnlBarraHorizontal.setBounds(0, 111, 944, 282);
		pnlBarraHorizontal.setLayout(null);
		getContentPane().add(pnlBarraHorizontal);

		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Gestión de Talleres");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 400, 40);
		getContentPane().add(lblTitulo);

		// --- BOTONES ---
		btnCrear = new JButton("Crear");
		btnCrear.setBackground(new Color(165, 191, 201));
		btnCrear.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCrear.setBounds(22, 25, 109, 30);
		btnCrear.addActionListener(controlador);
		pnlBarraHorizontal.add(btnCrear);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(165, 191, 201));
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminar.setBounds(22, 63, 109, 30);
		btnEliminar.addActionListener(controlador);
		pnlBarraHorizontal.add(btnEliminar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(165, 191, 201));
		btnModificar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnModificar.setBounds(22, 101, 109, 30);
		btnModificar.addActionListener(controlador);
		pnlBarraHorizontal.add(btnModificar);

		btnAtras = new JButton("");
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setBounds(22, 11, 30, 30);
		btnAtras.addActionListener(controlador);
		getContentPane().add(btnAtras);

		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		btnCerrarSesion.addActionListener(controlador);
		getContentPane().add(btnCerrarSesion);

		// --- TABLA ---
		JPanel pnlTablaContainer = new JPanel();
		pnlTablaContainer.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlTablaContainer.setBackground(new Color(165, 191, 201));
		pnlTablaContainer.setBounds(150, 25, 782, 236);
		pnlTablaContainer.setLayout(null);
		pnlBarraHorizontal.add(pnlTablaContainer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 762, 216);
		pnlTablaContainer.add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "NOMBRE", "TIPO"});
		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);

		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
		getContentPane().add(lblFondo);
	}
	
	public void cargarDatosTalleres(ArrayList<Taller> datosTaller) {
		modeloTabla.setRowCount(0);
		for(Taller t : datosTaller) {
			modeloTabla.addRow(new Object[]{
				t.getId_taller(),
				t.getNombre(),
				t.getTipo_sala()
			});
		}
	}
	
	public int getIdTallerSeleccionado() {
		int filaSeleccionada = table.getSelectedRow();
		return (filaSeleccionada != -1) ? (int) modeloTabla.getValueAt(filaSeleccionada, 0) : -1;
	}
	
	public String getNombreTallerSeleccionado() {
		int fila = table.getSelectedRow();
		return (fila != -1) ? modeloTabla.getValueAt(fila, 1).toString() : null;
	}

	public String getTipoTallerSeleccionado() {
		int fila = table.getSelectedRow();
		return (fila != -1) ? modeloTabla.getValueAt(fila, 2).toString() : null;
	}

	// Getters para el controlador
	public JButton getBtnEliminar() { return btnEliminar; }
	public JButton getBtnCrear() { return btnCrear; }
	public JButton getBtnModificar() { return btnModificar; }
	public JButton getBtnAtras() { return btnAtras; }
	public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
	public String getRangoUsuario() { return rangoUsuario; }
}
