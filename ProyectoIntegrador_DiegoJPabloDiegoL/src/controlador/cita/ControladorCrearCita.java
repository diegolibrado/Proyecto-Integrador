package controlador.cita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.ControladorLogin;
import modelo.Cita;
import modelo.Modelo;
import vista.VentanaCrearCita;
import vista.VentanaCrearTaller;
import vista.VentanaGestionCita;
import vista.VentanaGestionTalleres;
import vista.VentanaLogin;
import vista.VentanaModificarTaller;

public class ControladorCrearCita implements ActionListener {

	private VentanaCrearCita vista;
	private String rangoUsuario;
	private int idEmpleadoLogin;
	private Modelo modelo;

	public ControladorCrearCita(VentanaCrearCita vista, String rango, int id) {
		this.vista = vista;
		rangoUsuario = rango;
		idEmpleadoLogin = id;
		modelo = new Modelo();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(vista.getBtnCerrarSesion())){
			VentanaGestionCita vGestionCita = new VentanaGestionCita(rangoUsuario, idEmpleadoLogin);
			ControladorMenuCita cMenuCita = new ControladorMenuCita(vGestionCita, rangoUsuario, idEmpleadoLogin);
			vGestionCita.setControlador(cMenuCita);
			
			ArrayList<Cita> listaCitas = null;
			if(rangoUsuario.equals("Maestro")) {
				listaCitas = modelo.recuperarCitas();
			}
			vGestionCita.cargarDatosCitas(listaCitas);
			vGestionCita.setVisible(true);
			vista.dispose();
		}else if (e.getSource().equals(vista.getBtnGuardarCambios())) {
			try {
		        Cita nuevaCita = new Cita();
		        
		        // El ID de la cita SÍ es un número
		        nuevaCita.setId_cita(Integer.parseInt(vista.getIdCita())); 
		        
		        nuevaCita.setDia(vista.getFechaCita());
		        nuevaCita.setHoraString(vista.getHoraCita());
		        nuevaCita.setDuracion(vista.getDuracion());

		        // --- AQUÍ ESTABA EL ERROR ---
		        // Quitamos el Integer.parseInt porque vista.getNombreCliente() devuelve "Sara"
		        // Asegúrate de que en tu clase Cita.java estos setters acepten String temporalmente
		        // o que el Modelo maneje la conversión.
		        String nombreCli = vista.getNombreCliente();
		        String nombreTal = vista.getNombreTaller();
		        String nombreResp = vista.getNombreResponsable();
		        String nombreTraje = vista.getNombreTraje();

		        // Si tu base de datos requiere IDs (ints), tienes que pedírselos al modelo así:
		        nuevaCita.setId_cliente(modelo.obtenerIdCliente(nombreCli));
		        nuevaCita.setId_taller(modelo.obtenerIdTaller(nombreTal));
		        nuevaCita.setId_empleado(modelo.obtenerIdEmpleado(nombreResp));
		        nuevaCita.setId_traje(modelo.obtenerIdTraje(nombreTraje));

		        if (modelo.crearCita(nuevaCita)) {
		            JOptionPane.showMessageDialog(vista, "Cita creada correctamente");		        }

		    } catch (NumberFormatException nfe) {
		        // Ahora este error solo saltará si el "ID de la Cita" no es un número
		        JOptionPane.showMessageDialog(vista, "El ID de la Cita debe ser un número entero.");
		    }
		}
	}
}
