package com.duoc.speedfastapp.model;

import java.util.List;

import com.duoc.speedfastapp.interfaces.Cancelable;
import com.duoc.speedfastapp.interfaces.Despachable;
import com.duoc.speedfastapp.interfaces.Rastreable;

/**
 * CLASS Pedido
 */
public abstract class Pedido implements Rastreable, Despachable, Cancelable {

	private String idPedido;
	private Repartidor repartidorAsignado;
	private List<String> historial;
	private String direccionEntrega;
	private double distanciaKm;

	public Pedido(String nro_pedido, String direccion, double distancia){
		this.idPedido         = nro_pedido;
		this.direccionEntrega = direccion;
		this.distanciaKm      = distancia;
	}

	public String getNroPedido(){ return this.idPedido; }
	public void setNroPedido(String nroPedido){ this.idPedido=nroPedido; }

	public String getDireccionEntrega(){ return this.direccionEntrega; }
	public void setDireccionEntrega(String direccion){ this.direccionEntrega=direccion; }

	public double getDistanciaKm(){ return this.distanciaKm; }
	public void setDistanciaKm(double km){ this.distanciaKm=km; }

	public Repartidor getRepartidor(){ return this.repartidorAsignado; }
	public void setRepartidor(Repartidor repartidor){ this.repartidorAsignado=repartidor; }

	public List<String> getHistorial(){ return this.historial; }
	public void addHistarial(String traza){ this.historial.add(traza); }

	/**
	 * define visualizacion del historial a este nivel,
	 * ya que la rutina es la misma para todas las clases hijas.
	 */
	@Override
	public void verHistorial(){
		System.out.println("Historial:");
		for( String historia : this.getHistorial() ){
			System.out.println("- " + historia);
		}
	}

	public void asignarRepartidor(){
		// null
	}

	public void asignarRepartidor(String nombre){
		this.repartidorAsignado = new Repartidor(nombre, "0.000.000-0");
	}

	public void mostrarResumen(){
		System.out.println(
			"Resumen '"+this.getClass().getSimpleName() + "' \n" +
			"-------------------------- \n" +
			"- Pedido #" + this.idPedido + "\n" +
			"- Direccion: " + this.direccionEntrega + "\n" +
			"- Distancia: " + this.distanciaKm + "\n" +
			"- Repartidor asignado: " + "\n" +
			"- Tiempo estimado entrega : "+this.calcularTiempoEntrega() + " minuto(s)"
		);
		System.out.println("");
	}

	public abstract int calcularTiempoEntrega();


}
