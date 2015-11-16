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
package com.jaspersoft.studio.book.wizards;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.model.subreport.command.wizard.NewSubreportPage;

/**
 * Wizard page to add a page part to a JRBook, it allow to select
 * a report from the workspace or create a new one on the fly
 * 
 * @author Orlandin Marco
 */
public class AddPageWizardPage extends NewSubreportPage {

	public AddPageWizardPage() {
		super(); //$NON-NLS-1$
		setTitle(Messages.AddPageWizardPage_pageTitle);
		setDescription(Messages.AddPageWizardPage_pageDescription);
	}
	
	public void handleDataChanged() {
		setErrorMessage(null);
		setMessage(Messages.AddPageWizardPage_pageDescription);
		if (radioButtonUseReport.getSelection()) {
			boolean complete = !(subreportExpressionEditor.getExpression() == null || subreportExpressionEditor.getExpression().getText().isEmpty());
			if (!complete) {
				setErrorMessage(Messages.AddPageWizardPage_errorExpression);
			}
			setPageComplete(complete);
		}
		storeSettings();
		fireChangeEvent();
	}
	
	/**
	 * Dosen't show the empty button, on the book report 
	 */
	@Override
	protected void createEmptyButton(Composite composite) {
	}
}
