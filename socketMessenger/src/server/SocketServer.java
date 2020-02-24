package server;

import java.io.BufferedReader;
//  ___________
//_/BIBLIOTECAS
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public void establecerPuerto() {
		String mensajeEntrada;
//		 _________	
//______/VARIABLES	
		int puerto = 40000;
		boolean activo = true;
		ServerSocket servidor;
		Socket conexion;
		BufferedReader entrada;

		
		try {
//			 _____________________________
//__________/SE CREA LA CONEXION AL PUERTO
			servidor = new ServerSocket(puerto);
			System.out.println("Puerto disponible!");
			
			while (activo) {
//				 ______________________________________________________________________
//______________/SE ESPERA A QUE OTRO PROGRAMA ACCEDA AL PUERTO PARA CREAR UNA CONEXION
				System.out.println("...");
				conexion = servidor.accept();
				
//				 ______________________________
//______________/SE RECIBE E IMPRIME UN MENSAJE
				entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
				mensajeEntrada = entrada.readLine();
				System.out.println(mensajeEntrada);
				
				//conexion.close();
				//servidor.close();
				
			}
		}
		
		catch(IOException e) {
			System.out.print("Error de al establecer conexion al puerto");
		}
	}
}
