package com.mx.springlegacy.utils;

import java.util.List;

import com.mx.springlegacy.beans.PersonaTarea;

public class Utileria {
	
	public static String printEmpleadosInfo(List<PersonaTarea> persona) {
		StringBuilder stb = new StringBuilder();
		
		for(PersonaTarea tare : persona) {
			stb.append("<br>");
			stb.append(" ");
			stb.append(tare.getNombre());
			stb.append(" ");
			stb.append(tare.getEdad());
			stb.append(" ");
			stb.append(tare.getSexo());
			stb.append(" ");
			stb.append(tare.getSueldoDiario());
			stb.append(" ");
		}
		
		return stb.toString();
	}

}
