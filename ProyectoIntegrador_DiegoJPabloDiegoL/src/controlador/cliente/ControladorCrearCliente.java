package controlador.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import modelo.Modelo;
import modelo.Cliente;
import vista.VentanaCrearCliente;
import vista.VentanaGestionCliente;
import vista.VentanaLogin;

public class ControladorCrearCliente implements ActionListener {

	private VentanaCrearCliente vista;
	private int idUsuario;

	public ControladorCrearCliente(VentanaCrearCliente vista, int id) {
		this.vista = vista;
		int idUsuario = id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Modelo modelo = new Modelo();

		// Botón Atrás: Volver a la gestión de clientes
		if (e.getSource().equals(vista.getBtnAtras())) {
			VentanaGestionCliente vGestion = new VentanaGestionCliente(vista.getRangoUsuario(), idUsuario);
			// Cargamos los datos actualizados desde el modelo
			vGestion.cargarDatosClientes(modelo.recuperarClientes()); 
			vGestion.setVisible(true);
			vista.dispose();
			return;

		// Botón Cerrar Sesión
		} else if (e.getSource().equals(vista.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin cLogin = new ControladorLogin(vLogin);
			vLogin.setControlador(cLogin);
			vLogin.setVisible(true);
			vista.dispose();
			return;

		// Botón Guardar (Crear Cliente)
		} else if (e.getSource().equals(vista.getBtnGuardarCambios())) {
			// Solo pedimos Nombre, Superpoder y Color
			String nombre = vista.getNombreCliente();
			String superpoder = vista.getSuperpoderCliente();
			String color = vista.getColorCliente();

			// Validación de campos vacíos
			if (nombre.isEmpty() || superpoder.isEmpty() || color.isEmpty()) {
				JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
				return;
			}

			// Creamos el objeto Cliente 
			Cliente cliente = new Cliente();
			cliente.setNombre(nombre);
			cliente.setSuperpoder(superpoder);
			cliente.setColores(color);

			// Llamada al método del modelo para insertar en la BD
			if (modelo.crearCliente(cliente)) {
				JOptionPane.showMessageDialog(vista, "Cliente creado exitosamente");
				
				// Tras exito, volvemos a la ventana de gestión refrescada
				VentanaGestionCliente vGestion = new VentanaGestionCliente(vista.getRangoUsuario(), idUsuario);
				vGestion.cargarDatosClientes(modelo.recuperarClientes());
				vGestion.setVisible(true);
				vista.dispose();
			} else {
				JOptionPane.showMessageDialog(vista, "Error: No se pudo crear el cliente");
			}
		}
	}
}