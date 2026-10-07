package com.duoc.speedfastapp.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.duoc.speedfastapp.controller.ControladorEntrega;
import com.duoc.speedfastapp.controller.ControladorPedido;
import com.duoc.speedfastapp.controller.ControladorRepartidor;
import com.duoc.speedfastapp.dao.impl.EntregaDAOImpl;
import com.duoc.speedfastapp.dao.impl.PedidoDAOImpl;
import com.duoc.speedfastapp.dao.impl.RepartidorDAOImpl;

public class VentanaPrincipal {

	JFrame ventana;

	public VentanaPrincipal(String tituloVentana){
        this.ventana     = new JFrame(tituloVentana);
		this.ventana.setSize(800,480);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cerrar app
		this.ventana.setLocationRelativeTo(null); // centrar
		this.ventana.setLayout(new GridLayout( 1, 2, 0, 0));
		ConfigurarComponentes( tituloVentana);
		ventana.setVisible(true);
    }

    private void ConfigurarComponentes(String tituloPadre)
	{

		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridLayout( 5, 1, 10, 10));
		panelMenu.setBorder(BorderFactory.createEmptyBorder(15, 50, 50, 50));

		JLabel lblDescripcion = new JLabel("Bienvenido al sistema de gestion.");
		lblDescripcion.setFont(new Font("Arial", Font.BOLD, 16));

		JPanel panelLogo     = new JPanel();
		panelLogo.setLayout(new GridLayout( 1, 1, 5, 5));
		ImageIcon imagenLogo = new ImageIcon("../../../../../../resources/speedfast-logo.jpeg");
		JLabel labelLogo     = new JLabel(imagenLogo);
		labelLogo.setSize(new Dimension(160, 160));
		labelLogo.setMaximumSize(new Dimension(160, 160));
		panelLogo.add(labelLogo);

		// crear botones para el menu
		JButton boton1 = new JButton("1. Registrar Pedido");
		boton1.setFont(new Font("Arial", Font.PLAIN, 20));
		JButton boton4 = new JButton("2. Registrar Repartidor");
		boton4.setFont(new Font("Arial", Font.PLAIN, 20));
		JButton boton2 = new JButton("3. Gestionar Entregas");
		boton2.setFont(new Font("Arial", Font.PLAIN, 20));
		//JButton boton3 = new JButton("3. Asignar repartidor / Iniciar entrega");
		//boton3.setFont(new Font("Arial", Font.PLAIN, 14));
		JButton boton3 = new JButton("4. Salir");
		boton3.setFont(new Font("Arial", Font.PLAIN, 18));

		boton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// Oculta ventana principal
				ventana.setVisible(false);
				// Ventana Registo Pedido
				PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
				ControladorPedido controladorPedido = new ControladorPedido(pedidoDAO);
				VentanaRegistroPedido ventanaPedido = new VentanaRegistroPedido( tituloPadre+" - Registrar pedidos", ventana, controladorPedido);
			}
		});

		boton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// Oculta ventana principal
				ventana.setVisible(false);
				// Ventana Gestion Entregas
				EntregaDAOImpl entregaDAO = new EntregaDAOImpl();
				ControladorEntrega controladorEntrega = new ControladorEntrega(entregaDAO);
				VentanaRegistroEntrega ventanaListado = new VentanaRegistroEntrega( tituloPadre+" - Gestion entregas", ventana, controladorEntrega);
			}
		});

		boton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// Oculta ventana principal
				ventana.setVisible(false);
				// Ventana Registro Repartidor
				RepartidorDAOImpl repartidorDAO = new RepartidorDAOImpl();
				ControladorRepartidor controladorRepartidor = new ControladorRepartidor(repartidorDAO);
				VentanaRegistroRepartidor ventanaRepartidor = new VentanaRegistroRepartidor( tituloPadre+" - Registrar repartidor", ventana, controladorRepartidor);
			}
		});

		boton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});

		// Agrega elementos al menu
		panelMenu.add(lblDescripcion);
		panelMenu.add(boton1);
		panelMenu.add(boton4);
		panelMenu.add(boton2);
		panelMenu.add(boton3);

		ventana.add(panelLogo);
		ventana.add(panelMenu);
	}

}
