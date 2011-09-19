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

import java.util.List;

import net.sf.jasperreports.charts.JRGanttSeries;
import net.sf.jasperreports.charts.design.JRDesignGanttDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog.SeriesDialog;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.series.GanttSeries;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.widget.DatasetSeriesWidget;
import com.jaspersoft.studio.property.dataset.ExpressionWidget;

public class DSGantt extends ADSComponent {
	private JRDesignGanttDataset dataset;
	private ExpressionWidget labelWidget;
	private ExpressionWidget percent;
	private ExpressionWidget endDate;
	private ExpressionWidget starDate;
	private ExpressionWidget subtask;
	private ExpressionWidget task;
	private CCombo seriesCombo;

	public DSGantt(Composite composite, DatasetSeriesWidget dsWidget) {
		super(composite, dsWidget);
	}

	@Override
	public String getName() {
		return "Gantt Dataset";
	}

	@Override
	public void setData(DrawVisitor drawVisitor, JRDesignChart jrChart,
			SimpleFileResolver fResolver) {
		Assert.isTrue(jrChart.getDataset() instanceof JRDesignGanttDataset);
		super.setData(drawVisitor, jrChart, fResolver);
		dataset = (JRDesignGanttDataset) jrChart.getDataset();
		setSeries(0);
	}

	private void setSeries(int selection) {
		List<JRGanttSeries> seriesList = dataset.getSeriesList();
		if (!seriesList.isEmpty()) {
			String[] srnames = new String[seriesList.size()];
			for (int i = 0; i < seriesList.size(); i++) {
				JRGanttSeries cs = seriesList.get(i);
				JRExpression se = cs.getSeriesExpression();
				srnames[i] = se != null && se.getText() != null ? se.getText()
						: "";
			}
			seriesCombo.setItems(srnames);
			seriesCombo.select(selection);
			handleSelectSeries(selection);
		} else {
			seriesCombo.setItems(new String[0]);
			handleSelectSeries(-1);
		}
	}

	private void handleSelectSeries(int selection) {
		JRGanttSeries serie = null;
		if (selection >= 0 && selection < dataset.getSeriesList().size())
			serie = dataset.getSeriesList().get(selection);

		task.bindObject(serie, "TaskExpression");
		subtask.bindObject(serie, "SubtaskExpression");
		starDate.bindObject(serie, "StartDateExpression");
		endDate.bindObject(serie, "EndDateExpression");
		percent.bindObject(serie, "PercentExpression");
		labelWidget.bindObject(serie, "LabelExpression");
	}

	protected Control createChartTop(Composite composite) {
		Composite yCompo = new Composite(composite, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		yCompo.setLayout(new GridLayout(10, false));

		Label lbl = new Label(yCompo, SWT.NONE);
		lbl.setText("Series");

		seriesCombo = new CCombo(yCompo, SWT.READ_ONLY | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 300;
		seriesCombo.setLayoutData(gd);
		seriesCombo.setItems(new String[] { "series 1" });
		seriesCombo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				handleSelectSeries(seriesCombo.getSelectionIndex());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		final Button btn = new Button(yCompo, SWT.PUSH | SWT.FLAT);
		btn.setText("...");
		btn.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				GanttSeries serie = new GanttSeries();
				SeriesDialog dlg = new SeriesDialog(btn.getShell(), serie);
				List<JRGanttSeries> oldList = dataset.getSeriesList();
				int oldsel = seriesCombo.getSelectionIndex();
				JRGanttSeries selected = null;
				if (oldsel >= 0)
					selected = oldList.get(oldsel);
				serie.setList(oldList);
				if (dlg.open() == Window.OK) {
					List<JRGanttSeries> newlist = serie.getList();
					for (JRGanttSeries item : dataset.getSeries())
						dataset.removeGanttSeries(item);
					for (JRGanttSeries item : serie.getList())
						dataset.addGanttSeries(item);

					int sel = selected != null
							&& newlist.indexOf(selected) >= 0 ? newlist
							.indexOf(selected) : 0;
					setSeries(sel);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		return yCompo;
	}

	@Override
	protected Control createChartLeft(Composite parent) {
		Composite yCompo = new Composite(parent, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		yCompo.setLayout(new GridLayout(3, false));

		task = new ExpressionWidget(yCompo, "Task");
		subtask = new ExpressionWidget(yCompo, "Subtask");
		return yCompo;
	}

	@Override
	protected Control createChartBottom(Composite parent) {
		Composite yCompo = new Composite(parent, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		yCompo.setLayout(new GridLayout(9, false));

		starDate = new ExpressionWidget(yCompo, "Start Date");
		endDate = new ExpressionWidget(yCompo, "End Date");
		percent = new ExpressionWidget(yCompo, "Percent");
		return yCompo;
	}

	@Override
	protected Control createChartRight(Composite parent) {
		Composite yCompo = new Composite(parent, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		yCompo.setLayout(new GridLayout(3, false));

		labelWidget = new ExpressionWidget(yCompo, "Label");
		return yCompo;
	}

}
