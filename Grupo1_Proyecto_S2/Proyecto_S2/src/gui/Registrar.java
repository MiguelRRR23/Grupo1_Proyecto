package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Arreglo.conexionMysql;
import Arreglo.HorariosSql;
import Arreglo.UsuariosSql;
import Clase.Horario;
import java.awt.Color;
import gui.RoundedBorder;
import java.awt.Font;

public class Registrar extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JComboBox<String> cbRol;
    private JComboBox<Horario> cbHorario;
    private JButton btnGuardar;

    public Registrar() {
        setTitle("Registrar Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Ajustar tamaño para ofrecer mayor espacio a los campos y botones
        setBounds(100, 100, 450, 407);

        // Crear barra de menú para cerrar sesión
        JMenuBar menuBar = new JMenuBar();
        // Definir color de fondo suave para la barra de menú
        // Tono rojo suave #ed3729 para el fondo
        Color bgColor = new Color(237, 55, 41);
        menuBar.setBackground(bgColor);
        JMenu menuUsuario = new JMenu("Cuenta");
        menuUsuario.setForeground(java.awt.Color.WHITE);
        JMenuItem itemCerrar = new JMenuItem("Cerrar sesión");
        itemCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login lg = new Login();
                lg.setVisible(true);
                dispose();
            }
        });
        menuUsuario.add(itemCerrar);
        menuBar.add(menuUsuario);
        setJMenuBar(menuBar);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(bgColor);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(java.awt.Color.WHITE);
        lblUsuario.setBounds(59, 121, 100, 20);
        contentPane.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(159, 121, 200, 20);
        contentPane.add(txtUsuario);

        JLabel lblClave = new JLabel("Clave:");
        lblClave.setForeground(java.awt.Color.WHITE);
        lblClave.setBounds(59, 161, 100, 20);
        contentPane.add(lblClave);

        txtClave = new JPasswordField();
        txtClave.setBounds(159, 161, 200, 20);
        contentPane.add(txtClave);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setForeground(java.awt.Color.WHITE);
        lblRol.setBounds(59, 201, 100, 20);
        contentPane.add(lblRol);

        cbRol = new JComboBox<>(new String[] {"EMPLEADO", "ADMIN"});
        cbRol.setBounds(159, 201, 200, 20);
        contentPane.add(cbRol);

        // Combobox para seleccionar el horario. Se llena con datos de la base
        cbHorario = new JComboBox<>();
        cbHorario.setEnabled(false);
        cbHorario.setBounds(159, 231, 200, 20);
        contentPane.add(cbHorario);
        // Cargar horarios desde la base de datos
        HorariosSql hsql = new HorariosSql();
        for (Horario h : hsql.listar()) {
            cbHorario.addItem(h);
        }

        // Habilitar o deshabilitar la selección de horario según el rol
        cbRol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rolSeleccionado = cbRol.getSelectedItem().toString();
                cbHorario.setEnabled(rolSeleccionado.equals("EMPLEADO"));
            }
        });

        btnGuardar = new JButton("GUARDAR");
        btnGuardar.addActionListener(this);
        btnGuardar.setBounds(179, 281, 120, 35);
        contentPane.add(btnGuardar);
        // Personalizar el botón
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBackground(new Color(205, 45, 31));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBorder(new RoundedBorder(10, Color.WHITE));
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Registrar.class.getResource("/Imagen/bannerscotia.png")));
        lblNewLabel_2.setBounds(85, 11, 274, 41);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblRegistro = new JLabel("REGISTRO");
        lblRegistro.setForeground(Color.WHITE);
        lblRegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblRegistro.setBounds(182, 75, 80, 21);
        contentPane.add(lblRegistro);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarUsuario();
        }
    }

    private void guardarUsuario() {
        String usuario = txtUsuario.getText().trim();
        String clave = String.valueOf(txtClave.getPassword());
        String rol = cbRol.getSelectedItem().toString();

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        try {
            UsuariosSql usql = new UsuariosSql();
            Integer idTurno = null;
            // Si el rol es EMPLEADO, obtener el ID del turno seleccionado
            if ("EMPLEADO".equals(rol)) {
                Horario seleccionado = (Horario) cbHorario.getSelectedItem();
                if (seleccionado != null) {
                    idTurno = seleccionado.getIdTurno();
                }
            }
            boolean ok = usql.insertarUsuario(usuario, clave, rol, idTurno);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Usuario guardado correctamente en la base de datos.");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + ex.getMessage());
        }
    }
}
