package main;

import controlador.ControladorLogin;
import vista.VentanaLogin;

public class ProyInt_Main {

	public static void main(String[] args) {
		VentanaLogin vLogin = new VentanaLogin ("Ventana Login");
		ControladorLogin c = new ControladorLogin(vLogin);
		
		vLogin.setControlador(c);
		vLogin.setVisible(true);
	}

}
