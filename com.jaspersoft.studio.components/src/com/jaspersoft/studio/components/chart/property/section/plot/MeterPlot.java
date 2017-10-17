/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.plot;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

import net.sf.jasperreports.charts.design.JRDesignDataRange;
import net.sf.jasperreports.charts.design.JRDesignMeterPlot;
import net.sf.jasperreports.charts.design.JRDesignValueDisplay;

public class MeterPlot extends AbstractRealValueSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		createWidget4Property(parent, JRDesignMeterPlot.PROPERTY_UNITS);
		createWidget4Property(parent, JRDesignMeterPlot.PROPERTY_SHAPE);
		Composite group = getWidgetFactory().createSection(parent,Messages.common_tick, false, 2, 2);
		createWidget4Property(group,JRDesignMeterPlot.PROPERTY_TICK_COLOR);
		createWidget4Property(group,JRDesignMeterPlot.PROPERTY_TICK_INTERVAL);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group,JRDesignMeterPlot.PROPERTY_TICK_LABEL_FONT, false).getControl().setLayoutData(gd);

		createWidget4Property(parent, JRDesignMeterPlot.PROPERTY_NEEDLE_COLOR);

		createWidget4Property(parent, JRDesignMeterPlot.PROPERTY_METER_BACKGROUND_COLOR);

		createWidget4Property(parent, JRDesignMeterPlot.PROPERTY_METER_ANGLE);

		group = getWidgetFactory().createSection(parent, "Value", false, 2, 2);

		createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_COLOR);

		createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_MASK);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_FONT, false)
				.getControl().setLayoutData(gd);

		createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_DATA_RANGE + "."
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_DATA_RANGE + "."
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent,
				JRDesignMeterPlot.PROPERTY_INTERVALS, false).getControl()
				.setLayoutData(gd);
	}
}
