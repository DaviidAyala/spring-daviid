package com.mx.springlegacy.beans;

import java.util.List;

public class PersonaTarea {
	
	private String nombre;
	private Double sueldoDiario;
	private int edad;
	private String sexo;
	private List<Persona> subordinados;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getSueldoDiario() {
		return sueldoDiario;
	}
	public void setSueldoDiario(Double sueldoDiario) {
		this.sueldoDiario = sueldoDiario;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public List<Persona> getSubordinados() {
		return subordinados;
	}
	public void setSubordinados(List<Persona> subordinados) {
		this.subordinados = subordinados;
	}
	
}
