/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.bridge;

import java.util.ResourceBundle;

import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/**
 * Icon descriptor for the Bridge component element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class BridgeNodeIconDescriptor extends NodeIconDescriptor {

	private static ResourceBundle resourceBundleIcons;
	
	public BridgeNodeIconDescriptor(String name) {
		super(name,Activator.getDefault());
	}

	@Override
	public ResourceBundle getResourceBundleIcons() {
		return resourceBundleIcons;
	}

	@Override
	public void setResourceBundleIcons(ResourceBundle resourceBundleIcons) {
		BridgeNodeIconDescriptor.resourceBundleIcons = resourceBundleIcons;
	}
	
}
