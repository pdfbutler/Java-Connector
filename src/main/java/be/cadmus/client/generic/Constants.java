package be.cadmus.client.generic;

public class Constants {

	public static final String MERGE_FIELD_PREFIX = "[[!";
	public static final String MERGE_FIELD_SUFFIX = "!]]";
	public static final String WORD_EXTENSION = ".docx";
	public static final String PDF_EXTENSION = ".pdf";
	public static final String ROLE_SEPERATOR = "-";
	public static final Version CURRENT_VERSION = Version.V1_0;
	
	public enum Version {
		V1_0("v1.0");
		
		private String version;
		Version(String version) {this.version=version;}
		public String getValue() {return this.version;}
	}
	
	public enum Stage {
		PROD,
		TEST
	}

	public enum EnvironmentType {
		DEV,
		PROD, 
	}

	public enum IssueLevel {
		INFO,
		WARNING, 
		ERROR 
	}

	public enum ConfigDataResult {
		SUCCESS,
		SUCCESS_WITH_WARNINGS, 
		FAILED 
	}

	public enum DataSourceType {
		SINGLE,
		LIST
	}

	public enum UserRole {
		ADMIN,
		USER
	}
}
