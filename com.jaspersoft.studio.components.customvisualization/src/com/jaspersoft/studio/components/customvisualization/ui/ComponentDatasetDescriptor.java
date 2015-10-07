/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui;

import java.util.List;

public class ComponentDatasetDescriptor {
	private String label;
	private int cardinality;
	private List<ComponentSectionDescriptor> sections;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCardinality() {
		return cardinality;
	}

	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}

	public List<ComponentSectionDescriptor> getSections() {
		return sections;
	}

	public void setSections(List<ComponentSectionDescriptor> sections) {
		this.sections = sections;
	}

}
