package controlador.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import controlador.cita.ControladorCrearCita;
import modelo.Modelo;
import modelo.Cliente;
import vista.VentanaCrearCita;
import vista.VentanaCrearCliente;
import vista.VentanaGestionCliente;

public class ControladorCrearCliente implements ActionListener {

	private VentanaCrearCliente vista;
	private int idUsuario;
	private String rangoUsuario;

	public ControladorCrearCliente(VentanaCrearCliente vista, int id) {
		this.vista = vista;
		this.idUsuario = id;
		this.rangoUsuario = vista.getRangoUsuario();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Modelo m = new Modelo();
 
		if (e.getSource().equals(vista.getBtnAtras())) {
			volver(m);
		} else if (e.getSource().equals(vista.getBtnGuardarCambios())) { // para guardar los cambios
			Cliente c = new Cliente();
			c.setNombre(vista.getNombreCliente());
			c.setSuperpoder(vista.getSuperpoderCliente());
			c.setColores(vista.getColorCliente());

			if (m.crearCliente(c)) {
				JOptionPane.showMessageDialog(vista, "Cliente creado exitosamente");
				volver(m);
			} else {
				JOptionPane.showMessageDialog(vista, "Error al crear cliente");
			}
		}
	}

	// metodo de volver
	private void volver(Modelo m) {
		String origen = vista.getVentanaOrigen();
		if (origen != null && origen.equals("Crear Cita")) {
			VentanaCrearCita vCrearCita = new VentanaCrearCita(rangoUsuario);
			ControladorCrearCita cCrearCita = new ControladorCrearCita(vCrearCita, rangoUsuario, idUsuario);
			vCrearCita.setControlador(cCrearCita);
			vCrearCita.setVisible(true);
			vista.dispose();
		} else {
			VentanaGestionCliente vG = new VentanaGestionCliente(vista.getRangoUsuario(), idUsuario);
			ControladorMenuCliente cG = new ControladorMenuCliente(vG, vista.getRangoUsuario(), idUsuario);
			vG.setControlador(cG);
			vG.cargarDatosClientes(m.recuperarClientes());
			vG.setVisible(true);
			vista.dispose();			
		}

		
	}
}