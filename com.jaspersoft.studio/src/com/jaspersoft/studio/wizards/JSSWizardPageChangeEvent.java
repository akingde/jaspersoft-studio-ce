/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards;


/**
 * Event fired by a JSSWizardPage Page to notify a change in the UI.
 * A wizard may react by changing the page set which makes up the wizard
 * in response to the event.
 * 
 * @author gtoffoli
 *
 */
public class JSSWizardPageChangeEvent {
	
	private JSSWizardPage page;
	
	public JSSWizardPage getPage() {
		return page;
	}

	public void setPage(JSSWizardPage page) {
		this.page = page;
	}

	public JSSWizardPageChangeEvent(JSSWizardPage page)
	{
		this.page = page;
	}

}
