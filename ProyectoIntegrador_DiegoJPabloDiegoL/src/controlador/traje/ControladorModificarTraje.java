package controlador.traje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import modelo.Modelo;
import modelo.Traje; // <- Importación correcta
import vista.VentanaModificarTraje;
import vista.VentanaGestionTrajes;
import vista.VentanaLogin;

public class ControladorModificarTraje implements ActionListener {

	private VentanaModificarTraje vista;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorModificarTraje(VentanaModificarTraje ventana, String rango, int id) {
	    this.vista = ventana;
	    this.rangoUsuario = rango;
	    this.idUsuario = id;
	}

	public void actionPerformed(ActionEvent e) {
		Modelo modelo = new Modelo();

		// Si se pulsa el boton de atras
		if (e.getSource().equals(vista.getBtnAtras())) {
			VentanaGestionTrajes vGestionTrajes = new VentanaGestionTrajes(vista.getRangoUsuario(), idUsuario);
			vGestionTrajes.cargarDatosTrajes(modelo.recuperarTrajes());
			vGestionTrajes.setVisible(true);
			vista.dispose();
			return;
		} else if (e.getSource().equals(vista.getBtnCerrarSesion())){
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin c = new ControladorLogin(vLogin);
			vLogin.setControlador(c);
			vLogin.setVisible(true);
			vista.dispose();
		} else if (e.getSource().equals(vista.getBtnGuardarCambios())){
			
			String idStr = vista.getIdTraje(); 
			String nombre = vista.getNombreTraje();
			String estado = vista.getEstado();

			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(vista, "El nombre del traje no puede estar vacío.");
				return;
			}

			int idTraje;
			try {
				idTraje = Integer.parseInt(idStr);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(vista, "Error: El ID del traje es invalido.");
				return;
			}

			Traje trajeModificado = new Traje();
			trajeModificado.setId_traje(idTraje);
			trajeModificado.setNombre(nombre);
			trajeModificado.setEstado(estado);

			boolean exito = modelo.modificarTraje(trajeModificado);

			if (exito) {
				JOptionPane.showMessageDialog(vista, "Traje actualizado correctamente.");
				
				// OJO AQUI: Le pasamos idUsuario a la ventana de gestión para mantener la sesión, no el idTraje
				VentanaGestionTrajes vGestionTrajes = new VentanaGestionTrajes(vista.getRangoUsuario(), idUsuario);
				vGestionTrajes.cargarDatosTrajes(modelo.recuperarTrajes());
				vGestionTrajes.setVisible(true);
				vista.dispose();
			} else {
				JOptionPane.showMessageDialog(vista, "Error: No se pudo actualizar el traje");
			}
		}
	}
}