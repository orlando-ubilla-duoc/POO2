package com.duoc.speedfastapp.model;

public class PedidoEncomienda extends Pedido {

	public PedidoEncomienda(int nro_pedido, String direccion, double distancia_km){
		super(nro_pedido,direccion,distancia_km);
	}

	/**
	 * Calcula el tiempo de entrega para pedidos por encomienda
	 * @return integer	Total de minutos estimados
	 */
	@Override
	public int calcularTiempoEntrega(){

		return (int) Math.round( 20 + (1.5 * getDistanciaKm()));
	}

}
