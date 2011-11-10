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
package com.jaspersoft.studio.components.chart.model.chartAxis.command;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jasperreports.charts.JRAreaPlot;
import net.sf.jasperreports.charts.JRBar3DPlot;
import net.sf.jasperreports.charts.JRBarPlot;
import net.sf.jasperreports.charts.JRBubblePlot;
import net.sf.jasperreports.charts.JRCandlestickPlot;
import net.sf.jasperreports.charts.JRHighLowPlot;
import net.sf.jasperreports.charts.JRLinePlot;
import net.sf.jasperreports.charts.JRScatterPlot;
import net.sf.jasperreports.charts.JRTimeSeriesPlot;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.wizard.ChartTypeWizardPage;

public class ChartAxesWizardPage extends WizardPage {
	private byte chartAxes;
	private Table chartTable;
	private Class<? extends JRChartPlot> chartPlot;

	public byte getChartAxis() {
		return chartAxes;
	}

	protected ChartAxesWizardPage(Class<? extends JRChartPlot> chartPlot) {
		super("chartaxiswizard"); //$NON-NLS-1$
		setTitle(Messages.common_chartaxis_wizard);
		setDescription(Messages.ChartAxesWizardPage_chartaxis_wizard_description);
		this.chartPlot = chartPlot;
	}

	@Override
	public void dispose() {
		TableItem[] tis = chartTable.getSelection();
		if (tis.length > 0) {
			TableItem ti = tis[0];
			chartAxes = (Byte) ti.getData();
		}
		super.dispose();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		setControl(composite);

		chartTable = new Table(composite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 500;
		gd.widthHint = 300;
		chartTable.setLayoutData(gd);
		chartTable.setHeaderVisible(false);
		chartTable.setLinesVisible(true);

		TableColumn[] column2 = new TableColumn[1];
		column2[0] = new TableColumn(chartTable, SWT.NONE);
		column2[0].setText(Messages.common_name);

		fillTableb4j(chartTable);
		column2[0].pack();
	}

	private void fillTableb4j(Table table) {
		table.setRedraw(false);

		for (byte ctype : plotmap.keySet()) {
			// if (chartPlot != null
			// && !plotmap.get(ctype).isAssignableFrom(chartPlot))
			// continue;
			// hmm here we should use the same from jfreechart
			ChartTypeWizardPage.getTableItem(ctype, table);
		}

		table.setRedraw(true);
	}

	private static Map<Byte, Class<? extends JRChartPlot>> plotmap = new LinkedHashMap<Byte, Class<? extends JRChartPlot>>();
	static {
		plotmap.put(JRDesignChart.CHART_TYPE_LINE, JRLinePlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_XYLINE, JRLinePlot.class);

		plotmap.put(JRDesignChart.CHART_TYPE_AREA, JRAreaPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_XYAREA, JRAreaPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_STACKEDAREA, JRAreaPlot.class);

		plotmap.put(JRDesignChart.CHART_TYPE_BAR, JRBarPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_STACKEDBAR, JRBarPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_XYBAR, JRBarPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_BAR3D, JRBar3DPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_STACKEDBAR3D, JRBar3DPlot.class);

		plotmap.put(JRDesignChart.CHART_TYPE_BUBBLE, JRBubblePlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_CANDLESTICK,
				JRCandlestickPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_HIGHLOW, JRHighLowPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_SCATTER, JRScatterPlot.class);
		plotmap.put(JRDesignChart.CHART_TYPE_TIMESERIES, JRTimeSeriesPlot.class);
	}

}
