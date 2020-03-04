package com.mx.springlegacy.beans;

import java.util.List;

public class Empresa {
	
	private String nombre;
	private Persona ceo;
	private List<Persona> empleados;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Persona getCeo() {
		return ceo;
	}
	public void setCeo(Persona ceo) {
		this.ceo = ceo;
	}
	public List<Persona> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Persona> empleados) {
		this.empleados = empleados;
	}
	
	
}
