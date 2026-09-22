package com.duoc.speedfastapp.model;

public abstract class Pedido {

	private int id;
	private String direccionEntrega;
	private double distanciaKm;
	private String estado;

	public Pedido(int nro_pedido, String direccion, double distancia){
		this.id               = nro_pedido;
		this.direccionEntrega = direccion;
		this.distanciaKm      = distancia;
		this.estado           = "PENDIENTE";
	}

	public int getNroPedido(){ return this.id; }
	public void setNroPedido(int nroPedido){ this.id=nroPedido; }

	public String getDireccionEntrega(){ return this.direccionEntrega; }
	public void setDireccionEntrega(String direccion){ this.direccionEntrega=direccion; }

	public double getDistanciaKm(){ return this.distanciaKm; }
	public void setDistanciaKm(double km){ this.distanciaKm=km; }

	public String getEstado(){ return this.estado; }
	public void setEstado(String nuevoEstado){ this.estado=nuevoEstado; }

	public abstract int calcularTiempoEntrega();

	@Override 
	public String toString(){
		return(
			"Clase '"+this.getClass().getSimpleName() + "' \n" +
			"-------------------------- \n" +
			"- ID-Pedido #" + this.id + "\n" +
			"- Direccion: " + this.direccionEntrega + "\n" +
			"- Distancia: " + this.distanciaKm + "\n" +
			"- Estado: " + this.estado + "\n"
		);
	}

}
