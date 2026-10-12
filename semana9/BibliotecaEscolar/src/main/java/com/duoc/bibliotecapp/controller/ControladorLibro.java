package com.duoc.bibliotecapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.dao.LibroDAO;
import com.duoc.bibliotecapp.model.Libro;

/**
 * Maneja las operaciones CRUD de libros.
 */
public class ControladorLibro {

	private final LibroDAO libroDAO;

	public ControladorLibro(LibroDAO dao) {
		this.libroDAO = dao;
	}

	public boolean guardar(Libro libro) throws SQLException {
		return this.libroDAO.create(libro);
	}

	public boolean actualizar(Libro libro) throws SQLException {
		return this.libroDAO.update(libro);
	}

	public List<Libro> listarLibros() throws SQLException {
		return this.libroDAO.readAll();
	}

	public boolean borrar(int id) throws SQLException {
		return this.libroDAO.delete(id);
	}
}
