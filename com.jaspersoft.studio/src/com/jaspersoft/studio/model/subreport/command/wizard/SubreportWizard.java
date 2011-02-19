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

import java.util.Map;

import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog.SubreportPropertyPage;
import com.jaspersoft.studio.wizards.dataset.WizardConnectionPage;

public class SubreportWizard extends Wizard {

	private WizardNewSubreportPage step1;
	private WizardConnectionPage step2;
	private SubreportPropertyPage step3;
	private MSubreport subreport;

	public SubreportWizard() {
		super();
		setWindowTitle(Messages.common_subreport);
	}

	@Override
	public void addPages() {
		this.subreport = new MSubreport();
		subreport.setValue(subreport.createJRElement(jasperDesign));

		MDatasetRun mdataset = new MDatasetRun(new JRDesignDatasetRun(), jasperDesign);

		step1 = new WizardNewSubreportPage();
		addPage(step1);
		step1.setSubreport(subreport);

		step2 = new WizardConnectionPage();
		addPage(step2);
		step2.setDataSetRun(mdataset);

		step3 = new SubreportPropertyPage();
		addPage(step3);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {

		if (page == step3) {
			Map map = (Map) subreport.getPropertyValue(JRDesignSubreport.PROPERTY_PARAMETERS);
			if (map != null)
				step3.setValue(map);
		}
		return super.getNextPage(page);
	}

	public MSubreport getSubreport() {
		Map<String, JRSubreportParameter> map = step3.getValue();
		if (map != null)
			subreport.setPropertyValue(JRDesignSubreport.PROPERTY_PARAMETERS, map);

		MDatasetRun dr = step2.getDataSetRun();
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION,
				dr.getPropertyValue(JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION));
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION,
				dr.getPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION));
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION,
				dr.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION));

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
