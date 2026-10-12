package com.duoc.bibliotecapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.model.Libro;

public interface LibroDAO {

	public boolean create(Libro nuevoLibro) throws SQLException;
	public List<Libro> readAll() throws SQLException;
	public boolean update(Libro cambiaLibro) throws SQLException;
	public boolean delete(int idKey) throws SQLException;
}
