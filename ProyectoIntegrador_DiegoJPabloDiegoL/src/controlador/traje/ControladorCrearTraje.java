package controlador.traje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import controlador.cita.ControladorCrearCita;
import controlador.cliente.ControladorMenuCliente;
import modelo.Modelo;
import modelo.Traje;
import vista.VentanaCrearCita;
import vista.VentanaCrearTaller;
import vista.VentanaCrearTraje;
import vista.VentanaGestionCliente;
import vista.VentanaGestionTrajes;
import vista.VentanaLogin;
import vista.VentanaModificarTaller;

public class ControladorCrearTraje implements ActionListener {

	private VentanaCrearTraje vista;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorCrearTraje(VentanaCrearTraje vista, String rango, int id) {
		this.vista = vista;
		this.rangoUsuario = rango;
		this.idUsuario = id;
	}

	public void actionPerformed(ActionEvent e) {
		Modelo m = new Modelo();

		if (e.getSource().equals(vista.getBtnAtras())) {
			String origen = vista.getVentanaOrigen();
			
			if (origen != null && origen.equals("Crear Cita")) {
				VentanaCrearCita vCrearCita = new VentanaCrearCita(rangoUsuario);
				ControladorCrearCita cCrearCita = new ControladorCrearCita(vCrearCita, rangoUsuario, idUsuario);
				vCrearCita.setControlador(cCrearCita);
				vCrearCita.setVisible(true);
			} else { 
				VentanaGestionTrajes vGestionTrajes = new VentanaGestionTrajes(vista.getRangoUsuario(), idUsuario);
				ControladorMenuTraje cGestionTrajes = new ControladorMenuTraje(vGestionTrajes, vista.getRangoUsuario(), idUsuario);
				vGestionTrajes.setControlador(cGestionTrajes);
				vGestionTrajes.cargarDatosTrajes(m.recuperarTrajes());
				vGestionTrajes.setVisible(true);
				vista.dispose();			
			}			
		} else if (e.getSource().equals(vista.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin cLogin = new ControladorLogin(vLogin);
			vLogin.setControlador(cLogin);
			vLogin.setVisible(true);
			vista.dispose();
			return;
		} else if (e.getSource().equals(vista.getBtnGuardarCambios())) {
			String idStr = vista.getIdTraje();
			String nombre = vista.getNombreTaller();
			String estado = vista.getTipoSala();

			if (idStr.isEmpty() || nombre.isEmpty()) {
				JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
				return;
			}

			try {
				int idTraje = Integer.parseInt(idStr);
				Traje traje = new Traje();
				traje.setId_traje(idTraje);
				traje.setNombre(nombre);
				traje.setEstado(estado);

				if (m.crearTraje(traje)) {
					JOptionPane.showMessageDialog(vista, "Traje creado exitosamente");
					
					String origen = vista.getVentanaOrigen();
					if (origen != null && origen.equals("Crear Cita")) {
						VentanaCrearCita vCrearCita = new VentanaCrearCita(rangoUsuario);
						ControladorCrearCita cCrearCita = new ControladorCrearCita(vCrearCita, rangoUsuario, idUsuario);
						vCrearCita.setControlador(cCrearCita);
						vCrearCita.setVisible(true);
						vista.dispose();
					} else {
						VentanaGestionTrajes vGestion = new VentanaGestionTrajes(vista.getRangoUsuario(), idUsuario);
						ControladorMenuTraje cMenu = new ControladorMenuTraje(vGestion, vista.getRangoUsuario(), idUsuario);
						vGestion.setControlador(cMenu);
						vGestion.cargarDatosTrajes(m.recuperarTrajes());
						vGestion.setVisible(true);
						vista.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(vista, "Error: No se pudo crear el traje");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(vista, "El ID del traje debe ser un número entero.");
			}
		}
	}
}
