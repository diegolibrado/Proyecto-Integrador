package controlador.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		// 1. Obtenemos el ID del cliente seleccionado en la vista
		int idCliente = vista.getIdClienteSeleccionado();

		// Si es -1, significa que no hay ninguna fila seleccionada
		if (idCliente == -1) {
			JOptionPane.showMessageDialog(vista, "Por favor, selecciona un cliente de la tabla para eliminarlo.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 2. Pedimos confirmación al usuario antes de borrar
		int confirmacion = JOptionPane.showConfirmDialog(vista,
				"¿Estás seguro de que deseas eliminar el cliente con ID " + idCliente + "?", "Confirmar eliminación",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirmacion == JOptionPane.YES_OPTION) {
			Modelo modelo = new Modelo();
			boolean exito = modelo.eliminarCliente(idCliente);

			if (exito) {
				JOptionPane.showMessageDialog(vista, "Cliente eliminado correctamente.");
				// Refrescamos la tabla de clientes
				vista.cargarDatosClientes(modelo.recuperarClientes());
			} else {
				JOptionPane.showMessageDialog(vista, "Error: No se pudo eliminar el cliente.");
			}
		}
	}
}