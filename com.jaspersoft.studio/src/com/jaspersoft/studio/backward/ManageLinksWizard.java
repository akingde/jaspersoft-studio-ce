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
package com.jaspersoft.studio.backward;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

/**
 * Wizard used to edit or add a new link
 * 
 * @author Orlandin Marco
 *
 */
public class ManageLinksWizard extends Wizard {
	
	/**
	 * Page where the controls are displayed
	 */
	private ManageLinksWizardPage page;
	
	/**
	 * Create a wizard to edit an existing property. This wizard
	 * doesn't modify the list directly but return the list that 
	 * can be used as new one
	 */
	public ManageLinksWizard() {
		page = new ManageLinksWizardPage();
	}
	
	@Override
	public void addPages() {
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}
	
	/**
	 * Return the list of the link defined when the dialog is closed
	 * 
	 * @return a not null list of jr links
	 */
	public List<JRDefinition> getElements(){
		return page.getElements();
	}
}
