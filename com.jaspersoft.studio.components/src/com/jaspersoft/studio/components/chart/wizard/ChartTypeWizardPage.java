/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.nebula.widgets.gallery.RoundedGalleryItemRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Scale;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSWizardPage;

import net.sf.jasperreports.charts.JRCategorySeries;
import net.sf.jasperreports.charts.JRGanttSeries;
import net.sf.jasperreports.charts.JRTimeSeries;
import net.sf.jasperreports.charts.JRXySeries;
import net.sf.jasperreports.charts.JRXyzSeries;
import net.sf.jasperreports.charts.design.JRDesignAreaPlot;
import net.sf.jasperreports.charts.design.JRDesignBar3DPlot;
import net.sf.jasperreports.charts.design.JRDesignBarPlot;
import net.sf.jasperreports.charts.design.JRDesignBubblePlot;
import net.sf.jasperreports.charts.design.JRDesignCandlestickPlot;
import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
import net.sf.jasperreports.charts.design.JRDesignGanttDataset;
import net.sf.jasperreports.charts.design.JRDesignGanttSeries;
import net.sf.jasperreports.charts.design.JRDesignHighLowDataset;
import net.sf.jasperreports.charts.design.JRDesignHighLowPlot;
import net.sf.jasperreports.charts.design.JRDesignLinePlot;
import net.sf.jasperreports.charts.design.JRDesignMeterPlot;
import net.sf.jasperreports.charts.design.JRDesignMultiAxisPlot;
import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;
import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.charts.design.JRDesignPiePlot;
import net.sf.jasperreports.charts.design.JRDesignScatterPlot;
import net.sf.jasperreports.charts.design.JRDesignThermometerPlot;
import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesPlot;
import net.sf.jasperreports.charts.design.JRDesignValueDataset;
import net.sf.jasperreports.charts.design.JRDesignXyDataset;
import net.sf.jasperreports.charts.design.JRDesignXySeries;
import net.sf.jasperreports.charts.design.JRDesignXyzDataset;
import net.sf.jasperreports.charts.design.JRDesignXyzSeries;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.design.JRDesignChart;

public class ChartTypeWizardPage extends JSSWizardPage {
	private static final int GALLERY_HEIGHT = 100;
	private static final int GALLERY_WIDTH = 100;
	private MChart chart;
	private byte chartType = JRDesignChart.CHART_TYPE_LINE;
	private Scale zoomFactor;
	private Gallery chartsGallery;
	private GalleryItem itemGroup;
	private static Map<String, Image> standardImages = new HashMap<String, Image>();
	private static Map<String, Image> selectedImages = new HashMap<String, Image>();

