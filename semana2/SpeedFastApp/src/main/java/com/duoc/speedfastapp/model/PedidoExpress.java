package com.duoc.speedfastapp.model;

/**
 * Representa un pedido de compra express.
 */
public class PedidoExpress extends Pedido {


	public PedidoExpress(String id_pedido, String direccion_entrega, double distancia_km) {
		super(id_pedido, direccion_entrega, distancia_km);
	}


	/**
	 * Calcula el tiempo de entrega para pedidos de comida
	 * @return integer	Total de minutos estimados
	 */
	public int calcularTiempoEntrega(){

		int tiempoBase = 10;

		if( getDistanciaKm() > 5 ){
			tiempoBase += 5;
		}

		return tiempoBase;
	}


}
