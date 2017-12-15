/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.prm.wizard;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.prm.ParameterSet;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ParameterSetWizard extends Wizard {
	private ParameterSetWizardPage page0;
	private JasperReportsConfiguration jConfig;

	public ParameterSetWizard(JasperReportsConfiguration jConfig) {
		super();
		setWindowTitle(Messages.ParameterSetWizard_0);
		this.jConfig = jConfig;
	}

	public ParameterSet getValue() {
		return page0.getValue();
	}

	public boolean isOverride() {
		return page0.isOverride();
	}

	@Override
	public void addPages() {
		page0 = new ParameterSetWizardPage(jConfig);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}
}
