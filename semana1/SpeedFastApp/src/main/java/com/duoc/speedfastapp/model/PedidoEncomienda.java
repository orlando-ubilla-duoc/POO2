package com.duoc.speedfastapp.model;

/**
 * Representa un pedido de encomienda (documentos o paquetes).
 * Criterio específico de asignación: requiere validación de peso y que el embalaje esté verificado.
 */
public class PedidoEncomienda extends Pedido {

	private double pesoPaqueteKg;
	

	public PedidoEncomienda() {
		super();
	}

	public PedidoEncomienda(String idPedido, String cliente, String direccionOrigen, String direccionDestino, double costoEnvio, double pesoKg) {
		super(idPedido, cliente, direccionOrigen, direccionDestino, costoEnvio);
		this.pesoPaqueteKg = pesoKg;
	}


	/**
	 * Sobreescribe metodo base: Valida peso y embalaje.
	 * @param repartidor
	 * @return boolean
	 */
	@Override
	public boolean asignarRepartidor(Repartidor repartidor) {

		if (repartidor == null) {
			System.out.println("Error: Repartidor no válido.");
			return false;
		}

		if (!repartidor.isEmbalaje() ) {
			System.out.println("Asignación rechazada: pedido " + this.getIdPedido());
			System.out.println("Repartidor " + repartidor.getNombre() + " NO posee embalaje de paquetes.");
			return false;
		}

		if( this.pesoPaqueteKg > repartidor.getCapacidadKg() ){
			System.out.println("Asignación rechazada: pedido " + this.getIdPedido());
			System.out.println("Repartidor " + repartidor.getNombre() + " no tiene capacidad para transportar paquete de "+this.pesoPaqueteKg+" Kg. (" + repartidor.getCapacidadKg() + ").");
			return false;
		}

		return super.asignarRepartidor(repartidor);
	}


	/**
	 * Sobrecarga metodo base.
	 * @param repartidor
	 * @param nombreRepartidor
	 * @return boolean
	 */
	public boolean asignarRepartidor(Repartidor repartidor, String nombreRepartidor) {

		if (repartidor == null) {
			System.out.println("Error: Repartidor no válido.");
			return false;
		}

		repartidor.setNombre(nombreRepartidor);

		return this.asignarRepartidor(repartidor);
	}


	public double getPesoKg() {
		return pesoPaqueteKg;
	}

	public void setPesoKg(double pesoKg) {
		this.pesoPaqueteKg = pesoKg;
	}


}
