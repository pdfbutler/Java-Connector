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
import be.cadmus.client.model.DatasourcePicture;
import be.cadmus.client.model.DatasourcePicture.Picture;
import be.cadmus.client.model.DatasourceSingle;
import be.cadmus.client.model.Datasources;
import be.cadmus.client.model.Metadata;

public class PdfButlerAdvancedClientApplication {

	public static void main(String[] args) throws Exception {
		
		//create metadata
		Metadata metadata = new Metadata();
		//not required: passes for instance the department that uses PDF Butler
		metadata.setOrganizationId("CloudCrossing.Sales");
		//required: indicate which version of the document to call
		metadata.setStage(Stage.TEST);
		//not required: passes the title to replace
		metadata.setTargetName("PdfButlerAdvanced");
		//not required: use this field to log who used PDF Butler in your organization
		metadata.setUserId("end system user");
		metadata.setTargetType(ConvertFileType.PDF);

		//Create datasources
		Datasources datasources = new Datasources();
		DatasourceSingle account = datasources.getSingle("<YOUR ACCOUNT DATASOURCE ID>");
		account.addData("Id", "Acc1");
		account.addData("Name", "CloudCrossing");
		account.addData("Phone", "555/12345678");
		account.addData("Fax", "555/87654321");

		DatasourceList opportunities = datasources.getList("<YOUR OPPORTUNITIES DATASOURCE ID>");
		Map<String, String> map = opportunities.addMap();
		map.put("Id", "Opp1");
		map.put("OppName", "500.000 widgets");
		map.put("StageName", "Ready to sign");
		map.put("AccountId", "Acc1");
		
		map = opportunities.addMap();
		map.put("Id", "Opp2");
		map.put("OppName", "200.000 widgets");
		map.put("StageName", "Closed Won");
		map.put("AccountId", "Acc1");
		
		map = opportunities.addMap();
		map.put("Id", "Opp3");
		map.put("OppName", "5.000 widgets");
		map.put("StageName", "Closed Lost");
		map.put("AccountId", "Acc1");

		
		DatasourceList opportunityProducts = datasources.getList("<YOUR OPPORTUNITY PRODUCTS DATASOURCE ID>");
		map = opportunityProducts.addMap();
		map.put("ProductName", "Widget 1");
		map.put("Quantity", "200.000");
		map.put("OpportunityId", "Opp1");
		map.put("UnitPrice", "50");
		map.put("ProductCode", "100");
		map.put("TemplateId", "<MOTORS TEMPLATE ID>");
		
		map = opportunityProducts.addMap();
		map.put("ProductName", "Widget 2");
		map.put("Quantity", "300.000");
		map.put("OpportunityId", "Opp1");
		map.put("UnitPrice", "70");
		map.put("ProductCode", "200");
		map.put("TemplateId", "<OPTIONS TEMPLATE ID>");
		
		map = opportunityProducts.addMap();
		map.put("ProductName", "Widget 2");
		map.put("Quantity", "200.000");
		map.put("OpportunityId", "Opp2");
		map.put("UnitPrice", "70");
		map.put("ProductCode", "200");
		map.put("TemplateId", "<MOTORS TEMPLATE ID>");
		
		map = opportunityProducts.addMap();
		map.put("ProductName", "Widget 3");
		map.put("Quantity", "5.000");
		map.put("OpportunityId", "Opp3");
		map.put("UnitPrice", "1000");
		map.put("ProductCode", "300");
		map.put("TemplateId", "<OPTIONS TEMPLATE ID>");

		DatasourcePicture logo = datasources.getPicture("<YOUR LOGO DATASOURCE ID>");
		Picture logo1 = logo.addPicture();
		logo1.setFile(new File("C:/Users/istuyver/Pictures/pdfbutlerlogo.png"));
		logo1.setName("Pdf Butler Logo");
		logo1.setParentId(null);

		DatasourcePicture accountLogo = datasources.getPicture("<YOUR ACCOUNT LOGO DATASOURCE ID>");
		Picture accountLogo1 = accountLogo.addPicture();
		accountLogo1.setFile(new File("C:/Users/istuyver/Pictures/cadmus_arch.png"));
		accountLogo1.setName("Pdf Butler Architecture");
		accountLogo1.setParentId("Acc1");
		
		
		//!! be aware that this implementation requires the password for the USER role
		ConvertResponse resp = Convertor.doConvert("<YOUR USERNAME>", "<YOUR PASSWORD>", metadata, datasources, "<YOUR DOC CONFIG ID>");
		System.out.println("PDF Butler ready with status: " + resp.getResult());
		System.out.println("PDF Butler ready with target name: " + resp.getMetadata().getTargetName());
		
		FileUtils.writeByteArrayToFile(new File( resp.getMetadata().getTargetName() ), Base64Utils.decodeFromString( resp.getBase64() ));
	}
}
