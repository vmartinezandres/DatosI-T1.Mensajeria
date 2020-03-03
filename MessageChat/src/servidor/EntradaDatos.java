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
	public static int puertoProcedencia, buscadorContacto, puertoEntrada = InfoInicial.puertoEntrada;
	public static Socket conexion;
	public static BufferedReader entrada;
	public static String mensajeEntrada;
	public static String conversacionActiva;
	public static boolean bandera = true;
	
	public EntradaDatos(String msg) {
		super(msg);
	}	
	
//	 ___________________
//__/ACCIONES DEL THREAD
	
	/*
	 * Inicia comprobando que no exista otro usuario con el mismo puerto de entrada, en dicho caso se debera reiniciar el programa.
	 * Si los requisitos se cumplen, crea un puerto de entrada de datos (personal para cada usuario), al que le llegaran los puertos 
	 * de procedencia y los mensajes en cuestion, al combrobarse que el puerto de procedencia sea el mismo al puerto con quien se tiene 
	 * una conversacion, el mensaje se imprimira en pantalla, en caso contrario, aparecera una alerta de que algun usuario intenta comunicarse.
	 * 
	 * */
	public void run() {
		try {
			servidor = new ServerSocket(puertoEntrada);
			
			while (true) {
				conexion = servidor.accept();
				
				entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
				mensajeEntrada = entrada.readLine();

				int separadorPuertoTexto = mensajeEntrada.length();
				
				while (bandera) {
					try {
						puertoProcedencia = Integer.parseInt(mensajeEntrada.substring(0, separadorPuertoTexto));
						bandera = false;
					}
					catch (Exception e) {
						separadorPuertoTexto -= 1;
					}
				}
				
				bandera = true;

				mensajeEntrada = mensajeEntrada.substring(separadorPuertoTexto, mensajeEntrada.length());
				
				if (puertoProcedencia == Chat.puertoSalida) {
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
				
				else {
					JOptionPane.showMessageDialog(null, "+" + puertoProcedencia + " INTENTA HABLARTE", "MENSAJE ENTRANTE", JOptionPane.WARNING_MESSAGE);
				}						
			}
		}
		
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "PUERTO YA EXISTENTE", "ERROR", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}