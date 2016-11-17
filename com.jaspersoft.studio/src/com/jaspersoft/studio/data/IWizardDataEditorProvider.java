/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;



import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

/*
 * This Interface provides a way for a DataAdapter to provide an editor during the first step of a wizard. If the data
 * adapter selected by the user in the wizard implements this interface, the editor contributed allows the user to
 * retrieve fields from the data adapter itself.
 * 
 * 
 * An example is a simple text area used to insert a SQL query.
 * 
 * @author gtoffoli
 */
public interface IWizardDataEditorProvider {

	/**
   * Return the name of the language to use
   * Null if a language is not required.
   */
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page);
  
}

