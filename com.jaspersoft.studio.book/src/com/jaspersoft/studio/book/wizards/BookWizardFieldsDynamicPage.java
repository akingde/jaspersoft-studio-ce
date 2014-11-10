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

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.book.bundle.BookTemplateBundle;
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
