package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaGestionTalleres;

public class ControladorEliminarTaller implements ActionListener {

    private VentanaGestionTalleres vista;

    public ControladorEliminarTaller(VentanaGestionTalleres vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Obtenemos el ID del taller seleccionado en la vista
        int idTaller = vista.getIdTallerSeleccionado();

        // Si es -1, significa que no hay ninguna fila seleccionada
        if (idTaller == -1) {
            JOptionPane.showMessageDialog(vista, "Por favor, selecciona un taller de la tabla para eliminarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Pedimos confirmación al usuario antes de borrar
        int confirmacion = JOptionPane.showConfirmDialog(vista, 
                "¿Estás seguro de que deseas eliminar el taller con ID " + idTaller + "?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // 3. Conexión y borrado en la Base de Datos
            Modelo conector = new Modelo();
            Connection conexion = conector.getConexion();

            String query = "DELETE FROM TALLER WHERE id_taller = ?";

            try (PreparedStatement pst = conexion.prepareStatement(query)) {
                pst.setInt(1, idTaller);

                int resultado = pst.executeUpdate();
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(vista, "Taller eliminado correctamente.");
                    // 4. Refrescamos la tabla en la misma vista sin tener que cerrarla
                    vista.cargarDatosTalleres(); 
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo encontrar el taller a eliminar.");
                }
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar en BD: " + sqlex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            } finally {
                conector.cerrarConexion(conexion);
            }
        }
    }
}