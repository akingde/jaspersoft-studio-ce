/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */

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
