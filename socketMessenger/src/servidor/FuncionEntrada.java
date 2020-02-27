package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import cliente.Chat;
import cliente.InfoInicial;

public class FuncionEntrada extends Thread {
	
//	 _______________________________
//__/VARIABLES INTERNAS DEL PROGRAMA
	public static ServerSocket servidor;
	public static int puertoEntrada = InfoInicial.puertoEntrada;
	public static int puertoSalida = InfoInicial.puertoSalida;
	public static Socket conexion;
	public static BufferedReader entrada;
	public static String mensajeEntrada;
	public static String conversacionGuardada;
	
	public FuncionEntrada(String msg) {
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
				
//				 ____________________
//______________/SE IMPRIME EL MENSAJE
				conversacionGuardada = Chat.areaTexto.getText();
				Chat.areaTexto.setText(conversacionGuardada + "\n" + puertoSalida + ": " + mensajeEntrada);
			}
		}
		
		catch(IOException e) {
//			 __________________________________________________________
//__________/ERROR AL EXISTIR DOS CHATS CON EL MISMOS PUERTO DE ENTRADA			
			JOptionPane.showMessageDialog(null, "ERROR: YA EXISTE UN CHAT CON ESE PUERTO DE ENTRADA");
			System.exit(0);
		}
	}
}
