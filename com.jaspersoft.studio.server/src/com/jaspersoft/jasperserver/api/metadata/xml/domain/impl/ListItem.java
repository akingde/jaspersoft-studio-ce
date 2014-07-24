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
 * It's an implementation of ListOfValuesItem used in Webservices. This class
 * does not implements directly ListOfValuesItem to avoid the need of this
 * interface on client side.
 * 
 * @author gtoffoli
 */
public class ListItem implements Serializable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private Object value;
	private String label;
	private boolean selected = false;
	private boolean isListItem = false;

	/** Creates a new instance of ListItem */
	public ListItem() {
	}

	/** Creates a new instance of ListItem */
	public ListItem(String label, Object value) {
		this.value = value;
		this.label = label;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isIsListItem() {
		return isListItem;
	}

	public void setIsListItem(boolean isListItem) {
		this.isListItem = isListItem;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
