/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.html;

import java.util.ResourceBundle;

import com.jaspersoft.studio.model.util.NodeIconDescriptor;
/*
 * The Class HtmlNodeIconDescriptor.
 * 
 * @author Narcis Marcu
 */
public class HtmlNodeIconDescriptor extends NodeIconDescriptor {

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param name
	 *          the name
	 */
	public HtmlNodeIconDescriptor(String name) {
		super(name, Activator.getDefault());
	}

	/** The resource bundle icons. */
	private static ResourceBundle resourceBundleIcons;

	public ResourceBundle getResourceBundleIcons() {
		return resourceBundleIcons;
	}

	public void setResourceBundleIcons(ResourceBundle resourceBundleIcons) {
		HtmlNodeIconDescriptor.resourceBundleIcons = resourceBundleIcons;
	}
}
