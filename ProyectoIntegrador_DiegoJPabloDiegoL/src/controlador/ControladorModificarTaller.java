package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaModificarTaller;
import vista.VentanaGestionTalleres;
import vista.VentanaModificarTaller;

public class ControladorModificarTaller implements ActionListener {

	private VentanaModificarTaller vista;
	
	public ControladorModificarTaller(VentanaModificarTaller vista) {
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent e) {
		String idStr = vista.getIdTaller();
		String nombre = vista.getNombreTaller();
		String tipo = vista.getTipoSala();

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "El nombre del taller no puede estar vacío.");
			return;
		}

		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();
		
		// Hacemos un UPDATE en vez de un INSERT
		String query = "UPDATE TALLER SET nombre_sala = ?, tipo_sala = ? WHERE id_taller = ?";

		try (PreparedStatement pst = conexion.prepareStatement(query)) {
			pst.setString(1, nombre);
			pst.setString(2, tipo);
			pst.setInt(3, Integer.parseInt(idStr)); // Condición WHERE

			int resultado = pst.executeUpdate();
			if (resultado > 0) {
				JOptionPane.showMessageDialog(vista, "Taller actualizado correctamente.");
				// Volvemos a la tabla
				VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(vista.getRangoUsuario());
				vGestionTalleres.setVisible(true);
				vista.dispose();
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(vista, "Error al actualizar en BD: " + sqlex.getMessage());
		} finally {
			conector.cerrarConexion(conexion);
		}

	}
}
