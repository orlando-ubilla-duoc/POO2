package com.duoc.speedfastapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.duoc.speedfastapp.model.Pedido;
import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.PedidoExpress;


public class ControladorPedido {

	//private List<Pedido> pedidos;
	//private Connection conn;

	public ControladorPedido()
	{
		//this.pedidos = new ArrayList<>();

		/*try {
			this.conn = ConexionBaseDatos.conectar();
			System.out.println("Conexión exitosa a la base de datos.");
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos:");
			e.printStackTrace();
		} finally {
			//
		}
		*/
	}

	public void guardar(Pedido pedido)
	{
		String sql = "INSERT INTO pedido(direccion, tipo, estado) VALUES (?, ?, ?)";
		try (Connection conn = ConexionBaseDatos.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, pedido.getDireccionEntrega());
			stmt.setString(2, pedido.getTipo());
			stmt.setString(3, pedido.getEstado());

			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro agregado correctamente.");
		} catch (SQLException e){
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error al guardar el registro en la base de datos.");
		}
	}

	/**
	 * Recuperar todos los registros de Pedidos
	 * @param limitLast Limite de ultimos registros a recuperar. Cero para ignorar.
	 * @return List Pedido
	 */
	public List<Pedido> listarPedidos(int limitLast)
	{
		List<Pedido> pedidos = new ArrayList<>();
		String sql = "SELECT * FROM pedido ORDER BY id DESC;";

		try (Connection conn = ConexionBaseDatos.conectar()){

			if( limitLast>0 ) sql = "SELECT * FROM pedido LIMIT ? ORDER BY id DESC;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			if( limitLast>0 ) stmt.setInt(1, limitLast);
			ResultSet rs = stmt.executeQuery();

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
				System.out.println("Tipo="+rs.getString("tipo"));
				pedidos.add(tuplaPedido);
			}

			try { rs.close(); } catch (SQLException e) { System.err.print(e); }
			try { stmt.close(); } catch (SQLException e) { System.err.print(e); }

		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error al cargar registros desde la base de datos.");
		} catch (Exception e){
			System.err.print(e);
			JOptionPane.showMessageDialog(null, "Error desconocido al intentar consultar datos.");
		}

		return pedidos;
	}
	
}
