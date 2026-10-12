package com.duoc.bibliotecapp.model;

import java.time.LocalDate;

public class Prestamo {

	private int id;
	private int idEstudiante;
	private int idLibro;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	private boolean devuelto;
	private String nombreEstudiante;
	private String rutEstudiante;
	private String cursoEstudiante;
	private String tituloLibro;

	public Prestamo() {
	}

	public Prestamo(int id, int idEstudiante, int idLibro, LocalDate fechaPrestamo, LocalDate fechaDevolucion, boolean devuelto){
		this.id              = id;
		this.idEstudiante    = idEstudiante;
		this.idLibro         = idLibro;
		this.fechaPrestamo   = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.devuelto        = devuelto;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getIdEstudiante() {
		return idEstudiante;
	}
	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public boolean isDevuelto() {
		return devuelto;
	}
	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}

	public String getNombreEstudiante() {
		return nombreEstudiante;
	}
	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}

	public String getRutEstudiante() {
		return rutEstudiante;
	}
	public void setRutEstudiante(String rutEstudiante) {
		this.rutEstudiante = rutEstudiante;
	}

	public String getCursoEstudiante() {
		return cursoEstudiante;
	}
	public void setCursoEstudiante(String cursoEstudiante) {
		this.cursoEstudiante = cursoEstudiante;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}
	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}
}
