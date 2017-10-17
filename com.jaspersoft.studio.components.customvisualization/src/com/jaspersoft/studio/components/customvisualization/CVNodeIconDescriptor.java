/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization;

import java.util.ResourceBundle;

import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/**
 * Icon descriptor for the Custom Visualization component element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class CVNodeIconDescriptor extends NodeIconDescriptor {

	private static ResourceBundle resourceBundleIcons;
	
	public CVNodeIconDescriptor(String name) {
		super(name,CustomVisualizationActivator.getDefault());
	}

	@Override
	public ResourceBundle getResourceBundleIcons() {
		return resourceBundleIcons;
	}

	@Override
	public void setResourceBundleIcons(ResourceBundle resourceBundleIcons) {
		CVNodeIconDescriptor.resourceBundleIcons = resourceBundleIcons;
	}
	
}
