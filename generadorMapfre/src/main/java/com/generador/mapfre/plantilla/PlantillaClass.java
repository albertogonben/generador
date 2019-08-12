package com.generador.mapfre.plantilla;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generador.mapfre.bean.Atributo;
import com.generador.mapfre.generador.Generador;
import com.generador.mapfre.util.Util;

@SuppressWarnings("rawtypes")
public class PlantillaClass {

	public static void crear(String tipo, List<Class> entities, String pathDestino) {
		switch (tipo) {
		case "C":
			crearController(entities, pathDestino);
			break;
		case "S":
			crearService(entities, pathDestino);
			break;
		case "R":
			crearRepository(entities, pathDestino);
			break;
		case "P":
			crearPostman(entities, pathDestino);
			break;
		case "B":
			crearDTO(entities, pathDestino);
			break;
		default:
			break;
		}
	}

	private static void crearController(List<Class> entities, String pathDestino) {

		entities.forEach((entity) -> {

			String nombre = Util.getNombre(entity);
			String nombreMIN = Util.getNombreMIN(entity);
			String paquete = Util.getPaquete(entity);
			String descripcion = Util.getDescripcion(entity);
			String path = Util.getPath(entity);

			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("name", nombre);
			parametros.put("nameMIN", nombreMIN);
			parametros.put("package", paquete);
			parametros.put("descripcion", descripcion);
			parametros.put("path", path);

			Generador.generar("C", parametros, pathDestino);

		});

	}

	private static void crearService(List<Class> entities, String pathDestino) {
		entities.forEach((entity) -> {

			String nombre = Util.getNombre(entity);
			String nombreMIN = Util.getNombreMIN(entity);
			String paquete = Util.getPaquete(entity);
			String descripcion = Util.getDescripcion(entity);

			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("name", nombre);
			parametros.put("nameMIN", nombreMIN);
			parametros.put("package", paquete);
			parametros.put("descripcion", descripcion);

			Generador.generar("S", parametros, pathDestino);

		});

	}

	private static void crearRepository(List<Class> entities, String pathDestino) {
		entities.forEach((entity) -> {

			String nombre = Util.getNombre(entity);
			String paquete = Util.getPaquete(entity);

			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("name", nombre);
			parametros.put("package", paquete);

			Generador.generar("R", parametros, pathDestino);

		});

	}

	private static void crearPostman(List<Class> entities, String pathDestino) {
		entities.forEach((entity) -> {

			String nombre = Util.getNombre(entity);
			String path = Util.getPath(entity);

			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("name", nombre);
			parametros.put("path", path);

			Generador.generar("P", parametros, pathDestino);

		});

	}
	
	private static void crearDTO(List<Class> entities, String pathDestino) {
		entities.forEach((entity) -> {

			String nombre = Util.getNombre(entity);
			String path = Util.getPath(entity);
			String paquete = Util.getPaquete(entity);
			List<Atributo> parameters = Util.getAtributtes(entity);
			
			Map parametros = new HashMap<String, String>();
			parametros.put("name", nombre);
			parametros.put("path", path);
			parametros.put("package", paquete);
			parametros.put("parameters", parameters);
			
			Generador.generar("B", parametros, pathDestino);

		});

	}

}
