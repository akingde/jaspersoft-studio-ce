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
package com.jaspersoft.studio.wizards.datasource;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.templates.JrxmlTemplateBundle;
import com.jaspersoft.studio.wizards.ReportNewWizard;


/**
 * Page to choose the datasource during the creation of a report template
 * 
 * @author Orlandin Marco
 *
 */
public class ReportWizardDataSourceDynamicPage extends StaticWizardDataSourcePage {
	
	/**
	 * The template bundle where this page is contained
	 */
	private JrxmlTemplateBundle containerBundle;
	
	/**
	 * Create the page
	 * 
	 * @param containerBundle The template bundle where this page is contained
	 */
	public ReportWizardDataSourceDynamicPage(JrxmlTemplateBundle containerBundle) {
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
		ReportNewWizard containerWizard = (ReportNewWizard)getWizard();
		if (!getSettings().containsKey(ReportWizardDataSourceDynamicPage.DISCOVERED_FIELDS) || ((List<?>) getSettings().get(ReportWizardDataSourceDynamicPage.DISCOVERED_FIELDS)).isEmpty()) {
			//no fields discovered
			if (!containerWizard.hasCongratulationStep()) {
				containerBundle.getStep3().setWizard(getWizard());
				return containerBundle.getStep3();
			}
			// jump to the congratulation page
			return containerWizard.getCongratulationsStep();
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
