/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;

public class ZoomFitPageAction extends AReportAction {

	public ZoomFitPageAction(IReportViewer viewer) {
		super(viewer);

		setText(Messages.ZoomFitPageAction_actionName); 
		setToolTipText(Messages.ZoomFitPageAction_actionTooltip); 
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/zoomfitpage.gif"));//$NON-NLS-1$
		setDisabledImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/zoomfitpaged.gif"));//$NON-NLS-1$
		update();
	}

	private void update() {
		setChecked(rviewer.getZoomMode() == IReportViewer.ZOOM_MODE_FIT_PAGE);
	}

	@Override
	public void viewerStateChanged(EventObject evt) {
		super.viewerStateChanged(evt);
		update();
	}

	@Override
	public void run() {
		rviewer.setZoomMode(IReportViewer.ZOOM_MODE_FIT_PAGE);
		update();
	}

	public boolean isActionEnabled() {
		return rviewer.canChangeZoom();
	}

}
