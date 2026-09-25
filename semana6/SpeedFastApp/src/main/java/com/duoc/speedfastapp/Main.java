package com.duoc.speedfastapp;

import com.duoc.speedfastapp.view.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> {
			VentanaPrincipal programa = new VentanaPrincipal( "SpeedFast v1.1");
		});
		/*
		// Instancia clase para Zona de Carga
		ZonaDeCarga _zonaDeCarga = new ZonaDeCarga();
		// Agrega pool de Pedidos
		_zonaDeCarga.agregarPedido(new PedidoComida(1001,"Av. Providencia 123", 4.5));
		_zonaDeCarga.agregarPedido(new PedidoEncomienda(1002,"Calle Los Leones 456", 7.0));
		_zonaDeCarga.agregarPedido(new PedidoExpress(1003,"Calle Suecia 789", 1.5));
		_zonaDeCarga.agregarPedido(new PedidoEncomienda(1005,"Av.Manuel Montt 801", 6.2));
		_zonaDeCarga.agregarPedido(new PedidoComida(1004,"Av. Irrarazaval 1234", 8.1));
		_zonaDeCarga.agregarPedido(new PedidoExpress(1006,"Calle Las Gardenias 1058", 3.6));
		_zonaDeCarga.agregarPedido(new PedidoComida(1007,"Av. Libertad 1058", 40.2));
		System.out.println("");

		// Crea Repartidores
		Repartidor repartidor1 = new Repartidor("Carlos Gomez Fuentes","15.876.543-1", _zonaDeCarga);
		Repartidor repartidor2 = new Repartidor("Matias Castro Vargas","17.654.321-3", _zonaDeCarga);
		Repartidor repartidor3 = new Repartidor("Juan Morales Silva","16.543.892-K", _zonaDeCarga);

		// Ejecuta hilos
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(repartidor1);
		executor.execute(repartidor2);
		executor.execute(repartidor3);

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		executor.shutdownNow();
		System.out.println("Todos los pedidos han sido entregados correctamente.");
		*/
	}
}