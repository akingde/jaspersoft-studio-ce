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
package com.jaspersoft.studio.components.chart.property.section;

import java.util.SortedSet;

import net.sf.jasperreports.engine.base.JRBaseChartPlot;
import net.sf.jasperreports.engine.base.JRBaseChartPlot.JRBaseSeriesColor;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.components.chart.property.descriptor.seriescolor.dialog.SeriesColorEditor;
import com.jaspersoft.studio.components.chart.property.section.plot.APlot;
import com.jaspersoft.studio.components.chart.property.section.plot.PlotFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPColor;
import com.jaspersoft.studio.property.section.widgets.SPNumber;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ChartPlotSection extends AbstractSection {
	private CCombo modeType;
	private SPColor backButton;
	private Composite pcomposite;
	private SPNumber bgAlpha;
	private SPNumber fgAlpha;
	private Composite composite;

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
		this.pcomposite = parent;

		composite = createNewRow(parent);

		createLabel(composite, getWidgetFactory(), Messages.common_backcolor
				+ ":", 101); //$NON-NLS-1$

		backButton = new SPColor(
				composite,
				this,
				JRBaseChartPlot.PROPERTY_BACKCOLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_backcolor_description);

		getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_background_alpha_percent);

		bgAlpha = new SPNumber(
				composite,
				this,
				JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA,
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_background_alpha_percent_description);

		getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_foreground_alpha_percent);

		fgAlpha = new SPNumber(
				composite,
				this,
				JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA,
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_foreground_alpha_percent_description);

		composite = createNewRow(parent);

		createLabel(
				composite,
				getWidgetFactory(),
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_orientation,
				101);

		modeType = new CCombo(composite, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		modeType.setItems(new String[] {
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_horizontal,
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_vertical });
		modeType.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseChartPlot.PROPERTY_ORIENTATION,
						new Integer(modeType.getSelectionIndex()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		modeType.setToolTipText(Messages.ColorsSection_transparency_tool_tip);

		final Button colorSeries = new Button(composite, SWT.PUSH);
		colorSeries.setText("Series Colors");
		colorSeries.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				APropertyNode element = getElement();
				if (element != null) {
					SeriesColorEditor wizard = new SeriesColorEditor();
					wizard.setValue((SortedSet<JRBaseSeriesColor>) element
							.getPropertyValue(JRBaseChartPlot.PROPERTY_SERIES_COLORS));

					WizardDialog dialog = new WizardDialog(colorSeries
							.getShell(), wizard);
					dialog.create();
					if (dialog.open() == Dialog.OK) {
						changeProperty(JRBaseChartPlot.PROPERTY_SERIES_COLORS,
								wizard.getValue());
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private APlot plot;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			Integer b = (Integer) element
					.getPropertyValue(JRBaseChartPlot.PROPERTY_ORIENTATION);
			modeType.select(b);

			backButton.setData((RGB) element
					.getPropertyValue(JRBaseChartPlot.PROPERTY_BACKCOLOR));

			bgAlpha.setData((Float) element
					.getPropertyValue(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA));

			fgAlpha.setData((Float) element
					.getPropertyValue(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA));
			if (plot != null)
				plot.setData((MChartPlot) element);
		}
		isRefreshing = false;
	}

	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MChart) {
			MChartPlot chartplot = (MChartPlot) md
					.getPropertyValue(MChart.PLOTPROPERTY);
			if (plot == null)
				plot = PlotFactory.getPlot(chartplot, this.pcomposite, this);
			return chartplot;
		}
		return md;
	}

	@Override
	public boolean isDisposed() {
		return pcomposite.isDisposed();
	}
}
