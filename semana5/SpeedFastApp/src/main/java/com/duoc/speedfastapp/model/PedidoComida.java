package com.duoc.speedfastapp.model;

public class PedidoComida extends Pedido {

	public PedidoComida(int nro_pedido, String direccion, double distancia_km){
		super(nro_pedido,direccion,distancia_km);
	}

	/**
	 * Calcula el tiempo de entrega para pedidos de comida
	 * @return integer	Total de minutos estimados
	 */
	@Override
	public int calcularTiempoEntrega(){

		return (int) Math.round( 15 + (2 * getDistanciaKm()));
	}

}
