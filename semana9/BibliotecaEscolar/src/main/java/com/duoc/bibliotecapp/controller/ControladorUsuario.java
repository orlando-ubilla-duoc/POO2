package com.duoc.bibliotecapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.dao.UsuarioDAO;
import com.duoc.bibliotecapp.model.Usuario;

/**
 * Maneja las operaciones de acceso a usuarios.
 */
public class ControladorUsuario {

	private final UsuarioDAO usuarioDAO;

	public ControladorUsuario(UsuarioDAO dao) {
		this.usuarioDAO = dao;
	}

	public boolean guardar(Usuario usuario) throws SQLException {
		return this.usuarioDAO.create(usuario);
	}

	public boolean actualizar(Usuario usuario) throws SQLException {
		return this.usuarioDAO.update(usuario);
	}

	public List<Usuario> listarUsuarios() throws SQLException {
		return this.usuarioDAO.readAll();
	}

	public boolean borrar(int id) throws SQLException {
		return this.usuarioDAO.delete(id);
	}

	public Usuario validarUsuario(String usuario, char[] password) throws Exception
	{
		if( usuario.isEmpty() || password.length == 0 ){
			throw new Exception("Debe completar todos los datos de acceso");
		}

		try {
			return this.usuarioDAO.consultaUsuario( usuario, password);
		} catch (SQLException e) {
			System.err.println(e);
			throw new Exception(e.getMessage());
		}
	}

}
