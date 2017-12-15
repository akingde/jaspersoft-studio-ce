/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.dataset;

import net.sf.jasperreports.charts.design.JRDesignHighLowDataset;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.dataset.descriptor.DatasetSection;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetHigLowSection extends DatasetSection {

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent = getWidgetFactory().createSection(parent, "High Low Dataset",
				false, 2, 2);

		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION, Messages.MChartHighLowDataset_high_expression);
		addProvidedProperties(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION, Messages.MChartHighLowDataset_low_expression);
		addProvidedProperties(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION, Messages.MChartHighLowDataset_open_expression);
		addProvidedProperties(JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION, Messages.MChartHighLowDataset_close_expression);
		addProvidedProperties(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION, Messages.MChartHighLowDataset_data_expression);
		addProvidedProperties(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION, Messages.common_series_expression);
		addProvidedProperties(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION, Messages.MChartHighLowDataset_volume_expression);
	}

}
