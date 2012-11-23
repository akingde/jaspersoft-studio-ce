/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
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
