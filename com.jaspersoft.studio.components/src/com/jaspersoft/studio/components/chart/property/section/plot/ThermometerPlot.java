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
import net.sf.jasperreports.charts.design.JRDesignThermometerPlot;
import net.sf.jasperreports.charts.design.JRDesignValueDisplay;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class ThermometerPlot extends APlot {

	@Override
	public void createControls(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_MERCURY_COLOR);

		Group group = section.getWidgetFactory().createGroup(parent, "Value");
		group.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		group.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		section.createWidget4Property(group,
				JRDesignThermometerPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_FONT, false)
				.getControl().setLayoutData(gd);

		section.createWidget4Property(group,
				JRDesignThermometerPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_COLOR);

		section.createWidget4Property(group,
				JRDesignThermometerPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_MASK);

		section.createWidget4Property(group,
				JRDesignThermometerPlot.PROPERTY_VALUE_LOCATION);

		dataRange(section, parent, tabbedPropertySheetPage);
		highRange(section, parent, tabbedPropertySheetPage);
		mediumRange(section, parent, tabbedPropertySheetPage);
		lowRange(section, parent, tabbedPropertySheetPage);

	}

	private void dataRange(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_DATA_RANGE + "." //$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_DATA_RANGE + "."//$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);
	}

	private void lowRange(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_LOW_RANGE + "." //$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_LOW_RANGE + "."//$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);
	}

	private void mediumRange(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_MEDIUM_RANGE + "." //$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_MEDIUM_RANGE + "."//$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);
	}

	private void highRange(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_HIGH_RANGE + "." //$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		section.createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_HIGH_RANGE + "."//$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);
	}

}
