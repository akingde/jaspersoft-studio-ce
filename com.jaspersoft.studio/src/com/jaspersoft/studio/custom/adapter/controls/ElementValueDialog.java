/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.custom.adapter.controls;

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

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;

/**
 * Dialog used to edit or add a value to a list of value
 * 
 * @author Orlandin Marco
 *
 */
public class ElementValueDialog extends PersistentLocationDialog {

	/**
	 * Type of the value, the controls created inside the dialog depends on the type.
	 * Valid types are int, float, string and boolean
	 */
	private String type;
	
	/**
	 * The old value if the dialog is used to edit
	 */
	private String previousValue;
	
	/**
	 * The new value
	 */
	private String newValue;
	
	/**
	 * The control created to add or edit the value
	 */
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
		if (previousValue != null) shell.setText(Messages.ElementValueDialog_editText);
		else shell.setText(Messages.ElementValueDialog_newText);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		//Check the type and create the appropriate control
		if (type.equals("int")){ //$NON-NLS-1$
			Spinner control = new Spinner(composite, SWT.BORDER);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			createdControl = control;
			if (previousValue != null) control.setSelection(Integer.parseInt(previousValue));
		} else if (type.equals("boolean")){ //$NON-NLS-1$
			Combo control = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
			control.setItems(new String[]{"true","false"}); //$NON-NLS-1$ //$NON-NLS-2$
			createdControl = control;
			if (previousValue != null) control.select(Boolean.parseBoolean(previousValue) ? 0 : 1);
		} else if (type.equals("float")){ //$NON-NLS-1$
			Text control = new Text(composite, SWT.BORDER);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			createdControl = control;
			//Listener to validate the value as float
			control.addVerifyListener(new VerifyListener() {
				@Override
				public void verifyText(VerifyEvent e) {
					Text text = (Text) e.getSource();
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
		} else if (type.equals("string")){ //$NON-NLS-1$
			Text control = new Text(composite, SWT.BORDER);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			createdControl = control;
			if (previousValue != null) control.setText(previousValue);
		}
		return composite;
	}
	
	/**
	 * When the ok button is pressed the value inserted by the user is 
	 * converted into a string and stored
	 */
	@Override
	protected void okPressed() {
		if (type.equals("float") || type.equals("string")){ //$NON-NLS-1$ //$NON-NLS-2$
			newValue = ((Text)createdControl).getText();
		} else if (type.equals("int")){ //$NON-NLS-1$
			newValue = String.valueOf(((Spinner)createdControl).getSelection());
		} else if (type.equals("boolean")){ //$NON-NLS-1$
			Combo combo = (Combo)createdControl;
			newValue = Boolean.toString(combo.getSelectionIndex() == 0);
		}
		super.okPressed();
	}
	
	/**
	 * Return the stored value
	 * 
	 * @return the stored value, could be null
	 */
	public String getReturnValue(){
		return newValue;
	}
}
