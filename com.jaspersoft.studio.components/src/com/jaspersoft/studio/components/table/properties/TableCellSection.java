/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.properties;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.components.table.DesignCell;

public class TableCellSection extends AbstractSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, "Cell Properties",
				false, 2);

		createWidget4Property(parent, DesignCell.PROPERTY_HEIGHT);

		createWidget4Property(parent, DesignCell.PROPERTY_ROW_SPAN);
		createWidget4Property(parent, DesignCell.PROPERTY_STYLE);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(DesignCell.PROPERTY_HEIGHT, Messages.MCell_height);
		addProvidedProperties(DesignCell.PROPERTY_ROW_SPAN, Messages.MCell_rowspan);
		addProvidedProperties(DesignCell.PROPERTY_STYLE, Messages.MCell_parent_style);
	}
	
}
