package com.gentux.datos.generador;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;

import com.gentux.datos.CatalogoDAO;
import com.gentux.datos.dto.Table;
import com.gentux.datos.template.ClaseRegistrosBDTemplate;

public class GeneradorStructs{

	public static void main(String args[]) {



		try {


			Configuration nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
			
			ArrayList<Table> listaTablas = new CatalogoDAO().getCatalogo();

			StringBuffer resultadoFinal = new StringBuffer();

			for (Table table : listaTablas) {
				//table.setStructName(nomenclatura.get  +table.getName());
				System.out.println("nombre tabla="+table.getTable_name());
				
				//TODO validar si existe tabla
				
			    ClaseRegistrosBDTemplate generateXml = new ClaseRegistrosBDTemplate();
			    resultadoFinal.append(generateXml.generate(table)).append("\n");	

			}

			File _directorioSalida = new File("out");
			File file = new File(_directorioSalida+"/"+nomenclatura.getString("nombre_archivo_estructura"));
			
			try {
				FileUtils.writeStringToFile(file, resultadoFinal.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.print("No fue posible escribir en archivo");
				e.printStackTrace();
			}
			
			

			System.out.println("Programa finalizado.");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 
	
}

