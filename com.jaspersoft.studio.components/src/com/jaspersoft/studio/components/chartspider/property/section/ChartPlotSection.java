/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chartspider.property.section;

import net.sf.jasperreports.components.spiderchart.StandardSpiderPlot;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ChartPlotSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_BACKCOLOR);
		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_BACKGROUND_ALPHA);
		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_FOREGROUND_ALPHA);
		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_AXIS_LINE_COLOR);
		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_AXIS_LINE_WIDTH);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_LABEL_COLOR);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_LABEL_FONT);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_LABEL_GAP);

		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_MAX_VALUE_EXPRESSION);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_WEB_FILLED);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_TABLE_ORDER);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_START_ANGLE);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_HEAD_PERCENT);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_INTERIOR_GAP);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_ROTATION);
	}

}
