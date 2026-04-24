package controlador.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import modelo.Modelo;
import vista.VentanaCrearCliente;
import vista.VentanaGestionCliente;
import vista.VentanaLogin;
import vista.VentanaMaestro;
import vista.VentanaModificarCliente;

public class ControladorMenuCliente implements ActionListener {

	private VentanaGestionCliente vGestionClientes;
	private Modelo m;

	public ControladorMenuCliente(VentanaGestionCliente v) {
		vGestionClientes = v;
		m = new Modelo();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(vGestionClientes.getBtnEliminar())) {
			int idCliente = vGestionClientes.getIdClienteSeleccionado();
			if (idCliente == -1) {
				JOptionPane.showMessageDialog(vGestionClientes, "Selecciona el cliente que desea eliminar");
			} else {
				int confirmacion = JOptionPane.showConfirmDialog(vGestionClientes,
						"¿Seguro que deseas eliminar el cliente con ID " + idCliente + "?", "Confirmar",
						JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {
					if (m.eliminarCliente(idCliente)) {
						JOptionPane.showMessageDialog(vGestionClientes, "Cliente eliminado");
						vGestionClientes.cargarDatosClientes(m.recuperarClientes());
					} else {
						JOptionPane.showMessageDialog(vGestionClientes, "Error al eliminar el cliente");
					}
				}
			}
		} else if (e.getSource().equals(vGestionClientes.getBtnCrear())) {
			// Abrimos la ventana de Crear Cliente
			VentanaCrearCliente vCrearCliente = new VentanaCrearCliente(vGestionClientes.getRangoUsuario());
			ControladorCrearCliente cCrearCliente = new ControladorCrearCliente(vCrearCliente);
			vCrearCliente.setControladorGuardar(cCrearCliente);
			vCrearCliente.setVisible(true);
			vGestionClientes.dispose();
            
		} else if (e.getSource().equals(vGestionClientes.getBtnAtras())) {
			VentanaMaestro vMaestro = new VentanaMaestro("Menú Principal");
			vMaestro.setVisible(true);
			vGestionClientes.dispose();
            
		} else if (e.getSource().equals(vGestionClientes.getBtnModificar())) {
			int idCliente = vGestionClientes.getIdClienteSeleccionado();
			if (idCliente == -1) {
				JOptionPane.showMessageDialog(vGestionClientes, "Selecciona un cliente para modificar");
			} else {
				String nombre = vGestionClientes.getNombreClienteSeleccionado();
				String superpoder = vGestionClientes.getSuperpoderClienteSeleccionado();
				String colores = vGestionClientes.getColoresClienteSeleccionado();
				
				// Abrimos la ventana de Modificar Cliente pasándole los datos seleccionados
				VentanaModificarCliente vModificarCliente = new VentanaModificarCliente(vGestionClientes.getRangoUsuario(), idCliente, nombre, superpoder, colores);
				ControladorModificarCliente cModificarCliente = new ControladorModificarCliente(vModificarCliente);
				vModificarCliente.setControladorModificar(cModificarCliente);
				vModificarCliente.setVisible(true);
				vGestionClientes.dispose();
			}
            
		} else if (e.getSource().equals(vGestionClientes.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin c = new ControladorLogin(vLogin);
			vLogin.setControlador(c);
			vLogin.setVisible(true);
			vGestionClientes.dispose();
		}
	}
}