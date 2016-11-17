/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.chartAxis.command;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.chart.messages.Messages;

public class ChartAxesWizard extends Wizard {
	private ChartAxesWizardPage page0;
	private byte chart;

	public ChartAxesWizard() {
		super();
		setWindowTitle(Messages.common_chartaxis_wizard);
	}

	@Override
	public void addPages() {
		page0 = new ChartAxesWizardPage();
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
	
	@Override
	public boolean canFinish() {
		byte chartAxis = getChartAxis();
		if(chartAxis<=0){
			return false;
		}
		return super.canFinish();
	}

}
