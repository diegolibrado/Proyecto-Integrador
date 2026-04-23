package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorMenuPpal;
import controlador.taller.ControladorEliminarTaller;
import controlador.taller.ControladorMenuTaller;
import controlador.taller.ControladorModificarTaller;
import modelo.Cita;
import modelo.Modelo;
import modelo.Taller;

public class VentanaGestionTalleres extends JFrame {

	// Declaracion de variables
	private String rangoUsuario;
	private JButton btnEliminar;
	private JButton btnCrear;
	private JButton btnModificar;
	private JButton btnAtras;
	private DefaultTableModel modeloTabla;
	private JTable table;
	private JButton btnCerrarSesion;
	
	public VentanaGestionTalleres(String rango) {
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

		ControladorMenuTaller controlador = new ControladorMenuTaller(this);
		
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

<<<<<<< HEAD
		// Boton Cerrar Sesion
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCerrarSesion.setBackground(new Color(165, 191, 201));
		btnCerrarSesion.setBounds(787, 68, 135, 30);
		getContentPane().add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(controlador);
		
=======
>>>>>>> caec6d45d0cee0e81dcbe1ce763a2f7177665918
		// Titulo Pagina
		JLabel lblTitulo = new JLabel("Gestión de Talleres");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setBounds(22, 63, 333, 40);
		getContentPane().add(lblTitulo);

		// BOTONES
		btnCrear = new JButton("Crear");
		btnCrear.setBackground(new Color(165, 191, 201));
		btnCrear.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCrear.setBounds(22, 25, 109, 30);
		pnlBarraHorizontal.add(btnCrear);
		btnCrear.addActionListener(controlador);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(165, 191, 201));
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminar.setBounds(22, 63, 109, 30);
		pnlBarraHorizontal.add(btnEliminar);
		ControladorEliminarTaller cEliminar = new ControladorEliminarTaller(this);
		btnEliminar.addActionListener(controlador);

		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(165, 191, 201));
		btnModificar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnModificar.setBounds(22, 101, 109, 30);
		pnlBarraHorizontal.add(btnModificar);
		btnModificar.addActionListener(controlador);


<<<<<<< HEAD
		btnAtras = new JButton("");
=======
		        // 3. Abrimos la ventana de Modificar pasándole el rango Y los datos del taller
		        VentanaModificarTaller vModificar = new VentanaModificarTaller(rangoUsuario, id, nombre, tipo);
		        controlador.ControladorModificarTaller cModificar = new controlador.ControladorModificarTaller(vModificar);
		        vModificar.setControladorModificar(cModificar);
		        vModificar.setVisible(true);
		        dispose();
		    }
		});
		

		JButton btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnGuardarCambios.setBackground(new Color(165, 191, 201));
		btnGuardarCambios.setBounds(22, 141, 109, 30);
		pnlBarraHorizontal.add(btnGuardarCambios);

		JButton btnAtras = new JButton("");
>>>>>>> caec6d45d0cee0e81dcbe1ce763a2f7177665918
		ImageIcon iconoAtras = new ImageIcon("img\\flecha_izq.png");
		// Para que se autoescale y se coloque el tamaño correctamente
		java.awt.Image imgAtras = iconoAtras.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnAtras.setIcon(new ImageIcon(imgAtras));
		btnAtras.setBackground(new Color(165, 191, 201));
		btnAtras.setFont(new Font("Verdana", Font.PLAIN, 5));
		btnAtras.setBounds(22, 11, 30, 30); // Posición arriba a la izquierda
		getContentPane().add(btnAtras);
		btnAtras.addActionListener(controlador);
		
		// Panel con informacion
		JPanel pnlBarraHorizontal_1 = new JPanel();
		pnlBarraHorizontal_1.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
		pnlBarraHorizontal_1.setLayout(null);
		pnlBarraHorizontal_1.setForeground(new Color(196, 204, 203));
		pnlBarraHorizontal_1.setBackground(new Color(165, 191, 201));
		pnlBarraHorizontal_1.setBounds(150, 25, 782, 236);
		pnlBarraHorizontal.add(pnlBarraHorizontal_1);

		// ScrollPane para la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 762, 216);
		pnlBarraHorizontal_1.add(scrollPane);

		// Tabla
		table = new JTable();
		scrollPane.setViewportView(table);
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("NOMBRE");
		modeloTabla.addColumn("TIPO");

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);
		
				// Boton Cerrar Sesion
				JButton btnCerrarSesion = new JButton("Cerrar sesión");
				btnCerrarSesion.setBounds(5, 211, 140, 30);
				pnlBarraHorizontal.add(btnCerrarSesion);
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

		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 944, 501);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
	}
	
	//mover a controlador
	public void cargarDatosTalleres(ArrayList<Taller> datosTaller) {
		modeloTabla.setRowCount(0);
		for(Taller t : datosTaller) {
			Object[] fila = new Object[3];
			fila[0] = t.getId_taller();
			fila[1] = t.getNombre();
			fila[2] = t.getTipo_sala();
			
			modeloTabla.addRow(fila);
		}
	}
	
	/**
	 * Método para obtener el ID del taller seleccionado en la tabla.
	 * Devuelve -1 si no hay ninguna fila seleccionada.
	 */
	public int getIdTallerSeleccionado() {
		int filaSeleccionada = table.getSelectedRow();
		if (filaSeleccionada == -1) {
			return -1; // Nada seleccionado
		}
		// Sabiendo que el ID está en la columna 0
		return (int) modeloTabla.getValueAt(filaSeleccionada, 0);
	}
	
	public String getNombreTallerSeleccionado() {
	    int fila = table.getSelectedRow();
	    return modeloTabla.getValueAt(fila, 1).toString();
	}

	public String getTipoTallerSeleccionado() {
	    int fila = table.getSelectedRow();
	    return modeloTabla.getValueAt(fila, 2).toString();
	}

	/**
	 * @return the btnEliminar
	 */
	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	/**
	 * @return the btnCrear
	 */
	public JButton getBtnCrear() {
		return btnCrear;
	}

	/**
	 * @return the btnModificar
	 */
	public JButton getBtnModificar() {
		return btnModificar;
	}

	/**
	 * @return the btnAtras
	 */
	public JButton getBtnAtras() {
		return btnAtras;
	}

	/**
	 * @return the btnCerrarSesion
	 */
	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	/**
	 * @return the rangoUsuario
	 */
	public String getRangoUsuario() {
		return rangoUsuario;
	}
}
