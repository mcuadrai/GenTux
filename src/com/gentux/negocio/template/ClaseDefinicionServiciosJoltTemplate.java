package com.gentux.negocio.template;

import com.gentux.negocio.dto.*;
import com.gentux.datos.util.*;
import java.util.*;

public class ClaseDefinicionServiciosJoltTemplate
{
  protected static String nl;
  public static synchronized ClaseDefinicionServiciosJoltTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseDefinicionServiciosJoltTemplate result = new ClaseDefinicionServiciosJoltTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "service=";
  protected final String TEXT_2 = NL + "export=true" + NL + "inbuf=FML32" + NL + "outbuf=FML32";
  protected final String TEXT_3 = NL + "param=";
  protected final String TEXT_4 = NL + "type=";
  protected final String TEXT_5 = NL + "access=";
  protected final String TEXT_6 = NL + "param=HRM_MENSAJE_SERVICIO" + NL + "type=string" + NL + "access=out" + NL;
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Service service = (Service) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append( service.getName());
    stringBuffer.append(TEXT_2);
    
	  for (Iterator<Parameter> _iterator = service.getParameters().iterator(); _iterator.hasNext();) {
			Parameter parameter = (Parameter) _iterator.next();
  
    stringBuffer.append(TEXT_3);
    stringBuffer.append(parameter.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(parameter.getType());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(parameter.getAccess());
    }
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
