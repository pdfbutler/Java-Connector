package be.cadmus.client.model;

import java.util.HashMap;
import java.util.Map;

import be.cadmus.client.generic.Constants.DataSourceType;

public class DatasourceSingle extends AbstractDatasource {

	private Map<String, String> data = new HashMap<String, String>();
	
	protected DatasourceSingle() {
		this.setType(DataSourceType.SINGLE);
	}
	public Map<String, String> getData() {
		return data;
	}
	public void addData(String key, String value) {
		this.data.put(key, value);
	}
}
