package cliente;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class UsuarioDos {
	public static void main (String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		
//		 ________________________________________
//______/SE ESTABLECE CONEXION CON LA IP Y PUERTO		
		Socket conexion = new Socket("127.0.0.1", 40000);
		
//		 _______________________________
//______/SE MANDA UN MENSAJE AL SERVIDOR	
		OutputStreamWriter salida = new OutputStreamWriter(conexion.getOutputStream());
		System.out.print("USUARIO 2: ");
		String mensajeSalida = "USUARIO 2: " + teclado.nextLine();
		salida.write(mensajeSalida);
		salida.flush();
		
		conexion.close();
		teclado.close();
	}
}
