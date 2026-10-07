package com.duoc.speedfastapp.view;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.duoc.speedfastapp.controller.ControladorEntrega;

public class VentanaRegistroEntrega extends JFrame {

	private JTable tablaRegistros;
	private DefaultTableModel modeloTabla;
	private ControladorEntrega controladorEntrega;

	public VentanaRegistroEntrega(String titulo, JFrame ventanaPadre, ControladorEntrega ctr)
	{
		//
		this.controladorEntrega = ctr;
	}

}
