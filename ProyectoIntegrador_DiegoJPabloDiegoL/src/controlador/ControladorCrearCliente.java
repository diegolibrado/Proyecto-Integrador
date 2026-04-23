package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaCrearCliente;
import vista.VentanaGestionCliente;

public class ControladorCrearCliente implements ActionListener {

	private VentanaCrearCliente vista;
	
	public ControladorCrearCliente(VentanaCrearCliente vista) {
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent e) {
		// 1. Recogemos los datos de la vista de Cliente
		String idStr = vista.getIdCliente();
		String nombre = vista.getNombreCliente();
		String superpoder = vista.getSuperpoder();
		String colores = vista.getColores();

		// 2. Validaciones básicas
		if (idStr.isEmpty() || nombre.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos obligatorios.");
			return;
		}

		int idCliente = 0;
		try {
			idCliente = Integer.parseInt(idStr); 
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vista, "El ID del cliente debe ser un número entero.");
			return;
		}

		// 3. Conexión y guardado en la Base de Datos
		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();

		// Query adaptada a la tabla CLIENTE
		String query = "INSERT INTO CLIENTE (id_cliente, nombre, superpoder, colores) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pst = conexion.prepareStatement(query)) {
			pst.setInt(1, idCliente);
			pst.setString(2, nombre);
			pst.setString(3, superpoder);
			pst.setString(4, colores);

			int filasAfectadas = pst.executeUpdate();

			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(vista, "Cliente creado exitosamente.");
				
				// Volvemos a la ventana de gestión (SINGULAR)
				VentanaGestionCliente vGestionCliente = new VentanaGestionCliente(vista.getRangoUsuario());
				vGestionCliente.setVisible(true);
				vista.dispose(); 
			}

		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, "Error al crear el cliente en BD: \n" + sqlex.getMessage(), "Error SQL",
				JOptionPane.ERROR_MESSAGE);
		} finally {
			conector.cerrarConexion(conexion);
		}
	}
}