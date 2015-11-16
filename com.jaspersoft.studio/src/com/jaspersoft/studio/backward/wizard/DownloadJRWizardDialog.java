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

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.messages.Messages;

/**
 * Wizard dialog used to manage the installed version of the JR used 
 * to compile the JRXML with the older version
 * 
 * @author Orlandin Marco
 *
 */
public class DownloadJRWizardDialog extends WizardDialog {
	
	public DownloadJRWizardDialog(Shell parentShell) {
		super(parentShell, new DownloadJRWizard());
		((DownloadJRWizard)getWizard()).setNeedsProgressMonitor(true);
		setTitle(Messages.DownloadJRWizardDialog_dialogTitle);
	}
	
	/**
	 * When the next button is pressed the thread to perform the operation
	 * is run
	 */
	@Override
	protected void nextPressed() {
		((DownloadJRWizard)getWizard()).nextPressed(this);
	}
	
	/**
	 * When the thread is completed this method can be called to force
	 * the wizard to go to the summary page
	 */
	protected void threadCompleted(){
		super.nextPressed();
	}

}
