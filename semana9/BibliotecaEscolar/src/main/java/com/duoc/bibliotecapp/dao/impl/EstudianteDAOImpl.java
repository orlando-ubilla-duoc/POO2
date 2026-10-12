package com.duoc.bibliotecapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duoc.bibliotecapp.config.DatabaseConnection;
import com.duoc.bibliotecapp.dao.EstudianteDAO;
import com.duoc.bibliotecapp.model.Estudiante;

public class EstudianteDAOImpl implements EstudianteDAO {

	public EstudianteDAOImpl(){}

	@Override
	public boolean create(Estudiante nuevoEstudiante) throws SQLException
	{
		String sql = "INSERT INTO estudiantes(nombre, rut, curso, correo) VALUES (?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setString(1, nuevoEstudiante.getNombre());
			stmt.setString(2, nuevoEstudiante.getRut());
			stmt.setString(3, nuevoEstudiante.getCurso());
			stmt.setString(4, nuevoEstudiante.getCorreo());

			boolean creado = stmt.executeUpdate() > 0;
			return creado;
		}
	}

	@Override
	public List<Estudiante> readAll() throws SQLException
	{
		List<Estudiante> estudiantes = new ArrayList<>();
		String sql = "SELECT id, nombre, rut, curso, correo FROM estudiantes ORDER BY id DESC";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()
		){
			while (rs.next()) {
				estudiantes.add(new Estudiante(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("rut"),
						rs.getString("curso"),
						rs.getString("correo")));
			}
		}
		return estudiantes;
	}

	@Override
	public boolean update(Estudiante cambiaEstudiante) throws SQLException
	{
		String sql = "UPDATE estudiantes SET nombre=?, rut=?, curso=?, correo=? WHERE id=?";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setString(1, cambiaEstudiante.getNombre());
			stmt.setString(2, cambiaEstudiante.getRut());
			stmt.setString(3, cambiaEstudiante.getCurso());
			stmt.setString(4, cambiaEstudiante.getCorreo());
			stmt.setInt(5, cambiaEstudiante.getId());

			boolean actualizado = stmt.executeUpdate() > 0;
			return actualizado;
		}
	}

	@Override
	public boolean delete(int idKey) throws SQLException
	{
		String sql = "DELETE FROM estudiantes WHERE id=?";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setInt(1, idKey);

			boolean eliminado = stmt.executeUpdate() > 0;
			return eliminado;
		}
	}
}
