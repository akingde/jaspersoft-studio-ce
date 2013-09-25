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
package com.jaspersoft.studio.components.map.model.path;

import net.sf.jasperreports.components.map.StandardMapComponent;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.map.model.itemdata.ElementsListWidgetConfiguration;

/**
 * Configuration information for the  {@link SPMapPathsList} widget.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapPathsWidgetConfiguration implements ElementsListWidgetConfiguration {

	@Override
	public String getElementsTabTitle() {
		return "Map Paths";
	}

	@Override
	public String getElementTxt() {
		return "Path";
	}

	@Override
	public Image getAddNewElementIcon() {
		return Activator.getDefault().getImage("/icons/path-icon-16.png");
	}

	@Override
	public String getWidgetPropertyID() {
		return StandardMapComponent.PROPERTY_PATH_DATA_LIST;
	}

	@Override
	public String getElementPropertiesResourceLocation() {
		return "/resources/pathData.properties";
	}

	@Override
	public boolean isElementNameMandatory() {
		return false;
	}

}
