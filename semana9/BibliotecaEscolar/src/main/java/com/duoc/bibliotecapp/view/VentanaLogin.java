package com.duoc.bibliotecapp.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.duoc.bibliotecapp.controller.ControladorUsuario;
import com.duoc.bibliotecapp.model.Usuario;

public class VentanaLogin {

	private final JFrame ventana;
	private JTextField txtUser;
	private JPasswordField txtContrasena;
	private ControladorUsuario controladorUsuario;

	public VentanaLogin(ControladorUsuario ctr)
	{
		this.controladorUsuario = ctr;
		this.ventana = new JFrame("Acceso de Sistema Biblioteca");
		this.ventana.setSize(420, 240);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventana.setLocationRelativeTo(null);
		this.ventana.setLayout(new BorderLayout());
		configurarComponentes();
		this.ventana.setVisible(true);
		txtUser.requestFocus();
	}

	private void configurarComponentes() {
		JPanel panel = new JPanel(new BorderLayout(10, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		JLabel lblDescripcion = new JLabel("Bienvenido al sistema de gestión", JLabel.CENTER);
		lblDescripcion.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblDescripcion, BorderLayout.NORTH);

		JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 10, 10));
		panelFormulario.setOpaque(false);

		txtUser = new JTextField();
		txtContrasena = new JPasswordField();

		panelFormulario.add(new JLabel("RUT:"));
		panelFormulario.add(txtUser);
		panelFormulario.add(new JLabel("Contraseña:"));
		panelFormulario.add(txtContrasena);
		panel.add(panelFormulario, BorderLayout.CENTER);

		JButton botonIngresar = new JButton("Ingresar");
		botonIngresar.setFont(new Font("Arial", Font.PLAIN, 16));
		botonIngresar.addActionListener(event -> validarCredenciales());
		panel.add(botonIngresar, BorderLayout.SOUTH);

		this.ventana.add(panel, BorderLayout.CENTER);
		this.ventana.getRootPane().setDefaultButton(botonIngresar);
	}

	private void validarCredenciales()
	{
		try {
			String rut = txtUser.getText().trim();
			char[] contrasenaIngresada = txtContrasena.getPassword();

			Usuario loginUsuario = this.controladorUsuario.validarUsuario(rut, contrasenaIngresada);
			if( loginUsuario==null ){
				JOptionPane.showMessageDialog(ventana,"No se pudo ingresar al sistema.","Acceso denegado",JOptionPane.ERROR_MESSAGE);
			}

			JOptionPane.showMessageDialog( ventana, "Bienvenido "+loginUsuario.getNombre()+" !","Acceso correcto", JOptionPane.INFORMATION_MESSAGE);
			VentanaPrincipal programa = new VentanaPrincipal( loginUsuario);
		} catch (Exception e) {
			JOptionPane.showMessageDialog( ventana, e.getMessage(),"Error acceso", JOptionPane.ERROR_MESSAGE);
		}
	}

}
