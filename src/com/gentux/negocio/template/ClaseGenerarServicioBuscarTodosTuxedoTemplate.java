package com.gentux.negocio.template;

import com.gentux.negocio.dto.*;
import com.gentux.datos.util.*;

public class ClaseGenerarServicioBuscarTodosTuxedoTemplate
{
  protected static String nl;
  public static synchronized ClaseGenerarServicioBuscarTodosTuxedoTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ClaseGenerarServicioBuscarTodosTuxedoTemplate result = new ClaseGenerarServicioBuscarTodosTuxedoTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + "  @author Mario de la Cuadra" + NL + "  @app Realizado por generador de código ";
  protected final String TEXT_2 = NL + "   ";
  protected final String TEXT_3 = NL + "*/" + NL + "void ";
  protected final String TEXT_4 = "(TPSVCINFO *rqst)" + NL + "{" + NL + "   FBFR32 *fml;" + NL + "   int transaccionGlobal;" + NL + "   long sts;";
  protected final String TEXT_5 = NL + "      ";
  protected final String TEXT_6 = " *";
  protected final String TEXT_7 = ";" + NL + "   char mensaje[1024+1];" + NL + "   int i=0;" + NL + "   Q_HANDLE *lista;" + NL + "" + NL + "   /******   Buffer de entrada   ******/" + NL + "   fml = (FBFR32 *)rqst->data;" + NL + "   " + NL + "   /******   Cuerpo del servicio   ******/" + NL + "   transaccionGlobal = tpgetlev();" + NL + "   TRX_BEGIN(transaccionGlobal);" + NL + "   if ((lista = (Q_HANDLE *)QNew()) == NULL)    " + NL + "   {" + NL + "      Fadd32(fml, HRM_MENSAJE_SERVICIO, \"No es posible realizar la accion solicitada\", 0);" + NL + "      TRX_ABORT(transaccionGlobal);" + NL + "      tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);" + NL + "   }" + NL + "   sts = ";
  protected final String TEXT_8 = "(lista, mensaje);" + NL + "   if (sts != SQL_SUCCESS)" + NL + "   {" + NL + "      if (sts == SQL_NOT_FOUND)" + NL + "      {" + NL + "         Fadd32(fml, HRM_MENSAJE_SERVICIO, \"No hay datos de ";
  protected final String TEXT_9 = "\", 0);" + NL + "      }" + NL + "      else" + NL + "      {" + NL + "         Fadd32(fml, HRM_MENSAJE_SERVICIO, mensaje, 0);" + NL + "       }" + NL + "       QDelete(lista);" + NL + "       TRX_ABORT(transaccionGlobal);" + NL + "       tpreturn(TPFAIL, ErrorServicio(sts, rqst->name),(char *)fml, 0L, 0L);" + NL + "   }" + NL + "   tpfree((char *)fml);" + NL + "   //                                cantidad de short y long   doubles,  char , caracteres/2" + NL + "   // TODO modificar valores de NewFml32" + NL + "   fml = NewFml32((long)lista->count, (short) 9, (short)0, (short) 1, (long)1024);" + NL + "   if (fml == NULL)" + NL + "   {" + NL + "      fml = NewFml32((long)1,(short)0, (short)0, (short)1, (long)512);" + NL + "      Fadd32(fml, HRM_MENSAJE_SERVICIO, \"No es posible realizar la accion solicitada.\", 0);" + NL + "      QDelete(lista);" + NL + "      TRX_ABORT(transaccionGlobal);" + NL + "      tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);" + NL + "   }" + NL + "" + NL + "   for (i=0; i<lista->count; i++)" + NL + "   {";
  protected final String TEXT_10 = " = (  ";
  protected final String TEXT_11 = " *)QGetItem(lista,i);" + NL + "      copiar";
  protected final String TEXT_12 = "HaciaFML(";
  protected final String TEXT_13 = ", fml);" + NL + "" + NL + "   }" + NL + "   QDelete(lista);" + NL + "   TRX_COMMIT(transaccionGlobal);" + NL + "   tpreturn(TPSUCCESS, SRV_SUCCESS, (char *)fml, 0L, 0L);" + NL + "    " + NL + "}";
  protected final String TEXT_14 = NL;

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
    stringBuffer.append(TEXT_5);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(service.getLogicEntity().toUpperCase()));
    stringBuffer.append(TEXT_6);
    stringBuffer.append( service.getLogicEntity());
    stringBuffer.append(TEXT_7);
    stringBuffer.append( GeneradorUtil.getPrefijoServicioDatos());
    stringBuffer.append(TEXT_8);
    stringBuffer.append( service.getLogicEntity() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_5);
    stringBuffer.append( service.getLogicEntity() );
    stringBuffer.append(TEXT_10);
    stringBuffer.append(GeneradorUtil.getNombreTipoEstructura(service.getLogicEntity().toUpperCase()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(GeneradorUtil.convertirConPrimeraLetraMayuscula(service.getLogicEntity()) );
    stringBuffer.append(TEXT_12);
    stringBuffer.append( service.getLogicEntity() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}
