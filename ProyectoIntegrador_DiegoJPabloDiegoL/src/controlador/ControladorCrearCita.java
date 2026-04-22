package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaCrearCita;
import vista.VentanaCrearTaller;
import vista.VentanaGestionCita;
import vista.VentanaGestionTalleres;
import vista.VentanaModificarTaller;

public class ControladorCrearCita implements ActionListener {

	private VentanaCrearCita vista;

	public ControladorCrearCita(VentanaCrearCita vista) {
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		// Guardamos los datos de la vista aqui
		String idCitaStr = vista.getIdCita();
		String nombreCliente = vista.getNombreCliente();
		String nombreTaller = vista.getNombreTaller();
		String nombreResponsable = vista.getNombreResponsable();
		String nombreTraje = vista.getNombreTraje();
		String numAprendices = vista.getNumAprendices();

		// Datos de los spinners
		java.sql.Date fecha = vista.getFechaCita();
		String horaStr = vista.getHoraCita();
		int duracion = vista.getDuracion();

		// Validar que se rellene todo
		if (idCitaStr.isEmpty() || nombreCliente.isEmpty() || nombreTaller.isEmpty() || nombreResponsable.isEmpty() || nombreTraje.isEmpty() || numAprendices.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
			return;
		}
		
		// Pasar el id de la cita a int ya que esta en String
		int idCita = 0;
		try {
			idCita = Integer.parseInt(idCitaStr);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(vista, "El ID de la cita debe ser un número entero.");
		}
		
		Modelo conector = new Modelo();
		Connection conexion = conector.getConexion();
		
		// Declaracion de insercion a la base de datos
		String query = "INSERT INTO CITA (id_cita, dia, hora, duracion, id_cliente, id_taller, id_empleado_responsable, id_traje) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		// Insertamos los datos dentro de la tabla
		try (PreparedStatement pst = conexion.prepareStatement(query)){
			pst.setInt(1, idCita);
			pst.setDate(2, fecha);
			pst.setString(3, horaStr);
			pst.setInt(4, duracion);
			pst.setString(5, nombreCliente);
			pst.setString(6, nombreTaller);
			pst.setString(7, nombreResponsable);
			pst.setString(8, nombreTraje);
			
			int filasAfectadas = pst.executeUpdate();
			
			// Mensaje de Exito
			if(filasAfectadas > 0) {
				JOptionPane.showMessageDialog(vista, "Cita creada exitosamente");
				VentanaGestionCita vGestionCita = new VentanaGestionCita(vista.getRangoUsuario());
				vGestionCita.setVisible(true);
				vista.dispose();
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(vista, "Error al crear la cita en la Base de datos:");
		// Se ejecutara pase lo que pase
		} finally {
			conector.cerrarConexion(conexion);
		}
		
		
		
	}
}
