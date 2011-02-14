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
package com.jaspersoft.studio.model.subreport.command.wizard;

import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.wizards.dataset.WizardConnectionPage;

public class SubreportWizard extends Wizard {

	private WizardConnectionPage page5;
	private MSubreport subreport;

	public SubreportWizard() {
		super();
		setWindowTitle(Messages.SubreportWizard_0);
	}

	@Override
	public void addPages() {

		MDatasetRun mdataset = new MDatasetRun(new JRDesignDatasetRun(), jasperDesign);

		page5 = new WizardConnectionPage();
		addPage(page5);
		page5.setDataSetRun(mdataset);
	}

	public MSubreport getSubreport() {
		this.subreport = new MSubreport();
		subreport.setValue(subreport.createJRElement(jasperDesign));
		JRDesignSubreport jrSubreport = (JRDesignSubreport) subreport.getValue();
		JRDatasetRun jrDR = (JRDatasetRun) page5.getDataSetRun().getValue();

		jrSubreport.setDataSourceExpression(jrDR.getDataSourceExpression());
		jrSubreport.setConnectionExpression(jrDR.getConnectionExpression());

		return subreport;
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
