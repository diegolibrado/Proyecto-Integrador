package controlador.cita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import modelo.Cita;
import modelo.Modelo;
import vista.VentanaModificarCitas;
import vista.VentanaGestionCita;
import vista.VentanaLogin;
import controlador.ControladorLogin;

public class ControladorModificarCita implements ActionListener {

	private VentanaModificarCitas vista;
	private String rangoUsuario;
	private int idUsuario;
	private Modelo modelo;

	public ControladorModificarCita(VentanaModificarCitas vista, String rango, int id) {
		this.vista = vista;
		this.rangoUsuario = rango;
		this.idUsuario = id;
		this.modelo = new Modelo();

		// 1. Al cargar, rellenamos los combos de la vista (importante para que se pueda
		// seleccionar)
		cargarCombos();
	}

	private void cargarCombos() {
		ArrayList<String> clientes = modelo.recuperarNombresClientes();
		ArrayList<String> talleres = modelo.recuperarNombresTalleres();
		ArrayList<String> empleados = modelo.recuperarNombresEmpleados();
		ArrayList<String> trajes = modelo.recuperarNombresTrajes();
		vista.rellenarComboBox(clientes, talleres, empleados, trajes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Modelo m = new Modelo();

		if (e.getSource().equals(vista.getBtnGuardar())) {
			try {
				Cita cita = new Cita();

				// 1. Sacamos los datos de la vista
				cita.setId_cita(Integer.parseInt(vista.getTxtIdCita().getText()));
				cita.setDia(new java.sql.Date(((java.util.Date) vista.getSpinnerFecha().getValue()).getTime()));
				java.util.Date dHora = (java.util.Date) vista.getSpinnerHora().getValue();
				cita.setHora(new java.sql.Time(dHora.getTime()));
				cita.setDuracion((int) vista.getSpinnerDuracion().getValue());

				// 2. Buscamos los IDs según lo que hay seleccionado en los Combos
				int idClienteCmb = m
						.obtenerIdClientePorNombre(vista.getCmbNombreCliente().getSelectedItem().toString());
				int idTallerCmb = m.obtenerIdTallerPorNombre(vista.getCmbNombreTaller().getSelectedItem().toString());
				int idResponsableCmb = m
						.obtenerIdEmpleadoPorNombre(vista.getCmbNombreResponsable().getSelectedItem().toString());
				int idTrajeCmb = m.obtenerIdTrajePorNombre(vista.getCmbNombreTraje().getSelectedItem().toString());

				cita.setId_cliente(idClienteCmb);
				cita.setId_taller(idTallerCmb);
				cita.setId_empleado(idResponsableCmb);
				cita.setId_traje(idTrajeCmb);

				// 3. Intentamos modificar
				if (m.modificarCita(cita)) {
					JOptionPane.showMessageDialog(vista, "Cita actualizada correctamente");
					VentanaGestionCita vG = new VentanaGestionCita(rangoUsuario, idUsuario);
					vG.setControlador(new ControladorMenuCita(vG, rangoUsuario, idUsuario));

					if (rangoUsuario.equalsIgnoreCase("Maestro") || rangoUsuario.equalsIgnoreCase("Oficial")) {
						vG.cargarDatosCitas(m.recuperarCitas());
					} else {
						vG.cargarDatosCitas(m.recuperarCitasPropias(idUsuario));
					}

					vG.setVisible(true);
					vista.dispose();
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
			}
		} else if (e.getSource().equals(vista.getBtnAtras())) {
			VentanaGestionCita vG = new VentanaGestionCita(rangoUsuario, idUsuario);
			vG.setControlador(new ControladorMenuCita(vG, rangoUsuario, idUsuario));
			if (rangoUsuario.equalsIgnoreCase("Maestro")) {
				vG.cargarDatosCitas(m.recuperarCitas());
			} else {
				vG.cargarDatosCitas(m.recuperarCitasPropias(idUsuario));
			}
			vG.setVisible(true);
			vista.dispose();
		} else if (e.getSource().equals(vista.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
			vLogin.setControlador(new ControladorLogin(vLogin));
			vLogin.setVisible(true);
			vista.dispose();
		}
	}
}