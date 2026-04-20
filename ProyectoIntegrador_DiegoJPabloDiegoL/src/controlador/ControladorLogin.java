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
import vista.VentanaAprendiz;
import vista.VentanaLogin;
import vista.VentanaMaestro;
import vista.VentanaOficial;

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

		// realizamos la conexion
		Modelo modelo = new Modelo();
		Connection conexion = modelo.getConexion();

		// Query (consulta)
		String query = "SELECT CATEGORIA FROM EMPLEADO WHERE ID_EMPLEADO = ? AND CONTRASENA = ?";
		try (PreparedStatement ps = conexion.prepareStatement(query)) {
			int id_empleado_numero = Integer.parseInt(usuario);
			// Hacemos dos Statement, uno para usuario y otro para contraseña
			ps.setInt(1, id_empleado_numero);
			ps.setString(2, contraseña);
			ResultSet rs = ps.executeQuery();

			// Comienza la logica de comparacion para intentar acceder
			if (rs.next()) {
				String categoriaEmpleado = rs.getString("categoria");
				JOptionPane.showMessageDialog(null, "Acceso conseguido!");
				vLogin.dispose();
				if (categoriaEmpleado.equalsIgnoreCase("Aprendiz")) {
					new VentanaAprendiz("Menu Aprendiz").setVisible(true);
				} else if (categoriaEmpleado.equalsIgnoreCase("Maestro")) {
					new VentanaMaestro("Menu Maestro").setVisible(true);
				} else if(categoriaEmpleado.equalsIgnoreCase("Oficial")){
					new VentanaOficial("Menu Oficial").setVisible(true);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Acceso denegado: Contraseña Incorrecta");
			}

		} catch (SQLException SQLe) {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
			SQLe.printStackTrace();
		// Pase lo que pase cerramos la conexion con la base de datos
		} finally {

		}
	}
}
