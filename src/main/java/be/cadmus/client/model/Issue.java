package be.cadmus.client.model;

import be.cadmus.client.generic.Constants.IssueLevel;

public class Issue {

	private String description;
	private IssueLevel level;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public IssueLevel getLevel() {
		return level;
	}
	public void setLevel(IssueLevel level) {
		this.level = level;
	}
}
