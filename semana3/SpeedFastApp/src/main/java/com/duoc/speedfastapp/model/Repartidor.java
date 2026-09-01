package com.duoc.speedfastapp.model;

/**
 * CLASS Repartidor
 * representa repartidor de pedidos.
 */
public class Repartidor {

	private String nombre;

	public Repartidor(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){ return this.nombre; }
	public void setNombre(String nombre){ this.nombre=nombre; }

	public String toString(){
		return (
			"Class " + this.getClass().getSimpleName() + ": \n" +
			"- nombre=" + this.nombre + "\n"
		);
	}

}
