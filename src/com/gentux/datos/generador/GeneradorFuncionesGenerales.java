package com.gentux.datos.generador;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;

import com.gentux.datos.dto.Column;
import com.gentux.datos.dto.Table;
import com.gentux.datos.template.ClaseFunHTemplate;
import com.gentux.datos.template.ClaseFunTemplate;
import com.gentux.datos.template.ClaseImportarFunTemplate;

public class GeneradorFuncionesGenerales{

	
	public static void main(String args[]) {

		Connection con = null;
		PreparedStatement columnsStatement;

		String queryColumns = " select * "
				+ " from  user_tab_columns"
				+ " where table_name = ? "
				+ " order by table_name, column_id";

		try {
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			Configuration _configBD = new PropertiesConfiguration("config/baseDeDatos.properties");
			Configuration nomenclatura = new PropertiesConfiguration("config/nomenclatura.properties");
			con = DriverManager.getConnection(_configBD.getString("url"), _configBD.getString("usuario"), _configBD.getString("password"));
			

			columnsStatement = con.prepareStatement(queryColumns);

			
			Configuration config = new PropertiesConfiguration("config/generador.properties");
			
			StringBuffer resultadoFinal = new StringBuffer();
			StringBuffer resultadoFinalH = new StringBuffer();
			
			File _directorioSalida = new File("out");
			File fileC = new File(_directorioSalida+"/"+nomenclatura.getString("nombre_archivo_funciones"));
			
			ClaseImportarFunTemplate generateImportCodeC = new ClaseImportarFunTemplate();
		    resultadoFinal.append(generateImportCodeC.generate(null));
			
			List<Object> listaTablas =          config.getList("nombre_tabla");
			List<Object> listaNombresLogicos =  config.getList("nombrelogico_tabla");

			
			for (int i = 0; i < listaTablas.size(); i++) {

				Table table = new Table();
				table.setName((String) listaNombresLogicos.get(i));
				table.setTable_name((String) listaTablas.get(i));
				System.out.println("nombre tabla="+table.getTable_name());
				
				//TODO validar si existe tabla
				
				columnsStatement.setString(1, table.getTable_name());
				ResultSet rs = columnsStatement.executeQuery();

				ArrayList<Column> listaColumnas = new ArrayList<Column>();
				
				
				while (rs.next()) {
					Column columna = new Column();
					columna.setColumn_name(rs.getString("column_name"));
					columna.setData_type(rs.getString("data_type"));
					columna.setData_precision(rs.getInt("data_precision"));
					columna.setData_length(rs.getInt("data_length"));
					columna.setData_scale(rs.getInt("data_scale"));
					listaColumnas.add(columna);
				}
				table.setColumnas(listaColumnas);
				
				ClaseFunTemplate generateCodeC = new ClaseFunTemplate();
			    resultadoFinal.append(generateCodeC.generate(table)).append("\n");
			    
			    ClaseFunHTemplate generateCodeH = new ClaseFunHTemplate();
			    resultadoFinalH.append(generateCodeH.generate(table));
			    
			}

			try {
				FileUtils.writeStringToFile(fileC, resultadoFinal.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.print("No fue posible escribir en archivo");
				e.printStackTrace();
			}
			
			File _fileH = new File(_directorioSalida+"/"+nomenclatura.getString("nombre_archivo_def_funciones"));
			try {
				FileUtils.writeStringToFile(_fileH, resultadoFinalH.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.print("No fue posible escribir en archivo");
				e.printStackTrace();
			}


			
			columnsStatement.close();
			con.close();

			System.out.println("Programa finalizado.");
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back");
					con.rollback();
				} catch (SQLException excep) {
					System.err.print("SQLException: ");
					System.err.println(excep.getMessage());
				}
			}
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 
	
}

