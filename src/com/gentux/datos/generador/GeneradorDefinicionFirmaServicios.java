package com.gentux.datos.generador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;

import com.gentux.datos.CatalogoDAO;
import com.gentux.datos.dto.Table;
import com.gentux.datos.template.ClaseEstandarSQLHTemplate;

public class GeneradorDefinicionFirmaServicios{

	
	public static void main(String args[]) {

            
		try {
			
			Configuration config = new PropertiesConfiguration("config/generador.properties");
			Configuration nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
			
			List<Object> listaTablas 		 = config.getList("nombre_tabla");
			List<Object> listaNombresLogicos = config.getList("nombrelogico_tabla");

			StringBuffer resultadoFinal = new StringBuffer();
			for (int i = 0; i < listaTablas.size(); i++) {

				Table table = new CatalogoDAO().getTable((String) listaTablas.get(i));
				table.setName((String) listaNombresLogicos.get(i));
				System.out.println("tabla="+table.getTable_name());				
				
				ClaseEstandarSQLHTemplate generateXml = new ClaseEstandarSQLHTemplate();
			    resultadoFinal.append(generateXml.generate(table));	

			}

			File _directorioSalida = new File("out");
			File file = new File(_directorioSalida+"/"+nomenclatura.getString("nombre_archivo_def_basedatos"));
			
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

