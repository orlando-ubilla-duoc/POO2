package com.duoc.speedfastapp.ui;

import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.PedidoExpress;

public class Main {

	public static void main(String[] args){

		System.out.println("=================================================");
		System.out.println(" SPEEDFAST ");
		System.out.println("=================================================\n");

		PedidoComida pedidoComida = new PedidoComida("1001","Av. Providencia 123", 4.5);
		PedidoEncomienda pedidoEncomienda = new PedidoEncomienda("1002","Calle Los Leones 456", 7.0);
		PedidoExpress pedidoExpress = new PedidoExpress("1003","Calle Suecia 789", 1.5);

		// Pedido-Comida
		pedidoComida.asignarRepartidor("Juan Araya M.");
		pedidoComida.mostrarResumen();
		pedidoComida.despachar();
		pedidoComida.cancelar();
		pedidoComida.verHistorial();

		// Pedido-Encomienda
		pedidoEncomienda.asignarRepartidor();
		pedidoEncomienda.mostrarResumen();
		pedidoEncomienda.despachar();
		pedidoEncomienda.cancelar();
		pedidoEncomienda.verHistorial();

		// Pedido-Express
		pedidoExpress.asignarRepartidor();
		pedidoExpress.mostrarResumen();
		pedidoExpress.despachar();
		pedidoExpress.cancelar();
		pedidoExpress.verHistorial();
		

		System.out.println("\n=================================================");
		System.out.println(" FIN ACTIVIDAD");
		System.out.println("=================================================");
	}
}
