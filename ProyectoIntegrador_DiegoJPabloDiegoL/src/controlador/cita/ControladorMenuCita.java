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
		    int filaSeleccionada = vGestionCita.getTable().getSelectedRow();
		    if (filaSeleccionada == -1) {
		        JOptionPane.showMessageDialog(vGestionCita, "Selecciona una cita");
		        return;
		    }

		    if (rangoUsuario.equals("Oficial")) {
		        String responsableCita = (String) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 6);
		        String nombreEmpleado = m.obtenerNombreEmpleado(idUsuario);

		        if (!responsableCita.equalsIgnoreCase(nombreEmpleado)) {
		            JOptionPane.showMessageDialog(vGestionCita, "Solo puedes eliminar tus propias citas.");
		            return;
		        }
		    }

		    int idCita = (int) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 0); 
		    
		    int confirm = JOptionPane.showConfirmDialog(vGestionCita, "¿Eliminar cita con ID " + idCita + "?");
		    if (confirm == JOptionPane.YES_OPTION) {
		        if (m.eliminarCita(idCita)) {
		            vGestionCita.cargarDatosCitas(m.recuperarCitas());
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
				if (rangoUsuario.equals("Oficial")) {
			        String responsableCita = (String) vGestionCita.getModeloTabla().getValueAt(filaSeleccionada, 6);
			        String nombreUsuarioActual = m.obtenerNombreEmpleado(idUsuario);

			        if (!responsableCita.equalsIgnoreCase(nombreUsuarioActual)) {
			            JOptionPane.showMessageDialog(vGestionCita, "Acceso denegado: Solo puedes modificar tus propias citas.");
			            return;
			        }
			    }
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