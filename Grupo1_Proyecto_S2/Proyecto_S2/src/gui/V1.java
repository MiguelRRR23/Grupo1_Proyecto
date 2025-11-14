package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Arreglo.OPERAC;
import Clase.Banco;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

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
	private JComboBox comboBox;


	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public V1() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(V1.class.getResource("/Imagen/Banco.png")));
		setTitle("BANCO_PRESTAMOS Y CREDITOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nombre y Apellido:");
		lblNewLabel.setBounds(10, 32, 117, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(10, 58, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Tipo de cuenta:");
		lblNewLabel_2.setBounds(10, 87, 103, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(283, 32, 88, 14);
		contentPane.add(lblNewLabel_3);
		
		txtNom = new JTextField();
		txtNom.setBounds(119, 29, 86, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(119, 57, 86, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtCanti = new JTextField();
		txtCanti.setBounds(342, 29, 86, 20);
		contentPane.add(txtCanti);
		txtCanti.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 282, 851, 201);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		btnNewButton = new JButton("REPORTAR");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(24, 234, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("ADICIONAR");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(119, 234, 103, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("BUSCAR");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(232, 234, 89, 23);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("ELIMINAR");
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setBounds(331, 234, 89, 23);
		contentPane.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("MODIFICAR");
		btnNewButton_4.addActionListener(this);
		btnNewButton_4.setBounds(430, 234, 103, 23);
		contentPane.add(btnNewButton_4);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Corriente", "Credito", "Ahorro"}));
		comboBox.setBounds(109, 83, 115, 22);
		contentPane.add(comboBox);
		
		lblNewLabel_4 = new JLabel("Plazo");
		lblNewLabel_4.setBounds(286, 58, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtPlazo = new JTextField();
		txtPlazo.setBounds(342, 55, 86, 20);
		contentPane.add(txtPlazo);
		txtPlazo.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Tasa anual");
		lblNewLabel_5.setBounds(270, 87, 78, 14);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(342, 86, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		BtnSimular = new JButton("SIMULAR");
		BtnSimular.addActionListener(this);
		BtnSimular.setBounds(553, 234, 89, 23);
		contentPane.add(BtnSimular);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(655, 234, 89, 23);
		contentPane.add(btnGuardar);

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
		txtS.append(s+"\n");
	}
	
	OPERAC ae=new OPERAC();
	private JLabel lblNewLabel_4;
	private JTextField txtPlazo;
	private JLabel lblNewLabel_5;
	private JTextField textField;
	private JButton BtnSimular;
	private JButton btnGuardar;
	
	void Listado() {
		txtS.setText(""); 
	    Imprimir("N° CUENTA\tDNI\tNOMBRE CLIENTE\tBANCO\tTIPO CUENTA\tMONTO");
	    Imprimir("--------------------------------------------------------------------------");

	    for (int i = 0; i < ae.Tamaño(); i++) {
	        Clase.Cuenta c = ae.Obtener(i);
	        Imprimir(
	            c.getNumeroCuenta() + "\t" +
	            c.getPersona().getDni() + "\t" +
	            c.getPersona().getNombre() + "\t" +
	            c.getBanco().getNombreBanco() + "\t" +
	            c.getTipoCuenta() + "\t" +
	            "S/ " + String.format("%.2f", c.getMonto())
	        );
	    }	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			do_btnNewButton_6_actionPerformed(e);
		}
		if (e.getSource() == BtnSimular) {
			do_btnNewButton_5_actionPerformed(e);
		}
		if (e.getSource() == btnNewButton_4) {
			do_btnNewButton_4_actionPerformed(e);
		}
		if (e.getSource() == btnNewButton_3) {
			do_btnNewButton_3_actionPerformed(e);
		}
		if (e.getSource() == btnNewButton_2) {
			do_btnNewButton_2_actionPerformed(e);
		}
		if (e.getSource() == btnNewButton_1) {
			do_btnNewButton_1_actionPerformed(e);
		}
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		txtS.setText("");
		Listado();
	}
	
	
	protected void do_btnNewButton_1_actionPerformed(ActionEvent e) {
		try {
	        int dni = leerDNI();
	        if (dni <= 0) {
	            JOptionPane.showMessageDialog(this, "El DNI no puede ser negativo. Ingrese nuevamente el DNI.");
	            return;
	        }

	        String nombre = leerNomApell();
	        if (nombre == null) return;

	        double monto = LeerCanti();
	        if (monto <= 0) {
	            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero.");
	            return;
	        }

	        String tipo = leerTipo();

	       
	        Clase.Persona persona = new Clase.Persona(dni, nombre, "", "Sin dirección", "Sin teléfono", "Sin correo");
	        Clase.Banco banco = new Clase.Banco(1, "Scotiabank Perú S.A.A.", "Av. Canaval y Moreyra 522", "Lima", "013122222", "20100105939", "Carlos Ramos");

	        
	        String numeroCuenta = "CT-" + dni;

	        
	        Clase.Cuenta cuentaExistente = ae.BuscarPorDni(dni);
	        if (cuentaExistente == null) {
	            Clase.Cuenta nuevaCuenta = new Clase.Cuenta(numeroCuenta, persona, banco, tipo, monto);
	            ae.Adicionar(nuevaCuenta);
	            JOptionPane.showMessageDialog(this, "Cuenta creada correctamente en Scotiabank.");
	        } else {
	            JOptionPane.showMessageDialog(this, "Ya existe una cuenta registrada con este DNI.");
	        }

	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Verifique los datos ingresados.");
	    }
	}
	
	
	protected void do_btnNewButton_2_actionPerformed(ActionEvent e) {
		txtS.setText("");

	    Clase.Cuenta cuenta = null;

	    if (!txtDni.getText().trim().isEmpty()) {
	        cuenta = ae.BuscarPorDni(leerDNI());
	    } else if (!txtNom.getText().trim().isEmpty()) {
	        cuenta = ae.BuscarPorNombre(leerNomApell());
	    }

	    if (cuenta != null) {
	        txtS.append("N° Cuenta\tDNI\tCliente\tTipo\tMonto\n");
	        txtS.append("--------------------------------------------------\n");
	        txtS.append(cuenta.getNumeroCuenta() + "\t" +
	                    cuenta.getPersona().getDni() + "\t" +
	                    cuenta.getPersona().getNombre() + "\t" +
	                    cuenta.getTipoCuenta() + "\tS/ " + cuenta.getMonto() + "\n");
	    } else {
	        JOptionPane.showMessageDialog(this, "No existe el registro.");
	    }
		
	}
	

	protected void do_btnNewButton_3_actionPerformed(ActionEvent e) {
		boolean eliminado = false;
	    if (!txtDni.getText().trim().isEmpty()) {
	        Clase.Cuenta c = ae.BuscarPorDni(leerDNI());
	        if (c != null) {
	            ae.Eliminar(c.getNumeroCuenta());
	            eliminado = true;
	        }
	    } else if (!txtNom.getText().trim().isEmpty()) {
	        Clase.Cuenta c = ae.BuscarPorNombre(leerNomApell());
	        if (c != null) {
	            ae.Eliminar(c.getNumeroCuenta());
	            eliminado = true;
	        }
	    }

	    if (eliminado)
	        JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.");
	    else
	        JOptionPane.showMessageDialog(this, "No existe el registro a eliminar.");
	}
	
	
	protected void do_btnNewButton_4_actionPerformed(ActionEvent e) {
		txtS.setText("");
		Clase.Cuenta c = null;

	    if (!txtDni.getText().trim().isEmpty()) {
	        c = ae.BuscarPorDni(leerDNI());
	    } else if (!txtNom.getText().trim().isEmpty()) {
	        c = ae.BuscarPorNombre(leerNomApell());
	    }

	    if (c != null) {
	        c.setTipoCuenta(leerTipo());
	        c.setMonto(LeerCanti());
	        JOptionPane.showMessageDialog(this, "Cuenta modificada correctamente.");
	    } else {
	        JOptionPane.showMessageDialog(this, "No se encontró la cuenta para modificar.");
	    }
	}

	protected void do_btnNewButton_5_actionPerformed(ActionEvent e)   {
		
		try {
	        
	        double monto = Double.parseDouble(txtCanti.getText());
	        int plazo = Integer.parseInt(txtPlazo.getText());
	        double tasaAnual = Double.parseDouble(textField.getText());

	        
	        double tasaMensual = tasaAnual / 12 / 100;

	      
	        double cuota = (monto * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -plazo));

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

	protected void do_btnNewButton_6_actionPerformed(ActionEvent e) {
		 try {
		        ae.GuardarArchivo();
		        JOptionPane.showMessageDialog(this, "Registros guardados correctamente en el archivo.");
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(this, "Error al guardar archivo.");
		    }
	}
	
}
