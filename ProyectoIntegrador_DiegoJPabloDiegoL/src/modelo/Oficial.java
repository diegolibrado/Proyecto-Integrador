package modelo;

/**
 * Clase Oficial que hereda de Empleado.
 */
public class Oficial extends Empleado {
    public Oficial(int id_empleado, String nombre, String apodo, String categoria, String contrasena) {
        // super llama al constructor de Empleado
        super(id_empleado, nombre, apodo, categoria, contrasena);
    }
    /**
     * Valida si el tipo de cita es uno de los permitidos para el Oficial.
     */
    public boolean validarTipoCita(String tipo) {
        return tipo.equalsIgnoreCase("Diseño") || 
               tipo.equalsIgnoreCase("Costura") || 
               tipo.equalsIgnoreCase("Pruebas");
    }

    /**
     * Verifica la regla de negocio
     */
    public boolean puedeAsignarAprendices(int cantidad) {
        return cantidad >= 0 && cantidad <= 2;
    }

    /**
     * Compara el ID del oficial con el ID asignado a una cita.
     */
    public boolean esDuenioDeCita(int idEmpleadoEnCita) {
        // getId_empleado() es el método que acabas de enseñarme de la clase Empleado
        return this.getId_empleado() == idEmpleadoEnCita;
    }
}