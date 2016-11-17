/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.report;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class PageSizeSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "Page Size", false, 2);

		createWidget4Property(group, JasperDesign.PROPERTY_PAGE_WIDTH);
		createWidget4Property(group, JasperDesign.PROPERTY_PAGE_HEIGHT);

		group = getWidgetFactory().createSection(parent, "Page Orientation", false, 2);

		createWidget4Property(group, JasperDesign.PROPERTY_ORIENTATION, false);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JasperDesign.PROPERTY_PAGE_WIDTH,  Messages.MReport_top_margin);
		addProvidedProperties(JasperDesign.PROPERTY_PAGE_HEIGHT, Messages.MReport_bottom_margin);
		addProvidedProperties(JasperDesign.PROPERTY_LEFT_MARGIN, Messages.MReport_left_margin);
		addProvidedProperties(JasperDesign.PROPERTY_RIGHT_MARGIN, Messages.MReport_right_margin);
	}

}
