package controlador.taller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import controlador.ControladorMenuMaestro;
import modelo.Cita;
import modelo.Modelo;
import modelo.Taller;
import vista.VentanaCrearTaller;
import vista.VentanaGestionCita;
import vista.VentanaGestionTalleres;
import vista.VentanaLogin;
import vista.VentanaMaestro;
import vista.VentanaModificarTaller;

public class ControladorMenuTaller implements ActionListener {

	private VentanaGestionTalleres vGestionTalleres;
	private Modelo m;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorMenuTaller(VentanaGestionTalleres v, String rango, int id) {
		vGestionTalleres = v;
		m = new Modelo();
		rangoUsuario = rango;
		idUsuario = id;
	}
 
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(vGestionTalleres.getBtnEliminar())) {
			int idTaller = vGestionTalleres.getIdTallerSeleccionado();
			if (idTaller == -1) {
				JOptionPane.showMessageDialog(vGestionTalleres, "Selecciona el taller que desea eliminar");
			} else {
				int confirmacion = JOptionPane.showConfirmDialog(vGestionTalleres,
						"¿Seguro que deseas eliminar el taller con ID " + idTaller + "?", "Confirmar",
						JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {
					Modelo modelo = new Modelo();
					if (m.eliminarTaller(idTaller)) {
						JOptionPane.showMessageDialog(vGestionTalleres, "Taller eliminado");
						vGestionTalleres.cargarDatosTalleres(m.recuperarTalleres());
					} else {
						JOptionPane.showMessageDialog(vGestionTalleres, "Error al eliminar el taller");
					}
				}
			}
		} else if (e.getSource().equals(vGestionTalleres.getBtnCrear())) {
			VentanaCrearTaller vCrearTaller = new VentanaCrearTaller(vGestionTalleres.getRangoUsuario());
			ControladorCrearTaller cCrearTaller = new ControladorCrearTaller(vCrearTaller, rangoUsuario, idUsuario);
			vCrearTaller.setControladorGuardar(cCrearTaller);
			vCrearTaller.setVisible(true);
			vGestionTalleres.dispose();
		} else if (e.getSource().equals(vGestionTalleres.getBtnAtras())) {
			VentanaMaestro vMaestro = new VentanaMaestro("Gestion de citas", rangoUsuario, idUsuario);
			ControladorMenuMaestro cMaestro = new ControladorMenuMaestro(vMaestro, rangoUsuario, idUsuario);
			vMaestro.setControlador(cMaestro);
			vMaestro.setVisible(true);
			vGestionTalleres.dispose();
		} else if (e.getSource().equals(vGestionTalleres.getBtnModificar())) {
			int idTaller = vGestionTalleres.getIdTallerSeleccionado();
			if (idTaller == -1) {
				JOptionPane.showMessageDialog(vGestionTalleres, "Selecciona un taller para modificar");
			} else {
				String nombreTaller = vGestionTalleres.getNombreTallerSeleccionado();
				String tipoTaller = vGestionTalleres.getTipoTallerSeleccionado();
				
				// 3. Abrimos la ventana de Modificar pasándole el rango Y el ID del taller
				VentanaModificarTaller vModificarTaller = new VentanaModificarTaller(vGestionTalleres.getRangoUsuario(), idTaller, nombreTaller, tipoTaller, idTaller);
				ControladorModificarTaller cModificarTaller = new ControladorModificarTaller(vModificarTaller, tipoTaller, idTaller);
				vModificarTaller.setControladorModificar(cModificarTaller);
				vModificarTaller.setVisible(true);
				vGestionTalleres.dispose();
			}
		} else if (e.getSource().equals(vGestionTalleres.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin c = new ControladorLogin(vLogin);
			vLogin.setControlador(c);
			vLogin.setVisible(true);
			vGestionTalleres.dispose();
		}
	}

}