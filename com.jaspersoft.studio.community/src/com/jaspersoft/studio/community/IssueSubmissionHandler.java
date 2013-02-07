/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.community;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;

import com.jaspersoft.studio.community.messages.Messages;
import com.jaspersoft.studio.community.wizards.IssueCreationWizard;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * Creates the Issue submission dialog and opens it. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class IssueSubmissionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IssueCreationWizard newWizard = IssueCreationWizard.createWizard();
		if(newWizard!=null){
			newWizard.setNeedsProgressMonitor(true);
			WizardDialog issueCreationDialog = new WizardDialog(UIUtils.getShell(), newWizard){
				@Override
				protected void setShellStyle(int newShellStyle) {
					super.setShellStyle(SWT.DIALOG_TRIM | SWT.MODELESS);
				}
			};
			issueCreationDialog.open();
		}
		else {
			MessageDialog.openWarning(UIUtils.getShell(),
					Messages.IssueSubmissionHandler_WarningTitle,
					Messages.IssueSubmissionHandler_WarningMsg);
		}
		return null;
	}

}
