/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.sections;

import net.sf.jasperreports.engine.base.JRBaseSubreport;
import net.sf.jasperreports.engine.design.JRDesignPart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Section for a report part to show the control to define the part expression and the caching value. And other
 * common properties of the graphical elements
 * 
 * @author Orlandin Marco
 */
public class ReportPartSection extends AbstractSection {

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));
		GridData comboData = new GridData(SWT.LEFT,SWT.FILL,false,false);
		comboData.widthHint = 100;
		createWidget4Property(parent, MReportPart.PROPERTY_EVALTIME_TYPE).getControl().setLayoutData(comboData);
		createWidget4Property(parent, JRBaseSubreport.PROPERTY_USING_CACHE);
		createWidget4Property(parent, MReportPart.COMPONENT_EXPRESSION);
		createWidget4Property(parent, JRDesignPart.PROPERTY_PRINT_WHEN_EXPRESSION);
		createWidget4Property(parent, JRDesignPart.PROPERTY_PART_NAME_EXPRESSION);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignPart.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.MReportPart_printWhen);
		addProvidedProperties(JRDesignPart.PROPERTY_PART_NAME_EXPRESSION, Messages.MReportPart_partName);
		addProvidedProperties(MReportPart.PROPERTY_EVALTIME_TYPE, Messages.common_evaluation_time);
		addProvidedProperties(MReportPart.COMPONENT_EXPRESSION, Messages.MReportPart_componentExpression);
		addProvidedProperties(JRBaseSubreport.PROPERTY_USING_CACHE, Messages.MReportPart_cacheLabel);
	}
}
