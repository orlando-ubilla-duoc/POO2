package com.duoc.speedfastapp.ui;

import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoCompraExpress;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.Repartidor;

public class Main {

	public static void main(String[] args){

		System.out.println("=================================================");
		System.out.println(" SPEEDFAST ");
		System.out.println("=================================================\n");

		// Repartidores distintos
		Repartidor r1 = new Repartidor("19726150-1", "Carlos Perez", true, true, 2.5, 10.0, false);
		Repartidor r2 = new Repartidor("12364886-2", "Ana Castillo", false, true, 1.2, 5.0, false);
		Repartidor r3 = new Repartidor("16844773-6", "Pedro Munoz", false, false, 0.8, 30.0, true);

		System.out.println("--- REPARTIDORES DISPONIBLES ---");
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		System.out.println();

		// Crear Pedidos de distintos tipos

		System.out.println("\n--- PROBANDO REPARTIDORES EN PEDIDO DE COMIDA ---");
		PedidoComida pedidoComida = new PedidoComida("P-COM-01", "Orlando", "Restaurante Sushi", "Av. Providencia 123", 3500);
		pedidoComida.asignarRepartidor(r1);
		pedidoComida.asignarRepartidor(r2);
		pedidoComida.asignarRepartidor(r3);

		System.out.println("\n--- PROBANDO REPARTIDORES EN PEDIDO ENCOMIENDA ---");
		PedidoEncomienda pedidoEncomienda = new PedidoEncomienda("P-ENC-01", "María", "Oficina Central", "Calle Los Leones 456", 5000, 15.0);
		pedidoEncomienda.asignarRepartidor(r1);
		pedidoEncomienda.asignarRepartidor(r2);
		pedidoEncomienda.asignarRepartidor(r3);

		System.out.println("\n--- PROBANDO REPARTIDORES EN PEDIDO COMPRA EXPRESS ---");
		PedidoCompraExpress pedidoExpress = new PedidoCompraExpress("P-EXP-01", "Miguel", "Farmacia Ahumada", "Calle Suecia 789", 2500, 1.5);
		pedidoExpress.asignarRepartidor(r1);
		pedidoExpress.asignarRepartidor(r2);
		pedidoExpress.asignarRepartidor(r3);

		System.out.println("\n=================================================");
		System.out.println(" FIN ACTIVIDAD");
		System.out.println("=================================================");
	}
}
