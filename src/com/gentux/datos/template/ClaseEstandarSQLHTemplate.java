package com.gentux.datos.template;

import com.gentux.datos.dto.*;
import com.gentux.datos.util.*;

public class ClaseEstandarSQLHTemplate
{
  protected static String nl;
  public static synchronized ClaseEstandarSQLHTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseEstandarSQLHTemplate result = new ClaseEstandarSQLHTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "long ";
  protected final String TEXT_2 = "Insertar";
  protected final String TEXT_3 = "(";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = ", char *mensaje);" + NL + "long ";
  protected final String TEXT_6 = "Modificar";
  protected final String TEXT_7 = "Eliminar";
  protected final String TEXT_8 = "Buscar";
  protected final String TEXT_9 = ", ";
  protected final String TEXT_10 = " *";
  protected final String TEXT_11 = "BuscarTodos";
  protected final String TEXT_12 = "(Q_HANDLE *lista, char *mensaje);";
  protected final String TEXT_13 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Table table = (Table) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append( GeneradorUtil.getPrefijoServicioDatos());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(table.getName()));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append( GeneradorUtil.getPrefijoServicioDatos());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(table.getName()));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append( GeneradorUtil.getPrefijoServicioDatos());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(table.getName()));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append( GeneradorUtil.getPrefijoServicioDatos());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GeneradorUtil.drawDefinitionVariables(table.getColumnasDeClaveUnicaPrincipal()) );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(table.getName()));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append( GeneradorUtil.getPrefijoServicioDatos());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
