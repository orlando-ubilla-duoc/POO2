package com.duoc.speedfastapp.model;

public class PedidoExpress extends Pedido {

	public PedidoExpress(int nro_pedido, String direccion, double distancia_km){
		super(nro_pedido,direccion,distancia_km);
	}

	/**
	 * Calcula el tiempo de entrega para pedidos express
	 * @return integer	Total de minutos estimados
	 */
	@Override
	public int calcularTiempoEntrega(){

		int tiempoBase = 10;

		if( getDistanciaKm() > 5 ){
			tiempoBase += 5;
		}

		return tiempoBase;
	}

}
