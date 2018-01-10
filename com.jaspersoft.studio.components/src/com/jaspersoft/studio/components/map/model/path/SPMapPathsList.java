/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.path;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.map.model.itemdata.ElementsListWidgetConfiguration;
import com.jaspersoft.studio.components.map.model.itemdata.MapDataElementsContentProvider;
import com.jaspersoft.studio.components.map.model.itemdata.SPMapDataElementsList;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Widget that allows to manage the <code>StandardMapComponent.PROPERTY_PATH_DATA_LIST</code> property.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class SPMapPathsList extends SPMapDataElementsList {

	public SPMapPathsList(Composite parent, AbstractSection section,
			IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	protected IBaseLabelProvider getElementsViewerLabelProvider() {
		return new PathsLabelProvider();
	}

	@Override
	protected IContentProvider getElementsViewerContentProvider() {
		return new MapDataElementsContentProvider();
	}

	@Override
	protected List<String> getMandatoryProperties() {
		return Arrays.asList("latitude","longitude"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected ElementsListWidgetConfiguration getWidgetConfiguration() {
		return new MapPathsWidgetConfiguration();
	}

}
