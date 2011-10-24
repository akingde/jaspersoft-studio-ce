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
package com.jaspersoft.studio.components.chart.property.section.series;

import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPExpression;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class SeriesTimeSerieSection extends AbstractSection {
	private SPExpression tpExpr;
	private SPExpression labelExpr;
	private SPExpression seriesExpr;
	private SPExpression valueExpr;

	protected Composite composite;
	protected Composite parent;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));
		this.parent = parent;

		tpExpr = createExpression(parent,
				JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION,
				Messages.MTimeSeries_time_period_expression, 200);
		labelExpr = createExpression(parent,
				JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION,
				Messages.common_label_expression, 200);
		seriesExpr = createExpression(parent,
				JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION,
				Messages.common_series_expression, 200);
		valueExpr = createExpression(parent,
				JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION,
				Messages.common_value_expression, 200);

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {

			tpExpr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION));
			labelExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION));
			seriesExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION));
			valueExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION));

		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}

}
