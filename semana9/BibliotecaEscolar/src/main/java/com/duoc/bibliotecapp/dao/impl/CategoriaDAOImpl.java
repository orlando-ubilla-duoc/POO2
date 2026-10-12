package com.duoc.bibliotecapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duoc.bibliotecapp.config.DatabaseConnection;
import com.duoc.bibliotecapp.dao.CategoriaDAO;
import com.duoc.bibliotecapp.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

	public CategoriaDAOImpl(){}

	@Override
	public boolean create(Categoria nuevaCategoria) throws SQLException
	{
		String sql = "INSERT INTO categorias(nombre) VALUES (?)";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setString(1, nuevaCategoria.getNombre());

			boolean creada = stmt.executeUpdate() > 0;
			return creada;
		}
	}

	@Override
	public List<Categoria> readAll() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT id, nombre FROM categorias ORDER BY id DESC";
		try (Connection conn = DatabaseConnection.getInstancia().getConn();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				categorias.add(new Categoria(rs.getInt("id"), rs.getString("nombre")));
			}
		}
		return categorias;
	}

	@Override
	public boolean update(Categoria cambiaCategoria) throws SQLException
	{
		String sql = "UPDATE categorias SET nombre=? WHERE id=?";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setString(1, cambiaCategoria.getNombre());
			stmt.setInt(2, cambiaCategoria.getId());

			boolean actualizada = stmt.executeUpdate() > 0;
			return actualizada;
		}
	}

	@Override
	public boolean delete(int idKey) throws SQLException
	{
		String sql = "DELETE FROM categorias WHERE id=?";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setInt(1, idKey);

			boolean eliminada = stmt.executeUpdate() > 0;
			return eliminada;
		}
	}
}
