package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Cita;
import modelo.Modelo;
import modelo.Taller;
import vista.VentanaGestionCita;
import vista.VentanaGestionTalleres;
import vista.VentanaMaestro;

public class ControladorMenuPpal implements ActionListener {

	private VentanaMaestro vMaestro;
	private Modelo m;
	
	public ControladorMenuPpal (VentanaMaestro v) {
		vMaestro = v;
		m = new Modelo();
	}
	
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(vMaestro.getBtnCitas())) {
			VentanaGestionCita vGestionCitas = new VentanaGestionCita("Gestion de citas");
			ArrayList<Cita> datosCitas = m.recuperarCitas();
			vGestionCitas.cargarDatosCitas(datosCitas);
			vGestionCitas.setVisible(true);
			vMaestro.dispose();
		} else if (e.getSource().equals(vMaestro.getBtnTalleres())){
			VentanaGestionTalleres vGestionTalleres = new VentanaGestionTalleres("Gestion de Talleres");
			ArrayList<Taller> datosTalleres = m.recuperarTalleres();
			vGestionTalleres.cargarDatosTalleres(datosTalleres);
			vGestionTalleres.setVisible(true);
			vMaestro.dispose();
		}
	}

}
