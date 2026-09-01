package com.duoc.speedfastapp.model;

/**
 * CLASS Repartidor
 * representa repartidor de pedidos.
 */
public class Repartidor {

	private String nombre;
	private String rut;

	public Repartidor(String nombre, String rut){
		this.nombre = nombre;
		this.rut = rut;
	}

	public String getNombre(){ return this.nombre; }
	public void setNombre(String nombre){ this.nombre=nombre; }

	public String getRut(){ return this.rut; }
	public void setRut(String Rut){ this.rut=Rut; }

	@Override
	public String toString(){
		return (
			"Class " + this.getClass().getSimpleName() + ": \n" +
			"- nombre=" + this.nombre + "\n"
		);
	}

}
