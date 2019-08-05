package com.generador.mapfre;

import java.util.List;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

import com.generador.mapfre.entrada.Input;
import com.generador.mapfre.plantilla.Plantilla;

public class GeneradorJFace {

	public static void start() {
		DirectoryDialog dirDialog = new DirectoryDialog(new Shell());
		dirDialog.setText("Seleccionar el directorio de entities");
		String selectedDir = dirDialog.open();

		List<String> clases = Input.obtenerEntitiesString(selectedDir);

		Plantilla.crearControllerString(clases);
		Plantilla.crearServiceString(clases);
		Plantilla.crearRepositoryString(clases);

	}

}
