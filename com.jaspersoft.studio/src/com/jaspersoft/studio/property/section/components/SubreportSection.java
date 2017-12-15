/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.components;

import net.sf.jasperreports.engine.base.JRBaseSubreport;
import net.sf.jasperreports.engine.design.JRDesignSubreport;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SubreportSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM);
		createWidget4Property(parent, JRBaseSubreport.PROPERTY_OVERFLOW_TYPE);
		createWidget4Property(parent, JRBaseSubreport.PROPERTY_USING_CACHE);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION);

		Composite cmp = getWidgetFactory().createComposite(parent);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		createWidget4Property(cmp, JRDesignSubreport.PROPERTY_RETURN_VALUES, false);

		createWidget4Property(cmp, JRDesignSubreport.PROPERTY_PARAMETERS, false);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM, Messages.MSubreport_run_to_bottom);
		addProvidedProperties(JRBaseSubreport.PROPERTY_USING_CACHE, Messages.common_using_cache);
		addProvidedProperties(JRBaseSubreport.PROPERTY_OVERFLOW_TYPE, Messages.MSubreport_0);
		addProvidedProperties(JRDesignSubreport.PROPERTY_EXPRESSION, Messages.common_expression);
		addProvidedProperties(JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION, Messages.common_parameters_map_expression);
		addProvidedProperties(JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION, Messages.common_connection_expression);
		addProvidedProperties(JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION, Messages.MSubreport_datasource_expression);
		addProvidedProperties(JRDesignSubreport.PROPERTY_RETURN_VALUES, Messages.common_return_values);
		addProvidedProperties(JRDesignSubreport.PROPERTY_PARAMETERS, Messages.common_parameters);
	}
}
