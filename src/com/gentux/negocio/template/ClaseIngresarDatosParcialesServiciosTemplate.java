package com.gentux.negocio.template;

import com.gentux.negocio.dto.*;
import com.gentux.negocio.util.*;
import java.util.*;

public class ClaseIngresarDatosParcialesServiciosTemplate
{
  protected static String nl;
  public static synchronized ClaseIngresarDatosParcialesServiciosTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseIngresarDatosParcialesServiciosTemplate result = new ClaseIngresarDatosParcialesServiciosTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>" + NL + "<serviciosJolt>";
  protected final String TEXT_2 = NL + "  <servicio>" + NL + "    <nombre>";
  protected final String TEXT_3 = "</nombre>" + NL + "    <tipo>";
  protected final String TEXT_4 = "</tipo>" + NL + "    <descripcion>";
  protected final String TEXT_5 = "</descripcion>" + NL + "    <entidadLogica>";
  protected final String TEXT_6 = "</entidadLogica>" + NL + "    <parametros> ";
  protected final String TEXT_7 = NL + "\t  <parametro>" + NL + "\t\t\t <nombre>";
  protected final String TEXT_8 = "</nombre>" + NL + "\t\t\t <tipo>";
  protected final String TEXT_9 = "</tipo>" + NL + "\t         <acceso>";
  protected final String TEXT_10 = "</acceso>" + NL + "\t  </parametro> ";
  protected final String TEXT_11 = "</parametros>" + NL + "  </servicio>";
  protected final String TEXT_12 = NL + "  " + NL + "</serviciosJolt>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
       ArrayList serviceList = (ArrayList) argument; 
	  for (Iterator<Service> _iteratorSvc = serviceList.iterator(); _iteratorSvc.hasNext();) {
			Service serviceXML = (Service) _iteratorSvc.next();
  
    stringBuffer.append(TEXT_2);
    stringBuffer.append( serviceXML.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append( serviceXML.getType());
    stringBuffer.append(TEXT_4);
    stringBuffer.append( serviceXML.getDescription());
    stringBuffer.append(TEXT_5);
    stringBuffer.append( serviceXML.getLogicEntity() );
    stringBuffer.append(TEXT_6);
    	  for (Iterator<Parameter> _iterator = serviceXML.getParameters().iterator(); _iterator.hasNext();) {
			Parameter parameterXML = (Parameter) _iterator.next();  
    stringBuffer.append(TEXT_7);
    stringBuffer.append(parameterXML.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(parameterXML.getType());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(parameterXML.getAccess());
    stringBuffer.append(TEXT_10);
     } 
    stringBuffer.append(TEXT_11);
     }
    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}
