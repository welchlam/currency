package com.welch.conversion.object;

public class Rule {
	private String envName;
	private String tagName;
	private String attribute;
	private String oldValue;
	private String newValue;
	private String converType;

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		if (newValue == null) {
			newValue = "";
		}
		this.newValue = newValue;
	}

	public String getConverType() {
		return converType;
	}

	public void setConverType(String converType) {
		this.converType = converType;
	}

}
