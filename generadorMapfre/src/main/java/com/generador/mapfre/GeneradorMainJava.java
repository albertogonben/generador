package com.generador.mapfre;

import java.util.List;

import com.generador.mapfre.entrada.Input;
import com.generador.mapfre.plantilla.Plantilla;

public class GeneradorMainJava {

	private static String PATH = "src/main/resources/inputs";
	private static String PATH_DESTINO = "src/main/resources";


	public static void start() {
		
		List<String> clases = Input.obtenerEntitiesString(PATH);
		
		if (clases.size() > 0) {
			Plantilla.crearControllerString(clases,PATH_DESTINO);
			Plantilla.crearServiceString(clases,PATH_DESTINO);
			Plantilla.crearRepositoryString(clases,PATH_DESTINO);
			Plantilla.crearPostman(clases,PATH_DESTINO);
		}
		
	}
	
}
