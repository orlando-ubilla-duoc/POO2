package com.duoc.speedfastapp.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inicializador {

	public Inicializador()
	{
		// TODO
	}

	public static void inicializar() throws SQLException
	{
		String sql_init_tabla = """
				CREATE TABLE IF NOT EXIST pedidos (
					id INT AUTO_INCREMENT PRIMARY KEY,
					direccion VARCHAR(150) NOT NULL,
					tipo VARCHAR(30) NOT NULL,
					estado VARCHAR(20) NOT NULL
				)
				""";;
		try(
			Connection conexion = ConexionDB.obtenerConexion();
			Statement stm = conexion.createStatement();
		) {
			stm.execute(sql_init_tabla);
		} catch (Exception e) {
			//
		}
	}

	public static boolean existTabla(String tableName)
	{
		String sql = "SELECT COUNT(*) FROM "+tableName;
		try(
			Connection conexion = ConexionDB.obtenerConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			return rs.next() && rs.getInt(1)==0;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

}
