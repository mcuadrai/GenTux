package com.gentux.datos.template;

import com.gentux.datos.dto.*;
import com.gentux.datos.util.*;

public class ClaseFunHTemplate
{
  protected static String nl;
  public static synchronized ClaseFunHTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseFunHTemplate result = new ClaseFunHTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "void copiarFMLHacia";
  protected final String TEXT_2 = "(FBFR32 *fml, ";
  protected final String TEXT_3 = " *";
  protected final String TEXT_4 = ");" + NL + "void copiar";
  protected final String TEXT_5 = "HaciaFML(";
  protected final String TEXT_6 = ", FBFR32 *fml);";
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Table table = (Table) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_2);
    stringBuffer.append( GeneradorUtil.getNombreTipoEstructura(table.getName()) );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_5);
    stringBuffer.append( GeneradorUtil.getNombreTipoEstructura(table.getName()) );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
