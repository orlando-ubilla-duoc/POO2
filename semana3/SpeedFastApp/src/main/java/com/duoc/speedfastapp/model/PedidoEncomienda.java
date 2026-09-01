package com.duoc.speedfastapp.model;

public class PedidoEncomienda extends Pedido {

	public PedidoEncomienda(String nro_pedido, String direccion, double distancia_km){
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

	@Override
	public void asignarRepartidor(){
		// Asigna automaticamente un repartidor de encomiendas
		this.setRepartidor(new Repartidor("Matias Castro Vargas","17.654.321-3"));
	}

	@Override
	public void despachar(){
		this.mostrarResumen();
		this.addHistarial("Pedido por Encomienda #" + this.getNroPedido() + " - entregado por " + this.getRepartidor().getNombre() );
		System.out.println("-> Pedido por Encomienda despachado correctamente...");
	}

	@Override
	public void cancelar(){
		System.out.println("Cancelando Pedido por Encomienda #" + this.getNroPedido() + "...");
		System.out.println("-> Pedido por Encomienda cancelado exitosamente.");
		this.addHistarial("Pedido por Encomienda #" + this.getNroPedido() + " Cancelando." );
	}
}
