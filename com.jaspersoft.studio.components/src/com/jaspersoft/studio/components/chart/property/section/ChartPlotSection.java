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

import net.sf.jasperreports.engine.base.JRBaseChartPlot;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.PlotFactory;
import com.jaspersoft.studio.model.APropertyNode;
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

		parent.setLayout(new GridLayout(2, false));
		createCommon(parent);

		PlotFactory.createPlot(this, parent, tabbedPropertySheetPage);
	}

	public void createCommon(Composite parent) {
		Composite group = getWidgetFactory().createComposite(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		group.setLayoutData(gd);
		group.setLayout(new GridLayout(4, false));

		getWidgetFactory().createCLabel(group, "Backcolor");

		createWidget4Property(group, JRBaseChartPlot.PROPERTY_BACKCOLOR, false);

		getWidgetFactory().createCLabel(group, "Alpha");
		createWidget4Property(group, JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA,
				false);

		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalSpan = 3;
		createWidget4Property(group, JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA)
				.getLabel().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		createWidget4Property(group, JRBaseChartPlot.PROPERTY_SERIES_COLORS)
				.getControl().setLayoutData(gd);

		createWidget4Property(group, JRBaseChartPlot.PROPERTY_ORIENTATION);
	}

	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MChart) {
			MChartPlot chartplot = (MChartPlot) md
					.getPropertyValue(MChart.PLOTPROPERTY);
			return chartplot;
		}
		return md;
	}

}
