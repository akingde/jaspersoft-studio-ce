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
public class TitleSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSectionTitle(parent, "Title",
				true, 4, 1);

		getWidgetFactory().createCLabel(group, "Expression");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		createWidget4Property(group,
				StandardChartSettings.PROPERTY_TITLE_EXPRESSION, false)
				.getControl().setLayoutData(gd);

		createWidget4Property(group,
				StandardChartSettings.PROPERTY_TITLE_POSITION);

		createWidget4Property(group, StandardChartSettings.PROPERTY_TITLE_COLOR);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		createWidget4Property(group, StandardChartSettings.PROPERTY_TITLE_FONT,
				false).getControl().setLayoutData(gd);

	}

}
