package com.duoc.speedfastapp.controller;

import java.util.ArrayList;
import java.util.List;
import com.duoc.speedfastapp.model.Pedido;

public class ControladorPedido {

	private List<Pedido> pedidos;

	public ControladorPedido()
	{
		this.pedidos = new ArrayList<>();
	}

	public void agregarPedido(Pedido pedido)
	{
		this.pedidos.add(pedido);
	}

	public List<Pedido> getPedidos()
	{
		return this.pedidos;
	}
	
}
