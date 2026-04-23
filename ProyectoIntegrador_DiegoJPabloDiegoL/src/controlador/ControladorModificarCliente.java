package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaModificarCliente;
import vista.VentanaGestionCliente;

public class ControladorModificarCliente implements ActionListener {

	private VentanaModificarCliente vista;
	
	public ControladorModificarCliente(VentanaModificarCliente vista) {
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent e) {
		String idStr = vista.getIdCliente();
		String nombre = vista.getNombreCliente();
		String superpoder = vista.getSuperpoder();
		String colores = vista.getColores();

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "El nombre del cliente no puede estar vacío.");
			return;
		}

		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();
		
		// Hacemos un UPDATE en la tabla CLIENTE
		String query = "UPDATE CLIENTE SET nombre = ?, superpoder = ?, colores = ? WHERE id_cliente = ?";

		try (PreparedStatement pst = conexion.prepareStatement(query)) {
			pst.setString(1, nombre);
			pst.setString(2, superpoder);
			pst.setString(3, colores);
			pst.setInt(4, Integer.parseInt(idStr)); // Condición WHERE

			int resultado = pst.executeUpdate();
			if (resultado > 0) {
				JOptionPane.showMessageDialog(vista, "Cliente actualizado correctamente.");
				// Volvemos a la tabla (en SINGULAR)
				VentanaGestionCliente vGestionCliente = new VentanaGestionCliente(vista.getRangoUsuario());
				vGestionCliente.setVisible(true);
				vista.dispose();
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(vista, "Error al actualizar en BD: " + sqlex.getMessage());
		} finally {
			conector.cerrarConexion(conexion);
		}

	}
}