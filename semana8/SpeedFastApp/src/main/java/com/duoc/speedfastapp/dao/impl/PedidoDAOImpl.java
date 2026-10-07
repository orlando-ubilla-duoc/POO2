package com.duoc.speedfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.duoc.speedfastapp.config.ConexionDB;
import com.duoc.speedfastapp.dao.PedidoDAO;
import com.duoc.speedfastapp.model.Pedido;
import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.PedidoExpress;

public class PedidoDAOImpl implements PedidoDAO {

	public PedidoDAOImpl()
	{
		// vacio.-
	}

	@Override
	public boolean create(Pedido nuevoPedido) throws SQLException
	{
		String sql = "INSERT INTO pedidos(direccion, tipo, estado) VALUES (?, ?, ?)";
		try (
			Connection conn = ConexionDB.obtenerConexion();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1, nuevoPedido.getDireccionEntrega());
			stmt.setString(2, nuevoPedido.getTipo());
			stmt.setString(3, nuevoPedido.getEstado());

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
	public List<Pedido> readAll() throws SQLException
	{
		List<Pedido> pedidos = new ArrayList<>();
		String sql = "SELECT * FROM pedidos ORDER BY id DESC;";

		try (
			Connection conn = ConexionDB.obtenerConexion();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		){	
			while (rs.next())
			{
				Pedido tuplaPedido;

				switch (rs.getString("tipo")) {
					case "COMIDA":
						tuplaPedido = new PedidoComida(
							rs.getInt("id"),
							rs.getString("direccion"),
							rs.getString("tipo"),
							rs.getString("estado")
						);
						break;
					case "ENCOMIENDA":
						tuplaPedido = new PedidoEncomienda(
							rs.getInt("id"),
							rs.getString("direccion"),
							rs.getString("tipo"),
							rs.getString("estado")
						);
						break;
					case "EXPRESS":
						tuplaPedido = new PedidoExpress(
							rs.getInt("id"),
							rs.getString("direccion"),
							rs.getString("tipo"),
							rs.getString("estado")
						);
						break;
					default:
						tuplaPedido=new PedidoComida();
				}
				//System.out.println("Tipo="+rs.getString("tipo"));
				pedidos.add(tuplaPedido);
			}

		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error al cargar registros desde la base de datos.");
		} catch (Exception e){
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error desconocido al intentar consultar datos.");
		}

		return pedidos;
	}

	@Override
	public boolean update(Pedido cambiaPedido) throws SQLException
	{
		String sql = "UPDATE pedidos SET direccion=?, tipo=?, estado=? WHERE id=? ";
		try (
			Connection conn = ConexionDB.obtenerConexion();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1, cambiaPedido.getDireccionEntrega());
			stmt.setString(2, cambiaPedido.getTipo());
			stmt.setString(3, cambiaPedido.getEstado());
			stmt.setInt(4, cambiaPedido.getNroPedido());
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
		String sql = "DELETE FROM pedidos WHERE id=? ";
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
