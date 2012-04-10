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

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;

public class ChartWizard extends JSSWizard {
	private ChartTypeWizardPage page0;
	private ChartDataPage step1;
	private MGraphicElement chart;
	private JRDesignElementDataset edataset;

	private int width;
	private int height;
	private boolean skipFirstPage = false;

	public ChartWizard(MGraphicElement chart, JRDesignElementDataset edataset,
			boolean skipFirstPage) {
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

		step1 = new ChartDataPage((JRDesignElement) chart.getValue(), edataset,
				getConfig());
		addPage(step1);
	}

	public MGraphicElement getChart() {
		JRDesignElement jrChart = (JRDesignElement) chart.getValue();
		jrChart.setWidth(width);
		jrChart.setHeight(height);
		return chart;
	}

	@Override
	public IWizardPage getStartingPage() {
		if (skipFirstPage && page0 != null)
			return step1;
		return super.getStartingPage();
	}

	@Override
	public boolean performFinish() {
		if (page0 != null) {
			return (page0.isPageComplete() && step1.isPageComplete());
		}
		return step1.isPageComplete();
	}

	@Override
	public void init(JasperReportsConfiguration jConfig) {
		super.init(jConfig);
		if (chart != null)
			chart.setJasperConfiguration(jConfig);
	}
}
