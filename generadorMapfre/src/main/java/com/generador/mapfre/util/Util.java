package com.generador.mapfre.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.generador.mapfre.bean.Atributo;

public class Util {

	private static String PACKAGE = "com.mapfre.gaia.amap3";

	public static String getNombre(Class entity) {
		
		return entity.getSimpleName();

	}

	public static String getNombreMIN(Class entity) {
		return entity.getSimpleName().substring(0, 1).toLowerCase() + entity.getSimpleName().substring(1);
	}

	public static String getPaquete(Class entity) {
		return PACKAGE;
	}

	public static String getDescripcion(Class entity) {
		String[] nombreSeparado = separarNombre(entity.getSimpleName());
		String descripcion = "";
		for (int i = 0; i < nombreSeparado.length; i++) {
			if (i == 0) {
				descripcion += nombreSeparado[i].toLowerCase();
			} else {
				descripcion += " " + nombreSeparado[i].toLowerCase();
			}
		}
		return descripcion;
	}

	public static String getBO(Class entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getPath(Class entity) {
		String[] nombreSeparado = separarNombre(entity.getSimpleName());
		String path = "";
		for (int i = 0; i < nombreSeparado.length; i++) {
			if (i == 0) {
				path += nombreSeparado[i].toLowerCase();
			} else {
				path += "-" + nombreSeparado[i].toLowerCase();
			}
		}
		return path;
	}

	public static String getId(Class entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Atributo> getAtributtes(Class entity) {
		List<Atributo> atributtes = new ArrayList<Atributo>();
		Field[] atributos = entity.getDeclaredFields();
		for (Field atr : atributos) {
			atributtes.add(new Atributo(Modifier.toString(atr.getModifiers()), atr.getGenericType().getTypeName(),
					atr.getName()));
		}
		
		return atributtes;
	}

	private static String[] separarNombre(String nombre) {

		return nombre.split("(?=[A-Z])");

	}

}
