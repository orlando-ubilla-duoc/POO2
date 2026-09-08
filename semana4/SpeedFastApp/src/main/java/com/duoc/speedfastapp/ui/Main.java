package com.duoc.speedfastapp.ui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.PedidoExpress;
import com.duoc.speedfastapp.model.Repartidor;

public class Main {

	public static void main(String[] args){

		System.out.println("=================================================");
		System.out.println(" SPEEDFAST ");
		System.out.println("=================================================\n");

		Repartidor repartidor1 = new Repartidor("Carlos Gomez Fuentes","15.876.543-1");
		repartidor1.addPedido(new PedidoComida("1001","Av. Providencia 123", 4.5));
		repartidor1.addPedido(new PedidoEncomienda("1002","Calle Los Leones 456", 7.0));

		Repartidor repartidor2 = new Repartidor("Matias Castro Vargas","17.654.321-3");
		repartidor2.addPedido(new PedidoExpress("1003","Calle Suecia 789", 1.5));
		repartidor2.addPedido(new PedidoComida("1004","Av. Irrarazaval 1234", 8.1));

		Repartidor repartidor3 = new Repartidor("Juan Morales Silva","16.543.892-K");
		repartidor3.addPedido(new PedidoEncomienda("1005","Av.Manuel Montt 801", 6.2));
		repartidor3.addPedido(new PedidoExpress("1006","Calle Las Gardenias 1058", 3.6));

		ExecutorService executor = Executors.newFixedThreadPool(4);
		executor.execute(repartidor1);
		executor.execute(repartidor2);
		executor.execute(repartidor3);

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		executor.shutdownNow();

		System.out.println("\n=================================================");
		System.out.println(" FIN ACTIVIDAD");
		System.out.println("=================================================");
	}
}
