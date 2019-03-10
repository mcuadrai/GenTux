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
import com.gentux.datos.template.ClaseFMLTemplate;

public class GeneradorFMLs{

	public static void main(String args[]) throws ConfigurationException {

            
		    StringBuffer resultadoFinal = new StringBuffer();
			ClaseFMLTemplate generateXml = new ClaseFMLTemplate();
			Configuration nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
			
			ArrayList<Table> tablas = new CatalogoDAO().getCatalogo();
			for (Table table : tablas) {
				resultadoFinal.append(generateXml.generate(table));	
			}
				
			File _directorioSalida = new File("out");
			File file = new File(_directorioSalida+"/"+nomenclatura.getString("nombre_archivo_FML"));
			
			try {
				FileUtils.writeStringToFile(file, resultadoFinal.toString());
				System.out.println("FMLs creados");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.print("No fue posible escribir en archivo");
				e.printStackTrace();
			}
			
	}

	 
	
}

