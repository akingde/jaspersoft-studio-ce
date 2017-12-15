/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui.framework;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Descriptor for a CVC widgets load from its JSON definition file
 * 
 * @author Orlandin Marco
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CVCWidgetsDescriptor extends WidgetsDescriptor {

	private String module;

	private String thumbnail;

	private List<DatasetPropertyDescriptor> datasets;

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

	public List<DatasetPropertyDescriptor> getDatasets() {
		if (datasets == null) {
			datasets = new ArrayList<DatasetPropertyDescriptor>();
		}
		return datasets;
	}

	public void setDatasets(List<DatasetPropertyDescriptor> datasets) {
		this.datasets = datasets;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ComponentDescriptor)
			return getModule().equals(((ComponentDescriptor) obj).getModule());
		else if (obj instanceof CVCWidgetsDescriptor)
			return getModule().equals(((CVCWidgetsDescriptor) obj).getModule());
		return false;
	}

	@Override
	public int hashCode() {
		if (getModule() != null)
			return getModule().hashCode();
		return 0;
	}

	@Override
	public String getLocalizedString(String key) {
		return UIManager.getProperty(this, key);
	}

}
