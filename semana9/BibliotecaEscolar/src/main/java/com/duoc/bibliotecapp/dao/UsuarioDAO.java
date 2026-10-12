package com.duoc.bibliotecapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.duoc.bibliotecapp.model.Usuario;

public interface UsuarioDAO {

	public boolean create(Usuario nuevoUsuario) throws SQLException;
	public List<Usuario> readAll() throws SQLException;
	public boolean update(Usuario cambiaUsuario) throws SQLException;
	public boolean delete(int idKey) throws SQLException;
	public Usuario consultaUsuario(String usuario, char[] password) throws SQLException;
}
