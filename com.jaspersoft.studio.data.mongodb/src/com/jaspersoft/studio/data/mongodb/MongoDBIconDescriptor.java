/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.mongodb;

import java.util.ResourceBundle;

import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/*
 * The Class NodeIconDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class MongoDBIconDescriptor extends NodeIconDescriptor {

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param name
	 *            the name
	 */
	public MongoDBIconDescriptor(String name) {
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
		MongoDBIconDescriptor.resourceBundleIcons = resourceBundleIcons;
	}
}
