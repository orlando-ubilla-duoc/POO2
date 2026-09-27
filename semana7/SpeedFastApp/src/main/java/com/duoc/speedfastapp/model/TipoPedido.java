package com.duoc.speedfastapp.model;

public enum TipoPedido {
	COMIDA,
	ENCOMIENDA,
	EXPRESS;
	
	@Override
	public String toString() {
		// Permite mostrar con mayúscula inicial
		String texto = name().toLowerCase();
		return texto.substring(0, 1).toUpperCase() + texto.substring(1);
	}
}