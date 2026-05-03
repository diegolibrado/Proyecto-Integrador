package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controlador.cita.ControladorMenuCita;
import controlador.taller.ControladorMenuTaller;
import controlador.cliente.ControladorMenuCliente;
import controlador.traje.ControladorMenuTraje;
import modelo.Cita;
import modelo.Modelo;
import modelo.Taller;
import modelo.Cliente;
import modelo.Traje;
import vista.VentanaGestionCita;
import vista.VentanaGestionCliente;
import vista.VentanaGestionTalleres;
import vista.VentanaGestionTrajes;
import vista.VentanaLogin;
import vista.VentanaMaestro;
import vista.aprendiz.VentanaAprendiz;

public class ControladorMenuAprendiz implements ActionListener {

	private VentanaAprendiz vMaestro;
	private Modelo m;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorMenuAprendiz(VentanaAprendiz v, String rango, int id) {
		vMaestro = v;
		m = new Modelo();
		rangoUsuario = rango;
		idUsuario = id;
	}

	public void actionPerformed(ActionEvent e) {

	    if (e.getSource().equals(vMaestro.getBtnCerrarSesion())) {
	        VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
	        vLogin.setControlador(new ControladorLogin(vLogin));
	        vLogin.setVisible(true);
	        vMaestro.dispose();
	    }
	}
}
