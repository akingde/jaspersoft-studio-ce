/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.wizards;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.book.bundle.BookTemplateBundle;
import com.jaspersoft.studio.wizards.CongratulationsWizardPage;
import com.jaspersoft.studio.wizards.ReportNewWizard;
import com.jaspersoft.studio.wizards.datasource.ReportWizardDataSourceDynamicPage;
import com.jaspersoft.studio.wizards.datasource.StaticWizardDataSourcePage;

/**
 * Page to choose the data adapter during the creation of a book template
 * 
 * @author Orlandin Marco
 *
 */
public class BookWizardDataSourceDynamicPage extends StaticWizardDataSourcePage {
	
	/**
	 * The template bundle where this page is contained
	 */
	private BookTemplateBundle containerBundle;
	
	/**
	 * Create the page
	 * 
	 * @param containerBundle The template bundle where this page is contained
	 */
	public BookWizardDataSourceDynamicPage(BookTemplateBundle containerBundle) {
		super();
		this.containerBundle = containerBundle;
	}

	/**
	 * Return the second of the dynamic pages, but only if this page
	 * has discovered some fields, otherwise it return the congratulation page if available.
	 * Otherwise it return the group page
	 */
	@Override
	public IWizardPage getNextPage() {
		//need to call this to run the thread to discover the fields
		super.getNextPage();
		if (!getSettings().containsKey(ReportWizardDataSourceDynamicPage.DISCOVERED_FIELDS) || ((List<?>) getSettings().get(ReportWizardDataSourceDynamicPage.DISCOVERED_FIELDS)).isEmpty()) {
			//no fields discovered, skip page 2
			if(containerBundle.getStep3()!=null){
				containerBundle.getStep3().setWizard(getWizard());
				return containerBundle.getStep3();
			}
			else {
				getSettings().put(BookTemplateBundle.COVER_SETTING,false);
				getSettings().put(BookTemplateBundle.BACK_COVER_SETTING, false);
				getSettings().put(BookTemplateBundle.TOC_SETTING, false);
				CongratulationsWizardPage congratPage = ((ReportNewWizard)getWizard()).getCongratulationsStep();
				congratPage.setWizard(getWizard());
				return congratPage;
			}
		}
		//has discovered some fields, return the fields page
		containerBundle.getStep2().setWizard(getWizard());
		return containerBundle.getStep2();
	}
	
	/**
	 * Return the last of the static pages
	 * 
	 * @return the page to define the file location
	 */
	@Override
	public IWizardPage getPreviousPage() {
		return ((ReportNewWizard)getWizard()).getFileLocationStep();
	}

	/**
	 * Check only the complete to advance to the next page
	 */
	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}
}
