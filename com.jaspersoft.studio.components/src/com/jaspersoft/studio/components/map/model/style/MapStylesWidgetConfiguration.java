/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.style;

import net.sf.jasperreports.components.map.StandardMapComponent;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.itemdata.ElementsListWidgetConfiguration;

/**
 * Configuration information for the  {@link SPMapStylesList} widget.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapStylesWidgetConfiguration implements ElementsListWidgetConfiguration {

	@Override
	public String getElementsTabTitle() {
		return Messages.MapStylesWidgetConfiguration_MapStyles;
	}

	@Override
	public String getElementTxt() {
		return Messages.MapStylesWidgetConfiguration_Style;
	}

	@Override
	public Image getAddNewElementIcon() {
		return Activator.getDefault().getImage("/icons/pathstyle-add-16.png"); //$NON-NLS-1$
	}

	@Override
	public String getWidgetPropertyID() {
		return StandardMapComponent.PROPERTY_PATH_STYLE_LIST;
	}

	@Override
	public String getElementPropertiesResourceLocation() {
		return "/resources/styleData.properties"; //$NON-NLS-1$
	}

	@Override
	public boolean isElementNameMandatory() {
		return true;
	}

}