	protected ChartTypeWizardPage(MChart chart) {
		super("chartwizard"); //$NON-NLS-1$
		setTitle(Messages.common_chart_wizard);
		setDescription(Messages.ChartWizardPage_chart_wizard_description);
		this.chart = chart;
		this.chartType = ((JRDesignChart) chart.getValue()).getChartType();
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_CHART_TYPE;
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

		chartsGallery = new Gallery(composite, SWT.VIRTUAL | SWT.V_SCROLL | SWT.BORDER);
		final NoGroupRenderer gr = new NoGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemSize(GALLERY_WIDTH, GALLERY_HEIGHT);
		gr.setAutoMargin(true);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		chartsGallery.setLayoutData(gd);
		chartsGallery.setGroupRenderer(gr);
		RoundedGalleryItemRenderer ir = new RoundedGalleryItemRenderer();
		ir.setShowLabels(true);
		chartsGallery.setItemRenderer(ir);

		itemGroup = new GalleryItem(chartsGallery, SWT.NONE);

		fillTableb4j(chartsGallery, itemGroup);

		chartsGallery.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.item instanceof GalleryItem) {
					chartType = (Byte) ((GalleryItem) e.item).getData();
					if (chartType == JRDesignChart.CHART_TYPE_MULTI_AXIS) {
						if (getWizard() instanceof ChartWizard)
							((ChartWizard) getWizard()).fixLastPage(true);
					} else if (getWizard() instanceof ChartWizard)
						((ChartWizard) getWizard()).fixLastPage(false);
					setPageComplete(true);
				} else
					setPageComplete(false);
			}

		});

		setTableSelection();
		zoomFactor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double c = 1 + 0.1 * zoomFactor.getSelection();
				gr.setItemSize((int) (GALLERY_WIDTH * c), (int) (GALLERY_HEIGHT * c));
			}
		});
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
		if (finishPage()) {
			lastPageShown = true;
			return super.getNextPage();
		}
		return this;
	}

	private boolean lastPageShown = false;

	public void setLastPageShown(boolean lastPageShown) {
		this.lastPageShown = lastPageShown;
	}

	public boolean finishPage() {
		JRDesignChart oldChart = (JRDesignChart) chart.getValue();
		if (chartType != oldChart.getChartType()) {
			if ((lastPageShown)) {
				if (UIUtils.showConfirmation(Messages.ChartTypeWizardPage_0, Messages.ChartTypeWizardPage_1)) {
					setChartType(oldChart, chartType);
				} else
					return false;
			} else
				setChartType(oldChart, chartType);
			// MChart.setupChart(oldChart);
		}
		return true;
	}

	public void setChartType(JRDesignChart chart, byte chartType) {
		try {
			byte old = chart.getChartType();

			JRChartDataset ds = chart.getDataset();
			JRChartPlot plot = chart.getPlot();
			switch (chartType) {
			case JRChart.CHART_TYPE_AREA:
				if (!(ds instanceof JRDesignCategoryDataset))
					chart.setDataset(merge2CategoryDataset(ds));
				if (!(plot instanceof JRDesignAreaPlot))
					setPlot(merge2AreaPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_BAR:
				if (!(ds instanceof JRDesignCategoryDataset))
					chart.setDataset(merge2CategoryDataset(ds));
				if (!(plot instanceof JRDesignBarPlot))
					setPlot(merge2BarPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_BAR3D:
				if (!(ds instanceof JRDesignCategoryDataset))
					chart.setDataset(merge2CategoryDataset(ds));
				if (!(plot instanceof JRDesignBar3DPlot))
					setPlot(merge2Bar3DPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_BUBBLE:
				if (!(ds instanceof JRDesignXyzDataset))
					chart.setDataset(merge2XyzDataset(ds));
				if (!(plot instanceof JRDesignBubblePlot))
					setPlot(merge2BubblePlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_CANDLESTICK:
				if (!(ds instanceof JRDesignHighLowDataset))
					chart.setDataset(new JRDesignHighLowDataset(ds));
				if (!(plot instanceof JRDesignCandlestickPlot))
					setPlot(merge2CandleStickPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_HIGHLOW:
				if (!(ds instanceof JRDesignHighLowDataset))
					chart.setDataset(new JRDesignHighLowDataset(ds));
				if (!(plot instanceof JRDesignHighLowPlot))
					setPlot(merge2HighLowPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_LINE:
				if (!(ds instanceof JRDesignCategoryDataset))
					chart.setDataset(merge2CategoryDataset(ds));
				if (!(plot instanceof JRDesignLinePlot))
					setPlot(merge2LinePlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_METER:
				if (!(ds instanceof JRDesignValueDataset))
					chart.setDataset(new JRDesignValueDataset(ds));
				if (!(plot instanceof JRDesignMeterPlot))
					setPlot(merge2MeterPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_MULTI_AXIS:
				chart.setDataset(null);
				setPlot(new JRDesignMultiAxisPlot(chart.getPlot(), chart), chart);
				break;
			case JRChart.CHART_TYPE_PIE:
				if (!(ds instanceof JRDesignPieDataset))
					chart.setDataset(new JRDesignPieDataset(ds));
				if (!(plot instanceof JRDesignPiePlot))
					setPlot(merge2PiePlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_PIE3D:
				if (!(ds instanceof JRDesignPieDataset))
					chart.setDataset(new JRDesignPieDataset(ds));
				if (!(plot instanceof JRDesignPie3DPlot))
					setPlot(merge2Pie3DPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_SCATTER:
				if (!(ds instanceof JRDesignXyDataset))
					chart.setDataset(merge2XyDataset(ds));
				if (!(plot instanceof JRDesignScatterPlot))
					setPlot(merge2ScatterPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_STACKEDBAR:
				if (!(ds instanceof JRDesignCategoryDataset))
					chart.setDataset(merge2CategoryDataset(ds));
				if (!(plot instanceof JRDesignBarPlot))
					setPlot(merge2BarPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_STACKEDBAR3D:
				if (!(ds instanceof JRDesignCategoryDataset))
					chart.setDataset(merge2CategoryDataset(ds));
				if (!(plot instanceof JRDesignBar3DPlot))
					setPlot(merge2Bar3DPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_THERMOMETER:
				if (!(ds instanceof JRDesignValueDataset))
					chart.setDataset(new JRDesignValueDataset(ds));
				if (!(plot instanceof JRDesignThermometerPlot))
					setPlot(merge2ThermometerPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_TIMESERIES:
				if (!(ds instanceof JRDesignTimeSeriesDataset))
					chart.setDataset(merge2TimeSeriesDataset(ds));
				// other datasets could be supported
				if (!(plot instanceof JRDesignTimeSeriesPlot))
					setPlot(merge2TimeSeriesPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_XYAREA:
				if (!(ds instanceof JRDesignXyDataset))
					chart.setDataset(merge2XyDataset(ds));
				if (!(plot instanceof JRDesignAreaPlot))
					setPlot(merge2AreaPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_XYBAR:
				if (!(ds instanceof JRDesignXyDataset))
					chart.setDataset(merge2XyDataset(ds));
				if (!(plot instanceof JRDesignBarPlot))
					setPlot(merge2BarPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_XYLINE:
				if (!(ds instanceof JRDesignXyDataset))
					chart.setDataset(merge2XyDataset(ds));
				if (!(plot instanceof JRDesignLinePlot))
					setPlot(merge2LinePlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_STACKEDAREA:
				if (!(ds instanceof JRDesignCategoryDataset))
					chart.setDataset(merge2CategoryDataset(ds));
				if (!(plot instanceof JRDesignAreaPlot))
					setPlot(merge2AreaPlot(chart, plot), chart);
				break;
			case JRChart.CHART_TYPE_GANTT:
				if (!(ds instanceof JRDesignGanttDataset))
					chart.setDataset(merge2GanttDataset(ds));
				if (!(plot instanceof JRDesignBarPlot))
					setPlot(merge2BarPlot(chart, plot), chart);
				break;
			default:
				throw new JRRuntimeException(JRDesignChart.EXCEPTION_MESSAGE_KEY_UNSUPPORTED_CHART_TYPE,
						(Object[]) null);
			}

			setChartTypeByte(chart, chartType);
			chart.getEventSupport().firePropertyChange(JRDesignChart.PROPERTY_CHART_TYPE, old, chartType);
		} catch (SecurityException e) {
			e.printStackTrace();
			chart.setChartType(chartType);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			chart.setChartType(chartType);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			chart.setChartType(chartType);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			chart.setChartType(chartType);
		} catch (JRException e) {
			e.printStackTrace();
			chart.setChartType(chartType);
		}
	}

	private void setChartTypeByte(JRDesignChart chart, byte type)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field f = chart.getClass().getDeclaredField("chartType"); //$NON-NLS-1$
		f.setAccessible(true);
		f.setByte(chart, type);
	}

	private void setPlot(JRChartPlot plot, JRDesignChart chart)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field f = chart.getClass().getDeclaredField("plot"); //$NON-NLS-1$
		f.setAccessible(true);
		f.set(chart, plot);
		// need to call the setup to initialize correctly the new plot
		MChart.setupPlot(chart);
	}

	protected JRDesignMeterPlot merge2MeterPlot(JRDesignChart chart, JRChartPlot plot) throws JRException {
		JRDesignMeterPlot np = new JRDesignMeterPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignThermometerPlot) {
			np.setDataRange(((JRDesignThermometerPlot) plot).getDataRange());
			np.setValueDisplay(((JRDesignThermometerPlot) plot).getValueDisplay());
		}
		return np;
	}

	protected JRDesignThermometerPlot merge2ThermometerPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignThermometerPlot np = new JRDesignThermometerPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignMeterPlot) {
			np.setDataRange(((JRDesignMeterPlot) plot).getDataRange());
			np.setValueDisplay(((JRDesignMeterPlot) plot).getValueDisplay());
		}
		return np;
	}

	protected JRDesignPiePlot merge2PiePlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignPiePlot np = new JRDesignPiePlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignPie3DPlot) {
			np.setCircular(((JRDesignPie3DPlot) plot).getCircular());
			np.setLabelFormat(((JRDesignPie3DPlot) plot).getLabelFormat());
			np.setLegendLabelFormat(((JRDesignPie3DPlot) plot).getLegendLabelFormat());
			np.setItemLabel(((JRDesignPie3DPlot) plot).getItemLabel());
			np.setShowLabels(((JRDesignPie3DPlot) plot).getShowLabels());
		}
		return np;
	}

	protected JRDesignPie3DPlot merge2Pie3DPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignPie3DPlot np = new JRDesignPie3DPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignPiePlot) {
			np.setCircular(((JRDesignPiePlot) plot).getCircular());
			np.setLabelFormat(((JRDesignPiePlot) plot).getLabelFormat());
			np.setLegendLabelFormat(((JRDesignPiePlot) plot).getLegendLabelFormat());
			np.setItemLabel(((JRDesignPiePlot) plot).getItemLabel());
			np.setShowLabels(((JRDesignPiePlot) plot).getShowLabels());
		}
		return np;
	}

	protected JRDesignScatterPlot merge2ScatterPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignScatterPlot np = new JRDesignScatterPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBarPlot) {
			np.setXAxisLabelColor(((JRDesignBarPlot) plot).getCategoryAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignBarPlot) plot).getCategoryAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignBarPlot) plot).getCategoryAxisLabelFont());
			np.setXAxisLineColor(((JRDesignBarPlot) plot).getCategoryAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignBarPlot) plot).getCategoryAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignBarPlot) plot).getCategoryAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignBarPlot) plot).getCategoryAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignBarPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBarPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBarPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBarPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBarPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignBarPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignBarPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignBarPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignBarPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignBarPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignBarPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignBarPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignBarPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBar3DPlot) {
			np.setXAxisLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignBar3DPlot) plot).getCategoryAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisLabelFont());
			np.setXAxisLineColor(((JRDesignBar3DPlot) plot).getCategoryAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignBar3DPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignBar3DPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignBar3DPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignBar3DPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignBar3DPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignBar3DPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignBar3DPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignAreaPlot) {
			np.setXAxisLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignAreaPlot) plot).getCategoryAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisLabelFont());
			np.setXAxisLineColor(((JRDesignAreaPlot) plot).getCategoryAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignAreaPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignAreaPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignAreaPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignAreaPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignAreaPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignAreaPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignAreaPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignLinePlot) {
			np.setXAxisLabelColor(((JRDesignLinePlot) plot).getCategoryAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignLinePlot) plot).getCategoryAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignLinePlot) plot).getCategoryAxisLabelFont());
			np.setXAxisLineColor(((JRDesignLinePlot) plot).getCategoryAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignLinePlot) plot).getCategoryAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignLinePlot) plot).getCategoryAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignLinePlot) plot).getCategoryAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignLinePlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignLinePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignLinePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignLinePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignLinePlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignLinePlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignLinePlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignLinePlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignLinePlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignLinePlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignLinePlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignLinePlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignLinePlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignCandlestickPlot) {
			np.setXAxisLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignCandlestickPlot) plot).getTimeAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisLabelFont());
			np.setXAxisLineColor(((JRDesignCandlestickPlot) plot).getTimeAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignCandlestickPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignCandlestickPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignTimeSeriesPlot) {
			np.setXAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelFont());
			np.setXAxisLineColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignHighLowPlot) {
			np.setXAxisLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignHighLowPlot) plot).getTimeAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisLabelFont());
			np.setXAxisLineColor(((JRDesignHighLowPlot) plot).getTimeAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignHighLowPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignHighLowPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignHighLowPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignHighLowPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignHighLowPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignHighLowPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignHighLowPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBubblePlot) {
			np.setXAxisLabelColor(((JRDesignBubblePlot) plot).getXAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignBubblePlot) plot).getXAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignBubblePlot) plot).getXAxisLabelFont());
			np.setXAxisLineColor(((JRDesignBubblePlot) plot).getXAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignBubblePlot) plot).getXAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignBubblePlot) plot).getXAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignBubblePlot) plot).getXAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignBubblePlot) plot).getYAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignBubblePlot) plot).getYAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignBubblePlot) plot).getYAxisLabelFont());
			np.setYAxisLineColor(((JRDesignBubblePlot) plot).getYAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignBubblePlot) plot).getYAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignBubblePlot) plot).getYAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignBubblePlot) plot).getYAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignBubblePlot merge2BubblePlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignBubblePlot np = new JRDesignBubblePlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBarPlot) {
			np.setXAxisLabelColor(((JRDesignBarPlot) plot).getCategoryAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignBarPlot) plot).getCategoryAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignBarPlot) plot).getCategoryAxisLabelFont());
			np.setXAxisLineColor(((JRDesignBarPlot) plot).getCategoryAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignBarPlot) plot).getCategoryAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignBarPlot) plot).getCategoryAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignBarPlot) plot).getCategoryAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignBarPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBarPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBarPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBarPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBarPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignBarPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignBarPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignBarPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignBarPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignBarPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignBarPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignBarPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignBarPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBar3DPlot) {
			np.setXAxisLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignBar3DPlot) plot).getCategoryAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisLabelFont());
			np.setXAxisLineColor(((JRDesignBar3DPlot) plot).getCategoryAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignBar3DPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignBar3DPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignBar3DPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignBar3DPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignBar3DPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignBar3DPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignBar3DPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignAreaPlot) {
			np.setXAxisLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignAreaPlot) plot).getCategoryAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisLabelFont());
			np.setXAxisLineColor(((JRDesignAreaPlot) plot).getCategoryAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignAreaPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignAreaPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignAreaPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignAreaPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignAreaPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignAreaPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignAreaPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignLinePlot) {
			np.setXAxisLabelColor(((JRDesignLinePlot) plot).getCategoryAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignLinePlot) plot).getCategoryAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignLinePlot) plot).getCategoryAxisLabelFont());
			np.setXAxisLineColor(((JRDesignLinePlot) plot).getCategoryAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignLinePlot) plot).getCategoryAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignLinePlot) plot).getCategoryAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignLinePlot) plot).getCategoryAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignLinePlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignLinePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignLinePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignLinePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignLinePlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignLinePlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignLinePlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignLinePlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignLinePlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignLinePlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignLinePlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignLinePlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignLinePlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignCandlestickPlot) {
			np.setXAxisLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignCandlestickPlot) plot).getTimeAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisLabelFont());
			np.setXAxisLineColor(((JRDesignCandlestickPlot) plot).getTimeAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignCandlestickPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignCandlestickPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignTimeSeriesPlot) {
			np.setXAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelFont());
			np.setXAxisLineColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignHighLowPlot) {
			np.setXAxisLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignHighLowPlot) plot).getTimeAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisLabelFont());
			np.setXAxisLineColor(((JRDesignHighLowPlot) plot).getTimeAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignHighLowPlot) plot).getValueAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignHighLowPlot) plot).getValueAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignHighLowPlot) plot).getValueAxisLabelFont());
			np.setYAxisLineColor(((JRDesignHighLowPlot) plot).getValueAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignHighLowPlot) plot).getValueAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignHighLowPlot) plot).getValueAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignHighLowPlot) plot).getValueAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignScatterPlot) {
			np.setXAxisLabelColor(((JRDesignScatterPlot) plot).getXAxisLabelColor());
			np.setXAxisLabelExpression(((JRDesignScatterPlot) plot).getXAxisLabelExpression());
			np.setXAxisLabelFont(((JRDesignScatterPlot) plot).getXAxisLabelFont());
			np.setXAxisLineColor(((JRDesignScatterPlot) plot).getXAxisLineColor());
			np.setXAxisTickLabelColor(((JRDesignScatterPlot) plot).getXAxisTickLabelColor());
			np.setXAxisTickLabelFont(((JRDesignScatterPlot) plot).getXAxisTickLabelFont());
			np.setXAxisTickLabelMask(((JRDesignScatterPlot) plot).getXAxisTickLabelMask());
			np.setXAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMinValueExpression());
			np.setYAxisLabelColor(((JRDesignScatterPlot) plot).getYAxisLabelColor());
			np.setYAxisLabelExpression(((JRDesignScatterPlot) plot).getYAxisLabelExpression());
			np.setYAxisLabelFont(((JRDesignScatterPlot) plot).getYAxisLabelFont());
			np.setYAxisLineColor(((JRDesignScatterPlot) plot).getYAxisLineColor());
			np.setYAxisTickLabelColor(((JRDesignScatterPlot) plot).getYAxisTickLabelColor());
			np.setYAxisTickLabelFont(((JRDesignScatterPlot) plot).getYAxisTickLabelFont());
			np.setYAxisTickLabelMask(((JRDesignScatterPlot) plot).getYAxisTickLabelMask());
			np.setYAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignHighLowPlot merge2HighLowPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignHighLowPlot np = new JRDesignHighLowPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBarPlot) {
			np.setTimeAxisLabelColor(((JRDesignBarPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBarPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBarPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBarPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBarPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBarPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBarPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBarPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBarPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBarPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBarPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBarPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBarPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBarPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBarPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBarPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBarPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBarPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBarPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBarPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBar3DPlot) {
			np.setTimeAxisLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBar3DPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBar3DPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBar3DPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBar3DPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBar3DPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBar3DPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBar3DPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBar3DPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBar3DPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignAreaPlot) {
			np.setTimeAxisLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignAreaPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignAreaPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignAreaPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignAreaPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignAreaPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignAreaPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignAreaPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignAreaPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignAreaPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignLinePlot) {
			np.setTimeAxisLabelColor(((JRDesignLinePlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignLinePlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignLinePlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignLinePlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignLinePlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignLinePlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignLinePlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignLinePlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignLinePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignLinePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignLinePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignLinePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignLinePlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignLinePlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignLinePlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignLinePlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignLinePlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignLinePlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignLinePlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignLinePlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignCandlestickPlot) {
			np.setTimeAxisLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignCandlestickPlot) plot).getTimeAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignCandlestickPlot) plot).getTimeAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignCandlestickPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignCandlestickPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignTimeSeriesPlot) {
			np.setTimeAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBubblePlot) {
			np.setTimeAxisLabelColor(((JRDesignBubblePlot) plot).getXAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBubblePlot) plot).getXAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBubblePlot) plot).getXAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBubblePlot) plot).getXAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBubblePlot) plot).getXAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBubblePlot) plot).getXAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBubblePlot) plot).getXAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBubblePlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBubblePlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBubblePlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBubblePlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBubblePlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBubblePlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBubblePlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getYAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignScatterPlot) {
			np.setTimeAxisLabelColor(((JRDesignScatterPlot) plot).getXAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignScatterPlot) plot).getXAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignScatterPlot) plot).getXAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignScatterPlot) plot).getXAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignScatterPlot) plot).getXAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignScatterPlot) plot).getXAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignScatterPlot) plot).getXAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignScatterPlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignScatterPlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignScatterPlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignScatterPlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignScatterPlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignScatterPlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignScatterPlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignTimeSeriesPlot merge2TimeSeriesPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignTimeSeriesPlot np = new JRDesignTimeSeriesPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBarPlot) {
			np.setTimeAxisLabelColor(((JRDesignBarPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBarPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBarPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBarPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBarPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBarPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBarPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBarPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBarPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBarPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBarPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBarPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBarPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBarPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBarPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBarPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBarPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBarPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBarPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBarPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBar3DPlot) {
			np.setTimeAxisLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBar3DPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBar3DPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBar3DPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBar3DPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBar3DPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBar3DPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBar3DPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBar3DPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBar3DPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignAreaPlot) {
			np.setTimeAxisLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignAreaPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignAreaPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignAreaPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignAreaPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignAreaPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignAreaPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignAreaPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignAreaPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignAreaPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignLinePlot) {
			np.setTimeAxisLabelColor(((JRDesignLinePlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignLinePlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignLinePlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignLinePlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignLinePlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignLinePlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignLinePlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignLinePlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignLinePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignLinePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignLinePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignLinePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignLinePlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignLinePlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignLinePlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignLinePlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignLinePlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignLinePlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignLinePlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignLinePlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignCandlestickPlot) {
			np.setTimeAxisLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignCandlestickPlot) plot).getTimeAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignCandlestickPlot) plot).getTimeAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignCandlestickPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignCandlestickPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignHighLowPlot) {
			np.setTimeAxisLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignHighLowPlot) plot).getTimeAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignHighLowPlot) plot).getTimeAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignHighLowPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignHighLowPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignHighLowPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignHighLowPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignHighLowPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignHighLowPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignHighLowPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBubblePlot) {
			np.setTimeAxisLabelColor(((JRDesignBubblePlot) plot).getXAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBubblePlot) plot).getXAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBubblePlot) plot).getXAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBubblePlot) plot).getXAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBubblePlot) plot).getXAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBubblePlot) plot).getXAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBubblePlot) plot).getXAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBubblePlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBubblePlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBubblePlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBubblePlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBubblePlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBubblePlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBubblePlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getYAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignScatterPlot) {
			np.setTimeAxisLabelColor(((JRDesignScatterPlot) plot).getXAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignScatterPlot) plot).getXAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignScatterPlot) plot).getXAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignScatterPlot) plot).getXAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignScatterPlot) plot).getXAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignScatterPlot) plot).getXAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignScatterPlot) plot).getXAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignScatterPlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignScatterPlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignScatterPlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignScatterPlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignScatterPlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignScatterPlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignScatterPlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignCandlestickPlot merge2CandleStickPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignCandlestickPlot np = new JRDesignCandlestickPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBarPlot) {
			np.setTimeAxisLabelColor(((JRDesignBarPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBarPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBarPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBarPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBarPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBarPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBarPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBarPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBarPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBarPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBarPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBarPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBarPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBarPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBarPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBarPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBarPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBarPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBarPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBarPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBar3DPlot) {
			np.setTimeAxisLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBar3DPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBar3DPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBar3DPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBar3DPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBar3DPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBar3DPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBar3DPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBar3DPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBar3DPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignAreaPlot) {
			np.setTimeAxisLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignAreaPlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignAreaPlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignAreaPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignAreaPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignAreaPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignAreaPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignAreaPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignAreaPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignAreaPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignLinePlot) {
			np.setTimeAxisLabelColor(((JRDesignLinePlot) plot).getCategoryAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignLinePlot) plot).getCategoryAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignLinePlot) plot).getCategoryAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignLinePlot) plot).getCategoryAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignLinePlot) plot).getCategoryAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignLinePlot) plot).getCategoryAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignLinePlot) plot).getCategoryAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignLinePlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignLinePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignLinePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignLinePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignLinePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignLinePlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignLinePlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignLinePlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignLinePlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignLinePlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignLinePlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignLinePlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignLinePlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignTimeSeriesPlot) {
			np.setTimeAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignHighLowPlot) {
			np.setTimeAxisLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignHighLowPlot) plot).getTimeAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignHighLowPlot) plot).getTimeAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignHighLowPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignHighLowPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignHighLowPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignHighLowPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignHighLowPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignHighLowPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignHighLowPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBubblePlot) {
			np.setTimeAxisLabelColor(((JRDesignBubblePlot) plot).getXAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignBubblePlot) plot).getXAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignBubblePlot) plot).getXAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignBubblePlot) plot).getXAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignBubblePlot) plot).getXAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignBubblePlot) plot).getXAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignBubblePlot) plot).getXAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBubblePlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBubblePlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBubblePlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBubblePlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBubblePlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBubblePlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBubblePlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getYAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignScatterPlot) {
			np.setTimeAxisLabelColor(((JRDesignScatterPlot) plot).getXAxisLabelColor());
			np.setTimeAxisLabelExpression(((JRDesignScatterPlot) plot).getXAxisLabelExpression());
			np.setTimeAxisLabelFont(((JRDesignScatterPlot) plot).getXAxisLabelFont());
			np.setTimeAxisLineColor(((JRDesignScatterPlot) plot).getXAxisLineColor());
			np.setTimeAxisTickLabelColor(((JRDesignScatterPlot) plot).getXAxisTickLabelColor());
			np.setTimeAxisTickLabelFont(((JRDesignScatterPlot) plot).getXAxisTickLabelFont());
			np.setTimeAxisTickLabelMask(((JRDesignScatterPlot) plot).getXAxisTickLabelMask());
			np.setTimeAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignScatterPlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignScatterPlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignScatterPlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignScatterPlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignScatterPlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignScatterPlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignScatterPlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignAreaPlot merge2AreaPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignAreaPlot np = new JRDesignAreaPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBarPlot) {
			np.setCategoryAxisLabelColor(((JRDesignBarPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBarPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBarPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBarPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBarPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBarPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBarPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignBarPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBarPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBarPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBarPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBarPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBarPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBarPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBarPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBarPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBarPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBarPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBarPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBarPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBarPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBar3DPlot) {
			np.setCategoryAxisLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBar3DPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBar3DPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBar3DPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBar3DPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBar3DPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBar3DPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBar3DPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBar3DPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBar3DPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignLinePlot) {
			np.setCategoryAxisLabelColor(((JRDesignLinePlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignLinePlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignLinePlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignLinePlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignLinePlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignLinePlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignLinePlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignLinePlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignLinePlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignLinePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignLinePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignLinePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignLinePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignLinePlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignLinePlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignLinePlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignLinePlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignLinePlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignLinePlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignLinePlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignLinePlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignCandlestickPlot) {
			np.setCategoryAxisLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignCandlestickPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignCandlestickPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignCandlestickPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignCandlestickPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignTimeSeriesPlot) {
			np.setCategoryAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignHighLowPlot) {
			np.setCategoryAxisLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignHighLowPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignHighLowPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignHighLowPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignHighLowPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignHighLowPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignHighLowPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignHighLowPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignHighLowPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignHighLowPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBubblePlot) {
			np.setCategoryAxisLabelColor(((JRDesignBubblePlot) plot).getXAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBubblePlot) plot).getXAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBubblePlot) plot).getXAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBubblePlot) plot).getXAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBubblePlot) plot).getXAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBubblePlot) plot).getXAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBubblePlot) plot).getXAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBubblePlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBubblePlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBubblePlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBubblePlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBubblePlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBubblePlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBubblePlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getYAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignScatterPlot) {
			np.setCategoryAxisLabelColor(((JRDesignScatterPlot) plot).getXAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignScatterPlot) plot).getXAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignScatterPlot) plot).getXAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignScatterPlot) plot).getXAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignScatterPlot) plot).getXAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignScatterPlot) plot).getXAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignScatterPlot) plot).getXAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignScatterPlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignScatterPlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignScatterPlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignScatterPlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignScatterPlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignScatterPlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignScatterPlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignLinePlot merge2LinePlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignLinePlot np = new JRDesignLinePlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBarPlot) {
			np.setCategoryAxisLabelColor(((JRDesignBarPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBarPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBarPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBarPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBarPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBarPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBarPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignBarPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBarPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBarPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBarPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBarPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBarPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBarPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBarPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBarPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBarPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBarPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBarPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBarPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBarPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBar3DPlot) {
			np.setCategoryAxisLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBar3DPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBar3DPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBar3DPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBar3DPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBar3DPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBar3DPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBar3DPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBar3DPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBar3DPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignAreaPlot) {
			np.setCategoryAxisLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignAreaPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignAreaPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignAreaPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignAreaPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignAreaPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignAreaPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignAreaPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignAreaPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignAreaPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignCandlestickPlot) {
			np.setCategoryAxisLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignCandlestickPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignCandlestickPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignCandlestickPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignCandlestickPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignTimeSeriesPlot) {
			np.setCategoryAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignHighLowPlot) {
			np.setCategoryAxisLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignHighLowPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignHighLowPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignHighLowPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignHighLowPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignHighLowPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignHighLowPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignHighLowPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignHighLowPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignHighLowPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBubblePlot) {
			np.setCategoryAxisLabelColor(((JRDesignBubblePlot) plot).getXAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBubblePlot) plot).getXAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBubblePlot) plot).getXAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBubblePlot) plot).getXAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBubblePlot) plot).getXAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBubblePlot) plot).getXAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBubblePlot) plot).getXAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBubblePlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBubblePlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBubblePlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBubblePlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBubblePlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBubblePlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBubblePlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getYAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignScatterPlot) {
			np.setCategoryAxisLabelColor(((JRDesignScatterPlot) plot).getXAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignScatterPlot) plot).getXAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignScatterPlot) plot).getXAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignScatterPlot) plot).getXAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignScatterPlot) plot).getXAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignScatterPlot) plot).getXAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignScatterPlot) plot).getXAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignScatterPlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignScatterPlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignScatterPlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignScatterPlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignScatterPlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignScatterPlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignScatterPlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignBar3DPlot merge2Bar3DPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignBar3DPlot np = new JRDesignBar3DPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBarPlot) {
			np.setCategoryAxisLabelColor(((JRDesignBarPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBarPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBarPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBarPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBarPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBarPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBarPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignBarPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBarPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBarPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBarPlot) plot).getDomainAxisMinValueExpression());
			np.setItemLabel(((JRDesignBarPlot) plot).getItemLabel());
			np.setRangeAxisMaxValueExpression(((JRDesignBarPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBarPlot) plot).getRangeAxisMinValueExpression());
			np.setShowLabels(((JRDesignBarPlot) plot).getShowLabels());
			np.setValueAxisLabelColor(((JRDesignBarPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBarPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBarPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBarPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBarPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBarPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBarPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBarPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignAreaPlot) {
			np.setCategoryAxisLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignAreaPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignAreaPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignAreaPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignAreaPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignAreaPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignAreaPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignAreaPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignAreaPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignAreaPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignLinePlot) {
			np.setCategoryAxisLabelColor(((JRDesignLinePlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignLinePlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignLinePlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignLinePlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignLinePlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignLinePlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignLinePlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignLinePlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignLinePlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignLinePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignLinePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignLinePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignLinePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignLinePlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignLinePlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignLinePlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignLinePlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignLinePlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignLinePlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignLinePlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignLinePlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignCandlestickPlot) {
			np.setCategoryAxisLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignCandlestickPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignCandlestickPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignCandlestickPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignCandlestickPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignTimeSeriesPlot) {
			np.setCategoryAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignHighLowPlot) {
			np.setCategoryAxisLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignHighLowPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignHighLowPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignHighLowPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignHighLowPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignHighLowPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignHighLowPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignHighLowPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignHighLowPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignHighLowPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBubblePlot) {
			np.setCategoryAxisLabelColor(((JRDesignBubblePlot) plot).getXAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBubblePlot) plot).getXAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBubblePlot) plot).getXAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBubblePlot) plot).getXAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBubblePlot) plot).getXAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBubblePlot) plot).getXAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBubblePlot) plot).getXAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBubblePlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBubblePlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBubblePlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBubblePlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBubblePlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBubblePlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBubblePlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getYAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignScatterPlot) {
			np.setCategoryAxisLabelColor(((JRDesignScatterPlot) plot).getXAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignScatterPlot) plot).getXAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignScatterPlot) plot).getXAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignScatterPlot) plot).getXAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignScatterPlot) plot).getXAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignScatterPlot) plot).getXAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignScatterPlot) plot).getXAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignScatterPlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignScatterPlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignScatterPlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignScatterPlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignScatterPlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignScatterPlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignScatterPlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignBarPlot merge2BarPlot(JRDesignChart chart, JRChartPlot plot) {
		JRDesignBarPlot np = new JRDesignBarPlot(chart.getPlot(), chart);
		if (plot instanceof JRDesignBar3DPlot) {
			np.setCategoryAxisLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBar3DPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBar3DPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignBar3DPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBar3DPlot) plot).getDomainAxisMinValueExpression());
			np.setItemLabel(((JRDesignBar3DPlot) plot).getItemLabel());
			np.setRangeAxisMaxValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBar3DPlot) plot).getRangeAxisMinValueExpression());
			np.setShowLabels(((JRDesignBar3DPlot) plot).getShowLabels());
			np.setValueAxisLabelColor(((JRDesignBar3DPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBar3DPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBar3DPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBar3DPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBar3DPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBar3DPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBar3DPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBar3DPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignAreaPlot) {
			np.setCategoryAxisLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignAreaPlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignAreaPlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignAreaPlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignAreaPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignAreaPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignAreaPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignAreaPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignAreaPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignAreaPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignAreaPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignAreaPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignAreaPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignAreaPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignLinePlot) {
			np.setCategoryAxisLabelColor(((JRDesignLinePlot) plot).getCategoryAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignLinePlot) plot).getCategoryAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignLinePlot) plot).getCategoryAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignLinePlot) plot).getCategoryAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignLinePlot) plot).getCategoryAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignLinePlot) plot).getCategoryAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignLinePlot) plot).getCategoryAxisTickLabelMask());
			np.setCategoryAxisTickLabelRotation(((JRDesignLinePlot) plot).getCategoryAxisTickLabelRotation());
			np.setCategoryAxisVerticalTickLabels(((JRDesignLinePlot) plot).getCategoryAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignLinePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignLinePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignLinePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignLinePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignLinePlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignLinePlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignLinePlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignLinePlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignLinePlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignLinePlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignLinePlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignLinePlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignCandlestickPlot) {
			np.setCategoryAxisLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignCandlestickPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignCandlestickPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignCandlestickPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignCandlestickPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignCandlestickPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignCandlestickPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignCandlestickPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignTimeSeriesPlot) {
			np.setCategoryAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignTimeSeriesPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignTimeSeriesPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignTimeSeriesPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignTimeSeriesPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignHighLowPlot) {
			np.setCategoryAxisLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignHighLowPlot) plot).getTimeAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignHighLowPlot) plot).getTimeAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignHighLowPlot) plot).getTimeAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getTimeAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignHighLowPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignHighLowPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignHighLowPlot) plot).getValueAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignHighLowPlot) plot).getValueAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignHighLowPlot) plot).getValueAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignHighLowPlot) plot).getValueAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignHighLowPlot) plot).getValueAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignHighLowPlot) plot).getValueAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignHighLowPlot) plot).getValueAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignHighLowPlot) plot).getValueAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignBubblePlot) {
			np.setCategoryAxisLabelColor(((JRDesignBubblePlot) plot).getXAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignBubblePlot) plot).getXAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignBubblePlot) plot).getXAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignBubblePlot) plot).getXAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignBubblePlot) plot).getXAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignBubblePlot) plot).getXAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignBubblePlot) plot).getXAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignBubblePlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignBubblePlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignBubblePlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignBubblePlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignBubblePlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignBubblePlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignBubblePlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignBubblePlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignBubblePlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignBubblePlot) plot).getYAxisVerticalTickLabels());
		} else if (plot instanceof JRDesignScatterPlot) {
			np.setCategoryAxisLabelColor(((JRDesignScatterPlot) plot).getXAxisLabelColor());
			np.setCategoryAxisLabelExpression(((JRDesignScatterPlot) plot).getXAxisLabelExpression());
			np.setCategoryAxisLabelFont(((JRDesignScatterPlot) plot).getXAxisLabelFont());
			np.setCategoryAxisLineColor(((JRDesignScatterPlot) plot).getXAxisLineColor());
			np.setCategoryAxisTickLabelColor(((JRDesignScatterPlot) plot).getXAxisTickLabelColor());
			np.setCategoryAxisTickLabelFont(((JRDesignScatterPlot) plot).getXAxisTickLabelFont());
			np.setCategoryAxisTickLabelMask(((JRDesignScatterPlot) plot).getXAxisTickLabelMask());
			np.setCategoryAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getXAxisVerticalTickLabels());
			np.setDomainAxisMaxValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMaxValueExpression());
			np.setDomainAxisMinValueExpression(((JRDesignScatterPlot) plot).getDomainAxisMinValueExpression());
			np.setRangeAxisMaxValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMaxValueExpression());
			np.setRangeAxisMinValueExpression(((JRDesignScatterPlot) plot).getRangeAxisMinValueExpression());
			np.setValueAxisLabelColor(((JRDesignScatterPlot) plot).getYAxisLabelColor());
			np.setValueAxisLabelExpression(((JRDesignScatterPlot) plot).getYAxisLabelExpression());
			np.setValueAxisLabelFont(((JRDesignScatterPlot) plot).getYAxisLabelFont());
			np.setValueAxisLineColor(((JRDesignScatterPlot) plot).getYAxisLineColor());
			np.setValueAxisTickLabelColor(((JRDesignScatterPlot) plot).getYAxisTickLabelColor());
			np.setValueAxisTickLabelFont(((JRDesignScatterPlot) plot).getYAxisTickLabelFont());
			np.setValueAxisTickLabelMask(((JRDesignScatterPlot) plot).getYAxisTickLabelMask());
			np.setValueAxisVerticalTickLabels(((JRDesignScatterPlot) plot).getYAxisVerticalTickLabels());
		}
		return np;
	}

	protected JRDesignTimeSeriesDataset merge2TimeSeriesDataset(JRChartDataset ds) {
		JRDesignTimeSeriesDataset nds = new JRDesignTimeSeriesDataset(ds);
		if (ds instanceof JRDesignXyDataset) {
			for (JRXySeries s : ((JRDesignXyDataset) ds).getSeries()) {
				JRDesignTimeSeries ns = new JRDesignTimeSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setTimePeriodExpression(s.getXValueExpression());
				ns.setValueExpression(s.getYValueExpression());
				nds.addTimeSeries(ns);
			}
		} else if (ds instanceof JRDesignCategoryDataset) {
			for (JRCategorySeries s : ((JRDesignCategoryDataset) ds).getSeries()) {
				JRDesignTimeSeries ns = new JRDesignTimeSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setTimePeriodExpression(s.getCategoryExpression());
				ns.setValueExpression(s.getValueExpression());
				nds.addTimeSeries(ns);
			}
		} else if (ds instanceof JRDesignXyDataset) {
			for (JRXySeries s : ((JRDesignXyDataset) ds).getSeries()) {
				JRDesignTimeSeries ns = new JRDesignTimeSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setTimePeriodExpression(s.getXValueExpression());
				ns.setValueExpression(s.getYValueExpression());
				nds.addTimeSeries(ns);
			}
		} else if (ds instanceof JRDesignGanttDataset) {
			for (JRGanttSeries s : ((JRDesignGanttDataset) ds).getSeries()) {
				JRDesignTimeSeries ns = new JRDesignTimeSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				nds.addTimeSeries(ns);
			}
		}
		return nds;
	}

	protected JRDesignGanttDataset merge2GanttDataset(JRChartDataset ds) {
		JRDesignGanttDataset nds = new JRDesignGanttDataset(ds);
		if (ds instanceof JRDesignXyDataset) {
			for (JRXySeries s : ((JRDesignXyDataset) ds).getSeries()) {
				JRDesignGanttSeries ns = new JRDesignGanttSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				nds.addGanttSeries(ns);
			}
		} else if (ds instanceof JRDesignCategoryDataset) {
			for (JRCategorySeries s : ((JRDesignCategoryDataset) ds).getSeries()) {
				JRDesignGanttSeries ns = new JRDesignGanttSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				nds.addGanttSeries(ns);
			}
		} else if (ds instanceof JRDesignXyDataset) {
			for (JRXySeries s : ((JRDesignXyDataset) ds).getSeries()) {
				JRDesignGanttSeries ns = new JRDesignGanttSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				nds.addGanttSeries(ns);
			}
		} else if (ds instanceof JRDesignTimeSeriesDataset) {
			for (JRTimeSeries s : ((JRDesignTimeSeriesDataset) ds).getSeries()) {
				JRDesignGanttSeries ns = new JRDesignGanttSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				nds.addGanttSeries(ns);
			}
		}
		return nds;
	}

	protected JRDesignXyzDataset merge2XyzDataset(JRChartDataset ds) {
		JRDesignXyzDataset nds = new JRDesignXyzDataset(ds);
		if (ds instanceof JRDesignXyDataset) {
			for (JRXySeries s : ((JRDesignXyDataset) ds).getSeries()) {
				JRDesignXyzSeries ns = new JRDesignXyzSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setXValueExpression(s.getXValueExpression());
				ns.setYValueExpression(s.getYValueExpression());
				nds.addXyzSeries(ns);
			}
		} else if (ds instanceof JRDesignCategoryDataset) {
			for (JRCategorySeries s : ((JRDesignCategoryDataset) ds).getSeries()) {
				JRDesignXyzSeries ns = new JRDesignXyzSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setXValueExpression(s.getCategoryExpression());
				ns.setYValueExpression(s.getValueExpression());
				nds.addXyzSeries(ns);
			}
		} else if (ds instanceof JRDesignGanttDataset) {
			for (JRGanttSeries s : ((JRDesignGanttDataset) ds).getSeries()) {
				JRDesignXyzSeries ns = new JRDesignXyzSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				nds.addXyzSeries(ns);
			}
		} else if (ds instanceof JRDesignTimeSeriesDataset) {
			for (JRTimeSeries s : ((JRDesignTimeSeriesDataset) ds).getSeries()) {
				JRDesignXyzSeries ns = new JRDesignXyzSeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setXValueExpression(s.getTimePeriodExpression());
				ns.setYValueExpression(s.getValueExpression());
				nds.addXyzSeries(ns);
			}
		}
		return nds;
	}

	protected JRDesignXyDataset merge2XyDataset(JRChartDataset ds) {
		JRDesignXyDataset nds = new JRDesignXyDataset(ds);
		if (ds instanceof JRDesignXyzDataset) {
			for (JRXyzSeries s : ((JRDesignXyzDataset) ds).getSeries()) {
				JRDesignXySeries ns = new JRDesignXySeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setXValueExpression(s.getXValueExpression());
				ns.setYValueExpression(s.getYValueExpression());
				nds.addXySeries(ns);
			}
		} else if (ds instanceof JRDesignCategoryDataset) {
			for (JRCategorySeries s : ((JRDesignCategoryDataset) ds).getSeries()) {
				JRDesignXySeries ns = new JRDesignXySeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setXValueExpression(s.getCategoryExpression());
				ns.setYValueExpression(s.getValueExpression());
				nds.addXySeries(ns);
			}
		} else if (ds instanceof JRDesignGanttDataset) {
			for (JRGanttSeries s : ((JRDesignGanttDataset) ds).getSeries()) {
				JRDesignXySeries ns = new JRDesignXySeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				nds.addXySeries(ns);
			}
		} else if (ds instanceof JRDesignTimeSeriesDataset) {
			for (JRTimeSeries s : ((JRDesignTimeSeriesDataset) ds).getSeries()) {
				JRDesignXySeries ns = new JRDesignXySeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setXValueExpression(s.getTimePeriodExpression());
				ns.setYValueExpression(s.getValueExpression());
				nds.addXySeries(ns);
			}
		}
		return nds;
	}

	protected JRDesignCategoryDataset merge2CategoryDataset(JRChartDataset ds) {
		JRDesignCategoryDataset nds = new JRDesignCategoryDataset(ds);
		if (ds instanceof JRDesignXyzDataset) {
			for (JRXyzSeries s : ((JRDesignXyzDataset) ds).getSeries()) {
				JRDesignCategorySeries ns = new JRDesignCategorySeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setCategoryExpression(s.getXValueExpression());
				ns.setValueExpression(s.getYValueExpression());
				nds.addCategorySeries(ns);
			}
		} else if (ds instanceof JRDesignXyDataset) {
			for (JRXySeries s : ((JRDesignXyDataset) ds).getSeries()) {
				JRDesignCategorySeries ns = new JRDesignCategorySeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setCategoryExpression(s.getXValueExpression());
				ns.setValueExpression(s.getYValueExpression());
				nds.addCategorySeries(ns);
			}
		} else if (ds instanceof JRDesignGanttDataset) {
			for (JRGanttSeries s : ((JRDesignGanttDataset) ds).getSeries()) {
				JRDesignCategorySeries ns = new JRDesignCategorySeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				nds.addCategorySeries(ns);
			}
		} else if (ds instanceof JRDesignTimeSeriesDataset) {
			for (JRTimeSeries s : ((JRDesignTimeSeriesDataset) ds).getSeries()) {
				JRDesignCategorySeries ns = new JRDesignCategorySeries();
				ns.setItemHyperlink(s.getItemHyperlink());
				ns.setSeriesExpression(s.getSeriesExpression());
				ns.setCategoryExpression(s.getTimePeriodExpression());
				ns.setValueExpression(s.getValueExpression());
				nds.addCategorySeries(ns);
			}
		}
		return nds;
	}

	@Override
	public boolean canFlipToNextPage() {
		if (chartType == JRDesignChart.CHART_TYPE_MULTI_AXIS) {
			JRDesignChart old = (JRDesignChart) chart.getValue();
			if (chartType != old.getChartType())
				old.setChartType(chartType);
		}
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
			setGallyeryItemImageInfo(ti, "/icons/area_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_AREA);
			return ti;

		case JRDesignChart.CHART_TYPE_BAR:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_bar_chart);
			setGallyeryItemImageInfo(ti, "/icons/bar_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BAR);
			return ti;
		case JRDesignChart.CHART_TYPE_BAR3D:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_bar3d_chart);
			setGallyeryItemImageInfo(ti, "/icons/bar3d_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BAR3D);
			return ti;
		case JRDesignChart.CHART_TYPE_BUBBLE:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_bubble_chart);
			setGallyeryItemImageInfo(ti, "/icons/bubble_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_BUBBLE);
			return ti;
		case JRDesignChart.CHART_TYPE_CANDLESTICK:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_candlestick_chart);
			setGallyeryItemImageInfo(ti, "/icons/candlestick_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_CANDLESTICK);
			return ti;
		case JRDesignChart.CHART_TYPE_GANTT:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_gantt);
			setGallyeryItemImageInfo(ti, "/icons/gantt_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_GANTT);
			return ti;
		case JRDesignChart.CHART_TYPE_HIGHLOW:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_highlow_chart);
			setGallyeryItemImageInfo(ti, "/icons/highlow_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_HIGHLOW);
			return ti;
		case JRDesignChart.CHART_TYPE_LINE:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_line_chart);
			setGallyeryItemImageInfo(ti, "/icons/line_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_LINE);
			return ti;
		case JRDesignChart.CHART_TYPE_METER:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_meter_chart);
			setGallyeryItemImageInfo(ti, "/icons/meter_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_METER);
			return ti;
		case JRDesignChart.CHART_TYPE_MULTI_AXIS:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_multiaxes_chart);
			setGallyeryItemImageInfo(ti, "/icons/multiaxis_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_MULTI_AXIS);
			return ti;
		case JRDesignChart.CHART_TYPE_PIE:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_pie_chart);
			setGallyeryItemImageInfo(ti, "/icons/pie_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_PIE);
			return ti;
		case JRDesignChart.CHART_TYPE_PIE3D:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_pie3d_chart);
			setGallyeryItemImageInfo(ti, "/icons/pie3d_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_PIE3D);
			return ti;
		case JRDesignChart.CHART_TYPE_SCATTER:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_scatter_chart);
			setGallyeryItemImageInfo(ti, "/icons/scatter_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_SCATTER);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDAREA:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_stacked_area);
			setGallyeryItemImageInfo(ti, "/icons/stackedarea_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDAREA);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDBAR:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_stacked_bar);
			setGallyeryItemImageInfo(ti, "/icons/stackedbar_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDBAR);
			return ti;
		case JRDesignChart.CHART_TYPE_STACKEDBAR3D:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_stacked_bar3D);
			setGallyeryItemImageInfo(ti, "/icons/stackedbar3d_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_STACKEDBAR3D);
			return ti;
		case JRDesignChart.CHART_TYPE_THERMOMETER:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_thermometer_chart);
			setGallyeryItemImageInfo(ti, "/icons/thermometer_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_THERMOMETER);
			return ti;
		case JRDesignChart.CHART_TYPE_TIMESERIES:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_timeseries_chart);
			setGallyeryItemImageInfo(ti, "/icons/timeseries_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_TIMESERIES);
			return ti;
		case JRDesignChart.CHART_TYPE_XYAREA:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_xy_area);
			setGallyeryItemImageInfo(ti, "/icons/xyarea_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYAREA);
			return ti;
		case JRDesignChart.CHART_TYPE_XYBAR:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_xy_bar);
			setGallyeryItemImageInfo(ti, "/icons/xybar_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYBAR);
			return ti;
		case JRDesignChart.CHART_TYPE_XYLINE:
			ti = new GalleryItem(gr, SWT.NONE);
			ti.setText(Messages.common_xy_line);
			setGallyeryItemImageInfo(ti, "/icons/xyline_big.png"); //$NON-NLS-1$
			ti.setData(JRDesignChart.CHART_TYPE_XYLINE);
			return ti;
		}
		return null;
	}

	private static void setGallyeryItemImageInfo(GalleryItem item, String imagePath) {
		UIUtil.setGallyeryItemImageInfo(item, Activator.PLUGIN_ID, imagePath, selectedImages, standardImages);
	}
}
