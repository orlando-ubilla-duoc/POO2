package com.duoc.bibliotecapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duoc.bibliotecapp.config.DatabaseConnection;
import com.duoc.bibliotecapp.dao.UsuarioDAO;
import com.duoc.bibliotecapp.model.Rol;
import com.duoc.bibliotecapp.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	public UsuarioDAOImpl(){}

	@Override
	public boolean create(Usuario nuevoUsuario) throws SQLException
	{
		String sql = "INSERT INTO usuarios(nombre, rut, correo, `contraseña`, rol) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setString(1, nuevoUsuario.getNombre());
			stmt.setString(2, nuevoUsuario.getRut());
			stmt.setString(3, nuevoUsuario.getCorreo());
			stmt.setString(4, nuevoUsuario.getContrasena());
			stmt.setString(5, nuevoUsuario.getRol().toString());

			boolean creado = stmt.executeUpdate() > 0;
			return creado;
		}
	}

	@Override
	public List<Usuario> readAll() throws SQLException
	{
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT id, nombre, rut, correo, `contraseña`, rol FROM usuarios ORDER BY id DESC";

		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()
		){
			while (rs.next()) {

				Rol rol= Rol.ESTUDIANTE; // estudiante por defecto
				if (rs.getString("rol").equals(Rol.BIBLIOTECARIO.toString())) rol=Rol.BIBLIOTECARIO;
				//if (rs.getString("rol").equals(Rol.ESTUDIANTE.toString())) rol=Rol.ESTUDIANTE;

				usuarios.add(new Usuario(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("rut"),
						rs.getString("correo"),
						rs.getString("contraseña"),
						rol
				));
			}
		}
		return usuarios;
	}

	@Override
	public boolean update(Usuario cambiaUsuario) throws SQLException
	{
		String sql = "UPDATE usuarios SET nombre=?, rut=?, correo=?, `contraseña`=?, rol=? WHERE id=?";
		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setString(1, cambiaUsuario.getNombre());
			stmt.setString(2, cambiaUsuario.getRut());
			stmt.setString(3, cambiaUsuario.getCorreo());
			stmt.setString(4, cambiaUsuario.getContrasena());
			stmt.setString(5, cambiaUsuario.getRol().toString());
			stmt.setInt(6, cambiaUsuario.getId());

			boolean actualizado = stmt.executeUpdate() > 0;
			return actualizado;
		}
	}

	@Override
	public Usuario getByRutEmail(String rut, String email) throws SQLException
	{
		String sql = "SELECT * FROM usuarios WHERE rut = ? AND correo = ?";
		Usuario u = null;
		try(Connection conexion = DatabaseConnection.getInstancia().getConn();
			PreparedStatement ps = conexion.prepareStatement(sql);
		){
			ps.setString(1, rut);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ){
				Rol rol= Rol.ESTUDIANTE; // estudiante por defecto
				if (rs.getString("rol").equals(Rol.BIBLIOTECARIO.toString())) rol=Rol.BIBLIOTECARIO;
				u = new Usuario(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("rut"),
					rs.getString("correo"),
					rs.getString("contraseña"),
					rol
				);
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
		return u;
	}

	@Override
	public boolean delete(int idKey) throws SQLException
	{
		String sql = "DELETE FROM usuarios WHERE id=?";
		try (Connection conn = DatabaseConnection.getInstancia().getConn();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setInt(1, idKey);

			boolean eliminado = stmt.executeUpdate() > 0;
			return eliminado;
		}
	}

	@Override
	public Usuario consultaUsuario(String usuario, char[] password) throws SQLException
	{
		String sql = "SELECT * FROM usuarios WHERE rut = ? AND contraseña = ?";
		Usuario u = null;
		try(Connection conexion = DatabaseConnection.getInstancia().getConn();
			PreparedStatement ps = conexion.prepareStatement(sql);
		){
			ps.setString(1, usuario);
			ps.setString(2, new String(password));
			ResultSet rs = ps.executeQuery();
			if( rs.next() ){
				Rol rol= Rol.ESTUDIANTE; // estudiante por defecto
				if (rs.getString("rol").equals(Rol.BIBLIOTECARIO.toString())) rol=Rol.BIBLIOTECARIO;
				u = new Usuario(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("rut"),
					rs.getString("correo"),
					rs.getString("contraseña"),
					rol
				);
			}
		} catch (Exception e) {
			//System.err.println(e);
			throw new SQLException(e.getMessage());
		}
		return u;
	}
}
