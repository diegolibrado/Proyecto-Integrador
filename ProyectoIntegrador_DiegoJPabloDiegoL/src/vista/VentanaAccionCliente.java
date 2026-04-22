package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class VentanaAccionCliente extends JDialog {
    private JTextField txtID, txtNombre, txtSuperpoder, txtColores;
    private JButton btnConfirmar;
    private JLabel lblTitulo;
    private String modo;
    private boolean guardado = false; // Importante para saber si el usuario aceptó

    public VentanaAccionCliente(JFrame padre, String modo) {
        super(padre, true);
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
        pnlCentral.setBounds(139, 136, 782, 280);
        pnlCentral.setLayout(null);
        getContentPane().add(pnlCentral);

        crearEtiquetaYCampo(pnlCentral, "ID:", 30, txtID = new JTextField());
        crearEtiquetaYCampo(pnlCentral, "Nombre:", 70, txtNombre = new JTextField());
        crearEtiquetaYCampo(pnlCentral, "Superpoder:", 110, txtSuperpoder = new JTextField());
        crearEtiquetaYCampo(pnlCentral, "Colores:", 150, txtColores = new JTextField());

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(160, 210, 200, 35);
        btnConfirmar.addActionListener(e -> {
            guardado = true; // El usuario quiere guardar los cambios en la tabla
            dispose();
        });
        pnlCentral.add(btnConfirmar);

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
        txtID.setEditable(false);
        if (modo.equals("CREAR")) {
            lblTitulo.setText("Registrar Nuevo Héroe/Cliente");
            txtID.setText("Auto");
        } else {
            lblTitulo.setText("Modificar Cliente");
        }
    }

    public void prellenarDatos(String id, String nom, String superp, String col) {
        txtID.setText(id);
        txtNombre.setText(nom);
        txtSuperpoder.setText(superp);
        txtColores.setText(col);
    }

    // Getters para que la ventana principal lea los datos
    public String getNombre() { return txtNombre.getText(); }
    public String getSuperpoder() { return txtSuperpoder.getText(); }
    public String getColores() { return txtColores.getText(); }
    public boolean isGuardado() { return guardado; }
}