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
package com.jaspersoft.studio.kpi.dialog.pages.parameters;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.wizard.Wizard;

/**
 * Wizard to modify or create a new parameter
 * 
 * @author Orlandin Marco
 *
 */
public class ParameterWizard extends Wizard{

	/**
	 * The jasperdesign of the handled kpi
	 */
	private JasperDesign jd;
	
	/**
	 * The element to modify, can be null if the wizard is used
	 * to create a new element. It is used at the end of the wizard to store the
	 * result
	 */
	private ParameterDefinition param;
	
	/**
	 * The page with the controls
	 */
	private ParameterWizardPage page0;
	
	/**
	 * Create the wizard 
	 * 
	 * @param element the element to modify, can be null if the wizard is used
	 * to create a new element, in this case it is equivalent to use the one parameter
	 * Constructor
	 * @param jd the jasperdesign of the handled KPI, must be not null
	 */
	public ParameterWizard(ParameterDefinition param, JasperDesign jd){
		Assert.isNotNull(jd);
		this.param = param;
		this.jd = jd;
	}
	
	/**
	 * Create the wizard for the creation of a new range definition
	 * 
	 * @param jd the jasperdesign of the handled KPI, must be not null
	 */
	public ParameterWizard(JasperDesign jd){
		this(null, jd);
	}
	
	@Override
	public void addPages() {
		page0 = new ParameterWizardPage(param, jd);
		addPage(page0);
	}
	
	/**
	 * If the wizard finish store the result parameter
	 */
	@Override
	public boolean performFinish() {
		param = page0.regenerateParameter();
		return true;
	}
	
	/**
	 * If the wizard is aborted the result parameter is set to null
	 */
	@Override
	public boolean performCancel() {
		param = null;
		return super.performCancel();
	}
	
	/**
	 * Return the parameter after the wizard is closed
	 * 
	 * @return a parameter definition or null if the wizard was aborted
	 */ 
	public ParameterDefinition getParameter(){
		return param;
	}
}
