/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

import net.sf.jasperreports.customvisualization.design.CVDesignComponent;

/**
 * Section where the dynamic controls of a CVC are created
 * 
 * @author Orlandin Marco
 *
 */
public class CVDynamicAreaSection extends AbstractSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		//Widget for the properties
		ASPropertyWidget<?> itemPropsW = createWidget4Property(parent, CVDesignComponent.PROPERTY_ITEM_PROPERTIES, false);
		itemPropsW.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		//Widget for the dataset
		ASPropertyWidget<?> itemDataW = createWidget4Property(parent, CVDesignComponent.PROPERTY_ITEM_DATA, false);
		itemDataW.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(CVDesignComponent.PROPERTY_ITEM_PROPERTIES, Messages.CVSection_CVItemPropertiesDesc);
		addProvidedProperties(CVDesignComponent.PROPERTY_ITEM_DATA, Messages.CVSection_InnerConfiguration);
	}
	
}
