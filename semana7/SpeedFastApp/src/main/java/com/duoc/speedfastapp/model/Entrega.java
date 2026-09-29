package com.duoc.speedfastapp.model;

import java.time.LocalTime;
import java.util.Date;

public abstract class Entrega {

	private int id;
	private int id_pedido;
	private int id_repartidor;
	private Date fecha;
	private LocalTime hora;

	public Entrega(int id_pk, int fk_pedido, int fk_repartidor, Date fecha, LocalTime hora){
		this.id            = id_pk;
		this.id_pedido     = fk_pedido;
		this.id_repartidor = fk_repartidor;
		this.fecha         = fecha;
		this.hora          = hora;
		
	}

	public int getId(){ return this.id; }
	public void setId(int id){ this.id=id; }

	public int getIdPedido(){ return this.id_pedido; }
	public void setIdPedido(int idPedido){ this.id_pedido=idPedido; }

	public int getIdRepartidor(){ return this.id_repartidor; }
	public void setIdRepartidor(int idRepartidor){ this.id_repartidor=idRepartidor; }

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