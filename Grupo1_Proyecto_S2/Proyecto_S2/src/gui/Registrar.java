package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;

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

        cbRol = new JComboBox<>(new String[] {"ADMIN", "EMPLEADO"});
        cbRol.setModel(new DefaultComboBoxModel(new String[] {"EMPLEADO", "ADMIN"}));
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

        try {
            FileWriter fw = new FileWriter("C:\\\\Users\\\\User\\\\Desktop\\\\PROYECTO22\\\\Grupo1_Proyecto\\\\Grupo1_Proyecto_S2\\\\Proyecto_S2\\\\src\\\\Data\\\\usuarios.txt", true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(usuario + ";" + clave + ";" + rol);
            pw.close();
            fw.close();

            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar usuario.");
        }
    }
}

