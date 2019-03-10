package com.gentux.datos.template;

public class ClaseImportarFunTemplate
{
  protected static String nl;
  public static synchronized ClaseImportarFunTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseImportarFunTemplate result = new ClaseImportarFunTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "#include <string.h>" + NL + "#include <stdlib.h>" + NL + "#include <stdio.h>" + NL + "#include <ctype.h>" + NL + "#include <atmi.h>           /* TUXEDO Header File API's */" + NL + "#include <userlog.h>        /* TUXEDO Header File */" + NL + "#include <fml32.h>          /* TUXEDO FML Support */" + NL + "#include <transferencia.h>" + NL + "#include <transferenciaFML.h>" + NL + "#include <olist.h>" + NL + NL;
  protected final String TEXT_2 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
