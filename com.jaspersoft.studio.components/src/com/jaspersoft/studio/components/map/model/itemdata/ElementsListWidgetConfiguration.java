/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.itemdata;

import net.sf.jasperreports.components.items.ItemData;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.map.model.path.SPMapPathsList;
import com.jaspersoft.studio.components.map.model.style.SPMapStylesList;

/**
 * This interface should be used by whose widgets that want to have access to
 * configuration information about specific properties implying the use of
 * {@link ItemData} elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * @see SPMapPathsList
 * @see SPMapStylesList
 * 
 */
public interface ElementsListWidgetConfiguration {

	String getElementsTabTitle();

	String getElementTxt();

	Image getAddNewElementIcon();

	String getWidgetPropertyID();

	String getElementPropertiesResourceLocation();

	boolean isElementNameMandatory();
}
