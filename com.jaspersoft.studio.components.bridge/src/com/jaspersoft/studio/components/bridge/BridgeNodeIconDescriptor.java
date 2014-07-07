/*******************************************************************************
 * Copyright (C) 2010 - 2014 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *  
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * 	Jaspersoft Studio Team - initial API and implementation
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
