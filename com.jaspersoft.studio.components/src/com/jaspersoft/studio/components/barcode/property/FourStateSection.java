/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.property;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.components.barcode4j.Barcode4jComponent;
import net.sf.jasperreports.components.barcode4j.FourStateBarcodeComponent;

public class FourStateSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "Four State", false, 2);

		createWidget4Property(group, Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE);

		createWidget4Property(group, FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE);
		createWidget4Property(group, FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH);
		createWidget4Property(group, FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT);
		createWidget4Property(group, FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT);
		createWidget4Property(group, Barcode4jComponent.PROPERTY_TEXT_POSITION);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE, Messages.common_checksum_mode);
		addProvidedProperties(FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH,
				Messages.common_interchar_gap_width);
		addProvidedProperties(FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT,
				Messages.MFourStateBarcode_ascender_height);
		addProvidedProperties(FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT, Messages.MFourStateBarcode_track_height);
		addProvidedProperties(Barcode4jComponent.PROPERTY_TEXT_POSITION, Messages.MBarcode4j_text_position);
		addProvidedProperties(Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE, Messages.MBarcode4j_vertical_quiet_zone);
	}
}
