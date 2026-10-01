package com.duoc.speedfastapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.duoc.speedfastapp.model.Entrega;

public class ControladorEntrega {
	
	public ControladorEntrega(){
		//
	}

	public boolean guardar(Entrega entrega)
	{
		String sql = "INSERT INTO entrega(id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

		try (Connection conn = ConexionBaseDatos.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setInt(1, entrega.getIdPedido());
			stmt.setInt(2, entrega.getIdRepartidor());
			stmt.setDate(3, new java.sql.Date(entrega.getFecha().getTime()));
			stmt.setTime(4, java.sql.Time.valueOf(entrega.getHora()));
			stmt.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Registro agregado correctamente.");
		} catch (SQLException e){
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error al guardar el registro en la base de datos.");
			return false;
		}
		return true;
	}
}
