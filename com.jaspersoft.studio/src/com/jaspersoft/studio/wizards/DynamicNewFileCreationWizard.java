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
package com.jaspersoft.studio.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;

import com.jaspersoft.studio.wizards.category.ReportTemplatesWizardPage;

/**
 * 
 * Wizard page to select the destination of a report file. It dynamically 
 * concatenate the next page to provide a dynamic new report wizard
 * 
 * @author Orlandin Marco
 *
 */
public class DynamicNewFileCreationWizard extends NewFileCreationWizard {

	public DynamicNewFileCreationWizard(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}
	
	/**
	 * The next page of this is given from the template engine to provide a dynamic new report wizard
	 */
	@Override
	public IWizardPage getNextPage() {
		// get from the first step the TemplateBundle and read from it the dynamic steps and return the first one
		ReportTemplatesWizardPage firstStaticStep = ((ReportNewWizard)getWizard()).getTemplateChooserStep();
		WizardPage firstDynamicStep = firstStaticStep.getTemplateBundle().getCustomWizardPages()[0];
		firstDynamicStep.setWizard(getWizard());
		return firstDynamicStep;
	}
	
	/**
	 * The next page of this is given from the template engine to provide a dynamic new report wizard
	 */
	@Override
	public boolean canFlipToNextPage() {
		ReportNewWizard container = (ReportNewWizard)getWizard();
		ReportTemplatesWizardPage firstStaticStep = container.getTemplateChooserStep();
		WizardPage[] pages = firstStaticStep.getTemplateBundle().getCustomWizardPages();
		return  isPageComplete() && (pages.length>0 || container.hasCongratulationStep());
	}

}
