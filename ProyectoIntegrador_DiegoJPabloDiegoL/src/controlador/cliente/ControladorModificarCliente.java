package controlador.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import modelo.Modelo;
import modelo.Cliente;
import vista.VentanaModificarCliente;
import vista.VentanaGestionCliente;
import vista.VentanaLogin;

public class ControladorModificarCliente implements ActionListener {

	private VentanaModificarCliente vista;
	private int idUsuario;

	public ControladorModificarCliente(VentanaModificarCliente vista, int id) {
		this.vista = vista;
		this.idUsuario = id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Modelo modelo = new Modelo();

		// si se pulsa el boton de atrás
		if (e.getSource().equals(vista.getBtnAtras())) {
			regresarGestion(modelo);

		// si se pulsa cerrar sesión
		} else if (e.getSource().equals(vista.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin cLogin = new ControladorLogin(vLogin);
			vLogin.setControlador(cLogin);
			vLogin.setVisible(true);
			vista.dispose();

		// si se pulsa el botón de guardar cambios
		} else if (e.getSource().equals(vista.getBtnGuardarCambios())) {
			String idStr = vista.getIdCliente();
			String nombre = vista.getNombreCliente();
			String superpoder = vista.getSuperpoderCliente();
			String colores = vista.getColorCliente();

			if (nombre.isEmpty() || superpoder.isEmpty() || colores.isEmpty()) {
				JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
				return;
			}

			int idCliente;
			try {
				idCliente = Integer.parseInt(idStr);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(vista, "Error: El ID del cliente es inválido.");
				return;
			}

			// creamos el objeto cliente con los datos modificados
			Cliente clienteModificado = new Cliente();
			clienteModificado.setId(idCliente);
			clienteModificado.setNombre(nombre);
			clienteModificado.setSuperpoder(superpoder);
			clienteModificado.setColores(colores);

			// llamamos al metodo del modelo
			boolean exito = modelo.modificarCliente(clienteModificado);

			if (exito) {
				JOptionPane.showMessageDialog(vista, "Cliente actualizado correctamente.");
				regresarGestion(modelo);
			} else {
				JOptionPane.showMessageDialog(vista, "Error: No se pudo actualizar el cliente.");
			}
		}
	}

	// metodo para volver a la gestion con el controlador activado
	private void regresarGestion(Modelo modelo) {
		VentanaGestionCliente vG = new VentanaGestionCliente(vista.getRangoUsuario(), idUsuario);
		ControladorMenuCliente cG = new ControladorMenuCliente(vG, vista.getRangoUsuario(), idUsuario);
		vG.setControlador(cG); // esto es lo que hace que botones funcionen al volver
		vG.cargarDatosClientes(modelo.recuperarClientes());
		vG.setVisible(true);
		vista.dispose();
	}
}