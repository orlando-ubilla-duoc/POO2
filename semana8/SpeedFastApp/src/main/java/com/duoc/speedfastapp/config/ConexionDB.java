package com.duoc.speedfastapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	private static final String URL        = "jdbc:mysql://localhost:3306/db_poo2";
	private static final String USUARIO    = "root";
	private static final String CONTRASENA = "bmxkdhiu1234";

	public ConexionDB()
	{
		// TODO
	}

	public static Connection obtenerConexion() throws SQLException 
	{
		return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
	}

}
