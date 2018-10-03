package com.exoplatform.testtask.qname;

/**
 * Qualified name entity
 *
 * @author  Denys Tymchuk
 */
public class QName {

	/**
	 * The prefix of name
	 */
	private String prefix;

	/**
	 * The local name
	 */
	private String localName;

	public QName(String prefix, String localName) {
		this.prefix = prefix;
		this.localName = localName;
	}

	public QName() {
	}

	/**
	 * @return {@link QName#prefix}
	 */
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return {@link QName#localName}
	 */
	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	/**
	 * @return {@link QName#localName} and {@link QName#prefix} if the prefix is not null and not empty,
	 * otherwise only {@link QName#localName}
     */
	public String getAsString() {
		return (prefix != null && !prefix.isEmpty()) ? prefix + ":" + localName : localName;
	}
}
