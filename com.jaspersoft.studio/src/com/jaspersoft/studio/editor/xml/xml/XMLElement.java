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
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.Position;

public class XMLElement {

	private List<XMLElement> elementChildren = new ArrayList<XMLElement>();
	private List<XMLAttribute> attributeChildren = new ArrayList<XMLAttribute>();

	private String name;
	private XMLElement parent;
	private Position position;

	public XMLElement(String name) {
		super();
		this.name = name;
	}

	public List<XMLElement> getChildrenDTDElements() {
		return elementChildren;
	}

	public XMLElement addChildElement(XMLElement element) {
		elementChildren.add(element);
		element.setParent(this);
		return this;
	}

	public void setParent(XMLElement element) {
		this.parent = element;
	}

	public XMLElement getParent() {
		return parent;
	}

	public XMLElement addChildAttribute(XMLAttribute attribute) {
		attributeChildren.add(attribute);
		return this;
	}

	public String getName() {
		return name;
	}

	public String getAttributeValue(String localName) {
		for (Iterator<XMLAttribute> iter = attributeChildren.iterator(); iter.hasNext();) {
			XMLAttribute attribute = (XMLAttribute) iter.next();
			if (attribute.getName().equals(localName))
				return attribute.getValue();
		}
		return null;
	}

	public void clear() {
		elementChildren.clear();
		attributeChildren.clear();
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}
}