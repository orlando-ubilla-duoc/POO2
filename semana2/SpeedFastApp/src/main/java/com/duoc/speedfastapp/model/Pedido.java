package com.duoc.speedfastapp.model;

/**
 * Clase abstracta base que representa un Pedido genérico en SpeedFast.
 * Aplica principios de encapsulamiento y polimorfismo (clases abstractas).
 */
public abstract class Pedido {

	private String idPedido;
	private String direccionEntrega;
	private double distanciaKm;

	public Pedido() {
	}

	public Pedido(String id_pedido, String direccion_entrega, double distancia_km) {
		this.idPedido         = id_pedido;
		this.direccionEntrega = direccion_entrega;
		this.distanciaKm      = distancia_km;
	}

	public String getIdPedido() { return idPedido; }
	public void setIdPedido(String idPedido) { this.idPedido = idPedido;}

	public String getDireccionEntrega() { return direccionEntrega; }
	public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega;}

	public double getDistanciaKm() { return distanciaKm; }
	public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm;}

	public abstract int calcularTiempoEntrega();

	public void mostrarResumen(){
		System.out.println("Resumen clase "+this.getClass().getName() );
		System.out.println("===============================");
		System.out.println("nro.pedido : "+this.idPedido);
		System.out.println("direccion entrega : "+this.direccionEntrega);
		System.out.println("distancia en km : "+this.distanciaKm);
		System.out.println("tiempo estimado entrega : "+this.calcularTiempoEntrega() + " minuto(s)");
	}
}
