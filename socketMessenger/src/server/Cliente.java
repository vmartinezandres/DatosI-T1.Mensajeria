package server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Cliente {
	public static void main (String[] args) throws IOException {
		Socket conexion = new Socket("127.0.0.1", 40000);
		OutputStreamWriter salida = new OutputStreamWriter(conexion.getOutputStream());
		salida.write("Hola todo bien?");
		salida.flush();
		conexion.close();
	}
}
