package com.duoc.bibliotecapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.dao.CategoriaDAO;
import com.duoc.bibliotecapp.model.Categoria;

/**
 * Maneja las operaciones de acceso a categorías.
 */
public class ControladorCategoria {

	private final CategoriaDAO categoriaDAO;

	public ControladorCategoria(CategoriaDAO dao) {
		this.categoriaDAO = dao;
	}

	public boolean guardar(Categoria categoria) throws SQLException {
		return this.categoriaDAO.create(categoria);
	}

	public boolean actualizar(Categoria categoria) throws SQLException {
		return this.categoriaDAO.update(categoria);
	}

	public List<Categoria> listarCategorias() throws SQLException {
		return this.categoriaDAO.readAll();
	}

	public boolean borrar(int id) throws SQLException {
		return this.categoriaDAO.delete(id);
	}
}
