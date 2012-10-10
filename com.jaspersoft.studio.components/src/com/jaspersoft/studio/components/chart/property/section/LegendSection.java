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
package com.jaspersoft.studio.components.chart.property.section;

import net.sf.jasperreports.engine.base.JRBaseChart;
import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class LegendSection extends AbstractRealValueSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, Messages.LegendSection_Legend_Label,
				true, 2);

		createWidget4Property(group, JRBaseChart.PROPERTY_SHOW_LEGEND);

		getWidgetFactory().createCLabel(group, Messages.LegendSection_Position_Label);
		createWidget4Property(group, JRBaseChart.PROPERTY_LEGEND_POSITION,
				false);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		
		Composite colorComposite = new Composite(group, SWT.NONE);
		colorComposite.setLayout(new GridLayout(4,false));
		colorComposite.setLayoutData(gd);
		
		getWidgetFactory().createCLabel(colorComposite, Messages.LegendSection_Forecolor_Label);
		createWidget4Property(colorComposite, JRBaseChart.PROPERTY_LEGEND_COLOR, false);

		getWidgetFactory().createCLabel(colorComposite, Messages.LegendSection_Backcolor_Label);
		createWidget4Property(colorComposite, JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR, false);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, JRDesignChart.PROPERTY_LEGEND_FONT, false).getControl().setLayoutData(gd);

	}

}
