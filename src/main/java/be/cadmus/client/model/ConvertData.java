package be.cadmus.client.model;

public class ConvertData {

	private Metadata metadata;
	private String customerDocumentConfigId;
	private String dataSources;
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public String getDataSources() {
		return dataSources;
	}
	public void setDataSources(String json) {
		this.dataSources = json;
	}
	public String getCustomerDocumentConfigId() {
		return customerDocumentConfigId;
	}
	public void setCustomerDocumentConfigId(String customerDocumentConfigId) {
		this.customerDocumentConfigId = customerDocumentConfigId;
	}
}
