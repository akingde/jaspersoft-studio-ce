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
package com.jaspersoft.studio.editor.preview.view.report.swt.action;

import java.util.EventObject;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.services.IDisposable;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewerListener;

public abstract class AReportAction extends Action implements IReportViewerListener, IDisposable {
	protected IReportViewer rviewer;

	public AReportAction(IReportViewer rviewer) {
		super();
		this.rviewer = rviewer;
		rviewer.addReportViewerListener(this);
		setEnabled(isActionEnabled());
	}

	public abstract boolean isActionEnabled();

	public void viewerStateChanged(EventObject evt) {
		setEnabled(isActionEnabled());
	}

	public void dispose() {
		rviewer.removeReportViewerListener(this);
	}
}
