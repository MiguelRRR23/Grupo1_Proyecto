package gui;

import java.awt.EventQueue;
import java.awt.Font;

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
import java.awt.Color;
import gui.RoundedBorder;
import javax.swing.ImageIcon;


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

    // Botón para mostrar información del programa
    private JButton btnInfo;

    // Guarda el identificador del usuario actualmente autenticado. Este valor
    // se inicializa luego de un inicio de sesión exitoso y puede ser
    // consultado por otras ventanas (por ejemplo, para registrar el
    // prestamista asociado a un préstamo).
    public static int currentUserId = -1;
    private JLabel lblNewLabel_3;

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
    	setTitle("Login");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Imagen/Banco.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ajustar tamaño de la ventana para acomodar el botón de información
        // Ajustar tamaño y centrar la ventana. Se aumenta ligeramente el alto
        // para dar más espacio al botón de información y a los logos.
        setBounds(100, 100, 520, 340);
        LgClave = new JPanel();
        LgClave.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(LgClave);
        LgClave.setLayout(null);
        // Establecer un tono rojo más suave para la ventana de login
        // Tono rojo suave #ed3729
        Color bgColor = new Color(237, 55, 41);
        LgClave.setBackground(bgColor);

        
 
        
        lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(227, 76, 80, 21);
        LgClave.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Usuario:");
        lblNewLabel_1.setForeground(java.awt.Color.WHITE);
        lblNewLabel_1.setBounds(110, 108, 80, 14);
        LgClave.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Clave:");
        lblNewLabel_2.setForeground(java.awt.Color.WHITE);
        lblNewLabel_2.setBounds(110, 145, 80, 14);
        LgClave.add(lblNewLabel_2);

        LgUsuario = new JTextField();
        LgUsuario.setBounds(181, 105, 200, 20);
        LgClave.add(LgUsuario);
        LgUsuario.setColumns(10);

        LgClaveTxt = new JPasswordField();
        LgClaveTxt.setBounds(181, 142, 200, 20);
        LgClave.add(LgClaveTxt);

        btnLgIngresar = new JButton("INGRESAR");
        btnLgIngresar.addActionListener(this);
        btnLgIngresar.setBounds(75, 200, 150, 30);
        LgClave.add(btnLgIngresar);

        btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.addActionListener(this);
        btnRegistrar.setBounds(266, 200, 150, 30);
        LgClave.add(btnRegistrar);

        // Crear botón de información
        btnInfo = new JButton("INFORMACIÓN");
        btnInfo.setBounds(350, 260, 130, 30);
        btnInfo.addActionListener(this);
        LgClave.add(btnInfo);
        
        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/Imagen/bannerscotia.png")));
        lblNewLabel_3.setBounds(107, 25, 274, 40);
        LgClave.add(lblNewLabel_3);

        // Estilizar botones con bordes redondeados y colores personalizados
        for (JButton b : new JButton[]{btnLgIngresar, btnRegistrar, btnInfo}) {
            b.setFocusPainted(false);
            // Color más oscuro para contraste con el fondo
            b.setBackground(new Color(205, 45, 31));
            b.setForeground(Color.WHITE);
            b.setBorder(new RoundedBorder(10, Color.WHITE));
        }

        // Colocar logo de Scotiabank en la parte superior izquierda

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
            return;
        }

        // Abrir ventana de información del programa
        if (e.getSource() == btnInfo) {
            InfoPrograma info = new InfoPrograma();
            info.setVisible(true);
            return;
        }
    }

    
    protected void do_btnLgIngresar_actionPerformed(ActionEvent e) {

        String usuario = LgUsuario.getText().trim();
        String clave = new String(LgClaveTxt.getPassword());

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese usuario y clave.");
            return;
        }

        // Obtener el rol del usuario a través de la base de datos
        String rol = obtenerRol(usuario, clave);
        if (rol != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido al sistema " + usuario);
            // Recuperar y almacenar el ID del usuario autenticado
            currentUserId = obtenerIdUsuario(usuario);
            // Si el usuario es administrador, abrir ventana especial
            if ("ADMIN".equalsIgnoreCase(rol)) {
                AdminV1 adminView = new AdminV1();
                adminView.setVisible(true);
            } else {
                V1 ventanaPrincipal = new V1();
                ventanaPrincipal.setVisible(true);
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos.");
        }
    }

    /**
     * Obtiene el rol del usuario si las credenciales son válidas. Devuelve
     * <code>null</code> si el usuario no existe o la clave es incorrecta.
     * @param usuario nombre de usuario
     * @param clave contraseña
     * @return rol del usuario (ADMIN o EMPLEADO), o null si no se encuentra
     */
    private String obtenerRol(String usuario, String clave) {
        try {
            java.sql.Connection cx = conexionMysql.getConexion();
            java.sql.PreparedStatement ps = cx.prepareStatement(
                    "SELECT rol FROM usuarios WHERE usuario=? AND clave=?");
            ps.setString(1, usuario);
            ps.setString(2, clave);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("rol");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener rol: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Devuelve el identificador del usuario según el nombre de usuario. Si no se
     * encuentra, devuelve -1.
     *
     * @param usuario nombre de usuario
     * @return id correspondiente o -1 si no se encuentra
     */
    private int obtenerIdUsuario(String usuario) {
        try {
            Connection cx = conexionMysql.getConexion();
            PreparedStatement ps = cx.prepareStatement(
                "SELECT id_usuario FROM usuarios WHERE usuario=?");
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_usuario");
            }
        } catch (Exception ex) {
            // Manejo silencioso; en caso de error se devuelve -1
        }
        return -1;
    }
    
    
}
