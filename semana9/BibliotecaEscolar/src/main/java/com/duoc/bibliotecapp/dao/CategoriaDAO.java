package com.duoc.bibliotecapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.model.Categoria;

public interface CategoriaDAO {

	boolean create(Categoria nuevaCategoria) throws SQLException;

	List<Categoria> readAll() throws SQLException;

	boolean update(Categoria cambiaCategoria) throws SQLException;

	boolean delete(int idKey) throws SQLException;
}
