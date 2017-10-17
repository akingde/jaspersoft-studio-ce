/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.property;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class CrosstabGroupSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(final Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));
		createWidget4Property(parent, JRDesignCrosstabGroup.PROPERTY_NAME);
		createWidget4Property(parent, JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION);
		createWidget4Property(parent, JRDesignCrosstabGroup.PROPERTY_MERGE_HEADER_CELLS, false);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignCrosstabGroup.PROPERTY_NAME, Messages.common_name);
		addProvidedProperties(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION, Messages.common_total_position);
		addProvidedProperties(JRDesignCrosstabGroup.PROPERTY_MERGE_HEADER_CELLS, Messages.MCrosstabGroup_0);
	}
}
