/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards;

/**
 * Listener can detect when something changes in the page of a wizard
 * by implementing this interface.
 * 
 * JSSWizard autoregister itself to get this event for all JSSWizardPage's
 * added to the wizard.
 * 
 * @author gtoffoli
 *
 */
public interface JSSWizardPageChangeListener {

	  /**
	   * 
	   * @param event containing the changed page
	   */
		public void pageChanged(JSSWizardPageChangeEvent event);
	
}
