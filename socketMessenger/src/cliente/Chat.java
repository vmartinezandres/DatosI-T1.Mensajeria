package cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chat extends JFrame implements ActionListener {
	
//	 ____________________________________________________
//__/VARIABLES PARA LA CONSTRUCION DE LA INTERFAZ GRAFICA
	public static JTextArea areaTexto;
	public static JScrollPane barra;
	public static JButton botonEnviar;
	public static JTextField campoTexto;
	
//	 ________________________________________________
//__/VARIABLES PARA LA VISUALIZACION DE LA MENSAJERIA
	public static String miMensaje;
	public static String conversacionGuardada;
	
//	 _______________________________
//__/VARIABLES INTERNAS DEL PROGRAMA
	public static String nombreUsuario = InfoInicial.nombreUsuario;
	public static int puertoSalida = InfoInicial.puertoSalida;
	public static String ip = "127.0.0.1";
	public static OutputStreamWriter salida;
	
//	 ____________________________________
//__/CONSTRUCTOR PARA LA INTERFAZ GRAFICA		
	public Chat() {
		setLayout(null);
		setTitle("Chat de " + nombreUsuario);
		
		botonEnviar = new JButton("ENVIAR");
		botonEnviar.setBounds(500,720,80,50);
		botonEnviar.addActionListener(this);
		botonEnviar.setEnabled(true);
		add(botonEnviar);
		
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		barra = new JScrollPane(areaTexto);
		barra.setBounds(140,40,435,650);
		add(barra);
		
		campoTexto = new JTextField();
		campoTexto.setBounds(100,726,400,35);
		add(campoTexto);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	 _____________________________________
//__/ACTIONES AL PRESIONAR EL BOTON ENVIAR
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonEnviar) {
			miMensaje = campoTexto.getText().trim();
			
			if (miMensaje.equals("")) {
				campoTexto.setBackground(Color.PINK);
			}
			else {
				campoTexto.setBackground(Color.WHITE);
				campoTexto.setText("");
				try {
//					 ________________________________________
//__________________/SE ESTABLECE CONEXION CON LA IP Y PUERTO		
					Socket conexion = new Socket(ip, puertoSalida);
							
//					 _______________________________
//__________________/SE MANDA UN MENSAJE AL SERVIDOR						
					salida = new OutputStreamWriter(conexion.getOutputStream());
					salida.write(miMensaje);
					salida.flush();
						
					conversacionGuardada = areaTexto.getText();
					areaTexto.setText(conversacionGuardada + "\nYo: " + miMensaje);
					
					conexion.close();	
				} 
				catch (IOException exception) {
					JOptionPane.showMessageDialog(null, "ERROR: NO SE PUDO CONECTAR A NINGUN CHAT");
				}
			}
		}
	}
	
	public static void main(String args[]) {
		Chat ventanaChat = new Chat();
		ventanaChat.setBounds(0,0,600,800);
		ventanaChat.setVisible(true);
		ventanaChat.setLocationRelativeTo(null);
		ventanaChat.setResizable(false);
	}
}