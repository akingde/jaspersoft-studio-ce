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
package com.jaspersoft.studio.components.chart.property.section.plot;

import net.sf.jasperreports.charts.design.JRDesignDataRange;
import net.sf.jasperreports.charts.design.JRDesignMeterPlot;
import net.sf.jasperreports.charts.design.JRDesignValueDisplay;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class MeterPlot extends APlot {

	@Override
	public void createControls(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		section.createWidget4Property(parent, JRDesignMeterPlot.PROPERTY_UNITS);

		section.createWidget4Property(parent, JRDesignMeterPlot.PROPERTY_SHAPE);

		Group group = section.getWidgetFactory().createGroup(parent, "Tick");
		group.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		group.setLayoutData(gd);

		section.createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_TICK_COLOR);

		section.createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_TICK_INTERVAL);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		section.createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_TICK_LABEL_FONT, false).getControl()
				.setLayoutData(gd);

		section.createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_NEEDLE_COLOR);

		section.createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_METER_BACKGROUND_COLOR);

		section.createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_METER_ANGLE);

		group = section.getWidgetFactory().createGroup(parent, "Value");
		group.setLayout(new GridLayout(2, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		group.setLayoutData(gd);

		section.createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_COLOR);

		section.createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_MASK);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		section.createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_FONT, false)
				.getControl().setLayoutData(gd);

		section.createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_DATA_RANGE + "."
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		section.createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_DATA_RANGE + "."
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		section.createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_INTERVALS, false).getControl()
				.setLayoutData(gd);

	}
}
