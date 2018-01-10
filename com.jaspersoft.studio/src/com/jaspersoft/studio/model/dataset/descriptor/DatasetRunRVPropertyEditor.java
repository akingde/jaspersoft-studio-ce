/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.dataset.descriptor;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ReturnValue;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.descriptor.returnvalue.ReturnValueContainer;

/**
 * Wizard to edit the return parameters of a dataset run. If there is some 
 * inconsistencies with the variables on the main or on the subdataset it profose to fix them
 * 
 * @author Orlandin Marco
 *
 */
public class DatasetRunRVPropertyEditor extends Wizard {
	
	/**
	 * The edit dataset run
	 */
	private MDatasetRun mdatasetrun;
	
	/**
	 * The page where the user can edit\add\delete the parameters
	 */
	private DatasetRunRVPropertyPage page0;
	
	/**
	 * Create the wizard to edit the return parameters of a dataset run
	 *  
	 * @param mdatasetrun the edit dataset run
	 */
	public DatasetRunRVPropertyEditor(MDatasetRun mdatasetrun) {
		super();
		setWindowTitle(Messages.common_properties);
		setNeedsProgressMonitor(false);
		this.mdatasetrun = mdatasetrun;
	}
	
	/**
	 * Return the list of parameters after the change made from the user
	 * 
	 * @return a not null list of return parameters to set inside the dataset run
	 */
	public List<ReturnValue> getValue() {
		return ReturnValueContainer.convertToDatasetRun(page0.getValue());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addPages() {
		page0 = new DatasetRunRVPropertyPage(mdatasetrun);
		List<ReturnValue> returnValues = (List<ReturnValue>)mdatasetrun.getPropertyValue(JRDesignDatasetRun.PROPERTY_RETURN_VALUES);
		page0.setValue(ReturnValueContainer.convertFromDatasetRunReturn(returnValues));
		addPage(page0);
	}
	
	/**
	 * Check if in the main dataset or in the subdataset associated with the dataset
	 * run there are missing variables
	 * 
	 * @return true if some variables are missing and the report will not compile, false otherwise
	 */
	private boolean hasMissingVariables(){
		JasperDesign design = mdatasetrun.getJasperDesign();
		JRDesignDataset mainDataset = (JRDesignDataset)design.getMainDataset();
		String subDatasetName = mdatasetrun.getValue().getDatasetName();
		JRDesignDataset subDataset = (JRDesignDataset)design.getDatasetMap().get(subDatasetName);
				
		for(ReturnValueContainer container : page0.getValue()){
			//Check the from variable
			String fromVariable = container.getFromVariable();
			if (!subDataset.getVariablesMap().containsKey(fromVariable)){
				return true;
			}
				
			//Check the to variable
			String toVariable = container.getToVariable();
			if (!mainDataset.getVariablesMap().containsKey(toVariable)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if in the main dataset or in the subdataset associated with the dataset
	 * run there are missing variables. When a missing variable is found then it is created
	 * where needed
	 */
	private void addMissingVariables(){
			JasperDesign design = mdatasetrun.getJasperDesign();
			JRDesignDataset mainDataset = (JRDesignDataset)design.getMainDataset();
			String subDatasetName = mdatasetrun.getValue().getDatasetName();
			JRDesignDataset subDataset = (JRDesignDataset)design.getDatasetMap().get(subDatasetName);
					
			for(ReturnValueContainer container : page0.getValue()){
				//Check the from variable
				String fromVariable = container.getFromVariable();
				if (!subDataset.getVariablesMap().containsKey(fromVariable)){
					JRDesignVariable newVariable = new JRDesignVariable();
					newVariable.setName(fromVariable);
					try {
						subDataset.addVariable(newVariable);
					} catch (JRException e) {
						e.printStackTrace();
					}
				}
					
				//Check the to variable
				String toVariable = container.getToVariable();
				if (!mainDataset.getVariablesMap().containsKey(toVariable)){
					JRDesignVariable newVariable = new JRDesignVariable();
					newVariable.setName(toVariable);
					try {
						mainDataset.addVariable(newVariable);
					} catch (JRException e) {
						e.printStackTrace();
					}
				}
			}
	}

	@Override
	public boolean performFinish() {
		if (hasMissingVariables()){
			//propose the fix
			boolean create_variables = UIUtils.showConfirmation(Messages.DatasetRunRVPropertyEditor_missingTitle, Messages.DatasetRunRVPropertyEditor_missingDescription);
			if (create_variables){
				addMissingVariables();
			}
		}
		return true;
	}

}
