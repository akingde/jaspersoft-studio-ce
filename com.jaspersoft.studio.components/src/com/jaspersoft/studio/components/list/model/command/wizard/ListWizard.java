/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.list.model.command.wizard;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;
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
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.dataset.wizard.WizardConnectionPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetPage;
import com.jaspersoft.studio.wizards.JSSWizard;
import com.jaspersoft.studio.wizards.datasource.StaticWizardDataSourcePage;
import com.jaspersoft.studio.wizards.fields.StaticWizardFieldsPage;

/**
 * Wizard to create an MList. 
 * 
 * @author gtoffoli
 *
 */
public class ListWizard extends JSSWizard {
	
	private WizardDatasetPage step1;
	private WizardConnectionPage step2;
	private StaticWizardFieldsPage step3;
	
	/**
	 * Suggested width of the list element
	 */
	private int suggestedWidth = -1;
	
	/**
	 * Suggested height of the list element
	 */
	private int suggestedHeight = -1;
	
	
	public ListWizard() {
		super();
		setWindowTitle(Messages.common_list);
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Crate the list with a suggested width and height. This values
	 * are used also to calculate the size of the elements placed inside the list.
	 * 
	 * @param suggestedWidth the suggested width for the list, or -1 for the default value
	 * @param suggestedHeight the suggested height for the list, or -1 for the default value
	 */
	public ListWizard(int suggestedWidth, int suggestedHeight) {
		this();
		this.suggestedHeight = suggestedHeight;
		this.suggestedWidth = suggestedWidth;
	}

	@Override
	public void addPages() {

		step1 = new WizardDatasetPage(false, Messages.ListWizard_0);
		addPage(step1);

		step2 = new WizardConnectionPage();
		addPage(step2);

		step3 = new StaticWizardFieldsPage();
		addPage(step3);
		step3.setTitle(Messages.ListWizard_1);
		step3.setDescription(Messages.ListWizard_2);
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
				getSettings().put( StaticWizardDataSourcePage.DISCOVERED_FIELDS, new ArrayList<Object>( listDataset.getFieldsList() ));
				getSettings().put( StaticWizardDataSourcePage.DISCOVERED_PARAMETERS, new ArrayList<Object>( listDataset.getParametersList() ));
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
		
		JRDesignComponentElement jrElement = list.createJRElement(getConfig().getJasperDesign(), true);
		//Set the element size
		if (suggestedWidth > 0) jrElement.setWidth(suggestedWidth);
		if (suggestedHeight > 0) jrElement.setHeight(suggestedHeight);
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
		//Width of each element inside the list
		int elementWidth = lst.size() > 0 ? Math.round(jrElement.getWidth()/lst.size()) : jrElement.getWidth();
		if (lst != null)
			for (Object f : lst) {
				JRDesignTextField element = mtext.createJRElement(jd, true);
				element.setX(x);
				element.setWidth(elementWidth);
				element.setHeight(jrElement.getHeight());
				String field = ((JRField) f).getName();
				element.setExpression(new JRDesignExpression("$F{" + field + "}")); //$NON-NLS-1$ //$NON-NLS-2$
				((DesignListContents) jrList.getContents()).addElement(element);
				x += element.getWidth();
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
