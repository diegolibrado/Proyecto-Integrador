package controlador.cita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import modelo.Modelo;
import modelo.Taller;
import vista.VentanaModificarTaller;
import vista.VentanaGestionTalleres;
import vista.VentanaLogin;
import vista.VentanaModificarTaller;

public class ControladorModificarCita implements ActionListener {

	private VentanaModificarTaller vista;
	private int idUsuario;

	public ControladorModificarCita(VentanaModificarTaller vista, int id) {
		this.vista = vista;
		this.idUsuario = id;
	}

	public void actionPerformed(ActionEvent e) {
		Modelo modelo = new Modelo();

		// Si se pulsa el boton de atras
		if (e.getSource().equals(vista.getBtnAtras())) {
			VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(vista.getRangoUsuario(), idUsuario);
			vGestionTalleres.cargarDatosTalleres(modelo.recuperarTalleres());
			vGestionTalleres.setVisible(true);
			vista.dispose();
			return;
		}else if (e.getSource().equals(vista.getBtnCerrarSesion())){
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			ControladorLogin c = new ControladorLogin(vLogin);
			vLogin.setControlador(c);
			vLogin.setVisible(true);
			vista.dispose();
		}else if (e.getSource().equals(vista.getBtnGuardarCambios())){
			// Si se pulsa el de giardar
			String idStr = vista.getIdTaller();
			String nombre = vista.getNombreTaller();
			String tipo = vista.getTipoSala();

			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(vista, "El nombre del taller no puede estar vacío.");
				return;
			}

			int idTaller;
			try {
				idTaller = Integer.parseInt(idStr);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(vista, "Error: El ID del taller es invalido.");
				return;
			}

			Taller tallerModificado = new Taller();
			tallerModificado.setId_taller(idTaller);
			tallerModificado.setNombre(nombre);
			tallerModificado.setTipo_sala(tipo);

			boolean exito = modelo.modificarTaller(tallerModificado);

			if (exito) {
				JOptionPane.showMessageDialog(vista, "Taller actualizado correctamente.");
				VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(vista.getRangoUsuario(), idUsuario);
				vGestionTalleres.cargarDatosTalleres(modelo.recuperarTalleres());
				vGestionTalleres.setVisible(true);
				vista.dispose();
			} else {
				JOptionPane.showMessageDialog(vista, "Error: No se pudo actualizar el taller");
			}
		}
	}
}
