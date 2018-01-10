/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.sections;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Section where the proeprties of a BookReport are shown
 */
public class BookReportSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(1, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		Composite firstSection = getWidgetFactory().createComposite(parent);
		firstSection.setLayout(new GridLayout(2, false));
		firstSection.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(firstSection, JasperDesign.PROPERTY_NAME).getControl().setLayoutData(gd);

		gd = new GridData();
		createWidget4Property(firstSection, JasperDesign.PROPERTY_LANGUAGE).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(firstSection, JasperDesign.PROPERTY_IMPORTS).getControl().setLayoutData(gd);

		//FIXME: commented until the version of > 6.2.1, because 6.2.1 has a bug and this is not used
		/*gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE).getControl().setLayoutData(gd);*/
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JasperDesign.PROPERTY_NAME, Messages.MReport_report_name);
		addProvidedProperties(JasperDesign.PROPERTY_LANGUAGE, Messages.common_language);
		addProvidedProperties(JasperDesign.PROPERTY_IMPORTS, Messages.MReport_imports);
		//addProvidedProperties(JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE, Messages.MReport_when_no_data_type);
	}
	

}
