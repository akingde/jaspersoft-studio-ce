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
package com.jaspersoft.studio.kpi.dialog.pages.range;

import org.eclipse.jface.wizard.Wizard;

/**
 * Wizard to modify or create a new range
 * 
 * @author Orlandin Marco
 *
 */
public class RangeWizard extends Wizard{
	
	/**
	 * The element to modify, can be null if the wizard is used
	 * to create a new element. It is used at the end of the wizard to store the
	 * result
	 */
	private RangeDefinition element;
	
	/**
	 * The page with the controls
	 */
	private RangeWizardPage page0;
	
	/**
	 * Create the wizard 
	 * 
	 * @param element the element to modify, can be null if the wizard is used
	 * to create a new element, in this case it is equivalent to the no parameters
	 * constructor
	 */
	public RangeWizard(RangeDefinition element){
		this.element = element;
	}
	
	/**
	 * Create the wizard for the creation of a new range definition
	 */
	public RangeWizard(){
		this(null);
	}
	
	@Override
	public void addPages() {
		page0 = new RangeWizardPage(element);
		addPage(page0);
	}
	
	/**
	 * If the wizard finish store the result range
	 */
	@Override
	public boolean performFinish() {
		element = page0.regenerateRange();
		return true;
	}
	
	/**
	 * If the wizard is aborted the result range is set to null
	 */
	@Override
	public boolean performCancel() {
		element = null;
		return super.performCancel();
	}
	
	/**
	 * Return the result range after the wizard is closed
	 * 
	 * @return a range definition or null if the wizard was aborted
	 */ 
	public RangeDefinition getRange(){
		return element;
	}

}
