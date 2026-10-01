package com.duoc.speedfastapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.duoc.speedfastapp.model.Pedido;

/**
 * 
 * PedidoDAO:
 * Interface de acceso a datos para operaciones CRUD sobre la entidad base.
 */
public interface PedidoDAO
{
	/**
	 * Inserta en la tabla Pedidos un registro nuevo.
	 * @param nuevoPedido
	 * @throws SQLException
	 */
	public boolean create(Pedido nuevoPedido) throws SQLException;

	/**
	 * Recuperar todas las tuplas de la tabla Pedidos.
	 * @return Listado de Pedidos
	 * @throws SQLException
	 */
	public List<Pedido> readAll() throws SQLException;

	/**
	 * Actualiza un registro de Pedido existente en la BD.
	 * @param cambiaPedido
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Pedido cambiaPedido) throws SQLException;

	/**
	 * Elimina permanente mente un registro de Pedido en la BD.
	 * @param idKey
	 * @throws SQLException
	 */
	public boolean delete(int idKey) throws SQLException;

}
