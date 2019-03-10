package com.gentux.negocio.dto;

import java.util.ArrayList;

public class Service {

	private String name;
	private String description;
	private String logicEntity;
	private String type;
	
	private ArrayList<Parameter> parameters = new ArrayList<Parameter>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(ArrayList<Parameter> list) {
		this.parameters = list;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLogicEntity() {
		return logicEntity;
	}
	public void setLogicEntity(String logicEntity) {
		this.logicEntity = logicEntity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
