package com.gentux.negocio.template;

import com.gentux.negocio.dto.*;
import com.gentux.datos.util.*;

public class ClaseGenerarServicioActualizarTuxedoTemplate
{
  protected static String nl;
  public static synchronized ClaseGenerarServicioActualizarTuxedoTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseGenerarServicioActualizarTuxedoTemplate result = new ClaseGenerarServicioActualizarTuxedoTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + "   @author Mario de la Cuadra Izquierdo" + NL + "   @app Realizado por generador de c�digo ";
  protected final String TEXT_2 = NL + "   ";
  protected final String TEXT_3 = NL + "*/" + NL + "void ";
  protected final String TEXT_4 = "(TPSVCINFO *rqst)" + NL + "{" + NL + "   FBFR32 *fml;" + NL + "   long sts;";
  protected final String TEXT_5 = " ";
  protected final String TEXT_6 = ";" + NL + "   int transaccionGlobal;" + NL + "   char mensaje[1024+1];" + NL + "" + NL + "   /******   Buffer de entrada   ******/" + NL + "   fml = (FBFR32 *)rqst->data;" + NL + "" + NL + "   copiarFMLHacia";
  protected final String TEXT_7 = "(fml, &";
  protected final String TEXT_8 = ");" + NL + "" + NL + "   /******   Cuerpo del servicio   ******/" + NL + "   transaccionGlobal = tpgetlev();" + NL + "   TRX_BEGIN(transaccionGlobal);" + NL + "   " + NL + "    sts = ";
  protected final String TEXT_9 = "(";
  protected final String TEXT_10 = ", mensaje);" + NL + "\tif (sts != SQL_SUCCESS)" + NL + "    {" + NL + "       Fadd32(fml, HRM_MENSAJE_SERVICIO, mensaje, 0);" + NL + "" + NL + "       TRX_ABORT(transaccionGlobal);" + NL + "       tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);" + NL + "    }" + NL + "" + NL + "   TRX_COMMIT(transaccionGlobal);" + NL + "   tpreturn(TPSUCCESS, SRV_SUCCESS, (char *)fml, 0L, 0L);" + NL + "   " + NL + "}";
  protected final String TEXT_11 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Service service = (Service) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append( service.getDescription());
    stringBuffer.append(TEXT_3);
    stringBuffer.append( service.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(service.getLogicEntity().toUpperCase()));
    stringBuffer.append(TEXT_5);
    stringBuffer.append( service.getLogicEntity());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(GeneradorUtil.convertirConPrimeraLetraMayuscula(service.getLogicEntity()) );
    stringBuffer.append(TEXT_7);
    stringBuffer.append( service.getLogicEntity() );
    stringBuffer.append(TEXT_8);
    stringBuffer.append( GeneradorUtil.getPrefijoServicioDatos());
    stringBuffer.append( service.getName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append( service.getLogicEntity() );
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
