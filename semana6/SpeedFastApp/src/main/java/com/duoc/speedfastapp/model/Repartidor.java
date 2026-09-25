package com.duoc.speedfastapp.model;

import com.duoc.speedfastapp.controller.ZonaDeCarga;

/**
 * CLASS Repartidor
 * representa repartidor de pedidos.
 */
public class Repartidor implements Runnable {

	private String nombre;
	private String rut;
	private ZonaDeCarga zonaCarga;

	public Repartidor(String nombre, String rut, ZonaDeCarga z){
		this.nombre    = nombre;
		this.rut       = rut;
		this.zonaCarga = z;
	}

	public String getNombre(){ return this.nombre; }
	public void setNombre(String nombre){ this.nombre=nombre; }

	public String getRut(){ return this.rut; }
	public void setRut(String Rut){ this.rut=Rut; }

	private synchronized void entregarPedido(Pedido p){
		System.out.println("["+this.getNombre()+"] retirando Pedido #"+p.getNroPedido()+"...");
		System.out.println("["+this.getNombre()+"] Estado: "+p.getEstado());
		p.setEstado("ENTREGADO");
		System.out.println("["+this.getNombre()+"] entregando Pedido #"+p.getNroPedido()+"...");
		System.out.println("["+this.getNombre()+"] Estado: "+p.getEstado());
		System.out.println("");
	}

	@Override
	public String toString(){
		return (
			"Class " + this.getClass().getSimpleName() + ": \n" +
			"- nombre=" + this.nombre + "\n"
		);
	}

	@Override
	public void run() {
		try {
			// Toma pedidos hasta agotar cola.
			Pedido pedidoReparto = zonaCarga.retirarPedido();
			while (pedidoReparto!=null) {
				// rutina de entrega de pedido
				entregarPedido(pedidoReparto);
				// espera antes de ir por el siguiente pedido
				Thread.sleep(350);
				// toma otro pedido, si es que quedan
				pedidoReparto = zonaCarga.retirarPedido();
			}

		} catch (InterruptedException e) {
			System.out.println("Error: Hilo interrumpido. " + e.getMessage() );
			Thread.currentThread().interrupt();
		}

	}

}