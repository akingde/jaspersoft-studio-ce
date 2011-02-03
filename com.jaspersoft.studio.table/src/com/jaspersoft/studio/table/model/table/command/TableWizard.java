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
package com.jaspersoft.studio.table.model.table.command;

import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.table.messages.Messages;
import com.jaspersoft.studio.table.model.MTable;
import com.jaspersoft.studio.wizards.dataset.WizardConnectionPage;
import com.jaspersoft.studio.wizards.dataset.WizardDatasetPage;

public class TableWizard extends Wizard {
	private WizardDatasetPage page0;
	private TableWizardFieldsPage page1;
	private WizardConnectionPage page2;
	private TableWizardLayoutPage page3;
	private MTable table;

	public TableWizard() {
		super();
		setWindowTitle(Messages.common_table_wizard);
	}

	@Override
	public void addPages() {
		this.table = new MTable();
		table.setValue(table.createJRElement(jasperDesign));

		MDatasetRun mdataset = (MDatasetRun) table.getPropertyValue(StandardTable.PROPERTY_DATASET_RUN);
		if (mdataset == null)
			mdataset = new MDatasetRun(new JRDesignDatasetRun(), jasperDesign);

		page0 = new WizardDatasetPage(jasperDesign);
		addPage(page0);
		page0.setDataSetRun(mdataset);

		page2 = new WizardConnectionPage();
		addPage(page2);
		page2.setDataSetRun(mdataset);

		page1 = new TableWizardFieldsPage();
		addPage(page1);

		page3 = new TableWizardLayoutPage();
		addPage(page3);
	}

	public MTable getTable() {
		return table;
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
