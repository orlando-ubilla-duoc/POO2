package com.duoc.speedfastapp.model;

public class PedidoComida extends Pedido {

	public PedidoComida(int nro_pedido, String direccionEntrega, String tipoPedido, String estadoPedido){
		super(nro_pedido,direccionEntrega,tipoPedido,estadoPedido);
	}

	public PedidoComida(){
		super( 0, "NO_DIR", "COMIDA", "PENDIENTE");
	}

	/**
	 * Calcula el tiempo de entrega para pedidos de comida
	 * @return integer	Total de minutos estimados
	 */
	/*
	@Override
	public int calcularTiempoEntrega(){

		return (int) Math.round( 15 + (2 * getDistanciaKm()));
	}
	*/

}
