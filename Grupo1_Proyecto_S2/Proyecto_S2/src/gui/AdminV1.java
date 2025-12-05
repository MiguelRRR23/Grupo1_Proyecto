package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Arreglo.CuentasSql;
import Arreglo.UsuariosSql;
import Arreglo.PrestamosSql;
import Arreglo.HorariosSql;
import Clase.Banco;
import Clase.Cuenta;
import Clase.Persona;
import Clase.Horario;
import Clase.Prestamo;
import gui.Login;
import gui.RoundedBorder;

/**
 * Ventana principal para el rol Administrador. Hereda la mayoría de
 * funcionalidades de la gestión de cuentas y préstamos de la versión
 * estándar (V1) e incorpora un panel adicional para gestionar usuarios
 * (empleados y administradores). Permite agregar y eliminar usuarios
 * directamente desde la interfaz.
 */
public class AdminV1 extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    // Panel principal
    private JPanel contentPane;
    private JTextField txtNom;
    private JTextField txtDni;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtCanti;
    private JScrollPane scrollPane;
    private JTextArea txtS;
    private JButton btnReportar;
    private JButton btnAdicionar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnModificar;
    // Botón de simulación eliminado al unificar con el de evaluación
    private JButton btnEvaluar;
    private JComboBox<String> comboBox;
    private JTextField txtPlazo;
    private JTextField txtTasa;
    // Evaluación
    private JTextField txtIngresos;
    private JTextField txtMotivo;
    // Fecha de inicio de pago (reemplaza al campo de deudas)
    private JTextField txtFechaInicio;

    // Panel de gestión de usuarios
    private JTextField txtEmpUsuario;
    private JPasswordField txtEmpClave;
    private JComboBox<String> cbEmpRol;
    private JButton btnAgregarEmp;
    private JButton btnEliminarEmp;
    private JButton btnListarEmp;
    private JTextArea txtEmpListado;
    private JScrollPane scrollEmp;

    // Botón para listar préstamos
    private JButton btnListarPrestamos;

    // Gestión de horarios (para asignar turnos a los usuarios)
    private HorariosSql horariosSql = new HorariosSql();
    private JComboBox<Horario> cbEmpHorario;

    private CuentasSql cuentasSql = new CuentasSql();
    private UsuariosSql usuariosSql = new UsuariosSql();
    private PrestamosSql prestamosSql = new PrestamosSql();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminV1 frame = new AdminV1();
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
    public AdminV1() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(AdminV1.class.getResource("/Imagen/Banco.png")));
        setTitle("SCOTIABANK - PRESTAMOS Y CREDITOS ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Mayor altura para incluir la sección de empleados
        // Ajustar altura para incluir la sección de usuarios y de prestamistas
        // Se aumenta la altura para proporcionar mayor espacio a la sección de gestión
        // de usuarios y evitar que los controles se vean comprimidos. También se
        // reserva espacio adicional para futuros elementos.
        setBounds(100, 100, 930, 873);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        // Definir un tono rojo más suave para la interfaz.
        // Tono rojo suave #ed3729 para el fondo
        Color bgColor = new Color(237, 55, 41);
        contentPane.setBackground(bgColor);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Barra de menú con opción para cerrar sesión
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(bgColor);
        JMenu menuUsuario = new JMenu("Cuenta");
        menuUsuario.setForeground(Color.WHITE);
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

        // Título y logo
        JLabel lblTitulo = new JLabel("GESTIÓN DE CUENTAS Y PRÉSTAMOS - ADMIN");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setBounds(200, 62, 600, 20);
        contentPane.add(lblTitulo);

        // Panel de datos del cliente
        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(null);
        panelCliente.setOpaque(false);
        panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));
        // Se incrementa la altura para incluir dirección, teléfono y email
        panelCliente.setBounds(10, 87, 280, 210);
        contentPane.add(panelCliente);

        int yPos = 25;
        int labelWidth = 130;
        int fieldWidth = 120;
        int rowHeight = 25;

        JLabel lblNomAp = new JLabel("Nombre y Apellido:");
        lblNomAp.setForeground(Color.WHITE);
        lblNomAp.setBounds(10, 56, labelWidth, 20);
        panelCliente.add(lblNomAp);

        txtNom = new JTextField();
        txtNom.setBounds(140, 56, fieldWidth, 20);
        panelCliente.add(txtNom);

        yPos += rowHeight;
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setForeground(Color.WHITE);
        lblDni.setBounds(10, 82, labelWidth, 20);
        panelCliente.add(lblDni);

        txtDni = new JTextField();
        txtDni.setBounds(140, 82, fieldWidth, 20);
        panelCliente.add(txtDni);

        yPos += rowHeight;
        JLabel lblDir = new JLabel("Dirección:");
        lblDir.setForeground(Color.WHITE);
        lblDir.setBounds(10, 114, labelWidth, 20);
        panelCliente.add(lblDir);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(140, 114, fieldWidth, 20);
        panelCliente.add(txtDireccion);

        yPos += rowHeight;
        JLabel lblTel = new JLabel("Teléfono:");
        lblTel.setForeground(Color.WHITE);
        lblTel.setBounds(10, 145, labelWidth, 20);
        panelCliente.add(lblTel);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 145, fieldWidth, 20);
        panelCliente.add(txtTelefono);

        yPos += rowHeight;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setBounds(10, 179, labelWidth, 20);
        panelCliente.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(140, 179, fieldWidth, 20);
        panelCliente.add(txtEmail);

        yPos += rowHeight;
        JLabel lblTipoC = new JLabel("Tipo de cuenta:");
        lblTipoC.setForeground(Color.WHITE);
        lblTipoC.setBounds(10, 26, labelWidth, 20);
        panelCliente.add(lblTipoC);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Corriente", "Credito", "Ahorro"}));
        comboBox.setBounds(140, 26, fieldWidth, 22);
        panelCliente.add(comboBox);

        // Panel de datos del préstamo
        JPanel panelPrestamo = new JPanel();
        panelPrestamo.setLayout(null);
        panelPrestamo.setOpaque(false);
        panelPrestamo.setBorder(BorderFactory.createTitledBorder("Datos del préstamo"));
        panelPrestamo.setBounds(310, 87, 280, 120);
        contentPane.add(panelPrestamo);

        JLabel lblCant = new JLabel("Cantidad:");
        lblCant.setForeground(Color.WHITE);
        lblCant.setBounds(10, 25, 100, 20);
        panelPrestamo.add(lblCant);

        txtCanti = new JTextField();
        txtCanti.setBounds(140, 25, 120, 20);
        panelPrestamo.add(txtCanti);

        JLabel lblPlazo = new JLabel("Plazo (meses):");
        lblPlazo.setForeground(Color.WHITE);
        lblPlazo.setBounds(10, 55, 100, 20);
        panelPrestamo.add(lblPlazo);

        txtPlazo = new JTextField();
        txtPlazo.setBounds(140, 55, 120, 20);
        panelPrestamo.add(txtPlazo);

        JLabel lblTasa = new JLabel("Tasa anual (%):");
        lblTasa.setForeground(Color.WHITE);
        lblTasa.setBounds(10, 85, 100, 20);
        panelPrestamo.add(lblTasa);

        txtTasa = new JTextField();
        txtTasa.setBounds(140, 85, 120, 20);
        panelPrestamo.add(txtTasa);

        // Panel de evaluación
        JPanel panelEval = new JPanel();
        panelEval.setLayout(null);
        panelEval.setOpaque(false);
        panelEval.setBorder(BorderFactory.createTitledBorder("Evaluación Préstamo"));
        panelEval.setBounds(600, 87, 290, 120);
        contentPane.add(panelEval);

        JLabel lblIng = new JLabel("Ingresos Mensuales (S/):");
        lblIng.setForeground(Color.WHITE);
        lblIng.setBounds(10, 25, 160, 20);
        panelEval.add(lblIng);

        txtIngresos = new JTextField();
        txtIngresos.setBounds(170, 25, 100, 20);
        panelEval.add(txtIngresos);

        JLabel lblMot = new JLabel("Motivo del préstamo:");
        lblMot.setForeground(Color.WHITE);
        lblMot.setBounds(10, 55, 160, 20);
        panelEval.add(lblMot);

        txtMotivo = new JTextField();
        txtMotivo.setBounds(170, 55, 100, 20);
        panelEval.add(txtMotivo);

        JLabel lblFechaInicio = new JLabel("F. inicio pago (YYYY-MM-DD):");
        lblFechaInicio.setForeground(Color.WHITE);
        lblFechaInicio.setBounds(10, 85, 200, 20);
        panelEval.add(lblFechaInicio);

        txtFechaInicio = new JTextField();
        txtFechaInicio.setBounds(170, 86, 100, 20);
        panelEval.add(txtFechaInicio);

        // Botones de acción
        int yBotones = 170;
        int ancho = 110;
        int x = 20;
        int esp = 10;

        btnReportar = new JButton("REPORTAR");
        btnReportar.setBounds(10, 317, ancho, 25);
        contentPane.add(btnReportar);
        btnReportar.addActionListener(this);

        btnAdicionar = new JButton("ADICIONAR");
        btnAdicionar.setBounds(248, 317, ancho, 25);
        contentPane.add(btnAdicionar);
        btnAdicionar.addActionListener(this);

        btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBounds(486, 317, ancho, 25);
        contentPane.add(btnBuscar);
        btnBuscar.addActionListener(this);

        btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBounds(368, 317, ancho, 25);
        contentPane.add(btnEliminar);
        btnEliminar.addActionListener(this);

        btnModificar = new JButton("MODIFICAR");
        btnModificar.setBounds(128, 317, ancho, 25);
        contentPane.add(btnModificar);
        btnModificar.addActionListener(this);

        // Unificar la simulación con la evaluación en un solo botón
        btnEvaluar = new JButton("SIMULAR/EVALUAR");
        // Avanzar dos espacios de botón: uno para SIMULAR y otro para EVALUAR en la versión anterior
        x += (ancho + esp) * 2;
        btnEvaluar.setBounds(600, 317, 150, 25);
        contentPane.add(btnEvaluar);
        btnEvaluar.addActionListener(this);
        // Botón adicional para listar todos los préstamos registrados, ubicado después del botón de evaluación
        btnListarPrestamos = new JButton("LISTAR PRÉSTAMOS");
        btnListarPrestamos.setBounds(754, 317, 150, 25);
        contentPane.add(btnListarPrestamos);
        btnListarPrestamos.addActionListener(this);

        // Ajustar fuente de los botones y aplicar estilo personalizado
        Font fBoton = new Font("Segoe UI", Font.PLAIN, 12);
        for (JButton b : new JButton[]{btnReportar, btnAdicionar, btnBuscar, btnEliminar, btnModificar, btnEvaluar, btnListarPrestamos}) {
            b.setFont(fBoton);
            b.setFocusPainted(false);
            // Usar un tono más oscuro para los botones que contraste con el fondo
            b.setBackground(new Color(205, 45, 31));
            b.setForeground(Color.WHITE);
            b.setBorder(new RoundedBorder(10, Color.WHITE));
        }

        // Área de texto para reportes y resultados
        scrollPane = new JScrollPane();
        scrollPane.setBounds(24, 357, 851, 230);
        contentPane.add(scrollPane);

        txtS = new JTextArea();
        txtS.setEditable(false);
        txtS.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtS.setLineWrap(false);
        scrollPane.setViewportView(txtS);

        // Panel de gestión de empleados
        JPanel panelEmp = new JPanel();
        panelEmp.setLayout(null);
        panelEmp.setOpaque(false);
        panelEmp.setBorder(BorderFactory.createTitledBorder("Gestión de Usuarios"));
        panelEmp.setBounds(10, 597, 880, 210);
        contentPane.add(panelEmp);

        JLabel lblUserEmp = new JLabel("Usuario:");
        lblUserEmp.setForeground(Color.WHITE);
        lblUserEmp.setBounds(20, 25, 80, 20);
        panelEmp.add(lblUserEmp);

        txtEmpUsuario = new JTextField();
        txtEmpUsuario.setBounds(100, 25, 150, 20);
        panelEmp.add(txtEmpUsuario);

        JLabel lblClaveEmp = new JLabel("Clave:");
        lblClaveEmp.setForeground(Color.WHITE);
        lblClaveEmp.setBounds(270, 25, 80, 20);
        panelEmp.add(lblClaveEmp);

        txtEmpClave = new JPasswordField();
        txtEmpClave.setBounds(330, 25, 150, 20);
        panelEmp.add(txtEmpClave);

        JLabel lblRolEmp = new JLabel("Rol:");
        lblRolEmp.setForeground(Color.WHITE);
        lblRolEmp.setBounds(500, 25, 80, 20);
        panelEmp.add(lblRolEmp);

        cbEmpRol = new JComboBox<>(new String[]{"EMPLEADO", "ADMIN"});
        cbEmpRol.setBounds(540, 25, 120, 22);
        panelEmp.add(cbEmpRol);

        // Campo para seleccionar el horario del usuario (solo aplica a empleados)
        JLabel lblHorarioEmp = new JLabel("Horario:");
        lblHorarioEmp.setForeground(Color.WHITE);
        lblHorarioEmp.setBounds(20, 55, 80, 20);
        panelEmp.add(lblHorarioEmp);

        cbEmpHorario = new JComboBox<>();
        cbEmpHorario.setBounds(100, 55, 230, 22);
        panelEmp.add(cbEmpHorario);
        // Cargar horarios desde la base de datos
        for (Horario h : horariosSql.listar()) {
            cbEmpHorario.addItem(h);
        }
        cbEmpHorario.setEnabled(false);

        // Acción para habilitar/deshabilitar el combo horario según el rol seleccionado
        cbEmpRol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rolSel = cbEmpRol.getSelectedItem().toString();
                boolean esEmpleado = "EMPLEADO".equalsIgnoreCase(rolSel);
                cbEmpHorario.setEnabled(esEmpleado);
            }
        });

        btnAgregarEmp = new JButton("AGREGAR");
        btnAgregarEmp.setBounds(680, 25, 100, 25);
        panelEmp.add(btnAgregarEmp);
        btnAgregarEmp.addActionListener(this);
        // Estilo para los botones del panel de usuarios
        btnAgregarEmp.setFont(fBoton);
        btnAgregarEmp.setFocusPainted(false);
        btnAgregarEmp.setBackground(new Color(205, 45, 31));
        btnAgregarEmp.setForeground(Color.WHITE);
        btnAgregarEmp.setBorder(new RoundedBorder(10, Color.WHITE));

        btnEliminarEmp = new JButton("ELIMINAR");
        // Ajustar posición para dar espacio al campo horario
        btnEliminarEmp.setBounds(680, 60, 100, 25);
        panelEmp.add(btnEliminarEmp);
        btnEliminarEmp.addActionListener(this);
        btnEliminarEmp.setFont(fBoton);
        btnEliminarEmp.setFocusPainted(false);
        btnEliminarEmp.setBackground(new Color(205, 45, 31));
        btnEliminarEmp.setForeground(Color.WHITE);
        btnEliminarEmp.setBorder(new RoundedBorder(10, Color.WHITE));

        btnListarEmp = new JButton("LISTAR");
        // Ajustar posición para dar espacio al campo horario
        btnListarEmp.setBounds(680, 95, 100, 25);
        panelEmp.add(btnListarEmp);
        btnListarEmp.addActionListener(this);
        btnListarEmp.setFont(fBoton);
        btnListarEmp.setFocusPainted(false);
        btnListarEmp.setBackground(new Color(205, 45, 31));
        btnListarEmp.setForeground(Color.WHITE);
        btnListarEmp.setBorder(new RoundedBorder(10, Color.WHITE));

        // Área para listar usuarios
        scrollEmp = new JScrollPane();
        // Mover ligeramente hacia abajo para no superponerse con los campos adicionales
        scrollEmp.setBounds(20, 85, 640, 120);
        panelEmp.add(scrollEmp);
        txtEmpListado = new JTextArea();
        txtEmpListado.setEditable(false);
        txtEmpListado.setFont(new Font("Consolas", Font.PLAIN, 12));
        scrollEmp.setViewportView(txtEmpListado);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(AdminV1.class.getResource("/Imagen/bannerscotia.png")));
        lblNewLabel.setBounds(316, 11, 274, 41);
        contentPane.add(lblNewLabel);

    }

    // Métodos auxiliares para lectura y validación (copiados de V1)
    int leerDNI() {
        String texto = txtDni.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo DNI no puede estar vacío.");
            return -1;
        }
        try {
            int dni = Integer.parseInt(texto);
            if (dni <= 0) {
                JOptionPane.showMessageDialog(this, "El DNI no puede ser negativo o cero.");
                return -1;
            }
            return dni;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Este campo solo permite números.");
            return -1;
        }
    }

    String leerNomApell() {
        String nom = txtNom.getText().trim();
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Nombres y Apellidos no puede estar vacío.");
            return null;
        }
        if (nom.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El campo Nombres y Apellidos no puede contener solo números.");
            return null;
        }
        return nom;
    }

    String leerTipo() {
        return comboBox.getSelectedItem().toString();
    }

    double LeerCanti() {
        try {
            return Double.parseDouble(txtCanti.getText());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    void Imprimir(String s) {
        txtS.append(s + "\n");
    }

    void Listado() {
        txtS.setText("");
        Imprimir("N° CUENTA\tDNI\tNOMBRE CLIENTE\tTIPO CUENTA\tMONTO\tCUOTAS\tF. INICIO\tF. FIN");
        Imprimir("------------------------------------------------------------------------------------------------------");

        for (Cuenta c : cuentasSql.listar()) {
            Imprimir(
                c.getNumeroCuenta() + "\t" +
                c.getPersona().getDni() + "\t" +
                c.getPersona().getNombre() + " " + c.getPersona().getApellido() + "\t" +
                c.getTipoCuenta() + "\t" +
                "S/ " + String.format("%.2f", c.getMonto()) + "\t" +
                c.getNumeroCuotas() + "\t" +
                (c.getFechaInicio() != null ? c.getFechaInicio().toString() : "-") + "\t" +
                (c.getFechaFin() != null ? c.getFechaFin().toString() : "-")
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnEvaluar) {
            evaluarPrestamo();
        } else if (src == btnModificar) {
            modificarCuenta();
        } else if (src == btnEliminar) {
            eliminarCuenta();
        } else if (src == btnBuscar) {
            buscarCuenta();
        } else if (src == btnAdicionar) {
            adicionarCuenta();
        } else if (src == btnReportar) {
            Listado();
        } else if (src == btnAgregarEmp) {
            agregarUsuario();
        } else if (src == btnEliminarEmp) {
            eliminarUsuario();
        } else if (src == btnListarEmp) {
            listarUsuarios();
        } else if (src == btnListarPrestamos) {
            listarPrestamos();
        }
    }

    // Métodos de acción para las cuentas (basados en V1)
    private void adicionarCuenta() {
        try {
            int dni = leerDNI();
            if (dni <= 0) return;

            String nombreCompleto = leerNomApell();
            if (nombreCompleto == null) return;

            double monto = LeerCanti();
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero.");
                return;
            }

            String tipo = leerTipo();

            // Leer número de cuotas (plazo)
            int numCuotas;
            try {
                numCuotas = Integer.parseInt(txtPlazo.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Plazo inválido.");
                return;
            }
            if (numCuotas <= 0) {
                JOptionPane.showMessageDialog(this, "El plazo debe ser mayor a cero.");
                return;
            }
            // Leer fecha de inicio de pago y calcular fecha final
            String fechaInicioStr = txtFechaInicio.getText().trim();
            if (fechaInicioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la fecha de inicio de pago.");
                return;
            }
            java.sql.Date fechaInicioSql;
            java.sql.Date fechaFinSql;
            try {
                java.time.LocalDate fi = java.time.LocalDate.parse(fechaInicioStr);
                fechaInicioSql = java.sql.Date.valueOf(fi);
                java.time.LocalDate ff = fi.plusMonths(numCuotas);
                fechaFinSql = java.sql.Date.valueOf(ff);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha de inicio inválido (YYYY-MM-DD).");
                return;
            }

            Persona personaBD = cuentasSql.buscarPersonaDNI(dni);

            if (personaBD == null) {
                // Dividir nombre completo en nombre y apellido si es posible
                String nombre = nombreCompleto;
                String apellido = "";
                // Leer datos adicionales del cliente
                String direccion = txtDireccion.getText().trim();
                if (direccion.isEmpty()) direccion = "Sin dirección";
                String telefono = txtTelefono.getText().trim();
                if (telefono.isEmpty()) telefono = "Sin teléfono";
                String email = txtEmail.getText().trim();
                if (email.isEmpty()) email = "Sin correo";
                Persona nuevaPersona = new Persona(
                        dni, nombre, apellido,
                        direccion, telefono, email
                );
                boolean okPersona = cuentasSql.insertarPersona(nuevaPersona);
                if (!okPersona) {
                    JOptionPane.showMessageDialog(this,
                            "No se pudo registrar la persona en la BD.");
                    return;
                }
                personaBD = nuevaPersona;
            }

            Banco banco = new Banco(
                    1, "Scotiabank Perú S.A.A.",
                    "Av. Canaval y Moreyra 522",
                    "Lima", "013122222",
                    "20100105939", "Carlos Ramos"
            );

            String numeroCuenta = "CT-" + dni;
            Cuenta nuevaCuenta = new Cuenta(
                    numeroCuenta,
                    personaBD,
                    banco,
                    tipo,
                    monto,
                    numCuotas,
                    fechaInicioSql,
                    fechaFinSql
            );

            if (cuentasSql.insertar(nuevaCuenta)) {
                // Registrar un préstamo asociado a la nueva cuenta. Se asigna el
                // prestamista con id 1 por defecto. Los campos de motivo, tasa y
                // ingresos se leen de la interfaz; si no se proporcionan se usan
                // valores predeterminados.
                try {
                    double ingresosMensuales = 0;
                    try {
                        ingresosMensuales = Double.parseDouble(txtIngresos.getText().trim());
                    } catch (Exception ex) {
                        ingresosMensuales = 0;
                    }
                    double tasaAnualPrestamo = 0;
                    try {
                        tasaAnualPrestamo = Double.parseDouble(txtTasa.getText().trim());
                    } catch (Exception ex) {
                        tasaAnualPrestamo = 0;
                    }
                    String motivoPrestamo = txtMotivo.getText().trim();
                    Prestamo pr = new Prestamo();
                    pr.setDni(personaBD.getDni());
                    // El usuario que registra el préstamo es el usuario actualmente autenticado (ADMIN o EMPLEADO)
                    int idUsuario = Login.currentUserId > 0 ? Login.currentUserId : 1;
                    pr.setIdUsuario(idUsuario);
                    pr.setNumeroCuenta(numeroCuenta);
                    pr.setMonto(monto);
                    pr.setNumCuotas(numCuotas);
                    pr.setTasaAnual(tasaAnualPrestamo);
                    pr.setFechaInicio(fechaInicioSql);
                    pr.setFechaFin(fechaFinSql);
                    pr.setEstado("APROBADO");
                    pr.setMotivo(motivoPrestamo);
                    pr.setIngresosMensuales(ingresosMensuales);
                    prestamosSql.insertar(pr);
                } catch (Exception ex) {
                    System.out.println("Error al registrar préstamo: " + ex.getMessage());
                }
                JOptionPane.showMessageDialog(this,
                        "Cuenta y préstamo registrados correctamente en Scotiabank (persona registrada en BD).");
                Listado();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear la cuenta.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique los datos ingresados.");
        }
    }

    private void buscarCuenta() {
        txtS.setText("");
        int dni = leerDNI();
        if (dni <= 0) return;
        Cuenta cuenta = cuentasSql.buscarCuentaDNI(dni);
        if (cuenta != null) {
            txtS.append("N° Cuenta\tDNI\tCliente\tTipo\tMonto\tCuotas\tF.Inicio\tF.Fin\n");
            txtS.append("-------------------------------------------------------------------------\n");
            txtS.append(
                cuenta.getNumeroCuenta() + "\t" +
                cuenta.getPersona().getDni() + "\t" +
                cuenta.getPersona().getNombre() + " " + cuenta.getPersona().getApellido() + "\t" +
                cuenta.getTipoCuenta() + "\tS/ " + cuenta.getMonto() + "\t" +
                cuenta.getNumeroCuotas() + "\t" +
                (cuenta.getFechaInicio() != null ? cuenta.getFechaInicio().toString() : "-") + "\t" +
                (cuenta.getFechaFin() != null ? cuenta.getFechaFin().toString() : "-") + "\n"
            );
        } else {
            JOptionPane.showMessageDialog(this, "No existe la cuenta para ese DNI.");
        }
    }

    private void eliminarCuenta() {
        int dni = leerDNI();
        if (dni <= 0) return;
        Cuenta c = cuentasSql.buscarCuentaDNI(dni);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "No existe la cuenta para ese DNI.");
            return;
        }
        if (cuentasSql.eliminar(c.getNumeroCuenta())) {
            JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.");
            Listado();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la cuenta.");
        }
    }

    private void modificarCuenta() {
        try {
            int dni = leerDNI();
            if (dni <= 0) return;
            Persona p = cuentasSql.buscarPersonaDNI(dni);
            Cuenta c = cuentasSql.buscarCuentaDNI(dni);
            if (p == null || c == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró persona/cuenta para ese DNI.");
                return;
            }
            String nuevoNombre = leerNomApell();
            if (nuevoNombre == null) return;
            p.setNombre(nuevoNombre);
            boolean okPersona = cuentasSql.editarPersona(p);
            if (!okPersona) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo actualizar los datos del cliente.");
                return;
            }
            String nuevoTipo = leerTipo();
            double nuevoMonto = LeerCanti();
            if (nuevoMonto <= 0) {
                JOptionPane.showMessageDialog(this,
                        "La cantidad debe ser mayor que cero.");
                return;
            }
            // Leer nuevas cuotas y fechas
            int numCuotas;
            try {
                numCuotas = Integer.parseInt(txtPlazo.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Plazo inválido.");
                return;
            }
            if (numCuotas <= 0) {
                JOptionPane.showMessageDialog(this, "El plazo debe ser mayor que cero.");
                return;
            }
            String fechaIniStr = txtFechaInicio.getText().trim();
            if (fechaIniStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la fecha de inicio de pago.");
                return;
            }
            java.sql.Date fechaIniSql;
            java.sql.Date fechaFinSql;
            try {
                java.time.LocalDate fi = java.time.LocalDate.parse(fechaIniStr);
                fechaIniSql = java.sql.Date.valueOf(fi);
                java.time.LocalDate ff = fi.plusMonths(numCuotas);
                fechaFinSql = java.sql.Date.valueOf(ff);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha de inicio inválido (YYYY-MM-DD).");
                return;
            }
            c.setTipoCuenta(nuevoTipo);
            c.setMonto(nuevoMonto);
            c.setNumeroCuotas(numCuotas);
            c.setFechaInicio(fechaIniSql);
            c.setFechaFin(fechaFinSql);
            boolean okCuenta = cuentasSql.modificar(c);
            if (!okCuenta) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo actualizar la cuenta en la BD.");
                return;
            }
            JOptionPane.showMessageDialog(this, "Datos modificados correctamente.");
            Listado();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al modificar: " + ex.getMessage());
        }
    }


    private void evaluarPrestamo() {
        try {
            int dni = leerDNI();
            if (dni <= 0) return;
            // La evaluación puede realizarse sin que exista una cuenta previa.
            double montoSolicitado = LeerCanti();
            if (montoSolicitado <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor que cero.");
                return;
            }
            int plazo = 0;
            try {
                plazo = Integer.parseInt(txtPlazo.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Plazo inválido.");
                return;
            }
            if (plazo <= 0) {
                JOptionPane.showMessageDialog(this, "El plazo debe ser mayor que cero.");
                return;
            }
            double ingresosMensuales = 0;
            try {
                ingresosMensuales = Double.parseDouble(txtIngresos.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para los ingresos mensuales.");
                return;
            }
            if (ingresosMensuales <= 0) {
                JOptionPane.showMessageDialog(this, "Los ingresos mensuales deben ser mayores que cero.");
                return;
            }
            double tasaAnual = 0;
            try {
                tasaAnual = Double.parseDouble(txtTasa.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese una tasa anual válida.");
                return;
            }
            // Leer fecha de inicio de pago y calcular fecha final
            String fechaInicioStr = txtFechaInicio.getText().trim();
            if (fechaInicioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la fecha de inicio de pago para evaluar.");
                return;
            }
            java.sql.Date fechaInicioSql;
            java.sql.Date fechaFinSql;
            try {
                java.time.LocalDate inicioLocal = java.time.LocalDate.parse(fechaInicioStr);
                fechaInicioSql = java.sql.Date.valueOf(inicioLocal);
                java.time.LocalDate finLocal = inicioLocal.plusMonths(plazo);
                fechaFinSql = java.sql.Date.valueOf(finLocal);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha de inicio inválido (YYYY-MM-DD).");
                return;
            }
            double cuotaSimple = montoSolicitado / plazo;
            double capacidadMensual = ingresosMensuales * 0.35;
            txtS.setText("");
            txtS.append("--- RESULTADO DE EVALUACIÓN ---\n");
            txtS.append("Monto Solicitado: S/ " + String.format("%.2f", montoSolicitado) + "\n");
            txtS.append("Plazo (cuotas): " + plazo + " meses\n");
            txtS.append("Ingresos Mensuales: S/ " + String.format("%.2f", ingresosMensuales) + "\n");
            txtS.append("35% de Ingresos Mensuales: S/ " + String.format("%.2f", capacidadMensual) + "\n");
            txtS.append("Fecha inicio de pago: " + fechaInicioSql + "\n");
            txtS.append("Fecha final de pago: " + fechaFinSql + "\n");
            txtS.append("Cuota Mensual (sin intereses): S/ " + String.format("%.2f", cuotaSimple) + "\n\n");
            if (capacidadMensual >= cuotaSimple) {
                txtS.append("RESULTADO: APTO Y APROBADO\n");
                txtS.append("El cliente tiene la capacidad para pagar la cuota mensual.\n\n");
                double tasaMensual = tasaAnual / 12 / 100;
                double cuota = (montoSolicitado * tasaMensual) /
                        (1 - Math.pow(1 + tasaMensual, -plazo));
                double totalPagar = cuota * plazo;
                double interesesTotales = totalPagar - montoSolicitado;
                txtS.append("--- SIMULACIÓN DE PAGO ---\n");
                txtS.append("Tasa Anual: " + tasaAnual + "%\n");
                txtS.append("Cuota Mensual (con intereses): S/ " + String.format("%.2f", cuota) + "\n");
                txtS.append("Total a pagar: S/ " + String.format("%.2f", totalPagar) + "\n");
                txtS.append("Intereses Totales: S/ " + String.format("%.2f", interesesTotales) + "\n");
            } else {
                txtS.append("RESULTADO: NO APTO\n");
                txtS.append("Motivo: Excede su capacidad de crédito.\n");
                double maximoPrestamo = capacidadMensual * plazo;
                txtS.append("Crédito máximo a ofrecer: S/ " + String.format("%.2f", maximoPrestamo) + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en la evaluación: " + ex.getMessage());
        }
    }

    // Métodos para la gestión de usuarios
    private void agregarUsuario() {
        String usuario = txtEmpUsuario.getText().trim();
        String clave = new String(txtEmpClave.getPassword());
        String rol = cbEmpRol.getSelectedItem().toString();
        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos para agregar un usuario.");
            return;
        }
        // Determinar el id del turno a asignar. Si el rol es ADMIN se usa null.
        Integer idTurno = null;
        if ("EMPLEADO".equalsIgnoreCase(rol)) {
            Object selected = cbEmpHorario.getSelectedItem();
            if (selected instanceof Horario) {
                idTurno = ((Horario) selected).getIdTurno();
            }
        }
        boolean ok = usuariosSql.insertarUsuario(usuario, clave, rol, idTurno);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Usuario agregado correctamente.");
            txtEmpUsuario.setText("");
            txtEmpClave.setText("");
            listarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el usuario. Verifique si ya existe.");
        }
    }

    private void eliminarUsuario() {
        String usuario = txtEmpUsuario.getText().trim();
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el usuario a eliminar.");
            return;
        }
        if (usuario.equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(this, "No es posible eliminar la cuenta principal de administrador.");
            return;
        }
        boolean ok = usuariosSql.eliminarUsuario(usuario);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
            txtEmpUsuario.setText("");
            txtEmpClave.setText("");
            listarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario. Verifique el nombre.");
        }
    }

    private void listarUsuarios() {
        txtEmpListado.setText("");
        // Cabecera: ID, usuario, clave, rol, turno, días y horario
        txtEmpListado.append("ID\tUSUARIO\tCLAVE\tROL\tTURNO\tDÍAS\tHORARIO\n");
        txtEmpListado.append("------------------------------------------------------------------------------\n");
        for (String[] data : usuariosSql.listar()) {
            // data: [id_usuario, usuario, clave, rol, id_turno, dia_turnos, horario]
            txtEmpListado.append(data[0] + "\t" + data[1] + "\t" + data[2] + "\t" + data[3] + "\t" +
                                data[4] + "\t" + data[5] + "\t" + data[6] + "\n");
        }
    }

    /*
     *  Métodos adicionales para la gestión de préstamos.
     *  Se mantiene la clase PrestamosSql para interactuar con la tabla
     *  `prestamos`.  En versiones anteriores existía una clase
     *  PrestamistasSql que gestionaba a los asesores de crédito de forma
     *  independiente, pero ahora los prestamistas están unificados con
     *  la tabla `usuarios` y ya no se usa una clase separada.
     */


    // Lista todos los préstamos y los muestra en el área de texto principal
    private void listarPrestamos() {
        java.util.ArrayList<Prestamo> lista = prestamosSql.listar();
        txtS.setText("");
        // Cabecera con usuario que realizó el préstamo
        txtS.append("ID\tDNI Cliente\tUsuario\tCuenta\tMonto\tCuotas\tTasa\tF.Inicio\tF.Fin\tEstado\n");
        txtS.append("--------------------------------------------------------------------------------------------------------------\n");
        for (Prestamo pr : lista) {
            txtS.append(pr.getIdPrestamo() + "\t" + pr.getDni() + "\t" + pr.getNombreUsuario() + "\t" +
                    pr.getNumeroCuenta() + "\t" +
                    String.format("%.2f", pr.getMonto()) + "\t" + pr.getNumCuotas() + "\t" +
                    String.format("%.2f", pr.getTasaAnual()) + "\t" +
                    (pr.getFechaInicio() != null ? pr.getFechaInicio().toString() : "-") + "\t" +
                    (pr.getFechaFin() != null ? pr.getFechaFin().toString() : "-") + "\t" +
                    pr.getEstado() + "\n");
        }
    }
}