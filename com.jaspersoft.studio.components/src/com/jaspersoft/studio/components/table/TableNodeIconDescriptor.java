/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table;

import java.util.ResourceBundle;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
/*
 * The Class NodeIconDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class TableNodeIconDescriptor extends NodeIconDescriptor {

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param name
	 *          the name
	 */
	public TableNodeIconDescriptor(String name) {
		super(name, Activator.getDefault());
	}

	/** The resource bundle icons. */
	private static ResourceBundle resourceBundleIcons;

	@Override
	public ResourceBundle getResourceBundleIcons() {
		return resourceBundleIcons;
	}

	@Override
	public void setResourceBundleIcons(ResourceBundle resourceBundleIcons) {
		TableNodeIconDescriptor.resourceBundleIcons = resourceBundleIcons;
	}
}
