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
	private static final String contrasena = "root";

	/**
	 * Método para conectar la base de datos
	 * * @return
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
	 * * @param c
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
	 * @return
	 */
	public ArrayList<Cita> recuperarCitas() {
		ArrayList<Cita> citas = new ArrayList<Cita>();

		// modeloTabla.setRowCount(0);

		// Modelo conector = new Modelo();
		Connection conexion = getConexion();

		String query = "SELECT c.dia, c.hora, c.duracion, cl.nombre AS cliente, ta.nombre_sala AS taller, e.nombre AS empleado, tr.nombre AS traje "
				+ "FROM CITA c " + "JOIN CLIENTE cl ON c.id_cliente = cl.id_cliente "
				+ "JOIN TALLER ta ON c.id_taller = ta.id_taller "
				+ "JOIN EMPLEADO e ON c.id_empleado_responsable = e.id_empleado "
				+ "JOIN TRAJE tr ON c.id_traje = tr.id_traje";
		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);

			Cita c = new Cita();
			// Añadimos los datos
			while (rs.next()) {

				c.setDia(rs.getDate("dia"));
//				fila[1] = rs.getTime("hora");
//				fila[2] = rs.getInt("duracion");
//				fila[3] = rs.getString("cliente");
//				fila[4] = rs.getString("taller");
//				fila[5] = rs.getString("empleado");
//				fila[6] = rs.getString("traje");

				// modeloTabla.addRow(fila);

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
	    String query = "SELECT id_cliente, nombre, superpoder, colores FROM CLIENTE";

	    try {
	        Statement st = conexion.createStatement();
	        ResultSet rs = st.executeQuery(query);

	        while (rs.next()) {
	            Cliente c = new Cliente();
	            // Agregado el ID para que los controladores puedan identificar al cliente
	            c.setId(rs.getInt("id_cliente")); 
	            c.setNombre(rs.getString("nombre"));
	            c.setSuperpoder(rs.getString("superpoder"));
	            c.setColores(rs.getString("colores"));
	            clientes.add(c);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error de SQL al recuperar clientes: " + e.getMessage());
	    } finally {
	        cerrarConexion(conexion);
	    }
	    return clientes;
	}

	public boolean crearCliente(Cliente cliente) {
	    // No incluimos el id_cliente porque es autoincremental en la BBDD
	    String query = "INSERT INTO CLIENTE (nombre, superpoder, colores) VALUES (?, ?, ?)";
	    Connection conexion = getConexion();
	    
	    try {
	        PreparedStatement pst = conexion.prepareStatement(query);
	        pst.setString(1, cliente.getNombre());
	        pst.setString(2, cliente.getSuperpoder());
	        pst.setString(3, cliente.getColores());
	        
	        int filasAfectadas = pst.executeUpdate();
	        return filasAfectadas > 0;
	        
	    } catch(SQLException sqlex) {
	        System.err.println("Error al crear el cliente: " + sqlex.getMessage());
	        return false;
	    } finally {
	        cerrarConexion(conexion);
	    }
	}

	// método de eliminar cliente
	public boolean eliminarCliente(int idCliente) {
		String query = "DELETE FROM CLIENTE WHERE id_cliente = ?";
		Connection conexion = getConexion();
		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, idCliente);
			int resultado = pst.executeUpdate();
			return resultado > 0;
		} catch(SQLException sqlex) {
			System.err.println("Error al eliminar cliente: " + sqlex.getMessage());
			return false;
		} finally {
			cerrarConexion(conexion);
		}	
	}

	//  metodo de modificar cliente
	public boolean modificarCliente(Cliente cliente) {
		String query = "UPDATE CLIENTE SET nombre = ?, superpoder = ?, colores = ? WHERE id_cliente = ?";
		Connection conexion = getConexion();
		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, cliente.getNombre());
			pst.setString(2, cliente.getSuperpoder());
			pst.setString(3, cliente.getColores());
			pst.setInt(4, cliente.getId());
			int filasAfectadas = pst.executeUpdate();
			return filasAfectadas > 0;
		} catch(SQLException sqlex) {
			System.err.println("Error al modificar el cliente: " + sqlex.getMessage());
			return false;
		} finally {
			cerrarConexion(conexion);
		}
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
			
			if(rs.next()) {
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
		String query= "INSERT INTO TALLER (id_taller, nombre_sala, tipo_sala) VALUES (?, ?, ?)";
		Connection conexion = getConexion();
		
		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, taller.getId_taller());
			pst.setString(2, taller.getNombre());
			pst.setString(3, taller.getTipo_sala());
			
			int filasAfectadas = pst.executeUpdate();
			// Si hay cambios devolvera true
			return filasAfectadas > 0;
			
		} catch(SQLException sqlex) {
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
		} catch(SQLException sqlex) {
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
			
		}catch(SQLException sqlex) {
			System.err.println("Error al modificar el taller: " + sqlex.getMessage());
		}finally {
			cerrarConexion(conexion);
		}
		
		return false;
	}

	
