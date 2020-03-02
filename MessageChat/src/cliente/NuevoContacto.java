package cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NuevoContacto extends JFrame implements ActionListener {
	
//	 ________________________________
//__/VARIABLES PARA GUARDAR LOS DATOS
	public static String nombreContacto, puertoSalidaAnterior = Chat.puertoSalidaTexto;
	public static int puertoSalida;
//	public static String matrizContactos[][] = Chat.matrizContactos;
	
//	 ____________________________________________________
//__/VARIABLES PARA LA CONSTRUCION DE LA INTERFAZ GRAFICA
	public static JLabel etiquetaImagen, etiquetaNombre, etiquetaPuertoSalida;
	public static JTextField campoNombre, campoPuertoSalida;
	public static JButton botonAceptar;
	
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
					campoPuertoSalida.setText("");
					campoPuertoSalida.setBackground(Color.WHITE);

					Chat.cajaContactos.addItem(puertoSalida);
			
					Chat.matrizContactos[Chat.cantidadContactos][0] = nombreContacto;
					Chat.matrizContactos[Chat.cantidadContactos][1] = String.valueOf(puertoSalida);
					
					Chat.cantidadContactos++;
						
					Chat.botonConfirmarContacto.setEnabled(true);
					this.setVisible(false);
					}
				
				catch (Exception e2) {
					campoPuertoSalida.setBackground(Color.PINK);
					JOptionPane.showMessageDialog(null, "PUERTO INVALIDO", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	public static void main (String[] args) {
		NuevoContacto ventanaContacto = new NuevoContacto();
		ventanaContacto.setBounds(0,0,245,300);
		ventanaContacto.setVisible(true);
		ventanaContacto.setLocationRelativeTo(null);
		ventanaContacto.setResizable(false);
	}

}
