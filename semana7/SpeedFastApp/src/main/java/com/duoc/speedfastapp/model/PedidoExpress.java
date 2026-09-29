package com.duoc.speedfastapp.model;

public class PedidoExpress extends Pedido {

	public PedidoExpress(int nro_pedido, String direccionEntrega, String tipoPedido, String estadoPedido){
		super(nro_pedido,direccionEntrega,tipoPedido,estadoPedido);
	}

	/**
	 * Calcula el tiempo de entrega para pedidos express
	 * @return integer	Total de minutos estimados
	 */
	/*
	@Override
	public int calcularTiempoEntrega(){

		int tiempoBase = 10;

		if( getDistanciaKm() > 5 ){
			tiempoBase += 5;
		}

		return tiempoBase;
	}
	*/

}
