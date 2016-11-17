/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards.fields;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.templates.JrxmlTemplateBundle;

/**
 * Page to choose the fields during the creation of a report template
 * 
 * @author Orlandin Marco
 *
 */
public class ReportWizardFieldsDynamicPage extends StaticWizardFieldsPage {

	/**
	 * The template bundle where this page is contained
	 */
	private JrxmlTemplateBundle containerBundle;

	/**
	 * Create the page
	 * 
	 * @param containerBundle The template bundle where this page is contained
	 */
	public ReportWizardFieldsDynamicPage(JrxmlTemplateBundle containerBundle) {
		super();
		this.containerBundle = containerBundle;
	}

	/**
	 * Return the next dynamic page
	 * 
	 * @return the page to define group
	 */
	@Override
	public IWizardPage getNextPage() {
		containerBundle.getStep3().setWizard(getWizard());
		return containerBundle.getStep3();
	}
	
	/**
	 * Return the previous dynamic page
	 * 
	 * @return the page to define the data adapter
	 */
	@Override
	public IWizardPage getPreviousPage() {
		containerBundle.getStep1().setWizard(getWizard());
		return containerBundle.getStep1();
	}
	
	/**
	 * To flip to the next page is required only that the page is completed
	 */
	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}
}
