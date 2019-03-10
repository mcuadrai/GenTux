package com.gentux.negocio.generador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FileUtils;

import com.gentux.datos.CatalogoDAO;
import com.gentux.negocio.dto.Parameter;
import com.gentux.negocio.dto.Service;
import com.gentux.negocio.template.ClaseIngresarDatosParcialesServiciosTemplate;
import com.gentux.negocio.util.ConvertidorUtil;

public class GeneradorServiciosTuxedoXML {

	/**
	 * @param args
	 * @throws ConfigurationException 
	 */
	public static void main(String[] args) throws ConfigurationException {

		File _directorioEntrada = new File("config");
		File file = new File(_directorioEntrada + "/"+ "serviciosTuxedoParciales.txt");

		ArrayList serviceList = new ArrayList();
		List<String> lineas;
		try {
			lineas = FileUtils.readLines(file);

			for (String linea : lineas) {
				Service service = new Service();
				String[] _columnas = linea.trim().split(";");
				service.setDescription(_columnas[0].trim());
				service.setType(_columnas[1].trim());
				service.setLogicEntity(_columnas[2].trim());
				
				service.setName(ConvertidorUtil.generarNombreTuxedo(service.getDescription()));

				String _entidadesSimples = _columnas[5].trim();
				String _tipoEntidad = _columnas[3].trim();
				String _accesoEntidad = _columnas[4].trim();

				ArrayList parametersOfList = new ArrayList();
				List<String> lista = Arrays
						.asList(_entidadesSimples.split(","));

				if (_tipoEntidad.equals("table")) {
					for (String _nombreEntidad : lista) {
                                                // TODO descomentar, comentario temporal
						parametersOfList = ConvertidorUtil.cargarNuevosParametrosAlServicio(new CatalogoDAO(), _nombreEntidad, _accesoEntidad);
						
					}
				} else {
					parametersOfList.add(new Parameter(lista.get(0),_tipoEntidad, _accesoEntidad));
				}
                                System.out.println("servicio "+service.getName()+ " terminado.");
				
				service.setParameters(parametersOfList);
				serviceList.add(service);
			}

			StringBuffer resultadoFinal = new StringBuffer();
			ClaseIngresarDatosParcialesServiciosTemplate generateXml = new ClaseIngresarDatosParcialesServiciosTemplate();
			resultadoFinal.append(generateXml.generate(serviceList));

			File _directorioSalida = new File("config");
			File _outputFile = new File(_directorioSalida + "/"+ "definicionServiciosNegocioTuxedo.xml");

			try {

				FileUtils.writeStringToFile(_outputFile, resultadoFinal
						.toString());

				System.out.println("Archivo " + _outputFile.getAbsoluteFile()	+ " generado.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.print("No fue posible escribir en archivo");
				e.printStackTrace();
			}

			System.out.println("Programa terminado.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
