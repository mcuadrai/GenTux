package com.gentux.datos.generador.test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;

import com.gentux.datos.dto.Column;
import com.gentux.datos.dto.Table;
import com.gentux.datos.template.ClaseRegistrosBDTemplate;


public class GeneratorCore {

	/**
	 * @param args
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		 HelloWorldTemplate helloworld = new HelloWorldTemplate();
//		 String result = helloworld.generate(null);
//		 System.out.println(result);
		 
//		 GreetingTemplate sayHello = new GreetingTemplate();
//		 String result = sayHello.generate("Tutorial Reader");
//		 System.out.println(result);
		 
		 
//		 List data = new ArrayList();
//		   data.add("first");
//		   data.add("second");
//		   data.add("third");
//		 
//		   XMLDemoTemplate generateXml = new XMLDemoTemplate();
//		   String result = generateXml.generate(data);
//		   System.out.println(result);
		
	
		Configuration config = null;
		List<Object> list = null ;
		try {
			config = new PropertiesConfiguration("config/generador.properties");
			list =  config.getList("tablas");
			
		} catch (ConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String tabla1 = (String) list.get(0);
		String tabla2 = (String) list.get(1);
		   
		System.out.println("tabla1="+tabla1);
		System.out.println("tabla2="+tabla2);
		
               Table table = new Table("solicitud");
			   table.setTable_name("SOL_SOLICITUD");
               ArrayList<Column> listaColumnas = new ArrayList<Column>();
               ArrayList<Column> listaColumnasClaveUnica = new ArrayList<Column>();
               ArrayList<Column> listaColumnasNoClaveUnica = new ArrayList<Column>();
               
               
               Column columna = new Column();
               columna.setColumn_name("CODIGO");
               columna.setData_type("NUMBER");
               columna.setData_precision(new Integer(4));
               columna.setKey(true);
               listaColumnas.add(columna);
               listaColumnasClaveUnica.add(columna);
               
               Column columna2 = new Column();
               columna2.setColumn_name("SOL_ESTADO");
               columna2.setData_type("NUMBER");
               columna2.setData_precision(new Integer(9));
               columna2.setKey(false);
               listaColumnas.add(columna2);
               listaColumnasNoClaveUnica.add(columna2);
               
               
               Column columna3 = new Column();
               columna3.setColumn_name("DESCRIPCION");
               columna3.setData_type("VARCHAR2");
               columna3.setData_precision(null);
               columna3.setKey(false);
               listaColumnas.add(columna3);
               listaColumnasNoClaveUnica.add(columna3);
               
               table.setColumnas(listaColumnas);
               table.setColumnasDeClaveUnicaPrincipal(listaColumnasClaveUnica);
               table.setColumnasNoPertenecenAClaveUnica(listaColumnasNoClaveUnica);
               
               ClaseRegistrosBDTemplate claseRegistrosBDTemplate = new ClaseRegistrosBDTemplate();
			   String result = claseRegistrosBDTemplate.generate(table);
			   System.out.println(result);
               
			   File _directorioSalida = new File("out");
			   File file = new File(_directorioSalida+"/"+table.getName()+"_sql.h");
			   try {
				FileUtils.writeStringToFile(file, result);
				
			   } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			   }
			   
               
//               ClaseBaseDatosTuxedoTemplate generateXml = new ClaseBaseDatosTuxedoTemplate();
//			   String result = generateXml.generate(table);
//			   System.out.println(result);

		   
		   
		   
	}

}
