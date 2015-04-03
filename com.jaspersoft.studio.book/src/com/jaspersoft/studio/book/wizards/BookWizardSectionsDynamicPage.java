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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.book.bundle.BookTemplateBundle;
import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.wizards.CongratulationsWizardPage;
import com.jaspersoft.studio.wizards.JSSWizardPage;
import com.jaspersoft.studio.wizards.ReportNewWizard;

/**
 * Page to choose the book sections during the creation of a book template
 * 
 * @author Orlandin Marco
 *
 */
public class BookWizardSectionsDynamicPage extends JSSWizardPage {

	/**
	 * The template bundle where this page is contained
	 */
	private BookTemplateBundle containerBundle;
	
	/**
	 * Listener to store the settings into the settings map when a checkbox
	 * changes
	 */
	private SelectionAdapter checkBoxSelected = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			Button checkBox = (Button)e.widget;
			boolean value = checkBox.getSelection();
			String properyKey = (String)checkBox.getData();
			getSettings().put(properyKey, value);
		};
		
	};
	
	/**
	 * Create the page
	 * 
	 * @param containerBundle The template bundle where this page is contained
	 */
	public BookWizardSectionsDynamicPage(BookTemplateBundle containerBundle) {
		super("tablepage"); //$NON-NLS-1$
		this.containerBundle = containerBundle;
		setTitle(Messages.BookWizardSectionsDynamicPage_title);
		setDescription(Messages.BookWizardSectionsDynamicPage_description);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Button coverButton = new Button(container, SWT.CHECK);
		coverButton.setText(Messages.BookWizardSectionsDynamicPage_coverButton);
		coverButton.setData(BookTemplateBundle.COVER_SETTING);
		coverButton.addSelectionListener(checkBoxSelected);
		coverButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button tocButton = new Button(container, SWT.CHECK);
		tocButton.setText(Messages.BookWizardSectionsDynamicPage_tocButton);
		tocButton.setData(BookTemplateBundle.TOC_SETTING);
		tocButton.addSelectionListener(checkBoxSelected);
		tocButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button backcoverButton = new Button(container, SWT.CHECK);
		backcoverButton.setText(Messages.BookWizardSectionsDynamicPage_backCoverButton);
		backcoverButton.setData(BookTemplateBundle.BACK_COVER_SETTING);
		backcoverButton.addSelectionListener(checkBoxSelected);
		backcoverButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		coverButton.setSelection(true);
		tocButton.setSelection(true);
		backcoverButton.setSelection(true);
		
		setControl(container);
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	/**
	 * Return the congratulation page if it is available
	 * 
	 * @return the congratulation page or null if the congratulation page
	 * shouldn't be displayed
	 */
	@Override
	public IWizardPage getNextPage() {
		CongratulationsWizardPage congratPage = ((ReportNewWizard)getWizard()).getCongratulationsStep();
		congratPage.setWizard(getWizard());
		return congratPage;
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
	 * and there must be the congratulation page
	 */
	@Override
	public boolean canFlipToNextPage() {
		ReportNewWizard container = (ReportNewWizard)getWizard();
		return isPageComplete() && container.hasCongratulationStep();
	}

}
