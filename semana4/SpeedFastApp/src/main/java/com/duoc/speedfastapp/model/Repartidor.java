package com.duoc.speedfastapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASS Repartidor
 * representa repartidor de pedidos.
 */
public class Repartidor implements Runnable {

	private String nombre;
	private String rut;
	private List<Pedido> pedidos;

	public Repartidor(String nombre, String rut){
		this.nombre = nombre;
		this.rut    = rut;
		this.pedidos = new ArrayList<Pedido>();
	}

	public String getNombre(){ return this.nombre; }
	public void setNombre(String nombre){ this.nombre=nombre; }

	public String getRut(){ return this.rut; }
	public void setRut(String Rut){ this.rut=Rut; }

	public List<Pedido> getPedidos(){ return this.pedidos; }
	public void addPedido(Pedido pedido){ this.pedidos.add(pedido);	}
	public void setPedidos(List<Pedido> listado_pedidos){ this.pedidos=listado_pedidos; }

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
			for (Pedido p : getPedidos() ) {
				System.out.println( getNombre()+" completando pedido #"+ p.getNroPedido()+", 10%");
				Thread.sleep(10);
				System.out.println( getNombre()+" completando pedido #"+ p.getNroPedido()+", 30%");
				Thread.sleep(10);
				System.out.println( getNombre()+" completando pedido #"+ p.getNroPedido()+", 60%");
				Thread.sleep(10);
				System.out.println( getNombre()+" completando pedido #"+ p.getNroPedido()+", 80%");
				Thread.sleep(10);
				System.out.println( getNombre()+" completando pedido #"+ p.getNroPedido()+", 100% (Entregado!)");
			}
		} catch (InterruptedException e) {
			System.out.println("Error: Hilo interrumpido. " + e.getMessage() );
			Thread.currentThread().interrupt();
		}

	}

}
