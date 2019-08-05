package com.generador.mapfre.plantilla;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generador.mapfre.generador.Generador;
import com.generador.mapfre.util.Util;

public class Plantilla {

	private static String PACKAGE = "com.mapfre.gaia.amap3";

	public static void crearController(List<Class> entities) {

		entities.forEach((entity) -> {

			String nombre = Util.getNombre(entity);
			String nombreMIN = Util.getNombreMIN(entity);
			String paquete = Util.getPaquete(entity);
			String descripcion = Util.getDescripcion(entity);
			String path = Util.getPath(entity);

			Map parametros = new HashMap();
			parametros.put("name", nombre);
			parametros.put("nombreMIN", nombreMIN);
			parametros.put("package", paquete);
			parametros.put("descripcion", descripcion);
			parametros.put("path", path);

			Generador.generar("C", parametros);

		});

	}

	public static void crearControllerString(List<String> entities) {

		entities.forEach((entity) -> {

			String nombre = entity;
			String nombreMIN = nombre.substring(0, 1).toLowerCase() + nombre.substring(1);
			String paquete = PACKAGE;
			String[] nombreSeparado = separarNombre(nombre);

			String descripcion = "";
			String path = "";
			for (int i = 0; i < nombreSeparado.length; i++) {
				if (i == 0) {
					descripcion += nombreSeparado[i].toLowerCase();
					path += nombreSeparado[i].toLowerCase();
				} else {
					descripcion += " " + nombreSeparado[i].toLowerCase();
					path += "-" + nombreSeparado[i].toLowerCase();
				}
			}

			Map parametros = new HashMap();
			parametros.put("name", nombre);
			parametros.put("nameMIN", nombreMIN);
			parametros.put("package", paquete);
			parametros.put("descripcion", descripcion);
			parametros.put("path", path);

			Generador.generar("C", parametros);

		});

	}

	private static String[] separarNombre(String nombre) {

		return nombre.split("(?=[A-Z])");

	}

	public static void crearServiceString(List<String> entities) {
		entities.forEach((entity) -> {

			String nombre = entity;
			String nombreMIN = nombre.substring(0, 1).toLowerCase() + nombre.substring(1);
			String paquete = PACKAGE;
			String[] nombreSeparado = separarNombre(nombre);

			String descripcion = "";
			for (int i = 0; i < nombreSeparado.length; i++) {
				if (i == 0) {
					descripcion += nombreSeparado[i].toLowerCase();
				} else {
					descripcion += " " + nombreSeparado[i].toLowerCase();
				}
			}

			Map parametros = new HashMap();
			parametros.put("name", nombre);
			parametros.put("nameMIN", nombreMIN);
			parametros.put("package", paquete);
			parametros.put("descripcion", descripcion);

			Generador.generar("S", parametros);

		});

	}

	public static void crearRepositoryString(List<String> entities) {
		entities.forEach((entity) -> {

			String nombre = entity;
			String paquete = PACKAGE;

			Map parametros = new HashMap();
			parametros.put("name", nombre);
			parametros.put("package", paquete);

			Generador.generar("R", parametros);

		});

	}

}
