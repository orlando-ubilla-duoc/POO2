package com.duoc.speedfastapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.speedfastapp.dao.PedidoDAO;
import com.duoc.speedfastapp.model.Pedido;


public class ControladorPedido
{

	private final PedidoDAO pedidoDAO;

	public ControladorPedido(PedidoDAO dao)
	{
		this.pedidoDAO = dao;
	}

	/**
	 * Guarda Entidado Pedido en la base de datos.
	 * @param pedido
	 * @throws SQLException
	 */
	public void guardar(Pedido pedido) throws SQLException
	{
		this.pedidoDAO.insertar(pedido);
	}

	/**
	 * Recuperar todos los registros de Pedidos
	 * @param limitLast Limite de ultimos registros a recuperar. Cero para ignorar.
	 * @return List Pedido
	 */
	public List<Pedido> listarPedidos(int limitLast) throws SQLException
	{
		return this.pedidoDAO.listarTodos(0);
	}
	
}
