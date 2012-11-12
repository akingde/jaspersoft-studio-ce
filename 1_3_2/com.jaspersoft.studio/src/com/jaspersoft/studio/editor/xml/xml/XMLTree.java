/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.xml.xml;

import java.util.ArrayList;
import java.util.List;

public class XMLTree {

	private XMLElement rootElement;
	private List<XMLElement> allElements = new ArrayList<XMLElement>();
	private List<String> allAttributes = new ArrayList<String>();

	public XMLTree() {

		super();
		rootElement = new XMLElement("jasperReport");
		rootElement.addChildAttribute(new XMLAttribute("name"));
		rootElement.addChildAttribute(new XMLAttribute("language"));
		rootElement.addChildAttribute(new XMLAttribute("pageWidth"));
		rootElement.addChildAttribute(new XMLAttribute("pageHeight"));
		
		XMLElement property = newDTDElement("property");
		rootElement.addChildElement(property);
		property.addChildAttribute(new XMLAttribute("name"));
		addAttribute("name");

		property.addChildAttribute(new XMLAttribute("value"));
//		addAttribute("value");

		XMLElement style = newDTDElement("style");
		rootElement.addChildElement(style);
		style.addChildAttribute(new XMLAttribute("name"));
//		addAttribute("name");
		style.addChildAttribute(new XMLAttribute("mode"));
//		addAttribute("mode");
		style.addChildAttribute(new XMLAttribute("forecolor"));
//		addAttribute("forecolor");
		style.addChildAttribute(new XMLAttribute("backcolor"));
//		addAttribute("backcolor");
		style.addChildAttribute(new XMLAttribute("fontName"));
//		addAttribute("fontName");
		style.addChildAttribute(new XMLAttribute("fontSize"));
//		addAttribute("fontSize");
		style.addChildAttribute(new XMLAttribute("isBold"));
//		addAttribute("isBold");
		style.addChildAttribute(new XMLAttribute("isItalic"));
//		addAttribute("isItalic");
		style.addChildAttribute(new XMLAttribute("isUnderline"));
//		addAttribute("isUnderline");
		style.addChildAttribute(new XMLAttribute("isStrikeThrough"));
//		addAttribute("isStrikeThrough");
		style.addChildAttribute(new XMLAttribute("isItalic"));
//		addAttribute("isItalic");
		style.addChildAttribute(new XMLAttribute("pdfFontName"));
//		addAttribute("pdfFontName");
		style.addChildAttribute(new XMLAttribute("rotation"));
//		addAttribute("rotation");
		style.addChildAttribute(new XMLAttribute("hAlign"));
//		addAttribute("hAlign");
		style.addChildAttribute(new XMLAttribute("vAlign"));
//		addAttribute("vAlign");

		XMLElement conditionalStyle = newDTDElement("conditionalStyle");
		style.addChildElement(conditionalStyle);
		XMLElement conditionExpression = newDTDElement("conditionExpression");
		conditionalStyle.addChildElement(conditionExpression);

	}

	private XMLElement newDTDElement(String elementName) {
		XMLElement element = new XMLElement(elementName);
		allElements.add(element);
		return element;
	}

	private void addAttribute(String attributeName) {
		if (!allAttributes.contains(attributeName)) {
			allAttributes.add(attributeName);
		}
	}

	public List<XMLElement> getAllElements() {
		return allElements;
	}

	public List<String> getAllAttributes() {
		return allAttributes;
	}

	public XMLElement getRootElement() {
		return rootElement;
	}

	public void setRootElement(XMLElement rootElement) {
		this.rootElement = rootElement;
	}
}