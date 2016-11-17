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

import net.sf.jasperreports.charts.design.JRDesignAreaPlot;

public class AreaPlot extends AbstractRealValueSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		createCategory(parent, tabbedPropertySheetPage);
		createValue(parent, tabbedPropertySheetPage);
	}

	private void createCategory(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		
		parent = getWidgetFactory().createSectionTitle(parent,Messages.AreaPlot_categoryAxis, true, 2, 2);

		createWidget4Property(parent, JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LINE_COLOR);

		Composite group = getWidgetFactory().createSection(parent, Messages.common_label, false, 2, 2); //$NON-NLS-1$

		createWidget4Property(group, JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_EXPRESSION);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_FONT, false).getControl().setLayoutData(gd);

		group = getWidgetFactory().createSection(parent, Messages.AreaPlot_tick, false, 2, 2);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_FONT, false).getControl().setLayoutData(gd);

		getWidgetFactory().createCLabel(group, Messages.AreaPlot_color);
		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_COLOR, false);

		getWidgetFactory().createCLabel(group, Messages.AreaPlot_mask);
		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_MASK, false);

		getWidgetFactory().createCLabel(group, Messages.AreaPlot_rotation);
		createWidget4Property(group, JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_ROTATION, false);

		getWidgetFactory().createCLabel(group, Messages.AreaPlot_verticalTick); createWidget4Property(group, JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_VERTICAL_TICK_LABELS, false);

		createWidget4Property(parent, JRDesignAreaPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION);
		createWidget4Property(parent, JRDesignAreaPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION);
	}

	private void createValue(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		parent = getWidgetFactory().createSectionTitle(parent,
				Messages.AreaPlot_valueAxis, true, 2, 2);

		createWidget4Property(parent,JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LINE_COLOR);

		Composite group = getWidgetFactory().createSection(parent,
				Messages.common_label, false, 2, 2); //$NON-NLS-1$

		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_FONT, false)
				.getControl().setLayoutData(gd);
		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR);

		group = getWidgetFactory().createSection(parent, Messages.common_tick, false, //$NON-NLS-1$
				2, 2);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT, false)
				.getControl().setLayoutData(gd);
		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR);
		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK);
		createWidget4Property(group,
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS);
		createWidget4Property(parent,
				JRDesignAreaPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION);
		createWidget4Property(parent,
				JRDesignAreaPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION);
	}

}
