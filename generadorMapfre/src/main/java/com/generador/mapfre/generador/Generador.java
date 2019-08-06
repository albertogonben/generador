package com.generador.mapfre.generador;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class Generador {

	private static String PACKAGE = "com/mapfre/gaia/amap3/";

	private static String PLANTILLA_CONTROLLER = "Controller.ftlh";
	private static String PLANTILLA_ICONTROLLER = "IController.ftlh";

	private static String PLANTILLA_SERVICE = "Service.ftlh";
	private static String PLANTILLA_ISERVICE = "IService.ftlh";

	private static String PLANTILLA_IREPOSITORY = "Repository.ftlh";

	private static String PLANTILLA_POSTMAN = "postman.ftlh";

	public static String PATH_DESTINO = "";
	
	public static void generar(String tipo, Map parametros, String pathDestino) {

		PATH_DESTINO = pathDestino;
		
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
			case "P":
				generarPostman(cfg, parametros);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void generarPostman(Configuration cfg, Map parametros) throws Exception {
		
		new File(PATH_DESTINO+"/postman").mkdir();

//		Postman
		Template tempIRepository = cfg.getTemplate(PLANTILLA_POSTMAN);

		File newFile = new File(
				PATH_DESTINO+"/postman/" + parametros.get("name") + "-postman.json");
		newFile.createNewFile();
		Writer file = new FileWriter(newFile);
		tempIRepository.process(parametros, file);

	}

	private static void generarRepository(Configuration cfg, Map parametros) throws Exception {

		new File(PATH_DESTINO+"/Repository").mkdir();

//		IRepository
		Template tempIRepository = cfg.getTemplate(PLANTILLA_IREPOSITORY);
//		Writer outIRepository = new OutputStreamWriter(System.out);
//		tempIRepository.process(parametros, outIRepository);		

		File newFile = new File(
				PATH_DESTINO+"/Repository/" + parametros.get("name") + "Repository.java");
		newFile.createNewFile();
		Writer file = new FileWriter(newFile);
		tempIRepository.process(parametros, file);
	}

	private static void generarService(Configuration cfg, Map parametros) throws Exception {

		new File(PATH_DESTINO+"/Iservice").mkdir();
		new File(PATH_DESTINO+"/service").mkdir();

//		IService
		Template tempIService = cfg.getTemplate(PLANTILLA_ISERVICE);
//		Writer outIService = new OutputStreamWriter(System.out);
//		tempIService.process(parametros, outIService);

		File newFile = new File(PATH_DESTINO+"/Iservice/I" + parametros.get("name") + "BL.java");
		newFile.createNewFile();
		Writer file = new FileWriter(newFile);
		tempIService.process(parametros, file);

//		Service
		Template tempService = cfg.getTemplate(PLANTILLA_SERVICE);
//		Writer outService = new OutputStreamWriter(System.out);
//		tempService.process(parametros, outService);

		newFile = new File(PATH_DESTINO+"/service/" + parametros.get("name") + "BLImpl.java");
		newFile.createNewFile();
		file = new FileWriter(newFile);
		tempService.process(parametros, file);

	}

	private static void generarController(Configuration cfg, Map parametros) throws Exception {

		new File(PATH_DESTINO+"/Icontroller").mkdir();
		new File(PATH_DESTINO+"/controller").mkdir();

//		IControler
		Template tempIControler = cfg.getTemplate(PLANTILLA_ICONTROLLER);
//		Writer outIControler = new OutputStreamWriter(System.out);
//		tempIControler.process(parametros, outIControler);

		File newFile = new File(
				PATH_DESTINO+"/Icontroller/I" + parametros.get("name") + "Controller.java");
		newFile.createNewFile();
		Writer file = new FileWriter(newFile);
		tempIControler.process(parametros, file);

//		Controller
		Template tempController = cfg.getTemplate(PLANTILLA_CONTROLLER);
//		Writer outController = new OutputStreamWriter(System.out);
//		tempController.process(parametros, outController);

		newFile = new File(PATH_DESTINO+"/controller/" + parametros.get("name") + "Controller.java");
		newFile.createNewFile();
		file = new FileWriter(newFile);
		tempController.process(parametros, file);

	}

}
