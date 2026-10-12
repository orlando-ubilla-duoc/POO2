package com.duoc.bibliotecapp.model;

public class Usuario {

	private int id;
	private String nombre;
	private String rut;
	private String correo;
	private String contrasena;
	private Rol rol;

	public Usuario() {
		this.id     = 0;
		this.nombre = "";
		this.rut    = "";
		this.correo = "";
		this.rol    = Rol.ESTUDIANTE;
		this.contrasena = "";
	}

	public Usuario(int id, String nombre, String rut, String correo, String contrasena, Rol rol) {
		this.id     = id;
		this.nombre = nombre;
		this.rut    = rut;
		this.correo = correo;
		this.rol    = rol;
		this.contrasena = contrasena;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	public String getRut() {return rut;}
	public void setRut(String rut) {this.rut = rut;}

	public String getCorreo() {return correo;}
	public void setCorreo(String correo) {this.correo = correo;}

	public String getContrasena() {return contrasena;}
	public void setContrasena(String contrasena) {this.contrasena = contrasena;}

	public Rol getRol() {return rol;}
	public void setRol(Rol rol) {this.rol = rol;}
}
