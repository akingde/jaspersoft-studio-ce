/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.dataset;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapterParameterContributorFactory;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRDataset;

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
	private String location;
	
	/**
	 * File of the opened report used as context to resolve the workspace
	 * paths
	 */
	private IFile context;
	
	/**
	 * The page with the controls
	 */
	private SelectDefaultDatasetPage page0;
	
	
	private String newValue = null;
	
	/**
	 * Create the wizard 
	 * 
	 * @param node The current report, the default data adapter property will be set on the main dataset, 
	 * must be not null
	 */
	public SelectDefaultDatasetWizard(MReport node){
		JRDataset dataset = node.getJasperDesign().getMainDataset(); 
		location = dataset.getPropertiesMap().getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
		JasperReportsConfiguration jConfig = node.getJasperConfiguration();
		context = (IFile) jConfig.get(FileUtils.KEY_FILE);
	}
	
	/**
	 * Create the wizard 
	 * 
	 * @param node The current dataset, the default data adapter property will be set on this dataset, 
	 * must be not null
	 */
	public SelectDefaultDatasetWizard(MDataset node){
		JRDataset dataset = node.getValue();
		location = dataset.getPropertiesMap().getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
		JasperReportsConfiguration jConfig = node.getJasperConfiguration();
		context = (IFile) jConfig.get(FileUtils.KEY_FILE);
	}
	
	/**
	 * Create the wizard 
	 * 
	 * @param location current value of the jr default data adapter property
	 * @param context File of the opened report used as context to resolve the workspace
	 * paths
	 */
	public SelectDefaultDatasetWizard(String location, IFile context) {
		this.location = location;
		this.context = context;
	}
	
	@Override
	public void addPages() {
		newValue = location;
		page0 = new SelectDefaultDatasetPage(context, location);
		addPage(page0);
	}
	
	/**
	 * Get the information from the page and create the command to set the property
	 */
	@Override
	public boolean performFinish() {
		newValue = page0.getDataAdapterPath();
		return true;
	}
	
	public String getValue(){
		return newValue;
	}
}
