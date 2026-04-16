package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Modelo {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/tu_base_datos";
    private static final String usuario = "root";
    private static final String contrasena = "2004";
    
    /**
	 * Método para conectar la base de datos
	 * 
	 * @return
	 */
	public Connection getConexion() {
		Connection conexion = null;
		// Controlamos excepciones a la hora de conectar la base de datos
		try {
			// cargamos el driver
			Class.forName(driver);

			// obtenemos conexión
			conexion = DriverManager.getConnection(url, usuario, contrasena);
			System.out.println("Conexión establecida");
		} catch (Exception e) {
			System.out.println("Error al conectar con la BBDD:");
			e.printStackTrace();
		}

		return conexion;
	}

	/**
	 * Metodo para cerrar la conexion de la base de datos con el proyecto
	 * 
	 * @param c
	 */
	public void cerrarConexion(Connection c) {
		// Control de excepciones para cerrar la conexion
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
