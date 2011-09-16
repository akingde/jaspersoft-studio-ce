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

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChart;

public class ChartWizard extends Wizard {
	private ChartTypeWizardPage page0;
	private ChartDataPage step1;
	// private ChartVisualPage step2;
	private MChart chart;

	private int width;
	private int height;

	public ChartWizard(MChart chart, JasperDesign jasperDesign) {
		super();
		setWindowTitle(Messages.common_chart_wizard);
		this.chart = chart;
		this.jasperDesign = jasperDesign;
		JRChart jrChart = (JRChart) chart.getValue();
		width = jrChart.getWidth();
		height = jrChart.getHeight();
	}

	@Override
	public void addPages() {
		page0 = new ChartTypeWizardPage(chart);
		addPage(page0);

		step1 = new ChartDataPage(chart, jasperDesign);
		addPage(step1);

		// step2 = new ChartVisualPage(chart, jasperDesign);
		// addPage(step2);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {

		return super.getNextPage(page);
	}

	public MChart getChart() {
		JRDesignChart jrChart = (JRDesignChart) chart.getValue();
		jrChart.setWidth(width);
		jrChart.setHeight(height);
		return chart;
	}

	@Override
	public boolean performFinish() {
		return (page0.canFlipToNextPage() && step1.canFlipToNextPage());
	}

	private JasperDesign jasperDesign;

}
