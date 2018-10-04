package com.exoplatform.testtask.qname;

import org.junit.Test;

import static org.junit.Assert.*;

public class QNameTest {

	@Test
	public void getAsString_givenPrefixAndLocalName_returnValidString() {
		QName qName = new QName("prefix", "name");

		assertEquals("prefix:name", qName.getAsString() );
	}

	@Test
	public void getAsString_givenOnlyLocalName_returnLocalNameString() {
		QName qName = new QName();
		qName.setLocalName("name");

		assertEquals("name", qName.getAsString() );
	}

	@Test
	public void getAsString_givenEmpyPrefix_returnLocalNameString() {
		QName qName = new QName();
		qName.setLocalName("name");
		qName.setPrefix("");

		assertEquals("name", qName.getAsString() );
	}
}