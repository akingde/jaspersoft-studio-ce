/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.list.property;

import net.sf.jasperreports.components.list.StandardListComponent;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.list.messages.Messages;
import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class ListDatasetSection extends AbstractSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent,
				"Dataset Run", false, 2, 2);
		createWidget4Property(group, MList.PREFIX
				+ StandardListComponent.PROPERTY_DATASET_RUN);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(MList.PREFIX+StandardListComponent.PROPERTY_DATASET_RUN, Messages.MList_dataset_run);
	}
	
}
