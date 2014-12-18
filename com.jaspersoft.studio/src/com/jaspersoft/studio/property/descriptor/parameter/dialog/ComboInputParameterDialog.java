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
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * This dialog is used to define a pair of parameter name and expression for 
 * the parameter of a dataset run. since the parameter must match an existing parameter
 * inside the original dataset then the selection of the name can be made only between
 * a fixed set of parameters
 * 
 * @author Orlandin Marco
 *
 */
public class ComboInputParameterDialog  extends InputParameterDialog {
	
	/**
	 * The list of name to show in the combo
	 */
	private List<String> availableNames;
	
	/**
	 * Style for the combo
	 */
	private int style_bit;
	
	/**
	 * Create the dialog for an edit operation the combo to select the parameter name will be read only
	 * 
	 * @param parentShell the shell
	 * @param editedParameter the parameter to edit
	 * @param availableNames a not null list of the available names for the parameter
	 */
	public ComboInputParameterDialog(Shell parentShell, List<String> availableNames, GenericJSSParameter editedParameter) {
		this(parentShell, availableNames, editedParameter, SWT.READ_ONLY);
	}
	
	/**
	 * Create the dialog for an edit operation, the combo to select the parameter name will have a user defined style bit
	 * 
	 * @param parentShell the shell
	 * @param editedParameter the parameter to edit
	 * @param availableNames a not null list of the available names for the parameter
	 * @param style_bit the style bit for the combo
	 */
	public ComboInputParameterDialog(Shell parentShell, List<String> availableNames, GenericJSSParameter editedParameter, int style_bit) {
		super(parentShell, editedParameter , new ArrayList<GenericJSSParameter>());
		this.availableNames = availableNames;
		this.style_bit = style_bit;
	}
	
	/**
	 * Create the dialog for the creation of a new parameter
	 * 
	 * @param parentShell the shell
	 * @param availableNames list of the parameter names to show inside the combo
	 */
	public ComboInputParameterDialog(Shell parentShell, List<String> availableNames) {
		this(parentShell, availableNames, new GenericJSSParameter());
	}
	

	@Override
	protected String getNameFromControl() {
		return ((Combo)parameterName).getText();
	}
	
	@Override
	protected void setNameOnControl(String name) {
		if (name != null && !name.trim().isEmpty()){
			Combo cobmoName = (Combo)parameterName;
			int selectionIndex = availableNames.indexOf(name);
			if (selectionIndex == -1 && resultParameter.getName() != null) {
				availableNames.add(0,name);
				cobmoName.setItems(availableNames.toArray(new String[availableNames.size()]));
				cobmoName.select(0);
			} else {
				cobmoName.select(selectionIndex);
			}
		}
	}
	
	@Override
	protected Control getParameterNameControl(Composite parent) {
		final Combo comboParamterName = new Combo(parent, style_bit);
		comboParamterName.setItems(availableNames.toArray(new String[availableNames.size()]));
		comboParamterName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comboParamterName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (availableNames.size() == 0 || comboParamterName.getSelectionIndex() == -1){
					getButton(IDialogConstants.OK_ID).setEnabled(false);
				} else {
					getButton(IDialogConstants.OK_ID).setEnabled(true);
				}
			}
		});
		return comboParamterName;
	}
	
	@Override
	protected void configureNameControl() {
		((Combo)parameterName).addModifyListener(widgetModified);
	}
}

