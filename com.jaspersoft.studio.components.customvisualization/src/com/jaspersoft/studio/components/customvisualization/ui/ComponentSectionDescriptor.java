/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/

package com.jaspersoft.studio.components.customvisualization.ui;

import java.util.ArrayList;
import java.util.List;

public class ComponentSectionDescriptor {
	private String name;
	private boolean expandable = false;

	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperties(List<ComponentPropertyDescriptor> properties) {
		this.properties = properties;
	}

	private List<ComponentPropertyDescriptor> properties;

	public List<ComponentPropertyDescriptor> getProperties() {
		if (properties == null)
			properties = new ArrayList<ComponentPropertyDescriptor>();
		return properties;
	}

	public String getName() {
		return name;
	}

}
