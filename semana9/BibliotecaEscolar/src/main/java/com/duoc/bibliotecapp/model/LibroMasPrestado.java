package com.duoc.bibliotecapp.model;

public class LibroMasPrestado {

	private final int idLibro;
	private final String titulo;
	private final String autor;
	private final long cantidadPrestamos;

	public LibroMasPrestado(int idLibro, String titulo, String autor, long cantidadPrestamos) {
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.cantidadPrestamos = cantidadPrestamos;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public long getCantidadPrestamos() {
		return cantidadPrestamos;
	}
}
