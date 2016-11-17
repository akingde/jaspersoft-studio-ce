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
import net.sf.jasperreports.components.barcode4j.PDF417Component;

public class PDF417Section extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "PDF417", false, 2);

		createWidget4Property(group, Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE);

		createWidget4Property(group, PDF417Component.PROPERTY_MIN_COLUMNS);

		createWidget4Property(group, PDF417Component.PROPERTY_MAX_COLUMNS);
		createWidget4Property(group, PDF417Component.PROPERTY_MIN_ROWS);
		createWidget4Property(group, PDF417Component.PROPERTY_MAX_ROWS);

		createWidget4Property(group, PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(PDF417Component.PROPERTY_MIN_COLUMNS, Messages.MPDF417_min_columns);
		addProvidedProperties(PDF417Component.PROPERTY_MAX_COLUMNS, Messages.MPDF417_max_columns);
		addProvidedProperties(PDF417Component.PROPERTY_MIN_ROWS, Messages.MPDF417_min_rows);
		addProvidedProperties(PDF417Component.PROPERTY_MAX_ROWS, Messages.MPDF417_max_rows);
		addProvidedProperties(PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL, Messages.MPDF417_error_correction_level);
		addProvidedProperties(Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE, Messages.MBarcode4j_vertical_quiet_zone);
	}
}
