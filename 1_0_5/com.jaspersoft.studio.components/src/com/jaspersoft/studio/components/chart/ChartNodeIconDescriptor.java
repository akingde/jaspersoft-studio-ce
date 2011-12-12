/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.chart;

import java.util.ResourceBundle;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/*
 * The Class NodeIconDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class ChartNodeIconDescriptor extends NodeIconDescriptor {

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param name
	 *            the name
	 */
	public ChartNodeIconDescriptor(String name) {
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
		ChartNodeIconDescriptor.resourceBundleIcons = resourceBundleIcons;
	}
}
