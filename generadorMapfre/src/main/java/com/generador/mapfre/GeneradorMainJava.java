package com.generador.mapfre;

import java.util.List;

import com.generador.mapfre.entrada.Input;
import com.generador.mapfre.plantilla.PlantillaString;

public class GeneradorMainJava {

	private static String PATH = "src/main/resources/inputs";
	private static String PATH_DESTINO = "src/main/resources";


	public static void start() {
		
		List<String> clases = Input.obtenerEntitiesString(PATH);
		
		if (clases.size() > 0) {
			PlantillaString.crearControllerString(clases,PATH_DESTINO);
			PlantillaString.crearServiceString(clases,PATH_DESTINO);
			PlantillaString.crearRepositoryString(clases,PATH_DESTINO);
			PlantillaString.crearPostman(clases,PATH_DESTINO);
		}
		
	}
	
}
