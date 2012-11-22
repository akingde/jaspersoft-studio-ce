/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.chartAxis.command;

import net.sf.jasperreports.engine.JRChartPlot;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.chart.messages.Messages;

public class ChartAxesWizard extends Wizard {
	private ChartAxesWizardPage page0;
	private byte chart;
	private Class<? extends JRChartPlot> chartPlot;

	public ChartAxesWizard(Class<? extends JRChartPlot> chartPlot) {
		super();
		this.chartPlot = chartPlot;
		setWindowTitle(Messages.common_chartaxis_wizard);
	}

	@Override
	public void addPages() {
		page0 = new ChartAxesWizardPage(chartPlot);
		addPage(page0);
	}

	public byte getChartAxis() {
		if (page0 != null)
			return page0.getChartAxis();
		return chart;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
