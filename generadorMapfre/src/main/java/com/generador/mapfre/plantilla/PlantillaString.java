package com.generador.mapfre.plantilla;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generador.mapfre.generador.Generador;

public class PlantillaString {

	private static String PACKAGE = "com.mapfre.gaia.amap3";

	public static void crearControllerString(List<String> entities, String pathDestino) {

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

			Generador.generar("C", parametros, pathDestino);

		});

	}

	private static String[] separarNombre(String nombre) {

		return nombre.split("(?=[A-Z])");

	}

	public static void crearServiceString(List<String> entities, String pathDestino) {
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

			Generador.generar("S", parametros, pathDestino);

		});

	}

	public static void crearRepositoryString(List<String> entities, String pathDestino) {
		entities.forEach((entity) -> {

			String nombre = entity;
			String paquete = PACKAGE;

			Map parametros = new HashMap();
			parametros.put("name", nombre);
			parametros.put("package", paquete);

			Generador.generar("R", parametros, pathDestino);

		});

	}

	public static void crearPostman(List<String> entities, String pathDestino) {
		entities.forEach((entity) -> {

			String nombre = entity;
			String[] nombreSeparado = separarNombre(nombre);

			String path = "";
			for (int i = 0; i < nombreSeparado.length; i++) {
				if (i == 0) {
					path += nombreSeparado[i].toLowerCase();
				} else {
					path += "-" + nombreSeparado[i].toLowerCase();
				}
			}

			Map parametros = new HashMap();
			parametros.put("name", nombre);
			parametros.put("path", path);

			Generador.generar("P", parametros, pathDestino);

		});

	}

}
