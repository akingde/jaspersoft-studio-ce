/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.dataset;

import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.dataset.descriptor.DatasetSection;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetTimeSeriesSection extends DatasetSection {

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
		parent = getWidgetFactory().createSection(parent, "Time Series Dataset", false, 2, 2);

		createWidget4Property(parent,
				JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD, Messages.MChartTimeSeriesDataset_time_period);
	}

}
