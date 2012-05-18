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

import net.sf.jasperreports.charts.design.JRDesignBar3DPlot;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class Bar3dPlot extends APlot {

	@Override
	public void createControls(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		createCommon(section, parent);

		createCategory(section, parent, tabbedPropertySheetPage);

		createValue(section, parent, tabbedPropertySheetPage);

		createItemLabel(section, parent, tabbedPropertySheetPage);
	}

	public void createCommon(AbstractSection section, Composite parent) {
		parent = section.getWidgetFactory().createComposite(parent);
		parent.setLayout(new GridLayout(2, false));

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_SHOW_LABELS);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_X_OFFSET);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_Y_OFFSET);
	}

	private void createCategory(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		parent = section.getWidgetFactory().createSectionTitle(parent,
				"Category Axis", true, 2, 2);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LINE_COLOR);

		Composite group = section.getWidgetFactory().createSection(parent,
				"Label", false, 2, 2);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_EXPRESSION);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_FONT, false)
				.getControl().setLayoutData(gd);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_COLOR);

		group = section.getWidgetFactory().createSection(parent, "Tick", false,
				2, 2);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_FONT, false)
				.getControl().setLayoutData(gd);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_COLOR);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_MASK);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_ROTATION);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_VERTICAL_TICK_LABELS);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION);
	}

	private void createValue(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		parent = section.getWidgetFactory().createSectionTitle(parent,
				"Value Axis", true, 2, 2);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LINE_COLOR);

		Composite group = section.getWidgetFactory().createSection(parent,
				"Label", false, 2, 2);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_FONT, false)
				.getControl().setLayoutData(gd);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR);

		group = section.getWidgetFactory().createSection(parent, "Tick", false,
				2, 2);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT, false)
				.getControl().setLayoutData(gd);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK);

		section.createWidget4Property(group,
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION);
	}

	private void createItemLabel(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		parent = section.getWidgetFactory().createSectionTitle(parent,
				"Item Label", true, 2, 2);

		section.createWidget4Property(parent,
				JRDesignBar3DPlot.PROPERTY_ITEM_LABEL, false);
	}
}
