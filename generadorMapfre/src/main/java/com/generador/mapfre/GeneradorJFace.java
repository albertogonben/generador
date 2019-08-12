package com.generador.mapfre;

import java.util.List;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

import com.generador.mapfre.entrada.Input;
import com.generador.mapfre.plantilla.PlantillaClass;

public class GeneradorJFace {

	public static void start() {
		DirectoryDialog dirDialog = new DirectoryDialog(new Shell());
		dirDialog.setText("Seleccionar el directorio de entities");
		String selectedDir = dirDialog.open();

		if (selectedDir != null && selectedDir != "") {

			DirectoryDialog destDialog = new DirectoryDialog(new Shell());
			destDialog.setText("Seleccionar el directorio destino");
			String selectedDest = destDialog.open();

			if (selectedDest != null && selectedDir != "") {
				List<Class> clases = Input.obtenerEntities(selectedDir);

				if (clases.size() > 0) {
					PlantillaClass.crear("C", clases, selectedDest);
					PlantillaClass.crear("S", clases, selectedDest);
					PlantillaClass.crear("R", clases, selectedDest);
					PlantillaClass.crear("P", clases, selectedDest);
					PlantillaClass.crear("B", clases, selectedDest);
				}
			}

		}

	}

}
