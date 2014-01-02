/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.jasperserver.api.metadata.xml.domain.impl;

/**
 * 
 * @author gtoffoli
 */
public class ResourceProperty {

	private String name = "";
	private String value = "";

	private java.util.List<ResourceProperty> properties = new java.util.ArrayList<ResourceProperty>();

	/** Creates a new instance of ResourceProperty */
	public ResourceProperty(String name) {
		this(name, null);
	}

	/** Creates a new instance of ResourceProperty */
	public ResourceProperty(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		isDirty = true;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		isDirty = true;
	}

	public java.util.List<ResourceProperty> getProperties() {
		return properties;
	}

	public void setProperties(java.util.List<ResourceProperty> properties) {
		this.properties = properties;
		isDirty = true;
	}

	private boolean isDirty = true;

	public boolean isDirty() {
		if (!isDirty && properties != null)
			for (ResourceProperty p : properties)
				if (p.isDirty())
					return true;
		return isDirty;
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
		if (properties != null)
			for (ResourceProperty p : properties)
				p.setDirty(isDirty);
	}
}
