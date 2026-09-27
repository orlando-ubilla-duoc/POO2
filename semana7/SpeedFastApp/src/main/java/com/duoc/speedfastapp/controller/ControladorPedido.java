package com.duoc.speedfastapp.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duoc.speedfastapp.model.Pedido;

import main.java.com.duoc.speedfastapp.controller.ConexionBaseDatos;


public class ControladorPedido {

	private List<Pedido> pedidos;
	private Connection conn;

	public ControladorPedido()
	{
		this.pedidos = new ArrayList<>();

		try {
			this.conn = ConexionBaseDatos.obtenerConexion();
			System.out.println("Conexión exitosa a la base de datos.");
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos:");
			e.printStackTrace();
		}
	}

	public void agregarPedido(Pedido pedido)
	{
		this.pedidos.add(pedido);
	}

	public List<Pedido> getPedidos()
	{
		return this.pedidos;
	}
	
}
