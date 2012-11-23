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
package com.jaspersoft.studio.editor.preview.actions.export;

import java.util.EventObject;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.services.IDisposable;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewerListener;

public abstract class AReportViewerAction extends Action implements IReportViewerListener, IDisposable {

	private IReportViewer reportViewer;

	public AReportViewerAction(IReportViewer viewer) {
		Assert.isNotNull(viewer);
		this.reportViewer = viewer;
		viewer.addReportViewerListener(this);
		setEnabled(calculateEnabled());
	}

	public AReportViewerAction(IReportViewer viewer, int style) {
		super(null, style);
		Assert.isNotNull(viewer);
		this.reportViewer = viewer;
		viewer.addReportViewerListener(this);
		setEnabled(calculateEnabled());
	}

	protected abstract boolean calculateEnabled();

	public void viewerStateChanged(EventObject evt) {
		setEnabled(calculateEnabled());
	}

	protected IReportViewer getReportViewer() {
		return reportViewer;
	}

	@Override
	public void run() {
		BusyIndicator.showWhile(null, new Runnable() {
			public void run() {
				runBusy();
			}
		});
	}

	protected void runBusy() {

	}

	public void dispose() {
		reportViewer.removeReportViewerListener(this);
	}
}
