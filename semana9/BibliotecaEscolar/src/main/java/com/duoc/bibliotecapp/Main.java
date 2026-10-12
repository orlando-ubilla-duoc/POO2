package com.duoc.bibliotecapp;

import javax.swing.SwingUtilities;

import com.duoc.bibliotecapp.controller.ControladorUsuario;
import com.duoc.bibliotecapp.dao.UsuarioDAO;
import com.duoc.bibliotecapp.dao.impl.UsuarioDAOImpl;
import com.duoc.bibliotecapp.view.VentanaLogin;

public class Main {

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> {
			UsuarioDAO usuarioDao = new UsuarioDAOImpl();
			ControladorUsuario controlador = new ControladorUsuario(usuarioDao);
			VentanaLogin loginFrame = new VentanaLogin( controlador);
		});
	}
}