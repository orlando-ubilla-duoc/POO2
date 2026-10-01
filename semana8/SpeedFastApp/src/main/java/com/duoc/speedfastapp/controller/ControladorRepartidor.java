package com.duoc.speedfastapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.duoc.speedfastapp.model.Repartidor;

public class ControladorRepartidor {

	public ControladorRepartidor(){
		//
	}

	public void guardar(Repartidor repartidor)
	{
		String sql = "INSERT INTO repartidor(nombre) VALUES (?)";
		try (Connection conn = ConexionBaseDatos.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, repartidor.getNombre());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro agregado correctamente.");
		} catch (SQLException e){
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error al guardar el registro en la base de datos.");
		}
	}

	public List<Repartidor> listarTodos()
	{
		List<Repartidor> repartidores = new ArrayList<>();
		String sql = "SELECT * FROM repartidor ORDER BY id DESC;";

		try ( Connection conn = ConexionBaseDatos.conectar();
			  PreparedStatement stmt = conn.prepareStatement(sql);
			  ResultSet rs = stmt.executeQuery())
		{

			while (rs.next())
			{
				Repartidor tuplaRepartidor = new Repartidor( rs.getInt("id"), rs.getString("nombre"), "");
				repartidores.add(tuplaRepartidor);
			}

		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error al cargar registros desde la base de datos.");
		} catch (Exception e){
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error desconocido al intentar consultar datos.");
		}

		return repartidores;
	}
	
}
