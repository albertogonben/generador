package com.generador.mapfre;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.generador.mapfre.entrada.Input;
import com.generador.mapfre.plantilla.Plantilla;

@SpringBootApplication
public class GeneradorMapfreApplication {

	private static String PATH = "src/main/resources/inputs";
	
	public static void main(String[] args) {
		//SpringApplication.run(GeneradorMapfreApplication.class, args);

		List<String> clases = Input.obtenerEntitiesString(PATH);
		
		Plantilla.crearControllerString(clases);
		Plantilla.crearServiceString(clases);
		Plantilla.crearRepositoryString(clases);
		
	}

}
