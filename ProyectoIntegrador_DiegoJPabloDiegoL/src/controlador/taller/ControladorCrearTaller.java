package controlador.taller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import modelo.Modelo;
import modelo.Taller;
import vista.VentanaCrearTaller;
import vista.VentanaGestionTalleres;
import vista.VentanaLogin;
import vista.VentanaModificarTaller;

public class ControladorCrearTaller implements ActionListener {

	private VentanaCrearTaller vista;

	public ControladorCrearTaller(VentanaCrearTaller vista) {
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		Modelo modelo = new Modelo();

		if (e.getSource().equals(vista.getBtnAtras())) {
			VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(vista.getRangoUsuario());
			vGestionTalleres.cargarDatosTalleres(modelo.recuperarTalleres());
			vGestionTalleres.setVisible(true);
			vista.dispose();
			return;
		} else if (e.getSource().equals(vista.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin cLogin = new ControladorLogin(vLogin);
			vLogin.setControlador(cLogin);
			vLogin.setVisible(true);
			vista.dispose();
			return;
		} else if (e.getSource().equals(vista.getBtnGuardarCambios())) {
			String idStr = vista.getIdTaller();
			String nombre = vista.getNombreTaller();
			String tipo = vista.getTipoSala();

			if (idStr.isEmpty() || nombre.isEmpty()) {
				JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
				return;
			}

			try {
				int idTaller = Integer.parseInt(idStr);
				Taller taller = new Taller();
				taller.setId_taller(idTaller);
				taller.setNombre(nombre);
				taller.setTipo_sala(tipo);

				if (modelo.crearTaller(taller)) {
					JOptionPane.showMessageDialog(vista, "Taller creado exitosamente");
					VentanaGestionTalleres vGestion = new VentanaGestionTalleres(vista.getRangoUsuario());
					vGestion.cargarDatosTalleres(modelo.recuperarTalleres());
					vGestion.setVisible(true);
					vista.dispose();
				} else {
					JOptionPane.showMessageDialog(vista, "Error: No se pudo crear el taller");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(vista, "El ID del taller debe ser un número entero.");
			}
		}
	}
}
