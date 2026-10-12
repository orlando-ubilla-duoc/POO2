package com.duoc.bibliotecapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL        = "jdbc:mysql://localhost:3306/biblioteca";
	private static final String USUARIO    = "root";
	private static final String CONTRASENA = "bmxkdhiu1234";

	private static DatabaseConnection instancia;
	private Connection conn;

	private DatabaseConnection() throws SQLException
	{
		this.conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
	}

	public static synchronized DatabaseConnection getInstancia() throws SQLException
	{
		if( instancia == null ){
			instancia = new DatabaseConnection();
		} else {
			// valida si es que se pierde la conexion
			if( instancia.getConn().isClosed() ){
				instancia = new DatabaseConnection();
			}
		}
		return instancia;
	}

	public Connection getConn() throws SQLException 
	{
		return conn;
	}

}
