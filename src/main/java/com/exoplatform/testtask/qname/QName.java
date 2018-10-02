package com.exoplatform.testtask.qname;

public class QName {

	private String prefix;

	private String localName;

	public QName(String prefix, String localName) {
		this.prefix = prefix;
		this.localName = localName;
	}

	public QName() {
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getAsString() {
		return (prefix != null && !prefix.trim().isEmpty()) ? prefix + ":" + localName : localName;
	}
}
