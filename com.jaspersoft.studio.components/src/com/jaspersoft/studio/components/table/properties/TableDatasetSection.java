/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.properties;

import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class TableDatasetSection extends AbstractSection {

	private ExpandableComposite section;
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, StandardTable.PROPERTY_WHEN_NO_DATA_TYPE);

		Composite group = getWidgetFactory().createSection(parent,
				"Dataset Run", true, 2, 2);
		section = (ExpandableComposite)group.getParent();
		createWidget4Property(group, StandardTable.PROPERTY_DATASET_RUN);
	}
	
	@Override
	public void expandForProperty(Object propertyId) {
		if (section != null && !section.isExpanded() && propertyId.equals(StandardTable.PROPERTY_DATASET_RUN)) section.setExpanded(true);
	}
	
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(StandardTable.PROPERTY_WHEN_NO_DATA_TYPE, Messages.MTable_whennodatalabel);
		addProvidedProperties(StandardTable.PROPERTY_DATASET_RUN, Messages.MTable_dataset_run);
	}
}
