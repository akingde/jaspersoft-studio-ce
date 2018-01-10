/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.sections;

import net.sf.jasperreports.engine.design.JRDesignSubreport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/**
 * Section for a report part to show the control to define the part connection and\or the part
 * datasource parameters and the buttons to configure the part parameters and return values
 * 
 * @author Orlandin Marco
 */
public class PartDataSection extends AbstractSection {
	
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent.setLayout(new GridLayout(1, false));
		createWidget4Property(parent, MReportPart.REPORT_CONNECTION);
		createWidget4Property(parent, MReportPart.REPORT_DATASOURCE);
		
		//CREATE THE PARAMETERS AND RETURN VALUES BUTTONS
		
		Composite buttonsComposite = getWidgetFactory().createSection(parent, Messages.ReportPartSection_advancedLabel, false, 2, 2 , Section.EXPANDED);
		
		//Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayout(new GridLayout(2, false));
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		buttonsData.horizontalSpan = 2;
		buttonsComposite.setLayoutData(buttonsData);
		
		ASPropertyWidget<?> returnWidget = createWidget4Property(buttonsComposite, JRDesignSubreport.PROPERTY_RETURN_VALUES, false);
		GridData buttonData = new GridData();
		buttonsData.horizontalAlignment = SWT.CENTER;
		returnWidget.getControl().setLayoutData(buttonData);
		
		ASPropertyWidget<?> parametersWidget = createWidget4Property(buttonsComposite, JRDesignSubreport.PROPERTY_PARAMETERS, false);
		GridData parametersData = new GridData();
		parametersData.horizontalAlignment = SWT.CENTER;
		parametersWidget.getControl().setLayoutData(parametersData);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(MReportPart.REPORT_CONNECTION, Messages.MReportPart_connectionExp);
		addProvidedProperties(MReportPart.REPORT_DATASOURCE, Messages.MReportPart_dataSourceExp);
		addProvidedProperties(JRDesignSubreport.PROPERTY_RETURN_VALUES, Messages.SPPartReturnValuesButton_returnValuesButton);
		addProvidedProperties(JRDesignSubreport.PROPERTY_PARAMETERS, Messages.SPPartParametersButton_parametersButton);
	}

}
