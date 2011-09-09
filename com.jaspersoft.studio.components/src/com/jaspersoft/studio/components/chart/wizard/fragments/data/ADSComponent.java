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
package com.jaspersoft.studio.components.chart.wizard.fragments.data;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignTimePeriodDataset;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports.charts.design.JRDesignXyDataset;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.chart.figure.ChartFigure;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog.ChartDatasetDialog;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.widget.DatasetSeriesWidget;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class ADSComponent {
	private Control control;
	protected Label imgLabel;
	private ChartFigure chartFigure;
	private Canvas canvasChart;
	private JRDesignChart jrChart;
	private DatasetSeriesWidget dsWidget;

	public ADSComponent(Composite composite, DatasetSeriesWidget dsWidget) {
		createControl(composite);
		this.dsWidget = dsWidget;
	}

	public abstract String getName();

	public void setData(DrawVisitor drawVisitor, JRDesignChart jrChart,
			SimpleFileResolver fResolver) {
		this.jrChart = jrChart;
		jrChart.setWidth(500);
		jrChart.setHeight(325);
		chartFigure.setJRElement(jrChart, drawVisitor, fResolver);
		canvasChart.redraw();
	}

	public Control getControl() {
		return control;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		final Button b = new Button(composite, SWT.PUSH | SWT.FLAT);
		b.setText(getName());
		b.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				Map<Class<? extends JRDesignElementDataset>, String> map = new HashMap<Class<? extends JRDesignElementDataset>, String>();
				if (jrChart.getChartType() == JRChart.CHART_TYPE_XYBAR) {
					map.put(JRDesignTimePeriodDataset.class,
							dsWidget.getName(JRDesignTimePeriodDataset.class));
					map.put(JRDesignTimeSeriesDataset.class,
							dsWidget.getName(JRDesignTimeSeriesDataset.class));
					map.put(JRDesignXyDataset.class,
							dsWidget.getName(JRDesignXyDataset.class));

				}
				if (!map.isEmpty()) {
					Class<? extends JRDesignElementDataset> selclass = (Class<? extends JRDesignElementDataset>) jrChart
							.getDataset().getClass();
					ChartDatasetDialog dialog = new ChartDatasetDialog(b
							.getShell(), map, selclass);
					if (dialog.open() == Window.OK) {
						Class<? extends JRDesignElementDataset> newselclass = dialog
								.getSelection();
						if (!selclass.equals(newselclass))
							try {
								JRChartDataset jrded = (JRChartDataset) newselclass
										.getConstructor(JRChartDataset.class)
										.newInstance(jrChart.getDataset());
								jrChart.setDataset(jrded);
								dsWidget.setDataset(null, jrChart);
							} catch (Exception e1) {
								UIUtils.showError(e1);
							}
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		createChartTop(composite);
		new Label(composite, SWT.NONE).setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		createChartLeft(composite);

		createChartPreview(composite);

		createChartRight(composite);

		new Label(composite, SWT.NONE).setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		createChartBottom(composite);

		this.control = composite;
	}

	protected abstract Control createChartTop(Composite parent);

	protected abstract Control createChartLeft(Composite parent);

	protected abstract Control createChartRight(Composite parent);

	protected abstract Control createChartBottom(Composite parent);

	protected Control createChartPreview(Composite composite) {
		canvasChart = new Canvas(composite, SWT.BORDER | SWT.NO_REDRAW_RESIZE
				| SWT.NO_BACKGROUND);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 500;
		gd.heightHint = 325;
		canvasChart.setLayoutData(gd);

		LightweightSystem lws = new J2DLightweightSystem();
		lws.setControl(canvasChart);

		chartFigure = new ChartFigure();
		lws.setContents(chartFigure);
		return canvasChart;
	}
}
