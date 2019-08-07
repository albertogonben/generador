package com.generador.mapfre.entrada;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.eclipse.core.runtime.Path;

public class Input {

	private static String PATH_DESTINO = "src/main/resources";
	private static String PATH_LOADER = "com.mapfre.gaia.amap3.entities.";
	private static List<Class> clases = new ArrayList<Class>();

	public static List<Class> obtenerEntities(String path) {

		List<Path> sourceFiles = new ArrayList<Path>();

		try {
			File directorio = new File(path);
			sourceFiles.addAll(obtenerRutas(directorio));
			Path dest = new Path(PATH_DESTINO);
			javac(dest, sourceFiles);

			obtenerClases(directorio);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clases;
	}

	private static void obtenerClases(File path) throws Exception {

		File[] ruta = path.listFiles();

		for (File f : ruta) {
			if (f.isFile() && f.getName().contains(".java")) {

				String name = f.getName().replace(".java", "");
				Class<?> myClass = Thread.currentThread().getContextClassLoader().loadClass(PATH_LOADER + name);

				clases.add(myClass);

			} else if (f.isDirectory()) {
				obtenerClases(f);
			}
		}

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

	private static List<Path> obtenerRutas(File path) throws ClassNotFoundException, IOException {

		List<Path> sourceFiles = new ArrayList<Path>();

		File[] ruta = path.listFiles();

		for (File f : ruta) {
			if (f.isFile() && f.getName().contains(".java")) {

				sourceFiles.add(new Path(f.getPath()));

			} else if (f.isDirectory()) {
				sourceFiles.addAll(obtenerRutas(f));
			}
		}

		return sourceFiles;
	}

	static void javac(Path dest, List<Path> sourceFiles) throws IOException, ClassNotFoundException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {

			List<File> files = sourceFiles.stream().map(p -> p.toFile()).collect(Collectors.toList());

			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(files);

			List<String> optionList = new ArrayList<String>();
			optionList.addAll(Arrays.asList("-classpath", System.getProperty("java.class.path")));

			JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, optionList, null,
					compilationUnits);

			boolean passed = task.call();

			if (!passed)
				throw new RuntimeException("Error compiling " + files);

		}
	}

}
