package controlador.cita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import controlador.ControladorMenuMaestro;
import controlador.taller.ControladorModificarTaller;
import modelo.Cita;
import modelo.Modelo;
import modelo.Taller;
import vista.VentanaCrearCita;
import vista.VentanaCrearTaller;
import vista.VentanaGestionCita;
import vista.VentanaGestionTalleres;
import vista.VentanaLogin;
import vista.VentanaMaestro;
import vista.VentanaModificarTaller;

public class ControladorMenuCita implements ActionListener {

	private VentanaGestionCita vGestionCita;
	private Modelo m;
	private String rangoUsuario;
	private int idEmpleadoLogin;

	public ControladorMenuCita(VentanaGestionCita v, String rango, int idUsuario) {
		vGestionCita = v;
		m = new Modelo();
		rangoUsuario = rango;
		idEmpleadoLogin = idUsuario;
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
			ControladorCrearCita cCrearCita = new ControladorCrearCita(vCrearCita, rangoUsuario, idEmpleadoLogin);
			vCrearCita.getBtnGuardarCambios().addActionListener(cCrearCita);
			vCrearCita.setVisible(true);
			vGestionCita.dispose();
		// ATRAS
		} else if (e.getSource().equals(vGestionCita.getBtnAtras())) {
			// Verificamos si es Maestro para volver al menú o al Login
		    if (rangoUsuario.equalsIgnoreCase("Maestro")) {
		        // ERROR: Solo creabas la ventana, pero no el controlador
		        VentanaMaestro vMaestro = new VentanaMaestro("Menú Maestro", rangoUsuario, idEmpleadoLogin);
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
//			int idTaller = vGestionCita.getIdCitaSeleccionado();
//			if (idTaller == -1) {
//				JOptionPane.showMessageDialog(vGestionCita, "Selecciona un taller para modificar");
//			} else {
//				String nombreTaller = vGestionCita.getNombreTallerSeleccionado();
//				String tipoTaller = vGestionCita.getTipoTallerSeleccionado();
//				
//				// 3. Abrimos la ventana de Modificar pasándole el rango Y el ID del taller
//				VentanaModificarTaller vModificarTaller = new VentanaModificarTaller(vGestionCita.getRangoUsuario(), idTaller, nombreTaller, tipoTaller);
//				ControladorModificarTaller cModificarTaller = new ControladorModificarTaller(vModificarTaller);
//				vModificarTaller.setControladorModificar(cModificarTaller);
//				vModificarTaller.setVisible(true);
//				vGestionCita.dispose();
//			}
		// CERRAR SESION
		} else if (e.getSource().equals(vGestionCita.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			vLogin.setControlador(new ControladorLogin(vLogin));
			vLogin.setVisible(true);
			vGestionCita.dispose();
		}
	}

}
