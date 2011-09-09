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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChartItemLabel;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.section.AbstractSection;

public class BtnChartItemLabel {

	public BtnChartItemLabel(Composite parent, AbstractSection section,
			String property) {
		createComponent(parent, section, property);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		CLabel lbl = section.getWidgetFactory().createCLabel(composite,
				" Color", SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		ilColor = new BtnColor(composite, section,
				JRDesignItemLabel.PROPERTY_COLOR,
				Messages.MChartItemLabel_color_description);

		section.getWidgetFactory().createCLabel(composite, "Background",
				SWT.RIGHT);

		ilBGColor = new BtnColor(composite, section,
				JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR,
				Messages.MChartItemLabel_background_color_description);

		section.getWidgetFactory().createCLabel(composite, "Font", SWT.RIGHT);

		ilFont = new BtnFont(composite, section,
				JRDesignItemLabel.PROPERTY_FONT, false);
	}

	private BtnColor ilColor;
	private BtnColor ilBGColor;
	private BtnFont ilFont;

	public void setData(MChartItemLabel element) {
		if (element != null) {
			ilColor.setData(element, (RGB) element
					.getPropertyValue(JRDesignItemLabel.PROPERTY_COLOR));
			ilBGColor
					.setData(
							element,
							(RGB) element
									.getPropertyValue(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR));
			ilFont.setData(element, (MFont) element
					.getPropertyValue(JRDesignItemLabel.PROPERTY_FONT));
		}
	}
}
