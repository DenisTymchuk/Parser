package com.exoplatform.testtask.qname;

import com.exoplatform.testtask.exception.IllegalNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QNameParser {
	private static final String PREFIX_TEMPLATE = "(?!xml|Xml|xMl|XMl|xmL|XmL|xML|XML)[a-zA-Z_][a-zA-Z0-9-_.]+:";
	private static final String ONE_TWO_CHAR_LOCAL_NAME_TEMPLATE = "[^./:\\]\\[*'\"|\\s]{1,2}";
	private static final String THREE_OR_MORE_NAME_TEMPLATE = "[^/:\\]\\[*'\"|\\s][^/:\\]\\[*'\"|]{1,}[^/:\\]\\[*'\"|\\s]";
	private static final String ONE_CHAR_SIMPLE_NAME_TEMPLATE = "[^./:\\]\\[*'\"|\\s]";
	private static final String TWO_CHAR_SIMPLE_NAME_TEMPLATE = "(?:[.][^./:\\]\\[*'\"|\\s])|(?:[^./:\\]\\[*'\"|\\s][.])|(?:[^./:\\]\\[*'\"|\\s]{2,2})";

	private static final Pattern NAME_PATTERN = Pattern.compile("^(?:(?:(" + PREFIX_TEMPLATE + ")" +
			"((?:" + ONE_TWO_CHAR_LOCAL_NAME_TEMPLATE + ")" +
			"|(?:" + THREE_OR_MORE_NAME_TEMPLATE + ")" +
			"))" +
			"|" +
			"((?:" + ONE_CHAR_SIMPLE_NAME_TEMPLATE + ")" +
			"|(?:" + TWO_CHAR_SIMPLE_NAME_TEMPLATE + ")" +
			"|(?:" + THREE_OR_MORE_NAME_TEMPLATE + ")))$");

	public static QName parse(String name) throws IllegalNameException {
		Matcher matcher = NAME_PATTERN.matcher(name);
		QName qName = new QName();

		if (matcher.find()) {
			try {
				String prefix = matcher.group(1);
				if (prefix != null) {
					qName.setPrefix(prefix.substring(0, prefix.length() - 1));
					qName.setLocalName(matcher.group(2));
				} else {
					qName.setLocalName(matcher.group(3));
				}
			} catch (NullPointerException exception) {
				throw new IllegalNameException();
			}

			return qName;
		} else {
			throw new IllegalNameException("Name cannot be parse");
		}
	}
}
