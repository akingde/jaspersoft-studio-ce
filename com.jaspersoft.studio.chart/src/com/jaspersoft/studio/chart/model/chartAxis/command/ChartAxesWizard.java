/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model.chartAxis.command;

import org.eclipse.jface.wizard.Wizard;

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

}
