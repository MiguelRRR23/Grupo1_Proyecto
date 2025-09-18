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

public class V1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtNom;
	private JTextField txtDni;
	private JTextField txtTipo;
	private JTextField txtCanti;
	private JScrollPane scrollPane;
	private JTextArea txtS;
	private JButton btnNewButton;

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
		
		txtTipo = new JTextField();
		txtTipo.setBounds(108, 122, 86, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
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

	}
	
	int leerDNI() {
		return Integer.parseInt(txtDni.getText());
	}
	String leerNomApell() {
		return txtNom.getText();
	}
	String leerTipo() {
		return txtTipo.getText();
	}
	double LeerCanti() {
		return Double.parseDouble(txtCanti.getText());
	}
	void Imprimir(String s) {
		txtS.append(s+"\n");
	}
	
	OPERAC ae=new OPERAC();
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	void Listado() {
		Imprimir("DNI\t NOMBRE Y APELLIDO\t TIPO DE CUENTA\t CANTIDAD");
		for(int i=0; i<ae.Tamaño(); i++) {
			Imprimir(""+ae.Obtener(i).getDni()+"\t"+ae.Obtener(i).getNom()+"\t\t"+ae.Obtener(i).getTipo()+"\t\t"+ae.Obtener(i).getCanti());
		}	
	}

	public void actionPerformed(ActionEvent e) {
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
		Banco es=ae.Buscar(leerDNI());
		if(es==null) {
			Banco e1=new Banco(leerDNI(), leerNomApell(),leerTipo(),LeerCanti());
			ae.Adicionar(e1);
		}
		else JOptionPane.showMessageDialog(this, "Este registro ya existe. Ingrese uno nuevo");
	}
	protected void do_btnNewButton_2_actionPerformed(ActionEvent e) {
		Banco es=ae.Buscar(leerDNI());
		if(es!=null) {
			Imprimir("DNI\t NOMBRE Y APELLIDO\t TIPO DE CUENTA\t CANTIDAD");
			Imprimir(""+es.getDni()+"\t"+es.getNom()+"\t\t"+es.getTipo()+"\t\t"+es.getCanti());
		}
	else JOptionPane.showMessageDialog(this, "No extiste este registro. Ingrese de nuevo");
		
	}
}
