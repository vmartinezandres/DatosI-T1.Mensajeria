package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import cliente.Chat;
import cliente.InfoInicial;

public class EntradaDatos extends Thread {
	
//	 _______________________________
//__/VARIABLES INTERNAS DEL PROGRAMA
	public static ServerSocket servidor;
	public static int buscadorContacto, puertoEntrada = InfoInicial.puertoEntrada;
	public static Socket conexion;
	public static BufferedReader entrada;
	public static String mensajeEntrada;
	public static String conversacionActiva;
	
	public EntradaDatos(String msg) {
		super(msg);
	}
	
	public void run() {
		try {
//			 _____________________________
//__________/SE CREA LA CONEXION AL PUERTO
			servidor = new ServerSocket(puertoEntrada);
			
			while (true) {
//				 ______________________________________________________________________
//______________/SE ESPERA A QUE OTRO PROGRAMA ACCEDA AL PUERTO PARA CREAR UNA CONEXION
				conexion = servidor.accept();
				
//				 ____________________
//______________/SE RECIBE EL MENSAJE
				entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
				mensajeEntrada = entrada.readLine();
				
//			     _____________________________________________
//______________/SE ENCUENTRA EL CONTACTO E IMPRIME EL MENSAJE
				while (buscadorContacto < Chat.cantidadContactos) {
					if (Chat.matrizContactos[buscadorContacto][1].equals(Chat.puertoSalidaTexto)) {
						conversacionActiva = Chat.areaTexto.getText();
						Chat.areaTexto.setText(conversacionActiva + "\n" + Chat.matrizContactos[buscadorContacto][0] + ": " + mensajeEntrada);
						break;
					}
					
					buscadorContacto++;
				}
				
				buscadorContacto = 0;						
			}
		}
		
		catch(IOException e) {
//			 __________________________________________________________
//__________/ERROR AL EXISTIR DOS CHATS CON EL MISMOS PUERTO DE ENTRADA		
			JOptionPane.showMessageDialog(null, "PUERTO YA EXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}