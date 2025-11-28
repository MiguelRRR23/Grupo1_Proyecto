package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Arreglo.CuentasSql;
import Clase.Banco;
import Clase.Cuenta;
import Clase.Persona;

public class V1 extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JTextField txtNom;
    private JTextField txtDni;
    private JTextField txtCanti;
    private JScrollPane scrollPane;
    private JTextArea txtS;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JComboBox<String> comboBox;

    private JLabel lblNewLabel_4;
    private JTextField txtPlazo;
    private JLabel lblNewLabel_5;
    private JTextField textField;
    private JButton BtnSimular;
    private JButton btnEvaluar;

    private CuentasSql cuentasSql = new CuentasSql();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    V1 frame = new V1();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public V1() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(V1.class.getResource("/Imagen/Banco.png")));
        setTitle("BANCO_PRESTAMOS Y CREDITOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 916, 533);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

       
        JLabel lblTitulo = new JLabel("GESTIÓN DE CUENTAS Y PRÉSTAMOS - SCOTIABANK");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setBounds(160, 5, 600, 20);
        contentPane.add(lblTitulo);

      
        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(null);
        panelCliente.setOpaque(false);
        panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));
        panelCliente.setBounds(10, 30, 280, 120);
        contentPane.add(panelCliente);

        lblNewLabel = new JLabel("Nombre y Apellido:");
        lblNewLabel.setBounds(10, 25, 130, 20);
        panelCliente.add(lblNewLabel);

        txtNom = new JTextField();
        txtNom.setBounds(140, 25, 120, 20);
        panelCliente.add(txtNom);

        lblNewLabel_1 = new JLabel("DNI:");
        lblNewLabel_1.setBounds(10, 55, 130, 20);
        panelCliente.add(lblNewLabel_1);

        txtDni = new JTextField();
        txtDni.setBounds(140, 55, 120, 20);
        panelCliente.add(txtDni);

        lblNewLabel_2 = new JLabel("Tipo de cuenta:");
        lblNewLabel_2.setBounds(10, 85, 130, 20);
        panelCliente.add(lblNewLabel_2);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Corriente", "Credito", "Ahorro"}));
        comboBox.setBounds(140, 85, 120, 22);
        panelCliente.add(comboBox);

       
        JPanel panelPrestamo = new JPanel();
        panelPrestamo.setLayout(null);
        panelPrestamo.setOpaque(false);
        panelPrestamo.setBorder(BorderFactory.createTitledBorder("Datos del préstamo"));
        panelPrestamo.setBounds(310, 30, 280, 120);
        contentPane.add(panelPrestamo);

        lblNewLabel_3 = new JLabel("Cantidad:");
        lblNewLabel_3.setBounds(10, 25, 100, 20);
        panelPrestamo.add(lblNewLabel_3);

        txtCanti = new JTextField();
        txtCanti.setBounds(140, 25, 120, 20);
        panelPrestamo.add(txtCanti);

        lblNewLabel_4 = new JLabel("Plazo (meses):");
        lblNewLabel_4.setBounds(10, 55, 100, 20);
        panelPrestamo.add(lblNewLabel_4);

        txtPlazo = new JTextField();
        txtPlazo.setBounds(140, 55, 120, 20);
        panelPrestamo.add(txtPlazo);

        lblNewLabel_5 = new JLabel("Tasa anual (%):");
        lblNewLabel_5.setBounds(10, 85, 100, 20);
        panelPrestamo.add(lblNewLabel_5);

        textField = new JTextField();
        textField.setBounds(140, 85, 120, 20);
        panelPrestamo.add(textField);

      
        int yBotones = 170;
        int ancho = 110;
        int x = 20;
        int esp = 10;

        btnNewButton = new JButton("REPORTAR");
        btnNewButton.setBounds(x, yBotones, ancho, 25);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(this);

        btnNewButton_1 = new JButton("ADICIONAR");
        btnNewButton_1.setBounds(x += ancho + esp, yBotones, ancho, 25);
        contentPane.add(btnNewButton_1);
        btnNewButton_1.addActionListener(this);

        btnNewButton_2 = new JButton("BUSCAR");
        btnNewButton_2.setBounds(x += ancho + esp, yBotones, ancho, 25);
        contentPane.add(btnNewButton_2);
        btnNewButton_2.addActionListener(this);

        btnNewButton_3 = new JButton("ELIMINAR");
        btnNewButton_3.setBounds(x += ancho + esp, yBotones, ancho, 25);
        contentPane.add(btnNewButton_3);
        btnNewButton_3.addActionListener(this);

        btnNewButton_4 = new JButton("MODIFICAR");
        btnNewButton_4.setBounds(x += ancho + esp, yBotones, ancho, 25);
        contentPane.add(btnNewButton_4);
        btnNewButton_4.addActionListener(this);

        BtnSimular = new JButton("SIMULAR");
        BtnSimular.setBounds(x += ancho + esp, yBotones, ancho, 25);
        contentPane.add(BtnSimular);
        BtnSimular.addActionListener(this);

        btnEvaluar = new JButton("EVALUAR PRÉSTAMO");
        btnEvaluar.setBounds(x += ancho + esp, yBotones, 150, 25);
        contentPane.add(btnEvaluar);
        btnEvaluar.addActionListener(this);

        Font fBoton = new Font("Segoe UI", Font.PLAIN, 12);
        for (JButton b : new JButton[]{btnNewButton, btnNewButton_1, btnNewButton_2,
                btnNewButton_3, btnNewButton_4, BtnSimular, btnEvaluar}) {
            b.setFont(fBoton);
        }

        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(24, 210, 851, 270);
        contentPane.add(scrollPane);

        txtS = new JTextArea();
        txtS.setEditable(false);
        txtS.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtS.setLineWrap(false);
        scrollPane.setViewportView(txtS);
    }

  

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
        Imprimir("N° CUENTA\tDNI\tNOMBRE CLIENTE\tBANCO\tTIPO CUENTA\tMONTO");
        Imprimir("--------------------------------------------------------------------------");

        for (Cuenta c : cuentasSql.listar()) {
            Imprimir(
                c.getNumeroCuenta() + "\t" +
                c.getPersona().getDni() + "\t" +
                c.getPersona().getNombre() + " " + c.getPersona().getApellido() + "\t" +
                c.getBanco().getNombreBanco() + "\t" +
                c.getTipoCuenta() + "\t" +
                "S/ " + String.format("%.2f", c.getMonto())
            );
        }
    }

    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEvaluar) {
            do_btnEvaluar_actionPerformed(e);
        } else if (e.getSource() == BtnSimular) {
            do_btnSimular_actionPerformed(e);
        } else if (e.getSource() == btnNewButton_4) {
            do_btnNewButton_4_actionPerformed(e);
        } else if (e.getSource() == btnNewButton_3) {
            do_btnNewButton_3_actionPerformed(e);
        } else if (e.getSource() == btnNewButton_2) {
            do_btnNewButton_2_actionPerformed(e);
        } else if (e.getSource() == btnNewButton_1) {
            do_btnNewButton_1_actionPerformed(e);
        } else if (e.getSource() == btnNewButton) {
            do_btnNewButton_actionPerformed(e);
        }
    }

    // BOTÓN REPORTAR 
    protected void do_btnNewButton_actionPerformed(ActionEvent e) {
        Listado();
    }

    // BOTÓN ADICIONAR 
    protected void do_btnNewButton_1_actionPerformed(ActionEvent e) {
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

            Persona personaBD = cuentasSql.buscarPersonaDNI(dni);

            if (personaBD == null) {
                String nombre = nombreCompleto;
                String apellido = "";

                Persona nuevaPersona = new Persona(
                        dni, nombre, apellido,
                        "Sin dirección", "Sin teléfono", "Sin correo"
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
            Cuenta nuevaCuenta = new Cuenta(numeroCuenta, personaBD, banco, tipo, monto);

            if (cuentasSql.insertar(nuevaCuenta)) {
                JOptionPane.showMessageDialog(this,
                        "Cuenta creada correctamente en Scotiabank (persona registrada en BD).");
                Listado();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear la cuenta.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique los datos ingresados.");
        }
    }

    // BOTÓN BUSCAR 
    protected void do_btnNewButton_2_actionPerformed(ActionEvent e) {
        txtS.setText("");

        int dni = leerDNI();
        if (dni <= 0) return;

        Cuenta cuenta = cuentasSql.buscarCuentaDNI(dni);

        if (cuenta != null) {
            txtS.append("N° Cuenta\tDNI\tCliente\tTipo\tMonto\n");
            txtS.append("--------------------------------------------------\n");
            txtS.append(cuenta.getNumeroCuenta() + "\t" +
                        cuenta.getPersona().getDni() + "\t" +
                        cuenta.getPersona().getNombre() + " " + cuenta.getPersona().getApellido() + "\t" +
                        cuenta.getTipoCuenta() + "\tS/ " + cuenta.getMonto() + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "No existe la cuenta para ese DNI.");
        }
    }

    //  BOTÓN ELIMINAR
    protected void do_btnNewButton_3_actionPerformed(ActionEvent e) {
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

    //  BOTÓN MODIFICAR 
    protected void do_btnNewButton_4_actionPerformed(ActionEvent e) {
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

            c.setTipoCuenta(nuevoTipo);
            c.setMonto(nuevoMonto);

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

    //  BOTÓN SIMULAR 
    protected void do_btnSimular_actionPerformed(ActionEvent e) {
        try {
            double monto = Double.parseDouble(txtCanti.getText());
            int plazo = Integer.parseInt(txtPlazo.getText());
            double tasaAnual = Double.parseDouble(textField.getText());

            double tasaMensual = tasaAnual / 12 / 100;
            double cuota = (monto * tasaMensual) /
                           (1 - Math.pow(1 + tasaMensual, -plazo));

            txtS.setText("");
            txtS.append("Monto Solicitado: S/ " + monto + "\n");
            txtS.append("Plazo: " + plazo + " meses\n");
            txtS.append("Tasa Anual: " + tasaAnual + "%\n");
            txtS.append("Cuota Mensual: S/ " + String.format("%.2f", cuota) + "\n");
            txtS.append("Total a pagar: S/ " + String.format("%.2f", (cuota * plazo)) + "\n");
            txtS.append("Intereses Totales: S/ " + String.format("%.2f", (cuota * plazo) - monto) + "\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Verifique los campos de simulación.");
        }
    }

    // LÓGICA DE EVALUACIÓN DE PRÉSTAMO 
    private String evaluarPrestamo(Cuenta cuenta,
                                   double montoSolicitado,
                                   int plazo,
                                   double tasaAnual) {

        String tipo = cuenta.getTipoCuenta();
        if (!(tipo.equalsIgnoreCase("Corriente") ||
              tipo.equalsIgnoreCase("Ahorro"))) {
            return "Rechazado: el tipo de cuenta no aplica para préstamos.";
        }

        double saldo = cuenta.getMonto();
        double maxPorSaldo = saldo * 3;

        if (montoSolicitado > maxPorSaldo) {
            return "Rechazado: el monto solicitado supera el máximo permitido según su saldo (máx S/ "
                    + String.format("%.2f", maxPorSaldo) + ").";
        }

        if (montoSolicitado > 50000) {
            return "Rechazado: el monto solicitado supera el límite de S/ 50,000.";
        }

        if (plazo < 6 || plazo > 48) {
            return "Rechazado: el plazo debe estar entre 6 y 48 meses.";
        }

        if (tasaAnual < 5 || tasaAnual > 40) {
            return "Rechazado: la tasa anual debe estar entre 5% y 40%.";
        }

        return "Aprobado: el cliente califica para el préstamo.";
    }

    //BOTÓN EVALUAR PRÉSTAMO
    protected void do_btnEvaluar_actionPerformed(ActionEvent e) {
        try {
            int dni = leerDNI();
            if (dni <= 0) return;

            Cuenta cuenta = cuentasSql.buscarCuentaDNI(dni);
            if (cuenta == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró una cuenta asociada a ese DNI.");
                return;
            }

            double montoSolicitado = LeerCanti();
            int plazo = Integer.parseInt(txtPlazo.getText().trim());
            double tasaAnual = Double.parseDouble(textField.getText().trim());

            if (montoSolicitado <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor que cero.");
                return;
            }

            String resultado = evaluarPrestamo(cuenta, montoSolicitado, plazo, tasaAnual);
            JOptionPane.showMessageDialog(this, resultado);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos para la evaluación.");
        }
    }
}
