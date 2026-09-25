package com.duoc.speedfastapp.controller;

import com.duoc.speedfastapp.model;
import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

	List<Pedido> colaPedidos;

	public ZonaDeCarga()
	{
		this.colaPedidos = new ArrayList<Pedido>();
	}

	/**
	 * Agrega un pedido a la cola.
	 * @param p	Pedido
	 */
	public synchronized void agregarPedido(Pedido p){
		System.out.println("Pedido #"+p.getNroPedido()+" agregado. Destino="+p.getDireccionEntrega()+"("+p.getDistanciaKm()+" Kms)");
		this.colaPedidos.add(p);
	}

	/**
	 * Retira pedido para enviar a Reparto
	 * @return Pedido
	 */
	public synchronized Pedido retirarPedido(){
		for (Pedido p: this.colaPedidos ) {
			if(p.getEstado().equals("PENDIENTE")){
				p.setEstado("EN_REPARTO");
				return p;
			}
		}
		return null;
	}

}