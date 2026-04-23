package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(null, "¡Acceso Conseguido!");
			vLogin.dispose();
			
			switch(categoria) {
			case "Aprendiz":
				new VentanaAprendiz("Menu Aprendiz").setVisible(true);
				break;
			case "Maestro":
				new VentanaMaestro("Menu Maestro").setVisible(true);
				break;
			case "Oficial":
				new VentanaGestionCita("Menu Oficial").setVisible(true);
				break;
				
			}
		}else {
			JOptionPane.showMessageDialog(null, "Acceso denegado: Contraseña o Usuario incorrectos");
		}
	}
}
