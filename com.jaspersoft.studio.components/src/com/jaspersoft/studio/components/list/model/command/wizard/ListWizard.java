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
import java.util.UUID;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
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
import com.jaspersoft.studio.property.dataset.wizard.WizardDataSourcePage;
import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * Wizard to create an MList. 
 * 
 * @author gtoffoli
 *
 */
public class ListWizard extends JSSWizard {
	
	private WizardDatasetPage step1;
	private WizardConnectionPage step2;
	private WizardFieldsPage step3;
	
	
	public ListWizard() {
		super();
		setWindowTitle(Messages.common_list);
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {

		step1 = new WizardDatasetPage(false, "List");
		addPage(step1);

		step2 = new WizardConnectionPage();
		addPage(step2);

		step3 = new WizardFieldsPage();
		addPage(step3);
		step3.setTitle("List fields");
		step3.setDescription("You can optionally select a set of fields from the dataset used to fill this list, in this way the list will be prepopulated with a set of properly configured text fields");
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		
		// Configuring next steps..
		if (page == step2) {
			
			// If we come from this step, we have devided which
			// dataset to use, it could be an existing one, a new one
			// or even an empty one.
			
			JRDesignDataset listDataset = step1.getSelectedDataset();
			if (listDataset != null && listDataset.getFieldsList().size() > 0)
			{
				getSettings().put( WizardDataSourcePage.DISCOVERED_FIELDS, new ArrayList<Object>( listDataset.getFieldsList() ));
			}
			else
			{
				// we need to skip step3...
				page = step3;
			}
		}
		return super.getNextPage(page);
	}


	/**
	 * 
	 * Create a new instance of List component, and return the model object (MList).
	 * Based on the information provided by the users, we configure a simple list.
	 * 
	 *
	 *  @return MList
	 */
	public MList getList() {
		
		MList list = new MList();
		
		JRDesignComponentElement jrElement = list.createJRElement(getConfig().getJasperDesign());
		StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();
		
		// Get a copy of the dataset run created by the step2
		JRDesignDatasetRun datasetRun = (JRDesignDatasetRun) step2.getJRDesignDatasetRun().clone();
		
		// Set the dataset name used by this dataset run...
		jrList.setDatasetRun(datasetRun);
		
		// This is all the times a new instance of JRDataset, we
		// are interested only in its name
		JRDesignDataset listDataset = step1.getSelectedDataset();
		
		if (listDataset != null)
		{
			datasetRun.setDatasetName(listDataset.getName());
		}
		else
		{
			// FIXME: Consider to create an empty dataset here....
			// even if we should never finish in this situation...
		}
		
		
		list.setValue(jrElement);
		list.setJasperConfiguration(getConfig());

		// Create the list with a set of elements..
		List<Object> lst = step3.getSelectedFields();
		JasperDesign jd = getConfig().getJasperDesign();
		
		int x = 0;
		MTextField mtext = new MTextField();
		if (lst != null)
			for (Object f : lst) {
				JRDesignTextField element = mtext.createJRElement(jd);
				element.setX(x);
				String field = ((JRField) f).getName();
				element.setExpression(new JRDesignExpression("$F{" + field
						+ "}"));
				((DesignListContents) jrList.getContents()).addElement(element);
				x += element.getWidth();
				jrElement.setHeight(Math.max(jrElement.getHeight(),
						element.getHeight()));
				jrElement.setWidth(Math.max(x, jrElement.getWidth()));
			}
	
		return list;
	}

	/**
	 * 
	 * In this wizard, the only required step is the first one, which force the user to
	 * pick a dataset (or decide to create a new one).
	 * 
	 * @see com.jaspersoft.studio.wizards.JSSWizard#canFinish()
	 *
	 * @return true if the step is not the first one
	 */
	@Override
	public boolean canFinish() {
		
		if (getContainer().getCurrentPage() == step1) return false;
		return super.canFinish();
	}

	

}
