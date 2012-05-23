/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.chart.property.widget;

import net.sf.jasperreports.charts.design.JRDesignItemLabel;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.model.MChartItemLabel;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPColor;
import com.jaspersoft.studio.property.section.widgets.SPFont;

public class SPChartItemLabel extends ASPropertyWidget {

	public SPChartItemLabel(Composite parent, AbstractSection section,
			IPropertyDescriptor pDescriptor) {
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

		ilColor = new SPColor(parent, section, pd);

		pd = ml.getPropertyDescriptor(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR);
		section.getWidgetFactory().createCLabel(parent, pd.getDisplayName());

		ilBGColor = new SPColor(parent, section, pd);

		pd = ml.getPropertyDescriptor(JRDesignItemLabel.PROPERTY_FONT);

		GridData gd = new GridData();
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
			ilColor.setData(pnode,
					ml.getPropertyValue(JRDesignItemLabel.PROPERTY_COLOR));
			ilBGColor
					.setData(
							pnode,
							ml.getPropertyValue(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR));
			ilFont.setData(pnode,
					ml.getPropertyValue(JRDesignItemLabel.PROPERTY_FONT));
		}
	}
}
