package com.duoc.speedfastapp.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.duoc.speedfastapp.controller.ControladorRepartidor;
import com.duoc.speedfastapp.model.Repartidor;

public class VentanaRegistroRepartidor extends JFrame {

	private JTextField txtNombre;
	private JButton btnGuardar;
	private ControladorRepartidor controlador;

	public VentanaRegistroRepartidor(String titulo, JFrame ventanaPadre)
	{
		super(titulo);
		this.controlador = new ControladorRepartidor();
		setSize(400,320);
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
		panelFormulario.setLayout(new GridLayout( 5, 2, 10, 10));
		panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelFormulario.add(new JLabel("Agregar nuevo Repartidor"));
		panelFormulario.add(new JLabel(""));

		panelFormulario.add(new JLabel("Nombre repartidor:"));
		txtNombre = new JTextField();
		panelFormulario.add(txtNombre);

		panelFormulario.add(new JLabel(""));
		btnGuardar = new JButton("Guardar");
		panelFormulario.add(btnGuardar);

		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		add(panelFormulario, BorderLayout.CENTER);
		btnGuardar.addActionListener(e -> guardarRepartidor());

		JPanel panel2 = new JPanel();
		//panel2.setBackground(Color.BLUE);
		add(panel2, BorderLayout.CENTER);

	}

	private void guardarRepartidor()
	{
		String nombre = txtNombre.getText().trim();

		if (nombre.isEmpty()){
			JOptionPane.showMessageDialog(
				this,
				"Por favor complete todos los campos.",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			return;
		}

		Repartidor nuevoRepartidor = new Repartidor(0, nombre, "");

		this.controlador.guardar(nuevoRepartidor);
	}



}
