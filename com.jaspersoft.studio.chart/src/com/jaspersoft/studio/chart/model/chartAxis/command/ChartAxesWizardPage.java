/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model.chartAxis.command;

import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.chart.Activator;

public class ChartAxesWizardPage extends WizardPage {
	private byte chartAxes;
	private Table chartTable;

	public byte getChartAxis() {
		return chartAxes;
	}

	protected ChartAxesWizardPage() {
		super("chartaxiswizard"); //$NON-NLS-1$
		setTitle(Messages.ChartAxesWizardPage_chartaxis_wizard);
		setDescription(Messages.ChartAxesWizardPage_chartaxis_wizard_description);
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
		column2[0].setText(Messages.ChartAxesWizardPage_name);

		fillTableb4j(chartTable);
		column2[0].pack();
	}

	private void fillTableb4j(Table table) {
		table.setRedraw(false);

		TableItem ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_line_chart);
		ti.setImage(Activator.getImage("/icons/line.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_LINE);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_area_chart);
		ti.setImage(Activator.getImage("/icons/area.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_AREA);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_bar_chart);
		ti.setImage(Activator.getImage("/icons/bar.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_BAR);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_bar3d_chart);
		ti.setImage(Activator.getImage("/icons/bar3d.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_BAR3D);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_bubble_chart);
		ti.setImage(Activator.getImage("/icons/bubble.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_BUBBLE);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_candlestick_chart);
		ti.setImage(Activator.getImage("/icons/candlestick.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_CANDLESTICK);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_highlow_chart);
		ti.setImage(Activator.getImage("/icons/highlow.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_HIGHLOW);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_meter_chart);
		ti.setImage(Activator.getImage("/icons/meter.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_METER);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_pie3d_chart);
		ti.setImage(Activator.getImage("/icons/pie3d.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_PIE3D);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_pie_chart);
		ti.setImage(Activator.getImage("/icons/pie.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_PIE);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_scatter_chart);
		ti.setImage(Activator.getImage("/icons/scatter.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_SCATTER);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_thermometer_chart);
		ti.setImage(Activator.getImage("/icons/thermometer.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_THERMOMETER);

		ti = new TableItem(table, SWT.NONE);
		ti.setText(Messages.ChartAxesWizardPage_timeseries_chart);
		ti.setImage(Activator.getImage("/icons/timeseries.png")); //$NON-NLS-1$
		ti.setData(JRDesignChart.CHART_TYPE_TIMESERIES);

		table.setRedraw(true);
	}

}
