/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.permission;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;

public class PermissionWizard extends Wizard implements SelectionListener {
	private AMResource res;
	private PermissionPage page0;

	public PermissionWizard(AMResource res) {
		super();
		setWindowTitle(Messages.PermissionWizard_0 + res.getValue().getUriString());
		setNeedsProgressMonitor(true);
		setHelpAvailable(false);
		this.res = res;
	}

	@Override
	public void addPages() {
		page0 = new PermissionPage(res);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		page0.setPermissions();
		return true;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		page0.setPermissions();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
