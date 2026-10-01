package com.duoc.speedfastapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.duoc.speedfastapp.controller.ConexionBaseDatos;
import com.duoc.speedfastapp.dao.EntregaDAO;
import com.duoc.speedfastapp.model.Entrega;

public class EntregaDAOImpl implements EntregaDAO {

	public EntregaDAOImpl()
	{
		// TODO
	}

	@Override
	public boolean create(Entrega nuevaEntrega) throws SQLException
	{
		String sql = "INSERT INTO entregas(id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";
		try (
			Connection conn = ConexionBaseDatos.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setInt(1, nuevaEntrega.getIdPedido());
			stmt.setInt(2, nuevaEntrega.getIdRepartidor());
			stmt.setDate(3, (Date) nuevaEntrega.getFecha());
			stmt.setTime(4, Time.valueOf(nuevaEntrega.getHora()));

			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro agregado correctamente.");
		} catch (SQLException e){
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error al guardar el registro en la base de datos.");
			return false;
		}
		return true;
	}

	@Override
	public List<Entrega> readAll() throws SQLException
	{
		List<Entrega> entregas = new ArrayList<>();
		String sql = "SELECT * FROM entregas ORDER BY id DESC;";

		try (
			Connection conn = ConexionBaseDatos.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		){	
			while (rs.next())
			{
				Entrega tuplaEntrega = new Entrega(
					rs.getInt("id"),
					rs.getInt("id_pedido"),
					rs.getInt("id_repartidor"),
					(Date) rs.getDate("fecha"),
					rs.getTime("hora").toLocalTime()
				);
				entregas.add(tuplaEntrega);
			}

		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error al cargar registros desde la base de datos.");
		} catch (Exception e){
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error desconocido al intentar consultar datos.");
		}

		return entregas;
	}

	@Override
	public boolean update(Entrega cambiaEntrega) throws SQLException
	{
		String sql = "UPDATE entregas SET id_pedido=?, id_repartidor=?, fecha=?, hora=? WHERE id=? ";
		try (
			Connection conn = ConexionBaseDatos.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setInt(1, cambiaEntrega.getIdPedido());
			stmt.setInt(2, cambiaEntrega.getIdRepartidor());
			stmt.setDate(3, (Date) cambiaEntrega.getFecha());
			stmt.setTime(4, Time.valueOf(cambiaEntrega.getHora()));
			stmt.setInt(5, cambiaEntrega.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
		return true;
	}

	@Override
	public boolean delete(int idKey) throws SQLException
	{
		String sql = "DELETE FROM entregas WHERE id=? ";
		try (
			Connection conn = ConexionBaseDatos.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql)
		){
			stmt.setInt(1, idKey);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
		return true;
	}

}
