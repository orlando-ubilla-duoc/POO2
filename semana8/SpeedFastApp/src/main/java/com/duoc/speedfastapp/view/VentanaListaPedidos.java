package com.duoc.speedfastapp.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.duoc.speedfastapp.controller.ControladorPedido;


public class VentanaListaPedidos extends JFrame {

	private JTable tablaPedidos;
	private DefaultTableModel modeloTabla;
	private ControladorPedido controlador;

	public VentanaListaPedidos(String titulo, JFrame ventanaPadre)
	{
		super(titulo);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setResizable(false);

		inicializarComponentes();

		refrescarTabla();

		// registro evento al cerrar esta ventana
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				ventanaPadre.setVisible(true);
			}

			@Override
			public void windowClosed(WindowEvent e){
				ventanaPadre.setVisible(true);
			}
		});

		setVisible(true);

	}

	private void inicializarComponentes()
	{
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Dirección");
		modeloTabla.addColumn("Tipo");
		modeloTabla.addColumn("Estado");
		tablaPedidos = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tablaPedidos);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void refrescarTabla()
	{
		/*
		modeloTabla.setRowCount(0); // limpia tabla
		List<Pedido> pedidos = controlador.listarPedidos(0);
		for (Pedido pedido : pedidos)
		{
			modeloTabla.addRow(new Object[]{
				pedido.getNroPedido(),
				pedido.getDireccionEntrega(),
				pedido.getTipo(),
				pedido.getEstado()
			});
		}
		*/
	}



}
