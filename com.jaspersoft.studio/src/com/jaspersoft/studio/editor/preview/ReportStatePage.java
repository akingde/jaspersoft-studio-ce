/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;

import com.jaspersoft.studio.editor.preview.view.control.VErrorPreview;
import com.jaspersoft.studio.utils.Console;

public class ReportStatePage extends Page {

	private VErrorPreview errorPreview;
	private Console console;

	public ReportStatePage(Console console) {
		this.console = console;
	}

	/*
	 * (non-Javadoc) Method declared on IPage.
	 */
	public void createControl(Composite parent) {
		errorPreview = new VErrorPreview(parent, null);
		setupConsole(console);
	}

	public void setupConsole(Console console) {
		this.console = console;
		if (console != null)
			console.addErrorPreview(errorPreview);
	}

	@Override
	public void dispose() {
		if (console != null)
			console.removeErrorPreview(errorPreview);
		super.dispose();
	}

	/*
	 * (non-Javadoc) Method declared on IPage.
	 */
	public Control getControl() {
		return errorPreview.getControl();
	}

	public void setFocus() {
		errorPreview.setFocus();
	}

}
