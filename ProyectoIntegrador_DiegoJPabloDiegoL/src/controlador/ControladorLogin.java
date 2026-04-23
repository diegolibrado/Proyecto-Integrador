package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlador.cita.ControladorMenuCita;
import modelo.Cita;
import modelo.Modelo;
import vista.VentanaGestionCita;
import vista.VentanaLogin;
import vista.VentanaMaestro;
import vista.VentanaOficial;
import vista.aprendiz.VentanaAprendiz;

public class ControladorLogin implements ActionListener {

	private String categoriaEmpleado;
	private VentanaLogin vLogin;

	public ControladorLogin(VentanaLogin vLogin) {
		this.vLogin = vLogin;
	}

	public void actionPerformed(ActionEvent e) {
		// Guardamos lo introducido en el textField en una variable
		String usuario = vLogin.getUsuario();
		String contraseña = new String(vLogin.getContrasena());

		int idEmpleado;
		try {
			idEmpleado = Integer.parseInt(usuario);
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "El usuario debe ser un numero (ID)");
			return;
		}
		
		Modelo modelo = new Modelo();
		String categoria = modelo.obtenerCategoria(idEmpleado, contraseña);
		
		if(categoria != null) {
			idEmpleado = Integer.parseInt(vLogin.getUsuario());
			JOptionPane.showMessageDialog(null, "¡Acceso Conseguido!");
			vLogin.dispose();
			
			switch(categoria) {
			case "Aprendiz":
				VentanaGestionCita vGestionCitasAprendiz = new VentanaGestionCita(categoria, idEmpleado);
				ControladorMenuCita cAprendiz = new ControladorMenuCita(vGestionCitasAprendiz, categoria, idEmpleado);
				vGestionCitasAprendiz.setControlador(cAprendiz);
				ArrayList<Cita> citasAprendiz = modelo.recuperarCitasPropias(idEmpleado);
				vGestionCitasAprendiz.cargarDatosCitas(citasAprendiz);
				vGestionCitasAprendiz.setVisible(true);
				break;
			case "Maestro":
				VentanaMaestro vMaestro = new VentanaMaestro("Menu Maestro", categoria, idEmpleado);
				ControladorMenuMaestro cMenuMaestro = new ControladorMenuMaestro(vMaestro, categoria, idEmpleado);
				vMaestro.setControlador(cMenuMaestro);
				vMaestro.setVisible(true);
				break;
			case "Oficial":
				VentanaGestionCita vGestionCitasOficial = new VentanaGestionCita(categoria, idEmpleado);
				ControladorMenuCita cOficial = new ControladorMenuCita(vGestionCitasOficial, categoria, idEmpleado);
				vGestionCitasOficial.setControlador(cOficial);
				ArrayList<Cita> citasOficial = modelo.recuperarCitas();
				vGestionCitasOficial.cargarDatosCitas(citasOficial);
				vGestionCitasOficial.setVisible(true);
				break;
			}
		}else {
			JOptionPane.showMessageDialog(null, "Acceso denegado: Contraseña o Usuario incorrectos");
		}
	}
}
