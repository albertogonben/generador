package com.generador.mapfre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneradorMapfreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneradorMapfreApplication.class, args);

		GeneradorJFace.start();
//        GeneradorMainJava.start();

	}

	

}
