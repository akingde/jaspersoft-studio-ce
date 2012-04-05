/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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

import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.property.dataset.wizard.WizardConnectionPage;
import com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog.SubreportPropertyPage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;
import com.jaspersoft.studio.wizards.ReportNewWizard;

public class SubreportWizard extends JSSWizard {
	private NewSubreportPage step0;
	private WizardConnectionPage step2;
	private SubreportPropertyPage step3;
	private MSubreport subreport;
	private MDatasetRun mdataset;

	public SubreportWizard() {
		super();
		setWindowTitle(Messages.common_subreport);
	}

	@Override
	public void addPages() {
		this.subreport = new MSubreport();
		subreport.setValue(subreport.createJRElement(getConfig().getJasperDesign()));
		subreport.setPropertyValue(JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION, "$P{REPORT_CONNECTION}");

		step0 = new NewSubreportPage();
		step0.setSubreport(subreport);
		addPage(step0);

		mdataset = new MDatasetRun(new JRDesignDatasetRun(), getConfig().getJasperDesign());
		mdataset.setPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, "$P{REPORT_CONNECTION}");
		subreport.setJasperConfiguration(getConfig());
		mdataset.setJasperConfiguration(getConfig());

		step2 = new WizardConnectionPage();
		addPage(step2);
		step2.setDataSetRun(mdataset);

		step3 = new SubreportPropertyPage();
		addPage(step3);

	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == step2) {
			IFile file = (IFile) getConfig().get(ReportNewWizard.REPORT_FILE);
			JasperDesign newjd = (JasperDesign) getConfig().get(ReportNewWizard.REPORT_DESIGN);
			step0.setUpSubreport(file, newjd);
		}
		if (page == step3) {
			JRSubreportParameter[] map = (JRSubreportParameter[]) subreport
					.getPropertyValue(JRDesignSubreport.PROPERTY_PARAMETERS);
			if (map != null)
				step3.setValue(map);
		}
		return super.getNextPage(page);
	}

	public MSubreport getSubreport() {
		JRSubreportParameter[] map = step3.getValue();
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
	public void init(JasperReportsConfiguration jd) {
		super.init(jd);
		if (subreport != null)
			subreport.setJasperConfiguration(jd);
		if (mdataset != null)
			mdataset.setJasperConfiguration(jd);
	}
}
