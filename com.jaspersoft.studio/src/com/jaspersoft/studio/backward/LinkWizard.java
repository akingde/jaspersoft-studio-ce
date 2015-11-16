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
 * Wizard used to edit or add a new link to download a specific version of JasperReports
 * 
 * @author Orlandin Marco
 *
 */
public class LinkWizard extends Wizard {

	/**
	 * The reference of the modified element if this is an edit operation, or null
	 * if it is an add operation
	 */
	private JRDefinition modifiedElement = null;
	
	/**
	 * The list of the current elements, the add or edit of the element will be one of this list
	 */
	private List<JRDefinition> elements = null;
	
	/**
	 * Page where the controls are displayed
	 */
	private LinkWizardPage page;
	
	/**
	 * Create a wizard to add a new link
	 * 
	 * @param elements the list of links actually defined, must be not null. The new link will be added
	 * on this list
	 */
	public LinkWizard(List<JRDefinition> elements) {
		this.elements = elements;
		page = new LinkWizardPage();
	}
	
	/**
	 * Create a wizard to edit an existing link
	 * 
	 * @param modfiedElement link to modify. This element is not edit directly
	 * @param elements the list of links actually defined, must be not null. The new link will be added
	 * on this list
	 */
	public LinkWizard(JRDefinition modfiedElement, List<JRDefinition> elements) {
		this.elements = elements;
		this.modifiedElement = modfiedElement;
		page = new LinkWizardPage(modfiedElement.getVersion(), modfiedElement.getResourceURL());
	}
	
	@Override
	public void addPages() {
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		if (modifiedElement == null){
			//it was an add, then add the new link to the list
			elements.add(new JRDefinition(page.getURL(), page.getVersion()));
		} else {
			//It was a modify, set the new link on the list
			int index = elements.indexOf(modifiedElement);
			if (index != -1){
				elements.set(index, new JRDefinition(page.getURL(), page.getVersion()));
			}
		}
		return true;
	}
}
