/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards.group;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.templates.JrxmlTemplateBundle;
import com.jaspersoft.studio.wizards.CongratulationsWizardPage;
import com.jaspersoft.studio.wizards.ReportNewWizard;

/**
 * Page to choose the groups during the creation of a report template
 * 
 * @author Orlandin Marco
 *
 */
public class ReportWizardFieldsGroupByDynamicPage extends StaticWizardFieldsGroupByPage {
	
	/**
	 * The template bundle where this page is contained
	 */
	private JrxmlTemplateBundle containerBundle;

	/**
	 * Create the page
	 * 
	 * @param containerBundle The template bundle where this page is contained
	 */
	public ReportWizardFieldsGroupByDynamicPage(JrxmlTemplateBundle containerBundle) {
		super();
		this.containerBundle = containerBundle;
	}

	/**
	 * Return the congratulation page if it is available or the fallback page
	 * if this was a subwizard. If the fallback page is available it is not neccessary
	 * to set the wizard on it since it is supposed it has already it, since it was
	 * a wizard created externally. If both the fallback page and the congratulation page
	 * are available the fallback has the priority
	 * 
	 * @return the fallback page or the congratulation page or null if the congratulation page
	 * shouldn't be displayed
	 */
	@Override
	public IWizardPage getNextPage() {
		ReportNewWizard container = (ReportNewWizard)getWizard();
		if (container.getFallbackPage() != null) {
			IWizardPage fallback = container.getFallbackPage();
			return fallback;
		} else if (container.hasCongratulationStep()) {
			CongratulationsWizardPage congratPage = (container).getCongratulationsStep();
			congratPage.setWizard(getWizard());
			return congratPage;
		}
		return null;
	}
	
	/**
	 * Return the second dynamic page
	 * 
	 * @return the page to configure the fields
	 */
	@Override
	public IWizardPage getPreviousPage() {
		containerBundle.getStep2().setWizard(getWizard());
		return containerBundle.getStep2();
	}
	
	/**
	 * To advance to the next page the page must have a status complete
	 * and there must be the congratulation page or a fallback page
	 */
	@Override
	public boolean canFlipToNextPage() {
		ReportNewWizard container = (ReportNewWizard)getWizard();
		return isPageComplete() && (container.hasCongratulationStep() || container.getFallbackPage() != null);
	}
}
