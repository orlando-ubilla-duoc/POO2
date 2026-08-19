package com.duoc.speedfastapp.model;

/**
 * Representa un pedido de comida (restaurante).
 */
public class PedidoComida extends Pedido {


	public PedidoComida(String idPedido, String cliente, String direccionOrigen, String direccionDestino, double costoEnvio) {
		super(idPedido, cliente, direccionOrigen, direccionDestino, costoEnvio);
	}


	/**
	 * Sobreescribe metodo base: Valida mochila termica.
	 * @param repartidor
	 * @return boolean
	 */
	@Override
	public boolean asignarRepartidor(Repartidor repartidor) {

		if (repartidor == null) {
			System.out.println("Error: Repartidor no válido.");
			return false;
		}

		if( !repartidor.isTieneMochilaTermica() ){
			System.out.println("Asignación rechazada: pedido " + this.getIdPedido());
			System.out.println("Repartidor " + repartidor.getNombre() + " NO posee mochila térmica.");
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

}
