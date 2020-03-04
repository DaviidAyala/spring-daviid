package com.mx.springlegacy.utils;

import com.mx.springlegacy.beans.Empresa;

public class Utileria {
	
	public static String printEmpleadosInfo(Empresa empresa) {
		StringBuilder stb = new StringBuilder();
		
		stb.append("El nombre del CEO ");
		stb.append(empresa.getCeo().getNombre());
		stb.append(" ");
		stb.append(empresa.getEmpleados());
		
		return stb.toString();
	}
	
}
