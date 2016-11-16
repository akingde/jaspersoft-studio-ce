/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.wizards;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.book.bundle.BookTemplateBundle;
import com.jaspersoft.studio.wizards.CongratulationsWizardPage;
import com.jaspersoft.studio.wizards.ReportNewWizard;
import com.jaspersoft.studio.wizards.fields.StaticWizardFieldsPage;

/**
 * Page to choose the fields during the creation of a book template
 * 
 * @author Orlandin Marco
 *
 */
public class BookWizardFieldsDynamicPage extends StaticWizardFieldsPage {

	/**
	 * The template bundle where this page is contained
	 */
	private BookTemplateBundle containerBundle;

	/**
	 * Create the page
	 * 
	 * @param containerBundle The template bundle where this page is contained
	 */
	public BookWizardFieldsDynamicPage(BookTemplateBundle containerBundle) {
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
		if(containerBundle.getStep3()!=null) {
			containerBundle.getStep3().setWizard(getWizard());
			return containerBundle.getStep3();
		}
		else {
			CongratulationsWizardPage congratPage = ((ReportNewWizard)getWizard()).getCongratulationsStep();
			congratPage.setWizard(getWizard());
			return congratPage;
		}
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
