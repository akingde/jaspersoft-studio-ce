/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.property;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class CrosstabMeasureSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(final Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(parent, JRDesignCrosstabMeasure.PROPERTY_NAME).getControl().setLayoutData(gd);

		gd = new GridData();
		createWidget4Property(parent,JRDesignCrosstabMeasure.PROPERTY_CALCULATION).getControl().setLayoutData(gd);

		gd = new GridData();
		createWidget4Property(parent,JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(parent,JRDesignCrosstabMeasure.PROPERTY_VALUE_EXPRESSION).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(parent,JRDesignCrosstabMeasure.PROPERTY_VALUE_CLASS).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(parent, JRDesignCrosstabMeasure.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(parent, JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME).getControl().setLayoutData(gd);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignCrosstabMeasure.PROPERTY_NAME, Messages.common_name);
		addProvidedProperties(JRDesignCrosstabMeasure.PROPERTY_CALCULATION, Messages.common_calculation);
		addProvidedProperties(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE, Messages.MMeasure_percentage_of_type);
		addProvidedProperties(JRDesignCrosstabMeasure.PROPERTY_VALUE_EXPRESSION, Messages.MMeasure_value_expression);
		addProvidedProperties(JRDesignCrosstabMeasure.PROPERTY_VALUE_CLASS, Messages.MMeasure_value_class);
		addProvidedProperties(JRDesignCrosstabMeasure.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME, Messages.MMeasure_incrementer_factory_class_name);
		addProvidedProperties(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME, Messages.MMeasure_percentage_calculation_class_name);
	}
}
