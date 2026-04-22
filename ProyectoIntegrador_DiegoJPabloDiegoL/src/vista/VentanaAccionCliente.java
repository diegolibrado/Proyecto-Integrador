package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import modelo.Modelo;
import java.sql.*;

public class VentanaAccionCliente extends JDialog {
    private JTextField txtID, txtNombre, txtSuperpoder, txtColores;
    private JButton btnAccion;
    private JLabel lblTitulo;
    private String modo;
    private VentanaGestionClientes ventanaPadre;

    public VentanaAccionCliente(VentanaGestionClientes padre, String modo) {
        super(padre, true);
        this.ventanaPadre = padre;
        this.modo = modo;
        configInicial();
        inicializarComponentes();
        ajustarSegunModo();
    }

    private void configInicial() {
        setSize(960, 540);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Gestión de Clientes");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
        lblTitulo.setBounds(22, 63, 600, 40);
        getContentPane().add(lblTitulo);

        JPanel pnlCentral = new JPanel();
        pnlCentral.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
        pnlCentral.setBackground(new Color(165, 191, 201));
        pnlCentral.setBounds(139, 136, 782, 280); // Un poco más alto para los nuevos campos
        pnlCentral.setLayout(null);
        getContentPane().add(pnlCentral);

        // Campos
        crearEtiquetaYCampo(pnlCentral, "ID:", 30, txtID = new JTextField());
        crearEtiquetaYCampo(pnlCentral, "Nombre:", 70, txtNombre = new JTextField());
        crearEtiquetaYCampo(pnlCentral, "Superpoder:", 110, txtSuperpoder = new JTextField());
        crearEtiquetaYCampo(pnlCentral, "Colores:", 150, txtColores = new JTextField());

        btnAccion = new JButton("Confirmar");
        btnAccion.setBounds(160, 210, 200, 35);
        btnAccion.addActionListener(e -> ejecutarAccion());
        pnlCentral.add(btnAccion);

        JLabel lblFondo = new JLabel("");
        lblFondo.setBounds(0, 0, 944, 501);
        lblFondo.setIcon(new ImageIcon("img\\fondo.jpeg"));
        getContentPane().add(lblFondo);
    }

    private void crearEtiquetaYCampo(JPanel p, String texto, int y, JTextField txt) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Verdana", Font.BOLD, 14));
        lbl.setBounds(50, y, 120, 25);
        p.add(lbl);
        txt.setBounds(180, y, 400, 25);
        p.add(txt);
    }

    private void ajustarSegunModo() {
        if (modo.equals("CREAR")) {
            lblTitulo.setText("Registrar Nuevo Héroe/Cliente");
            txtID.setEditable(false);
            txtID.setText("Auto");
        } else {
            lblTitulo.setText("Modificar Cliente");
            txtID.setEditable(false);
        }
    }

    public void prellenarDatos(String id, String nom, String superp, String col) {
        txtID.setText(id);
        txtNombre.setText(nom);
        txtSuperpoder.setText(superp);
        txtColores.setText(col);
    }

    private void ejecutarAccion() {
        Modelo mod = new Modelo();
        Connection con = mod.getConexion();
        String sql = "";
        
        try {
            if (modo.equals("CREAR")) {
                sql = "INSERT INTO CLIENTE (nombre, superpoder, colores) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtNombre.getText());
                ps.setString(2, txtSuperpoder.getText());
                ps.setString(3, txtColores.getText());
                ps.executeUpdate();
            } else if (modo.equals("MODIFICAR")) {
                sql = "UPDATE CLIENTE SET nombre=?, superpoder=?, colores=? WHERE id_cliente=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtNombre.getText());
                ps.setString(2, txtSuperpoder.getText());
                ps.setString(3, txtColores.getText());
                ps.setInt(4, Integer.parseInt(txtID.getText()));
                ps.executeUpdate();
            }
            
            ventanaPadre.cargarDatosCitas(); // Refresca la tabla automáticamente
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}