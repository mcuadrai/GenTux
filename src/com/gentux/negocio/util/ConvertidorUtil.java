package com.gentux.negocio.util;

import java.util.ArrayList;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.gentux.datos.CatalogoDAO;
import com.gentux.datos.dto.Column;
import com.gentux.datos.util.GeneradorUtil;
import com.gentux.negocio.dto.Parameter;

public class ConvertidorUtil {

	
	private static final int LARGO_MAXIMO_SERVICIO_TUXEDO = 15;
	
	
	
	public static ArrayList cargarNuevosParametrosAlServicio(CatalogoDAO catalogoDAO, String nombre, String acceso ) throws ConfigurationException{
		  
		   ArrayList listaParametros = new ArrayList(); 
		   ArrayList<Column> listaColumnas = catalogoDAO.getTable(nombre).getColumnas();
		   for (Column column : listaColumnas) {
			  String _tipo ; 
			  if (column.getStructDataType().equals("long")) {
			      	_tipo = "integer";     				  
			  } else if (column.getStructDataType().equals("short")) {
			         _tipo = "short";
			  } else if (column.getStructDataType().equals("double")) {       
				  _tipo = "double"; 
		          } else    {
				  _tipo = "string";
			  }
			  listaParametros.add(new Parameter(GeneradorUtil.getFMLField(column.getColumn_name()),_tipo, acceso));
		   }    
	       return listaParametros;
	}

	/**
	 * desde lectura de archivo de texto
	 * 
	 * @param descripcion
	 * @return
	 * @throws ConfigurationException 
	 */
//	public static String generarNombreTuxedo(String _nombreServicio) {
//		if ( (_nombreServicio.length() + PREFIJO_NOMBRE_SERVICIO_TUXEDO.length()) > ConvertidorUtil.LARGO_MAXIMO_SERVICIO_TUXEDO)
//			_nombreServicio = _nombreServicio.substring(0, ConvertidorUtil.LARGO_MAXIMO_SERVICIO_TUXEDO - PREFIJO_NOMBRE_SERVICIO_TUXEDO.length());
//		return _nombreServicio;
//	}
	public static String generarNombreTuxedo(String _nombreServicio) throws ConfigurationException {
		   String tmpNombreServicio;
		   String abreviacionNombre;
		   String nombreServicioTuxedo = "";
		   StringBuffer _nombreBuffer = new StringBuffer();
		   tmpNombreServicio =  _nombreServicio.replaceAll(" de ", " ");
		   String[] _palabra = tmpNombreServicio.split(" ");
		   
		   Configuration nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
		   
		   _nombreBuffer.append(nomenclatura.getString("prefijo_nombre_servicio_negocio"));
		   
		   int i = 0;
		   while (i < _palabra.length) {
			   String palabraTrim = _palabra[i].trim();
			   int numeroCorte;
			   if (palabraTrim.length() < 3) {
				   numeroCorte = palabraTrim.length();
			   } else {
				   numeroCorte = 3;
			   }
			   abreviacionNombre = palabraTrim.substring(0, numeroCorte);
			   _nombreBuffer.append(abreviacionNombre);
			   i++;
		   }
		   if (_nombreBuffer.length() > LARGO_MAXIMO_SERVICIO_TUXEDO) {
			   nombreServicioTuxedo = _nombreBuffer.substring(0, LARGO_MAXIMO_SERVICIO_TUXEDO);
		   } else {
			   nombreServicioTuxedo = _nombreBuffer.toString();
		   }
		   return nombreServicioTuxedo;
	}
	
	
	
}
