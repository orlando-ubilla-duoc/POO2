package com.duoc.speedfastapp.model;

/**
 * Representa un pedido de encomienda.
 */
public class PedidoEncomienda extends Pedido {


	public PedidoEncomienda(String id_pedido, String direccion_entrega, double distancia_km) {
		super(id_pedido, direccion_entrega, distancia_km);
	}


	/**
	 * Calcula el tiempo de entrega para pedidos de comida
	 * @return integer	Total de minutos estimados
	 */
	@Override
	public int calcularTiempoEntrega(){

		return (int) Math.round( 20 + (1.5 * getDistanciaKm()));
	}


}
