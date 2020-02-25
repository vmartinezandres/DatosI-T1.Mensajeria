package cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UsuarioUno extends JFrame implements ActionListener {
	InfoInicial claseInfo = new InfoInicial();

	
//	 ____________________________________________________
//__/VARIABLES PARA LA CONSTRUCION DE LA INTERFAZ GRAFICA
	private static JTextArea areaTexto;
	private static JScrollPane barra;
	private static JButton botonEnviar;
	private static JTextField campoTexto;
	
//	 ________________________________________________
//__/VARIABLES PARA LA VISUALIZACION DE LA MENSAJERIA
	static String miMensaje;
	static String conversacion;
	static String mensajeEntrada;
	
//	 _______________________________
//__/VARIABLES INTERNAS DEL PROGRAMA
	static int puertoEntrada = 40000;
	static int puertoSalida = 40001;
    String nombrePersona = claseInfo.nombreUsuario;
	static String ip = "127.0.0.1";
	static boolean activo = true;
	static ServerSocket servidor;
	static Socket conexion;
	static BufferedReader entrada;
	static OutputStreamWriter salida;
	
//	 ____________________________________
//__/CONSTRUCTOR PARA LA INTERFAZ GRAFICA		
	public UsuarioUno() {
		setLayout(null);
		setTitle("Messenger 1.0");
		
		botonEnviar = new JButton("ENVIAR");
		botonEnviar.setBounds(500,720,80,50);
		botonEnviar.addActionListener(this);
		botonEnviar.setEnabled(true);
		add(botonEnviar);
		
		areaTexto = new JTextArea();
		areaTexto.setEditable(true);
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
						
					conversacion = areaTexto.getText();
					areaTexto.setText(conversacion + "\n" + nombrePersona + ": " + miMensaje);
					
					conexion.close();	
				} 
				catch (IOException exception) {
					JOptionPane.showMessageDialog(null, "ERROR: NO SE PUDO ESTABLECER CONEXION CON EL SERVIDOR");;
				}
			}
		}
	}
	public static void mensajeEntrante() {
		try {
//			 _____________________________
//__________/SE CREA LA CONEXION AL PUERTO
			servidor = new ServerSocket(puertoEntrada);
			
			while (activo) {
//				 ______________________________________________________________________
//______________/SE ESPERA A QUE OTRO PROGRAMA ACCEDA AL PUERTO PARA CREAR UNA CONEXION
				conexion = servidor.accept();
				
//				 ______________________________
//______________/SE RECIBE E IMPRIME UN MENSAJE
				entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
				mensajeEntrada = entrada.readLine();
				
				conversacion = areaTexto.getText();
				areaTexto.setText(conversacion + "\nUSUARIO 2:" + mensajeEntrada);
			}
		}
		
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR: EL PROGRAMA FUE INICIADO ANTERIORMENTE");
		}
	}
	
	public static void main(String args[]) {
		UsuarioUno ventanaUsuarioUno = new UsuarioUno();
		ventanaUsuarioUno.setBounds(0,0,600,800);
		ventanaUsuarioUno.setVisible(true);
		ventanaUsuarioUno.setLocationRelativeTo(null);
		ventanaUsuarioUno.setResizable(false);
		mensajeEntrante();
	}
}
