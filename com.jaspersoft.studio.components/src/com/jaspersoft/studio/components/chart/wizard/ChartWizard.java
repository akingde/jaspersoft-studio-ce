/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.chartAxis.command.ChartAxesWizardPage;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;

public class ChartWizard extends JSSWizard implements IExpressionContextSetter {
	private ChartTypeWizardPage page0;
	private ChartDataPage step1a;
	private ChartAxesWizardPage step1b;
	private MGraphicElement chart;
	private JRDesignElementDataset edataset;
	private ExpressionContext expContext;

	private int width;
	private int height;
	private boolean skipFirstPage = false;

	public ChartWizard(MGraphicElement chart, JRDesignElementDataset edataset, boolean skipFirstPage) {
		this(chart, edataset);
		this.skipFirstPage = skipFirstPage;
		setNeedsProgressMonitor(false);
	}

	public ChartWizard(MGraphicElement chart, JRDesignElementDataset edataset) {
		super();
		setWindowTitle(Messages.common_chart_wizard);
		this.chart = chart;
		this.edataset = edataset;
		JRDesignElement jrChart = (JRDesignElement) chart.getValue();
		width = jrChart.getWidth();
		height = jrChart.getHeight();
	}

	@Override
	public void addPages() {
		if (chart instanceof MChart) {
			page0 = new ChartTypeWizardPage((MChart) chart);
			addPage(page0);
		}

		step1a = new ChartDataPage((JRDesignElement) chart.getValue(), edataset, getConfig());
		step1a.setExpressionContext(expContext);
		addPage(step1a);

		step1b = new ChartAxesWizardPage();
		// don't add the page, we will handle with method #fixLastPage(boolean)
	}

	public MGraphicElement getChart() {
		JRDesignElement jrChart = (JRDesignElement) chart.getValue();
		jrChart.setWidth(width);
		jrChart.setHeight(height);
		return chart;
	}

	@Override
	public IWizardPage getStartingPage() {
		if (skipFirstPage && page0 != null) {
			page0.setLastPageShown(true);
			return step1a;
		}
		return super.getStartingPage();
	}

	@Override
	public boolean performFinish() {
		if (page0 != null) {
			boolean finished = page0.isPageComplete() && step1a.isPageComplete();
			page0.finishPage();
			return finished;
		}
		return step1a.isPageComplete();
	}

	@Override
	public void setConfig(JasperReportsConfiguration config, boolean disposeConfig) {
		super.setConfig(config, disposeConfig);
		if (chart != null)
			chart.setJasperConfiguration(config);
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
		if (step1a != null) {
			step1a.setExpressionContext(expContext);
		}
	}

	public void fixLastPage(boolean isMultiAxis) {
		IWizardPage[] pages = getPages();
		if (pages[pages.length - 1] instanceof ChartDataPage) {
			if (isMultiAxis) {
				removePage(step1a);
				addPage(step1b);
			}
		} else if (pages[pages.length - 1] instanceof ChartAxesWizardPage) {
			if (!isMultiAxis) {
				removePage(step1b);
				addPage(step1a);
			}
		} else {
			throw new RuntimeException("Use case not expected!");
		}
	}

	public byte getChoseAxis() {
		if (step1b != null) {
			return step1b.getChartAxis();
		}
		return 0;
	}

}
