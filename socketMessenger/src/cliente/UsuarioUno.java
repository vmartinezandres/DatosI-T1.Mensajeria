package cliente;

import java.awt.Font;
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
		
	private static JTextArea areaTexto;
	private static JScrollPane barra;
	private static JButton botonEnviar;
	private static JTextField campoTexto;
	static String mensaje;
	static String conversacion;
	
	static boolean activo = true;
	static BufferedReader entrada;
	static ServerSocket servidor;
	static Socket conexion;
	static String mensajeEntrada;
	static int puerto = 40000;
	
	public static void Refrescar() {
		try {
//			 _____________________________
//__________/SE CREA LA CONEXION AL PUERTO
			servidor = new ServerSocket(puerto);
			System.out.println("Puerto disponible!");
			
			while (activo) {
//				 ______________________________________________________________________
//______________/SE ESPERA A QUE OTRO PROGRAMA ACCEDA AL PUERTO PARA CREAR UNA CONEXION
				System.out.println("Waiting...");
				conexion = servidor.accept();
				
//				 ______________________________
//______________/SE RECIBE E IMPRIME UN MENSAJE
				entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
				mensajeEntrada = entrada.readLine();
				
				if (mensajeEntrada.contentEquals(mensaje)) {
					System.out.println("No imprimo nada");
				}
				else {
					conversacion = areaTexto.getText();
					areaTexto.setText(conversacion + "\nUSUARIO 1:" + mensajeEntrada);
				}		
			}
		}
		
		catch(IOException e) {
			System.out.print("ERROR ??");
		}
	}

	
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
		campoTexto.setFont(new Font("Andale Mono", 0, 16));
		add(campoTexto);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	 ______________________________
//__/ACTIONES AL PRESIONAR UN BOTON
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonEnviar) {
			try {
//				 ________________________________________
//______________/SE ESTABLECE CONEXION CON LA IP Y PUERTO		
				Socket conexion = new Socket("127.0.0.1", 40000);
						
//				 _______________________________
//______________/SE MANDA UN MENSAJE AL SERVIDOR	
				mensaje = campoTexto.getText().trim();
				campoTexto.setText("");
				
				if (mensaje.equals("")) {
					JOptionPane.showMessageDialog(null, "MENSAJE VACIO!" );
				}
				else {
					OutputStreamWriter salida = new OutputStreamWriter(conexion.getOutputStream());
					salida.write(mensaje);
					salida.flush();
					
					conversacion = areaTexto.getText();
					areaTexto.setText(conversacion + "\nUSUARIO 1:" + mensaje);
					
					conexion.close();
				}
				
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(null, "ERROR: NO SE PUDO ESTABLECER CONEXION CON EL SERVIDOR");;
			}
		}
	}
	
	public static void main(String args[]) {
		UsuarioUno ventanaUsuario = new UsuarioUno();
		ventanaUsuario.setBounds(0,0,600,800);
		ventanaUsuario.setVisible(true);
		ventanaUsuario.setLocationRelativeTo(null);
		ventanaUsuario.setResizable(false);
		Refrescar();
	}
}
