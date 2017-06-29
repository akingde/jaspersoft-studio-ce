/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import net.sf.jasperreports.charts.design.JRDesignItemLabel;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.model.MChartItemLabel;
import com.jaspersoft.studio.components.chart.property.descriptor.PlotPropertyDescriptor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPColor;
import com.jaspersoft.studio.property.section.widgets.SPFont;

public class SPChartItemLabel extends ASPropertyWidget<PlotPropertyDescriptor> {

	public SPChartItemLabel(Composite parent, AbstractSection section,
			PlotPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return null;
	}

	protected void createComponent(Composite parent) {
		ml = new MChartItemLabel(new JRDesignItemLabel(null, null));

		IPropertyDescriptor pd = ml
				.getPropertyDescriptor(JRDesignItemLabel.PROPERTY_COLOR);

		section.getWidgetFactory().createCLabel(parent, pd.getDisplayName());

		ilColor = new SPColor(parent, section, (ColorPropertyDescriptor) pd);

		pd = ml.getPropertyDescriptor(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR);
		section.getWidgetFactory().createCLabel(parent, pd.getDisplayName());

		ilBGColor = new SPColor(parent, section, (ColorPropertyDescriptor) pd);

		pd = ml.getPropertyDescriptor(JRDesignItemLabel.PROPERTY_FONT);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		ilFont = new SPFont(parent, section, pd);
		ilFont.getControl().setLayoutData(gd);
	}

	private SPColor ilColor;
	private SPColor ilBGColor;
	private SPFont ilFont;
	private MChartItemLabel ml;

	public void setData(APropertyNode pnode, Object value) {
		ml = (MChartItemLabel) value;
		if (value != null) {
			ilColor.setData(pnode,ml.getPropertyValue(JRDesignItemLabel.PROPERTY_COLOR));
			ilBGColor.setData(pnode, ml.getPropertyValue(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR));
			ilFont.setData(pnode,ml.getPropertyValue(JRDesignItemLabel.PROPERTY_FONT));
		}
	}
}
