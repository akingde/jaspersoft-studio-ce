/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.svgimporter;

import org.apache.batik.css.parser.ExtendedParserWrapper;
import org.w3c.css.sac.Parser;

/**
 * Inkscape when converting a PDF into SVG add some internal tags in the style attribute
 * of an element. this tags start with a minus (-) and this broke the parsing on the 
 * batik attribute parser. For this reason we implement a custom parser to remove these
 * attributes before parsing them. This parser wrap the original one and avoid to let arrive
 * to it attributes in the inkscape format
 * 
 * @author Orlandin Marco
 *
 */
public class InkscapeExtenderParserWrapper extends ExtendedParserWrapper {

	public InkscapeExtenderParserWrapper(Parser parser) {
		super(parser);
	}

	/**
	 * Parse a style declaration to remove inkscape attributes, what remains is parsed by the defaul parser
	 */
	public void parseStyleDeclaration(String source) throws org.w3c.css.sac.CSSException ,java.io.IOException {
		String[] values = source.split(";");
		StringBuilder newValues = new StringBuilder();
		for(String styleProp : values) {
			if (!styleProp.toLowerCase().startsWith("-")) {
				newValues.append(styleProp);
				newValues.append(";");
			}
		}
		String newStyle = newValues.toString();
		if (newStyle.endsWith(";")) {
			newStyle = newStyle.substring(0, newStyle.length() - 1);
		}
		super.parseStyleDeclaration(newStyle);
	};
}
