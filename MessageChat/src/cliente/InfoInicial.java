package cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import servidor.EntradaDatos;

public class InfoInicial extends JFrame implements ActionListener {
	
//	 ________________________________
//__/VARIABLES PARA GUARDAR LOS DATOS
	public static String nombreUsuario;
	public static int puertoEntrada;
//	 ____________________________________________________
//__/VARIABLES PARA LA CONSTRUCION DE LA INTERFAZ GRAFICA
	public static JLabel etiquetaImagen, etiquetaNombre, etiquetaPuertoEntrada;
	public static JTextField campoNombre, campoPuertoEntrada;
	public static JButton botonAceptar;

//	 ____________________________________
//__/CONSTRUCTOR PARA LA INTERFAZ GRAFICA
	public InfoInicial() {
		setLayout(null);
		setTitle("Sign in");
		getContentPane().setBackground(new Color(0, 148, 200));
		
		ImageIcon imagen = new ImageIcon("Imagenes/IconoMensajeria.png");
		etiquetaImagen = new JLabel(imagen);
		etiquetaImagen.setBounds(65,0,115,115);
		add(etiquetaImagen);
		
		etiquetaNombre = new JLabel("Nombre:");
		etiquetaNombre.setBounds(25,120,100,30);
		etiquetaNombre.setFont(new Font("Chalkduster", 1, 18));
		etiquetaNombre.setForeground(Color.WHITE);
		add(etiquetaNombre);
		
		etiquetaPuertoEntrada = new JLabel("Puerto de entrada:");
		etiquetaPuertoEntrada.setBounds(25,180,250,30);
		etiquetaPuertoEntrada.setFont(new Font("Chalkduster", 1, 18));
		etiquetaPuertoEntrada.setForeground(Color.WHITE);
		add(etiquetaPuertoEntrada);
		
		campoNombre = new JTextField();
		campoNombre.setBounds(22,145,200,30);
		campoNombre.setFont(new Font(null, 0, 16));
		campoNombre.setBackground(new Color(236, 252, 255));
		add(campoNombre);
		
		campoPuertoEntrada = new JTextField();
		campoPuertoEntrada.setBounds(22,205,85,30);
		campoPuertoEntrada.setFont(new Font(null, 0, 16));
		campoPuertoEntrada.setBackground(new Color(236, 252, 255));
		add(campoPuertoEntrada);
		
		botonAceptar = new JButton("✔");
		botonAceptar.setBounds(175,220,50,40);
		botonAceptar.setFont(new Font(null, 1, 20));
		botonAceptar.addActionListener(this);
		botonAceptar.setEnabled(true);
		add(botonAceptar);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
//	 ______________________________________
//__/ACTIONES AL PRESIONAR EL BOTON ACEPTAR
	
	/*
	 * AL presionar el JButton: botonAceptar, se comprobara que los datos digitados sean validos, 
	 * en dicho caso, se ejecutara un Thread para recibir los mensajes entrantes y se pasara a la ventana de Chat
	 * 
	 * */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonAceptar) {
			nombreUsuario = campoNombre.getText().trim();
			if (nombreUsuario.equals("")) {
				campoNombre.setBackground(Color.PINK);
				JOptionPane.showMessageDialog(null, "NOMBRE INVALIDO", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
			else {
				campoNombre.setBackground(Color.WHITE);
				
				try {
					puertoEntrada = Integer.parseInt(campoPuertoEntrada.getText());
					campoPuertoEntrada.setBackground(Color.WHITE);
					
					Thread hilo = new EntradaDatos("Entrada de datos");
					hilo.start();

					Chat ventanaChat = new Chat();
					ventanaChat.setBounds(0,0,600,800);
					ventanaChat.setVisible(true);
					ventanaChat.setLocationRelativeTo(null);
					ventanaChat.setResizable(false);
					this.setVisible(false);
				}
				
				catch(Exception exc) {
					campoPuertoEntrada.setBackground(Color.PINK);
					JOptionPane.showMessageDialog(null, "PUERTO INVALIDO", "ERROR", JOptionPane.WARNING_MESSAGE);
				}	
			}
		}
	}
	
//	 ____
//__/MAIN
	public static void main (String[] args) {
		InfoInicial ventanaInfo = new InfoInicial();
		ventanaInfo.setBounds(0,0,245,300);
		ventanaInfo.setVisible(true);
		ventanaInfo.setLocationRelativeTo(null);
		ventanaInfo.setResizable(false);
	}
}