package com.gentux.negocio.generador;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.FileUtils;

import com.gentux.negocio.dto.Service;
import com.gentux.negocio.template.ClaseGenerarImportServicioNegocioTuxedoTemplate;
import com.gentux.negocio.template.ClaseGenerarServicioActualizarTuxedoTemplate;
import com.gentux.negocio.template.ClaseGenerarServicioBuscarTodosTuxedoTemplate;
import com.gentux.negocio.template.ClaseGenerarServicioBuscarTuxedoTemplate;

public class GeneradorImplementacionServiciosNegocioTuxedo{

	
	public static void main(String args[]) {


		try {

			XMLConfiguration config = new XMLConfiguration("config/definicionServiciosNegocioTuxedo.xml");
			Configuration nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
			
			List<HierarchicalConfiguration> listaServicios        =  config.configurationsAt("servicio");
			StringBuffer resultadoFinal = new StringBuffer();
			ClaseGenerarServicioBuscarTuxedoTemplate generateBuscarTuxedo = new ClaseGenerarServicioBuscarTuxedoTemplate();
			ClaseGenerarServicioBuscarTodosTuxedoTemplate generateBuscarTodosTuxedo = new ClaseGenerarServicioBuscarTodosTuxedoTemplate();
			ClaseGenerarServicioActualizarTuxedoTemplate generateActualizarTuxedo = new ClaseGenerarServicioActualizarTuxedoTemplate();
			ClaseGenerarImportServicioNegocioTuxedoTemplate generateImportTuxedo = new ClaseGenerarImportServicioNegocioTuxedoTemplate();
			
			resultadoFinal.append(generateImportTuxedo.generate(null));
			
			for (int i = 0; i < listaServicios.size(); i++) {
				Service service = new Service();
				service.setName(config.getString("servicio("+i+").nombre"));
				service.setDescription(config.getString("servicio("+i+").descripcion"));
				service.setLogicEntity(config.getString("servicio("+i+").entidadLogica"));
				service.setType(config.getString("servicio("+i+").tipo"));
				System.out.println("servicio="+service.getName());

				if (service.getType().equals("b")) { 
					resultadoFinal.append(generateBuscarTuxedo.generate(service));
				}else if (service.getType().equals("bt")) { 
						resultadoFinal.append(generateBuscarTodosTuxedo.generate(service));
				} else {
					resultadoFinal.append(generateActualizarTuxedo.generate(service));
				}	
			}

			File _directorioSalida = new File("out");
			File file = new File(_directorioSalida+"/"+nomenclatura.getString("nombre_archivo_negocio"));
			
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

