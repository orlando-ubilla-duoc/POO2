package com.duoc.bibliotecapp.model;

public enum Rol {
	BIBLIOTECARIO,
	ESTUDIANTE;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}