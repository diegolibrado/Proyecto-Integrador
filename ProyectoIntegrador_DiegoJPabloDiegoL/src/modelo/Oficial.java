package modelo;

public class Oficial {
    // Variables propias (sin depender de Empleado)
    private int id_empleado;
    private String nombre;
    private String apodo;

    // Constructor sencillo
    public Oficial(int id_empleado, String nombre, String apodo) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apodo = apodo;
    }

    // --- MÉTODOS DE LÓGICA (El relleno que te pide el profe) ---

    public boolean validarTipoCita(String tipo) {
        return tipo.equalsIgnoreCase("Diseño") || 
               tipo.equalsIgnoreCase("Costura") || 
               tipo.equalsIgnoreCase("Pruebas");
    }

    public boolean puedeAsignarAprendices(int num) {
        return num >= 0 && num <= 2;
    }

    public boolean esDueñoDeCita(int idCita) {
        return this.id_empleado == idCita;
    }
}