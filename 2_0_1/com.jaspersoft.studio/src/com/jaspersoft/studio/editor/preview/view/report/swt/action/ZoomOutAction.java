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

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;

public class ZoomOutAction extends AReportAction {

	public ZoomOutAction(IReportViewer viewer) {
		super(viewer);

		setText("Zoom Out"); //$NON-NLS-1$
		setToolTipText("Zoom out"); //$NON-NLS-1$
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/zoomout-16.png"));
		setDisabledImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/zoomout-16.png"));
	}

	@Override
	public void run() {
		rviewer.zoomOut();
	}

	public boolean isActionEnabled() {
		return rviewer.canZoomOut();
	}
}
