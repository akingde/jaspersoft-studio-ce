/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.ui;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.designer.AQueryStatus;

public class WizardQueryStatus extends AQueryStatus {
	private WizardPage page;

	public WizardQueryStatus(WizardPage page) {
		this.page = page;
	}

	@Override
	public void showError(final Throwable t) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				page.setErrorMessage(t.getMessage());
			}
		});
	}

	@Override
	public void showError(final String message, Throwable t) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				page.setErrorMessage(message);
			}
		});
	}

	@Override
	public void showWarning(final String msg) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				page.setErrorMessage(null);
				page.setMessage(msg);
			}
		});
	}

	@Override
	public void showInfo(final String msg) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				page.setErrorMessage(null);
				page.setMessage(msg);
			}
		});
	}

}
