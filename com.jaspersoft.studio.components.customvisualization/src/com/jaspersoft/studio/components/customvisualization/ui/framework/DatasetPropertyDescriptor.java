/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui.framework;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaspersoft.studio.widgets.framework.model.SectionPropertyDescriptor;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DatasetPropertyDescriptor {
	
	private String label;
	
	private int cardinality;
	
	private List<SectionPropertyDescriptor> sections;

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

	public List<SectionPropertyDescriptor> getSections() {
		if (sections == null){
			sections = new ArrayList<SectionPropertyDescriptor>();
		}
		return sections;
	}

	public void setSections(List<SectionPropertyDescriptor> sections) {
		this.sections = sections;
	}

}
