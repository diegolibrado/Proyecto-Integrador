package modelo;

/**
 * 
 */
public class Cliente {
	private int id_cliente;
    private String nombre;
    private String superpoder;
    private String colores;
    
    //Getters y Setters
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSuperpoder() {
		return superpoder;
	}
	public void setSuperpoder(String superpoder) {
		this.superpoder = superpoder;
	}
	public String getColores() {
		return colores;
	}
	public void setColores(String colores) {
		this.colores = colores;
	}
}
