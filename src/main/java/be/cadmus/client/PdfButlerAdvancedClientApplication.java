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
		//DatasourceSingle account = datasources.getSingle("<YOUR ACCOUNT DATASOURCE ID>");
		DatasourceSingle account = datasources.getSingle("7c52438e-58b8-442f-9471-1f5d9e3bd67f");
		account.addData("Id", "Acc1");
		account.addData("Name", "CloudCrossing");
		account.addData("Phone", "555/12345678");
		account.addData("Fax", "555/87654321");

		//DatasourceList opportunities = datasources.getList("<YOUR OPPORTUNITIES DATASOURCE ID>");
		DatasourceList opportunities = datasources.getList("f684dffa-1c54-482b-908b-1f5bac8c282f");
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

		
		//DatasourceList opportunityProducts = datasources.getList("<YOUR OPPORTUNITY PRODUCTS DATASOURCE ID>");
		DatasourceList opportunityProducts = datasources.getList("0a10137c-a6be-4b9c-abad-a47f76032c8c");
		map = opportunityProducts.addMap();
		map.put("ProductName", "Widget 1");
		map.put("Quantity", "200.000");
		map.put("OpportunityId", "Opp1");
		map.put("UnitPrice", "50");
		map.put("ProductCode", "100");
		map.put("TemplateId", "fc669f06-9f5e-4595-be09-a2235d6f94fe");
		
		map = opportunityProducts.addMap();
		map.put("ProductName", "Widget 2");
		map.put("Quantity", "300.000");
		map.put("OpportunityId", "Opp1");
		map.put("UnitPrice", "70");
		map.put("ProductCode", "200");
		map.put("TemplateId", "16f468ff-8f92-4762-8fdd-b6a09a1f6b08");
		
		map = opportunityProducts.addMap();
		map.put("ProductName", "Widget 2");
		map.put("Quantity", "200.000");
		map.put("OpportunityId", "Opp2");
		map.put("UnitPrice", "70");
		map.put("ProductCode", "200");
		map.put("TemplateId", "fc669f06-9f5e-4595-be09-a2235d6f94fe");
		
		map = opportunityProducts.addMap();
		map.put("ProductName", "Widget 3");
		map.put("Quantity", "5.000");
		map.put("OpportunityId", "Opp3");
		map.put("UnitPrice", "1000");
		map.put("ProductCode", "300");
		map.put("TemplateId", "16f468ff-8f92-4762-8fdd-b6a09a1f6b08");

		//DatasourcePicture logo = datasources.getPicture("<YOUR LOGO DATASOURCE ID>");
		DatasourcePicture logo = datasources.getPicture("7f9b1ef3-26b6-41f3-8fe9-ba20334481b7");
		Picture logo1 = logo.addPicture();
		logo1.setFile(new File("C:/Users/istuyver/Pictures/pdfbutlerlogo.png"));
		logo1.setName("Pdf Butler Logo");
		logo1.setParentId(null);

		//DatasourcePicture accountLogo = datasources.getPicture("<YOUR ACCOUNT LOGO DATASOURCE ID>");
		DatasourcePicture accountLogo = datasources.getPicture("0bdf1f1d-d745-47fd-802c-0c732f906262");
		Picture accountLogo1 = accountLogo.addPicture();
		accountLogo1.setFile(new File("C:/Users/istuyver/Pictures/cadmus_arch.png"));
		accountLogo1.setName("Pdf Butler Architecture");
		accountLogo1.setParentId("Acc1");
		
		
		//!! be aware that this implementation requires the password for the USER role
		//ConvertResponse resp = Convertor.doConvert("<YOUR USERNAME>", "<YOUR PASSWORD>", metadata, datasources, "<YOUR DOC CONFIG ID>");
		ConvertResponse resp = Convertor.doConvert("istuyver", "29f34ab1-baa0-419c-96fa-5ed9428d1e20", metadata, datasources, "95f4e257-1a4a-46ff-a6a8-6c3628c26906");
		System.out.println("PDF Butler ready with status: " + resp.getResult());
		System.out.println("PDF Butler ready with target name: " + resp.getMetadata().getTargetName());
		
		FileUtils.writeByteArrayToFile(new File( "C:/Temp/Temp/" + resp.getMetadata().getTargetName() ), Base64Utils.decodeFromString( resp.getBase64() ));
	}
}
