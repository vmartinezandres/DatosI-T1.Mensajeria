package cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InfoInicial extends JFrame implements ActionListener {
	
//	 ____________________________________________________
//__/VARIABLES PARA LA CONSTRUCION DE LA INTERFAZ GRAFICA
	private JLabel etiquetaNombre, etiquetaPuertoEntrada, etiqeutaPuertoSalida;
	private JTextField campoNombre, campoPuertoEntrada, campoPuertoSalida;
	private JButton botonAceptar;
	
//	 ________________________________
//__/VARIABLES PARA GUARDAR LOS DATOS
	public String nombreUsuario = "Andres";
	public int puertoEntrada, puertoSalida;

	public InfoInicial() {
		setLayout(null);
		setTitle("Datos Iniciales");
		
		etiquetaNombre = new JLabel("Nombre:");
		etiquetaNombre.setBounds(25,50,100,30);
		etiquetaNombre.setFont(new Font("Noteworthy", 1, 24));
		add(etiquetaNombre);
		
		etiquetaPuertoEntrada = new JLabel("Puerto de entrada:");
		etiquetaPuertoEntrada.setBounds(25,125,250,30);
		etiquetaPuertoEntrada.setFont(new Font("Noteworthy", 1, 24));
		add(etiquetaPuertoEntrada);
		
		etiqeutaPuertoSalida = new JLabel("Puerto de salida:");
		etiqeutaPuertoSalida.setBounds(25,200,250,30);
		etiqeutaPuertoSalida.setFont(new Font("Noteworthy", 1, 24));
		add(etiqeutaPuertoSalida);
		
		campoNombre = new JTextField();
		campoNombre.setBounds(120,55,200,30);
		campoNombre.setFont(new Font(null, 0, 16));
		add(campoNombre);
		
		campoPuertoEntrada = new JTextField();
		campoPuertoEntrada.setBounds(220,130,75,30);
		campoPuertoEntrada.setFont(new Font(null, 0, 16));
		add(campoPuertoEntrada);
		
		campoPuertoSalida = new JTextField();
		campoPuertoSalida.setBounds(200,205,75,30);
		campoPuertoEntrada.setFont(new Font(null, 0, 16));
		add(campoPuertoSalida);
		
		botonAceptar = new JButton("ACEPTAR");
		botonAceptar.setBounds(410,220,80,50);
		botonAceptar.addActionListener(this);
		botonAceptar.setEnabled(true);
		add(botonAceptar);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonAceptar) {
			nombreUsuario = campoNombre.getText().trim();
			if (nombreUsuario.equals("")) {
				campoNombre.setBackground(Color.PINK);
				JOptionPane.showMessageDialog(null, "ERROR: DIGITE UN NOMBRE");
			}
			else {
				campoNombre.setBackground(Color.WHITE);
				try {
					puertoEntrada = Integer.parseInt(campoPuertoEntrada.getText());
					puertoSalida = Integer.parseInt(campoPuertoSalida.getText());
					campoPuertoEntrada.setBackground(Color.WHITE);
					campoPuertoSalida.setBackground(Color.WHITE);
					
					System.out.print(nombreUsuario);
					System.out.print(puertoEntrada);
					System.out.print(puertoSalida);

//					UsuarioUno ventanaUsuarioUno = new UsuarioUno();
//					ventanaUsuarioUno.setBounds(0,0,600,800);
//					ventanaUsuarioUno.setVisible(true);
//					ventanaUsuarioUno.setLocationRelativeTo(null);
//					ventanaUsuarioUno.setResizable(false);
//					this.setVisible(false);
//					UsuarioUno.mensajeEntrante();
				}
				catch(Exception exc) {
					campoPuertoEntrada.setBackground(Color.PINK);
					campoPuertoSalida.setBackground(Color.PINK);
					JOptionPane.showMessageDialog(null, "ERROR: DIGITE PUERTOS VALIDOS");
				}
				
			}
		}
	}
	
	public static void main (String[] args) {
		InfoInicial ventanaInfo = new InfoInicial();
		ventanaInfo.setBounds(0,0,500,300);
		ventanaInfo.setVisible(true);
		ventanaInfo.setLocationRelativeTo(null);
		ventanaInfo.setResizable(false);
	}
}