//	public boolean crearCliente(Cliente cliente) {
//		String query= "INSERT INTO TALLER (id_taller, nombre_sala, tipo_sala) VALUES (?, ?, ?)";
//		Connection conexion = getConexion();
//		
//		try {
//			PreparedStatement pst = conexion.prepareStatement(query);
//			pst.setInt(1, taller.getId_taller());
//			pst.setString(2, taller.getNombre());
//			pst.setString(3, taller.getTipo_sala());
//			
//			int filasAfectadas = pst.executeUpdate();
//			// Si hay cambios devolvera true
//			return filasAfectadas > 0;
//			
//		} catch(SQLException sqlex) {
//			System.err.println("Error al crear el taller: " + sqlex.getMessage());
//			return false;
//		} finally {
//			cerrarConexion(conexion);
//		}
//		
//	}
//
//	public boolean eliminarCliente(int idCliente) {
//		String query = "DELETE FROM TALLER WHERE id_taller = ?";
//		Connection conexion = getConexion();
//		
//		try {
//			PreparedStatement pst = conexion.prepareStatement(query);
//			pst.setInt(1, idTaller);
//			
//			int resultado = pst.executeUpdate();
//			// Si hay algun cambio devuelve true
//			return resultado > 0;
//		} catch(SQLException sqlex) {
//			System.err.println("Error al eliminar taller: " + sqlex.getMessage());
//			return false;
//		} finally {
//			cerrarConexion(conexion);
//		}	
//	}
//
//	public boolean modificarCliente(Cliente cliente) {
//		String query = "UPDATE TALLER SET nombre_sala = ?, tipo_sala = ? WHERE id_taller = ?";
//		Connection conexion = getConexion();
//		
//		try {
//			PreparedStatement pst = conexion.prepareStatement(query);
//			
//			pst.setString(1, taller.getNombre());
//			pst.setString(2, taller.getTipo_sala());
//			pst.setInt(3, taller.getId_taller());
//			
//			int filasAfectadas = pst.executeUpdate();
//			return filasAfectadas > 0;
//			
//		}catch(SQLException sqlex) {
//			System.err.println("Error al modificar el taller: " + sqlex.getMessage());
//		}finally {
//			cerrarConexion(conexion);
//		}
//		
//		return false;
//	}
	
}


//	/**
//	 * Método para obtener el ID del taller seleccionado en la tabla. Devuelve -1 si
//	 * no hay ninguna fila seleccionada.
//	 */
//	public int getIdTallerSeleccionado() {
//		int filaSeleccionada = table.getSelectedRow();
//		if (filaSeleccionada == -1) {
//			return -1; // Nada seleccionado
//		}
//		// Sabiendo que el ID está en la columna 0
//		return (int) modeloTabla.getValueAt(filaSeleccionada, 0);
//	}
//}
