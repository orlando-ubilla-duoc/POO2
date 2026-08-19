package com.duoc.speedfastapp.model;

/**
 * Clase que representa a un repartidor de la empresa SpeedFast.
 * Almacena atributos necesarios para validar las asignaciones de pedidos.
 */
public class Repartidor {

	private String rut;
	private String nombre;
	private boolean disponible;
	private boolean tieneMochilaTermica;
	private double distanciaAlClienteKm;
	private double capacidadCargaKg;
	private boolean tieneEmbalaje;

	public Repartidor() {
	}

	public Repartidor(String rut, String nombre, boolean tieneMochilaTermica, boolean disponibleInmediato, double distanciaAlClienteKm, double capacidadKg, boolean tieneEmbalaje) {
		this.rut                  = rut;
		this.nombre               = nombre;
		this.disponible           = true;
		this.tieneMochilaTermica  = tieneMochilaTermica;
		this.distanciaAlClienteKm = distanciaAlClienteKm;
		this.capacidadCargaKg     = capacidadKg;
		this.tieneEmbalaje        = tieneEmbalaje;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isTieneMochilaTermica() {
		return tieneMochilaTermica;
	}

	public void setTieneMochilaTermica(boolean tieneMochilaTermica) {
		this.tieneMochilaTermica = tieneMochilaTermica;
	}

	public double getDistanciaAlClienteKm() {
		return distanciaAlClienteKm;
	}

	public void setDistanciaAlClienteKm(double distanciaAlClienteKm) {
		this.distanciaAlClienteKm = distanciaAlClienteKm;
	}

	public double getCapacidadKg() {
		return capacidadCargaKg;
	}

	public void setCapacidadKg(double capacidadKg) {
		this.capacidadCargaKg = capacidadKg;
	}

	public boolean isDisponible(){
		return this.disponible;
	}

	public boolean isEmbalaje() {
		return tieneEmbalaje;
	}

	public void setEmbalaje(boolean tieneEmbalaje) {
		this.tieneEmbalaje = tieneEmbalaje;
	}

	@Override
	public String toString() {
		return "Repartidor{ " +
				"Rut='" + this.rut + '\'' +
				", nombre='" + this.nombre + '\'' +
				", mochilaTermica=" + this.tieneMochilaTermica +
				", disponible=" + this.disponible +
				", distancia=" + this.distanciaAlClienteKm + " km" +
				", capMax=" + this.capacidadCargaKg + " KG" +
				", tiene-embalaje=" + this.tieneEmbalaje +
				" }";
	}
}
