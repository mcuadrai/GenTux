#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <malloc.h>

#include <atmi.h>           /* TUXEDO Header File API's */
#include <userlog.h>        /* TUXEDO Header File */
#include <fml32.h>          /* TUXEDO FML Support */

#include <olist.h>
#include <transferencia.h>
#include <transferencia_fun.h>
#include <transferencia_sql.h>
#include <transferenciaFML.h>
#include <hermesFML.h>
#include <servidor.h>
#include <omegafml32.h>          
#include <GestionDeErrores.h>

#define TIME_OUT 20

int tpsvrinit(int argc, char **argv)
{
   if (tpopen() == -1)
   {
      userlog("El Servidor de Admision: \"Admision\", fallo al conectarse con el Administrador de Base de Datos");
      return(-1);
   }

      userlog("El Servidor de Admision: \"Admision\", ha iniciado su ejecucion satisfactoriamente...");
   return(0);
}


void tpsvrdone()
{
   if (tpclose() == -1)
   {
      switch(tperrno)
      {
         case TPERMERR:
            userlog("El Servidor de Admision: \"Admision\", fallo al desconectarse del Administrador de Base de Datos para mas informacion consultar al manejador especifico");
            break;

         case TPEPROTO:
            userlog("El Servidor de Admision: \"Admision\", fallo al desconectarse del Administrador de Base de Datos debido a un problema de contexto del close()");
            break;

         case TPESYSTEM:
            userlog("El Servidor de Admision: \"Admision\", fallo al desconectarse del Administrador de Base de Datos debido a un problema con Tuxedo-System/T");
            break;

         case TPEOS:
            userlog("El Servidor de Admision: \"Admision\", fallo al desconectarse del Administrador de Base de Datos debido a un error del Sistema Operativo");
            break;

         default:
            userlog("El Servidor de Admision: \"Admision\", fallo al desconectarse del Administrador de Base de Datos debido a un error de excepcion en el sistema");
            break;
      }
	  return ;
   }
   userlog("El Servidor de Admision: \"Admision\", ha finalizado su ejecucion...");
   return ;
}

