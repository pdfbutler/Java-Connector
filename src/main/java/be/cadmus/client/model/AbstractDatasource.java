package be.cadmus.client.model;

import be.cadmus.client.generic.Constants.DataSourceType;

public abstract class AbstractDatasource {

	private String name;
	private DataSourceType type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataSourceType getType() {
		return type;
	}
	protected void setType(DataSourceType type) {
		this.type = type;
	}
}
