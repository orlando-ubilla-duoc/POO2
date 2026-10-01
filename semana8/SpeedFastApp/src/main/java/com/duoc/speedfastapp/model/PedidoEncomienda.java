package com.duoc.speedfastapp.model;

public class PedidoEncomienda extends Pedido {

	public PedidoEncomienda(int nro_pedido, String direccionEntrega, String tipoPedido, String estadoPedido){
		super(nro_pedido,direccionEntrega,tipoPedido,estadoPedido);
	}

	/**
	 * Calcula el tiempo de entrega para pedidos por encomienda
	 * @return integer	Total de minutos estimados
	 */
	/*
	@Override
	public int calcularTiempoEntrega(){

		return (int) Math.round( 20 + (1.5 * getDistanciaKm()));
	}
	*/

}
