package controlador.taller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaGestionTalleres;

public class ControladorEliminarTaller implements ActionListener {

	private VentanaGestionTalleres vista;

	public ControladorEliminarTaller(VentanaGestionTalleres vista) {
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		// 1. Obtenemos el ID del taller seleccionado en la vista
		int idTaller = vista.getIdTallerSeleccionado();

		// Si es -1, significa que no hay ninguna fila seleccionada
		if (idTaller == -1) {
			JOptionPane.showMessageDialog(vista, "Por favor, selecciona un taller de la tabla para eliminarlo.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 2. Pedimos confirmación al usuario antes de borrar
		int confirmacion = JOptionPane.showConfirmDialog(vista,
				"¿Estás seguro de que deseas eliminar el taller con ID " + idTaller + "?", "Confirmar eliminación",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirmacion == JOptionPane.YES_OPTION) {
			Modelo modelo = new Modelo();
			boolean exito = modelo.eliminarTaller(idTaller);

			if (exito) {
				JOptionPane.showMessageDialog(vista, "Taller eliminado correctamente.");
				vista.cargarDatosTalleres(modelo.recuperarTalleres());
			} else {
				JOptionPane.showMessageDialog(vista, "Error: No se pudo eliminar el taller.");
			}
		}
	}
}