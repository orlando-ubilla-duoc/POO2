package com.duoc.speedfastapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.duoc.speedfastapp.model.Entrega;

/**
 * 
 * EntregaDAO:
 * Interface de acceso a datos para operaciones CRUD sobre la entidad base.
 */
public interface EntregaDAO
{
	/**
	 * Inserta en la tabla Entregas un registro nuevo.
	 * @param nuevaEntrega
	 * @throws SQLException
	 */
	public boolean create(Entrega nuevaEntrega) throws SQLException;

	/**
	 * Recuperar todas las tuplas de la tabla Entregas.
	 * @return Listado de Entregas
	 * @throws SQLException
	 */
	public List<Entrega> readAll() throws SQLException;

	/**
	 * Actualiza un registro de Entrega existente en la BD.
	 * @param cambiaEntrega
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Entrega cambiaEntrega) throws SQLException;

	/**
	 * Elimina permanente mente un registro de Entrega en la BD.
	 * @param idKey
	 * @throws SQLException
	 */
	public boolean delete(int idKey) throws SQLException;
}
