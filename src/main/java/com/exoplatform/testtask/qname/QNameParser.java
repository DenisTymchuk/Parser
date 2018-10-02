package com.exoplatform.testtask.qname;

import com.exoplatform.testtask.exception.IllegalNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used for constructs an valid QName object.
 * <p>Uses java.util.regex package for matching
 *
 * @author  Denys Tymchuk
 */
public class QNameParser {

	/**
	 * Template for matching prefix part of the word.
	 * <p>It could be any valid XML Name, which follow these naming rules:
	 * <ul>
	 *     <li>Element names cannot start with the letters xml (or XML, or Xml, etc)
	 *     <li>Element names can contain letters, digits, hyphens, underscores, and periods.
	 *     <li>Element names cannot contain spaces.
	 *     <li>Element names must start with a letter or underscore.
	 * </ul>
	 */
	private static final String PREFIX_TEMPLATE = "(?!xml|Xml|xMl|XMl|xmL|XmL|xML|XML)[a-zA-Z_][a-zA-Z0-9-_.]+:";

	/**
	 * Template for matching one or two char.
	 * <p>It could be any Unicode character except: /, :, [, ], *, ', ", | or any whitespace character.
	 */
	private static final String ONE_TWO_CHAR_LOCAL_NAME_TEMPLATE = "[^/:\\]\\[*'\"|\\s]{1,2}";

	/**
	 * Template for matching three or more char.
	 */
	private static final String THREE_OR_MORE_CHAR_NAME_TEMPLATE = "[^/:\\]\\[*'\"|\\s][^/:\\]\\[*'\"|]{1,}[^/:\\]\\[*'\"|\\s]";

	/**
	 * Template for matching one char. It's used for localName in case when word has no prefix.
	 * <p>It could be any Unicode character except: ., /, :, [, ], *, ', ", | or any whitespace character.
	 */
	private static final String ONE_CHAR_SIMPLE_NAME_TEMPLATE = "[^./:\\]\\[*'\"|\\s]";

	/**
	 * Template for matching two char. It's used for localName in case when word has no prefix.
	 * <p>There are 3 types of two-character sequence
	 * <ul>
	 * 	 <li>The first element is a point(.) and the next is any Unicode character except: ,. /, :, [, ], *, ', ", | or any whitespace character.
	 * 	 <li>The first element is any Unicode character except: ,. /, :, [, ], *, ', ", | or any whitespace character and the next is a point(.)
	 * 	 <li>Exactly 2 elements, which are any Unicode character except: ,. /, :, [, ], *, ', ", | or any whitespace character.
	 * </ul>
	 */
	private static final String TWO_CHAR_SIMPLE_NAME_TEMPLATE = "(?:[.][^./:\\]\\[*'\"|\\s])|(?:[^./:\\]\\[*'\"|\\s][.])|(?:[^./:\\]\\[*'\"|\\s]{2,2})";


	/**
	 * Pattern which contains all required rules.
	 */
	private static final Pattern NAME_PATTERN = Pattern.compile("^(?:(?:(" + PREFIX_TEMPLATE + ")" +
			"((?:" + ONE_TWO_CHAR_LOCAL_NAME_TEMPLATE + ")" +
			"|(?:" + THREE_OR_MORE_CHAR_NAME_TEMPLATE + ")" +
			"))" +
			"|" +
			"((?:" + ONE_CHAR_SIMPLE_NAME_TEMPLATE + ")" +
			"|(?:" + TWO_CHAR_SIMPLE_NAME_TEMPLATE + ")" +
			"|(?:" + THREE_OR_MORE_CHAR_NAME_TEMPLATE + ")))$");

	/**
	 * This method parse the String name and constructs an valid QName object.
	 *
	 * @param name is a String which will be parse
	 * @exception IllegalNameException if name doesn't match the template
	 * @return valid QName
	 * @see QName
	 */
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
