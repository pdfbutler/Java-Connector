package be.cadmus.client.model;

import be.cadmus.client.generic.Constants;
import be.cadmus.client.generic.Constants.Stage;

public class Metadata {

	private String userId;
	private String organizationId;
	private Stage stage;
	private String targetName;
	private String version = Constants.CURRENT_VERSION.getValue();
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getVersion() {
		return version;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
