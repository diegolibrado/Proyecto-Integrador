package vista.oficial;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Cita;
import modelo.Modelo;
import vista.VentanaLogin;

public class VentanadeOficial extends JFrame {

    private DefaultTableModel modeloTabla;
    private JTable table;
    private JButton btnCrearCita, btnEliminarCita, btnModificarCita;

    public VentanadeOficial(String titulo) {
        super(titulo);
        configInicial();
        inicializarComponentes();
        
        // Carga inicial de datos
        Modelo m = new Modelo();
        cargarDatosCitas(m.recuperarCitas());
    }

    private void configInicial() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setSize(960, 540);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        // Título
        JLabel lblTitulo = new JLabel("Panel de Citas: Oficial");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
        lblTitulo.setBounds(22, 63, 500, 40);
        getContentPane().add(lblTitulo);

        // Panel horizontal (Gris)
        JPanel pnlBarraHorizontal = new JPanel();
        pnlBarraHorizontal.setBackground(new Color(196, 204, 203));
        pnlBarraHorizontal.setBounds(0, 111, 944, 282);
        pnlBarraHorizontal.setLayout(null);
        getContentPane().add(pnlBarraHorizontal);

        // BOTONES
        btnCrearCita = new JButton("Crear");
        btnCrearCita.setBackground(new Color(165, 191, 201));
        btnCrearCita.setBounds(22, 25, 109, 30);
        pnlBarraHorizontal.add(btnCrearCita);

        btnEliminarCita = new JButton("Eliminar");
        btnEliminarCita.setBackground(new Color(165, 191, 201));
        btnEliminarCita.setBounds(22, 63, 109, 30);
        pnlBarraHorizontal.add(btnEliminarCita);

        btnModificarCita = new JButton("Modificar");
        btnModificarCita.setBackground(new Color(165, 191, 201));
        btnModificarCita.setBounds(22, 101, 109, 30);
        pnlBarraHorizontal.add(btnModificarCita);

        // Botón Cerrar Sesión
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaLogin vLogin = new VentanaLogin("Inicio de Sesión");
                vLogin.setControlador(new controlador.ControladorLogin(vLogin));
                vLogin.setVisible(true);
                dispose();
            }
        });
        btnCerrarSesion.setBackground(new Color(165, 191, 201));
        btnCerrarSesion.setBounds(5, 211, 140, 30);
        pnlBarraHorizontal.add(btnCerrarSesion);

        // TABLA
        JPanel pnlTablaContainer = new JPanel();
        pnlTablaContainer.setBorder(new LineBorder(new Color(68, 68, 68), 1, true));
        pnlTablaContainer.setBackground(new Color(165, 191, 201));
        pnlTablaContainer.setBounds(150, 25, 782, 236);
        pnlTablaContainer.setLayout(null);
        pnlBarraHorizontal.add(pnlTablaContainer);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 762, 216);
        pnlTablaContainer.add(scrollPane);

        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] {"DÍA", "HORA", "DURACIÓN", "CLIENTE", "TALLER", "RESPONSABLE", "TRAJE"}
        );
        table = new JTable(modeloTabla);
        scrollPane.setViewportView(table);

        // Footer y Fondo
        JLabel lblFooter = new JLabel("© 2026 Payo-Vallecano, Inc. Todos los derechos reservados");
        lblFooter.setBounds(0, 481, 944, 20);
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        lblFooter.setForeground(Color.WHITE);
        getContentPane().add(lblFooter);

        JLabel lblFondo = new JLabel(new ImageIcon("img\\fondo.jpeg"));
        lblFondo.setBounds(0, 0, 944, 501);
        getContentPane().add(lblFondo);
    }

    public void cargarDatosCitas(ArrayList<Cita> datosCitas) {
        modeloTabla.setRowCount(0);
        for (Cita c : datosCitas) {
            Object[] fila = new Object[7];
            fila[0] = c.getDia();
            fila[1] = c.getHora();
            fila[2] = c.getDuracion();
            fila[3] = "Cargando..."; // Aquí luego pondrás el nombre real
            fila[4] = "Cargando...";
            fila[5] = "Cargando...";
            fila[6] = "Cargando...";
            modeloTabla.addRow(fila);
        }
    }
}