package com.duoc.bibliotecapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.model.LibroMasPrestado;
import com.duoc.bibliotecapp.model.Prestamo;

public interface PrestamoDAO {

	public boolean create(Prestamo nuevoPrestamo) throws SQLException;
	public List<Prestamo> readAll() throws SQLException;
	public boolean update(Prestamo cambiaPrestamo) throws SQLException;
	public boolean delete(int idKey) throws SQLException;
	public List<LibroMasPrestado> listarLibrosMasPrestados() throws SQLException;
	public List<Prestamo> listarPrestamosPorEstudiante(int idEstudiante) throws SQLException;
	public List<Prestamo> listarPrestamosActuales() throws SQLException;
}
