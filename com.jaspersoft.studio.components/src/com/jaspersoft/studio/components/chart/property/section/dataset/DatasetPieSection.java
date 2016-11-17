/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.dataset;

import net.sf.jasperreports.charts.design.JRDesignPieDataset;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.dataset.descriptor.DatasetSection;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetPieSection extends DatasetSection {

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, "Pie Dataset", false,
				2, 2);

		createWidget4Property(parent,
				JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION);
		createWidget4Property(parent,
				JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION);

		createWidget4Property(parent,
				JRDesignPieDataset.PROPERTY_MIN_PERCENTAGE);
		createWidget4Property(parent, JRDesignPieDataset.PROPERTY_MAX_COUNT);

	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION, Messages.MChartPieDataset_other_key_expression);
		addProvidedProperties(JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION, Messages.MChartPieDataset_other_label_expression);
		addProvidedProperties(JRDesignPieDataset.PROPERTY_MIN_PERCENTAGE, Messages.MChartPieDataset_min_percentage);
		addProvidedProperties(JRDesignPieDataset.PROPERTY_MAX_COUNT, Messages.MChartPieDataset_max_count);
	}
}
