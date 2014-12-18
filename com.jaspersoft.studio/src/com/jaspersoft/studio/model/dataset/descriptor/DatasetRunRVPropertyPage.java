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
package com.jaspersoft.studio.model.dataset.descriptor;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.ReturnValue;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.descriptor.returnvalue.RVPropertyPage;
import com.jaspersoft.studio.property.descriptor.returnvalue.ReturnValueContainer;

/**
 * Return values configuration page for a datasaet run element
 * 
 * @author Orlandin Marco
 *
 */
public class DatasetRunRVPropertyPage extends RVPropertyPage {

	/**
	 * The handled dataset run
	 */
	private JRDesignDatasetRun datasetRun;
	
	/**
	 * The design where the dataset associated to the dataset run is defined
	 */
	private JasperDesign design;
	
	/**
	 * Create a page to handle the return values of a dataset run
	 * 
	 * @param datasetRun the dataset run to edit
	 */
	public DatasetRunRVPropertyPage(MDatasetRun datasetRun) {
		super("datasetrunproperties");
		if (datasetRun != null){
			this.datasetRun = datasetRun.getValue();
			this.design = datasetRun.getJasperDesign();
		}
		setTitle(Messages.DatasetRunRVPropertyPage_pageTitle);
		setDescription(Messages.DatasetRunRVPropertyPage_pageDescription);
	}
	
	/**
	 * Create a page to handle the return values of a dataset run
	 * 
	 * @param datasetRun the jr dataset run to edit
	 * @param design the current design of the report
	 */
	public DatasetRunRVPropertyPage(JRDesignDatasetRun datasetRun, JasperDesign design) {
		super("datasetrunproperties");
		this.datasetRun = datasetRun;
		this.design = design;
		setTitle(Messages.DatasetRunRVPropertyPage_pageTitle);
		setDescription(Messages.DatasetRunRVPropertyPage_pageDescription);
	}
	
	/**
	 * Return the list of jrvariables defined inside the dataset run
	 */
	@Override
	public JRVariable[] getVariables() {
		if (datasetRun == null) return new JRVariable[0];
		String datasetName = datasetRun.getDatasetName();
		JRDataset dataset = design.getDatasetMap().get(datasetName);
		JRVariable[] vlist = dataset.getVariables();
		return vlist;
	}
	
	/**
	 * Change the dataset run handled by this page. When this 
	 * method is called the table showing the return values it
	 * is reinitialized with the information from the new dataset
	 * run
	 * 
	 * @param datasetRun the jr dataset run to edit
	 * @param design the current design of the report
	 */
	public void setDatasetRun(JRDesignDatasetRun datasetRun, JasperDesign design){
		this.design = design;
		this.datasetRun = datasetRun;
		updateDatasetRunReturnValues();
	}
	
	/**
	 * Take the information on the return values from the current dataset
	 * run and show them inside the table
	 */
	private void updateDatasetRunReturnValues(){
		if (datasetRun != null){
			List<ReturnValue> returnValues = datasetRun.getReturnValuesList();
			setValue(ReturnValueContainer.convertFromDatasetRunReturn(returnValues));
		}
	}
	
	/**
	 * Take the information on the return values from the table and save 
	 * them inside the dataset run
	 */
	public void saveValuesIntoDataset(){
		if (datasetRun != null){
			List<ReturnValueContainer> actaulValues = getValue();
			List<ReturnValue> newValues = ReturnValueContainer.convertToDatasetRun(actaulValues);
			List<ReturnValue> oldValues = new ArrayList<ReturnValue>(datasetRun.getReturnValuesList());
			//The element dosen't allow to swap the list, must remove the old element 
			for(ReturnValue oldValue : oldValues){
				datasetRun.removeReturnValue(oldValue);
			}
			//and add the new one
			for(ReturnValue newValue : newValues){
				datasetRun.addReturnValue(newValue);
			}
		}
	}
}
