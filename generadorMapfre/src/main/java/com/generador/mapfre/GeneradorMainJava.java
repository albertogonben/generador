package com.generador.mapfre;

import java.util.List;

import com.generador.mapfre.entrada.Input;
import com.generador.mapfre.plantilla.Plantilla;

public class GeneradorMainJava {

	private static String PATH = "src/main/resources/inputs";

	public static void start() {
		
		List<String> clases = Input.obtenerEntitiesString(PATH);
		
		if (clases.size() > 0) {
			Plantilla.crearControllerString(clases);
			Plantilla.crearServiceString(clases);
			Plantilla.crearRepositoryString(clases);
		}
		
	}
	
}
