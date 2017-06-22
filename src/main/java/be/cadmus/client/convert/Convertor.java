package be.cadmus.client.convert;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.cadmus.client.generic.Constants;
import be.cadmus.client.generic.Constants.UserRole;
import be.cadmus.client.model.ConvertData;
import be.cadmus.client.model.ConvertResponse;
import be.cadmus.client.model.Datasources;
import be.cadmus.client.model.Metadata;

public class Convertor {
	
	private static String pdfButlerUrl = "https://www.pdfbutler.com/convert";

	public static ConvertResponse doConvert(String username, String password, Metadata metadata, Datasources datasources, String docConfigId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic "+ createBearer(username, password));
		ConvertData convertData = new ConvertData();
		convertData.setMetadata(metadata);
		convertData.setDataSources(datasources.getString());
		convertData.setCustomerDocumentConfigId(docConfigId);

		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(convertData);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ConvertResponse result = restTemplate.postForObject(pdfButlerUrl, entity, ConvertResponse.class);
		
		return result;
	}
	
	private static String createBearer(String username, String password) {
		//create base64
		//use USER type of username
		
		String authString = username + Constants.ROLE_SEPERATOR + UserRole.USER.toString() + ":" + password;
		return Base64Utils.encodeToString(authString.getBytes());
	}

	public static String getPdfButlerUrl() {
		return pdfButlerUrl;
	}

	public static void setPdfButlerUrl(String pdfButlerUrl) {
		Convertor.pdfButlerUrl = pdfButlerUrl;
	}
}
