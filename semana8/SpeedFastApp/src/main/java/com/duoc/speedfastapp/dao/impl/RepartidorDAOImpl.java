package com.duoc.speedfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.duoc.speedfastapp.config.ConexionDB;
import com.duoc.speedfastapp.dao.RepartidorDAO;
import com.duoc.speedfastapp.model.Repartidor;

public class RepartidorDAOImpl implements RepartidorDAO {

	public RepartidorDAOImpl()
	{
		// vacio.-
	}

	@Override
	public boolean create(Repartidor nuevoRepartidor) throws SQLException
	{
		String sql = "INSERT INTO repartidores(nombre) VALUES (?)";
		try (
			Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1, nuevoRepartidor.getNombre());
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
	public List<Repartidor> readAll() throws SQLException
	{
		List<Repartidor> repartidores = new ArrayList<>();
		String sql = "SELECT * FROM repartidores ORDER BY id DESC;";

		try (
			Connection conn = ConexionDB.obtenerConexion();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		){	
			while (rs.next())
			{
				Repartidor tuplaRepartidor = new Repartidor(
					rs.getInt("id"),
					rs.getString("nombre"),
					""
				);
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

	@Override
	public boolean update(Repartidor cambiaRepartidor) throws SQLException
	{
		String sql = "UPDATE repartidores SET nombre=? WHERE id=? ";
		try (
			Connection conn = ConexionDB.obtenerConexion();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1, cambiaRepartidor.getNombre());
			stmt.setInt(2, cambiaRepartidor.getId());
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
		String sql = "DELETE FROM repartidores WHERE id=? ";
		try (
			Connection conn = ConexionDB.obtenerConexion();
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
