package com.generador.mapfre.generador;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class Generador {

	private static String PACKAGE = "com/mapfre/gaia/amap3/";

	private static String PLANTILLA_CONTROLLER = "Controller.ftlh";
	private static String PLANTILLA_ICONTROLLER = "IController.ftlh";

	private static String PLANTILLA_SERVICE = "Service.ftlh";
	private static String PLANTILLA_ISERVICE = "IService.ftlh";

	private static String PLANTILLA_IREPOSITORY = "Repository.ftlh";

	public static void generar(String tipo, Map parametros) {

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
		try {
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);

			switch (tipo) {
			case "C":
				generarController(cfg, parametros);
				break;
			case "S":
				generarService(cfg, parametros);
				break;
			case "R":
				generarRepository(cfg, parametros);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void generarRepository(Configuration cfg, Map parametros) throws Exception {
//		IRepository
		Template tempIRepository = cfg.getTemplate(PLANTILLA_IREPOSITORY);
		Writer outIRepository = new OutputStreamWriter(System.out);
		tempIRepository.process(parametros, outIRepository);		
	}

	private static void generarService(Configuration cfg, Map parametros) throws Exception {
//		IService
		Template tempIService = cfg.getTemplate(PLANTILLA_ISERVICE);
		Writer outIService = new OutputStreamWriter(System.out);
		tempIService.process(parametros, outIService);

//		Service
		Template tempService = cfg.getTemplate(PLANTILLA_SERVICE);
		Writer outService = new OutputStreamWriter(System.out);
		tempService.process(parametros, outService);

	}

	private static void generarController(Configuration cfg, Map parametros) throws Exception {

//		IControler
		Template tempIControler = cfg.getTemplate(PLANTILLA_ICONTROLLER);
		Writer outIControler = new OutputStreamWriter(System.out);
		tempIControler.process(parametros, outIControler);

//		Controller
		Template tempController = cfg.getTemplate(PLANTILLA_CONTROLLER);
		Writer outController = new OutputStreamWriter(System.out);
		tempController.process(parametros, outController);

	}

}
