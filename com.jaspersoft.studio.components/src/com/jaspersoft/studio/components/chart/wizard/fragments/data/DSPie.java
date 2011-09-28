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

import java.text.NumberFormat;
import java.util.List;

import net.sf.jasperreports.charts.JRPieSeries;
import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.apache.commons.validator.routines.FloatValidator;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog.SeriesDialog;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.series.PieSerie;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.widget.DatasetSeriesWidget;
import com.jaspersoft.studio.property.dataset.ExpressionWidget;

public class DSPie extends ADSComponent {
	private JRDesignPieDataset dataset;
	private ExpressionWidget valueWidget;
	private ExpressionWidget labelWidget;
	private ExpressionWidget key;
	private CCombo seriesCombo;
	private Text minSlice;
	private Spinner maxSlice;
	private Button obtn;

	public DSPie(Composite composite, DatasetSeriesWidget dsWidget) {
		super(composite, dsWidget);
	}

	@Override
	public String getName() {
		return "Pie Dataset";
	}

	@Override
	public void setData(DrawVisitor drawVisitor, JRDesignElement jrChart,
			JRDesignElementDataset eDataset, SimpleFileResolver fResolver) {
		Assert.isTrue(eDataset instanceof JRDesignPieDataset);
		super.setData(drawVisitor, jrChart, eDataset, fResolver);
		dataset = (JRDesignPieDataset) eDataset;
		setSeries(0);

		if (dataset.getMinPercentage() != null)
			minSlice.setText(NumberFormat.getInstance().format(
					dataset.getMinPercentage()));
		else
			minSlice.setText("");
		maxSlice.setSelection(dataset.getMaxCount() != null ? dataset
				.getMaxCount().intValue() : 0);
	}

	private void setSeries(int selection) {
		List<JRPieSeries> seriesList = dataset.getSeriesList();
		if (!seriesList.isEmpty()) {
			String[] srnames = new String[seriesList.size()];
			for (int i = 0; i < seriesList.size(); i++) {
				srnames[i] = String.format("Pie series (%d)", i);
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
		JRPieSeries serie = null;
		if (selection >= 0 && selection < dataset.getSeriesList().size())
			serie = dataset.getSeriesList().get(selection);

		valueWidget.bindObject(serie, "ValueExpression");
		key.bindObject(serie, "KeyExpression");
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
				obtn.setSelection(false);
				valueWidget.setEnabled(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		final Button btn = new Button(yCompo, SWT.PUSH | SWT.FLAT);
		btn.setText("...");
		btn.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				PieSerie serie = new PieSerie();
				SeriesDialog dlg = new SeriesDialog(btn.getShell(), serie);
				List<JRPieSeries> oldList = dataset.getSeriesList();
				int oldsel = seriesCombo.getSelectionIndex();
				JRPieSeries selected = null;
				if (oldsel >= 0)
					selected = oldList.get(oldsel);
				serie.setList(oldList);
				if (dlg.open() == Window.OK) {
					List<JRPieSeries> newlist = serie.getList();
					for (JRPieSeries item : dataset.getSeries())
						dataset.removePieSeries(item);
					for (JRPieSeries item : serie.getList())
						dataset.addPieSeries(item);

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

		obtn = new Button(yCompo, SWT.TOGGLE);
		obtn.setText("Other Section");
		obtn.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				boolean selection = obtn.getSelection();
				valueWidget.setEnabled(!selection);
				if (selection) {
					key.bindObject(dataset, "OtherKeyExpression");
					labelWidget.bindObject(dataset, "OtherLabelExpression");
				} else {
					handleSelectSeries(seriesCombo.getSelectionIndex());
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

		valueWidget = new ExpressionWidget(yCompo, "Value");
		labelWidget = new ExpressionWidget(yCompo, "Label");

		return yCompo;
	}

	@Override
	protected Control createChartBottom(Composite parent) {
		Composite yCompo = new Composite(parent, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		yCompo.setLayout(new GridLayout(10, false));

		key = new ExpressionWidget(yCompo, "Key");

		return yCompo;
	}

	@Override
	protected Control createChartRight(Composite parent) {
		Composite yCompo = new Composite(parent, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		yCompo.setLayout(new GridLayout(2, false));

		new Label(yCompo, SWT.NONE).setText("Min slice percentage");
		minSlice = new Text(yCompo, SWT.BORDER);
		minSlice.addListener(SWT.Verify, new Listener() {

			public void handleEvent(Event e) {
				try {
					String number = e.text;
					String oldText = ((Text) e.widget).getText();
					if (e.start == 0)
						number = e.text + oldText;
					else
						number = oldText.substring(0, e.start) + e.text;
					if (oldText.length() - 1 > e.start + 1)
						number += oldText.substring(e.start + 1);

					if (number.equals("-")) //$NON-NLS-1$
						number = "-0";//$NON-NLS-1$
					if (number.equals(".")) //$NON-NLS-1$
						number = "0.";//$NON-NLS-1$

					e.doit = FloatValidator.getInstance().isValid(number);
				} catch (NumberFormatException ne) {
					e.doit = false;
				}
			}
		});
		minSlice.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				try {
					dataset.setMinPercentage(new Float(minSlice.getText()));
				} catch (NumberFormatException ne) {

				}
			}
		});

		new Label(yCompo, SWT.NONE).setText("Max slices to show");
		maxSlice = new Spinner(yCompo, SWT.BORDER);
		maxSlice.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				dataset.setMaxCount(maxSlice.getSelection());
			}
		});

		return yCompo;
	}

}
