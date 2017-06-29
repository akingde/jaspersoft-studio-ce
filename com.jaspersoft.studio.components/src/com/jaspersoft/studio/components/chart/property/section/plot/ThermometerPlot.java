/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.plot;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

import net.sf.jasperreports.charts.design.JRDesignDataRange;
import net.sf.jasperreports.charts.design.JRDesignThermometerPlot;
import net.sf.jasperreports.charts.design.JRDesignValueDisplay;

public class ThermometerPlot extends AbstractRealValueSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_MERCURY_COLOR);

		Composite group = getWidgetFactory().createSection(parent,
				"Value", false, 2, 2);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group,
				JRDesignThermometerPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_FONT, false)
				.getControl().setLayoutData(gd);

		createWidget4Property(group,
				JRDesignThermometerPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_COLOR);

		createWidget4Property(group,
				JRDesignThermometerPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_MASK);

		createWidget4Property(group,
				JRDesignThermometerPlot.PROPERTY_VALUE_LOCATION);

		dataRange(parent, tabbedPropertySheetPage);
		highRange(parent, tabbedPropertySheetPage);
		mediumRange(parent, tabbedPropertySheetPage);
		lowRange(parent, tabbedPropertySheetPage);

	}

	private void dataRange(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_DATA_RANGE + "." //$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_DATA_RANGE + "."//$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);
	}

	private void lowRange(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_LOW_RANGE + "." //$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_LOW_RANGE + "."//$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);
	}

	private void mediumRange(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_MEDIUM_RANGE + "." //$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_MEDIUM_RANGE + "."//$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);
	}

	private void highRange(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_HIGH_RANGE + "." //$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		createWidget4Property(parent,
				JRDesignThermometerPlot.PROPERTY_HIGH_RANGE + "."//$NON-NLS-1$
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);
	}

}
