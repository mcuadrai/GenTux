package com.gentux.datos.template;

import com.gentux.datos.dto.*;
import com.gentux.datos.util.*;
import java.util.*;

public class ClaseFunTemplate
{
  protected static String nl;
  public static synchronized ClaseFunTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseFunTemplate result = new ClaseFunTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "void copiarFMLHacia";
  protected final String TEXT_2 = "(FBFR32 *fml, ";
  protected final String TEXT_3 = " *";
  protected final String TEXT_4 = ")" + NL + "{";
  protected final String TEXT_5 = NL + "       Fget32(fml, ";
  protected final String TEXT_6 = ", 0, ";
  protected final String TEXT_7 = "->";
  protected final String TEXT_8 = ", 0);";
  protected final String TEXT_9 = NL + "}" + NL + "" + NL + "void copiar";
  protected final String TEXT_10 = "HaciaFML( ";
  protected final String TEXT_11 = ", FBFR32 *fml)" + NL + "{";
  protected final String TEXT_12 = NL + "\t\tFadd32(fml, ";
  protected final String TEXT_13 = "  ,  ";
  protected final String TEXT_14 = "\t\t\t" + NL + "}";
  protected final String TEXT_15 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Table table = (Table) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(table.getName()));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_4);
    
	  for (Iterator<Column> _iterator = table.getColumnas().iterator(); _iterator.hasNext();) {
			Column column = (Column) _iterator.next();
  
    stringBuffer.append(TEXT_5);
    stringBuffer.append(GeneradorUtil.getFMLField(column.getColumn_name()) );
    stringBuffer.append(TEXT_6);
    stringBuffer.append( GeneradorUtil.writeCast(column.getData_type()));
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append( GeneradorUtil.convertFieldToVariable(column.getColumn_name()) );
    stringBuffer.append(TEXT_8);
     } 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(table.getNameWithFirstLetterUpperCase());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(table.getName()));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_11);
      
	  for (Iterator<Column> _iterator = table.getColumnas().iterator(); _iterator.hasNext();) {
			Column column = (Column) _iterator.next();
  
    stringBuffer.append(TEXT_12);
    stringBuffer.append(GeneradorUtil.getFMLField(column.getColumn_name()) );
    stringBuffer.append(TEXT_13);
    stringBuffer.append( GeneradorUtil.writeCast(column.getData_type()));
    stringBuffer.append(table.getName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append( GeneradorUtil.convertFieldToVariable(column.getColumn_name()) );
    stringBuffer.append(TEXT_8);
     } 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    return stringBuffer.toString();
  }
}
