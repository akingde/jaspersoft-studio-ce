/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.toolitems;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;


public class ToolItemsSet {
	private String id;
	private String name;
	private String description;
	private boolean visibility;
	private List<IConfigurationElement> controlsConfigurationElements = new ArrayList<IConfigurationElement>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public boolean isVisible() {
		return visibility;
	}

	public void setVisible(boolean visibility) {
		this.visibility = visibility;
	}
	
	public List<IConfigurationElement> getControlsConfiguration(){
		return controlsConfigurationElements;
	}
	
	public void addControlConfiguration(IConfigurationElement controlDefinition){
		controlsConfigurationElements.add(controlDefinition);
	}
}
