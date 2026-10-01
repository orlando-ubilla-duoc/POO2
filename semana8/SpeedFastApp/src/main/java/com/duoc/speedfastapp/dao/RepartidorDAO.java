package com.duoc.speedfastapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.duoc.speedfastapp.model.Repartidor;

/**
 * 
 * RepartidorDAO:
 * Interface de acceso a datos para operaciones CRUD sobre la entidad base.
 */
public interface RepartidorDAO
{
	/**
	 * Inserta en la tabla Repartidores un registro nuevo.
	 * @param nuevoRepartidor
	 * @throws SQLException
	 */
	public boolean create(Repartidor nuevoRepartidor) throws SQLException;

	/**
	 * Recuperar todas las tuplas de la tabla Repartidores.
	 * @return Listado de Repartidores
	 * @throws SQLException
	 */
	public List<Repartidor> readAll() throws SQLException;

	/**
	 * Actualiza un registro de Repartidor existente en la BD.
	 * @param cambiaRepartidor
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Repartidor cambiaRepartidor) throws SQLException;

	/**
	 * Elimina permanente mente un registro de Repartidor en la BD.
	 * @param idKey
	 * @throws SQLException
	 */
	public boolean delete(int idKey) throws SQLException;
}
