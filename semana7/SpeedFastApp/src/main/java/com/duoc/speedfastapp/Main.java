package com.duoc.speedfastapp;

import javax.swing.SwingUtilities;

import com.duoc.speedfastapp.controller.ControladorPedido;
import com.duoc.speedfastapp.view.VentanaPrincipal;

public class Main {

	public static void main(String[] args)
	{
		ControladorPedido controladorPedidos = new ControladorPedido();

		SwingUtilities.invokeLater(() -> {
			VentanaPrincipal programa = new VentanaPrincipal( "SpeedFast v1.3", controladorPedidos);
		});
	}
}