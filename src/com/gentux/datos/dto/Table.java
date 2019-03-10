package com.gentux.datos.dto;

import java.util.ArrayList;

public class Table {

	/**
	 * Nombre lógico en minúscula
	 */
	private String name;  
	private String nameWithFirstLetterUpperCase;
	private String table_name;
	
	private ArrayList<Column> columnas;
	private ArrayList<Column> columnasDeClaveUnicaPrincipal;
	private ArrayList<Column> columnasNoPertenecenAClaveUnica;
	
	// nombres físicos
	private String structName;
	
	
	public Table() {
		columnas = new ArrayList<Column>();
	}
	public Table(String name) {
		super();
		this.name = name.toLowerCase();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Column> getColumnas() {
		return columnas;
	}
	public void setColumnas(ArrayList<Column> columnas) {
		this.columnas = columnas;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public ArrayList<Column> getColumnasDeClaveUnicaPrincipal() {
		return columnasDeClaveUnicaPrincipal;
	}
	public void setColumnasDeClaveUnicaPrincipal(
			ArrayList<Column> columnasDeClaveUnicaPrincipal) {
		this.columnasDeClaveUnicaPrincipal = columnasDeClaveUnicaPrincipal;
	}
	public ArrayList<Column> getColumnasNoPertenecenAClaveUnica() {
		return columnasNoPertenecenAClaveUnica;
	}
	public void setColumnasNoPertenecenAClaveUnica(
			ArrayList<Column> columnasNoPertenecenAClaveUnica) {
		this.columnasNoPertenecenAClaveUnica = columnasNoPertenecenAClaveUnica;
	}
	public String getNameWithFirstLetterUpperCase() {
		this.setNameWithFirstLetterUpperCase(this.name);
		return this.nameWithFirstLetterUpperCase ;
	}
	public void setNameWithFirstLetterUpperCase(String name) {
		String primeraLetra = name.substring(0,1).toUpperCase();
		String restoDeNombre = name.substring(1, name.length());
		nameWithFirstLetterUpperCase = primeraLetra+restoDeNombre;
		
	}
	public String getStructName() {
		
		return structName;
	}
	public void setStructName(String structName) {
		this.structName = structName;
	}
    
   	
	
}
