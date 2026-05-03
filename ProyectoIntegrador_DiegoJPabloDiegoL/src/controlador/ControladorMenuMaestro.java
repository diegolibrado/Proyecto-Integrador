package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controlador.cita.ControladorMenuCita;
import controlador.taller.ControladorMenuTaller;
import controlador.traje.ControladorMenuTraje;
import modelo.Cita;
import modelo.Modelo;
import modelo.Cliente;
import vista.VentanaGestionCita;
import vista.VentanaGestionCliente;
import vista.VentanaGestionTalleres;
import vista.VentanaGestionTrajes;
import vista.VentanaLogin;
import vista.VentanaMaestro;

public class ControladorMenuMaestro implements ActionListener {

	private VentanaMaestro vMaestro;
	private Modelo m;
	private String rangoUsuario;
	private int idUsuario;

	public ControladorMenuMaestro(VentanaMaestro v, String rango, int id) {
		vMaestro = v;
		m = new Modelo();
		rangoUsuario = rango;
		idUsuario = id;
	}

	public void actionPerformed(ActionEvent e) {

	    // Citas
	    if (e.getSource().equals(vMaestro.getBtnCitas())) {
	        VentanaGestionCita vGestionCitas = new VentanaGestionCita(rangoUsuario, idUsuario);
	        ControladorMenuCita cCitas = new ControladorMenuCita(vGestionCitas, rangoUsuario, idUsuario);
	        vGestionCitas.setControlador(cCitas);

	        ArrayList<Cita> datosCitas;
	        if (rangoUsuario.equals("Aprendiz")) {
	            datosCitas = m.recuperarCitasPropias(idUsuario);
	        } else {
	            datosCitas = m.recuperarCitas();
	        }
	        
	        vGestionCitas.cargarDatosCitas(datosCitas);
	        vGestionCitas.setVisible(true);
	        vMaestro.dispose();

	    // Talleres
	    } else if (e.getSource().equals(vMaestro.getBtnTalleres())) {
	        VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(rangoUsuario, idUsuario);
	        ControladorMenuTaller cMenuTaller = new ControladorMenuTaller(vGestionTalleres, rangoUsuario, idUsuario);
	        vGestionTalleres.setControlador(cMenuTaller);
	        vGestionTalleres.cargarDatosTalleres(m.recuperarTalleres());
	        vGestionTalleres.setVisible(true);
	        vMaestro.dispose();

	    // Clientes
	    } else if (e.getSource().equals(vMaestro.getBtnClientes())) {
	        VentanaGestionCliente vGestionClientes = new VentanaGestionCliente(rangoUsuario, idUsuario);
	        // OJO: Aquí te faltaba el controlador para que no se te quede muerta la ventana de clientes
	        controlador.cliente.ControladorMenuCliente cClientes = new controlador.cliente.ControladorMenuCliente(vGestionClientes, rangoUsuario, idUsuario);
	        vGestionClientes.setControlador(cClientes);
	        
	        ArrayList<Cliente> datosActualizados = m.recuperarClientes();
	        vGestionClientes.cargarDatosClientes(datosActualizados);
	        vGestionClientes.setVisible(true);
	        vMaestro.dispose();

	    // Trajes 
	    } else if (e.getSource().equals(vMaestro.getBtnTrajes())) {
	        VentanaGestionTrajes vGestionTrajes = new VentanaGestionTrajes(rangoUsuario, idUsuario);
	        ControladorMenuTraje cMenuTrajes = new ControladorMenuTraje(vGestionTrajes, rangoUsuario, idUsuario);
	        vGestionTrajes.setControlador(cMenuTrajes); 
	        vGestionTrajes.cargarDatosTrajes(m.recuperarTrajes());
	        vGestionTrajes.setVisible(true);
	        vMaestro.dispose();

	    // Cerrar Sesion
	    } else if (e.getSource().equals(vMaestro.getBtnCerrarSesion())) {
	        VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
	        vLogin.setControlador(new ControladorLogin(vLogin));
	        vLogin.setVisible(true);
	        vMaestro.dispose();
	    }
	}
}
