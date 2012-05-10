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

import net.sf.jasperreports.charts.design.JRDesignValueDataset;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.components.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetValueSection extends DatasetSection {

	private SPExpression valueExpr;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent = this.parent;

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));

		valueExpr = createExpression(parent,
				JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION, "Value", 120);
	}

	@Override
	public void refresh() {
		super.refresh();
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			valueExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION));
			updateWidgetsExpressionContext(element);
		}
		isRefreshing = false;
	}
	
	private void updateWidgetsExpressionContext(APropertyNode element) {
		JasperDesign jd=element.getJasperDesign();
		JasperReportsConfiguration config=element.getJasperConfiguration();
		if(jd!=null && config!=null && 
				element instanceof MChartDataset){
			Object value = ((MChartDataset)element).getValue();
			if(value instanceof JRDesignChartDataset){
				JRDesignDataset ds = ModelUtils.getDesignDatasetForDatasetRun(jd, ((JRDesignChartDataset) value).getDatasetRun());
				ExpressionContext ec=new ExpressionContext(ds, config);
				valueExpr.setExpressionContext(ec);
			}
		}
	}

}
