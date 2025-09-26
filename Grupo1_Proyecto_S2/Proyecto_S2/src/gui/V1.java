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
		setTitle("BANCO_PRESTAMOS Y CREDITOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nombre y Apellido:");
		lblNewLabel.setBounds(10, 45, 103, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(10, 81, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Tipo de cuenta:");
		lblNewLabel_2.setBounds(10, 125, 103, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(10, 185, 88, 14);
		contentPane.add(lblNewLabel_3);
		
		txtNom = new JTextField();
		txtNom.setBounds(108, 42, 86, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(108, 78, 86, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtCanti = new JTextField();
		txtCanti.setBounds(108, 182, 86, 20);
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
		btnNewButton_1.setBounds(153, 234, 103, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("BUSCAR");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(294, 234, 89, 23);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("ELIMINAR");
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setBounds(405, 234, 89, 23);
		contentPane.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("MODIFICAR");
		btnNewButton_4.addActionListener(this);
		btnNewButton_4.setBounds(506, 234, 103, 23);
		contentPane.add(btnNewButton_4);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"   Corriente", "    Credito", "    Ahorro"}));
		comboBox.setBounds(107, 121, 115, 22);
		contentPane.add(comboBox);

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
	
	void Listado() {
		Imprimir("DNI\t NOMBRE Y APELLIDO\t TIPO DE CUENTA\t CANTIDAD");
		for(int i=0; i<ae.Tamaño(); i++) {
			Imprimir(""+ae.Obtener(i).getDni()+"\t"+ae.Obtener(i).getNom()+"\t\t"+ae.Obtener(i).getTipo()+"\t\t"+ae.Obtener(i).getCanti());
		}	
	}

	public void actionPerformed(ActionEvent e) {
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
	            JOptionPane.showMessageDialog(this, "El DNI no puede ser negativo.Ingrese nuevamente el DNI.");
	            return;
	        }
	        
	        String nombre = txtNom.getText().trim();
	        String cantidadTxt = txtCanti.getText().trim();
	        if (txtNom.getText().trim().isEmpty() ||  
	            txtCanti.getText().trim().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Complete todos los campos antes de adicionar.");
	            return;
	        }
	        if (nombre.matches("\\d+")) {
	            JOptionPane.showMessageDialog(this, "El campo Nombre y Apellido no puede contener solo números.");
	            return;
	        }
	        if (!cantidadTxt.matches("\\d+(\\.\\d+)?")) { 
	            JOptionPane.showMessageDialog(this, "La cantidad solo puede contener números.");
	            return;
	        }

	        Banco es = ae.Buscar(dni);
	        if (es == null) {
	        	String tipo = comboBox.getSelectedItem().toString();
	        	Banco e1 = new Banco(dni, leerNomApell(), leerTipo(), LeerCanti());
	            ae.Adicionar(e1);
	        } else {
	            JOptionPane.showMessageDialog(this, "Este registro ya existe. Ingrese uno nuevo.");
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "DNI debe ser un número válido.");
	    }
	}
	
	
	protected void do_btnNewButton_2_actionPerformed(ActionEvent e) {
txtS.setText("");
		
		Banco es = null;

        if (!txtDni.getText().isEmpty() && leerDNI() != -1) {
            es = ae.Buscar(leerDNI()); 
        } else if (!leerNomApell().isEmpty()) {
            es = ae.Buscar(leerNomApell()); 
        }
			if(es!=null) {
				Imprimir("DNI\t NOMBRE Y APELLIDO\t TIPO DE CUENTA\t CANTIDAD");
				Imprimir(""+es.getDni()+"\t"+es.getNom()+"\t\t"+es.getTipo()+"\t\t"+es.getCanti());
			}
		else JOptionPane.showMessageDialog(this, "No extiste este registro. Ingrese de nuevo");
		
		
	}
	

	protected void do_btnNewButton_3_actionPerformed(ActionEvent e) {
		boolean eliminado = false;

	    if (!txtDni.getText().isEmpty() && leerDNI() != -1) {
	        ae.Eliminar(leerDNI()); 
	        eliminado = true;
	    } 
	   
	    else if (!leerNomApell().isEmpty()) {
	        ae.Eliminar(leerNomApell()); 
	        eliminado = true;
	    }

	    if (eliminado) {
	        JOptionPane.showMessageDialog(this,"El registro fue eliminado correctamente.");
	    } else {
	        JOptionPane.showMessageDialog(this,"No existe el registro a eliminar.");
	    }
	}
	
	
	protected void do_btnNewButton_4_actionPerformed(ActionEvent e) {
		txtS.setText("");
		 Banco es = null;

		    if (!txtDni.getText().isEmpty() && leerDNI() != -1) {
		        es = ae.Buscar(leerDNI()); 
		    } else if (!leerNomApell().isEmpty()) {
		        es = ae.Buscar(leerNomApell()); 
		    }

		    if (es != null) {
		      
		        es.setNom(leerNomApell());
		        es.setTipo(comboBox.getSelectedItem().toString());
		        es.setCanti(LeerCanti());

		        JOptionPane.showMessageDialog(this, "Registro modificado correctamente.");
		    } else {
		        JOptionPane.showMessageDialog(this, "No existe el registro a modificar.");
		    }
	}
}
