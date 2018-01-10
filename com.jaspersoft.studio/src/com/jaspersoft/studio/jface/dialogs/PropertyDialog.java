/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jface.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;

public class PropertyDialog extends PersistentLocationDialog {
	
	private String[] propertyAndValue = null;
	private Text textProperty;
	private Text textValue;

	/**
	 * Create the PropertyDialog.
	 * @param parentShell
	 */
	public PropertyDialog(Shell parentShell, String[] propertyAndValue) {
		super(parentShell);
	  this.propertyAndValue = propertyAndValue;
	}

	/**
	 * Configure Shell attributes like setText
	 */
	@Override
	protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText(Messages.PropertyDialog_0);
  }
	
	/**
	 * Create contents of the PropertyDialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
	  // UI elements
		Composite container = (Composite) super.createDialogArea(parent);
		FillLayout fl_container = new FillLayout(SWT.HORIZONTAL);
		fl_container.marginWidth = 5;
		fl_container.marginHeight = 5;
		container.setLayout(fl_container);
		
		Group grpProperty = new Group(container, SWT.NONE);
		grpProperty.setText(Messages.PropertyDialog_1);
		grpProperty.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(grpProperty, SWT.NONE);
		lblNewLabel.setText(Messages.PropertyDialog_2);
		
		textProperty = new Text(grpProperty, SWT.BORDER);
		textProperty.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(grpProperty, SWT.NONE);
		lblNewLabel_1.setText(Messages.PropertyDialog_3);
		
		textValue = new Text(grpProperty, SWT.BORDER);
		textValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	  // UI elements listeners
		
	  // init UI elements values
		initElements();
		
		return container;
	}

	private void initElements() {
		
		if (propertyAndValue != null) {
			
			String property = propertyAndValue[0];
			if (property != null && property.length() > 0) {
				this.textProperty.setText(property);
			}
			
			String value = propertyAndValue[1];
			if (value != null && value.length() > 0) {
				this.textValue.setText(value);
			}
			
		}
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 170);
	}
	
	@Override
	protected void okPressed() {
		
		propertyAndValue = new String[]{textProperty.getText(), textValue.getText()};
		super.okPressed();
	}
	
	public String[] getPropertyAndValue() {
		return propertyAndValue;
	}
}
