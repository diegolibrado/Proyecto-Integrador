package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Cita;
import modelo.Modelo;
import modelo.Taller;
import modelo.Cliente; 
import vista.VentanaGestionCita;
import vista.VentanaGestionTalleres;
import vista.VentanaGestionCliente; 
import vista.VentanaGestionTrajes;  
import vista.VentanaMaestro;

public class ControladorMenuPpal implements ActionListener {

	private VentanaMaestro vMaestro;
	private Modelo m;
	
	public ControladorMenuPpal (VentanaMaestro v) {
		vMaestro = v;
		m = new Modelo();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// 1. LÓGICA PARA CITAS
		if (e.getSource().equals(vMaestro.getBtnCitas())) {
			VentanaGestionCita vGestionCitas = new VentanaGestionCita("Gestion de citas");
			ArrayList<Cita> datosCitas = m.recuperarCitas();
			vGestionCitas.cargarDatosCitas(datosCitas);
			vGestionCitas.setVisible(true);
			vMaestro.dispose();

		// 2. LÓGICA PARA TALLERES
		} else if (e.getSource().equals(vMaestro.getBtnTalleres())){
			VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres("Gestion de Talleres");
			ArrayList<Taller> datosTalleres = m.recuperarTalleres();
			vGestionTalleres.cargarDatosTalleres(datosTalleres);
			vGestionTalleres.setVisible(true);
			vMaestro.dispose();

		// 3. LÓGICA PARA CLIENTES
		} else if (e.getSource().equals(vMaestro.getBtnClientes())) {
			// La ventana de clientes ya carga sus propios datos en el constructor
			VentanaGestionCliente vGestionClientes = new VentanaGestionCliente(vMaestro.getRangoUsuario());
			vGestionClientes.setVisible(true);
			vMaestro.dispose();

		// 4. LÓGICA PARA TRAJES
		} else if (e.getSource().equals(vMaestro.getBtnTrajes())) {
			// Al igual que clientes, la ventana de trajes carga la tabla al iniciarse
			VentanaGestionTrajes vGestionTrajes = new VentanaGestionTrajes(vMaestro.getRangoUsuario());
			vGestionTrajes.setVisible(true);
			vMaestro.dispose();
		}
	}
}