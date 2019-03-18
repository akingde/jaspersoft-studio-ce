/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section;

import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.engine.base.JRBaseChart;
import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.chartAxis.MChartAxes;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.layout.ResizableControlLayout;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPEvaluationTime;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ChartSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(3, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		
		Composite renderContainer = new Composite(parent, SWT.NONE);
		//custom layout that make the style control start (small) and allow it to grow until it reach the width of the 
		//panel, but not above it
		Layout renderLayout = new ResizableControlLayout(100);
		renderContainer.setLayoutData(gd);
		createWidget4Property(renderContainer, JRBaseChart.PROPERTY_RENDER_TYPE).getControl().setLayoutData(gd);
		renderContainer.setLayout(renderLayout);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		Composite themeContainer = new Composite(parent, SWT.NONE);
		Layout themeLayout = new ResizableControlLayout(100);
		themeContainer.setLayoutData(gd);
		createWidget4Property(themeContainer, JRBaseChart.PROPERTY_THEME).getControl().setLayoutData(gd);
		themeContainer.setLayout(themeLayout);

		IPropertyDescriptor pd = getPropertyDesriptor(JRDesignChart.PROPERTY_EVALUATION_TIME);
		IPropertyDescriptor gpd = getPropertyDesriptor(JRDesignChart.PROPERTY_EVALUATION_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		widgets.put(pd.getId(), new SPEvaluationTime(parent, this, pd, gpd));
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRBaseChart.PROPERTY_RENDER_TYPE, Messages.MChart_renderer_type);
		addProvidedProperties(JRBaseChart.PROPERTY_THEME, Messages.MChart_theme);
		addProvidedProperties(JRDesignChart.PROPERTY_EVALUATION_TIME, Messages.MChart_evaluation_time);

	}

	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MChartAxes)
			return (APropertyNode) md.getPropertyValue(JRDesignChartAxis.PROPERTY_CHART);
		return md;
	}

}
