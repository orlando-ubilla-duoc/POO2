package com.duoc.bibliotecapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.duoc.bibliotecapp.config.DatabaseConnection;
import com.duoc.bibliotecapp.dao.PrestamoDAO;
import com.duoc.bibliotecapp.model.LibroMasPrestado;
import com.duoc.bibliotecapp.model.Prestamo;

public class PrestamoDAOImpl implements PrestamoDAO {

	public PrestamoDAOImpl(){}

	@Override
	public boolean create(Prestamo nuevoPrestamo) throws SQLException
	{
		String sql = "INSERT INTO prestamos(id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setInt(1, nuevoPrestamo.getIdEstudiante());
			stmt.setInt(2, nuevoPrestamo.getIdLibro());
			setFecha(stmt, 3, nuevoPrestamo.getFechaPrestamo());
			setFecha(stmt, 4, nuevoPrestamo.getFechaDevolucion());
			stmt.setBoolean(5, nuevoPrestamo.isDevuelto());

			boolean creado = stmt.executeUpdate() > 0;
			return creado;
		}
	}

	@Override
	public List<Prestamo> readAll() throws SQLException
	{
		List<Prestamo> prestamos = new ArrayList<>();
		String sql = "SELECT id, id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto FROM prestamos ORDER BY id DESC";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()
		){
			while (rs.next()) {
				Date fechaPrestamo = rs.getDate("fecha_prestamo");
				Date fechaDevolucion = rs.getDate("fecha_devolucion");
				prestamos.add(new Prestamo(
						rs.getInt("id"),
						rs.getInt("id_estudiante"),
						rs.getInt("id_libro"),
						fechaPrestamo == null ? null : fechaPrestamo.toLocalDate(),
						fechaDevolucion == null ? null : fechaDevolucion.toLocalDate(),
						rs.getBoolean("devuelto")));
			}
		}
		return prestamos;
	}

	@Override
	public boolean update(Prestamo cambiaPrestamo) throws SQLException
	{
		String sql = "UPDATE prestamos SET id_estudiante=?, id_libro=?, fecha_prestamo=?, fecha_devolucion=?, devuelto=? WHERE id=?";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setInt(1, cambiaPrestamo.getIdEstudiante());
			stmt.setInt(2, cambiaPrestamo.getIdLibro());
			setFecha(stmt, 3, cambiaPrestamo.getFechaPrestamo());
			setFecha(stmt, 4, cambiaPrestamo.getFechaDevolucion());
			stmt.setBoolean(5, cambiaPrestamo.isDevuelto());
			stmt.setInt(6, cambiaPrestamo.getId());

			boolean actualizado = stmt.executeUpdate() > 0;
			return actualizado;
		}
	}

	@Override
	public boolean delete(int idKey) throws SQLException
	{
		String sql = "DELETE FROM prestamos WHERE id=?";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setInt(1, idKey);

			boolean eliminado = stmt.executeUpdate() > 0;
			return eliminado;
		}
	}

	@Override
	public List<LibroMasPrestado> listarLibrosMasPrestados() throws SQLException
	{
		List<LibroMasPrestado> libros = new ArrayList<>();
		String sql = """
				SELECT l.id, l.titulo, l.autor, COUNT(p.id) AS cantidad_prestamos
				FROM libros l
				INNER JOIN prestamos p ON p.id_libro = l.id
				GROUP BY l.id, l.titulo, l.autor
				ORDER BY cantidad_prestamos DESC, l.titulo ASC
				""";
		Connection conn = DatabaseConnection.getInstancia().getConn();

		try (PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()
		){
			while (rs.next()) {
				libros.add(new LibroMasPrestado(
						rs.getInt("id"),
						rs.getString("titulo"),
						rs.getString("autor"),
						rs.getLong("cantidad_prestamos")));
			}
		}
		return libros;
	}

	@Override
	public List<Prestamo> listarPrestamosPorEstudiante(int idEstudiante) throws SQLException {
		String sql = """
				SELECT p.id, p.id_estudiante, p.id_libro, e.nombre AS nombre_estudiante, e.rut, e.curso,
				       l.titulo AS titulo_libro, p.fecha_prestamo, p.fecha_devolucion, p.devuelto
				FROM prestamos p
				INNER JOIN estudiantes e ON e.id = p.id_estudiante
				INNER JOIN libros l ON l.id = p.id_libro
				WHERE p.id_estudiante = ?
				ORDER BY p.fecha_prestamo DESC, p.id DESC
				""";
		Connection conn = DatabaseConnection.getInstancia().getConn();

		try (PreparedStatement stmt = conn.prepareStatement(sql) ){
			stmt.setInt(1, idEstudiante);
			try (ResultSet rs = stmt.executeQuery()) {
				return leerDetallesPrestamo(rs);
			}
		}
	}

	@Override
	public List<Prestamo> listarPrestamosActuales() throws SQLException {
		String sql = """
				SELECT p.id, p.id_estudiante, p.id_libro, e.nombre AS nombre_estudiante, e.rut, e.curso,
				       l.titulo AS titulo_libro, p.fecha_prestamo, p.fecha_devolucion, p.devuelto
				FROM prestamos p
				INNER JOIN estudiantes e ON e.id = p.id_estudiante
				INNER JOIN libros l ON l.id = p.id_libro
				WHERE p.devuelto = FALSE OR p.devuelto IS NULL
				ORDER BY p.fecha_devolucion ASC, p.id ASC
				""";
		Connection conn = DatabaseConnection.getInstancia().getConn();
		try (PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()
		){
			return leerDetallesPrestamo(rs);
		}
	}

	private List<Prestamo> leerDetallesPrestamo(ResultSet rs) throws SQLException {
		List<Prestamo> prestamos = new ArrayList<>();
		while (rs.next()) {
			Date fechaPrestamo = rs.getDate("fecha_prestamo");
			Date fechaDevolucion = rs.getDate("fecha_devolucion");
			Prestamo prestamo = new Prestamo(
					rs.getInt("id"),
					rs.getInt("id_estudiante"),
					rs.getInt("id_libro"),
					fechaPrestamo == null ? null : fechaPrestamo.toLocalDate(),
					fechaDevolucion == null ? null : fechaDevolucion.toLocalDate(),
					rs.getBoolean("devuelto"));
			prestamo.setNombreEstudiante(rs.getString("nombre_estudiante"));
			prestamo.setRutEstudiante(rs.getString("rut"));
			prestamo.setCursoEstudiante(rs.getString("curso"));
			prestamo.setTituloLibro(rs.getString("titulo_libro"));
			prestamos.add(prestamo);
		}
		return prestamos;
	}

	/**
	 * Valido mi fecha antes de enviar a la BD
	 * @param stmt
	 * @param indice
	 * @param fecha
	 * @throws SQLException
	 */
	private static void setFecha(PreparedStatement stmt, int indice, java.time.LocalDate fecha) throws SQLException
	{
		if (fecha == null) {
			stmt.setNull(indice, Types.DATE);
		} else {
			stmt.setDate(indice, Date.valueOf(fecha));
		}
	}
}
