package com.duoc.speedfastapp.model;

/**
 * Representa un pedido de compra express (supermercado o farmacia).
 */
public class PedidoCompraExpress extends Pedido {

	private double distanciaMinima;

	public PedidoCompraExpress(String idPedido, String cliente, String direccionOrigen, String direccionDestino, double costoEnvio, double distanciaMinima) {
		super(idPedido, cliente, direccionOrigen, direccionDestino, costoEnvio);
		this.distanciaMinima = distanciaMinima;
	}

	public double getDistanciaMinima() {
		return distanciaMinima;
	}

	public void setDistanciaMinima(double distancia) {
		this.distanciaMinima = distancia;
	}


	/**
	 * Sobreescribe metodo base: Valida disponibilidad y distancia.
	 * @param repartidor
	 * @return boolean
	 */
	@Override
	public boolean asignarRepartidor(Repartidor repartidor) {

		if (repartidor == null) {
			System.out.println("Error: Repartidor no válido.");
			return false;
		}

		if (!repartidor.isDisponible()) {
			System.out.println("Asignación rechazada: pedido " + this.getIdPedido());
			System.out.println("Repartidor " + repartidor.getNombre() + " NO posee disponibilidad inmediata.");
			return false;
		}

		if (repartidor.getDistanciaAlClienteKm() > this.distanciaMinima ) {
			System.out.println("Asignación rechazada: pedido " + this.getIdPedido());
			System.out.println("Repartidor " + repartidor.getNombre() + " se encuentra más lejos que lo requerido "+this.distanciaMinima+" km (" + repartidor.getDistanciaAlClienteKm() + ").");
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
