/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.report.swt.action;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.eclipse.viewer.action.AReportAction;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

public class PreviousPageAction extends AReportAction {

	public PreviousPageAction(IReportViewer rviewer) {
		super(rviewer);
		setText(Messages.PreviousPageAction_actionName); 
		setToolTipText(Messages.PreviousPageAction_actionTolltip); 
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/nav/previous.gif"));//$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/nav/previousd.gif"));//$NON-NLS-1$
	}

	public boolean isActionEnabled() {
		return rviewer.canGotoPreviousPage();
	}

	@Override
	public void run() {
		rviewer.gotoPreviousPage();
	}
}
