/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ComponentDescriptor {
	private String label;
	private String description;
	private String module;
	private String thumbnail;
	private List<ComponentSectionDescriptor> sections;
	private List<ComponentDatasetDescriptor> datasets;
	private ICustomComponentUIProivder customUIprovider;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public ICustomComponentUIProivder getCustomUIprovider() {
		return customUIprovider;
	}

	public void setCustomUIprovider(ICustomComponentUIProivder customUIprovider) {
		this.customUIprovider = customUIprovider;
	}

	public List<ComponentSectionDescriptor> getSections() {
		return sections;
	}

	public void setSections(List<ComponentSectionDescriptor> sections) {
		this.sections = sections;
	}

	public List<ComponentDatasetDescriptor> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<ComponentDatasetDescriptor> datasets) {
		this.datasets = datasets;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ComponentDescriptor)
			return getModule().equals(((ComponentDescriptor) obj).getModule());
		return false;
	}

	@Override
	public int hashCode() {
		if (getModule() != null)
			return getModule().hashCode();
		return 0;
	}
}
