package be.cadmus.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

public class Datasources {

	private List<AbstractDatasource> datasources = new ArrayList<AbstractDatasource>();
	
	public DatasourceSingle getSingle(String name) {
		DatasourceSingle temp = new DatasourceSingle();
		temp.setName(name);
		this.datasources.add(temp);
		
		return temp;
	}

	public DatasourceList getList(String name) {
		DatasourceList temp = new DatasourceList();
		temp.setName(name);
		this.datasources.add(temp);
		
		return temp;
	}

	public DatasourcePicture getPicture(String name) {
		DatasourcePicture temp = new DatasourcePicture();
		temp.setName(name);
		this.datasources.add(temp);
		
		return temp;
	}

	public List<AbstractDatasource> getDatasources() {
		return datasources;
	}
	
	public String getString() {
		
		JSONObject retObject = new JSONObject();
		
		for(AbstractDatasource ad : datasources) {
			JSONObject dsItem = new JSONObject();
			dsItem.put("type", ad.getType().toString());
			retObject.put(ad.getName(), dsItem);
			
			if(ad instanceof DatasourceSingle) {
				DatasourceSingle ds = (DatasourceSingle)ad;

				JSONObject data = new JSONObject();
				dsItem.put("data", data);
				
				for(Entry<String, String> entry : ds.getData().entrySet()) {
					data.put(entry.getKey(), entry.getValue());
				}
			} else if(ad instanceof DatasourceList) {
				DatasourceList ds = (DatasourceList)ad;

				JSONArray data = new JSONArray(ds.getData());
				dsItem.put("data", data);
			} else if(ad instanceof DatasourcePicture) {
				DatasourcePicture ds = (DatasourcePicture)ad;

				JSONArray data = new JSONArray(ds.getData());
				dsItem.put("data", data);
			} else {
				throw new RuntimeException("This datasource type is not supported: " + ad.getType().toString());
			}
		}
		return retObject.toString();
		//return JSONObject.valueToString(this.datasources);
	}
}
