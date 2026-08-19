package com.duoc.speedfastapp.model;

/**
 * Clase abstracta base que representa un Pedido genérico en SpeedFast.
 * Aplica principios de encapsulamiento y polimorfismo (sobrecarga de métodos).
 */
public abstract class Pedido {

	private String idPedido;
	private String cliente;
	private String direccionOrigen;
	private String direccionDestino;
	private double costoEnvio;
	private Repartidor repartidorAsignado;
	private String estado; // "PENDIENTE", "ASIGNADO", "EN_CAMINO", "ENTREGADO"

	public Pedido() {
		this.estado = "PENDIENTE";
	}

	public Pedido(String idPedido, String cliente, String direccionOrigen, String direccionDestino, double costoEnvio) {
		this.idPedido         = idPedido;
		this.cliente          = cliente;
		this.direccionOrigen  = direccionOrigen;
		this.direccionDestino = direccionDestino;
		this.costoEnvio       = costoEnvio;
		this.estado           = "PENDIENTE";
	}

	/**
	 * @param repartidor Objeto Repartidor a asignar.
	 * @return true si la asignación fue exitosa, false en caso contrario.
	 */
	public boolean asignarRepartidor(Repartidor repartidor){

		if( repartidor == null ){
			System.err.println("Error: No se puede asignar un repartidor nulo al pedido " + idPedido);
			return false;
		}
		this.repartidorAsignado = repartidor;
		this.estado             = "ASIGNADO";
		System.out.println("Repartidor " + repartidor.getNombre() + " asignado al pedido " + idPedido);
		return true;
	}

	public String getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDireccionOrigen() {
		return direccionOrigen;
	}

	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}

	public String getDireccionDestino() {
		return direccionDestino;
	}

	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	public double getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public Repartidor getRepartidorAsignado() {
		return repartidorAsignado;
	}

	public void setRepartidorAsignado(Repartidor repartidorAsignado) {
		this.repartidorAsignado = repartidorAsignado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
