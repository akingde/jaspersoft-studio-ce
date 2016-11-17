/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource;

import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.wizards.AWizardPage;

public class NewResourcePage extends AWizardPage {
	private APageContent rcontent;

	public NewResourcePage(APageContent rcontent) {
		super(rcontent.getPageName());
		this.rcontent = rcontent;
		rcontent.setPage(this);
		setTitle(rcontent.getName());
		setDescription(rcontent.getName());
	}

	public void createControl(Composite parent) {
		setControl(rcontent.createContent(parent));

		WizardPageSupport.create(this, rcontent.getBindingContext());
	}

	@Override
	public void dispose() {
		rcontent.dispose();
		super.dispose();
	}
	
	@Override
	public boolean isPageComplete() {
		if(rcontent!=null){
			return rcontent.isPageComplete();
		}
		return super.isPageComplete();
	}

	@Override
	protected String getContextName() {
		return null;
	}
}
