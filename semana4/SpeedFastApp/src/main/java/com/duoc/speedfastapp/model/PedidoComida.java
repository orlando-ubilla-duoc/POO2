package com.duoc.speedfastapp.model;

import com.duoc.speedfastapp.interfaces.Cancelable;
import com.duoc.speedfastapp.interfaces.Despachable;
import com.duoc.speedfastapp.interfaces.Rastreable;

public class PedidoComida extends Pedido implements Rastreable, Despachable, Cancelable {

	public PedidoComida(String nro_pedido, String direccion, double distancia_km){
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

	@Override
	public void asignarRepartidor(){
		// Asigna automaticamente un repartidor de comida
		this.setRepartidor(new Repartidor("Carlos Gomez Fuentes","15.876.543-1"));
	}

	@Override
	public void despachar(){
		this.mostrarResumen();
		this.addHistarial("Pedido de Comida #" + this.getNroPedido() + " - entregado por " + this.getRepartidor().getNombre() );
		System.out.println("-> Pedido de Comida despachado correctamente...");
	}

	@Override
	public void cancelar(){
		System.out.println("Cancelando Pedido de Comida #" + this.getNroPedido() + "...");
		System.out.println("-> Pedido de Comida cancelado exitosamente.\n");
		this.addHistarial("Pedido de Comida #" + this.getNroPedido() + " Cancelando." );
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
