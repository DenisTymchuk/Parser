package com.exoplatform.testtask.qname;

import com.exoplatform.testtask.exception.IllegalNameException;
import org.junit.Test;

import static org.junit.Assert.*;

public class QNameParserTest {

	@Test
	public void parse_givenValidName_returnQName() throws IllegalNameException {
		QName qName = QNameParser.parse("prefix:name");

		assertEquals("prefix:name", qName.getAsString());
	}

	@Test
	public void parse_givenPrefixAndLocalNameWithWhitespace_returnQName() throws IllegalNameException {
		QName qName = QNameParser.parse("prefix:na me");

		assertEquals("prefix:na me", qName.getAsString());
	}

	@Test
	public void parse_givenTwoCharSimpleNameWithPoint_returnQName() throws IllegalNameException {
		QName qName = QNameParser.parse(".n");

		assertEquals(".n", qName.getAsString());
	}

	@Test
	public void parse_givenTwoCharSimpleName_returnQName() throws IllegalNameException {
		QName qName = QNameParser.parse("nm");

		assertEquals("nm", qName.getAsString());
	}

	@Test
	public void parse_givenLocalNameWithWhitespace_returnQName() throws IllegalNameException {
		QName qName = QNameParser.parse("name surname");

		assertEquals("name surname", qName.getAsString());
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenPrefixAndLocalNameBeginWhitespace_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse("prefix: fdfs");
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenEmptyPrefix_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse(":name");
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenPoint_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse(".");
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenTwoPoints_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse("..");
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenEmptyLocalName_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse("prefix:");
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenLocalNameBeginWhitespace_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse(" name");
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenPrefixBeginWhitespace_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse(" prefix:name");
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenPrefixWithWhiteSpace_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse("pre fix:name");
	}

	@Test(expected = IllegalNameException.class)
	public void parse_givenInvalidXMLName_throwException() throws IllegalNameException {
		QName qName = new QName("prefix", "name");

		QNameParser.parse("xmlfix:name");
	}
}