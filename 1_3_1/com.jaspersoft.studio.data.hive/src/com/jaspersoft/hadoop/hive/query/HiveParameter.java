/*
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.hadoop.hive.query;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRValueParameter;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HiveParameter implements JRValueParameter {

	private String name;

	private Object value;

	public HiveParameter(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Object clone() {
		return null;
	}

	@Override
	public boolean hasProperties() {
		return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public void setDescription(String description) {
	}

	@Override
	public Class<?> getValueClass() {
		if (value != null) {
			return value.getClass();
		}
		return null;
	}

	@Override
	public String getValueClassName() {
		if (value != null) {
			return value.getClass().getName();
		}
		return null;
	}

	@Override
	public boolean isSystemDefined() {
		return false;
	}

	@Override
	public boolean isForPrompting() {
		return false;
	}

	@Override
	public JRExpression getDefaultValueExpression() {
		return null;
	}

	@Override
	public Class<?> getNestedType() {
		return null;
	}

	@Override
	public String getNestedTypeName() {
		return null;
	}

	@Override
	public JRPropertiesMap getPropertiesMap() {
		return null;
	}

	@Override
	public JRPropertiesHolder getParentProperties() {
		return null;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
	}
}