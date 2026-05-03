package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;
import vista.VentanaGestionTrajes;
import vista.VentanaLogin;
import vista.VentanaMaestro;

public class ControladorMenuTraje implements ActionListener {

	private VentanaGestionTrajes vTrajes;
	private Modelo m;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorMenuTraje(VentanaGestionTrajes v, String rango, int id) {
		vTrajes = v;
		m = new Modelo();
		rangoUsuario = rango;
		idUsuario = id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// BOTON ATRAS
		if (e.getSource().equals(vTrajes.getBtnAtras())) {
			VentanaMaestro vMaestro = new VentanaMaestro("Menú Maestro", rangoUsuario, idUsuario);
			ControladorMenuMaestro cMaestro = new ControladorMenuMaestro(vMaestro, rangoUsuario, idUsuario);
			vMaestro.setControlador(cMaestro);
			vMaestro.setVisible(true);
			vTrajes.dispose();
			
		// BOTON CERRAR SESION
		} else if (e.getSource().equals(vTrajes.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin c = new ControladorLogin(vLogin);
			vLogin.setControlador(c);
			vLogin.setVisible(true);
			vTrajes.dispose();
		
		} else if (e.getSource().equals(vTrajes.getBtnCrear())){
			
		} else if(e.getSource().equals(vTrajes.getBtnEliminar())) {
			
		} else if (e.getSource().equals(vTrajes.getBtnModificar())) {
			
		}
	}

}