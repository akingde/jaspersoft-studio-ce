/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chartspider.property.section;

import net.sf.jasperreports.components.spiderchart.StandardChartSettings;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class LegendSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSectionTitle(parent,
				"Legend", true, 6, 1);

		GridData gd = new GridData();
		gd.horizontalSpan = 5;

		createWidget4Property(group, StandardChartSettings.PROPERTY_SHOW_LEGEND)
				.getControl().setLayoutData(gd);

		getWidgetFactory().createCLabel(group, "Position");
		createWidget4Property(group,
				StandardChartSettings.PROPERTY_LEGEND_POSITION, false);

		getWidgetFactory().createCLabel(group, "F");
		createWidget4Property(group,
				StandardChartSettings.PROPERTY_LEGEND_COLOR, false);

		getWidgetFactory().createCLabel(group, "B");
		createWidget4Property(group,
				StandardChartSettings.PROPERTY_LEGEND_BACKGROUND_COLOR, false);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 6;
		createWidget4Property(group,
				StandardChartSettings.PROPERTY_LEGEND_FONT, false).getControl()
				.setLayoutData(gd);
	}

}
