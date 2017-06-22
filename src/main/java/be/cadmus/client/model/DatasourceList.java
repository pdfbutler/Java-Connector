package be.cadmus.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.cadmus.client.generic.Constants.DataSourceType;

public class DatasourceList extends AbstractDatasource {
	
	protected DatasourceList() {
		this.setType(DataSourceType.LIST);
	}

	private List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	
	public List<Map<String, String>> getData() {
		return data;
	}
	public void addData(Map<String, String> item) {
		this.data.add(item);
	}
}
