package com.duoc.speedfastapp.model;

/**
 * CLASS Repartidor
 * representa repartidor de pedidos.
 */
public class Repartidor implements Runnable {

	private int id;
	private String nombre;
	private String rut;

	public Repartidor(int pk, String nombre, String rut){
		this.id        = pk;
		this.nombre    = nombre;
		this.rut       = rut;
	}

	public int getId(){ return this.id; }
	public void setId(int pk){ this.id=pk; }

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
		/*
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
		*/

	}

}
