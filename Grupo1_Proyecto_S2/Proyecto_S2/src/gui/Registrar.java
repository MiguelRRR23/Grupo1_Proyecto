package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Arreglo.conexionMysql; 

public class Registrar extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JComboBox<String> cbRol;
    private JButton btnGuardar;

    public Registrar() {
        setTitle("Registrar Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 420, 280);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("REGISTRO DE USUARIO");
        lblTitulo.setBounds(130, 10, 200, 20);
        contentPane.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(40, 60, 100, 20);
        contentPane.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(140, 60, 200, 20);
        contentPane.add(txtUsuario);

        JLabel lblClave = new JLabel("Clave:");
        lblClave.setBounds(40, 100, 100, 20);
        contentPane.add(lblClave);

        txtClave = new JPasswordField();
        txtClave.setBounds(140, 100, 200, 20);
        contentPane.add(txtClave);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(40, 140, 100, 20);
        contentPane.add(lblRol);

        cbRol = new JComboBox<>(new String[] {"EMPLEADO", "ADMIN"});
        cbRol.setBounds(140, 140, 200, 20);
        contentPane.add(cbRol);

        btnGuardar = new JButton("GUARDAR");
        btnGuardar.addActionListener(this);
        btnGuardar.setBounds(150, 190, 120, 30);
        contentPane.add(btnGuardar);
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

        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = conexionMysql.getConexion();
            if (cn == null) {
                JOptionPane.showMessageDialog(this, "No hay conexión con la base de datos.");
                return;
            }

            
            String sqlInsert = "INSERT INTO usuarios (usuario, clave, rol) VALUES (?,?,?)";
            ps = cn.prepareStatement(sqlInsert);
            ps.setString(1, usuario);
            ps.setString(2, clave);   
            ps.setString(3, rol);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Usuario guardado correctamente en la base de datos.");
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception ex2) {}
        }
    }
}
