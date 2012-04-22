package com.jaspersoft.studio.editor.preview;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;

import com.jaspersoft.studio.editor.preview.view.control.VErrorPreview;

public class ReportStatePage extends Page {

	private VErrorPreview errorPreview;

	public ReportStatePage() {
		// do nothing
	}

	/*
	 * (non-Javadoc) Method declared on IPage.
	 */
	public void createControl(Composite parent) {
		errorPreview = new VErrorPreview(parent, null);
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