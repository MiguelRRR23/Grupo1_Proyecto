package Lab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
	private JTextField txtN1;
	private JTextField txtN2;
	private JTextField txtN3;
	private JTextField txtN4;
	private JScrollPane scrollPane;
	private JTextArea txtS;
	private JButton btnP;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nota 1");
		lblNewLabel.setBounds(10, 24, 45, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nota 2");
		lblNewLabel_1.setBounds(10, 47, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Nota 3");
		lblNewLabel_2.setBounds(205, 24, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Nota 4");
		lblNewLabel_3.setBounds(205, 47, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		txtN1 = new JTextField();
		txtN1.setBounds(54, 21, 96, 19);
		contentPane.add(txtN1);
		txtN1.setColumns(10);
		
		txtN2 = new JTextField();
		txtN2.setBounds(54, 44, 96, 19);
		contentPane.add(txtN2);
		txtN2.setColumns(10);
		
		txtN3 = new JTextField();
		txtN3.setBounds(248, 21, 96, 19);
		contentPane.add(txtN3);
		txtN3.setColumns(10);
		
		txtN4 = new JTextField();
		txtN4.setBounds(248, 44, 96, 19);
		contentPane.add(txtN4);
		txtN4.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 416, 123);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		btnP = new JButton("Calcular Promedio");
		btnP.addActionListener(this);
		btnP.setBounds(129, 89, 121, 31);
		contentPane.add(btnP);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnP) {
			do_btnP_actionPerformed(e);
		}
	}
	protected void do_btnP_actionPerformed(ActionEvent e) {
		double n1=Double.parseDouble(txtN1.getText());
		double n2=Double.parseDouble(txtN2.getText());
		double n3=Double.parseDouble(txtN3.getText());
		double n4=Double.parseDouble(txtN4.getText());
		Evaluacion c1= new Evaluacion(n1,n2,n3,n4);
		txtS.setText("");
		txtS.append("El preomdio es : "+c1.calcularPromedio());
	}
}
