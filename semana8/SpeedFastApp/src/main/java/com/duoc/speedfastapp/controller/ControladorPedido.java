package com.duoc.speedfastapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.speedfastapp.dao.PedidoDAO;
import com.duoc.speedfastapp.model.Pedido;

/**
 * Maneja operaciones CRUD de Pedido
 * CLASS ControladorPedido
 */
public class ControladorPedido {

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
		this.pedidoDAO.create(pedido);
	}

	/**
	 * Actualiza registro existente de Pedido
	 * @param pedido
	 * @throws SQLException
	 */
	public void actualizar(Pedido pedido) throws SQLException
	{
		this.pedidoDAO.update(pedido);
	}

	/**
	 * Recuperar todos los registros de Pedidos
	 * @return List Pedido
	 * @throws SQLException
	 */
	public List<Pedido> listarPedidos() throws SQLException
	{
		return this.pedidoDAO.readAll();
	}
	
	/**
	 * Borrar permanentemente registro de la base de datos
	 * @param id
	 * @throws SQLException
	 */
	public void borrar(int id) throws SQLException
	{
		this.pedidoDAO.delete(id);
	}

}
