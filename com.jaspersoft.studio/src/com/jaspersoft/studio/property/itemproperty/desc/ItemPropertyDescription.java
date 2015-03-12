/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.Text;

public class ItemPropertyDescription<T> {
	private String name;
	private String label;
	private String description;
	private boolean mandatory;
	private T defaultValue;

	public ItemPropertyDescription(String name, String description, boolean mandatory) {
		this(name, name, description, mandatory, null);
	}

	public ItemPropertyDescription(String name, String label, String description, boolean mandatory) {
		this(name, label, description, mandatory, null);
	}

	public ItemPropertyDescription(String name, String description, boolean mandatory, T defaultValue) {
		this(name, name, description, mandatory, defaultValue);
	}

	public ItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue) {
		super();
		this.name = name;
		this.label = label;
		this.description = description;
		this.mandatory = mandatory;
		this.defaultValue = defaultValue;
	}

	public boolean isMultiline() {
		return false;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getDefaultValueString() {
		if (defaultValue != null)
			return defaultValue.toString();
		return ""; //$NON-NLS-1$
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Class<?> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return String.class;
	}

	public String toSimpleString(String original) {
		return original;
	}

	public void handleEdit(Text txt, StandardItemProperty value) {
		String tvalue = txt.getText();
		if (tvalue.isEmpty())
			tvalue = null;
		if (value.getValueExpression() != null)
			((JRDesignExpression) value.getValueExpression()).setText(tvalue);
		else
			value.setValue(tvalue);
	}
}
