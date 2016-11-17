/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.returnvalue;

import java.util.HashSet;

import net.sf.jasperreports.engine.type.CalculationEnum;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.messages.Messages;

/**
 * Dialog to provide the configuration of a dataset run return value, allow both to define
 * new value or selecting from avaialable ones using two editable combos
 * 
 * @author Orlandin Marco
 *
 */
public class EditableInputReturnValueDialog extends InputReturnValueDialog {
	
	/**
	 * Available values for the from variable field
	 */
	private String[] fromVariables;
	
	/**
	 * Combo where the from values are shown
	 */
	private Combo fromVariableCombo;
	
	/**
	 * Hash set of values that are invalid to value
	 */
	private HashSet<String> invalidTo;
	
	/**
	 * Hash set of values that are invalid to value
	 */
	private HashSet<String> invalidFrom;
	
	/**
	 * Create the dialog
	 * 
	 * @param parentShell the parent shell
	 * @param rvContainer a not null container, the widget will be initialized with the content
	 * of this container, useful for edit operations and the output will be stored also inside this value
	 * @param toVariables list of suggested to variables in the combo
	 * @param fromVariables list of suggested from variables in the combo
	 * @param invalidTo invalid values for a to variable
	 * @param inavlidFrom invalid values for a from variable
	 */
	public EditableInputReturnValueDialog(Shell parentShell, ReturnValueContainer rvContainer, String[] toVariables, String[] fromVariables, 
																					HashSet<String> invalidTo, HashSet<String> inavlidFrom) {
		super(parentShell, rvContainer, toVariables);
		this.fromVariables = fromVariables != null ? fromVariables : new String[0];
		this.invalidTo = invalidTo != null ? invalidTo : new HashSet<String>();
		this.invalidFrom = inavlidFrom != null ? inavlidFrom : new HashSet<String>();
	}
	
	/**
	 * Create the from control as a editable combo
	 */
	@Override
	protected void createFromVariable(Composite container){
		Label fromVariableLabel = new Label(container, SWT.NONE);
		fromVariableLabel.setText(Messages.RVPropertyPage_subreport_variable);
		
		fromVariableCombo = new Combo(container, SWT.NONE);
		fromVariableCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		fromVariableCombo.setItems(fromVariables);
	}
	
	/**
	 * Create the to control as a editable combo
	 */
	@Override
	protected void createToVariable(Composite container){
		Label toVariableLabel = new Label(container, SWT.NONE);
		toVariableLabel.setText(Messages.RVPropertyPage_to_variable);
		
		toVariable = new Combo(container, SWT.NONE);
		toVariable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		toVariable.setItems(toVariables);
	}
	
	/**
	 * Initialize the from and to control with the passed container and attach
	 * the modify listeners to them
	 */
	@Override
	protected void initializeVariables(){
		if (rvContainer.getToVariable() != null) {
			int index = ArrayUtils.indexOf(toVariables, rvContainer.getToVariable());
			if (index == ArrayUtils.INDEX_NOT_FOUND) index = 0;
			toVariable.select(index);
		} else {
			toVariable.select(0);
		}
		
		if (rvContainer.getFromVariable() != null) {
			int index = ArrayUtils.indexOf(fromVariables, rvContainer.getFromVariable());
			if (index == ArrayUtils.INDEX_NOT_FOUND) index = 0;
			fromVariableCombo.select(index);
		} else {
			fromVariableCombo.select(0);
		}
		
		toVariable.addModifyListener(widgetModified);
		fromVariableCombo.addModifyListener(widgetModified);
	}

	/**
	 * Save the value from the widget inside the container
	 */
	@Override
	protected void updateContainer(){
		rvContainer.setCalculation(CalculationEnum.values()[calculation.getSelectionIndex()]);
		rvContainer.setToVariable(toVariable.getText());
		rvContainer.setFromVariable(fromVariableCombo.getText());
		rvContainer.setIncrementerFactoryClassName(incrementText.getText());
		validate();
	}
	
	/**
	 * Check if the content of the widget is valid and enable\disable the ok button.
	 * The from and to value must be not empty and not the same of an invalid value
	 */
	@Override
	protected void validate(){
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null){
			String fromValue = fromVariableCombo.getText().trim();
			String toValue = toVariable.getText().trim();
			boolean validFrom = !fromValue.isEmpty() && !invalidFrom.contains(fromValue);
			boolean validTo = !toValue.isEmpty() && !invalidTo.contains(toValue);
			okButton.setEnabled(validFrom && validTo);
		}
	}
}
