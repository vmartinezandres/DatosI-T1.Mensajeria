package cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NuevoContacto extends JFrame implements ActionListener {
	
//	 ________________________________
//__/VARIABLES PARA GUARDAR LOS DATOS
	public static String nombreContacto, puertoSalidaAnterior = Chat.puertoSalidaTexto;
	public static int puertoSalida, buscadorContacto;
	public static boolean aceptarContacto;
	
//	 ____________________________________________________
//__/VARIABLES PARA LA CONSTRUCION DE LA INTERFAZ GRAFICA
	public static JLabel etiquetaImagen, etiquetaNombre, etiquetaPuertoSalida;
	public static JTextField campoNombre, campoPuertoSalida;
	public static JButton botonAceptar;
	
//	 ____________________________________
//__/CONSTRUCTOR PARA LA INTERFAZ GRAFICA
	public NuevoContacto() {
		setLayout(null);
		setTitle("Nuevo contacto");
		getContentPane().setBackground(new Color(0, 148, 200));
		
		ImageIcon imagen = new ImageIcon("Imagenes/NuevoContacto.png");
		etiquetaImagen = new JLabel(imagen);
		etiquetaImagen.setBounds(65,5,115,115);
		add(etiquetaImagen);
		
		etiquetaNombre = new JLabel("Nombre:");
		etiquetaNombre.setBounds(25,120,100,30);
		etiquetaNombre.setFont(new Font("Chalkduster", 1, 18));
		etiquetaNombre.setForeground(Color.WHITE);
		add(etiquetaNombre);
		
		etiquetaPuertoSalida = new JLabel("Puerto de salida:");
		etiquetaPuertoSalida.setBounds(25,180,250,30);
		etiquetaPuertoSalida.setFont(new Font("Chalkduster", 1, 18));
		etiquetaPuertoSalida.setForeground(Color.WHITE);
		add(etiquetaPuertoSalida);
		
		campoNombre = new JTextField();
		campoNombre.setBounds(22,145,200,30);
		campoNombre.setFont(new Font(null, 0, 16));
		campoNombre.setBackground(new Color(236, 252, 255));
		add(campoNombre);
		
		campoPuertoSalida = new JTextField();
		campoPuertoSalida.setBounds(22,205,85,30);
		campoPuertoSalida.setFont(new Font(null, 0, 16));
		campoPuertoSalida.setBackground(new Color(236, 252, 255));
		add(campoPuertoSalida);
		
		botonAceptar = new JButton("✔");
		botonAceptar.setBounds(175,220,50,40);
		botonAceptar.setFont(new Font(null, 1, 20));
		botonAceptar.addActionListener(this);
		botonAceptar.setEnabled(true);
		add(botonAceptar);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
//	 ______________________________________
//__/ACTIONES AL PRESIONAR EL BOTON ACEPTAR
	
	/*
	 * Al presionar el JButton: BotonAceptar, se comprobara que los datos ingresados cumplan ciertas condiciones, 
	 * en caso de cumplirse, se agregara un nuevo contacto a la matriz: matrizContactos y se agregara un nuevo 
	 * item al JComboBox: cajaContactos con el nuevo puerto de salida.
	 * 
	 * */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonAceptar) {
			nombreContacto = campoNombre.getText().trim();
			if (nombreContacto.equals("")) {
				campoNombre.setBackground(Color.PINK);
				JOptionPane.showMessageDialog(null, "NOMBRE INVALIDO", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				campoNombre.setBackground(Color.WHITE);
				
				try {
					puertoSalida = Integer.parseInt(campoPuertoSalida.getText());
					
					if (puertoSalida != InfoInicial.puertoEntrada) {
						
						aceptarContacto = true;
						
						while (buscadorContacto < Chat.cantidadContactos) {
							if (Chat.matrizContactos[buscadorContacto][1].equals(String.valueOf(puertoSalida))) {
								aceptarContacto = false;
								break;
							}	
							
							buscadorContacto++;
						}	
						
						buscadorContacto = 0;
						
						if (aceptarContacto == true) {
							
							campoPuertoSalida.setText("");
							campoPuertoSalida.setBackground(Color.WHITE);

							Chat.cajaContactos.addItem(puertoSalida);
					
							Chat.matrizContactos[Chat.cantidadContactos][0] = nombreContacto;
							Chat.matrizContactos[Chat.cantidadContactos][1] = String.valueOf(puertoSalida);
							
							Chat.cantidadContactos++;
								
							this.setVisible(false);
						}
						
						else {
							campoPuertoSalida.setBackground(Color.PINK);
							JOptionPane.showMessageDialog(null, "PUERTO YA REGISTRADO", "ERROR", JOptionPane.WARNING_MESSAGE);
						}
					}
					
					else {
						campoPuertoSalida.setBackground(Color.PINK);
						JOptionPane.showMessageDialog(null, "PUERTO PERSONAL", "ERROR", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				catch (Exception e2) {
					campoPuertoSalida.setBackground(Color.PINK);
					JOptionPane.showMessageDialog(null, "PUERTO INVALIDO", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}
