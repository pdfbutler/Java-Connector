package be.cadmus.client;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

import be.cadmus.client.convert.Convertor;
import be.cadmus.client.generic.Constants.ConvertFileType;
import be.cadmus.client.generic.Constants.Stage;
import be.cadmus.client.model.ConvertResponse;
import be.cadmus.client.model.DatasourceList;
import be.cadmus.client.model.DatasourceSingle;
import be.cadmus.client.model.Datasources;
import be.cadmus.client.model.Metadata;

public class PdfButlerClientApplication {

	public static void main(String[] args) throws Exception {
		
		//create metadata
		Metadata metadata = new Metadata();
		//not required: passes for instance the department that uses PDF Butler
		metadata.setOrganizationId("CloudCrossing.Sales");
		//required: indicate which version of the document to call
		metadata.setStage(Stage.TEST);
		//not required: passes the title to replace
		metadata.setTargetName("[[!AccountName!]]_[[!StageName!]]");
		//not required: use this field to log who used PDF Butler in your organization
		metadata.setUserId("end system user");
		metadata.setTargetType(ConvertFileType.PDF);

		//Create datasources
		Datasources datasources = new Datasources();
		DatasourceSingle account = datasources.getSingle("<YOUR ACCOUNT DATASOURCE ID>");
		account.addData("OppOwner", "Igor Stuyver");
		account.addData("AccountName", "CloudCrossing");
		account.addData("StageName", "Closed Won");

		DatasourceList products = datasources.getList("<YOUR PRODUCTS DATASOURCE ID>");
		Map<String, String> map = products.addMap();
		map.put("ProdName", "Gizmo 1");
		map.put("ProdPrice", "1000");
		map.put("ProdQuantity", "3");
		
		map = products.addMap();
		map.put("ProdName", "Gizmo 2");
		map.put("ProdPrice", "500");
		map.put("ProdQuantity", "4");
		
		map = products.addMap();
		map.put("ProdName", "Gizmo 3");
		map.put("ProdPrice", "10");
		map.put("ProdQuantity", "50");
		
		//!! be aware that this implementation requires the password for the USER role
		ConvertResponse resp = Convertor.doConvert("<YOUR USERNAME>", "<YOUR PASSWORD>", metadata, datasources, "<YOUR DOC CONFIG ID>");
		System.out.println("PDF Butler ready with status: " + resp.getResult());
		System.out.println("PDF Butler ready with target name: " + resp.getMetadata().getTargetName());
		
		FileUtils.writeByteArrayToFile(new File( "C:/Temp/Temp/" + resp.getMetadata().getTargetName() ), Base64Utils.decodeFromString( resp.getBase64() ));
	}
}
