package com.duoc.speedfastapp.ui;

import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.PedidoExpress;

public class Main {

	public static void main(String[] args){

		System.out.println("=================================================");
		System.out.println(" SPEEDFAST ");
		System.out.println("=================================================\n");


		// Crear Pedidos de distintos tipos

		System.out.println("\n");
		PedidoComida pedidoComida = new PedidoComida("P-COM-01","Av. Providencia 123", 4.5);
		pedidoComida.mostrarResumen();

		System.out.println("\n");
		PedidoEncomienda pedidoEncomienda = new PedidoEncomienda("P-ENC-01","Calle Los Leones 456", 7.0);
		pedidoEncomienda.mostrarResumen();

		System.out.println("\n");
		PedidoExpress pedidoExpress = new PedidoExpress("P-EXP-01","Calle Suecia 789", 1.5);
		pedidoExpress.mostrarResumen();

		pedidoExpress = new PedidoExpress("P-EXP-02","Av.Irarrazaval 1075", 6.5);
		pedidoExpress.mostrarResumen();

		System.out.println("\n=================================================");
		System.out.println(" FIN ACTIVIDAD");
		System.out.println("=================================================");
	}
}
