/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.dataset.descriptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.ReturnValue;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.dialogs.Dialog;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.descriptor.returnvalue.EditableInputReturnValueDialog;
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
	 * Create a page to handle the return values of a dataset run
	 * 
	 * @param datasetRun
	 *          the dataset run to edit
	 */
	public DatasetRunRVPropertyPage(MDatasetRun datasetRun) {
		super("datasetrunproperties", datasetRun != null ? datasetRun.getJasperDesign() : null);
		if (datasetRun != null) {
			this.datasetRun = datasetRun.getValue();
		}
		setTitle(Messages.DatasetRunRVPropertyPage_pageTitle);
		setDescription(Messages.DatasetRunRVPropertyPage_pageDescription);
	}

	/**
	 * Create a page to handle the return values of a dataset run
	 * 
	 * @param datasetRun
	 *          the jr dataset run to edit
	 * @param design
	 *          the current design of the report
	 */
	public DatasetRunRVPropertyPage(JRDesignDatasetRun datasetRun, JasperDesign design) {
		super("datasetrunproperties", design);
		this.datasetRun = datasetRun;
		setTitle(Messages.DatasetRunRVPropertyPage_pageTitle);
		setDescription(Messages.DatasetRunRVPropertyPage_pageDescription);
	}

	/**
	 * Return the list of jrvariables defined inside the dataset run
	 */
	@Override
	public JRVariable[] getDatasetVariables() {
		if (datasetRun == null)
			return new JRVariable[0];
		String datasetName = datasetRun.getDatasetName();
		JRDataset dataset = design.getDatasetMap().get(datasetName);
		if (dataset != null)
			return dataset.getVariables();
		return new JRVariable[0];
	}

	@Override
	protected void openAddDialog() {
		ReturnValueContainer container = new ReturnValueContainer();
		HashSet<String> invalidTo = new HashSet<String>();
		HashSet<String> invalidFrom = new HashSet<String>();
		for (ReturnValueContainer definedValues : getValue()) {
			invalidTo.add(definedValues.getToVariable());
			invalidFrom.add(definedValues.getFromVariable());
		}
		EditableInputReturnValueDialog inputDialog = new EditableInputReturnValueDialog(getShell(), container,
				getToVariablesNames(), getFromVariablesNames(), invalidTo, invalidFrom);
		if (inputDialog.open() == Dialog.OK) {
			addElemenet(container);
		}
	}

	@Override
	protected void openEditDialog(ReturnValueContainer edited) {
		ReturnValueContainer result = edited.clone();
		String[] toVariables = getVariablesPlusElement(getToVariablesNames(), edited.getToVariable());
		String[] fromVariables = getVariablesPlusElement(getFromVariablesNames(), edited.getFromVariable());
		HashSet<String> invalidTo = new HashSet<String>();
		HashSet<String> invalidFrom = new HashSet<String>();
		for (ReturnValueContainer definedValues : getValue()) {
			if (edited != definedValues) {
				invalidTo.add(definedValues.getToVariable());
				invalidFrom.add(definedValues.getFromVariable());
			}
		}
		EditableInputReturnValueDialog inputDialog = new EditableInputReturnValueDialog(getShell(), result, toVariables,
				fromVariables, invalidTo, invalidFrom);
		if (inputDialog.open() == Dialog.OK) {
			editElement(edited, result);
		}
	}

	/*
	 * @Override protected void addElemenet(ReturnValueContainer container) { super.addElemenet(container);
	 * 
	 * //Check the from variable String fromVariable = container.getFromVariable(); String[] fromVariables =
	 * getFromVariablesNames(); if (!ArrayUtils.contains(fromVariables, fromVariable) && datasetRun != null){ //Need to
	 * add the variable to the subdataset String datasetName = datasetRun.getDatasetName(); JRDesignDataset dataset =
	 * (JRDesignDataset)design.getDatasetMap().get(datasetName); JRDesignVariable newVariable = new JRDesignVariable();
	 * newVariable.setName(fromVariable); try { dataset.addVariable(newVariable); } catch (JRException e) {
	 * e.printStackTrace(); } }
	 * 
	 * //Check the to variable String toVariable = container.getToVariable(); String[] toVariables =
	 * getToVariablesNames(); if (!ArrayUtils.contains(toVariables, toVariable) && design != null){ //Need to add the
	 * variable to the main dataset JRDesignDataset dataset = (JRDesignDataset)design.getMainDataset(); JRDesignVariable
	 * newVariable = new JRDesignVariable(); newVariable.setName(toVariable); try { dataset.addVariable(newVariable); }
	 * catch (JRException e) { e.printStackTrace(); } } }
	 * 
	 * @Override protected void editElement(ReturnValueContainer edited, ReturnValueContainer newValue) {
	 * super.editElement(edited, newValue);
	 * 
	 * //Check the from variable String fromVariable = newValue.getFromVariable(); String[] fromVariables =
	 * getVariablesPlusElement(getFromVariablesNames(), edited.getFromVariable()); if (!ArrayUtils.contains(fromVariables,
	 * fromVariable) && datasetRun != null){ //Need to add the variable to the subdataset String datasetName =
	 * datasetRun.getDatasetName(); JRDesignDataset dataset = (JRDesignDataset)design.getDatasetMap().get(datasetName);
	 * JRDesignVariable newVariable = new JRDesignVariable(); newVariable.setName(fromVariable); try {
	 * dataset.addVariable(newVariable); } catch (JRException e) { e.printStackTrace(); } }
	 * 
	 * //Check the to variable String toVariable = newValue.getToVariable(); String[] toVariables =
	 * getVariablesPlusElement(getToVariablesNames(), edited.getToVariable()); if (!ArrayUtils.contains(toVariables,
	 * toVariable) && design != null){ //Need to add the variable to the main dataset JRDesignDataset dataset =
	 * (JRDesignDataset)design.getMainDataset(); JRDesignVariable newVariable = new JRDesignVariable();
	 * newVariable.setName(toVariable); try { dataset.addVariable(newVariable); } catch (JRException e) {
	 * e.printStackTrace(); } } }
	 */

	/**
	 * Update the status of the buttons enabling or disabling them if there are input errors
	 */
	protected void updateButtonsStatus() {
		if (design != null) {
			JRDesignDataset mainDataset = (JRDesignDataset) design.getMainDataset();
			String datasetName = datasetRun.getDatasetName();
			JRDesignDataset subDataset = (JRDesignDataset) design.getDatasetMap().get(datasetName);

			for (ReturnValueContainer container : getValue()) {
				// Check the from variable
				String fromVariable = container.getFromVariable();
				if (subDataset != null && !subDataset.getVariablesMap().containsKey(fromVariable)) {
					setMessage("Some From variables are missing on the subdataset and will be possible to create them at the end of the wizard");
					return;
				}

				// Check the to variable
				String toVariable = container.getToVariable();
				if (!mainDataset.getVariablesMap().containsKey(toVariable)) {
					setMessage("Some To variables are missing on the main dataset and will be possible to create them at the end of the wizard");
					return;
				}
			}
		}
		setMessage(null);
	}

	/**
	 * Change the dataset run handled by this page. When this method is called the table showing the return values it is
	 * reinitialized with the information from the new dataset run
	 * 
	 * @param datasetRun
	 *          the jr dataset run to edit
	 * @param design
	 *          the current design of the report
	 */
	public void setDatasetRun(JRDesignDatasetRun datasetRun, JasperDesign design) {
		this.design = design;
		this.datasetRun = datasetRun;
		updateDatasetRunReturnValues();
	}

	/**
	 * Take the information on the return values from the current dataset run and show them inside the table
	 */
	private void updateDatasetRunReturnValues() {
		if (datasetRun != null) {
			List<ReturnValue> returnValues = datasetRun.getReturnValuesList();
			setValue(ReturnValueContainer.convertFromDatasetRunReturn(returnValues));
		}
	}

	/**
	 * Take the information on the return values from the table and save them inside the dataset run
	 */
	public void saveValuesIntoDataset() {
		if (datasetRun != null) {
			List<ReturnValueContainer> actaulValues = getValue();
			List<ReturnValue> newValues = ReturnValueContainer.convertToDatasetRun(actaulValues);
			List<ReturnValue> oldValues = new ArrayList<ReturnValue>(datasetRun.getReturnValuesList());
			// The element dosen't allow to swap the list, must remove the old element
			for (ReturnValue oldValue : oldValues) {
				datasetRun.removeReturnValue(oldValue);
			}
			// and add the new one
			for (ReturnValue newValue : newValues) {
				datasetRun.addReturnValue(newValue);
			}
		}
	}
}
