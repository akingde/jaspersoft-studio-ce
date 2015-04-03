/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.kpi.dialog.pages.widget;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.kpi.Activator;

/**
 * Class that encapsulate a Widget type with all the
 * informations needed by the kpi configurator
 * 
 * @author Orlandin Marco
 *
 */
public class WidgetDefinition {

	/**
	 * The type of the widget, this string is used as an identifier, so
	 * it should be unique among the widgtes
	 */
	private String widgetType;
	
	/**
	 * A path to a sample image of the widget
	 */
	private String imagePath;
	
	/**
	 * Build the container
	 * 
	 * @param widgetType The type of the widget, this string is used as an identifier, so
	 * it should be unique among the widgtes, must be not null
	 * @param imagePath A path to a sample image of the widget, null to don't use an image
	 */
	public WidgetDefinition(String widgetType, String imagePath){
		Assert.isNotNull(widgetType);
		this.widgetType = widgetType;
		this.imagePath = imagePath;
	}
	
	/**
	 * Try to load the image defined in the imagePath and return it. The image is cached and
	 * disposed automatically when the application is closed. Shouldn't be disposed manually
	 * 
	 * @return an image or null if the path is not valid
	 */
	public Image getImage(){
		if (imagePath == null) return null;
		ImageDescriptor descriptor = Activator.getImageDescriptor(imagePath);
		return ResourceManager.getImage(descriptor);
	}

	/**
	 * Return the widget type
	 * 
	 * @return a not null string
	 */
	public String getWidgetType() {
		return widgetType;
	}
	
}
