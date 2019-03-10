package com.gentux.datos;

public class Constantes {

	public static String queryTable = " select table_name, column_name, data_type, data_precision, data_length, data_scale "
									+ " from  user_tab_columns"
									+ " where table_name = ? "
									+ " order by table_name, column_id";

	
}
