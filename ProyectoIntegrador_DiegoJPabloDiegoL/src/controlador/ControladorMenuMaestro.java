package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controlador.cita.ControladorMenuCita;
import controlador.taller.ControladorMenuTaller;
import modelo.Cita;
import modelo.Modelo;
import modelo.Taller;
import vista.VentanaGestionCita;
import vista.VentanaGestionCliente;
import vista.VentanaGestionTalleres;
import vista.VentanaLogin;
import vista.VentanaMaestro;

public class ControladorMenuMaestro implements ActionListener {

	private VentanaMaestro vMaestro;
	private Modelo m;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorMenuMaestro(VentanaMaestro v, String rango, int id) {
		vMaestro = v;
		m = new Modelo();
		rangoUsuario = rango;
		idUsuario = id;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(vMaestro.getBtnCitas())) {
			VentanaGestionCita vGestionCitas = new VentanaGestionCita(rangoUsuario, idUsuario);

			ControladorMenuCita cCitas = new ControladorMenuCita(vGestionCitas, rangoUsuario, idUsuario);
			vGestionCitas.setControlador(cCitas);

			ArrayList<Cita> datosCitas;

			// Aqui hacemos condicional para rellenar el array de los datos propios o de
			// todos
			if (rangoUsuario.equals("Aprendiz")) {
				datosCitas = m.recuperarCitasPropias(idUsuario);
			} else {
				datosCitas = m.recuperarCitas();
			}

			vGestionCitas.cargarDatosCitas(datosCitas);
			vGestionCitas.setVisible(true);
			vMaestro.dispose();
		} else if (e.getSource().equals(vMaestro.getBtnTalleres())) {
			VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres("Gestion de Talleres", idUsuario);
			ControladorMenuTaller cMenuTaller = new ControladorMenuTaller(vGestionTalleres, rangoUsuario, idUsuario);
			vGestionTalleres.setControlador(cMenuTaller);
			ArrayList<Taller> datosTalleres = m.recuperarTalleres();
			vGestionTalleres.cargarDatosTalleres(datosTalleres);
			vGestionTalleres.setVisible(true);
			vMaestro.dispose();
		} else if (e.getSource().equals(vMaestro.getBtnClientes())) {
			VentanaGestionCliente vGestionCilientes = new VentanaGestionCliente("Gestion de clientes", idUsuario);
			vGestionCilientes.setVisible(true);
			vMaestro.dispose();
		} else if (e.getSource().equals(vMaestro.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			// Creo objeto tipo controlador asociado a la nueva ventana para que pueda
			// volver a iniciar sesion
			ControladorLogin c = new ControladorLogin(vLogin);
			vLogin.setControlador(c);
			vLogin.setVisible(true);
			vMaestro.dispose();
		}
	}

}
