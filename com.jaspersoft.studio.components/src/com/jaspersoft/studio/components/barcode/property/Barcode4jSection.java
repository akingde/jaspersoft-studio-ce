/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barcode4j.Barcode4jComponent;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class Barcode4jSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, Messages.Barcode4jSection_0, false, 2);

		createWidget4Property(group, Barcode4jComponent.PROPERTY_PATTERN_EXPRESSION);

		createWidget4Property(group, Barcode4jComponent.PROPERTY_MODULE_WIDTH);

		createWidget4Property(group, Barcode4jComponent.PROPERTY_ORIENTATION);

		createWidget4Property(group, Barcode4jComponent.PROPERTY_QUIET_ZONE);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(Barcode4jComponent.PROPERTY_PATTERN_EXPRESSION, Messages.MBarcode4j_pattern_expression);
		addProvidedProperties(Barcode4jComponent.PROPERTY_QUIET_ZONE, Messages.MBarcode4j_quiet_zone);
		addProvidedProperties(Barcode4jComponent.PROPERTY_MODULE_WIDTH, Messages.MBarcode4j_module_width);
		addProvidedProperties(Barcode4jComponent.PROPERTY_ORIENTATION, Messages.MBarcode4j_orientation);
	}
}
