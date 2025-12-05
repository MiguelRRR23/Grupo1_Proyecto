package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;

/**
 * Ventana informativa del programa. Muestra detalles del proyecto y los
 * integrantes del equipo. Permite seleccionar entre cinco espacios
 * distintos para visualizar la descripción de cada miembro junto a un
 * espacio destinado para su fotografía (actualmente un marcador de
 * posición).
 */
public class InfoPrograma extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> cbIntegrantes;
    private JTextArea txtDescripcion;
    private JLabel lblFoto;

    public InfoPrograma() {
        setTitle("Información del Programa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Aumentar el alto para dar más espacio a la descripción y al panel de foto
        setBounds(100, 100, 620, 500);
        setLocationRelativeTo(null);

        // Barra de menú para cerrar sesión
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        // Definir un color de fondo suave para la barra de menú de información
        // Tono rojo suave #ed3729 para el fondo
        Color bgColor = new Color(237, 55, 41);
        menuBar.setBackground(bgColor);
        javax.swing.JMenu menuUsuario = new javax.swing.JMenu("Cuenta");
        menuUsuario.setForeground(Color.WHITE);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Cerrar");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        menuUsuario.add(mntmNewMenuItem);
        menuBar.add(menuUsuario);
        setJMenuBar(menuBar);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(bgColor);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Proyecto: Gestión de Cuentas y Préstamos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setBounds(48, 59, 500, 25);
        contentPane.add(lblTitulo);

        JLabel lblSub = new JLabel("Integrantes del Equipo");
        lblSub.setForeground(Color.WHITE);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setBounds(48, 95, 200, 20);
        contentPane.add(lblSub);

        cbIntegrantes = new JComboBox<>(new String[]{"Miembro 1", "Miembro 2", "Miembro 3", "Miembro 4", "Miembro 5"});
        cbIntegrantes.setModel(new DefaultComboBoxModel(new String[] {"DAVID JEFERSON CANAZA VIVANCO", "MAX SEBASTIAN GARRIDO CASTILLO", "CHRISTOPHER ANDRE MANRIQUE ORTIZ", "MIGUEL ALEJANDRO REBONATTO RAMOS DE ROSAS", "GUSTAVO ALONSO TRUJILLO CASANA"}));
        cbIntegrantes.setBounds(48, 121, 230, 22);
        cbIntegrantes.addActionListener(this);
        contentPane.add(cbIntegrantes);

        // Área de descripción
        JScrollPane scrollDesc = new JScrollPane();
        scrollDesc.setBounds(48, 156, 300, 250);
        contentPane.add(scrollDesc);
        txtDescripcion = new JTextArea();
        txtDescripcion.setEditable(false);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBackground(new Color(255, 255, 255));
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        scrollDesc.setViewportView(txtDescripcion);

        // Fotografía (placeholder)
        lblFoto = new JLabel();
        lblFoto.setOpaque(true);
        lblFoto.setBackground(Color.LIGHT_GRAY);
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        lblFoto.setBounds(386, 95, 174, 244);
        contentPane.add(lblFoto);
        
        JLabel lblNewLabel = new JLabel("Profesor:");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(378, 350, 180, 30);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("LENIN FROY ARCE HUAMÁN");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(378, 378, 200, 14);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(InfoPrograma.class.getResource("/Imagen/bannerscotia.png")));
        lblNewLabel_2.setBounds(158, 11, 274, 41);
        contentPane.add(lblNewLabel_2);

        // Mensaje inicial
        actualizarInformacion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbIntegrantes) {
            actualizarInformacion();
        }
    }

    /**
     * Actualiza la descripción y la imagen en función del integrante seleccionado.
     * Por el momento se utilizan descripciones y fotografías genéricas.
     */
    private void actualizarInformacion() {
        String seleccionado = cbIntegrantes.getSelectedItem().toString();
        String descripcion;
        ImageIcon foto;

        switch (seleccionado) {

            case "DAVID JEFERSON CANAZA VIVANCO":
                descripcion = "Participó en el desarrollo del sistema, apoyando en la "
                            + "implementación de funciones, pruebas y organización del proyecto.";
                foto = new ImageIcon(getClass().getResource("/Imagen/dav.png"));
                break;

            case "MAX SEBASTIAN GARRIDO CASTILLO":
                descripcion = "Colaboró en la construcción del programa, aportando en "
                            + "la lógica del sistema y la integración con la base de datos.";
                foto = new ImageIcon(getClass().getResource("/Imagen/max.png"));
                break;

            case "CHRISTOPHER ANDRE MANRIQUE ORTIZ":
                descripcion = "Apoyó en el diseño y mejora de interfaces, además de "
                            + "participar en las validaciones y pruebas del sistema.";
                foto = new ImageIcon(getClass().getResource("/Imagen/cris.jpg"));
                break;

            case "MIGUEL ALEJANDRO REBONATTO RAMOS DE ROSAS":
                descripcion = "Contribuyó al análisis del proyecto y a la implementación "
                            + "de funcionalidades dentro de la aplicación.";
                foto = new ImageIcon(getClass().getResource("/Imagen/mig.png"));
                break;

            case "GUSTAVO ALONSO TRUJILLO CASANA":
            default:
                descripcion = "Apoyó en la revisión general del sistema, realizando "
                            + "pruebas y verificaciones del correcto funcionamiento.";
                foto = new ImageIcon(getClass().getResource("/Imagen/gus.png"));
                break;
        }
        txtDescripcion.setText(descripcion);
        if (foto != null) {
            lblFoto.setIcon(new ImageIcon(foto.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        } else {
            lblFoto.setIcon(null);
            lblFoto.setText("Foto");
            lblFoto.setHorizontalAlignment(JLabel.CENTER);
            lblFoto.setVerticalAlignment(JLabel.CENTER);
        }
    }
}