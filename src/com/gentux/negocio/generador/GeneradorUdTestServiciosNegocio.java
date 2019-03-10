package com.gentux.negocio.generador;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.FileUtils;

import com.gentux.datos.template.ClaseUdTestTemplate;
import com.gentux.negocio.dto.Parameter;
import com.gentux.negocio.dto.Service;

public class GeneradorUdTestServiciosNegocio{

	public static void main(String args[]) {

		try {
			XMLConfiguration config = new XMLConfiguration("config/definicionServiciosNegocioTuxedo.xml");
			
			List<HierarchicalConfiguration> listaServicios        =  config.configurationsAt("servicio");
			
			for (int i = 0; i < listaServicios.size(); i++) {
				StringBuffer resultadoFinal = new StringBuffer();
				Service service = new Service();
				service.setName(config.getString("servicio("+i+").nombre"));
				System.out.println("servicio="+service.getName());
				
				ArrayList<Parameter> parametersOfList = new ArrayList<Parameter>();
				List<HierarchicalConfiguration> parametros = config.configurationsAt("servicio("+i+").parametros.parametro");
				for(Iterator<HierarchicalConfiguration> it = parametros.iterator(); it.hasNext();)
				{
				    HierarchicalConfiguration sub = (HierarchicalConfiguration) it.next();
				    String entidad = sub.getString("nombre");
				    String tipo    = sub.getString("tipo");
				    String acceso  = sub.getString("acceso");
				
				    parametersOfList.add(new Parameter(entidad, tipo, acceso));
				
				}	
				service.setParameters(parametersOfList);
								
				ClaseUdTestTemplate generateXml = new ClaseUdTestTemplate();
			    resultadoFinal.append(generateXml.generate(service));
			    
			    File _directorioSalida = new File("out/ud");
			    String archivo = service.getName()+".ud";
				File file = new File(_directorioSalida+"/"+archivo);
				
				try {
					FileUtils.writeStringToFile(file, resultadoFinal.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.print("No fue posible escribir en archivo");
					e.printStackTrace();
				}
				System.out.println("archivo:"+archivo);
			}
			System.out.println("Programa finalizado.");
	
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
}

