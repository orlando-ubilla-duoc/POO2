package com.duoc.bibliotecapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.model.Estudiante;

public interface EstudianteDAO {

	public boolean create(Estudiante nuevoEstudiante) throws SQLException;
	public List<Estudiante> readAll() throws SQLException;
	public boolean update(Estudiante cambiaEstudiante) throws SQLException;
	public boolean delete(int idKey) throws SQLException;
}
