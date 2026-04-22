package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaCrearTaller;
import vista.VentanaGestionTalleres;
import vista.VentanaModificarTaller;

public class ControladorCrearTaller implements ActionListener {

	private VentanaCrearTaller vista;
	
	public ControladorCrearTaller(VentanaCrearTaller vista) {
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent e) {
		// 1. Recogemos los datos (del TextField y del ComboBox)
		String idStr = vista.getIdTaller();
		String nombre = vista.getNombreTaller();
		// Aquí sacamos el texto seleccionado en el ComboBox
		String tipo = vista.getTipoSala();

		// 2. Validaciones básicas
		if (idStr.isEmpty() || nombre.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
			return;
		}

		int idTaller = 0;
		try {
			idTaller = Integer.parseInt(idStr); // Convertimos el ID a número
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vista, "El ID del taller debe ser un número entero.");
			return;
		}

		// 3. Conexión y guardado en la Base de Datos
		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();

		// Utilizamos PreparedStatement para evitar inyección SQL
		String query = "INSERT INTO TALLER (id_taller, nombre_sala, tipo_sala) VALUES (?, ?, ?)";

		try (PreparedStatement pst = conexion.prepareStatement(query)) {
			// Asignamos los valores a los interrogantes de la query
			pst.setInt(1, idTaller);
			pst.setString(2, nombre);
			pst.setString(3, tipo);

			// Ejecutamos la consulta
			int filasAfectadas = pst.executeUpdate();

			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(vista, "Taller creado exitosamente.");
				
				// Volvemos automáticamente a la ventana de gestión para ver el nuevo taller
				VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(vista.getRangoUsuario());
				vGestionTalleres.setVisible(true);
				
				vista.dispose(); // Cerramos la ventana de creación
			}

		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, "Error al crear el taller en BD: \n" + sqlex.getMessage(), "Error SQL",
				JOptionPane.ERROR_MESSAGE);
		} finally {
			conector.cerrarConexion(conexion);
		}
	}
}
