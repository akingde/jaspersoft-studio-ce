/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model.command;

import java.util.List;

import com.jaspersoft.studio.components.customvisualization.ui.ComponentDescriptor;
import com.jaspersoft.studio.wizards.JSSWizard;

public class CVCWizard extends JSSWizard {
	private List<ComponentDescriptor> modules;
	private CVCTypeWizardPage page0;

	public CVCWizard(List<ComponentDescriptor> modules) {
		super();
		setNeedsProgressMonitor(false);
		this.modules = modules;
	}

	@Override
	public void addPages() {
		page0 = new CVCTypeWizardPage(modules);
		addPage(page0);
	}

	public ComponentDescriptor getModule() {
		return page0.getModule();
	}
}
