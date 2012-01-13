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

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChart;

public class ChartTypeWizardPage extends WizardPage {
	private static final int GALLERY_HEIGHT = 100;
	private static final int GALLERY_WIDTH = 100;
	private MChart chart;
	private byte chartType = JRDesignChart.CHART_TYPE_LINE;
	private Scale zoomFactor;
	private Gallery chartsGallery;
	private GalleryItem itemGroup;

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

		zoomFactor = new Scale(composite, SWT.NONE);
		zoomFactor.setMinimum(1);
		zoomFactor.setMaximum(50);
		zoomFactor.setIncrement(1);
		zoomFactor.setPageIncrement(5);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 150;
		zoomFactor.setLayoutData(gd);

		chartsGallery = new Gallery(composite, SWT.VIRTUAL | SWT.V_SCROLL
				| SWT.BORDER);
		final NoGroupRenderer gr = new NoGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemSize(GALLERY_WIDTH, GALLERY_HEIGHT);
		gr.setAutoMargin(true);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		chartsGallery.setLayoutData(gd);
		chartsGallery.setGroupRenderer(gr);
		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		ir.setShowLabels(true);
		ir.setShowRoundedSelectionCorners(false);
		ir.setSelectionForegroundColor(getShell().getDisplay().getSystemColor(
				SWT.COLOR_BLUE));
		chartsGallery.setItemRenderer(ir);

		itemGroup = new GalleryItem(chartsGallery, SWT.NONE);

		fillTableb4j(chartsGallery, itemGroup);

		chartsGallery.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item instanceof GalleryItem) {
					chartType = (Byte) ((GalleryItem) e.item).getData();

					getContainer().updateButtons();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		setTableSelection();
		zoomFactor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double c = 1 + 0.1 * zoomFactor.getSelection();
				gr.setItemSize((int) (GALLERY_WIDTH * c),
						(int) (GALLERY_HEIGHT * c));
			}
		});

		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(getControl(), "Jaspersoft.wizard"); //$NON-NLS-1$
	}

	private void setTableSelection() {
		for (GalleryItem ti : itemGroup.getItems()) {
			if (((Byte) ti.getData()).intValue() == chartType) {
				chartsGallery.setSelection(new GalleryItem[] { ti });
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
		if (chartType == JRDesignChart.CHART_TYPE_MULTI_AXIS)
			return false;
		return super.canFlipToNextPage();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			chartsGallery.setFocus();
	}

	private void fillTableb4j(Gallery table, GalleryItem rootItem) {
		table.setRedraw(false);

		getTableItem(JRDesignChart.CHART_TYPE_AREA, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_STACKEDAREA, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_XYAREA, rootItem);

		getTableItem(JRDesignChart.CHART_TYPE_BAR, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_BAR3D, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_XYBAR, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_STACKEDBAR, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_STACKEDBAR3D, rootItem);

		getTableItem(JRDesignChart.CHART_TYPE_LINE, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_XYLINE, rootItem);

		getTableItem(JRDesignChart.CHART_TYPE_PIE, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_PIE3D, rootItem);

		getTableItem(JRDesignChart.CHART_TYPE_BUBBLE, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_CANDLESTICK, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_TIMESERIES, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_HIGHLOW, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_SCATTER, rootItem);

		getTableItem(JRDesignChart.CHART_TYPE_THERMOMETER, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_METER, rootItem);

		getTableItem(JRDesignChart.CHART_TYPE_GANTT, rootItem);
		getTableItem(JRDesignChart.CHART_TYPE_MULTI_AXIS, rootItem);

		table.setRedraw(true);
	}

	public static GalleryItem getTableItem(byte chartype, GalleryItem gr) {
		switch (chartype) {
		case JRDesignChart.CHART_TYPE_AREA:
			GalleryItem ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_area_chart);
			ti.setImage(Activator.getImage("/icons/area.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_AREA);
			return ti;

		case JRDesignChart.CHART_TYPE_BAR:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_bar_chart);
			ti.setImage(Activator.getImage("/icons/bar.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BAR);
			return ti;
		case JRDesignChart.CHART_TYPE_BAR3D:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_bar3d_chart);
			ti.setImage(Activator.getImage("/icons/bar3d.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BAR3D);
			return ti;
		case JRDesignChart.CHART_TYPE_BUBBLE:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_bubble_chart);
			ti.setImage(Activator.getImage("/icons/bubble.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BUBBLE);
			return ti;
		case JRDesignChart.CHART_TYPE_CANDLESTICK:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_candlestick_chart);
			ti.setImage(Activator.getImage("/icons/candlestick.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_CANDLESTICK);
			return ti;
		case JRDesignChart.CHART_TYPE_GANTT:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_gantt);
			ti.setImage(Activator.getImage("/icons/gantt.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_GANTT);
			return ti;
		case JRDesignChart.CHART_TYPE_HIGHLOW:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_highlow_chart);
			ti.setImage(Activator.getImage("/icons/highlow.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_HIGHLOW);
			return ti;
		case JRDesignChart.CHART_TYPE_LINE:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_line_chart);
			ti.setImage(Activator.getImage("/icons/line.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_LINE);
			return ti;
		case JRDesignChart.CHART_TYPE_METER:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_meter_chart);
			ti.setImage(Activator.getImage("/icons/meter.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_METER);
			return ti;
		case JRDesignChart.CHART_TYPE_MULTI_AXIS:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_multiaxes_chart);
			ti.setImage(Activator.getImage("/icons/multiaxis.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_MULTI_AXIS);
			return ti;
		case JRDesignChart.CHART_TYPE_PIE:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_pie_chart);
			ti.setImage(Activator.getImage("/icons/pie.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_PIE);
			return ti;
		case JRDesignChart.CHART_TYPE_PIE3D:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_pie3d_chart);
			ti.setImage(Activator.getImage("/icons/pie3d.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_PIE3D);
			return ti;
		case JRDesignChart.CHART_TYPE_SCATTER:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_scatter_chart);
			ti.setImage(Activator.getImage("/icons/scatter.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_SCATTER);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDAREA:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_stacked_area);
			ti.setImage(Activator.getImage("/icons/stackedarea.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDAREA);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDBAR:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_stacked_bar);
			ti.setImage(Activator.getImage("/icons/stackedbar.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDBAR);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDBAR3D:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_stacked_bar3D);
			ti.setImage(Activator.getImage("/icons/stackedbar3d.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDBAR3D);
			return ti;
		case JRDesignChart.CHART_TYPE_THERMOMETER:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_thermometer_chart);
			ti.setImage(Activator.getImage("/icons/thermometer.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_THERMOMETER);
			return ti;
		case JRDesignChart.CHART_TYPE_TIMESERIES:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_timeseries_chart);
			ti.setImage(Activator.getImage("/icons/timeseries.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_TIMESERIES);
			return ti;
		case JRDesignChart.CHART_TYPE_XYAREA:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_xy_area);
			ti.setImage(Activator.getImage("/icons/xyarea.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYAREA);
			return ti;
		case JRDesignChart.CHART_TYPE_XYBAR:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_xy_bar);
			ti.setImage(Activator.getImage("/icons/xybar.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYBAR);
			return ti;
		case JRDesignChart.CHART_TYPE_XYLINE:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_xy_line);
			ti.setImage(Activator.getImage("/icons/xyline.png")); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYLINE);
			return ti;
		}
		return null;
	}
}
