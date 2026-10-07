package com.duoc.speedfastapp.model;

import java.time.LocalTime;
import java.util.Date;

public class Entrega {

	private int id;
	private int id_pedido;
	private String direccion_pedido;
	private int id_repartidor;
	private String nom_repartidor;
	private Date fecha;
	private LocalTime hora;

	public Entrega(int id_pk, int fk_pedido, int fk_repartidor, Date fecha, LocalTime hora, String direccionPedido, String nombreRepartidor){
		this.id            = id_pk;
		this.id_pedido     = fk_pedido;
		this.id_repartidor = fk_repartidor;
		this.fecha         = fecha;
		this.hora          = hora;
		this.direccion_pedido = direccionPedido;
		this.nom_repartidor = nombreRepartidor;
	}

	public int getId(){ return this.id; }
	public void setId(int id){ this.id=id; }

	public int getIdPedido(){ return this.id_pedido; }
	public void setIdPedido(int idPedido){ this.id_pedido=idPedido; }

	public String getDirPedido(){ return this.direccion_pedido; }
	public void setDirPedido(String direccionPedido){ this.direccion_pedido=direccionPedido; }

	public int getIdRepartidor(){ return this.id_repartidor; }
	public void setIdRepartidor(int idRepartidor){ this.id_repartidor=idRepartidor; }

	public String getNomRepartidor(){ return this.nom_repartidor; }
	public void setNomRepartidor(String nombreRepartidor){ this.nom_repartidor=nombreRepartidor; }

	public Date getFecha(){ return this.fecha; }
	public void setFecha(Date fecha){ this.fecha=fecha; }

	public LocalTime getHora(){ return this.hora; }
	public void setHora(LocalTime hora){ this.hora=hora; }

	@Override 
	public String toString(){
		return(
			"Clase '"+this.getClass().getSimpleName() + "' \n" +
			"-------------------------- \n" +
			"- ID-Entrega #" + this.id + "\n" +
			"- ID-Pedido #" + this.id_pedido + "\n" +
			"- ID-Repartidor #" + this.id_repartidor + "\n" +
			"- Fecha: " + this.fecha + "\n" +
			"- Hora: " + this.hora + "\n"
		);
	}

}