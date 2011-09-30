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
package com.jaspersoft.studio.components.chart.wizard;

import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChart;

public class ChartTypeWizardPage extends WizardPage {
	private MChart chart;
	private byte chartType = JRDesignChart.CHART_TYPE_LINE;
	private Table chartTable;

	protected ChartTypeWizardPage(MChart chart) {
		super("chartwizard"); //$NON-NLS-1$
		setTitle(Messages.common_chart_wizard);
		setDescription(Messages.ChartWizardPage_chart_wizard_description);
		this.chart = chart;
		this.chartType = ((JRDesignChart) chart.getValue()).getChartType();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		setControl(composite);

		chartTable = new Table(composite, SWT.V_SCROLL | SWT.MULTI
				| SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
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

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		chartTable.setLayout(tlayout);

		chartTable.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item instanceof TableItem) {
					chartType = (Byte) ((TableItem) e.item).getData();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		setTableSelection();
		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(getControl(), "Jaspersoft.wizard"); //$NON-NLS-1$
	}

	private void setTableSelection() {
		for (TableItem ti : chartTable.getItems()) {
			if (((Byte) ti.getData()).intValue() == chartType) {
				chartTable.setSelection(ti);
				break;
			}
		}
	}

	@Override
	public IWizardPage getNextPage() {
		JRDesignChart oldChart = (JRDesignChart) chart.getValue();
		if (chartType != oldChart.getChartType()) {
			oldChart.setChartType(chartType);
			MChart.setupChart(oldChart);
		}
		return super.getNextPage();
	}

	@Override
	public boolean canFlipToNextPage() {
		JRDesignChart old = (JRDesignChart) chart.getValue();
		if (chartType != old.getChartType()) {
			old.setChartType(chartType);
		}
		return super.canFlipToNextPage();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			chartTable.setFocus();
	}

	private void fillTableb4j(Table table) {
		table.setRedraw(false);

		getTableItem(JRDesignChart.CHART_TYPE_AREA, table);
		getTableItem(JRDesignChart.CHART_TYPE_STACKEDAREA, table);
		getTableItem(JRDesignChart.CHART_TYPE_XYAREA, table);

		getTableItem(JRDesignChart.CHART_TYPE_BAR, table);
		getTableItem(JRDesignChart.CHART_TYPE_BAR3D, table);
		getTableItem(JRDesignChart.CHART_TYPE_XYBAR, table);
		getTableItem(JRDesignChart.CHART_TYPE_STACKEDBAR, table);
		getTableItem(JRDesignChart.CHART_TYPE_STACKEDBAR3D, table);

		getTableItem(JRDesignChart.CHART_TYPE_LINE, table);
		getTableItem(JRDesignChart.CHART_TYPE_XYLINE, table);

		getTableItem(JRDesignChart.CHART_TYPE_PIE, table);
		getTableItem(JRDesignChart.CHART_TYPE_PIE3D, table);

		getTableItem(JRDesignChart.CHART_TYPE_BUBBLE, table);
		getTableItem(JRDesignChart.CHART_TYPE_CANDLESTICK, table);
		getTableItem(JRDesignChart.CHART_TYPE_TIMESERIES, table);
		getTableItem(JRDesignChart.CHART_TYPE_HIGHLOW, table);
		getTableItem(JRDesignChart.CHART_TYPE_SCATTER, table);

		getTableItem(JRDesignChart.CHART_TYPE_THERMOMETER, table);
		getTableItem(JRDesignChart.CHART_TYPE_METER, table);

		getTableItem(JRDesignChart.CHART_TYPE_GANTT, table);
		getTableItem(JRDesignChart.CHART_TYPE_MULTI_AXIS, table);

		table.setRedraw(true);
	}

	public static TableItem getTableItem(byte chartype, Table table) {
		switch (chartype) {
		case JRDesignChart.CHART_TYPE_AREA:
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_area_chart);
			ti.setImage(Activator.getImage("/icons/area.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_AREA);
			return ti;

		case JRDesignChart.CHART_TYPE_BAR:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_bar_chart);
			ti.setImage(Activator.getImage("/icons/bar.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BAR);
			return ti;
		case JRDesignChart.CHART_TYPE_BAR3D:

			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_bar3d_chart);
			ti.setImage(Activator.getImage("/icons/bar3d.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BAR3D);
			return ti;
		case JRDesignChart.CHART_TYPE_BUBBLE:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_bubble_chart);
			ti.setImage(Activator.getImage("/icons/bubble.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BUBBLE);
			return ti;
		case JRDesignChart.CHART_TYPE_CANDLESTICK:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_candlestick_chart);
			ti.setImage(Activator.getImage("/icons/candlestick.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_CANDLESTICK);
			return ti;
		case JRDesignChart.CHART_TYPE_GANTT:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_gantt);
			ti.setImage(Activator.getImage("/icons/gantt.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_GANTT);
			return ti;
		case JRDesignChart.CHART_TYPE_HIGHLOW:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_highlow_chart);
			ti.setImage(Activator.getImage("/icons/highlow.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_HIGHLOW);
			return ti;
		case JRDesignChart.CHART_TYPE_LINE:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_line_chart);
			ti.setImage(Activator.getImage("/icons/line.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_LINE);
			return ti;
		case JRDesignChart.CHART_TYPE_METER:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_meter_chart);
			ti.setImage(Activator.getImage("/icons/meter.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_METER);
			return ti;
		case JRDesignChart.CHART_TYPE_MULTI_AXIS:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_multiaxes_chart);
			ti.setImage(Activator.getImage("/icons/multiaxis.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_MULTI_AXIS);
			return ti;
		case JRDesignChart.CHART_TYPE_PIE:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_pie_chart);
			ti.setImage(Activator.getImage("/icons/pie.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_PIE);
			return ti;
		case JRDesignChart.CHART_TYPE_PIE3D:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_pie3d_chart);
			ti.setImage(Activator.getImage("/icons/pie3d.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_PIE3D);
			return ti;
		case JRDesignChart.CHART_TYPE_SCATTER:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_scatter_chart);
			ti.setImage(Activator.getImage("/icons/scatter.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_SCATTER);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDAREA:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_stacked_area);
			ti.setImage(Activator.getImage("/icons/stackedarea.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDAREA);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDBAR:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_stacked_bar);
			ti.setImage(Activator.getImage("/icons/stackedbar.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDBAR);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDBAR3D:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_stacked_bar3D);
			ti.setImage(Activator.getImage("/icons/stackedbar3d.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDBAR3D);
			return ti;
		case JRDesignChart.CHART_TYPE_THERMOMETER:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_thermometer_chart);
			ti.setImage(Activator.getImage("/icons/thermometer.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_THERMOMETER);
			return ti;
		case JRDesignChart.CHART_TYPE_TIMESERIES:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_timeseries_chart);
			ti.setImage(Activator.getImage("/icons/timeseries.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_TIMESERIES);
			return ti;
		case JRDesignChart.CHART_TYPE_XYAREA:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_xy_area);
			ti.setImage(Activator.getImage("/icons/xyarea.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYAREA);
			return ti;
		case JRDesignChart.CHART_TYPE_XYBAR:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_xy_bar);
			ti.setImage(Activator.getImage("/icons/xybar.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYBAR);
			return ti;
		case JRDesignChart.CHART_TYPE_XYLINE:
			ti = new TableItem(table, SWT.NONE);
			ti.setText(Messages.common_xy_line);
			ti.setImage(Activator.getImage("/icons/xyline.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYLINE);
			return ti;
		}
		return null;
	}
}
