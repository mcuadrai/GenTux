/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gentux.configuracion;

/**
 *
 * @author mdelacuadra
 */
public class Configuracion {

    private String _archivoTablasSeleccionadas = "";
    private String _archivoConexionEsquemaBaseDaDatos = "";
    private String _directorioSalida = "d:/generadorCodigo/out/";

    private String _prefijoArchivoSalida = "transferencia";
    private String _archivoImplementacionServicioDAO = "transferencia_sql.pc";
    private String _archivoFirmaDeServiciosDAO       = "transferencia_sql.h";
    private String _archivoEstructurasDAO            = "transferencia.h";

    private String _archivoFML                      = "transferenciaFML";
    private String _archivoTraspasoDeDatos          = "transferencia_fun.c";
    private String _archivoFirmasTraspasoDeDatos    = "transferencia_fun.h";
    private String _archivoJOLT    = "Trf.data";


    public void setNomenclaturaTodosLosArchivos(String _prefijoArchivo) {
         this._prefijoArchivoSalida = _prefijoArchivo;
         this.setArchivoFirmasTraspasoDeDatos(_prefijoArchivo+"_sql.pc");
         // TODO hacer los demas..
    }

    public String getDirectorioSalida() {
        return _directorioSalida;
    }

    public void setDirectorioSalida(String _directorioSalida) {
        this._directorioSalida = _directorioSalida;
    }

    public String getArchivoConexionEsquemaBaseDaDatos() {
        return _archivoConexionEsquemaBaseDaDatos;
    }

    public void setArchivoConexionEsquemaBaseDaDatos(String _archivoConexionEsquemaBaseDaDatos) {
        this._archivoConexionEsquemaBaseDaDatos = _archivoConexionEsquemaBaseDaDatos;
    }

    public String getArchivoTablasSeleccionadas() {
        return _archivoTablasSeleccionadas;
    }

    public void setArchivoTablasSeleccionadas(String _archivoTablasSeleccionadas) {
        this._archivoTablasSeleccionadas = _archivoTablasSeleccionadas;
    }

    public String getArchivoEstructurasDAO() {
        return _archivoEstructurasDAO;
    }

    public void setArchivoEstructurasDAO(String _archivoEstructurasDAO) {
        this._archivoEstructurasDAO = _archivoEstructurasDAO;
    }

    public String getArchivoFML() {
        return _archivoFML;
    }

    public void setArchivoFML(String _archivoFML) {
        this._archivoFML = _archivoFML;
    }

    public String getArchivoFirmaDeServiciosDAO() {
        return _archivoFirmaDeServiciosDAO;
    }

    public void setArchivoFirmaDeServiciosDAO(String _archivoFirmaDeServiciosDAO) {
        this._archivoFirmaDeServiciosDAO = _archivoFirmaDeServiciosDAO;
    }

    public String getArchivoFirmasTraspasoDeDatos() {
        return _archivoFirmasTraspasoDeDatos;
    }

    public void setArchivoFirmasTraspasoDeDatos(String _archivoFirmasTraspasoDeDatos) {
        this._archivoFirmasTraspasoDeDatos = _archivoFirmasTraspasoDeDatos;
    }

    public String getArchivoImplementacionServicioDAO() {
        return _archivoImplementacionServicioDAO;
    }

    public void setArchivoImplementacionServicioDAO(String _archivoImplementacionServicioDAO) {
        this._archivoImplementacionServicioDAO = _archivoImplementacionServicioDAO;
    }

    public String getArchivoTraspasoDeDatos() {
        return _archivoTraspasoDeDatos;
    }

    public void setArchivoTraspasoDeDatos(String _archivoTraspasoDeDatos) {
        this._archivoTraspasoDeDatos = _archivoTraspasoDeDatos;
    }
	public String get_archivoJOLT() {
		return _archivoJOLT;
	}
	public void set_archivoJOLT(String archivoJOLT) {
		_archivoJOLT = archivoJOLT;
	}
     
       

  

}

