package server;

import java.io.BufferedReader;
//  ___________
//_/BIBLIOTECAS
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
//	 _________	
//__/VARIABLES	
	int puerto = 40000;
	boolean activo = true;
	public String mensajeEntrada;
	
	BufferedReader entrada;
	ServerSocket servidor;
	Socket conexion;

	public void establecerPuerto() {
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
				enviarMensaje(mensajeEntrada);
				
				//System.out.println(mensajeEntrada);
				
			}
		}
		
		catch(IOException e) {
			System.out.print("ERROR AL ESTABLECER CONEXION AL PUERTO");
		}
	}
	public String enviarMensaje(String msj) {	
		return msj;
	}
}
