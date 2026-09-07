package com.duoc.speedfastapp.model;

public class PedidoExpress extends Pedido {

	public PedidoExpress(String nro_pedido, String direccion, double distancia_km){
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

	@Override
	public void asignarRepartidor(){
		// Asigna automaticamente un repartidor express
		this.setRepartidor(new Repartidor("Juan Morales Silva","16.543.892-K"));
	}

	@Override
	public void despachar(){
		this.mostrarResumen();
		this.addHistarial("Pedido Express #" + this.getNroPedido() + " - entregado por " + this.getRepartidor().getNombre() );
		System.out.println("-> Pedido Express despachado correctamente...");
	}

	@Override
	public void cancelar(){
		System.out.println("Cancelando Pedido Express #" + this.getNroPedido() + "...");
		System.out.println("-> Pedido Express cancelado exitosamente.\n");
		this.addHistarial("Pedido Express #" + this.getNroPedido() + " Cancelando." );
	}
}
