/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A single section. A section is a container of widgets with a name and a flag
 * to say if it is expandable or not
 * 
 * @author Orlandin Marco
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SectionPropertyDescriptor {
	
	private String name;
	
	private boolean expandable = false;
	
	private List<WidgetPropertyDescriptor> properties;

	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperties(List<WidgetPropertyDescriptor> properties) {
		this.properties = properties;
	}

	public List<WidgetPropertyDescriptor> getProperties() {
		if (properties == null)
			properties = new ArrayList<WidgetPropertyDescriptor>();
		return properties;
	}

	public String getName() {
		return name;
	}

}
