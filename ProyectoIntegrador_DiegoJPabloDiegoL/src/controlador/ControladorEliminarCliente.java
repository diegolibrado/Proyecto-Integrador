package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaGestionCliente;

public class ControladorEliminarCliente implements ActionListener {

	private VentanaGestionCliente vista;

	public ControladorEliminarCliente(VentanaGestionCliente vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int idCliente = vista.getIdClienteSeleccionado();

		if (idCliente == -1) {
			JOptionPane.showMessageDialog(vista, "Por favor, selecciona un cliente de la tabla.");
			return;
		}

		int confirmacion = JOptionPane.showConfirmDialog(vista, 
				"¿Eliminar cliente con ID " + idCliente + "?", 
				"Confirmar", JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			Modelo conector = new Modelo();
			Connection conexion = conector.getConexion();
			String query = "DELETE FROM CLIENTE WHERE id_cliente = ?";

			try (PreparedStatement pst = conexion.prepareStatement(query)) {
				pst.setInt(1, idCliente);
				if (pst.executeUpdate() > 0) {
					JOptionPane.showMessageDialog(vista, "Eliminado correctamente.");
					// Refrescamos la tabla
					vista.cargarDatosCliente(); 
				}
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(vista, "Error: " + sqlex.getMessage());
			} finally {
				conector.cerrarConexion(conexion);
			}
		}
	}
}