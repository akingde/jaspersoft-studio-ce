/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.model.crosstab.command.wizard;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.crosstab.model.MCrosstab;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.MElementDataset;
import com.jaspersoft.studio.wizards.dataset.WizardConnectionPage;
import com.jaspersoft.studio.wizards.dataset.WizardDatasetPage;

public class CrosstabWizard extends Wizard {
	private WizardDatasetPage page0;
	private CrosstabWizardColumnPage page1;
	private CrosstabWizardRowPage page2;
	private CrosstabWizardMeasurePage page3;
	private CrosstabWizardLayoutPage page4;
	private WizardConnectionPage page5;
	private MCrosstab crosstab;

	public CrosstabWizard() {
		super();
		setWindowTitle(Messages.common_crosstab_wizard);
	}

	@Override
	public void addPages() {
		this.crosstab = new MCrosstab();
		crosstab.setValue(crosstab.createJRElement(jasperDesign));

		page0 = new WizardDatasetPage(jasperDesign);
		addPage(page0);
		MElementDataset dataset = (MElementDataset) crosstab.getPropertyValue(JRDesignCrosstab.PROPERTY_DATASET);
		MDatasetRun mdataset = (MDatasetRun) dataset.getPropertyValue(JRDesignElementDataset.PROPERTY_DATASET_RUN);
		page0.setDataSetRun(mdataset);

		page5 = new WizardConnectionPage();
		addPage(page5);
		page5.setDataSetRun(mdataset);

		page1 = new CrosstabWizardColumnPage();
		addPage(page1);
		page1.setCrosstab(crosstab);

		page2 = new CrosstabWizardRowPage();
		addPage(page2);
		page2.setCrosstab(crosstab);

		page3 = new CrosstabWizardMeasurePage();
		addPage(page3);
		page3.setCrosstab(crosstab);

		page4 = new CrosstabWizardLayoutPage();
		addPage(page4);
		page4.setCrosstab(crosstab);
	}

	public MCrosstab getCrosstab() {
		return crosstab;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	private JasperDesign jasperDesign;

	public void init(JasperDesign jd) {
		this.jasperDesign = jd;
	}

}
