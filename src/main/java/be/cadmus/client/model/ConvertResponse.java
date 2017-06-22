package be.cadmus.client.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Base64Utils;

import be.cadmus.client.generic.Constants.ConfigDataResult;

public class ConvertResponse {

	private Metadata metadata;
	private String base64;
	private List<Issue> issues = new ArrayList<Issue>();
	private ConfigDataResult result;
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	public ConfigDataResult getResult() {
		return result;
	}
	public void setResult(ConfigDataResult result) {
		this.result = result;
	}
	
	//return file with filename of the metadata
	public File saveToFile(String path) {
		byte[] decodedBytes = Base64Utils.decodeFromString(base64);

		File file = new File("path" + this.metadata.getTargetName());
		FileOutputStream fop;
		try {
			fop = new FileOutputStream(file);
			fop.write(decodedBytes);
			fop.flush();
			fop.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return file;
	}
}
