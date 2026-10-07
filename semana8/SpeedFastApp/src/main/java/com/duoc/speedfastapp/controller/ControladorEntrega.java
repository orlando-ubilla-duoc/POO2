package com.duoc.speedfastapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.speedfastapp.dao.EntregaDAO;
import com.duoc.speedfastapp.model.Entrega;

/**
 * Maneja operaciones CRUD de Entrega
 * CLASS ControladorEntrega
 */
public class ControladorEntrega {

	private final EntregaDAO entregaDAO;
	
	public ControladorEntrega(EntregaDAO dao)
	{
		this.entregaDAO = dao;
	}

	/**
	 * Guarda Entidado Entrega en la base de datos.
	 * @param entrega
	 * @throws SQLException
	 */
	public void guardar(Entrega entrega) throws SQLException
	{
		this.entregaDAO.create(entrega);
	}

	/**
	 * Actualiza registro existente de Entrega
	 * @param entrega
	 * @throws SQLException
	 */
	public void actualizar(Entrega entrega) throws SQLException
	{
		this.entregaDAO.update(entrega);
	}

	/**
	 * Recuperar todos los registros de Entregas
	 * @return
	 * @throws SQLException
	 */
	public List<Entrega> listarEntregas() throws SQLException
	{
		return this.entregaDAO.readAll();
	}

	/**
	 * Borrar permanentemente registro de la base de datos
	 * @param id
	 * @throws SQLException
	 */
	public void borrar(int id) throws SQLException
	{
		this.entregaDAO.delete(id);
	}

}
