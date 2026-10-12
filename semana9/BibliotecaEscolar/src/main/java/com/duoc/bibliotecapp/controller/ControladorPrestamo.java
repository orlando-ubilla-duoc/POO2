package com.duoc.bibliotecapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.dao.PrestamoDAO;
import com.duoc.bibliotecapp.model.LibroMasPrestado;
import com.duoc.bibliotecapp.model.Prestamo;

/**
 * Maneja las operaciones CRUD de préstamos.
 */
public class ControladorPrestamo {

	private final PrestamoDAO prestamoDAO;

	public ControladorPrestamo(PrestamoDAO dao) {
		this.prestamoDAO = dao;
	}

	public boolean guardar(Prestamo prestamo) throws SQLException {
		return this.prestamoDAO.create(prestamo);
	}

	public boolean actualizar(Prestamo prestamo) throws SQLException {
		return this.prestamoDAO.update(prestamo);
	}

	public List<Prestamo> listarPrestamos() throws SQLException {
		return this.prestamoDAO.readAll();
	}

	public boolean borrar(int id) throws SQLException {
		return this.prestamoDAO.delete(id);
	}

	public List<LibroMasPrestado> listarLibrosMasPrestados() throws SQLException {
		return this.prestamoDAO.listarLibrosMasPrestados();
	}

	public List<Prestamo> listarPrestamosPorEstudiante(int idEstudiante) throws SQLException {
		return this.prestamoDAO.listarPrestamosPorEstudiante(idEstudiante);
	}

	public List<Prestamo> listarPrestamosActuales() throws SQLException {
		return this.prestamoDAO.listarPrestamosActuales();
	}
}
