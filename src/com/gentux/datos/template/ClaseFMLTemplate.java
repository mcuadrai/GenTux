package com.gentux.datos.template;

import com.gentux.datos.dto.*;
import com.gentux.datos.util.*;

public class ClaseFMLTemplate
{
  protected static String nl;
  public static synchronized ClaseFMLTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseFMLTemplate result = new ClaseFMLTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
  protected final String TEXT_2 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Table table = (Table) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append( GeneradorUtil.drawFMLs(table.getColumnas()));
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
