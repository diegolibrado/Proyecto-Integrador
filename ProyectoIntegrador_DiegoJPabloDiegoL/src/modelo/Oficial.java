package modelo;

/**
 * Clase del modelo para representar a un Oficial
 */
public class Oficial extends Empleado {

    // Constructor de Oficial
    public Oficial(int id_empleado, String nombre, String apodo) {
        // Envío de datos al constructor de la clase padre Empleado
        super(id_empleado, nombre, apodo);
    }

    // Ejemplo de método para obtener un saludo
    public String obtenerSaludo() {
        return "Bienvenido, Oficial " + this.nombre;
    }
}