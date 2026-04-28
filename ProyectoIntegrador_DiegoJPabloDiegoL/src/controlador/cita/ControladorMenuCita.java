package controlador.cita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import controlador.ControladorMenuMaestro;
import modelo.Modelo;
import vista.VentanaCrearCita;
import vista.VentanaGestionCita;
import vista.VentanaLogin;
import vista.VentanaMaestro;
import vista.VentanaModificarCitas;

public class ControladorMenuCita implements ActionListener {

	private VentanaGestionCita vGestionCita;
	private Modelo m;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorMenuCita(VentanaGestionCita v, String rango, int id) {
		vGestionCita = v;
		m = new Modelo();
		rangoUsuario = rango;
		idUsuario = id;
	}

	public void actionPerformed(ActionEvent e) {

		// ELIMINAR
		if (e.getSource().equals(vGestionCita.getBtnEliminarCita())) {
			int idCita = vGestionCita.getIdCitaSeleccionado();
			if (idCita == -1) {
				JOptionPane.showMessageDialog(vGestionCita, "Selecciona la cita que desea eliminar");
			} else {
				int confirmacion = JOptionPane.showConfirmDialog(vGestionCita,
						"¿Seguro que deseas eliminar el taller con ID " + idCita + "?", "Confirmar",
						JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {
					if (m.eliminarCita(idCita)) {
						JOptionPane.showMessageDialog(vGestionCita, "Cita eliminada");
						vGestionCita.cargarDatosCitas(m.recuperarCitas());
					} else {
						JOptionPane.showMessageDialog(vGestionCita, "Error al eliminar la cita");
					}
				}
			}
		// CREAR
		} else if (e.getSource().equals(vGestionCita.getBtnCrearCita())) {
			VentanaCrearCita vCrearCita = new VentanaCrearCita(rangoUsuario);
			ControladorCrearCita cCrearCita = new ControladorCrearCita(vCrearCita, rangoUsuario, idUsuario);
			vCrearCita.setControlador(cCrearCita);
			vCrearCita.setVisible(true);
			vGestionCita.dispose();
		// ATRAS
		} else if (e.getSource().equals(vGestionCita.getBtnAtras())) {
			// Verificamos si es Maestro para volver al menú o al Login
		    if (rangoUsuario.equalsIgnoreCase("Maestro")) {
		        VentanaMaestro vMaestro = new VentanaMaestro("Menú Maestro", rangoUsuario, idUsuario);
		        ControladorMenuMaestro cMaestro = new ControladorMenuMaestro(vMaestro, rangoUsuario, idUsuario);
		        vMaestro.setControlador(cMaestro);
		        vMaestro.setVisible(true); 
		    } else {
		        // Si es aprendiz u oficial, vuelve al login
		        VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
		        ControladorLogin c = new ControladorLogin(vLogin);
		        vLogin.setControlador(c);
		        vLogin.setVisible(true);
		    }
		    vGestionCita.dispose();
		// MODIFICAR
		} else if (e.getSource().equals(vGestionCita.getBtnModificarCita())) {
			int filaSeleccionada = vGestionCita.getTable().getSelectedRow();
			
			if (filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(vGestionCita, "Seleccione una cita para modificar");
			} else {
				try {
					// Datos tabla
					int idCita = (int) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 0);
			        java.util.Date fecha = (java.util.Date) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 1);
			        java.sql.Time hora = (java.sql.Time) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 2);
			        int duracion = (int) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 3);
			        String cliente = (String) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 4);
			        String taller = (String) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 5);
			        String empleado = (String) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 6);
			        String traje = (String) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 7);
			        
			        // Ventana y controlador
			        VentanaModificarCitas vModificarCitas = new VentanaModificarCitas(rangoUsuario, idUsuario);
			        ControladorModificarCita cModificarCita = new ControladorModificarCita(vModificarCitas, rangoUsuario, idUsuario);
			        
			        vModificarCitas.setControlador(cModificarCita);
			        vModificarCitas.cargarDatosEnFormulario(idCita, fecha, hora, duracion, cliente, taller, empleado, traje);
			        
			        vModificarCitas.setVisible(true);
			        vGestionCita.dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vGestionCita, "Error en la modificacion del la cita.");
					ex.printStackTrace();
				}    
			}
		// CERRAR SESION
		} else if (e.getSource().equals(vGestionCita.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			vLogin.setControlador(new ControladorLogin(vLogin));
			vLogin.setVisible(true);
			vGestionCita.dispose();
		}
	}

}