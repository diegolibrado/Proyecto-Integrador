package controlador.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import controlador.ControladorMenuMaestro; 
import modelo.Modelo;
import vista.VentanaCrearCliente;
import vista.VentanaGestionCliente;
import vista.VentanaLogin;
import vista.VentanaMaestro;
import vista.VentanaModificarCliente;

public class ControladorMenuCliente implements ActionListener {

	private VentanaGestionCliente vGestionClientes;
	private Modelo m;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorMenuCliente(VentanaGestionCliente v, String rango, int id) {
		vGestionClientes = v;
		m = new Modelo();
		this.rangoUsuario = rango;
		this.idUsuario = id;
	}

	@Override
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
		} 
		else if (e.getSource().equals(vGestionClientes.getBtnCrear())) {
			VentanaCrearCliente vCrear = new VentanaCrearCliente(vGestionClientes.getRangoUsuario(), idUsuario);
			ControladorCrearCliente cCrear = new ControladorCrearCliente(vCrear, idUsuario);
			vCrear.setControlador(cCrear); 
			
			vCrear.setVisible(true);
			vGestionClientes.dispose();
			
		} 
		else if (e.getSource().equals(vGestionClientes.getBtnAtras())) {
			VentanaMaestro vMaestro = new VentanaMaestro("Menú Principal", rangoUsuario, idUsuario);
			ControladorMenuMaestro cMaestro = new ControladorMenuMaestro(vMaestro, rangoUsuario, idUsuario);
			vMaestro.setControlador(cMaestro);
			vMaestro.setVisible(true);
			vGestionClientes.dispose();
            
		} 
		else if (e.getSource().equals(vGestionClientes.getBtnModificar())) {
			int idCliente = vGestionClientes.getIdClienteSeleccionado();
			if (idCliente == -1) {
				JOptionPane.showMessageDialog(vGestionClientes, "Selecciona un cliente para modificar");
			} else {
				String nombre = vGestionClientes.getNombreClienteSeleccionado();
				String superpoder = vGestionClientes.getSuperpoderClienteSeleccionado();
				String colores = vGestionClientes.getColoresClienteSeleccionado();
				
				VentanaModificarCliente vModificarCliente = new VentanaModificarCliente(vGestionClientes.getRangoUsuario(), idCliente, nombre, superpoder, colores);
				ControladorModificarCliente cModificarCliente = new ControladorModificarCliente(vModificarCliente, idUsuario);
				vModificarCliente.setControladorModificar(cModificarCliente);
				vModificarCliente.setVisible(true);
				vGestionClientes.dispose();
			}
            
		} 
		else if (e.getSource().equals(vGestionClientes.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin c = new ControladorLogin(vLogin);
			vLogin.setControlador(c);
			vLogin.setVisible(true);
			vGestionClientes.dispose();
		}
	}
}