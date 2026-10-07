package com.duoc.speedfastapp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @deprecated 
 * ConexionBaseDatos
 */
public class ConexionBaseDatos {

	private static final String URL        = "jdbc:mysql://localhost:3306/db_poo2";
	private static final String USUARIO    = "root";
	private static final String CONTRASENA = "bmxkdhiu1234";

	public static Connection conectar() throws SQLException 
	{
		return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
	}

}
