package com.generador.mapfre;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import com.generador.mapfre.entrada.Input;
import com.generador.mapfre.plantilla.PlantillaClass;

public class Test {
	
	private static String PATH = "src/main/resources/inputs";
	private static String PATH_DESTINO = "src/main/resources";
	
	public static void main(String[] args) throws CoreException {

//		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("Test");
////		IFolder folder = configureProjectWithXtext(project);
////		IFolder moduleFolder = createFolder(folder, TestFiles.moduleFolder());
//
////		IFile file2 = createTestFile(moduleFolder, "Class1", TestFiles.class1());
//
//		if (!project.exists()) {
//			IProjectDescription desc = project.getWorkspace().newProjectDescription(project.getName());
//			project.create(desc, null);
//			if (!project.isOpen()) {
//				project.open(null);
//			}
//		}

//		DirectoryDialog dirDialog = new DirectoryDialog(new Shell());
//		dirDialog.setText("Select your home directory");
//		String selectedDir = dirDialog.open();
//		System.out.println(selectedDir);
		
		List<Class> clases = Input.obtenerEntities(PATH);

		PlantillaClass.crear("B", clases, PATH_DESTINO);
				
	}

	

}
