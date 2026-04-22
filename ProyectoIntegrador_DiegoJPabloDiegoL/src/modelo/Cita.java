package modelo;

/**
 * 
 */
public class Cita {
	private int id_cita;
    private int id_cliente;
    private int id_traje;
    private int id_empleado;
    private int id_taller;
    private String dia;
    private String hora;
    private int duracion;
	
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
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
}
