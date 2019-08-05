package com.generador.mapfre;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

public class Test {
	public static void main(String[] args) throws CoreException {

		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("Test");
//		IFolder folder = configureProjectWithXtext(project);
//		IFolder moduleFolder = createFolder(folder, TestFiles.moduleFolder());

//		IFile file2 = createTestFile(moduleFolder, "Class1", TestFiles.class1());

		if (!project.exists()) {
			IProjectDescription desc = project.getWorkspace().newProjectDescription(project.getName());
			project.create(desc, null);
			if (!project.isOpen()) {
				project.open(null);
			}
		}

	}
}
