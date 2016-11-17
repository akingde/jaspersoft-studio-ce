/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardContainer;


public abstract class AWizardPage extends JSSHelpWizardPage {

	public AWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	public AWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public IWizardContainer getContainer() {
		return super.getContainer();
	}
}
