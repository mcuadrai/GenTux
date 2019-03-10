long SqlCjaInsertarTerminalAtm(tCjaTERMINALATM terminalAtm, char *mensaje);
long SqlCjaModificarTerminalAtm(tCjaTERMINALATM terminalAtm, char *mensaje);
long SqlCjaEliminarTerminalAtm(tCjaTERMINALATM terminalAtm, char *mensaje);
long SqlCjaBuscarTerminalAtm( short codigo, tCjaTERMINALATM *terminalAtm, char *mensaje);
long SqlCjaBuscarTodosTerminalAtm(Q_HANDLE *lista, char *mensaje);
