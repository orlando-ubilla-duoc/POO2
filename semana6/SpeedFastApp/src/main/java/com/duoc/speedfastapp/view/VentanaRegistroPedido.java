package com.duoc.speedfastapp.view;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaRegistroPedido extends JFrame {

	public VentanaRegistroPedido(String titulo, JFrame ventanaPadre){
		super(titulo);
		setSize(800,512);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setResizable(false);
		ConfigurarComponentes();

		// registro evento al cerrar esta ventana
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				ventanaPadre.setVisible(true);
				/*if( getVentanaPadre()!=null ){
					getVentanaPadre().setVisible(true); // vuelve a mostrar ventana padre
				}*/
			}

			@Override
			public void windowClosed(WindowEvent e){
				ventanaPadre.setVisible(true);
				/*if( getVentanaPadre()!=null ){
					getVentanaPadre().setVisible(true); // vuelve a mostrar ventana padre
				}*/
			}
		});

		setVisible(true);

	}

	private void ConfigurarComponentes(){
		// Panel para campos de texto
		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout( 4, 4, 10, 10));
		panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
}
