package com.gentux.negocio.template;

import com.gentux.negocio.dto.*;
import com.gentux.datos.util.*;

public class ClaseGenerarImportServicioNegocioTuxedoTemplate
{
  protected static String nl;
  public static synchronized ClaseGenerarImportServicioNegocioTuxedoTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseGenerarImportServicioNegocioTuxedoTemplate result = new ClaseGenerarImportServicioNegocioTuxedoTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "#include <string.h>" + NL + "#include <stdlib.h>" + NL + "#include <stdio.h>" + NL + "#include <ctype.h>" + NL + "#include <malloc.h>" + NL + "" + NL + "#include <atmi.h>           /* TUXEDO Header File API's */" + NL + "#include <userlog.h>        /* TUXEDO Header File */" + NL + "#include <fml32.h>          /* TUXEDO FML Support */" + NL + "" + NL + "#include <olist.h>" + NL + "#include <transferencia.h>" + NL + "#include <transferencia_fun.h>" + NL + "#include <transferencia_sql.h>" + NL + "#include <transferenciaFML.h>" + NL + "#include <hermesFML.h>" + NL + "#include <servidor.h>" + NL + "#include <omegafml32.h>          " + NL + "#include <GestionDeErrores.h>" + NL + "" + NL + "#define TIME_OUT 20" + NL + "" + NL + "int tpsvrinit(int argc, char **argv)" + NL + "{" + NL + "   if (tpopen() == -1)" + NL + "   {" + NL + "      userlog(\"El Servidor de Admision: \\\"Admision\\\", fallo al conectarse con el Administrador de Base de Datos\");" + NL + "      return(-1);" + NL + "   }" + NL + "" + NL + "      userlog(\"El Servidor de Admision: \\\"Admision\\\", ha iniciado su ejecucion satisfactoriamente...\");" + NL + "   return(0);" + NL + "}" + NL + "" + NL + "" + NL + "void tpsvrdone()" + NL + "{" + NL + "   if (tpclose() == -1)" + NL + "   {" + NL + "      switch(tperrno)" + NL + "      {" + NL + "         case TPERMERR:" + NL + "            userlog(\"El Servidor de Admision: \\\"Admision\\\", fallo al desconectarse del Administrador de Base de Datos para mas informacion consultar al manejador especifico\");" + NL + "            break;" + NL + "" + NL + "         case TPEPROTO:" + NL + "            userlog(\"El Servidor de Admision: \\\"Admision\\\", fallo al desconectarse del Administrador de Base de Datos debido a un problema de contexto del close()\");" + NL + "            break;" + NL + "" + NL + "         case TPESYSTEM:" + NL + "            userlog(\"El Servidor de Admision: \\\"Admision\\\", fallo al desconectarse del Administrador de Base de Datos debido a un problema con Tuxedo-System/T\");" + NL + "            break;" + NL + "" + NL + "         case TPEOS:" + NL + "            userlog(\"El Servidor de Admision: \\\"Admision\\\", fallo al desconectarse del Administrador de Base de Datos debido a un error del Sistema Operativo\");" + NL + "            break;" + NL + "" + NL + "         default:" + NL + "            userlog(\"El Servidor de Admision: \\\"Admision\\\", fallo al desconectarse del Administrador de Base de Datos debido a un error de excepcion en el sistema\");" + NL + "            break;" + NL + "      }" + NL + "\t  return ;" + NL + "   }" + NL + "   userlog(\"El Servidor de Admision: \\\"Admision\\\", ha finalizado su ejecucion...\");" + NL + "   return ;" + NL + "}" + NL;
  protected final String TEXT_2 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
