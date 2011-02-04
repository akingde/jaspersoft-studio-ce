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
package com.jaspersoft.studio.list.model.command.wizard;

import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.list.messages.Messages;
import com.jaspersoft.studio.list.model.MList;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.wizards.dataset.WizardConnectionPage;
import com.jaspersoft.studio.wizards.dataset.WizardDatasetPage;

public class ListWizard extends Wizard {
	private WizardDatasetPage page0;
	private WizardConnectionPage page2;
	private MList list;

	public ListWizard() {
		super();
		setWindowTitle(Messages.common_list);
	}

	@Override
	public void addPages() {

		this.list = new MList();
		list.setValue(list.createJRElement(jasperDesign));

		MDatasetRun mdataset = (MDatasetRun) list.getPropertyValue(MList.PREFIX + "DATASET_RUN"); //$NON-NLS-1$
		if (mdataset == null)
			mdataset = new MDatasetRun(new JRDesignDatasetRun(), jasperDesign);

		page0 = new WizardDatasetPage(jasperDesign);
		addPage(page0);
		page0.setDataSetRun(mdataset);

		page2 = new WizardConnectionPage();
		addPage(page2);
		page2.setDataSetRun(mdataset);

	}

	public MList getList() {
		return list;
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
