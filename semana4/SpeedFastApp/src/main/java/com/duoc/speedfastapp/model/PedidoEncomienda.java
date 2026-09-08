package com.duoc.speedfastapp.model;

import com.duoc.speedfastapp.interfaces.Cancelable;
import com.duoc.speedfastapp.interfaces.Despachable;
import com.duoc.speedfastapp.interfaces.Rastreable;

public class PedidoEncomienda extends Pedido implements Rastreable, Despachable, Cancelable {

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
		System.out.println("-> Pedido por Encomienda cancelado exitosamente.\n");
		this.addHistarial("Pedido por Encomienda #" + this.getNroPedido() + " Cancelando." );
	}

	/**
	 * define visualizacion del historial a este nivel,
	 * ya que la rutina es la misma para todas las clases hijas.
	 */
	@Override
	public void verHistorial(){
		System.out.println("Historial:");
		for( String historia : this.getHistorial() ){
			System.out.println("- " + historia);
		}
		System.out.println("\n \n");
	}

}
