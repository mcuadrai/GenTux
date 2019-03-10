#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <atmi.h>           /* TUXEDO Header File API's */
#include <userlog.h>        /* TUXEDO Header File */
#include <fml32.h>          /* TUXEDO FML Support */
#include <transferencia.h>
#include <transferenciaFML.h>
#include <olist.h>


void copiarFMLHaciaTerminalAtm(FBFR32 *fml, tCjaTERMINALATM *terminalAtm)
{
       Fget32(fml, CJA_CODIGO, 0, (char *) &terminalAtm->codigo, 0);
       Fget32(fml, CJA_DESCRIPCION, 0, terminalAtm->descripcion, 0);
}

void copiarTerminalAtmHaciaFML( tCjaTERMINALATM *terminalAtm, FBFR32 *fml)
{
		Fadd32(fml, CJA_CODIGO  ,  (char *) &terminalAtm->codigo, 0);
		Fadd32(fml, CJA_DESCRIPCION  ,  terminalAtm->descripcion, 0);			
}

