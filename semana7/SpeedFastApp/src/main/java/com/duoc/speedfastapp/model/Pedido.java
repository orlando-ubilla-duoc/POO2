package com.duoc.speedfastapp.model;

public abstract class Pedido {

	private int id;
	private String direccionEntrega;
	private String estado;
	private String tipo;

	public Pedido(int nro_pedido, String direccionEntrega, String tipoPedido, String estadoPedido){
		this.id               = nro_pedido;
		this.direccionEntrega = direccionEntrega;
		this.tipo             = tipoPedido;
		this.estado           = estadoPedido;
		
	}

	public int getNroPedido(){ return this.id; }
	public void setNroPedido(int nroPedido){ this.id=nroPedido; }

	public String getDireccionEntrega(){ return this.direccionEntrega; }
	public void setDireccionEntrega(String direccion){ this.direccionEntrega=direccion; }

	public String getEstado(){ return this.estado; }
	public void setEstado(String nuevoEstado){ this.estado=nuevoEstado; }

	public String getTipo(){ return this.tipo; }
	public void setTipo(String nuevo){ this.tipo=nuevo; }

	//public abstract int calcularTiempoEntrega();

	@Override 
	public String toString(){
		return(
			"Clase '"+this.getClass().getSimpleName() + "' \n" +
			"-------------------------- \n" +
			"- ID-Pedido #" + this.id + "\n" +
			"- Direccion: " + this.direccionEntrega + "\n" +
			"- Tipo: " + this.tipo + "\n" +
			"- Estado: " + this.estado + "\n"
		);
	}

}
