package com.gentux.datos.dto;

import com.gentux.datos.util.GeneradorUtil;

public class Column {

	private String column_name;
	private boolean isKey;

	private String data_type;
	private Integer data_precision;
	private Integer data_scale;
	
	private Integer data_length;
	
	// del struct, nombres fisicos
	private String structDataType;
	private String structField;
    
	private String fmlType;

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}



	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public Integer getData_precision() {
		return data_precision;
	}

	public void setData_precision(Integer data_precision) {
		this.data_precision = data_precision;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	public String getStructDataType() {
		
		if (this.getData_type().equals("NUMBER")) {
           if (this.getData_precision() != null  && this.getData_precision().intValue() <= 4) {		
 			   structDataType = "short";
           } else if (this.getData_precision() != null &&
        		      this.getData_scale()     != null &&
        		      this.getData_scale().intValue() > 0) {
				structDataType = "double";
           } else if (this.getData_precision() <= 9 ){
				structDataType = "long";
		   } else
			   structDataType = "double";
		} else if (this.getData_type().equals("VARCHAR2") ||
				   this.getData_type().equals("CHAR") || 
				   this.getData_type().equals("DATE")) {
			structDataType = "char";	
		}
          
		return structDataType;
	}

	public void setStructType(String structType) {
		this.structDataType = structType;
	}
	
    public String getFmlType() {
		
		if (this.getData_type().equals("NUMBER")) {
           if (this.getData_precision() != null  && this.getData_precision().intValue() <= 4) {		
 			   fmlType = "short";
           } else if (this.getData_precision() != null &&
        		      this.getData_scale()     != null &&
        		      this.getData_scale().intValue() > 0) {
        	   fmlType = "double";
           }  else if (this.getData_precision() <= 9 ){
        	   fmlType = "long";
		   } else {
			   fmlType = "double";
		   }		   ;
			
		} else if (this.getData_type().equals("VARCHAR2") ||
				   this.getData_type().equals("CHAR") || 
				   this.getData_type().equals("DATE")) {
			fmlType = "string";	
		}
          
		return fmlType;
	}

	public String getStructField() {
		String _structField = GeneradorUtil.convertFieldToVariable(this.getColumn_name());
		if (this.getData_type().equals("VARCHAR2")  || this.getData_type().equals("CHAR") ) {
			_structField = _structField + "["+this.getData_length()+"+1]" ;	
		} else if  (this.getData_type().equals("DATE")) {
			_structField = _structField + "[14+1]" ;
		}
		structField = _structField ;
		return structField;
	}
	
	public void setStructField(String structField) {
		this.structField = structField;
	}

	public Integer getData_length() {
		return data_length;
	}

	public void setData_length(Integer data_length) {
		this.data_length = data_length;
	}

	public Integer getData_scale() {
		return data_scale;
	}

	public void setData_scale(Integer data_scale) {
		this.data_scale = data_scale;
	}

    


	
}
