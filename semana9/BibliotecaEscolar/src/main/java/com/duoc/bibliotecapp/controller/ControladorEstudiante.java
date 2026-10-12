package com.duoc.bibliotecapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.dao.EstudianteDAO;
import com.duoc.bibliotecapp.model.Estudiante;

/**
 * Maneja las operaciones CRUD de estudiantes.
 */
public class ControladorEstudiante {

	private final EstudianteDAO estudianteDAO;

	public ControladorEstudiante(EstudianteDAO dao) {
		this.estudianteDAO = dao;
	}

	public boolean guardar(Estudiante estudiante) throws SQLException {
		return this.estudianteDAO.create(estudiante);
	}

	public boolean actualizar(Estudiante estudiante) throws SQLException {
		return this.estudianteDAO.update(estudiante);
	}

	public List<Estudiante> listarEstudiantes() throws SQLException {
		return this.estudianteDAO.readAll();
	}

	public boolean borrar(int id) throws SQLException {
		return this.estudianteDAO.delete(id);
	}
}
