package com.duoc.speedfastapp;

import com.duoc.speedfastapp.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

	List<Pedido> colaPedidos;

	public ZonaDeCarga()
	{
		this.colaPedidos = new ArrayList<Pedido>();
	}

	public void agregarPedido(Pedido p){
		System.out.println("Pedido #"+p.getNroPedido()+" agregado. Destino="+p.getDireccionEntrega()+"("+p.getDistanciaKm()+" Kms)");
		this.colaPedidos.add(p);
	}

	public Pedido retirarPedido(){
		// ***
		// CAMBIAR ESTA LOGICA, SE DEBE IR ENTREGANDO
		// ORDENADAMENTE Y SIN REPETIR, LOS PEDIDOS A DESPACHAR.
		// ***
		for (Pedido p: this.colaPedidos ) {
			if(p.getEstado().equals("PENDIENTE")){
				return p;
			}
		}
		return null;
	}

}