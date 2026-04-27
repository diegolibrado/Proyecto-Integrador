package modelo;

import java.sql.Date;
import java.sql.Time;

import javax.swing.JSpinner;

/**
 * 
 */
public class Cita {
	private int id_cita;
    private int id_cliente;
    private int id_traje;
    private int id_empleado;
    private int id_taller;
    private java.util.Date dia;
    private Time hora;
    private int duracion;
    private String nombreCliente;
    private String nombreTaller;
    private String nombreEmpleadoResponsable;
    private String nombreTraje;

    //Getters y Setters
    public int getId_cita() {
		return id_cita;
	}
	public void setId_cita(int id_cita) {
		this.id_cita = id_cita;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_traje() {
		return id_traje;
	}
	public void setId_traje(int id_traje) {
		this.id_traje = id_traje;
	}
	public int getId_empleado() {
		return id_empleado;
	}
	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}
	public int getId_taller() {
		return id_taller;
	}
	public void setId_taller(int id_taller) {
		this.id_taller = id_taller;
	}
	public java.util.Date getDia() {
		return dia;
	}
	public void setDia(java.util.Date date) {
		this.dia = date;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time time) {
		this.hora = time;
	}
	public int getDuracion() {
		return (int) duracion;
	}
	public void setDuracion(int i) {
		this.duracion = i;
	}
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @return the nombreTaller
	 */
	public String getNombreTaller() {
		return nombreTaller;
	}
	/**
	 * @return the nombreEmpleadoResponsable
	 */
	public String getNombreEmpleadoResponsable() {
		return nombreEmpleadoResponsable;
	}
	/**
	 * @return the nombreTraje
	 */
	public String getNombreTraje() {
		return nombreTraje;
	}
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	/**
	 * @param nombreTaller the nombreTaller to set
	 */
	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}
	/**
	 * @param nombreEmpleadoResponsable the nombreEmpleadoResponsable to set
	 */
	public void setNombreEmpleadoResponsable(String nombreEmpleadoResponsable) {
		this.nombreEmpleadoResponsable = nombreEmpleadoResponsable;
	}
	/**
	 * @param nombreTraje the nombreTraje to set
	 */
	public void setNombreTraje(String nombreTraje) {
		this.nombreTraje = nombreTraje;
	}
}
