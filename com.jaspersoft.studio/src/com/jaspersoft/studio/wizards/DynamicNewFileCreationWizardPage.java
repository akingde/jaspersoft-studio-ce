/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards;

import net.sf.jasperreports.eclipse.builder.jdt.JDTUtils;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.category.ReportTemplatesWizardPage;

/**
 * 
 * Wizard page to select the destination of a report file. It dynamically 
 * concatenate the next page to provide a dynamic new report wizard
 * 
 * @author Orlandin Marco
 *
 */
public class DynamicNewFileCreationWizardPage extends NewFileCreationWizardPage {

	public DynamicNewFileCreationWizardPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}
	
	/**
	 * The next page of this is given from the template engine to provide a dynamic new report wizard
	 */
	@Override
	public IWizardPage getNextPage() {
		// get from the first step the TemplateBundle and read from it the dynamic steps and return the first one
		ReportTemplatesWizardPage firstStaticStep = ((ReportNewWizard)getWizard()).getTemplateChooserStep();
		WizardPage[] pages = firstStaticStep.getTemplateBundle().getCustomWizardPages();
		//If there are no dynamic pages then go to the congratulation. One of this cases is valid since
		//the wizard can adavance if there is a dynamic page or if there is a congratulation
		if (pages.length > 0){
			WizardPage firstDynamicStep = firstStaticStep.getTemplateBundle().getCustomWizardPages()[0];
			firstDynamicStep.setWizard(getWizard());
			return firstDynamicStep;
		} else {
			CongratulationsWizardPage congratPage = ((ReportNewWizard)getWizard()).getCongratulationsStep();
			congratPage.setWizard(getWizard());
			return congratPage;
		}
 		
	}
	
	/**
	 * The next page of this is given from the template engine to provide a dynamic new report wizard
	 */
	@Override
	public boolean canFlipToNextPage() {
		if(JDTUtils.isVirtualResource(getContainerFullPath())) {
			setErrorMessage(Messages.DynamicNewFileCreationWizard_VirtualFolderError);
			return false;
		}
		ReportNewWizard container = (ReportNewWizard)getWizard();
		ReportTemplatesWizardPage firstStaticStep = container.getTemplateChooserStep();
		if ( firstStaticStep.getTemplateBundle() != null){
			WizardPage[] pages = firstStaticStep.getTemplateBundle().getCustomWizardPages();
			return  isPageComplete() && (pages.length>0 || container.hasCongratulationStep());
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isPageComplete() {
		return !JDTUtils.isVirtualResource(getContainerFullPath()) && super.isPageComplete();
	}
	
	@Override
	public void setVisible(boolean visible) {
		JDTUtils.deactivateLinkedResourcesSupport(visible);
		super.setVisible(visible);
	}

}
