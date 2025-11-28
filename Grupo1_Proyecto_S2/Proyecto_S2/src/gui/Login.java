package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Arreglo.conexionMysql;


public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel LgClave;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JTextField LgUsuario;
    private JButton btnLgIngresar;
    private JPasswordField LgClaveTxt;
    private JButton btnRegistrar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Imagen/Banco.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        LgClave = new JPanel();
        LgClave.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(LgClave);
        LgClave.setLayout(null);

        lblNewLabel = new JLabel("LOGIN SCOTIABANK");
        lblNewLabel.setBounds(150, 11, 180, 21);
        LgClave.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Usuario:");
        lblNewLabel_1.setBounds(39, 70, 80, 14);
        LgClave.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Clave:");
        lblNewLabel_2.setBounds(39, 107, 80, 14);
        LgClave.add(lblNewLabel_2);

        LgUsuario = new JTextField();
        LgUsuario.setBounds(110, 67, 180, 20);
        LgClave.add(LgUsuario);
        LgUsuario.setColumns(10);

        LgClaveTxt = new JPasswordField();
        LgClaveTxt.setBounds(110, 104, 180, 20);
        LgClave.add(LgClaveTxt);

        btnLgIngresar = new JButton("INGRESAR");
        btnLgIngresar.addActionListener(this);
        btnLgIngresar.setBounds(61, 162, 124, 23);
        LgClave.add(btnLgIngresar);

        btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.addActionListener(this);
        btnRegistrar.setBounds(242, 162, 124, 23);
        LgClave.add(btnRegistrar);
    }

    private boolean validarUsuario(String usuario, String clave) {
        try {
            Connection cx = conexionMysql.getConexion();
            PreparedStatement ps = cx.prepareStatement(
                "SELECT * FROM usuarios WHERE usuario=? AND clave=?"
            );
            ps.setString(1, usuario);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error BD: " + e.getMessage());
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnRegistrar) {
            Registrar ru = new Registrar();
            ru.setVisible(true);
        }

        if (e.getSource() == btnLgIngresar) {
            do_btnLgIngresar_actionPerformed(e);
        }
    }

    
    protected void do_btnLgIngresar_actionPerformed(ActionEvent e) {

        String usuario = LgUsuario.getText().trim();
        String clave = new String(LgClaveTxt.getPassword());

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese usuario y clave.");
            return;
        }

        if (validarUsuario(usuario, clave)) {

            JOptionPane.showMessageDialog(this, "Bienvenido al sistema " + usuario);

            V1 ventanaPrincipal = new V1();
            ventanaPrincipal.setVisible(true);

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos.");
        }
    }
    
    
}
