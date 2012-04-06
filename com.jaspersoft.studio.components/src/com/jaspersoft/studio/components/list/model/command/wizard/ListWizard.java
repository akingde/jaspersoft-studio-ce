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
package com.jaspersoft.studio.components.list.model.command.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.components.list.messages.Messages;
import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.dataset.wizard.DatasetWizard;
import com.jaspersoft.studio.property.dataset.wizard.WizardConnectionPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;

public class ListWizard extends JSSWizard {
	private WizardDatasetPage step1;
	private WizardConnectionPage step2;
	private WizardFieldsPage step3;
	private MList list;
	private JRDesignDataset jrdataset;

	public ListWizard() {
		super();
		setWindowTitle(Messages.common_list);
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		list = new MList();
		list.setValue(list.createJRElement(getConfig().getJasperDesign()));
		list.setJasperConfiguration(getConfig());

		MDatasetRun mdatasetrun = (MDatasetRun) list
				.getPropertyValue(MList.PREFIX + "DATASET_RUN");//$NON-NLS-1$
		if (mdatasetrun == null)
			mdatasetrun = new MDatasetRun(new JRDesignDatasetRun(), getConfig()
					.getJasperDesign());
		mdatasetrun.setPropertyValue(
				JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION,
				"$P{REPORT_CONNECTION}");

		step1 = new WizardDatasetPage(getConfig(), false, "List");
		addPage(step1);
		step1.setDataSetRun(mdatasetrun);

		step2 = new WizardConnectionPage();
		addPage(step2);
		step2.setDataSetRun(mdatasetrun);

		step3 = new WizardFieldsPage();
		addPage(step3);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		JasperDesign jd = getConfig().getJasperDesign();
		List<JRDataset> datasetsList = jd.getDatasetsList();
		if (page == step1) {
			if (datasetsList.size() == 0)
				return step2;
		}
		if (page == step2) {
			jrdataset = getDataset();
			if (jrdataset != null)
				step3.setFields(new ArrayList<JRField>(Arrays.asList(jrdataset
						.getFields())));
			else
				page = step3;
		}
		return super.getNextPage(page);
	}

	private JRDesignDataset getDataset() {
		JasperDesign jd = getConfig().getJasperDesign();
		List<JRDataset> datasetsList = jd.getDatasetsList();
		MDatasetRun dataSetRun = step1.getDataSetRun();
		JRDesignDataset ds = null;
		if (dataSetRun == null) {
			MDataset mds = (MDataset) getConfig().get(DatasetWizard.DATASET);
			if (mds != null)
				ds = mds.getValue();
		} else {
			String dsname = (String) dataSetRun
					.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME);
			for (JRDataset d : datasetsList)
				if (d.getName().equals(dsname)) {
					ds = (JRDesignDataset) d;
					break;
				}
		}
		return ds;
	}

	public MList getList() {
		JRDesignComponentElement jrElement = list.getValue();
		StandardListComponent jrList = (StandardListComponent) jrElement
				.getComponent();

		List<Object> lst = step3.getFields();
		JasperDesign jd = getConfig().getJasperDesign();
		int x = 0;
		MTextField mtext = new MTextField();
		for (Object f : lst) {
			JRDesignTextField element = mtext.createJRElement(jd);
			element.setX(x);
			String field = ((JRField) f).getName();
			element.setExpression(new JRDesignExpression("$F{" + field + "}"));
			((DesignListContents) jrList.getContents()).addElement(element);
			x += element.getWidth();
			jrElement.setHeight(Math.max(jrElement.getHeight(),
					element.getHeight()));
			jrElement.setWidth(Math.max(x, jrElement.getWidth()));
		}
		if (jrdataset == null)
			jrList.setDatasetRun(null);
		else
			((JRDesignDatasetRun) jrList.getDatasetRun())
					.setDatasetName(jrdataset.getName());

		return list;
	}

	@Override
	public void init(JasperReportsConfiguration jConfig) {
		super.init(jConfig);
		if (list != null)
			list.setJasperConfiguration(jConfig);
	}
}
