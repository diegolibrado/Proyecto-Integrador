package controlador.cita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import modelo.Cita;
import modelo.Modelo;
import vista.VentanaCrearCita;
import vista.VentanaLogin;
import vista.VentanaGestionCita;
import vista.VentanaGestionTalleres;
import vista.VentanaCrearCliente;
import vista.VentanaCrearTraje;
import controlador.ControladorLogin;
import controlador.cliente.ControladorCrearCliente;
import controlador.traje.ControladorCrearTraje;

public class ControladorCrearCita implements ActionListener {

	private VentanaCrearCita vista;
	private String rangoUsuario;
	private int idUsuario;
	private Modelo modelo;

	public ControladorCrearCita(VentanaCrearCita vista, String rango, int id) {
		this.vista = vista;
		this.rangoUsuario = rango;
		this.idUsuario = id;
		this.modelo = new Modelo();

		ArrayList<String> listaClientes = modelo.recuperarNombresClientes();
		ArrayList<String> listaTalleres = modelo.recuperarNombresTalleres();
		ArrayList<String> listaEmpleados = modelo.recuperarNombresEmpleados();
		ArrayList<String> listaTrajes = modelo.recuperarNombresTrajes(); 

		// Llamamos al método de la vista que acabas de corregir
		this.vista.rellenarComboBox(listaClientes, listaTalleres, listaEmpleados, listaTrajes);
	}

	public void actionPerformed(ActionEvent e) {

		// --- 1. DETECTAR SELECCIÓN EN COMBO CLIENTE ---
		if (e.getSource().equals(vista.getNombreCliente())) {
			if (vista.getNombreCliente().getSelectedIndex() == 0) {
				VentanaCrearCliente vCrearCliente = new VentanaCrearCliente(rangoUsuario, idUsuario, "Crear Cita");
				ControladorCrearCliente cCrearCliente = new ControladorCrearCliente(vCrearCliente, idUsuario);
				vCrearCliente.setControlador(cCrearCliente);
				vCrearCliente.setVisible(true);
				vista.dispose();
				return;
			} 
		} else if (e.getSource().equals(vista.getNombreTraje())) {
			if (vista.getNombreTraje().getSelectedIndex() == 0) {
				VentanaCrearTraje vCrearTraje = new VentanaCrearTraje(rangoUsuario, "Crear Cita");
				controlador.traje.ControladorCrearTraje cCrearTraje = new ControladorCrearTraje(vCrearTraje, rangoUsuario, idUsuario);
				vCrearTraje.setControladorGuardar(cCrearTraje);
				vCrearTraje.setVisible(true);
				vista.dispose();
				return;
			}
		} else if (e.getSource().equals(vista.getBtnCerrarSesion())) {
			VentanaLogin vLogin = new VentanaLogin(rangoUsuario);
			ControladorLogin cLogin = new ControladorLogin(vLogin);
			vLogin.setControlador(cLogin);

			ArrayList<Cita> listaCitas = null;
			if (rangoUsuario.equals("Maestro")) {
				listaCitas = modelo.recuperarCitas();
			} else {
				listaCitas = modelo.recuperarCitasPropias(idUsuario);
			}
			vLogin.setVisible(true);
			vista.dispose();
		} else if (e.getSource().equals(vista.getBtnGuardarCambios())) {
			try {
				// Controlamos que se haya seleccionado ualgo en cliente y traje
				if (vista.getNombreCliente().getSelectedIndex() == 0
						|| vista.getNombreTraje().getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(vista, "Seleccione un cliente y un traje válidos o cree uno nuevo.");
					return;
				}

				Cita nuevaCita = new Cita();
				nuevaCita.setId_cita(Integer.parseInt(vista.getIdCita()));
				nuevaCita.setDia(vista.getFechaCita());
				nuevaCita.setHora(vista.getHoraCita());
				nuevaCita.setDuracion(vista.getDuracion());

				// Recogemos los nombres seleccionados en el formulario
				String nombreCliente = vista.getNombreCliente().getSelectedItem().toString();
				String nombreTaller = vista.getNombreTaller().getSelectedItem().toString();
				String nombreResponsable = vista.getNombreResponsable().getSelectedItem().toString();
				String nombreTraje = vista.getNombreTraje().getSelectedItem().toString();

				// Buscar el id mediante el nombre
				int idCliente = modelo.obtenerIdClientePorNombre(nombreCliente);
				int idTaller = modelo.obtenerIdTallerPorNombre(nombreTaller);
				int idResponsable = modelo.obtenerIdEmpleadoPorNombre(nombreResponsable);
				int idTraje = modelo.obtenerIdTrajePorNombre(nombreTraje);

				// Controlamos que no falle ningun id en la base de datos
				if (idCliente == -1 || idTaller == -1 || idResponsable == -1 || idTraje == -1) {
					JOptionPane.showMessageDialog(vista, "Error: No se encontró algún dato en la base de datos.");
					return;
				}

				nuevaCita.setId_cliente(idCliente);
				nuevaCita.setId_taller(idTaller);
				nuevaCita.setId_empleado(idResponsable);
				nuevaCita.setId_traje(idTraje);

				if (modelo.crearCita(nuevaCita)) {
					JOptionPane.showMessageDialog(vista, "Cita creada correctamente");

					// Regresamos a la Gestión de Citas
					VentanaGestionCita vGestionFinal = new VentanaGestionCita(rangoUsuario, idUsuario);
					ControladorMenuCita cMenuFinal = new ControladorMenuCita(vGestionFinal, rangoUsuario, idUsuario);
					vGestionFinal.setControlador(cMenuFinal);

					ArrayList<Cita> listaFinal = null;
					if (rangoUsuario.equals("Maestro")) {
						listaFinal = modelo.recuperarCitas();
					} else {
						listaFinal = modelo.recuperarCitasPropias(idUsuario);
					}
					vGestionFinal.cargarDatosCitas(listaFinal);
					vGestionFinal.setVisible(true);
					vista.dispose();
				} else {
					JOptionPane.showMessageDialog(vista, "Error al guardar la cita en la base de datos.");
				}

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(vista, "Error: El ID de la Cita debe ser numérico.");
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(vista, "Error: Debe rellenar todos los campos");
			}
		} else if(e.getSource().equals(vista.getBtnAtras())) {
			VentanaGestionCita vGestionCita = new VentanaGestionCita(vista.getRangoUsuario(), idUsuario);
			vGestionCita.cargarDatosCitas(modelo.recuperarCitas());
			vGestionCita.setVisible(true);
		    vista.dispose();
		    return;
		}
	}
}