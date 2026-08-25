package com.duoc.speedfastapp.model;

/**
 * Representa un pedido de comida.
 */
public class PedidoComida extends Pedido {


	public PedidoComida(String id_pedido, String direccion_entrega, double distancia_km) {
		super(id_pedido, direccion_entrega, distancia_km);
	}


	/**
	 * Calcula el tiempo de entrega para pedidos de comida
	 * @return integer	Total de minutos estimados
	 */
	public int calcularTiempoEntrega(){

		return (int) Math.round( 15 + (2 * getDistanciaKm()));
	}

}
