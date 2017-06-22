package be.cadmus.client;

import java.util.HashMap;
import java.util.Map;

import be.cadmus.client.convert.Convertor;
import be.cadmus.client.generic.Constants.Stage;
import be.cadmus.client.model.ConvertResponse;
import be.cadmus.client.model.DatasourceList;
import be.cadmus.client.model.DatasourceSingle;
import be.cadmus.client.model.Datasources;
import be.cadmus.client.model.Metadata;

public class PdfButlerClientApplication {

	public static void main(String[] args) {
		
		//create metadata
		Metadata metadata = new Metadata();
		//not required: passes for instance the department that uses PDF Butler
		metadata.setOrganizationId("CloudCrossing.Sales");
		//required: indicate which version of the document to call
		metadata.setStage(Stage.TEST);
		//not required: passes the title to replace
		metadata.setTargetName("[[!AccountName!]]_[[!StageName!]].pdf");
		//not required: use this field to log who used PDF Butler in your organization
		metadata.setUserId("istuyver");

		//Create datasources
		Datasources datasources = new Datasources();
		DatasourceSingle s1 = datasources.getSingle("18393bdc-1445-4cf0-8e05-79fdb9e0d7ec");
		s1.addData("OppOwner", "Igor Stuyver");
		s1.addData("AccountName", "CloudCrossing");
		s1.addData("StageName", "Closed Won");
		
		DatasourceList sl1 = datasources.getList("62dbb7d8-6c49-4f40-8d4b-8a60e1a00f23");
		Map<String, String> list = new HashMap<String, String>();
		list.put("ProdName", "Prod 1");
		list.put("ProdPrice", "1000");
		list.put("ProdQuantity", "2");
		sl1.addData(list);
		
		Map<String, String> list2 = new HashMap<String, String>();
		list.put("ProdName", "Prod 2");
		list.put("ProdPrice", "500");
		list.put("ProdQuantity", "12");
		sl1.addData(list2);
		
		//for(int i=0;i<10;i++) {
			ConvertResponse resp = Convertor.doConvert("<YOUR USERNAME>", "<YOUR PASSWORD>", metadata, datasources, "<YOUR DOC CONFIG ID>");
			System.out.println("PDF Butler ready with status: " + resp.getResult());
		//}
	}
}
