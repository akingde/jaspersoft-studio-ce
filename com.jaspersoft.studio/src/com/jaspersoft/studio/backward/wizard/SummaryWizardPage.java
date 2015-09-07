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
package com.jaspersoft.studio.backward.wizard;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Page used to show the summary of the download operation
 * 
 * @author Orlandin Marco
 *
 */
public class SummaryWizardPage extends JSSWizardPage {

	/**
	 * Flag used to indicate that the operation was aborted
	 */
	private boolean abortedOperation = false;
	
	/**
	 * FLag used to indicate there was an error during the operation
	 */
	private boolean error = false;
	
	/**
	 * Label where the message is printed
	 */
	private Label infoLabel;
	
	protected SummaryWizardPage() {
		super("summaryPage"); //$NON-NLS-1$
		setTitle(Messages.SummaryWizardPage_title);
		setMessage(Messages.SummaryWizardPage_message);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1,false));
		infoLabel = new Label(container, SWT.WRAP);
		infoLabel.setLayoutData(new GridData(GridData.FILL_BOTH));
		updateLabelMessage();
		setControl(container);
	}

	/**
	 * Update the label content accordingly to the error or 
	 * operation aborted flags
	 */
	private void updateLabelMessage(){
		//Since this is called from outside it must be executed inside a graphic thread
		UIUtils.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (abortedOperation || error){
					setErrorMessage(Messages.SummaryWizardPage_message);
				}
				if (!error && !abortedOperation) setErrorMessage(null);
				if (infoLabel != null && !infoLabel.isDisposed()){
					if (abortedOperation){
						infoLabel.setText(Messages.SummaryWizardPage_operationAborted);
					} else if (error){
						infoLabel.setText(Messages.SummaryWizardPage_operationError);
					} else {
						infoLabel.setText(Messages.SummaryWizardPage_operationCompleted);
					}
					
				}
			}
		});
	}
	
	/**
	 * Set if the current operation was aborted. It will
	 * update the content of the page
	 * 
	 * @param value true if the operation was aborted, false otherwise
	 */
	public void setAborted(boolean value){
		abortedOperation = value;
		updateLabelMessage();
	}
	
	/**
	 * Set if there was an error during current operation. It will
	 * update the content of the page
	 * 
	 * @param value true if there was an error, false otherwise
	 */
	public void setError(boolean value){
		error = true;
		updateLabelMessage();
	}

	@Override
	public boolean isPageComplete() {
		return true;
	}
	
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_BACKWARD_COMPILER;
	}
}
