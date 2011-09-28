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
package com.jaspersoft.studio.components.chart.property.section.dataset;

import net.sf.jasperreports.charts.design.JRDesignHighLowDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.widgets.SPExpression;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetHigLowSection extends DatasetSection {
	private SPExpression highExpr;
	private SPExpression closeExpr;
	private SPExpression openExpr;
	private SPExpression lowExpr;
	private SPExpression dateExpr;
	private SPExpression seriesExpr;
	private SPExpression volumExpr;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent = this.parent;

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));

		highExpr = createExpression(parent,
				JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION, "High", 120);

		lowExpr = createExpression(parent,
				JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION, "Low", 120);

		openExpr = createExpression(parent,
				JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION, "Open", 120);

		closeExpr = createExpression(parent,
				JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION, "Close", 120);

		dateExpr = createExpression(parent,
				JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION, "Date", 120);

		seriesExpr = createExpression(parent,
				JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION, "Series",
				120);

		volumExpr = createExpression(parent,
				JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION, "Volume",
				120);
	}

	@Override
	public void refresh() {
		super.refresh();
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			highExpr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION));
			closeExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION));
			openExpr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION));
			lowExpr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION));
			dateExpr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION));
			seriesExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION));
			volumExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION));

		}
		isRefreshing = false;
	}

}
