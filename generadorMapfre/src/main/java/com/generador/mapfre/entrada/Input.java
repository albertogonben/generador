package com.generador.mapfre.entrada;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class Input {

	public static List<Class> obtenerEntities(String path) {

		List<Class> clases = new ArrayList<Class>();

		try {
			File directorio = new File(path);
			obtenerClases(directorio, clases);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clases;
	}

	public static List<String> obtenerEntitiesString(String path) {

		List<String> clases = new ArrayList<String>();

		try {
			File directorio = new File(path);
			obtenerClasesString(directorio, clases);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clases;
	}

	private static void obtenerClasesString(File path, List<String> clases) {
		File[] ruta = path.listFiles();

		for (File f : ruta) {
			if (f.isFile() && f.getName().contains(".java")) {

				String name = f.getName().replace(".java", "");
				clases.add(name);

			} else if (f.isDirectory()) {
				obtenerClasesString(f, clases);
			}
		}

	}

	private static void obtenerClases(File path, List<Class> clases)
			throws MalformedURLException, ClassNotFoundException {

		File[] ruta = path.listFiles();

		for (File f : ruta) {
			if (f.isFile() && f.getName().contains(".java")) {

				String name = f.getName().replace(".java", "");
				URLClassLoader classLoader = new URLClassLoader(new URL[] { path.toURI().toURL() },
						Thread.currentThread().getContextClassLoader());
				Class<?> myClass = Class.forName("" + name);

				clases.add(myClass);

			} else if (f.isDirectory()) {
				obtenerClases(f, clases);
			}
		}
	}

}
