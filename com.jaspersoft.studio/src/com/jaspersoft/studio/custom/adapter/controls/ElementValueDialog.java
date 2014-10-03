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
package com.jaspersoft.studio.custom.adapter.controls;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class ElementValueDialog extends Dialog {

	private String type;
	
	private String previousValue;
	
	private String newValue;
	
	private Control createdControl;
	
	public ElementValueDialog(Shell parentShell, String previousValue, String type) {
		super(parentShell);
		this.type = type;
		this.previousValue = previousValue;
	}
	
	/**
	 * Configure Shell attributes like setText
	 */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (previousValue != null) shell.setText("Edit the old value");
		else shell.setText("Insert the new value");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		if (type.equals("int")){
			Spinner control = new Spinner(composite, SWT.BORDER);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			createdControl = control;
			if (previousValue != null) control.setSelection(Integer.parseInt(previousValue));
		} else if (type.equals("boolean")){
			Combo control = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
			control.setItems(new String[]{"true","false"});
			createdControl = control;
			if (previousValue != null) control.select(Boolean.parseBoolean(previousValue) ? 0 : 1);
		} else if (type.equals("float")){
			Text control = new Text(composite, SWT.BORDER);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			createdControl = control;
			control.addVerifyListener(new VerifyListener() {
				@Override
				public void verifyText(VerifyEvent e) {
					Text text = (Text) e.getSource();
					// get old text and create new text by using the
					// VerifyEvent.text
					final String oldS = text.getText();
					String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);
					boolean isFloat = true;
					try {
						Float.parseFloat(newS);
					} catch (NumberFormatException ex) {
						isFloat = false;
					}
					if (!isFloat)
						e.doit = false;
				}
			});
			if (previousValue != null) control.setText(previousValue);
		} else if (type.equals("string")){
			Text control = new Text(composite, SWT.BORDER);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			createdControl = control;
			if (previousValue != null) control.setText(previousValue);
		}
		return composite;
	}

	@Override
	protected void okPressed() {
		if (type.equals("float") || type.equals("string")){
			newValue = ((Text)createdControl).getText();
		} else if (type.equals("int")){
			newValue = String.valueOf(((Spinner)createdControl).getSelection());
		} else if (type.equals("boolean")){
			Combo combo = (Combo)createdControl;
			newValue = Boolean.toString(combo.getSelectionIndex() == 0);
		}
		super.okPressed();
	}
	
	public String getReturnValue(){
		return newValue;
	}
}
