package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Modelo;
import vista.VentanaGestionTrajes;
import vista.VentanaLogin;
import vista.VentanaMaestro;

public class ControladorMenuTraje implements ActionListener {

	private VentanaGestionTrajes vTrajes;
	private Modelo m;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorMenuTraje(VentanaGestionTrajes v, String rango, int id) {
		this.vTrajes = v;
		this.m = new Modelo();
		this.rangoUsuario = rango;
		this.idUsuario = id;
		
		// Al instanciar el controlador, cargamos los datos directamente
		cargarDatosTrajes();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// BOTON ATRAS
		if (e.getSource().equals(vTrajes.getBtnAtras())) {
			VentanaMaestro vMaestro = new VentanaMaestro("Menú Maestro", rangoUsuario, idUsuario);
			ControladorMenuMaestro cMaestro = new ControladorMenuMaestro(vMaestro, rangoUsuario, idUsuario);
			vMaestro.setControlador(cMaestro);
			vMaestro.setVisible(true);
			vTrajes.dispose();
			
		// BOTON CERRAR SESION
		} else if (e.getSource().equals(vTrajes.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin c = new ControladorLogin(vLogin);
			vLogin.setControlador(c);
			vLogin.setVisible(true);
			vTrajes.dispose();
		}
	}

	public void cargarDatosTrajes() {
		DefaultTableModel modeloTabla = vTrajes.getModeloTabla();
		modeloTabla.setRowCount(0); 

		Connection conexion = m.getConexion();
		String query = "SELECT t.id_traje, t.nombre, t.estado, cl.nombre AS cliente " + 
		               "FROM TRAJE t " + 
				       "JOIN CLIENTE cl ON t.id_cliente = cl.id_cliente";
		
		try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {
			while (rs.next()) {
				Object[] fila = new Object[4];
				fila[0] = rs.getInt("id_traje");
				fila[1] = rs.getString("nombre");
				fila[2] = rs.getString("estado");
				fila[3] = rs.getString("cliente");
				modeloTabla.addRow(fila);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de SQL al cargar trajes: " + e.getMessage());
		} finally {
			m.cerrarConexion(conexion);
		}
	}
}