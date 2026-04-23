package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Modelo {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/Proyecto-Integrador";
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

	/**
	 * Metodo para Crear la tabla de citas
	 * 
	 * @return
	 */
	public ArrayList<Cita> recuperarCitas() {
		ArrayList<Cita> citas = new ArrayList<Cita>();
		Connection conexion = getConexion();

		String query = "SELECT c.id_cita, c.dia, c.hora, c.duracion, cl.nombre AS nombre_cliente, ta.nombre_sala AS nombre_taller, e.nombre AS nombre_empleado, tr.nombre AS nombre_traje, c.id_cliente, c.id_taller, c.id_traje, c.id_empleado_responsable "
	            + "FROM CITA c "
	            + "JOIN CLIENTE cl ON c.id_cliente = cl.id_cliente "
	            + "JOIN TALLER ta ON c.id_taller = ta.id_taller "
	            + "JOIN TRAJE tr ON c.id_traje = tr.id_traje "   
	            + "JOIN EMPLEADO e ON c.id_empleado_responsable = e.id_empleado";
		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);

			// Añadimos los datos
			while (rs.next()) {
				Cita c = new Cita();

				c.setId_cita(rs.getInt("id_cita"));
	            c.setDia(rs.getDate("dia"));
	            c.setHora(rs.getTime("hora"));
	            c.setDuracion(rs.getInt("duracion"));
	            // Los id no se mostraran pero son necesarios
	            c.setId_cliente(rs.getInt("id_cliente"));
	            c.setId_taller(rs.getInt("id_taller"));
	            c.setId_empleado(rs.getInt("id_empleado_responsable"));
	            c.setId_traje(rs.getInt("id_traje"));
	            // En lugar de los id se mostrara esto.
	            c.setNombreCliente(rs.getString("nombre_cliente"));
	            c.setNombreTaller(rs.getString("nombre_taller"));
	            c.setNombreEmpleadoResponsable(rs.getString("nombre_empleado"));
	            c.setNombreTraje(rs.getString("nombre_traje"));

				citas.add(c);

			}
		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(null, "Error de SQL: " + e.getMessage());ç
			System.err.println("Error de SQL: " + e.getMessage());
			// Si o si cerramos la conexion, haya errores o no.
		} finally {
			cerrarConexion(conexion);
		}

		return citas;
	}

	/**
	 * Metodo para crear la tabla de citas propias del aprendiz
	 * @param idAprendiz
	 * @return
	 */
	public ArrayList<Cita> recuperarCitasPropias(int idEmpleado) {
		ArrayList<Cita> citasPropias = new ArrayList<>();
		Connection conexion = getConexion();
		
		String query = "SELECT c.id_cita, c.dia, c.hora, c.duracion, "
                + "cl.nombre AS nombre_cliente, "
                + "ta.nombre_sala AS nombre_taller, "
                + "e.nombre AS nombre_empleado, "
                + "tr.nombre AS nombre_traje, "
                + "c.id_cliente, c.id_taller, c.id_traje, c.id_empleado_responsable "
                + "FROM CITA c "
                + "JOIN CLIENTE cl ON c.id_cliente = cl.id_cliente "
                + "JOIN TALLER ta ON c.id_taller = ta.id_taller "
                + "JOIN TRAJE tr ON c.id_traje = tr.id_traje "
                + "JOIN EMPLEADO e ON c.id_empleado_responsable = e.id_empleado "
                + "WHERE c.id_empleado_responsable = ?";
		
		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, idEmpleado);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Cita c = new Cita();
				
				c.setId_cita(rs.getInt("id_cita"));
	            c.setDia(rs.getDate("dia"));
	            c.setHora(rs.getTime("hora"));
	            c.setDuracion(rs.getInt("duracion"));
	            
	            // Guardamos los nombres para la tabla
	            c.setNombreCliente(rs.getString("nombre_cliente"));
	            c.setNombreTaller(rs.getString("nombre_taller"));
	            c.setNombreEmpleadoResponsable(rs.getString("nombre_empleado"));
	            c.setNombreTraje(rs.getString("nombre_traje"));
	            
	            // Igual que en recuperarCitas, aqui hacen falta los id
	            c.setId_empleado(rs.getInt("id_empleado_responsable"));
	            c.setId_cliente(rs.getInt("id_cliente"));
	            
	            citasPropias.add(c);
			}
		} catch(SQLException sqlex) {
			System.err.println("Error en citas propias: " + sqlex.getMessage());
		} finally {
			cerrarConexion(conexion);
		}
		return citasPropias;
	}

	public ArrayList<Taller> recuperarTalleres() {
		ArrayList<Taller> talleres = new ArrayList<Taller>();

		Connection conexion = getConexion();

		String query = "SELECT id_taller, nombre_sala, tipo_sala FROM TALLER";

		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);

			// Añadimos los datos
			while (rs.next()) {
				Taller t = new Taller();
				t.setId_taller(rs.getInt("id_taller"));
				t.setNombre(rs.getString("nombre_sala"));
				t.setTipo_sala(rs.getString("tipo_sala"));
				talleres.add(t);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de SQL: " + e.getMessage());
			// Si o si cerramos la conexion, haya errores o no.
		} finally {
			cerrarConexion(conexion);
		}

		return talleres;
	}

	public ArrayList<Cliente> recuperarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		Connection conexion = getConexion();

		String query = "SELECT id_taller, nombre_sala, tipo_sala FROM TALLER";

		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);

			Taller t = new Taller();
			// Añadimos los datos
			while (rs.next()) {
				t.setId_taller(rs.getInt("id_taller"));
				t.setNombre(rs.getString("nombre_sala"));
				t.setTipo_sala(rs.getString("tipo_sala"));
//
//				modeloTabla.addRow(fila);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de SQL: " + e.getMessage());
			// Si o si cerramos la conexion, haya errores o no.
		} finally {
			cerrarConexion(conexion);
		}

		return clientes;
	}

	public String obtenerCategoria(int id, String contrasena) {
		String categoria = null;
		String query = "SELECT categoria FROM EMPLEADO WHERE id_empleado = ? AND contrasena = ?";
		Connection conexion = getConexion();

		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, contrasena);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				categoria = rs.getString("categoria");
			}
		} catch (SQLException sqlex) {
			System.err.println("Error en ObtenerCategoriaEmpleado: " + sqlex.getMessage());
		} finally {
			cerrarConexion(conexion);
		}
		return categoria;
	}

	public boolean crearTaller(Taller taller) {
		String query = "INSERT INTO TALLER (id_taller, nombre_sala, tipo_sala) VALUES (?, ?, ?)";
		Connection conexion = getConexion();

		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, taller.getId_taller());
			pst.setString(2, taller.getNombre());
			pst.setString(3, taller.getTipo_sala());

			int filasAfectadas = pst.executeUpdate();
			// Si hay cambios devolvera true
			return filasAfectadas > 0;

		} catch (SQLException sqlex) {
			System.err.println("Error al crear el taller: " + sqlex.getMessage());
			return false;
		} finally {
			cerrarConexion(conexion);
		}

	}

	public boolean eliminarTaller(int idTaller) {
		String query = "DELETE FROM TALLER WHERE id_taller = ?";
		Connection conexion = getConexion();

		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, idTaller);

			int resultado = pst.executeUpdate();
			// Si hay algun cambio devuelve true
			return resultado > 0;
		} catch (SQLException sqlex) {
			System.err.println("Error al eliminar taller: " + sqlex.getMessage());
			return false;
		} finally {
			cerrarConexion(conexion);
		}
	}

	public boolean modificarTaller(Taller taller) {
		String query = "UPDATE TALLER SET nombre_sala = ?, tipo_sala = ? WHERE id_taller = ?";
		Connection conexion = getConexion();

		try {
			PreparedStatement pst = conexion.prepareStatement(query);

			pst.setString(1, taller.getNombre());
			pst.setString(2, taller.getTipo_sala());
			pst.setInt(3, taller.getId_taller());

			int filasAfectadas = pst.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException sqlex) {
			System.err.println("Error al modificar el taller: " + sqlex.getMessage());
		} finally {
			cerrarConexion(conexion);
		}

		return false;
	}

	public boolean crearCita(Cita cita) {
		String query = "INSERT INTO CITA (id_cita, dia, hora, duracion, id_cliente, id_taller, id_empleado_responsable, id_traje) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conexion = getConexion();

		try (PreparedStatement pst = conexion.prepareStatement(query)) {
	        pst.setInt(1, cita.getId_cita());
	        pst.setDate(2, new java.sql.Date(cita.getDia().getTime()));
	        pst.setString(3, cita.getHoraString());
	        pst.setInt(4, cita.getDuracion());
	        
	        pst.setInt(5, cita.getId_cliente());
	        pst.setInt(6, cita.getId_taller());
	        pst.setInt(7, cita.getId_empleado());
	        pst.setInt(8, cita.getId_traje());

	        return pst.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("Error al insertar cita: " + e.getMessage());
	        return false;
	    } finally {
	        cerrarConexion(conexion);
	    }
	}
	
	public boolean eliminarCita(int idCita) {
		String query = "DELETE FROM CITA WHERE id_cita = ?";
		Connection conexion = getConexion();

		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, idCita);

			int resultado = pst.executeUpdate();
			// Si hay algun cambio devuelve true
			return resultado > 0;
		} catch (SQLException sqlex) {
			System.err.println("Error al eliminar cita: " + sqlex.getMessage());
			return false;
		} finally {
			cerrarConexion(conexion);
		}
	}
	
	public int obtenerIdCliente(String nombre) {
	    String query = "SELECT id_cliente FROM CLIENTE WHERE nombre = ?";
	    try (Connection con = getConexion(); 
	         PreparedStatement pst = con.prepareStatement(query)) {
	        
	        pst.setString(1, nombre);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("id_cliente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    // Retorna -1 si no lo encuentra
	    return -1; 
	}
	
	public int obtenerIdEmpleado(String nombre) {
	    String query = "SELECT id_empleado FROM EMPLEADO WHERE nombre = ?";
	    try (Connection con = getConexion(); 
	         PreparedStatement pst = con.prepareStatement(query)) {
	        
	        pst.setString(1, nombre);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("id_empleado");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    // Retorna -1 si no lo encuentra
	    return -1; 
	}
	
	public int obtenerIdTaller(String nombre) {
	    String query = "SELECT id_taller FROM TALLER WHERE nombre = ?";
	    try (Connection con = getConexion(); 
	         PreparedStatement pst = con.prepareStatement(query)) {
	        
	        pst.setString(1, nombre);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("id_taller");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    // Retorna -1 si no lo encuentra
	    return -1; 
	}
	
	public int obtenerIdTraje(String nombre) {
	    String query = "SELECT id_traje FROM TRAJE WHERE nombre = ?";
	    try (Connection con = getConexion(); 
	         PreparedStatement pst = con.prepareStatement(query)) {
	        
	        pst.setString(1, nombre);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("id_traje");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1; // Retorna -1 si no lo encuentra
	}

}