/*
   @author Mario de la Cuadra
   @app Realizado por generador de c?digo 
   Insertar Terminal ATM
*/
void CjaInsTerATM(TPSVCINFO *rqst)
{
   FBFR32 *fml;
   long sts;
   tCjaTERMINALATM terminalAtm;
   int transaccionGlobal;
   char mensaje[1024+1];

   /******   Buffer de entrada   ******/
   fml = (FBFR32 *)rqst->data;

   copiarFMLHaciaTerminalAtm(fml, &terminalAtm);

   /******   Cuerpo del servicio   ******/
   transaccionGlobal = tpgetlev();
   TRX_BEGIN(transaccionGlobal);
   
    sts = SqlCjaCjaInsTerATM(terminalAtm, mensaje);
	if (sts != SQL_SUCCESS)
    {
       Fadd32(fml, HRM_MENSAJE_SERVICIO, mensaje, 0);

       TRX_ABORT(transaccionGlobal);
       tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);
    }

   TRX_COMMIT(transaccionGlobal);
   tpreturn(TPSUCCESS, SRV_SUCCESS, (char *)fml, 0L, 0L);
   
}
/*
   @author Mario de la Cuadra
   @app Realizado por generador de c?digo 
   Modificar Terminal ATM
*/
void CjaModTerATM(TPSVCINFO *rqst)
{
   FBFR32 *fml;
   long sts;
   tCjaTERMINALATM terminalAtm;
   int transaccionGlobal;
   char mensaje[1024+1];

   /******   Buffer de entrada   ******/
   fml = (FBFR32 *)rqst->data;

   copiarFMLHaciaTerminalAtm(fml, &terminalAtm);

   /******   Cuerpo del servicio   ******/
   transaccionGlobal = tpgetlev();
   TRX_BEGIN(transaccionGlobal);
   
    sts = SqlCjaCjaModTerATM(terminalAtm, mensaje);
	if (sts != SQL_SUCCESS)
    {
       Fadd32(fml, HRM_MENSAJE_SERVICIO, mensaje, 0);

       TRX_ABORT(transaccionGlobal);
       tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);
    }

   TRX_COMMIT(transaccionGlobal);
   tpreturn(TPSUCCESS, SRV_SUCCESS, (char *)fml, 0L, 0L);
   
}
/*
   @author Mario de la Cuadra
   @app Realizado por generador de c?digo 
   Eliminar Terminal ATM
*/
void CjaEliTerATM(TPSVCINFO *rqst)
{
   FBFR32 *fml;
   long sts;
   tCjaTERMINALATM terminalAtm;
   int transaccionGlobal;
   char mensaje[1024+1];

   /******   Buffer de entrada   ******/
   fml = (FBFR32 *)rqst->data;

   copiarFMLHaciaTerminalAtm(fml, &terminalAtm);

   /******   Cuerpo del servicio   ******/
   transaccionGlobal = tpgetlev();
   TRX_BEGIN(transaccionGlobal);
   
    sts = SqlCjaCjaEliTerATM(terminalAtm, mensaje);
	if (sts != SQL_SUCCESS)
    {
       Fadd32(fml, HRM_MENSAJE_SERVICIO, mensaje, 0);

       TRX_ABORT(transaccionGlobal);
       tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);
    }

   TRX_COMMIT(transaccionGlobal);
   tpreturn(TPSUCCESS, SRV_SUCCESS, (char *)fml, 0L, 0L);
   
}
/*
   @author Mario de la Cuadra
   @app Realizado por generador de código 
   Buscar Terminal ATM
*/
void CjaBusTerATM(TPSVCINFO *rqst)
{
   FBFR32 *fml;
   int transaccionGlobal;
   long sts;
   char mensaje[1024+1];
   tCjaTERMINALATM terminalAtm;

   /******   Buffer de entrada   ******/
   fml = (FBFR32 *)rqst->data;
   
  copiarFMLHaciaTerminalAtm(fml, &terminalAtm);

   /******   Cuerpo del servicio   ******/
   transaccionGlobal = tpgetlev();
   TRX_BEGIN(transaccionGlobal);

   sts = SqlCjaCjaBusTerATM(, &terminalAtm, mensaje);
   if (sts != SQL_SUCCESS && sts != SQL_NOT_FOUND)
   {
      Fadd32(fml, HRM_MENSAJE_SERVICIO, mensaje, 0);

      TRX_ABORT(transaccionGlobal);
      tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);
   }
  
   copiarTerminalAtmHaciaFML(&terminalAtm, fml);

   TRX_COMMIT(transaccionGlobal);
   tpreturn(TPSUCCESS, SRV_SUCCESS, (char *)fml, 0L, 0L);
    
}
/*
  @author Mario de la Cuadra
  @app Realizado por generador de código 
   Buscar Todos Terminales ATM
*/
void CjaBusTodTerATM(TPSVCINFO *rqst)
{
   FBFR32 *fml;
   int transaccionGlobal;
   long sts;
      tCjaTERMINALATM *terminalAtm;
   char mensaje[1024+1];
   int i=0;
   Q_HANDLE *lista;

   /******   Buffer de entrada   ******/
   fml = (FBFR32 *)rqst->data;
   
   /******   Cuerpo del servicio   ******/
   transaccionGlobal = tpgetlev();
   TRX_BEGIN(transaccionGlobal);
   if ((lista = (Q_HANDLE *)QNew()) == NULL)    
   {
      Fadd32(fml, HRM_MENSAJE_SERVICIO, "No es posible realizar la accion solicitada", 0);
      TRX_ABORT(transaccionGlobal);
      tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);
   }
   sts = SqlCja(lista, mensaje);
   if (sts != SQL_SUCCESS)
   {
      if (sts == SQL_NOT_FOUND)
      {
         Fadd32(fml, HRM_MENSAJE_SERVICIO, "No hay datos de terminalAtm", 0);
      }
      else
      {
         Fadd32(fml, HRM_MENSAJE_SERVICIO, mensaje, 0);
       }
       QDelete(lista);
       TRX_ABORT(transaccionGlobal);
       tpreturn(TPFAIL, ErrorServicio(sts, rqst->name),(char *)fml, 0L, 0L);
   }
   tpfree((char *)fml);
   //                                cantidad de short y long   doubles,  char , caracteres/2
   // TODO modificar valores de NewFml32
   fml = NewFml32((long)lista->count, (short) 9, (short)0, (short) 1, (long)1024);
   if (fml == NULL)
   {
      fml = NewFml32((long)1,(short)0, (short)0, (short)1, (long)512);
      Fadd32(fml, HRM_MENSAJE_SERVICIO, "No es posible realizar la accion solicitada.", 0);
      QDelete(lista);
      TRX_ABORT(transaccionGlobal);
      tpreturn(TPFAIL, ErrorServicio(sts, rqst->name), (char *)fml, 0L, 0L);
   }

   for (i=0; i<lista->count; i++)
   {
      terminalAtm = (  tCjaTERMINALATM *)QGetItem(lista,i);
      copiarTerminalAtmHaciaFML(terminalAtm, fml);

   }
   QDelete(lista);
   TRX_COMMIT(transaccionGlobal);
   tpreturn(TPSUCCESS, SRV_SUCCESS, (char *)fml, 0L, 0L);
    
}
