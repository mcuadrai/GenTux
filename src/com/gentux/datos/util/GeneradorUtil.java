package com.gentux.datos.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.gentux.datos.dto.Column;

public class GeneradorUtil {

	private static final String PREFIJO_TABLA = "";
	private static final int LARGO_MAXIMO_PREFIJO_TABLA = PREFIJO_TABLA.length();
	
	private static final int LARGO_MAXIMO_FMLFIELD = 30;
	
	public static final boolean ES_CON_REGISTRO = true;
	
	
	public static String getPrefijoServicioDatos(){
		Configuration nomenclatura = null;
		try {
			nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nomenclatura.getString("prefijo_nombre_servicio_datos");
		
	}
	
	
	public static String getNombreTipoEstructura(String nombreTabla){
		Configuration nomenclatura = null;
		try {
			nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nomenclatura.getString("prefijo_nombre_struct") + nombreTabla.toUpperCase(); 
	}

	/**
	 * Escribe los campos del arreglo separados por comas
	 * @param list
	 * @return
	 */
	public static String getFields(ArrayList<Column> list) {
		StringBuffer _sqlFields = new StringBuffer();
		for (Iterator<Column> _iterator = list.iterator(); _iterator.hasNext();) {
			Column column = (Column) _iterator.next();
			_sqlFields.append("     ");
			_sqlFields.append(column.getColumn_name());
			if (_iterator.hasNext()) {
				_sqlFields.append(", ");	
			}
		}
		return _sqlFields.toString();
	}
	
	/**
	 * Obtiene variables de base de datos
	 * @param list deben venir los valores en minúsculas
	 * @return
	 */
	public static String getDBVariables(String entidadLogica, String between  , ArrayList<Column> list) {
		StringBuffer _sqlFields = new StringBuffer();
		for (Iterator<Column> _iterator = list.iterator(); _iterator.hasNext();) {
			Column column = (Column) _iterator.next();
			_sqlFields.append("     ")
			          .append(":db").append(entidadLogica).append(between) 	
			          .append(convertFieldToVariable(column.getColumn_name()));
			if (_iterator.hasNext()) {
				_sqlFields.append(", ");	
			}
			_sqlFields.append("\n");
		}
		return _sqlFields.toString();
	}
	
	/**
	 * asigna variables de bd a campos
	 * @param entidadLogica
	 * @param list parametros sin identity o campos de secuencia
	 * @return
	 */
	public static String asignarVariablesHaciaColumnasEnSet(String entidadLogica, ArrayList<Column> list) {
		
		   String  _sqlFields =  GeneradorUtil.asignarVariablesHaciaColumnas(entidadLogica,", ", true,list);
		return _sqlFields.toString();
	}
	
public static String asignarVariablesHaciaColumnas(String entidadLogica, String separador, boolean esConRegistro , ArrayList<Column> list) {
		
		StringBuffer _sqlFields = new StringBuffer();
		for (Iterator<Column> _iterator = list.iterator(); _iterator.hasNext();) {
			Column column = (Column) _iterator.next();
			// TODO hacer template
			_sqlFields.append("     ")
			          .append(column.getColumn_name()).append(" = ")
			          .append(":db");
			          if (esConRegistro) {
			        	  _sqlFields.append(entidadLogica)
			                        .append(".")
				                     .append(convertFieldToVariable(column.getColumn_name()));
			          } else {
				          _sqlFields.append(convertirConPrimeraLetraMayuscula(convertFieldToVariable(column.getColumn_name())));
			          }
			          

			if (_iterator.hasNext()) {
				_sqlFields.append(separador);	
			}
			_sqlFields.append("\n");
		}
		return _sqlFields.toString();
	}
public static String asignarVariablesHaciaColumnasEnWhere(String entidadLogica, boolean esConRegistro ,ArrayList<Column> list) {
		
	   String  _sqlFields =  GeneradorUtil.asignarVariablesHaciaColumnas(entidadLogica," AND ", esConRegistro,list);
	   
		return _sqlFields.toString();
	}
	
	
	
	/**
	 * 
	 * @param list deben venir los valores en min�sculas
	 * @return
	 */
	public static String convertFieldToVariable(String field) {
		 StringBuffer variable = new StringBuffer();   	
		String _columnName = field.toLowerCase();
			/*if (_columnName.startsWith(PREFIJO_TABLA)) {
				
				String _resto = _columnName.substring(LARGO_MAXIMO_PREFIJO_TABLA, _columnName.length());
				// TODO realizar replace con expresion regular
				String _primeraLetra = (""+_resto.charAt(0)).toUpperCase();
				String _restoMenosPrimeraLetra = _columnName.substring(LARGO_MAXIMO_PREFIJO_TABLA+1, _columnName.length());
				
//				variable.append("codigo").append(_primeraLetra).append(_restoMenosPrimeraLetra);
				variable.append("codigo").append(_resto.toLowerCase());
			} else {*/
				variable.append(_columnName);	
//			}
		
		return variable.toString();
	}

	public static String convertirConPrimeraLetraMayuscula(String name){ 
		String primeraLetra  = name.substring(0,1).toUpperCase();
		String restoDeNombre = name.substring(1);
		return primeraLetra+restoDeNombre;
	}	
	
	
	public static String getFieldsFromStruct(ArrayList<Column> list) {
		StringBuffer _sqlFields = new StringBuffer();
		for (Iterator<Column> _iterator = list.iterator(); _iterator.hasNext();) {
			Column column = (Column) _iterator.next();
			_sqlFields.append("     ")
			          .append(column.getStructDataType()).append("	").append(column.getStructField())
			          .append(";")
					  .append("\n");
			           
		}
		return _sqlFields.toString();
	}
	
	
	
	
	/**
	 * Escribe los parametros, en formato "<tipoDeDato> <variable>", separados por coma
	 * @param list
	 * @return
	 */
	public static String drawDefinitionVariables(ArrayList<Column> list) {
		StringBuffer _sqlFields = new StringBuffer();
		for (Iterator<Column> _iterator = list.iterator(); _iterator.hasNext();) {
			Column column = (Column) _iterator.next();
			_sqlFields.append(" ").append(column.getStructDataType()).append(" ").append(column.getStructField());
			if (_iterator.hasNext()) {
				_sqlFields.append(",");	
			}	           
		}
		return _sqlFields.toString();
	}
	
	
	public static String drawFMLs(ArrayList<Column> list) {
		StringBuffer _sqlFields = new StringBuffer();
		int identificador = 1;
		for (Iterator<Column> _iterator = list.iterator(); _iterator.hasNext();) {
			Column column = (Column) _iterator.next();
			_sqlFields.append(getFMLField(column.getColumn_name()))
			          .append(" ").append(identificador)
			          .append(" ").append(column.getFmlType())
			          .append("\n");
			identificador++;
		}
		return _sqlFields.toString();
	}

	public static String getFMLField(String nombreColumna) {
		
		Configuration nomenclatura = null;
		try {
			nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String PREFIJO_MODULO = nomenclatura.getString("prefijo_nombre_FML");
		
		StringBuffer fmlField= new StringBuffer();   	
		fmlField.append(PREFIJO_MODULO)
		        .append(nombreColumna);

		String fmlFieldAjustado;
		if ( (fmlField.length()) > GeneradorUtil.LARGO_MAXIMO_FMLFIELD ) {
			fmlFieldAjustado = fmlField.substring(0, GeneradorUtil.LARGO_MAXIMO_FMLFIELD - PREFIJO_MODULO.length());
		}
		else {
			fmlFieldAjustado = fmlField.toString();
		}

		return fmlFieldAjustado;
	}
	
	public static String writeCast(String tipoColumna) {
		
		String _cast;
		if (tipoColumna.equals("NUMBER") || tipoColumna.equals("DATE")) {
			_cast = "(char *) &";
		} else {
			_cast = "";
		}
		return _cast;
	}

	
}
