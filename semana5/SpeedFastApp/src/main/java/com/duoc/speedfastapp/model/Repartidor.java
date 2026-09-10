package com.duoc.speedfastapp.model;

import com.duoc.speedfastapp.ZonaDeCarga;

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
			// Toma un Pedido de la Cola.
			Pedido pedidoReparto = zonaCarga.retirarPedido();
			if( pedidoReparto==null ){
				return;
			}

			// Cambia estado y "se envía a repartir".
			pedidoReparto.setEstado("EN_REPARTO");
			System.out.println("["+this.getNombre()+"] retirando Pedido #"+pedidoReparto.getNroPedido()+"...");
			System.out.println("["+this.getNombre()+"] Estado: "+pedidoReparto.getEstado());

			// ...
			Thread.sleep(100); // Repartiendo en cien milisegundos, Flash!!
			// Termina reparto, cambia estado.
			pedidoReparto.setEstado("ENTREGADO");
			System.out.println("["+this.getNombre()+"] entregando Pedido #"+pedidoReparto.getNroPedido()+"...");
			System.out.println("["+this.getNombre()+"] Estado: "+pedidoReparto.getEstado());
			System.out.println("");

		} catch (InterruptedException e) {
			System.out.println("Error: Hilo interrumpido. " + e.getMessage() );
			Thread.currentThread().interrupt();
		}

	}

}