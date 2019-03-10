package com.gentux.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.gentux.datos.dto.Column;
import com.gentux.datos.dto.Table;

public class CatalogoDAO {
	
    private String _archivoTablasSeleccionadas = "config/generador.properties";
    private String _archivoConexionEsquemaBaseDeDatos = "config/baseDeDatos.properties";

    public CatalogoDAO() {
    }


    public CatalogoDAO(String _archivoTablasSeleccionadas, String _archivoConexionEsquemaBaseDeDatos) {
        this._archivoTablasSeleccionadas = _archivoTablasSeleccionadas;
        this._archivoConexionEsquemaBaseDeDatos = _archivoConexionEsquemaBaseDeDatos;
    }


		public ArrayList<Table> getCatalogo() throws ConfigurationException{
		Configuration config = new PropertiesConfiguration(_archivoTablasSeleccionadas);
		
		List<Object> listaTablas =          config.getList("nombre_tabla");
		List<Object> listaNombresLogicos =   config.getList("nombrelogico_tabla");
		
		ArrayList<Table> _tables = new ArrayList<Table>();
		for (int i = 0; i < listaTablas.size(); i++) {

			Table table = getTable((String) listaTablas.get(i));
			table.setName((String) listaNombresLogicos.get(i));
			_tables.add(table);
		}	
		return _tables;
		
	}
	
		
	public  Table getTable(String name) throws ConfigurationException {
		
		String queryColumns = " select table_name, column_name, data_type, data_precision, data_length, data_scale "
				+ " from  user_tab_columns"
				+ " where table_name = ? "
				+ " order by table_name, column_id";
		
		String keyQuery = " select user_ind_columns.column_name, user_tab_columns.data_type, user_tab_columns.data_precision, user_tab_columns.data_length, user_tab_columns.data_scale "
			+ " from user_ind_columns, user_indexes, user_tab_columns"
			+ " where  user_indexes.uniqueness = 'UNIQUE' "
			+ "    and user_ind_columns.table_name = ? "
			+ "    and user_indexes.INDEX_NAME = user_ind_columns.INDEX_NAME "
			+ "    and user_ind_columns.table_name = user_tab_columns.table_name"
			+ "    and user_ind_columns.column_name = user_tab_columns.column_name"
			+ " order by user_ind_columns.index_name, user_ind_columns.column_position";

		
		Connection con = null;
		PreparedStatement columnsStatement = null;
		PreparedStatement primaryKeyStatement = null;

		Table table = new Table();
		table.setTable_name(name);
		try {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("No hay driver");

            }
			Configuration _configBD = new PropertiesConfiguration(this._archivoConexionEsquemaBaseDeDatos);
                        con = DriverManager.getConnection(_configBD.getString("url"), _configBD.getString("usuario"), _configBD.getString("password"));
			columnsStatement = con.prepareStatement(queryColumns);
			primaryKeyStatement = con.prepareStatement(keyQuery);
			
			columnsStatement.setString(1, name);
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

			ArrayList<Column> listaColumnasClavePrimaria = new ArrayList<Column>();
			primaryKeyStatement.setString(1, table.getTable_name());
			ResultSet resultSet = primaryKeyStatement.executeQuery();
			while (resultSet.next()) {
				Column columnaClave = new Column();
				columnaClave.setColumn_name(resultSet.getString("column_name"));
				columnaClave.setData_type(resultSet.getString("data_type"));
				columnaClave.setData_precision(resultSet.getInt("data_precision"));
				columnaClave.setData_length(resultSet.getInt("data_length"));
				columnaClave.setData_scale(resultSet.getInt("data_scale"));
				
				listaColumnasClavePrimaria.add(columnaClave);
			}
			
			table.setColumnasDeClaveUnicaPrincipal(listaColumnasClavePrimaria);
			// TODO obtener columnas no pertenecen a clave principal
			table.setColumnasNoPertenecenAClaveUnica(listaColumnas);

		} catch (SQLException e) {
                        Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, e);
			System.err.println("SQLException: " + e.getMessage());
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
		} finally {
			try {
				columnsStatement.close();
				primaryKeyStatement.close();
			    con.close();
			} catch (SQLException e) {
                                Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, e);
				System.err.println("SQLException: " + e.getMessage());
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
			}

		}

		return table;
	}

}
