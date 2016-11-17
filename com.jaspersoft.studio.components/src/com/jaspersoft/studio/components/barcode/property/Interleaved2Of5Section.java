/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barcode4j.Barcode4jComponent;
import net.sf.jasperreports.components.barcode4j.Interleaved2Of5Component;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class Interleaved2Of5Section extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "Interleaved2Of5", false, 2);

		createWidget4Property(group, Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE);

		createWidget4Property(group, Interleaved2Of5Component.PROPERTY_CHECKSUM_MODE);
		createWidget4Property(group, Interleaved2Of5Component.PROPERTY_WIDE_FACTOR);
		createWidget4Property(group, Interleaved2Of5Component.PROPERTY_DISPLAY_CHECKSUM);
		createWidget4Property(group, Barcode4jComponent.PROPERTY_TEXT_POSITION);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(Interleaved2Of5Component.PROPERTY_CHECKSUM_MODE, Messages.common_checksum_mode);
		addProvidedProperties(Interleaved2Of5Component.PROPERTY_WIDE_FACTOR, Messages.common_wide_factor);
		addProvidedProperties(Interleaved2Of5Component.PROPERTY_DISPLAY_CHECKSUM, Messages.common_display_checksum);
		addProvidedProperties(Barcode4jComponent.PROPERTY_TEXT_POSITION, Messages.MBarcode4j_text_position);
		addProvidedProperties(Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE, Messages.MBarcode4j_vertical_quiet_zone);
	}
}
