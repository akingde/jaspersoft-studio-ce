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

import net.sf.jasperreports.charts.design.JRDesignGanttSeries;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class SeriesGanttSection extends AbstractSection {
	private SPExpression startExpr;
	private SPExpression endExpr;
	private SPExpression taskExpr;
	private SPExpression subtaskExpr;
	private SPExpression labelExpr;
	private SPExpression seriesExpr;
	private SPExpression percentExpr;

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

		startExpr = createExpression(parent,
				JRDesignGanttSeries.PROPERTY_START_DATE_EXPRESSION,
				Messages.common_start_date_expression, 200);
		endExpr = createExpression(parent,
				JRDesignGanttSeries.PROPERTY_END_DATE_EXPRESSION,
				Messages.common_end_date_expression, 200);
		taskExpr = createExpression(parent,
				JRDesignGanttSeries.PROPERTY_TASK_EXPRESSION,
				Messages.MGanttSeries_task_expression, 200);
		subtaskExpr = createExpression(parent,
				JRDesignGanttSeries.PROPERTY_SUBTASK_EXPRESSION,
				Messages.MGanttSeries_subtask_expression, 200);
		labelExpr = createExpression(parent,
				JRDesignGanttSeries.PROPERTY_LABEL_EXPRESSION,
				Messages.common_label_expression, 200);
		seriesExpr = createExpression(parent,
				JRDesignGanttSeries.PROPERTY_SERIES_EXPRESSION,
				Messages.common_series_expression, 200);
		percentExpr = createExpression(parent,
				JRDesignGanttSeries.PROPERTY_PERCENT_EXPRESSION,
				Messages.MGanttSeries_percent_expression, 200);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {

			startExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignGanttSeries.PROPERTY_START_DATE_EXPRESSION));
			endExpr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignGanttSeries.PROPERTY_END_DATE_EXPRESSION));
			taskExpr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignGanttSeries.PROPERTY_TASK_EXPRESSION));
			subtaskExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignGanttSeries.PROPERTY_SUBTASK_EXPRESSION));
			labelExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignGanttSeries.PROPERTY_LABEL_EXPRESSION));
			seriesExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignGanttSeries.PROPERTY_SERIES_EXPRESSION));
			percentExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(JRDesignGanttSeries.PROPERTY_PERCENT_EXPRESSION));
			updateWidgetsExpressionContext(element);
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}

	private void updateWidgetsExpressionContext(APropertyNode element) {
		JasperDesign jd=element.getJasperDesign();
		JasperReportsConfiguration config=element.getJasperConfiguration();
		if(jd!=null && config!=null && 
				element.getParent() instanceof MChartDataset){
			Object value = ((MChartDataset)element.getParent()).getValue();
			if(value instanceof JRDesignChartDataset){
				JRDesignDataset ds = ModelUtils.getDesignDatasetForDatasetRun(jd, ((JRDesignChartDataset) value).getDatasetRun());
				ExpressionContext ec=new ExpressionContext(ds, config);
				endExpr.setExpressionContext(ec);
				labelExpr.setExpressionContext(ec);
				percentExpr.setExpressionContext(ec);
				seriesExpr.setExpressionContext(ec);
				startExpr.setExpressionContext(ec);
				subtaskExpr.setExpressionContext(ec);
				taskExpr.setExpressionContext(ec);
			}
		}
	}
}
