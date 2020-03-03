package cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chat extends JFrame implements ActionListener, ItemListener {
	
//	 _______________________________
//__/VARIABLES INTERNAS DEL PROGRAMA
	public static String nombreUsuario = InfoInicial.nombreUsuario;
	public static String puertoSalidaTexto, ip = "127.0.0.1";
	public static int puertoSalida, cantidadContactos, buscadorContacto;
	public static OutputStreamWriter salida;
	public static String matrizContactos[][] = new String[100][3];
	
//	 ________________________________________________
//__/VARIABLES PARA LA VISUALIZACION DE LA MENSAJERIA
	public static String miMensaje;
	public static String conversacionActiva;
	
//	 ____________________________________________________
//__/VARIABLES PARA LA CONSTRUCION DE LA INTERFAZ GRAFICA
	public static JTextArea areaTexto;
	public static JScrollPane barra; 
	public static JButton botonConfiguracion, botonNuevoContacto, 
	botonEliminarContacto, botonEnviar;
	public static JTextField campoTexto;
	public static JLabel etiquetaImagen, etiquetaContactos;
	public static JComboBox<Integer> cajaContactos;
	
//	 ____________________________________
//__/CONSTRUCTOR PARA LA INTERFAZ GRAFICA		
	public Chat() {
		setLayout(null);
		setTitle("Chat de " + nombreUsuario);
		getContentPane().setBackground(new Color(0, 148, 200));
		
		ImageIcon imagen = new ImageIcon("Imagenes/Usuario.png");
		etiquetaImagen = new JLabel(imagen);
		etiquetaImagen.setBounds(0,10,150,150);
		add(etiquetaImagen);
		
		etiquetaContactos = new JLabel("CONTACTOS");
		etiquetaContactos.setBounds(15,180,120,30);
		etiquetaContactos.setFont(new Font("Chalkduster", 1, 18));
		etiquetaContactos.setForeground(Color.WHITE);
		add(etiquetaContactos);
		
		cajaContactos = new JComboBox<Integer>();
		cajaContactos.setBounds(0,200,150,50);
		cajaContactos.setFont(new Font("Noteworthy", 1, 14));
		cajaContactos.setBackground(new Color(236, 252, 255));
		cajaContactos.addItemListener(this);
		add(cajaContactos);
		
		botonConfiguracion = new JButton("⚙");
		botonConfiguracion.setBounds(0,0,35,35);
		botonConfiguracion.setFont(new Font(null, 1, 32));
		botonConfiguracion.setForeground(Color.DARK_GRAY);
		botonConfiguracion.addActionListener(this);
		botonConfiguracion.setEnabled(true);
		add(botonConfiguracion);
		
		botonNuevoContacto = new JButton("+");
		botonNuevoContacto.setBounds(28,250,50,40);
		botonNuevoContacto.setFont(new Font(null, 1, 20));
		botonNuevoContacto.addActionListener(this);
		botonNuevoContacto.setEnabled(true);
		add(botonNuevoContacto);
		
		botonEliminarContacto = new JButton("✂");
		botonEliminarContacto.setBounds(73,250,50,40);
		botonEliminarContacto.setFont(new Font(null, 1, 20));
		botonEliminarContacto.addActionListener(this);
		botonEliminarContacto.setEnabled(false);
		add(botonEliminarContacto);
		
		botonEnviar = new JButton("➤");
		botonEnviar.setBounds(514,723,80,50);
		botonEnviar.setFont(new Font(null, 0, 24));
		botonEnviar.setForeground(new Color(0, 148, 200));
		botonEnviar.addActionListener(this);
		botonEnviar.setEnabled(true);
		add(botonEnviar);
		
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		areaTexto.setBackground(new Color(236, 252, 255));
		barra = new JScrollPane(areaTexto);
		barra.setBounds(150,0,450,720);
		barra.setBackground(new Color(236, 252, 255));
		add(barra);
		
		campoTexto = new JTextField();
		campoTexto.setBounds(146,730,367,35);
		campoTexto.setBackground(new Color(236, 252, 255));
		add(campoTexto);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	 _________________________________
//__/ACTIONES AL PRESIONAR ALGUN BOTON
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonConfiguracion) {
	
		}
		
		else if (e.getSource() == botonNuevoContacto) {
			NuevoContacto ventanaContacto = new NuevoContacto();
			ventanaContacto.setBounds(0,0,245,300);
			ventanaContacto.setVisible(true);
			ventanaContacto.setLocationRelativeTo(null);
			ventanaContacto.setResizable(false);
		}
		
		else if (e.getSource() == botonEliminarContacto) {
//			 __________________________________
//__________/SE ENCUENTRA Y ELIMINA EL CONTACTO
			while (buscadorContacto < cantidadContactos) {
				if (matrizContactos[buscadorContacto][1].equals(puertoSalidaTexto)) {
					
					matrizContactos[buscadorContacto][0] = "";
					matrizContactos[buscadorContacto][1] = "";
					matrizContactos[buscadorContacto][2] = "";
					
					for (int buscadorItem = 0; buscadorItem < cajaContactos.getItemCount(); buscadorItem++) {
						if (cajaContactos.getItemAt(buscadorItem).toString().equals(puertoSalidaTexto)) {
							
							cajaContactos.removeItemAt(buscadorItem);
						}
					}
				
					break;
				}
				
				buscadorContacto++;
			}
			
			buscadorContacto = 0;
		}
		
		else if (e.getSource() == botonEnviar) {
			miMensaje = campoTexto.getText().trim();
			
			if (miMensaje.equals("")) {
				campoTexto.setBackground(Color.PINK);
			}
			else {
				campoTexto.setBackground(new Color(236, 252, 255));
				campoTexto.setText("");
				try {
//					 ________________________________________
//__________________/SE ESTABLECE CONEXION CON LA IP Y PUERTO		
					Socket conexion = new Socket(ip, puertoSalida);
							
//					 _______________________________
//__________________/SE MANDA UN MENSAJE AL SERVIDOR						
					salida = new OutputStreamWriter(conexion.getOutputStream());
					salida.write(miMensaje);
					salida.flush();
						
					conversacionActiva = areaTexto.getText();
					areaTexto.setText(conversacionActiva + "\nYo: " + miMensaje);
					
					conexion.close();	
				} 
				catch (IOException exception) {
					JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTARSE A CHAT", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
//	 ___________________________
//__/ACTIONES AL CAMBIAR DE ITEM
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cajaContactos) {
			if (cajaContactos.getSelectedItem() != null && cajaContactos.getItemCount() != 0) {
// 				 ____________________________________________
//______________/SE ENCUENTRA EL CONTACTO Y SE GUARDA SU CHAT
				while (buscadorContacto < cantidadContactos) {
					if (matrizContactos[buscadorContacto][1].equals(puertoSalidaTexto)) {
						matrizContactos[buscadorContacto][2] = areaTexto.getText();
						areaTexto.setText("");
						break;
					}
								
					buscadorContacto++;
				}
							
				buscadorContacto = 0;
						
				puertoSalidaTexto = cajaContactos.getSelectedItem().toString();
				puertoSalida = Integer.parseInt(puertoSalidaTexto);	
							
//				 ______________________________________________
//______________/SE ENCUENTRA EL CONTACTO Y SE PROYECTA SU CHAT
				while (buscadorContacto < cantidadContactos) {
					if (matrizContactos[buscadorContacto][1].equals(puertoSalidaTexto)) {
									
						areaTexto.setText(matrizContactos[buscadorContacto][2]);
						break;
					}
								
					buscadorContacto++;
				}
							
				buscadorContacto = 0;
				
				botonEliminarContacto.setEnabled(true);
			}
			
			else {
				
				areaTexto.setText("");
				botonEliminarContacto.setEnabled(false);
			}
		}
	}
	
	public static void main(String args[]) {
		Chat ventanaChat = new Chat();
		ventanaChat.setBounds(0,0,600,800);
		ventanaChat.setVisible(true);
		ventanaChat.setLocationRelativeTo(null);
		ventanaChat.setResizable(false);
	}
}