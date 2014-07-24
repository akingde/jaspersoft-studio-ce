/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/


package com.jaspersoft.jasperserver.api.metadata.xml.domain.impl;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;

/**
 * 
 * @author gtoffoli
 */
public class InputControlQueryDataRow implements Serializable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private Object value = null;
	private java.util.List<String> columnValues = null;
	private boolean selected = false;

	/** Creates a new instance of InputControlQueryDataRow */
	public InputControlQueryDataRow() {
		columnValues = new java.util.ArrayList<String>();
	}

	public java.util.List<String> getColumnValues() {
		return columnValues;
	}

	public void setColumnValues(java.util.List<String> columnValues) {
		this.columnValues = columnValues;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}
}
