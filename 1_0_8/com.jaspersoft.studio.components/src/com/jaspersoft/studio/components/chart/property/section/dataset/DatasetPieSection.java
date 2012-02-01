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

import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.property.section.widgets.SPNumber;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetPieSection extends DatasetSection {
	private SPExpression otherKeyExpr;
	private SPExpression otherLabelExpr;

	private SPNumber minPerc;
	private SPNumber maxSlice;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent = this.parent;

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));

		otherKeyExpr = createExpression(parent,
				JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION, "Other Key",
				120);

		otherLabelExpr = createExpression(parent,
				JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION,
				"Other Label", 120);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		CLabel lbl = getWidgetFactory().createCLabel(composite,
				Messages.MChartPieDataset_min_percentage, SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 120;
		lbl.setLayoutData(rd);

		minPerc = new SPNumber(composite, this,
				JRDesignPieDataset.PROPERTY_MIN_PERCENTAGE,
				Messages.MChartPieDataset_min_percentage_description);

		getWidgetFactory().createCLabel(composite,
				Messages.MChartPieDataset_max_count, SWT.RIGHT);

		maxSlice = new SPNumber(composite, this,
				JRDesignPieDataset.PROPERTY_MAX_COUNT,
				Messages.MChartPieDataset_max_count_description);
	}

	@Override
	public void refresh() {
		super.refresh();
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			otherKeyExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION));
			otherLabelExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION));

			minPerc.setData((Double) element
					.getPropertyValue(JRDesignPieDataset.PROPERTY_MIN_PERCENTAGE));
			maxSlice.setData((Integer) element
					.getPropertyValue(JRDesignPieDataset.PROPERTY_MAX_COUNT));

		}
		isRefreshing = false;
	}

}
