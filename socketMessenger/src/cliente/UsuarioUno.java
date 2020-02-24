package cliente;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class UsuarioUno {
	public static void main (String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		
//		 ________________________________________
//______/SE ESTABLECE CONEXION CON LA IP Y PUERTO		
		Socket conexion = new Socket("127.0.0.1", 40000);
		
//		 _______________________________
//______/SE MANDA UN MENSAJE AL SERVIDOR	
		OutputStreamWriter salida = new OutputStreamWriter(conexion.getOutputStream());
		System.out.print("USUARIO 1: ");
		String mensajeSalida = "USUARIO 1: " + teclado.nextLine();
		salida.write(mensajeSalida);
		salida.flush();
		
		conexion.close();
		teclado.close();
	}
}
