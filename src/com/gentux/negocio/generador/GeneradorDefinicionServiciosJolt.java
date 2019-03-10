package com.gentux.negocio.generador;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.FileUtils;

import com.gentux.negocio.dto.Parameter;
import com.gentux.negocio.dto.Service;
import com.gentux.negocio.template.ClaseDefinicionServiciosJoltTemplate;

public class GeneradorDefinicionServiciosJolt{

	public static void main(String args[]) {


		try {

			XMLConfiguration config = new XMLConfiguration("config/definicionServiciosNegocioTuxedo.xml");
			Configuration nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
			
			List<HierarchicalConfiguration> listaServicios        =  config.configurationsAt("servicio");
			StringBuffer resultadoFinal = new StringBuffer();
			for (int i = 0; i < listaServicios.size(); i++) {
				Service service = new Service();
				service.setName(config.getString("servicio("+i+").nombre"));
				System.out.println("servicio="+service.getName());
				
				ArrayList<Parameter> parametersOfList = new ArrayList<Parameter>();
				List parametros = config.configurationsAt("servicio("+i+").parametros.parametro");
				for(Iterator it = parametros.iterator(); it.hasNext();)
				{
				    HierarchicalConfiguration sub = (HierarchicalConfiguration) it.next();
				    String entidad = sub.getString("nombre");
				    String tipo    = sub.getString("tipo");
				    String acceso  = sub.getString("acceso");
				
				    parametersOfList.add(new Parameter(entidad, tipo, acceso));
				
				}	
				service.setParameters(parametersOfList);
								
				ClaseDefinicionServiciosJoltTemplate generateXml = new ClaseDefinicionServiciosJoltTemplate();
			    resultadoFinal.append(generateXml.generate(service));	

			}

			File _directorioSalida = new File("out");
			File file = new File(_directorioSalida+"/"+ nomenclatura.getString("nombre_archivo_jolt"));
			
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

