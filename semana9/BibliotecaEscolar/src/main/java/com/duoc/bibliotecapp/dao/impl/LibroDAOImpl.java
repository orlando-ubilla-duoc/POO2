package com.duoc.bibliotecapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duoc.bibliotecapp.config.DatabaseConnection;
import com.duoc.bibliotecapp.dao.LibroDAO;
import com.duoc.bibliotecapp.model.Libro;

public class LibroDAOImpl implements LibroDAO {

	public LibroDAOImpl(){}

	@Override
	public boolean create(Libro nuevoLibro) throws SQLException
	{
		String sql = "INSERT INTO libros(titulo, autor, isbn, editorial, stock, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setString(1, nuevoLibro.getTitulo());
			stmt.setString(2, nuevoLibro.getAutor());
			stmt.setString(3, nuevoLibro.getIsbn());
			stmt.setString(4, nuevoLibro.getEditorial());
			stmt.setInt(5, nuevoLibro.getStock());
			stmt.setInt(6, nuevoLibro.getIdCategoria());

			boolean creado = stmt.executeUpdate() > 0;
			return creado;
		}
	}

	@Override
	public List<Libro> readAll() throws SQLException
	{
		List<Libro> libros = new ArrayList<>();
		String sql = "SELECT l.id, l.titulo, l.autor, l.isbn, l.editorial, l.stock, l.id_categoria, c.nombre FROM libros l, categorias c WHERE l.id_categoria=c.id ORDER BY id DESC";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()
		){
			while (rs.next()) {
				Libro item = new Libro(
					rs.getInt("id"),
					rs.getString("titulo"),
					rs.getString("autor"),
					rs.getString("isbn"),
					rs.getString("editorial"),
					rs.getInt("stock"),
					rs.getInt("id_categoria")
				);
				item.setNombreCategoria( rs.getString("nombre"));
				libros.add(item);
			}
		}
		return libros;
	}

	@Override
	public boolean update(Libro cambiaLibro) throws SQLException
	{
		String sql = "UPDATE libros SET titulo=?, autor=?, isbn=?, editorial=?, stock=?, id_categoria=? WHERE id=?";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setString(1, cambiaLibro.getTitulo());
			stmt.setString(2, cambiaLibro.getAutor());
			stmt.setString(3, cambiaLibro.getIsbn());
			stmt.setString(4, cambiaLibro.getEditorial());
			stmt.setInt(5, cambiaLibro.getStock());
			stmt.setInt(6, cambiaLibro.getIdCategoria());
			stmt.setInt(7, cambiaLibro.getId());

			boolean actualizado = stmt.executeUpdate() > 0;
			return actualizado;
		}
	}

	@Override
	public boolean delete(int idKey) throws SQLException
	{
		String sql = "DELETE FROM libros WHERE id=?";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setInt(1, idKey);

			boolean eliminado = stmt.executeUpdate() > 0;
			return eliminado;
		}
	}
}
