package com.duoc.speedfastapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.speedfastapp.dao.RepartidorDAO;
import com.duoc.speedfastapp.model.Repartidor;

/**
 * Maneja operaciones CRUD de Repartidor
 * CLASS ControladorRepartidor
 */
public class ControladorRepartidor {

	private final RepartidorDAO repartidorDAO;

	public ControladorRepartidor(RepartidorDAO dao)
	{
		this.repartidorDAO = dao;
	}

	/**
	 * Guarda Entidado Repartidor en la base de datos.
	 * @param repartidor
	 * @throws SQLException
	 */
	public void guardar(Repartidor repartidor) throws SQLException
	{
		this.repartidorDAO.create(repartidor);
	}

	/**
	 * Actualiza registro existente de Repartidor
	 * @param repartidor
	 * @throws SQLException
	 */
	public void actualizar(Repartidor repartidor) throws SQLException
	{
		this.repartidorDAO.update(repartidor);
	}

	/**
	 * Recuperar todos los registros de Repartidores
	 * @return
	 * @throws SQLException
	 */
	public List<Repartidor> listarTodos() throws SQLException
	{
		return this.repartidorDAO.readAll();
	}

	/**
	 * Borrar permanentemente registro de la base de datos
	 * @param id
	 * @throws SQLException
	 */
	public void borrar(int id) throws SQLException
	{
		this.repartidorDAO.delete(id);
	}
	
}
