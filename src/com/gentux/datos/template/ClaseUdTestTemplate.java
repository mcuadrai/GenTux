package com.gentux.datos.template;

import com.gentux.negocio.dto.*;
import java.util.*;

public class ClaseUdTestTemplate
{
  protected static String nl;
  public static synchronized ClaseUdTestTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseUdTestTemplate result = new ClaseUdTestTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "SRVCNM\t";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "\t1";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Service service = (Service) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append( service.getName());
    	  for (Iterator<Parameter> _iterator = service.getParameters().iterator(); _iterator.hasNext();) {
			Parameter parameter = (Parameter) _iterator.next();
  
    stringBuffer.append(TEXT_2);
    stringBuffer.append(parameter.getName());
    stringBuffer.append(TEXT_3);
    }
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
