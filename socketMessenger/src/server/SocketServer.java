package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main (String[] args) {
		boolean active = true;
		try {
			ServerSocket servidor = new ServerSocket(40000);
			while (active) {
				System.out.println("Listening...");
				
				Socket conexion = servidor.accept();
				 
				BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
				
				String mensaje = lector.readLine();
				System.out.println("Mensaje recibido: " + mensaje);
				
				conexion.close();

			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
