/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.band;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.engine.base.JRBaseBand;
import net.sf.jasperreports.engine.design.JRDesignBand;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class BandSection extends AbstractSection {

	private Section section;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		section = getWidgetFactory().createAndGetSection(parent, Messages.BandSection_title, false, 1);
		Composite container = new Composite((Composite)section.getClient(), SWT.NONE);
		GridLayout containerLayout = new GridLayout(2, false);
		containerLayout.marginWidth = 0;
		containerLayout.marginHeight = 0;
		container.setLayout(containerLayout);
		GridData containerData = new GridData(GridData.FILL_HORIZONTAL);
		containerData.heightHint = 150;
		containerData.minimumHeight = 150;
		container.setLayoutData(containerData);
		
		createWidget4Property(container, JRDesignBand.PROPERTY_HEIGHT);
		createWidget4Property(container, JRBaseBand.PROPERTY_splitType);
		createWidget4Property(container, JRDesignBand.PROPERTY_PRINT_WHEN_EXPRESSION);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		createWidget4Property(container, JRDesignBand.PROPERTY_RETURN_VALUES, false).getControl().setLayoutData(gd);
	}

	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();
		// The properties are not visible if the band is not created
		if (section != null)
			section.setVisible(getElement().getValue() != null);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignBand.PROPERTY_HEIGHT, Messages.common_height);
		addProvidedProperties(JRBaseBand.PROPERTY_splitType, Messages.common_split_type);
		addProvidedProperties(JRDesignBand.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.common_print_when_expression);
		addProvidedProperties(JRDesignBand.PROPERTY_RETURN_VALUES, Messages.common_return_values);
	}
}
