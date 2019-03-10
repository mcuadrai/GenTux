package com.gentux.datos.template;

import com.gentux.datos.dto.*;
import com.gentux.datos.util.*;

public class ClaseRegistrosBDTemplate
{
  protected static String nl;
  public static synchronized ClaseRegistrosBDTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseRegistrosBDTemplate result = new ClaseRegistrosBDTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = " " + NL + "" + NL + "typedef struct ";
  protected final String TEXT_3 = " {";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "}    ";
  protected final String TEXT_6 = ";";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     Table table = (Table) argument; 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append( GeneradorUtil.getFieldsFromStruct(table.getColumnas()));
    stringBuffer.append(TEXT_5);
    stringBuffer.append( GeneradorUtil.getNombreTipoEstructura(table.getName()));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
