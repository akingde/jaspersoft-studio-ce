/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.dataset;

import net.sf.jasperreports.data.DataAdapterParameterContributorFactory;
import net.sf.jasperreports.engine.JRDataset;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * Wizard composed of a single page used to select or delete the default
 * data adapter property
 * 
 * @author Orlandin Marco
 *
 */
public class SelectDefaultDatasetWizard extends Wizard {

	/**
	 * The current dataset
	 */
	private JRDataset dataset;
	
	/**
	 * The current JasperReportsConfiguration
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * The page with the controls
	 */
	private SelectDefaultDatasetPage page0;
	
	/**
	 * The section where the command is executed
	 */
	private AbstractSection section;
	
	/**
	 * Create the wizard 
	 * 
	 * @param node The current report, the default data adapter property will be set on the main dataset, 
	 * must be not null
	 * @param section The section where the command to set the property is executed, must be not null
	 */
	public SelectDefaultDatasetWizard(MReport node, AbstractSection section){
		dataset = node.getJasperDesign().getMainDataset(); 
		jConfig = node.getJasperConfiguration();
		this.section = section;
	}
	
	/**
	 * Create the wizard 
	 * 
	 * @param node The current dataset, the default data adapter property will be set on this dataset, 
	 * must be not null
	 * @param section The section where the command to set the property is executed, must be not null
	 */
	public SelectDefaultDatasetWizard(MDataset node, AbstractSection section){
		dataset = node.getValue();
		jConfig = node.getJasperConfiguration();
		this.section = section;
	}
	
	@Override
	public void addPages() {
		String location = dataset.getPropertiesMap().getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
		page0 = new SelectDefaultDatasetPage(jConfig, location);
		addPage(page0);
	}
	
	/**
	 * Get the information from the page and create the command to set the property
	 */
	@Override
	public boolean performFinish() {
		String result = page0.getDataAdapterPath();
		String location = dataset.getPropertiesMap().getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
		if (!ModelUtils.safeEquals(location, result)){
			SetPropertyValueCommand newCommand = new SetPropertyValueCommand(dataset.getPropertiesMap(), 
																																			 DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION, 
																																			 result);
			CommandStack cs = section.getEditDomain().getCommandStack();;
			cs.execute(newCommand);
		}
		return true;
	}
}
