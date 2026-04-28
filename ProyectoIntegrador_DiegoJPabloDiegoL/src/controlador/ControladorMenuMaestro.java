package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controlador.cita.ControladorMenuCita;
import controlador.taller.ControladorMenuTaller;
import controlador.cliente.ControladorMenuCliente; // Asegúrate de que este import exista
import modelo.Cita;
import modelo.Modelo;
import modelo.Taller;
import modelo.Cliente;
import modelo.Traje;
import vista.VentanaGestionCita;
import vista.VentanaGestionCliente;
import vista.VentanaGestionTalleres;
import vista.VentanaGestionTrajes; // Importamos la de trajes
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

	@Override
	public void actionPerformed(ActionEvent e) {

	    // EVENTO CITAS
	    if (e.getSource().equals(vMaestro.getBtnCitas())) {
	        VentanaGestionCita vGestionCitas = new VentanaGestionCita(rangoUsuario, idUsuario);
	        ControladorMenuCita cCitas = new ControladorMenuCita(vGestionCitas, rangoUsuario, idUsuario);
	        vGestionCitas.setControlador(cCitas);

	        ArrayList<Cita> datosCitas = (rangoUsuario.equals("Aprendiz")) ? m.recuperarCitasPropias(idUsuario) : m.recuperarCitas();
	        
	        vGestionCitas.cargarDatosCitas(datosCitas);
	        vGestionCitas.setVisible(true);
	        vMaestro.dispose();

	    // EVENTO TALLERES
	    } else if (e.getSource().equals(vMaestro.getBtnTalleres())) {
	        VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres(rangoUsuario, idUsuario);
	        ControladorMenuTaller cMenuTaller = new ControladorMenuTaller(vGestionTalleres, rangoUsuario, idUsuario);
	        vGestionTalleres.setControlador(cMenuTaller);
	        vGestionTalleres.cargarDatosTalleres(m.recuperarTalleres());
	        vGestionTalleres.setVisible(true);
	        vMaestro.dispose();

	    // EVENTO CLIENTES
	    } else if (e.getSource().equals(vMaestro.getBtnClientes())) {
	        VentanaGestionCliente vGestionClientes = new VentanaGestionCliente(rangoUsuario, idUsuario);
	        // OJO: Aquí te faltaba el controlador para que no se te quede muerta la ventana de clientes
	        controlador.cliente.ControladorMenuCliente cClientes = new controlador.cliente.ControladorMenuCliente(vGestionClientes, rangoUsuario, idUsuario);
	        vGestionClientes.setControlador(cClientes);
	        
	        ArrayList<Cliente> datosActualizados = m.recuperarClientes();
	        vGestionClientes.cargarDatosClientes(datosActualizados);
	        vGestionClientes.setVisible(true);
	        vMaestro.dispose();

	    // EVENTO TRAJES 
	    } else if (e.getSource().equals(vMaestro.getBtnTrajes())) {
	        VentanaGestionTrajes vTrajes = new VentanaGestionTrajes(rangoUsuario, idUsuario);
	        ControladorMenuTraje cTrajes = new ControladorMenuTraje(vTrajes, rangoUsuario, idUsuario);
	        vTrajes.setControlador(cTrajes); 
	        vTrajes.setVisible(true);
	        vMaestro.dispose();

	    // CERRAR SESIÓN (Ahora este es el único que hay)
	    } else if (e.getSource().equals(vMaestro.getBtnCerrarSesion())) {
	        VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
	        vLogin.setControlador(new ControladorLogin(vLogin));
	        vLogin.setVisible(true);
	        vMaestro.dispose();
	    }
	}
}
