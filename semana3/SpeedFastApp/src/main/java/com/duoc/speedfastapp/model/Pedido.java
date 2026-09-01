package com.duoc.speedfastapp.model;

public abstract class Pedido {

	private int idPedido;
	private Repartidor repartidorAsignado;

	public void Pedido(int nro_pedido){
		this.idPedido = nro_pedido;
	}

	public void asignarRepartidor(){}
	public void asignarRepartidor(String nombre){
		//
	}

	public void mostrarResumen(){}

	public abstract void calcularTiempoEntrega();
}
